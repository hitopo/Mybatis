package com.imooc.service;

import com.imooc.bean.Command;
import com.imooc.bean.CommandContent;
import com.imooc.dao.CommandDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 维护service
 */
public class MaintainService {
    private CommandDao commandDao = new CommandDao();

    /**
     * 单个删除
     *
     * @param id 删除文件id
     */
    public void deleteOne(String id) {
        //注意id从String转换成int
        //执行业务逻辑
        if (id != null && !"".equals(id)) {
            commandDao.deleteOne(Integer.valueOf(id));
        }
    }

    public void deleteBatch(String[] ids) {
        List<Integer> idList = new ArrayList<>();
        for (String id : ids) {
            idList.add(Integer.valueOf(id));
        }
        commandDao.deleteBatch(idList);
    }

    /**
     * 添加新的命令
     * @param name 指令名称
     * @param description  指令描述
     * @param contents 内容
     */
    public void addOne(String name,String description,String[] contents) {
        //初始化参数
        Command command = new Command();
        command.setName(name);
        command.setDescription(description);

        //添加contents需要判断有没有空的，否则不予添加
        List<CommandContent> contentList = new ArrayList<>();
        for (String content : contents) {
            //字符串非空也不是空格添加进列表
            if(content!=null&&!"".equals(content.trim())) {
                CommandContent commandContent = new CommandContent();
                commandContent.setContent(content);
                //注意这个地方commandId并没有设置
                //只有在sql运行之后，才能够得到外键id
                //这里放在dao层去解决
                contentList.add(commandContent);
            }
        }
        command.setContentList(contentList);

        //调用dao层完成数据库操作
        commandDao.addOne(command);
    }

    /**
     * 修改指令
     *
     * @param name        指令名称
     * @param description 指令描述
     * @param contents    指令内容
     */
    public void modifyOne(String id,String name, String description, String[] contents) {
        if(id==null || "".equals(id)) {
            return;
        }
        //组合参数
        Command command = new Command();
        command.setId(Integer.valueOf(id));
        command.setName(name);
        command.setDescription(description);
        List<CommandContent> contentList = new ArrayList<>();
        for (String content : contents) {
            if (content != null && !"".equals(content.trim())) {
                CommandContent commandContent = new CommandContent();
                commandContent.setContent(content);
                contentList.add(commandContent);
            }
        }
        command.setContentList(contentList);

        //调用到dao层完成修改操作
        commandDao.modifyOne(command);
    }
}
