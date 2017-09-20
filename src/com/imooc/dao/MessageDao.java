package com.imooc.dao;

import com.imooc.bean.Message;
import com.imooc.db.DbAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Message实体类有关的操作
 */
public class MessageDao {

    /**
     * 通过mybatis访问数据库实现查询操作
     */
    public List<Message> queryMessageList(String command, String description) {
        List<Message> messageList = new ArrayList<>();
       SqlSession sqlSession = null;
        try {
            sqlSession = DbAccess.getSqlSession();
            Message message = new Message();
            message.setCommand(command);
            message.setDescription(description);
            //使用面向接口式编程
            IMessage iMessage = sqlSession.getMapper(IMessage.class);
            messageList = iMessage.queryMessageList(message);
            //messageList = sqlSession.selectList("queryMessageList",message);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return messageList;

    }

    /**
     * 删除单条信息
     * @param id 删除消息的id
     */
    public void deleteOne(int id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = DbAccess.getSqlSession();
            sqlSession.delete("Message.deleteOne",id);
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
            sqlSession.delete("Message.deleteBatch",ids);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

        public static void main(String[] args) {
        MessageDao dao = new MessageDao();
        List<Message> list = dao.queryMessageList(null,null);
        for (Message message : list) {
            System.out.println(message);
        }
    }

//    /**
// //     * 查询参数列表(JDBC操作)
// //     *
// //     * @return 返回查询到的Message数组
// //     */
//    public List<Message> queryMessageList(String command, String description) {
//        List<Message> list = new ArrayList<>();
//        try {
//            //加载jdbc驱动
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf-8",
//                    "root", "");
//            //这里注意，写sql语句时由于字段不多，最好不要使用*，而是将使所有字段写出来
//            //避免sql驱动解析，可以加快速度
//            //sql语句中字段名是大小写不敏感的，大小写都是一样的
//            StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from message where 1=1");
//            //动态加载参数时，可以使用一个参数列表
//            List<String> params = new ArrayList<>();
//            if (command != null && !"".equals(command.trim())) {
//              sql.append(" and COMMAND = ?");
//              params.add(command);
//            }
//            if (description != null && !"".equals(description.trim())) {
//                //这里like前后必须添加空格
//                sql.append(" and DESCRIPTION like '%' ? '%'");
//                params.add(description);
//            }
//            PreparedStatement statement = conn.prepareStatement(sql.toString());
//            //设置sql语句中的参数
//            //原生jdbc小技巧，比较复杂
//            for (int i = 0; i < params.size(); i++) {
//                statement.setString(i + 1, params.get(i));
//            }
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                Message message = new Message();
//                //因为列表中存储的是引用，所以先添加进集合也可以，避免忘记
//                list.add(message);
//                message.setId(rs.getString("ID"));
//                message.setCommand(rs.getString("COMMAND"));
//                message.setDescription(rs.getString("DESCRIPTION"));
//                message.setContent(rs.getString("CONTENT"));
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//


}

