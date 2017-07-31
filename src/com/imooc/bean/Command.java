package com.imooc.bean;

import java.util.List;

/**
 * 命令实体类
 */
public class Command {
    private String id; //主键，命令id
    private String name;  //命令名称
    private String description;   //命令描述
    //内容列表
    private List<CommandContent> contentList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CommandContent> getContentList() {
        return contentList;
    }

    public void setContentList(List<CommandContent> contentList) {
        this.contentList = contentList;
    }
}
