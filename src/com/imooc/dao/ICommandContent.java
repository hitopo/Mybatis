package com.imooc.dao;

import com.imooc.bean.Command;
import com.imooc.bean.CommandContent;

import java.util.List;

/**
 * CommandContent配置文件对应的接口
 */
public interface ICommandContent {

    /**
     * 批量添加
     * @param contents 内容
     */
    void addBatch(List<CommandContent> contents);

    /**
     * 修改内容
     * @param command 具体内容
     */
    void modifyBatch(Command command);

    /**
     * 删除所有内容
     * @param command 内容
     */
    void deleteBatch(Command command);
}
