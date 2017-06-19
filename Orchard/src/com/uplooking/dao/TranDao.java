package com.uplooking.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.uplooking.vo.Tran;
import com.uplooking.vo.Users;

public interface TranDao {
	/*
	 * 将OBJ对象的内容写入到数据库相应表格的记录中
	 * 
	 * 返回：
	 * 	true  - 写入成功
	 *  false - 写入失败
	 */
	boolean obj2dbRecord(Tran transaction) throws ClassNotFoundException, SQLException;
	
	/*
	 * 将数据库表格的记录取出（根据编号），转换为相应的OBJ对象
	 * 
	 * 返回：
	 * 	null - 找不到指定编号的记录
	 * 	Transaction对象 - 找到该编号的记录并将其转换为Transaction对象
	 */
	Tran dbRecord2obj(long transactionNo) throws ClassNotFoundException, SQLException;
	
	ResultSet showTransaction(Users user) throws ClassNotFoundException, SQLException;
	
}
