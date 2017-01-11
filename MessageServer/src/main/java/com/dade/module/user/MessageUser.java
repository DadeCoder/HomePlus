package com.dade.module.user;

/**
 * 用户模型
 * Created by dade on 2016/1/10
 */
public class MessageUser
{
    private String userId;          // 用户ID
    private Integer userType;       // 用户类型, 0root, 1user
    private String name;            // 名称
    private String nick;            // 昵称
    private Integer gender;         // 性别[男|女]-统一使用
    private String headImageKey;    // 头像图片-key
    private String headImageUrl;    // 头像图片-url

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getHeadImageKey() {
        return headImageKey;
    }

    public void setHeadImageKey(String headImageKey) {
        this.headImageKey = headImageKey;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    @Override
    public String toString() {
        return "MessageUser{" +
                "userId='" + userId + '\'' +
                ", userType=" + userType +
                ", name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                ", gender=" + gender +
                ", headImageKey='" + headImageKey + '\'' +
                ", headImageUrl='" + headImageUrl + '\'' +
                '}';
    }
}
