package com.uplooking.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.uplooking.dao.UsersDao;
import com.uplooking.vo.Users;

public class UsersDaoImp implements UsersDao {

	@Override
	public boolean obj2dbRecord(Users user) throws ClassNotFoundException, SQLException {
		/*
		 * 判断user.account是否已经被注册。
		 * 若已经被注册，返回false;
		 * 否则继续往下执行。
		 */
		String sqlSelect = String.format(
				"select * from users where account='%s'",
				user.getAccount());
		ResultSet rs = BaseDao.query(sqlSelect);
		if(rs.next()) {
			return false;
		}
		/*
		 * 根据user信息，往users表格中插入一条记录。
		 */
		String sqlInsert = String.format(
				"insert into users(%s, %s, %s, %s, %s, %s) values('%s', '%s', '%s', '%s', '%s', %d)",
				"account",
				"password",
				"name",
				"sex",
				"phoneNo",
				"authority",
				user.getAccount(),
				user.getPassword(),
				user.getName(),
				user.getSex(),
				user.getPhoneNo(),
				user.getAuthority());
		if(BaseDao.update(sqlInsert) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Users dbRecord2obj(long userNo) throws ClassNotFoundException, SQLException {
		Users user = null;
		String sqlSelect = String.format(
				"select * from users where no=%s",
				userNo);
		ResultSet rs = BaseDao.query(sqlSelect);
		if(rs.next()) {
			user = new Users();
			user.setNo        (rs.getLong   ("no"));
			user.setAccount   (rs.getString ("account"));
			user.setPassword  (rs.getString ("password"));
			user.setName      (rs.getString ("name"));
			user.setSex       (rs.getString ("sex"));
			user.setPhoneNo   (rs.getString ("phoneNo"));
			user.setAuthority (rs.getInt    ("authority"));
		}
		return user;
	}
	
	@Override
	public Users dbRecord2obj(String account) throws ClassNotFoundException, SQLException {
		Users user = null;
		String sqlSelect = String.format(
				"select * from users where account='%s'",
				account);
		ResultSet rs = BaseDao.query(sqlSelect);
		if(rs.next()) {
			user = new Users();
			user.setNo        (rs.getLong   ("no"));
			user.setAccount   (rs.getString ("account"));
			user.setPassword  (rs.getString ("password"));
			user.setName      (rs.getString ("name"));
			user.setSex       (rs.getString ("sex"));
			user.setPhoneNo   (rs.getString ("phoneNo"));
			user.setAuthority (rs.getInt    ("authority"));
		}
		return user;
	}
	
	@Override
	public boolean login(Users user) throws ClassNotFoundException, SQLException {
		String sql = String.format(
				"select * from users where account='%s' and password='%s'",
				user.getAccount(),
				user.getPassword());
		ResultSet rs = BaseDao.query(sql);
		if(rs.next()) {
			user.setNo        (rs.getLong   ("no"));
			user.setAccount   (rs.getString ("account"));
			user.setPassword  (rs.getString ("password"));
			user.setName      (rs.getString ("name"));
			user.setSex       (rs.getString ("sex"));
			user.setPhoneNo   (rs.getString ("phoneNo"));
			user.setAuthority (rs.getInt    ("authority"));
			return true;
		}
		return false;
	}

	@Override
	public boolean isLogin(Users user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateInfo(Users oldInfo, Users newInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logout(Users user) {
		// TODO Auto-generated method stub
		return false;
	}


}
