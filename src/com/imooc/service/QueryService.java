package com.imooc.service;

import com.imooc.bean.Command;
import com.imooc.bean.CommandContent;
import com.imooc.dao.CommandDao;
import com.imooc.util.Iconst;

import java.util.List;
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
    public List<Command> queryCommandList(String command, String description) {
        return commandDao.queryCommandList(command, description);
    }

    public String queryByCommand(String command) {
        List<Command> commandList = null;
        //用户输入的是“帮助”
        if (Iconst.HTLP_COMMAND.equals(command)) {
            commandList = commandDao.queryCommandList(null, null);
            StringBuilder str = new StringBuilder();
            if (commandList.size() > 0) {
                for (int i = 0; i < commandList.size(); i++) {
                    if (i != 0) {
                        //第二行之后追加换行
                        str.append("<br/>");
                        str.append("回复[" + commandList.get(i).getName() + "]即可查看" + commandList.get(i).getDescription());
                    }
                }
                return str.toString();
            } else {
                //如果没有数据返回NO_AVAIABLE_COMMAND
                return Iconst.NO_AVAIABLE_COMMAND;
            }
        }

        commandList = commandDao.queryCommandList(command, null);
        if (commandList.size() > 0) {
            //后台确实有数据
            List<CommandContent> contentList = commandList.get(0).getContentList();
            int index = new Random().nextInt(contentList.size());
            return contentList.get(index).getContent();
        }
        //没有查询到数据
        return Iconst.NO_MATCH_CONTENT;
    }

}
