package com.uplooking.service.imp;

import java.sql.Connection;
import java.sql.SQLException;

import com.uplooking.dao.UsersDao;
import com.uplooking.dao.imp.BaseDao;
import com.uplooking.dao.imp.UsersDaoImp;
import com.uplooking.service.UserService;
import com.uplooking.vo.Users;

public class UserServiceImp implements UserService{
	private UsersDao dao = new UsersDaoImp();

	@Override
	public int addUser(Users user) {
		int num = 0;
		
		try {
			BaseDao.openCon();
			num = dao.addUser(user);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			BaseDao.closeAll(null, BaseDao.sta, BaseDao.con);
		}
		
		return num;
	}

	@Override
	public Users login(Users user) {
		try {
			BaseDao.openCon();
			user = dao.login(user);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			BaseDao.closeAll(BaseDao.rs, BaseDao.sta, BaseDao.con);
		}
		return user;
	}

}
