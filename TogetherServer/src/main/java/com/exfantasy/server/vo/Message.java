package com.exfantasy.server.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "message")
public class Message {
	/**
	 * 留言 ID
	 */
	private long messageId;
	/**
	 * 建立留言使用者 ID
	 */
	private long createUserId;
	/**
	 * 建立留言使用者暱稱
	 */
	private String createUserName;
	/**
	 * 留言內容
	 */
	private String content;
	/**
	 * 留言日期
	 */
	private int date;
	/**
	 * 留言時間
	 */
	private int time;
	
	public Message() {
	}

	public Message(long messageId, long createUserId, String createUserName, String content, int date, int time) {
		this.messageId = messageId;
		this.createUserId = createUserId;
		this.createUserName = createUserName;
		this.content = content;
		this.date = date;
		this.time = time;
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public long getCreateUserId() {
		return createUserId;
	}
	
	public void setCreateUserId(long createUserId) {
		this.createUserId = createUserId;
	}
	
	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", createUserId=" + createUserId + ", createUserName="
				+ createUserName + ", content=" + content + ", date=" + date + ", time=" + time + "]";
	}
}
