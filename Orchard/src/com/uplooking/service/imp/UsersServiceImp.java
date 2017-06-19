package com.uplooking.service.imp;

import java.sql.SQLException;

import com.uplooking.dao.UsersDao;
import com.uplooking.dao.imp.BaseDao;
import com.uplooking.dao.imp.UsersDaoImp;
import com.uplooking.service.UsersService;
import com.uplooking.vo.Users;

public class UsersServiceImp implements UsersService {

	@Override
	public boolean register(Users user) {
		boolean ret = false;
		UsersDao usersDao = new UsersDaoImp();
		try {
			BaseDao.openCon();
			ret = usersDao.obj2dbRecord(user);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll();
		}
		return ret;
	}

	@Override
	public boolean isRegistered(String account) {
		boolean ret = false;
		UsersDao usersDao = new UsersDaoImp();
		try {
			BaseDao.openCon();
			if (usersDao.dbRecord2obj(account) == null) {
				ret = false;
			} else {
				ret = true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll();
		}
		return ret;
	}

	@Override
	public boolean login(Users user) {
		boolean ret = false;
		UsersDao usersDao = new UsersDaoImp();
		try {
			BaseDao.openCon();
			ret = usersDao.login(user);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll();
		}
		return ret;
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
