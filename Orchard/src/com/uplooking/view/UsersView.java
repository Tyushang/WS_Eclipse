package com.uplooking.view;

import java.util.Scanner;

import com.uplooking.service.AddressService;
import com.uplooking.service.FruitService;
import com.uplooking.service.TranService;
import com.uplooking.service.UsersService;
import com.uplooking.service.imp.AddressServiceImp;
import com.uplooking.service.imp.FruitServiceImp;
import com.uplooking.service.imp.TranServiceImp;
import com.uplooking.service.imp.UsersServiceImp;
import com.uplooking.vo.Address;
import com.uplooking.vo.Fruit;
import com.uplooking.vo.Users;

public class UsersView {
	Scanner in;
	public void mainMenu() {
		in = new Scanner(System.in);
		int opt = 0;
		mainMenuLoop:
		while(true) {
			System.out.println("*************�й۹�԰ϵͳ************");
			System.out.println("1. ע��");
			System.out.println("2. ��¼");
			System.out.println("3. �˳�");
			
			opt = in.nextInt();
			switch(opt) {
			case 1:
				if(register()) {
					System.out.println("ע��ɹ���");
				} else {
					System.out.println("ע��ʧ�ܣ�");
				}
				break;
			case 2:
				Users user = new Users();
				if(!login(user)) {
					System.out.println("��¼ʧ�ܣ�");
					break;
				}
				System.out.println("��¼�ɹ���");
				if(user.getAuthority() == Users.BUYER) {
					buyerMenu(user);
				} else if(user.getAuthority() == Users.SELLER) {
					sellerMenu(user);
				} else {
					System.out.println("Ȩ��δ���壡�������޸�Ȩ�ޣ�");
					System.out.println("�����޸ģ�ϵͳ��֮Ϊ��ң�");
					buyerMenu(user);
				}
				break;
			case 3:
				break mainMenuLoop;
			default:
				System.out.println("����������������룡");
			}
		}
		in.close();
	}
	
	public boolean register () {
		Users user = new Users();
		UsersService service = new UsersServiceImp();
		/*
		 * ���������û�����������û����Ѿ�ע�����������ע�ᣡ
		 */
		System.out.println("�������˻��������");
		user.setAccount(in.next());
		if(service.isRegistered(user.getAccount())) {
			System.out.println("���˻���ע�ᣡ������ע�ᣡ");
			return false;
		}
		System.out.println("���������루�����");
		user.setPassword(in.next());
		System.out.println("������������ѡ�Ĭ��Ϊ�գ���");
		user.setName(in.next());
		System.out.println("�������Ա�ѡ�Ĭ��Ϊ�գ���");
		user.setSex(in.next());
		System.out.println("������绰��ѡ�Ĭ��Ϊ�գ���");
		user.setPhoneNo(in.next());
		System.out.println("������Ȩ�ޣ�0����ң�1�����ң���ѡ�Ĭ��Ϊ��ң���");
		if(in.next().equals("1")) {
			System.out.println("SELLER");
			user.setAuthority(Users.SELLER);
		} else {
			System.out.println("BUYER");
			user.setAuthority(Users.BUYER);
		}
		return service.register(user);
	}
	
	public boolean login (Users user) {
		UsersService service = new UsersServiceImp();
		
		System.out.printf("�������˻��������\n");
		user.setAccount(in.next());
		System.out.printf("���������루�����\n");
		user.setPassword(in.next());
		
		return service.login(user);
	}
	
	public void buyerMenu(Users user) {
		while(true) {
			System.out.println("*************�й۹�԰ϵͳ************");
			System.out.println("1. ����ˮ��");
			System.out.println("2. �鿴������Ϣ");
			System.out.println("3. �ҵ��ջ���ַ");
			System.out.println("4. �鿴���׼�¼");
			System.out.println("5. �ǳ�");
			int opt = in.nextInt();
			switch(opt) {
			case 1:
				buyFruit(user);
				break;
			case 2:
				showInfo(user);
				break;
			case 3:
				modifyAddress(user);
				break;
			case 4:
				showTransaction(user);
				break;
			case 5:
				return;
			default:
				break;
			}
		}
	}
	
	public void sellerMenu(Users user) {
		while(true) {
			System.out.println("*************�й۹�԰ϵͳ************");
			System.out.println("1. �ҵ�ˮ��");
			System.out.println("2. �鿴���׼�¼");
			System.out.println("3. ���ɱ���");
			System.out.println("4. �ǳ�");

			int opt = in.nextInt();
			switch(opt) {
			case 1:
				myFruit(user);
				break;
			case 2:
				showTransaction(user);
				break;
			case 3:
				generateReport(user);
				break;
			case 4:
				return;
			default:
				break;
			}
		}
	}
	
	public boolean buyFruit(Users user) {
		System.out.println("*************�������************");
		
		FruitService fs = new FruitServiceImp();
		fs.showFruit();
		System.out.println("������ˮ����ţ�");
		long fruitNo = in.nextLong();
		//�ж�ˮ���Ƿ����
		Fruit fruit = fs.getFruitByNo(fruitNo);
		if(fruit == null) {
			System.out.println("ָ����ˮ�������ڣ�");
			return false;
		}
		System.out.println("�����빺��������");
		float amount = in.nextFloat();
		System.out.println("��ѡ���ջ���ַ��");
		AddressService as = new AddressServiceImp();
		as.showAddress(user);
		long addressNo = in.nextLong();
		Address address = as.getAddressByNo(user, addressNo);
		//�жϵ�ַ�Ƿ����
		if (address == null) {
			System.out.println("ָ���ĵ�ַ�����ڣ�");
			return false;
		}
		TranService ts = new TranServiceImp();
		return ts.submitOrder(user, fruit, address, amount);
	}
	
	public void showInfo(Users user) {
		System.out.println("�˻���" + user.getAccount());
		System.out.println("������" + user.getName());
		System.out.println("�Ա�" + user.getSex());
		System.out.println("�绰��" + user.getPhoneNo());
		
		AddressService as = new AddressServiceImp();
		as.showAddress(user);
	}
	
	public boolean modifyAddress(Users user) {
		AddressService as = new AddressServiceImp();
		while(true) {
			System.out.println("*************�ҵ��ջ���ַ************");
			System.out.println("0. ����");
			System.out.println("1. ɾ����ַ");
			System.out.println("2. ��ӵ�ַ");
			System.out.println("3: �鿴��ַ");
			int opt = in.nextInt();
			switch(opt) {
			case 0:
				return true;
			case 1:
				if(deleteAddress(user)) {
					System.out.println("ɾ����ַ�ɹ���");
				} else {
					System.out.println("ɾ����ַʧ�ܣ�");
				}
				break;
			case 2:
				if(addAddress(user)) {
					System.out.println("��ӵ�ַ�ɹ���");
				} else {
					System.out.println("��ӵ�ַʧ�ܣ�");
				}
				break;
			case 3:
				as.showAddress(user);
				break;
			default:
				break;
			}
		}
	}
	
	public void showTransaction(Users user) {
		TranService ts = new TranServiceImp();
		
		ts.showTransaction(user);
	}
	
	public boolean myFruit(Users user) {
		FruitService fs = new FruitServiceImp();
		while(true) {
			System.out.println("*************�ҵ�ˮ��************");
			System.out.println("0. ����");
			System.out.println("1. ɾ��ˮ��");
			System.out.println("2. ���ˮ��");
			System.out.println("3: �鿴ˮ��");
			int opt = in.nextInt();
			switch(opt) {
			case 0:
				return true;
			case 1:
				if(deleteFruit(user)) {
					System.out.println("ɾ��ˮ���ɹ���");
				} else {
					System.out.println("ɾ��ˮ��ʧ�ܣ�");
				}
				break;
			case 2:
				if(addFruit(user)) {
					System.out.println("���ˮ���ɹ���");
				} else {
					System.out.println("���ˮ��ʧ�ܣ�");
				}
				break;
			case 3:
				fs.showFruit(user);
				break;
			default:
				break;
			}
		}
	}
	
	public void generateReport(Users user) {
		TranService ts = new TranServiceImp();
		ts.generateReport(user);
	}
	
	public boolean deleteAddress(Users user) {
		AddressService as = new AddressServiceImp();
		
		System.out.println("*************ɾ����ַ************");
		as.showAddress(user);
		System.out.println("�������ַ��ţ�");
		long addressNo = in.nextInt();
		Address address = as.getAddressByNo(user, addressNo);
		if(address == null) {
			System.out.println("����ĵ�ַ��ţ�");
			return false;
		}
		return as.deleteAddress(address);
	}
	
	public boolean addAddress(Users user) {
		AddressService as = new AddressServiceImp();
		
		System.out.println("*************��ӵ�ַ************");
		System.out.println("�������ַ��");
		String addrInfo = in.next();
		Address address = new Address();
		address.setUserAccount(user.getAccount());
		address.setAddress(addrInfo);
		
		return as.addAddress(address);
	}
	
	public boolean addFruit(Users user) {
		FruitService fs = new FruitServiceImp();
		Fruit fruit = new Fruit();
		
		fruit.setSellerNo(user.getNo());
		System.out.println("������ˮ�����ƣ�");
		fruit.setName     (in.next());
		System.out.println("������ˮ��ͼƬ��");
		fruit.setPicAddr  (in.next());
		System.out.println("������ˮ�����ۣ�");
		fruit.setPrice    (in.nextFloat());
		System.out.println("������ˮ��������");
		fruit.setAmount   (in.nextFloat());
		System.out.println("������ˮ���ۿۣ�");
		fruit.setDiscount (in.nextFloat());
		
		return fs.addFruit(fruit);
	}
	
	public boolean deleteFruit(Users user) {
		return false;
	}
	
}
