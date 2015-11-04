package com.exfantasy.server.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "event")
public class Event {
	/**
	 * 事件 ID
	 */
	private long id;
	/**
	 * 緯度
	 */
	private double latitude;
	/**
	 * 經度
	 */
	private double longitude;
	/**
	 * 事件名稱
	 */
	private String name;
	/**
	 * 事件內容
	 */
	private String content;
	/**
	 * 參加人數
	 */
	private int attendeeNum;
	/**
	 * 事件時間
	 */
	private long time;
	
	public Event() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "EventEntity [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", name=" + name
				+ ", content=" + content + ", attendeeNum=" + attendeeNum + ", time=" + time + "]";
	}
}