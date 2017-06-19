package com.uplooking.dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.uplooking.dao.UsersDao;
import com.uplooking.vo.Users;

/*
 * 操作users表的类
 * 类中实现各种操作函数
 */
public class UsersDaoImp implements UsersDao {
	@Override
	public int addUser(Users user) throws ClassNotFoundException, SQLException {
		String sql = "INSERT into users(username,password,name,age,sex,phone) values('" + user.getUsername() + "','"
				+ user.getPassword() + "','" + user.getName() + "'," + user.getAge() + ",'" + user.getSex() + "',"
				+ user.getPhone() + ")";
		return BaseDao.update(sql);
	}

	@Override
	public Users login(Users user) throws ClassNotFoundException, SQLException {
		String sql = "select * from users where username='"+user.getUsername()+"' and password = '"+user.getPassword()+"'";
		ResultSet rs = BaseDao.query(sql);
		if(rs.next()){
			return getUserByRs(rs);
		}
		return null;
	}
	private Users getUserByRs(ResultSet rs) throws SQLException{
		Users user = new Users();
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		user.setAge(rs.getInt("age"));
		user.setSex(rs.getString("sex").charAt(0));
		user.setPhone(rs.getLong("phone"));
		return user;
	}
}
