package com.uplooking.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.uplooking.dao.TranDao;
import com.uplooking.vo.Tran;
import com.uplooking.vo.Users;

public class TranDaoImp implements TranDao {

	@Override
	public boolean obj2dbRecord(Tran transaction) throws ClassNotFoundException, SQLException {
		String sql = String.format("insert into transaction(%s,%s,%s,%s,%s,%s,%s,%s) values('%d','%d','%d','%d','%f','%f',%s,'%d')",
				"sellerNo",
				"addressNo",
				"buyerNo",
				"fruitNo",
				"amount",
				"expenditure",
				"transTime",
				"transProgress",
				transaction.getSellerNo(),
				transaction.getAddressNo(),
				transaction.getBuyerNo(),
				transaction.getFruitNo(),
				transaction.getAmount(),
				transaction.getExpenditure(),
				"now()",
				transaction.getTransProcess()
				);
		
		if(BaseDao.update(sql) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Tran dbRecord2obj(long transactionNo) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet showTransaction(Users user) throws ClassNotFoundException, SQLException {
		String sql = String.format("select * from transaction where %s='%d'",
				((user.getAuthority() == Users.BUYER) ? "buyerNo" : "sellerNo"),
				user.getNo()
				);
		
		return BaseDao.query(sql);
	}

}
