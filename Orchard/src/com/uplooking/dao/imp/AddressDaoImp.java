package com.uplooking.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.uplooking.dao.AddressDao;
import com.uplooking.vo.Address;
import com.uplooking.vo.Users;

public class AddressDaoImp implements AddressDao {

	@Override
	public boolean obj2dbRecord(Address address) throws ClassNotFoundException, SQLException {
		String sql = String.format(
				"insert into address(%s, %s) values('%s', '%s')",
				"userAccount",
				"address",
				address.getUserAccount(),
				address.getAddress());
		if(BaseDao.update(sql) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Address dbRecord2obj(long addressNo) throws ClassNotFoundException, SQLException {
		Address address = null;
		String sql = String.format("select * from address where no='%s'",
				addressNo);
		ResultSet rs = BaseDao.query(sql);
		if(rs.next()) {
			address = new Address();
			address.setNo          (rs.getLong   ("no"));
			address.setUserAccount (rs.getString ("userAccount"));
			address.setAddress     (rs.getString ("address"));
		}
		return address;
	}

	@Override
	public ResultSet showAddress(Users user) throws ClassNotFoundException, SQLException {
		String sql = String.format("select * from address where userAccount='%s'",
				user.getAccount());
		return BaseDao.query(sql);
	}

	@Override
	public boolean updateAddress(Address oldAddress, Address newAddress) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAddress(Address address) throws ClassNotFoundException, SQLException {
		String sql = String.format(
				"delete from address where no='%d'",
				address.getNo());
		if(BaseDao.update(sql) > 0) {
			return true;
		}
		return false;
	}

}
