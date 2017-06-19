package com.uplooking.car_renting;

import java.util.Scanner;

public class Entry {

	public static void main(String[] args) {
		System.out.println("*************��ӭʹ��*************");
		System.out.println("0.	�رճ���");
		System.out.println("1.	��¼");
		System.out.println("2.	ע��");
		System.out.println("3.	�˳�");

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
					System.out.println("��¼ʧ�ܣ�");
				}
				break;
			case 2:
				if(userRegister()) {
					System.out.println("ע��ɹ���");
				} else {
					System.out.println("ע��ʧ�ܣ�");
				}
				break;
			case 3:
				if(userExit(user)) {
					System.out.println("�˳��ɹ���");
				} else {
					System.out.println("�û����˳���");
				}
				break;
			default:
				System.out.println("����������������롣");
			}
		}
	}

	public static User userLogin() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("�������û�����");
		String userAccount = scanner.nextLine();
		System.out.println("���������룺");
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
			System.out.println("������" + User.prompts[i] + ":");
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

		System.out.println("*************�������ϵͳ*************");
		System.out.println("1.	�⳵");
		System.out.println("2.	����");
		System.out.println("2.	����");
		System.out.println("4.	�˳�");

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
				System.out.println("����������������롣");
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
