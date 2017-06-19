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
			System.out.println("*************尚观果园系统************");
			System.out.println("1. 注册");
			System.out.println("2. 登录");
			System.out.println("3. 退出");
			
			opt = in.nextInt();
			switch(opt) {
			case 1:
				if(register()) {
					System.out.println("注册成功！");
				} else {
					System.out.println("注册失败！");
				}
				break;
			case 2:
				Users user = new Users();
				if(!login(user)) {
					System.out.println("登录失败！");
					break;
				}
				System.out.println("登录成功！");
				if(user.getAuthority() == Users.BUYER) {
					buyerMenu(user);
				} else if(user.getAuthority() == Users.SELLER) {
					sellerMenu(user);
				} else {
					System.out.println("权限未定义！请重新修改权限！");
					System.out.println("若不修改，系统视之为买家！");
					buyerMenu(user);
				}
				break;
			case 3:
				break mainMenuLoop;
			default:
				System.out.println("输入错误！请重新输入！");
			}
		}
		in.close();
	}
	
	public boolean register () {
		Users user = new Users();
		UsersService service = new UsersServiceImp();
		/*
		 * 检测输入的用户名，如果该用户名已经注册过，请重新注册！
		 */
		System.out.println("请输入账户（必填）：");
		user.setAccount(in.next());
		if(service.isRegistered(user.getAccount())) {
			System.out.println("该账户已注册！请重新注册！");
			return false;
		}
		System.out.println("请输入密码（必填）：");
		user.setPassword(in.next());
		System.out.println("请输入姓名（选填，默认为空）：");
		user.setName(in.next());
		System.out.println("请输入性别（选填，默认为空）：");
		user.setSex(in.next());
		System.out.println("请输入电话（选填，默认为空）：");
		user.setPhoneNo(in.next());
		System.out.println("请输入权限（0：买家；1：卖家）（选填，默认为买家）：");
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
		
		System.out.printf("请输入账户（必填）：\n");
		user.setAccount(in.next());
		System.out.printf("请输入密码（必填）：\n");
		user.setPassword(in.next());
		
		return service.login(user);
	}
	
	public void buyerMenu(Users user) {
		while(true) {
			System.out.println("*************尚观果园系统************");
			System.out.println("1. 购买水果");
			System.out.println("2. 查看个人信息");
			System.out.println("3. 我的收货地址");
			System.out.println("4. 查看交易记录");
			System.out.println("5. 登出");
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
			System.out.println("*************尚观果园系统************");
			System.out.println("1. 我的水果");
			System.out.println("2. 查看交易记录");
			System.out.println("3. 生成报表");
			System.out.println("4. 登出");

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
		System.out.println("*************购买界面************");
		
		FruitService fs = new FruitServiceImp();
		fs.showFruit();
		System.out.println("请输入水果编号：");
		long fruitNo = in.nextLong();
		//判断水果是否存在
		Fruit fruit = fs.getFruitByNo(fruitNo);
		if(fruit == null) {
			System.out.println("指定的水果不存在！");
			return false;
		}
		System.out.println("请输入购买数量：");
		float amount = in.nextFloat();
		System.out.println("请选择收货地址：");
		AddressService as = new AddressServiceImp();
		as.showAddress(user);
		long addressNo = in.nextLong();
		Address address = as.getAddressByNo(user, addressNo);
		//判断地址是否存在
		if (address == null) {
			System.out.println("指定的地址不存在！");
			return false;
		}
		TranService ts = new TranServiceImp();
		return ts.submitOrder(user, fruit, address, amount);
	}
	
	public void showInfo(Users user) {
		System.out.println("账户：" + user.getAccount());
		System.out.println("姓名：" + user.getName());
		System.out.println("性别：" + user.getSex());
		System.out.println("电话：" + user.getPhoneNo());
		
		AddressService as = new AddressServiceImp();
		as.showAddress(user);
	}
	
	public boolean modifyAddress(Users user) {
		AddressService as = new AddressServiceImp();
		while(true) {
			System.out.println("*************我的收货地址************");
			System.out.println("0. 返回");
			System.out.println("1. 删除地址");
			System.out.println("2. 添加地址");
			System.out.println("3: 查看地址");
			int opt = in.nextInt();
			switch(opt) {
			case 0:
				return true;
			case 1:
				if(deleteAddress(user)) {
					System.out.println("删除地址成功！");
				} else {
					System.out.println("删除地址失败！");
				}
				break;
			case 2:
				if(addAddress(user)) {
					System.out.println("添加地址成功！");
				} else {
					System.out.println("添加地址失败！");
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
			System.out.println("*************我的水果************");
			System.out.println("0. 返回");
			System.out.println("1. 删除水果");
			System.out.println("2. 添加水果");
			System.out.println("3: 查看水果");
			int opt = in.nextInt();
			switch(opt) {
			case 0:
				return true;
			case 1:
				if(deleteFruit(user)) {
					System.out.println("删除水果成功！");
				} else {
					System.out.println("删除水果失败！");
				}
				break;
			case 2:
				if(addFruit(user)) {
					System.out.println("添加水果成功！");
				} else {
					System.out.println("添加水果失败！");
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
		
		System.out.println("*************删除地址************");
		as.showAddress(user);
		System.out.println("请输入地址编号：");
		long addressNo = in.nextInt();
		Address address = as.getAddressByNo(user, addressNo);
		if(address == null) {
			System.out.println("错误的地址编号！");
			return false;
		}
		return as.deleteAddress(address);
	}
	
	public boolean addAddress(Users user) {
		AddressService as = new AddressServiceImp();
		
		System.out.println("*************添加地址************");
		System.out.println("请输入地址：");
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
		System.out.println("请输入水果名称：");
		fruit.setName     (in.next());
		System.out.println("请输入水果图片：");
		fruit.setPicAddr  (in.next());
		System.out.println("请输入水果单价：");
		fruit.setPrice    (in.nextFloat());
		System.out.println("请输入水果数量：");
		fruit.setAmount   (in.nextFloat());
		System.out.println("请输入水果折扣：");
		fruit.setDiscount (in.nextFloat());
		
		return fs.addFruit(fruit);
	}
	
	public boolean deleteFruit(Users user) {
		return false;
	}
	
}
