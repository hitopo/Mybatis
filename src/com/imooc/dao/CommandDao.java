package com.imooc.dao;

import com.imooc.bean.Command;
import com.imooc.bean.CommandContent;
import com.imooc.db.DbAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 与指令表对应的数据库方法
 */
public class CommandDao {
    /**
     * 通过mybatis访问数据库实现查询操作
     */
    public List<Command> queryCommandList(Map<String,Object> parameters) {
        List<Command> CommandList = new ArrayList<>();
        SqlSession sqlSession = null;
        try {
            sqlSession = DbAccess.getSqlSession();
            ICommand iCommand = sqlSession.getMapper(ICommand.class);
            CommandList = iCommand.queryCommandList(parameters);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return CommandList;
    }

    /**
     * 删除单条信息
     *
     * @param id 删除消息的id
     */
    public void deleteOne(int id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = DbAccess.getSqlSession();
            sqlSession.delete("Command.deleteOne", id);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * 批量删除数据
     *
     * @param ids 想要删除的id列表
     */
    public void deleteBatch(List<Integer> ids) {
        SqlSession sqlSession = null;
        try {
            sqlSession = DbAccess.getSqlSession();
            sqlSession.delete("Command.deleteBatch", ids);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * 添加新的command内容
     *
     * @param command 添加的指令参数
     *                添加操作必须分开
     *                mybatis不像hibernate可以级联插入
     *                只能自己书写sql语句分开单独插入所有内容
     */
    public void addOne(Command command) {
        System.out.println(command);
        SqlSession sqlSession = null;
        try {
            sqlSession = DbAccess.getSqlSession();
            //先插入command数据，先提交，否则不满足外键约束，添加不了数据
            sqlSession.insert("Command.addOne", command);
            sqlSession.commit();
            sqlSession.close();
            //重新获取sqlsession，使得两次插入操作分开
            sqlSession = DbAccess.getSqlSession();
            List<CommandContent> contents = command.getContentList();
            //设置commandId
            for (CommandContent content : contents) {
                content.setCommandId(command.getId());
            }
            //再插入content数据
            sqlSession.insert("CommandContent.addBatch", contents);
            //提交事务，完成添加
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * 查询单个command对象
     *
     * @param id 查询条件
     * @return command对象
     */
    public Command queryOneById(int id) {
        Command command = new Command();
        SqlSession sqlSession = null;
        try {
            sqlSession = DbAccess.getSqlSession();
            command = sqlSession.selectOne("Command.queryOneById", id);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return command;
    }

    /**
     * 修改单个对象
     *
     * @param command 参数
     */
    public void modifyOne(Command command) {
        SqlSession sqlSession = null;
        try {
            sqlSession = DbAccess.getSqlSession();
            sqlSession.update("Command.modifyOne", command);
            sqlSession.commit();
            sqlSession.close();
            //提交事务
            sqlSession = DbAccess.getSqlSession();
            sqlSession.update("Content.modifyBatch",command);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * 查询记录总条数
     * @param command 指令对象
     * @return 总条数
     */
    public int count(Command command) {
        int countNum = 0;
        SqlSession sqlSession = null;
        try {
            sqlSession = DbAccess.getSqlSession();
            ICommand iCommand = sqlSession.getMapper(ICommand.class);
            //从数据库查询得到总条数
            countNum = iCommand.count(command);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null) {
                sqlSession.close();
            }
        }
        return countNum;

    }

}
