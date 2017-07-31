package com.imooc.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * 获取数据库连接
 */
public  class DbAccess {
    /**
     *
     * @return sqlSession对象
     * @throws IOException 将异常抛给dao层处理
     */
    public static SqlSession getSqlSession() throws IOException {
        //读取配置文件
        Reader reader = Resources.getResourceAsReader("com/imooc/config/configuration.xml");
        //通过配置文件构建一个SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //通过sqlSessionFactory创建一个sqlSession
        return sqlSessionFactory.openSession();
    }
}
