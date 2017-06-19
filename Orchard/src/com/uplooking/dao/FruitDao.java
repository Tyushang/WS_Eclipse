package com.uplooking.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.uplooking.vo.Fruit;
import com.uplooking.vo.Users;

public interface FruitDao {
	/*
	 * 将OBJ对象的内容写入到数据库相应表格的记录中
	 * 
	 * 返回：
	 * 	true  - 写入成功
	 *  false - 写入失败
	 */
	boolean obj2dbRecord(Fruit fruit) throws ClassNotFoundException, SQLException;
	
	/*
	 * 将数据库表格的记录取出（根据编号），转换为相应的OBJ对象
	 * 
	 * 返回：
	 * 	null - 找不到指定编号的记录
	 * 	Fruit对象 - 找到该编号的记录并将其转换为Fruit对象
	 */
	Fruit dbRecord2obj(long fruitNo) throws ClassNotFoundException, SQLException;
	
	ResultSet showFruit() throws ClassNotFoundException, SQLException;
	ResultSet showFruit(Users user) throws ClassNotFoundException, SQLException;
	boolean updateFruit(Fruit oldFruit, Fruit newFruit);
	boolean deleteFruit(Fruit fruit);
}
