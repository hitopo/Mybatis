package com.imooc.bean;

/**
 * 数据参数Javabean
 */
public class Message {
    private Integer id;   //主键id
    private String command;  //命令（关键字）
    private String description; //描述
    private String content;  // 回复内容

    public Message(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", command='" + command + '\'' +
                ", description='" + description + '\'' +
                ", comment='" + content + '\'' +
                '}';
    }


}
