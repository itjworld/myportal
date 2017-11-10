package com.gspann.repositories.dao;

import com.gspann.entities.Users;


/**
 * @author Ashish Jaiswal
 *
 */
public interface UserDao {

	public Users findUserByUsername(String username);
	public boolean isExistUser(String username);
	public void createUser(Users users);
	
}
