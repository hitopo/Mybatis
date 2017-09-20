package com.imooc.dao;


import com.imooc.bean.Message;

import java.util.List;

/**
 * 和message.xml配置文件中对应的接口
 * 实现面向接口式编程
 */
public interface IMessage {
    /**
     * 查询所有messageList
     * @return message列表
     */
    public List<Message> queryMessageList(Message message);
}
