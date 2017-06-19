package com.uplooking.car_renting;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements UserInterface{
	private String account;
	private String password;
	private String name;
	private String sex;
	private int age;
	private String phoneNo;
	private String idCardNo;
	private String address;
	
	public static String[] prompts = {
			"账户",
			"密码",
			"姓名",
			"性别",
			"年龄",
			"电话",
			"身份证",
			"家庭住址",
	};
	
	public User() {
		
	}
	public User(String accout, String password) {
		this.account  = accout;
		this.password = password;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public boolean register(User user) {
		
		try {
			return UserAccess.add(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean login(User userOuter) {
		User userInner = UserAccess.getUser(userOuter.account);
		if((userInner == null) || (!userOuter.password.equals(userInner.password))) {
			return false;
		}
		userOuter.name     = userInner.name    ;
		userOuter.sex      = userInner.sex     ;
		userOuter.age      = userInner.age     ;
		userOuter.phoneNo  = userInner.phoneNo ;
		userOuter.idCardNo = userInner.idCardNo;
		userOuter.address  = userInner.address ;
		return true;
	}
	
	public boolean exit() {
		return true;
	}
	
	@Override
	public String getKeyStrByObj(User user) {
		// TODO Auto-generated method stub
		return user.account;
	}

	@Override
	public String getKeyStrByItem(String item) {
		String tmpStr = new String();
		String keyStr = new String();
		Pattern p = Pattern.compile("账户" + ":[a-zA-Z0-9]*;");
		Matcher m = p.matcher(item);
		if(m.find()) {
			tmpStr = m.group();
			keyStr = tmpStr.replaceAll("账户" + ":|;", "");
		} else {
			keyStr = null;
		}
		return keyStr;
	}

	@Override
	public String obj2str(User user) {
		StringBuilder item = new StringBuilder();
		item.append(prompts[0] + ":" + user.account  + ";");
		item.append(prompts[1] + ":" + user.password + ";");
		item.append(prompts[2] + ":" + user.name     + ";");
		item.append(prompts[3] + ":" + user.sex      + ";");
		item.append(prompts[4] + ":" + user.age      + ";");
		item.append(prompts[5] + ":" + user.phoneNo  + ";");
		item.append(prompts[6] + ":" + user.idCardNo + ";");
		item.append(prompts[7] + ":" + user.address  + ";");
		item.append("\n");
		return item.toString();
	}

	@Override
	public User str2obj(String str) {
		User user = new User();
		String tmpStr = new String();
		String[] fields = new String[prompts.length];
		for (int i=0; i<prompts.length; i++) {
			Pattern p = Pattern.compile(prompts[i] + ":[a-zA-Z0-9]*;");
			Matcher m = p.matcher(str);
			if(m.find()) {
				tmpStr = m.group();
				fields[i] = tmpStr.replaceAll(prompts[i] + ":|;", "");
			} else {
				fields[i] = null;
			}
		}
		user.account  = fields[0];
		user.password = fields[1];
		user.name     = fields[2];
		user.sex      = fields[3];
		user.age      = Integer.valueOf(fields[4]);
		user.phoneNo  = fields[5];
		user.idCardNo = fields[6];
		user.address  = fields[7];
		return user;
	}
}
