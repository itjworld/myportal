package com.gspann.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ashish Jaiswal
 *
 */
@Entity
@Table(name="TBL_USERS")
public class Users implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8261421847874103779L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false,unique=true)
	private long id;
	
	@Column(name="user_name",nullable=false,unique=true,length=100)
	private String username;
	
	@Column(name="password",nullable=false,length=255)
	private String password;
	
	@Column(name="enabled",nullable=false)
	private boolean enabled=true;
	
	@Column(name="accountNonExpired",nullable=false)
	private boolean accountNonExpired=true;
	
	@Column(name="credentialsNonExpired",nullable=false)
	private boolean credentialsNonExpired=true;
	
	@Column(name="accountNonLocked",nullable=false)
	private boolean accountNonLocked=true;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	
	
	
	
	
	
	
	
	

}
