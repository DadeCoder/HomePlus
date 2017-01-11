package com.dade.dto;

/**
 * 邮箱用户
 * 
 * @author zengxf
 */
public class EmailUserDto {

    private String userId;
    private String emailAccount;
    private String emailPassword;

    public EmailUserDto() {
	super();
    }

    public EmailUserDto( String userId, String emailAccount, String emailPassword ) {
	super();
	this.userId = userId;
	this.emailAccount = emailAccount;
	this.emailPassword = emailPassword;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId( String userId ) {
	this.userId = userId;
    }

    public String getEmailAccount() {
	return emailAccount;
    }

    public void setEmailAccount( String emailAccount ) {
	this.emailAccount = emailAccount;
    }

    public String getEmailPassword() {
	return emailPassword;
    }

    public void setEmailPassword( String emailPassword ) {
	this.emailPassword = emailPassword;
    }

}
