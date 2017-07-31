package com.imooc.bean;

/**
 * 命令对应的内容
 */
public class CommandContent {
    private int id;  //主键
    private String content;  //回复内容
    private String commandId;  //外键

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }
}
