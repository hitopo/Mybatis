package com.imooc.interceptor;

import com.imooc.entity.Page;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

/**
 * 分页拦截器
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class,Integer.class})})
public class PageInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        //使用metaobject通过反射访问mybatis里面的对象，获取传递过去的sql参数
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        //获取配置文件里面的sql语句
        //进行判断是否需要进行分页
        String id = mappedStatement.getId();
        //以ByPage结尾的视作需要分页
        if (id.matches(".+ByPage$")) {
            BoundSql boundSql = statementHandler.getBoundSql();
            //获取传递过去的sql语句
            String sql = boundSql.getSql();
            //获取总条数
            String countSql = "select count(*) from (" + sql + ")a";
            Connection connection = (Connection) invocation.getArgs()[0];
            PreparedStatement connectionStatement = connection.prepareStatement(countSql);
            ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
            parameterHandler.setParameters(connectionStatement);
            ResultSet rs = connectionStatement.executeQuery();
            //获得传递的参数
            Map<?,?> parameter = (Map<?, ?>) boundSql.getParameterObject();
            Page page = (Page) parameter.get("page");
            if(rs.next()) {
                //设置总条数
                page.setTotalNumber(rs.getInt(1));
            }
            String pageSql = sql + " limit " + page.getDbIndex() + "," + page.getDbNumber();
            //替换mybatis内部的sql语句
            metaObject.setValue("delegate.boundSql.sql", pageSql);
        }

        //将控制权交回给mybatis
        return invocation.proceed();

    }

    /**
     * 每次调用都会调用该方法
     * 在这里面使用动态代理
     */
    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o,this);
    }

    /**
     * 该方法可以从配置文件中获取属性值
     * 在分页拦截器中作用不大
     */
    @Override
    public void setProperties(Properties properties) {

    }
}
