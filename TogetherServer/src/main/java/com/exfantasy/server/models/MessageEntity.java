package com.exfantasy.server.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "messages")
public class MessageEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long messageId;
	
	@NotNull
	private long createUserId;
	
	@NotNull
	@Column(columnDefinition="varchar(200) default ''")
	private String content;
	
	@NotNull
	@Column(columnDefinition="Decimal(8,0) default '0'")
	private int date;
	
	@NotNull
	@Column(columnDefinition="Decimal(8,0) default '0'")
	private int time;
	
	public MessageEntity() {
	}

	public MessageEntity(long createUserId, String content, int date, int time) {
		this.createUserId = createUserId;
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
		return "MessageEntity [messageId=" + messageId + ", createUserId=" + createUserId + ", content=" + content
				+ ", date=" + date + ", time=" + time + "]";
	}
}
