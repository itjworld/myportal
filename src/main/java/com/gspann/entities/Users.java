package com.gspann.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private String userName;
	
	@Column(name="password",nullable=false,length=100)
	private String password;
	
	@Column(name="enabled",nullable=false)
	private boolean enabled=true;
	

}
