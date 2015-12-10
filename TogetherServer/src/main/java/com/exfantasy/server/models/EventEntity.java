package com.exfantasy.server.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "events")
public class EventEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long eventId;
	
	@NotNull
	private long createUserId;
	
	@NotNull
	@Column(columnDefinition="Decimal(10,6) default '0.0'")
	private double latitude;
	
	@NotNull
	@Column(columnDefinition="Decimal(10,6) default '0.0'")
	private double longitude;
	
	@NotNull
	@Column(columnDefinition="varchar(50) default ''")
	private String name;
	
	@NotNull
	@Column(columnDefinition="varchar(200) default ''")
	private String content;
	
	@NotNull
	@Column(columnDefinition="Decimal(5,0) default '0'")
	private int attendeeNum;
	
	@NotNull
	@Column(columnDefinition="Decimal(8,0) default '0'")
	private int date;
	
	@NotNull
	@Column(columnDefinition="Decimal(8,0) default '0'")
	private int time;
	
	@ManyToMany
    @JoinTable(
        name="event_user",
        joinColumns=@JoinColumn(name="event_fk"),
        inverseJoinColumns=@JoinColumn(name="user_fk")
    )
    private Set<UserEntity> userEntitys = new HashSet<>();
	
	@ManyToMany
	@JoinTable(
		name="event_message",
		joinColumns=@JoinColumn(name="event_fk"),
		inverseJoinColumns=@JoinColumn(name="message_fk")
	)
	private Set<MessageEntity> messageEntitys = new HashSet<>();
	
	public EventEntity() {
	}
	
	public EventEntity(long createUserId, double latitude, double longitude, String name, String content, int attendeeNum, int date, int time) {
		this.createUserId = createUserId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.content = content;
		this.attendeeNum = attendeeNum;
		this.date = date;
		this.time = time;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(long createUserId) {
		this.createUserId = createUserId;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getAttendeeNum() {
		return attendeeNum;
	}

	public void setAttendeeNum(int attendeeNum) {
		this.attendeeNum = attendeeNum;
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

	public Set<UserEntity> getUserEntitys() {
		return userEntitys;
	}

	public void setUserEntitys(Set<UserEntity> userEntitys) {
		this.userEntitys = userEntitys;
	}
	
	public Set<MessageEntity> getMessageEntitys() {
		return messageEntitys;
	}

	public void setMessageEntitys(Set<MessageEntity> messageEntitys) {
		this.messageEntitys = messageEntitys;
	}

	@Override
	public String toString() {
		return "EventEntity [eventId=" + eventId + ", createUserId=" + createUserId + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", name=" + name + ", content=" + content + ", attendeeNum="
				+ attendeeNum + ", date=" + date + ", time=" + time + ", user-counts=" + userEntitys.size()
				+ ", message-counts=" + messageEntitys.size() + "]";
	}
}
