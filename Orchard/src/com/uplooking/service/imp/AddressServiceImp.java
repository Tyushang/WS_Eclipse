package com.uplooking.service.imp;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.uplooking.dao.AddressDao;
import com.uplooking.dao.imp.AddressDaoImp;
import com.uplooking.dao.imp.BaseDao;
import com.uplooking.service.AddressService;
import com.uplooking.vo.Address;
import com.uplooking.vo.Users;

public class AddressServiceImp implements AddressService {

	@Override
	public boolean addAddress(Address address) {
		boolean ret = false;
		AddressDao addressDao = new AddressDaoImp();
		try {
			BaseDao.openCon();
			ret = addressDao.obj2dbRecord(address);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeAll();
		}
		return ret;
	}

	@Override
	public boolean deleteAddress(Address address) {
		boolean ret = false;
		AddressDao addressDao = new AddressDaoImp();
		try {
			BaseDao.openCon();
			ret = addressDao.deleteAddress(address);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeAll();
		}
		return ret;
	}

	@Override
	public void showAddress(Users user) {
		AddressDao addressDao = new AddressDaoImp();
		try {
			BaseDao.openCon();
			ResultSet rs = addressDao.showAddress(user);
			System.out.println("用户" + user.getAccount() + "的收货地址：");
			
			int count = 0;
			while(rs.next()) {
				count++;
				System.out.printf("%4d: %s\n",
						rs.getLong("no"),
						rs.getString("address"));
			}
			if(count == 0) {
				System.out.println("您还未设置收货地址，请添加！");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeAll();
		}
	}

	@Override
	public Address getAddressByNo(Users user, long addressNo) {
		Address address = null;
		AddressDao addressDao = new AddressDaoImp();
		try {
			BaseDao.openCon();
			address = addressDao.dbRecord2obj(addressNo);
			
			if((address != null) && !address.getUserAccount().equals(user.getAccount())) {
				address = null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeAll();
		}
		return address;
	}

	@Override
	public Address getAddressByNo(long addressNo) {
		Address address = null;
		AddressDao addressDao = new AddressDaoImp();
		try {
			BaseDao.openCon();
			address = addressDao.dbRecord2obj(addressNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeAll();
		}
		return address;
	}

}
