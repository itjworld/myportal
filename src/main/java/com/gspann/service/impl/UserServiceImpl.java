package com.gspann.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gspann.entities.Users;
import com.gspann.repositories.dao.UserDao;
import com.gspann.service.UserService;
import com.gspann.utils.Password;

@Service(value="userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional(readOnly = true)
	public Users findUserByUsername(String username) {
		return userDao.findUserByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isExistUser(String username) {
		return userDao.isExistUser(username);
	}

	@Override
	@Transactional(readOnly = false)
	public void createUser(String username, String password) {
		final Users users= new Users();
		users.setUsername(username);
		users.setPassword(Password.hashPassword(password));
		userDao.createUser(users);
	}

}
