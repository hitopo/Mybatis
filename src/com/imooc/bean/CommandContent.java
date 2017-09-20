package com.imooc.bean;

/**
 * 命令对应的内容
 */
public class CommandContent {
    private int id;  //主键
    private String content;  //回复内容
    private int commandId;  //外键
    private Command command;  //一对多关系，主指令引用

    public CommandContent() {
    }

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

    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "CommandContent{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", commandId='" + commandId + "'}";
    }
}
