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
	@Column(precision = 9, scale = 6)
	private double latitude;
	
	@NotNull
	@Column(precision = 9, scale = 6)
	private double longitude;
	
	@NotNull
	@Size(min = 1, max = 20)
	private String name;
}
