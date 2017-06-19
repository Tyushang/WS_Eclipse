package com.uplooking.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.uplooking.dao.FruitDao;
import com.uplooking.vo.Fruit;
import com.uplooking.vo.Users;

public class FruitDaoImp implements FruitDao {

	@Override
	public ResultSet showFruit() throws ClassNotFoundException, SQLException {
		String sql = String.format("select * from fruit");
		return BaseDao.query(sql);
	}

	@Override
	public ResultSet showFruit(Users user) throws ClassNotFoundException, SQLException {
		String sql = String.format("select * from fruit where sellerNo='%s'",
				user.getNo());
		return BaseDao.query(sql);
	}

	@Override
	public boolean updateFruit(Fruit oldFruit, Fruit newFruit) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean obj2dbRecord(Fruit fruit) throws ClassNotFoundException, SQLException {
		String sql = String.format("insert into fruit(%s,%s,%s,%s,%s,%s) values('%d','%s','%s','%f','%f','%f')",
				"sellerNo",
				"picAddr",
				"name",
				"price",
				"amount",
				"discount",
				fruit.getSellerNo(),
				fruit.getPicAddr(),
				fruit.getName(),
				fruit.getPrice(),
				fruit.getAmount(),
				fruit.getDiscount());
		if(BaseDao.update(sql) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteFruit(Fruit fruit) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Fruit dbRecord2obj(long fruitNo) throws ClassNotFoundException, SQLException {
		Fruit fruit = null;
		String sql = String.format("select * from fruit where no=%s",
				fruitNo);
		ResultSet rs = BaseDao.query(sql);
		if(rs.next()) {
			fruit = new Fruit();
			fruit.setNo      (rs.getLong   ("no"));
			fruit.setSellerNo(rs.getLong   ("sellerNo"));
			fruit.setPicAddr (rs.getString ("picAddr"));
			fruit.setName    (rs.getString ("name"));
			fruit.setPrice   (rs.getFloat  ("price"));
			fruit.setDiscount(rs.getFloat  ("discount"));
			fruit.setAmount  (rs.getFloat  ("amount"));
		}
		return fruit;
	}
}
