package com.dade.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * user dto
 * @author darren.ouyang <ouyang.darren@gmail.com>
 * @version 1.0
 * @date 2016/7/14
 * @copyright Copyright (c) 2016, darren.ouyang
 */
public class UserDto implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String userId;

    // 基本信息
    private Integer gender;             // 性别[男|女]-统一使用com.hunterplus.common.domain.Gender
    private String headImageKey;        // 头像图片-key
    private String headImageUrl;        // 头像图片-url
    private String name;                // 发送者名称
    private String nick;                // 昵称

    private String mobile;              // 手机号码 unique
    private String email;               // 电子邮件地址 unique
    private Integer type;               // 用户类型[猎头顾问|PM|审核员]

    private String cityId;              // 所在城市ID
    private String provinceId;          // 所在省份ID
    private String countryId;           // 所在国家ID

    private String cityName;            // 所在城市名字
    private String provinceName;        // 所在省份名字
    private String countryName;         // 所在国家名字

    private Integer status;             // 用户状态[正常|暂停|禁用]
    private Boolean hasLoginAfterAuth;  // 认证后是否已经登陆过
    private Date createDate;            // 创建日期
    private Date modifyDate;            // 修改日期
    private Date lastLoginDate;         // 上次登录日期

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

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getHasLoginAfterAuth() {
        return hasLoginAfterAuth;
    }

    public void setHasLoginAfterAuth(Boolean hasLoginAfterAuth) {
        this.hasLoginAfterAuth = hasLoginAfterAuth;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId='" + userId + '\'' +
                ", gender=" + gender +
                ", headImageKey='" + headImageKey + '\'' +
                ", headImageUrl='" + headImageUrl + '\'' +
                ", name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                ", cityId='" + cityId + '\'' +
                ", provinceId='" + provinceId + '\'' +
                ", countryId='" + countryId + '\'' +
                ", cityName='" + cityName + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", status=" + status +
                ", hasLoginAfterAuth=" + hasLoginAfterAuth +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", lastLoginDate=" + lastLoginDate +
                '}';
    }
}
