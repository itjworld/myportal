package com.gspann.repositories.dao;

import com.gspann.entities.Users;


public interface UserDao {

	public Users findUserByUsername(String username);
}
