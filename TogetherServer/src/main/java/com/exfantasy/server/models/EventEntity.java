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
import javax.validation.constraints.Size;

@Entity
@Table(name = "events")
public class EventEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long event_id;
	
	@NotNull
//	@Size(max = 20)
	private long create_user_id;
	
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
	@Column(columnDefinition="Decimal(17,0) default '0'")
	private long time;
	
	@ManyToMany
    @JoinTable(
        name="event_user",
        joinColumns=@JoinColumn(name="event_fk"),
        inverseJoinColumns=@JoinColumn(name="user_fk")
    )
    private Set<UserEntity> userEntitys = new HashSet<UserEntity>();
	
	public EventEntity() {
	}
	
	public EventEntity(long create_user_id, double latitude, double longitude, String name, String content, int attendeeNum, long time) {
		this.create_user_id = create_user_id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.content = content;
		this.attendeeNum = attendeeNum;
		this.time = time;
	}

	public long getEventId() {
		return event_id;
	}

	public void setEventId(long event_id) {
		this.event_id = event_id;
	}

	public long getCreate_user_id() {
		return create_user_id;
	}

	public void setCreate_user_id(long create_user_id) {
		this.create_user_id = create_user_id;
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

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Set<UserEntity> getUserEntitys() {
		return userEntitys;
	}

	public void setUserEntitys(Set<UserEntity> userEntitys) {
		this.userEntitys = userEntitys;
	}

	@Override
	public String toString() {
		return "EventEntity [event_id=" + event_id + ", create_user_id=" + create_user_id + ", latitude=" + latitude + ", longitude=" + longitude + ", name=" + name
				+ ", content=" + content + ", attendeeNum=" + attendeeNum + ", time=" + time + "]";
	}
}
