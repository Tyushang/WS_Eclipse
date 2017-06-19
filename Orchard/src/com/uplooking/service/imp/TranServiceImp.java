package com.uplooking.service.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.uplooking.dao.TranDao;
import com.uplooking.dao.imp.BaseDao;
import com.uplooking.dao.imp.TranDaoImp;
import com.uplooking.service.AddressService;
import com.uplooking.service.FruitService;
import com.uplooking.service.TranService;
import com.uplooking.vo.Address;
import com.uplooking.vo.Fruit;
import com.uplooking.vo.Tran;
import com.uplooking.vo.Users;

public class TranServiceImp implements TranService {

	@Override
	public void showTransaction(Users user) {
		TranDao td = new TranDaoImp();
		FruitService fs = new FruitServiceImp();
		AddressService as = new AddressServiceImp();
		
		try {
			BaseDao.openCon();
			ResultSet rs = td.showTransaction(user);
			if(user.getAuthority() == Users.BUYER) {	//买家交易记录
				System.out.printf("Date       Time           Name Price    Amount  Total     Proc  Picture\n");
				while(rs.next()) {
					long fruitNo = rs.getLong("fruitNo");
					Fruit fruit = fs.getFruitByNo(fruitNo);
	
					System.out.printf("%s %s %8s %8.2f %8.2f %8.2f %8s %s\n",
							rs.getDate("transTime").toString(),
							rs.getTime("transTime").toString(),
							fruit.getName(),
							fruit.getPrice(),
							rs.getFloat("amount"),
							rs.getFloat("expenditure"),
							((rs.getInt("transProgress") == 0) ? "下单" :
							 (rs.getInt("transProgress") == 1) ? "已发货" :
							 (rs.getInt("transProgress") == 1) ? "已发货" : 
							 (rs.getInt("transProgress") == 2) ? "已签收" : 
							 (rs.getInt("transProgress") == 3) ? "退货" :
											                     "状态未知"),
							fruit.getPicAddr()
							);
				}
			} else {	//卖家交易记录
				System.out.printf("Date       Time           Name Price    Amount  Total     Proc  Address\n");
				while(rs.next()) {
					long fruitNo = rs.getLong("fruitNo");
					Fruit fruit = fs.getFruitByNo(fruitNo);
					long addressNo = rs.getLong("addressNo");
					Address address = as.getAddressByNo(addressNo);
	
					System.out.printf("%s %s %8s %8.2f %8.2f %8.2f %8s %s\n",
							rs.getDate("transTime").toString(),
							rs.getTime("transTime").toString(),
							fruit.getName(),
							fruit.getPrice(),
							rs.getFloat("amount"),
							rs.getFloat("expenditure"),
							((rs.getInt("transProgress") == 0) ? "下单" :
							 (rs.getInt("transProgress") == 1) ? "已发货" :
							 (rs.getInt("transProgress") == 1) ? "已发货" : 
							 (rs.getInt("transProgress") == 2) ? "已签收" : 
							 (rs.getInt("transProgress") == 3) ? "退货" :
											                     "状态未知"),
							address.getAddress()
							);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeAll();
		}
		
	}

	@Override
	public boolean submitOrder(Users user, Fruit fruit, Address address, float amount) {
		Tran tran = new Tran();
		tran.setSellerNo(fruit.getSellerNo());
		tran.setAddressNo(address.getNo());
		tran.setBuyerNo(user.getNo());
		tran.setFruitNo(fruit.getNo());
		tran.setAmount(amount);
		tran.setExpenditure(amount*fruit.getPrice());
		tran.setTransProcess(0);
		
		TranDao td = new TranDaoImp();
		
		boolean ret = false;
		try {
			BaseDao.openCon();
			ret = td.obj2dbRecord(tran);
			//交易之后，更新库存
			Fruit newFruit = new Fruit();
			newFruit.setNo(fruit.getNo());
			newFruit.setSellerNo(fruit.getSellerNo());
			newFruit.setPicAddr(fruit.getPicAddr());
			newFruit.setName(fruit.getName());
			newFruit.setPrice(fruit.getPrice());
			newFruit.setAmount(fruit.getAmount() - amount);
			newFruit.setDiscount(fruit.getDiscount());
			
			FruitService fs = new FruitServiceImp();
			fs.updateFruit(fruit, newFruit);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeAll();
		}
		
		return ret;
	}

	@Override
	public void generateReport(Users user) {
		TranDao td = new TranDaoImp();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		float totalExpenditure = 0;
		int   totalSheets      = 0;
		int   signedSheets     = 0;
		int   unsignedSheets   = 0;
		int   backSheets       = 0;
		try {
			BaseDao.openCon();
			ResultSet rs = td.showTransaction(user);
			while(rs.next()) {
				totalExpenditure += rs.getLong("expenditure");
				totalSheets      += 1;
				int progress = rs.getInt("transProgress");
				switch(progress) {
				case 0:
				case 1:
					unsignedSheets +=1;
					break;
				case 2:
					signedSheets += 1;
					break;
				case 3:
					backSheets += 1;
				default :
					break;
				}
			}
			System.out.println("*************生成报表************");
			System.out.println("截至到目前：" + sdf.format(date));
			System.out.println("交易总金额为：" + totalExpenditure);
			System.out.println("交易数量为：" + totalSheets);
			System.out.println("已签收单数：" + signedSheets);
			System.out.println("未签收单数：" + unsignedSheets);
			System.out.println("退货数量：" + backSheets);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeAll();
		}
	}

}
