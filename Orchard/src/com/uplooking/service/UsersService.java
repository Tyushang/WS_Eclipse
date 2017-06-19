package com.uplooking.service;

import com.uplooking.vo.Users;

public interface UsersService {
	boolean register (Users user);
	boolean isRegistered(String account);
	boolean login(Users user);
	boolean updateInfo(Users oldInfo, Users newInfo);
	boolean logout(Users user);
}
