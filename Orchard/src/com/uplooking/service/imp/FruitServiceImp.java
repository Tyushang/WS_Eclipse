package com.uplooking.service.imp;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.uplooking.dao.FruitDao;
import com.uplooking.dao.imp.BaseDao;
import com.uplooking.dao.imp.FruitDaoImp;
import com.uplooking.service.FruitService;
import com.uplooking.vo.Fruit;
import com.uplooking.vo.Users;

public class FruitServiceImp implements FruitService {

	@Override
	public void showFruit() {
		ResultSet rs = null;
		FruitDao fruitDao = new FruitDaoImp();
		try {
			BaseDao.openCon();
			rs = fruitDao.showFruit();
			while(rs.next()) {
				System.out.printf("%4d: %8s %4d 元/千克 %s\n",
						rs.getLong("no"),
						rs.getString("name"),
						rs.getInt("price"),
						rs.getString("picAddr"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeAll();
		}
	}

	@Override
	public void showFruit(Users user) {
		ResultSet rs = null;
		FruitDao fruitDao = new FruitDaoImp();
		try {
			BaseDao.openCon();
			rs = fruitDao.showFruit(user);
			while(rs.next()) {
				System.out.printf("%8d: %8s %16.2f 元/千克 %16.2f 千克  %s\n",
						rs.getLong("no"),
						rs.getString("name"),
						rs.getFloat("price"),
						rs.getFloat("amount"),
						rs.getString("picAddr"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeAll();
		}
	}

	@Override
	public boolean updateFruit(Fruit oldFruit, Fruit newFruit) throws ClassNotFoundException, SQLException {
		String sql = String.format("update fruit set amount=%f where no='%d'",
				newFruit.getAmount(),
				oldFruit.getNo()
				);
		System.out.println(sql);
		if(BaseDao.update(sql) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean addFruit(Fruit fruit) {
		boolean ret = false;
		FruitDao fruitDao = new FruitDaoImp();
		try {
			BaseDao.openCon();
			ret = fruitDao.obj2dbRecord(fruit);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeAll();
		}
		return ret;
	}

	@Override
	public boolean deleteFruit(Fruit fruit) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Fruit getFruitByNo(long fruitNo) {
		Fruit fruit = null;
		FruitDao fruitDao = new FruitDaoImp();
		try {
			BaseDao.openCon();
			fruit = fruitDao.dbRecord2obj(fruitNo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeAll();
		}
		return fruit;
	}

}
