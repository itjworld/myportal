package com.gspann.entities;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author Ashish Jaiswal
 *
 */
@Entity
//@Cacheable
//@Cache(usage=CacheConcurrencyStrategy.READ_ONLY,region="employee")
@Table(name="TBL_EMPLOYEE",uniqueConstraints= {@UniqueConstraint(columnNames= {"EMAIL"})})
public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2570226147490514074L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID",unique=true,nullable=false)
	private long id;
	
	@Column(name="NAME" ,length=100,nullable=false)
	private String name;
	
	@Column(name="AGE" ,length=3,nullable=false)
	private int age;
	
	@Column(name="SALARY",nullable=false)
	private float salary;
	
	@Column(name="EMAIL" ,length=100,nullable=false,unique=true)
	private String email;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
