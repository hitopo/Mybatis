package com.imooc.dao;

import com.imooc.bean.Command;

import java.util.List;
import java.util.Map;

/**
 * 和command.xml配置文件对应的接口
 */
public interface ICommand {

    /**
     * 查询command
     * @param parameters 指令参数Map
     * @return 指令列表
     */
    List<Command> queryCommandList(Map<String, Object> parameters);


    /**
     *  查询指令总数
     * @return 总数
     */
     int count(Command command);
}
