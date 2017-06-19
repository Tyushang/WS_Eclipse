package com.uplooking.car_renting;

import java.util.Scanner;

public class Entry {

	public static void main(String[] args) {
		System.out.println("*************欢迎使用*************");
		System.out.println("0.	关闭程序");
		System.out.println("1.	登录");
		System.out.println("2.	注册");
		System.out.println("3.	退出");

		Scanner scanner = new Scanner(System.in);
		User user = null;
		while (true) {
			int opt = scanner.nextInt();
			switch (opt) {
			case 0:
				scanner.close();
				return;
			case 1:
				user = userLogin();
				if(user != null) {
					carRentingMain(user);
				} else {
					System.out.println("登录失败！");
				}
				break;
			case 2:
				if(userRegister()) {
					System.out.println("注册成功！");
				} else {
					System.out.println("注册失败！");
				}
				break;
			case 3:
				if(userExit(user)) {
					System.out.println("退出成功！");
				} else {
					System.out.println("用户已退出！");
				}
				break;
			default:
				System.out.println("输入错误！请重新输入。");
			}
		}
	}

	public static User userLogin() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入用户名：");
		String userAccount = scanner.nextLine();
		System.out.println("请输入密码：");
		String userPassword = scanner.nextLine();
		scanner.close();
		User user = new User(userAccount, userPassword);
		if(user.login(user)) {
			return user;
		} else {
			return null;
		}
	}

	public static boolean userRegister() {
		User user = new User();
		String[] fields = new String[User.prompts.length];
		Scanner scanner = new Scanner(System.in);
		for(int i=0; i<User.prompts.length; i++) {
			System.out.println("请输入" + User.prompts[i] + ":");
			fields[i] = scanner.nextLine();
		}
		scanner.close();
		user.setAccount (fields[0]);
		user.setPassword(fields[1]);
		user.setName    (fields[2]);
		user.setSex     (fields[3]);
		user.setAge     (Integer.valueOf(fields[4]));
		user.setPhoneNo (fields[5]);
		user.setIdCardNo(fields[6]);
		user.setAddress (fields[7]);
		
		return user.register(user);
	}

	public static boolean userExit(User user) {
		if(user == null) {
			return false;
		}
		user = null;
		return true;
	}
	
	public static void carRentingMain(User user) {

		System.out.println("*************汽车租借系统*************");
		System.out.println("1.	租车");
		System.out.println("2.	还车");
		System.out.println("2.	还车");
		System.out.println("4.	退出");

		Scanner scanner = new Scanner(System.in);
		while (true) {
			int opt = scanner.nextInt();
			switch (opt) {
			case 1:
				rentCar(user);
				break;
			case 2:
				backCar(user);
				break;
			case 3:
				showBills(user);
				break;
			case 4:
				carRentingExit();
				scanner.close();
				return;
			default:
				System.out.println("输入错误！请重新输入。");
			}
		}
	}
	
	public static boolean rentCar(User user) {
		return true;
	}
	
	public static boolean backCar(User user) {
		return true;
	}

	public static void showBills(User user) {
		
	}
	
	public static void carRentingExit() {
		
	}
}
