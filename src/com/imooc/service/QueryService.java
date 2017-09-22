package com.imooc.service;

import com.imooc.bean.Command;
import com.imooc.bean.CommandContent;
import com.imooc.dao.CommandDao;
import com.imooc.entity.Page;
import com.imooc.util.Iconst;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 列表相关的业务逻辑
 */
public class QueryService {
    private CommandDao commandDao = new CommandDao();

    /**
     * 显示后台数据
     *
     * @param command     查询参数
     * @param description 查询参数
     * @return 数据列表
     */
    public List<Command> queryCommandList(String command, String description, Page page) {
        //组织Command对象
        Command messageCommand = new Command();
        messageCommand.setName(command);
        messageCommand.setDescription(description);
        //获取总页数
        CommandDao commandDao = new CommandDao();
        int totalNumber = commandDao.count(messageCommand);
        page.setTotalNumber(totalNumber);
        page.count();
        //放置参数
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("command",messageCommand);
        parameters.put("page",page);
        //获取返回结果
        return commandDao.queryCommandList(parameters);
    }

    /**
     * 根据用户发送的关键词查询
     * @param command 关键词
     * @return 展现给用户的内容
     */
    public String queryByCommand(String command) {
        List<Command> commandList;
        Map<String,Object> params = new HashMap<>();
        Command messageCommand = new Command();
        params.put("command",messageCommand);
        //params.put("page",new Page());
        //用户输入的是“帮助”
        if (Iconst.HTLP_COMMAND.equals(command)) {
            commandList = commandDao.queryCommandList(params);
            StringBuilder str = new StringBuilder();
            if (commandList.size() > 0) {
                for (int i = 0; i < commandList.size(); i++) {
                    if (i != 0) {
                        //第二行之后追加换行
                        str.append("<br/>")
                                .append("回复[")
                                .append(commandList.get(i).getName())
                                .append("]即可查看")
                                .append(commandList.get(i).getDescription());
                    }
                }
                return str.toString();
            } else {
                //如果没有数据返回NO_AVAIABLE_COMMAND
                return Iconst.NO_AVAIABLE_COMMAND;
            }
        }

        //用户输入的不是“帮助”
        messageCommand.setName(command);
        commandList = commandDao.queryCommandList(params);
        if (commandList.size() > 0) {
            //后台确实有数据
            List<CommandContent> contentList = commandList.get(0).getContentList();
            if(contentList.size()>0) {
                int index = new Random().nextInt(contentList.size());
                return contentList.get(index).getContent();
            } else {
                //没有content数据
                return  Iconst.NO_AVAIABLE_CONTENT;
            }
        }
        //没有查询到数据
        return Iconst.NO_MATCH_CONTENT;
    }

    /**
     * 获取command对象
     * @param id 查询id
     * @return command对象
     */
    public Command queryOneById(String id) {
        if(id!=null && !"".equals(id)) {
            int i = Integer.valueOf(id);
            return commandDao.queryOneById(i);
        }
        return null;
    }
}
