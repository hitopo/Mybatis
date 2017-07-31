package com.imooc.dao;

import com.imooc.bean.Command;
import com.imooc.db.DbAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 与指令表对应的数据库方法
 */
public class CommandDao {
    /**
     * 通过mybatis访问数据库实现查询操作
     */
    public List<Command> queryCommandList(String name, String description) {
        List<Command> CommandList = new ArrayList<>();
        SqlSession sqlSession = null;
        try {
            sqlSession = DbAccess.getSqlSession();
            Command Command = new Command();
            Command.setName(name);
            Command.setDescription(description);
            CommandList = sqlSession.selectList("Command.queryCommandList",Command);
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
     * @param id 删除消息的id
     */
    public void deleteOne(int id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = DbAccess.getSqlSession();
            sqlSession.delete("Command.deleteOne",id);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

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
}
