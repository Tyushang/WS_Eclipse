package com.uplooking.service;

import java.sql.SQLException;

import com.uplooking.vo.Users;

public interface UserService {
	int addUser(Users user);
	Users login(Users user);
}
