package com.exfantasy.server.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "login_result")
public class LoginResult {
	/**
	 * 登入結果
	 */
	private int resultCode;
	/**
	 * 登入結果訊息
	 */
	private String resultMsg;
	/**
	 * User Id
	 */
	private long userId;
	/**
	 * 暱稱
	 */
	private String name;
	/**
	 * 發起活動需要
	 */
	private String email;
	/**
	 * 頭像 url
	 */
	private String userIconUrl;
	
	public LoginResult() {
	}
	
	public LoginResult(int resultCode, long userId, String name, String email, String userIconUrl) {
		this.resultCode = resultCode;
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.userIconUrl = userIconUrl;
	}

	public LoginResult(int resultCode, String errMsg) {
		this.resultCode = resultCode;
		this.resultMsg = errMsg;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserIconUrl() {
		return userIconUrl;
	}

	public void setUserIconUrl(String userIconUrl) {
		this.userIconUrl = userIconUrl;
	}
	
	@Override
	public String toString() {
		return "LoginResult [resultCode=" + resultCode + ", resultMsg=" + resultMsg + ", userId=" + userId + ", name="
				+ name + ", email=" + email + ", userIconUrl=" + userIconUrl + "]";
	}
}
