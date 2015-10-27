package com.exfantasy.server.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "activities")
public class Activity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	@Column(columnDefinition="Decimal(10,6) default '0.0'")
	private double latitude;
	
	@NotNull
	@Column(columnDefinition="Decimal(10,6) default '0.0'")
	private double longitude;
	
	@NotNull
	@Size(min = 1, max = 30)
	private String name;
	
	@NotNull
	@Column(columnDefinition="Decimal(3,0) default '0'")
	private int numberOfPeople;
	
	@NotNull
	@Size(min = 10, max = 50)
	private String remark;

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

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", name=" + name
				+ ", numberOfPeople=" + numberOfPeople + ", remark=" + remark + "]";
	}
}
