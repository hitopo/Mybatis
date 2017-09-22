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

    /**
     * 删除指令
     * @param id 指令id
     */
    void deleteOne(int id);

    /**
     * 批量删除
     * @param ids 指令ids
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 添加一条指令
     * @param command 指令
     */
    void addOne(Command command);

    /**
     * 查询command
     * @param id id
     * @return 指令对象
     */
    Command queryOneById(int id);

    /**
     * 修改指令
     * @param command 指令数据
     */
    void modifyOne(Command command);
}
