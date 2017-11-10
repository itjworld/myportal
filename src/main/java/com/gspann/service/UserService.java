package com.gspann.service;

import com.gspann.entities.Users;

public interface UserService {
	public Users findUserByUsername(String username);
	public boolean isExistUser(String username);
	public void createUser(String username,String password);
}
