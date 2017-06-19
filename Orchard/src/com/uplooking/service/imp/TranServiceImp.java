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
			if(user.getAuthority() == Users.BUYER) {	//��ҽ��׼�¼
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
							((rs.getInt("transProgress") == 0) ? "�µ�" :
							 (rs.getInt("transProgress") == 1) ? "�ѷ���" :
							 (rs.getInt("transProgress") == 1) ? "�ѷ���" : 
							 (rs.getInt("transProgress") == 2) ? "��ǩ��" : 
							 (rs.getInt("transProgress") == 3) ? "�˻�" :
											                     "״̬δ֪"),
							fruit.getPicAddr()
							);
				}
			} else {	//���ҽ��׼�¼
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
							((rs.getInt("transProgress") == 0) ? "�µ�" :
							 (rs.getInt("transProgress") == 1) ? "�ѷ���" :
							 (rs.getInt("transProgress") == 1) ? "�ѷ���" : 
							 (rs.getInt("transProgress") == 2) ? "��ǩ��" : 
							 (rs.getInt("transProgress") == 3) ? "�˻�" :
											                     "״̬δ֪"),
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
			//����֮�󣬸��¿��
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
			System.out.println("*************���ɱ���************");
			System.out.println("������Ŀǰ��" + sdf.format(date));
			System.out.println("�����ܽ��Ϊ��" + totalExpenditure);
			System.out.println("��������Ϊ��" + totalSheets);
			System.out.println("��ǩ�յ�����" + signedSheets);
			System.out.println("δǩ�յ�����" + unsignedSheets);
			System.out.println("�˻�������" + backSheets);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			BaseDao.closeAll();
		}
	}

}
