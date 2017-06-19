package com.uplooking.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.uplooking.vo.Users;

/*
 * 操作users表的类接口
 * 接口中定义各种操作数据库的函数
 */
public interface UsersDao {
	int addUser(Users user) throws ClassNotFoundException, SQLException;
	//Users login(String username,String password) throws ClassNotFoundException,SQLException;
	Users login(Users user) throws ClassNotFoundException,SQLException;
}
