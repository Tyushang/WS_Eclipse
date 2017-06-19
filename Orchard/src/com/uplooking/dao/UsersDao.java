package com.uplooking.dao;

import java.sql.SQLException;

import com.uplooking.vo.Users;

public interface UsersDao {
	/*
	 * 将OBJ对象的内容写入到数据库相应表格的记录中
	 * 
	 * 返回：
	 * 	true  - 写入成功
	 *  false - 写入失败
	 */
	boolean obj2dbRecord(Users user) throws ClassNotFoundException, SQLException;
	
	/*
	 * 将数据库表格的记录取出（根据编号），转换为相应的OBJ对象
	 * 
	 * 返回：
	 * 	null - 找不到指定编号的记录
	 * 	Users对象 - 找到该编号的记录并将其转换为Users对象
	 */
	Users dbRecord2obj(long userNo) throws ClassNotFoundException, SQLException;
	
	/*
	 * 将数据库表格的记录取出（根据账号），转换为相应的OBJ对象
	 * 
	 * 返回：
	 * 	null - 找不到指定账号的记录
	 * 	Users对象 - 找到该账号的记录并将其转换为Users对象
	 */
	Users dbRecord2obj(String account) throws ClassNotFoundException, SQLException;

	boolean login(Users user) throws ClassNotFoundException, SQLException;

	boolean isLogin(Users user);

	boolean updateInfo(Users oldInfo, Users newInfo);

	boolean logout(Users user);
}
