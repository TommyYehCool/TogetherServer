package com.exfantasy.server.models;

import java.util.Set;

import javax.persistence.CascadeType;
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
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long user_id;

	@NotNull
	@Size(min = 10, max = 30)
	private String email;
	
	@NotNull
	@Size(min = 6, max = 20)
	private String password;

	@NotNull
	@Size(min = 1, max = 30)
	private String name;
	
	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
        name="user_event",
        joinColumns={@JoinColumn(name="user_fk")},
        inverseJoinColumns={@JoinColumn(name="event_fk")}
    )
    private Set<EventEntity> eventEntitys;
	
	public UserEntity() {
	}
	
	public UserEntity(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}

	public long getUserId() {
		return user_id;
	}

	public void setUserId(long user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<EventEntity> getEventEntitys() {
		return eventEntitys;
	}

	public void setEventEntitys(Set<EventEntity> eventEntitys) {
		this.eventEntitys = eventEntitys;
	}
	
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", email=" + email + ", password=" + password + ", name=" + name + "]";
	}
}