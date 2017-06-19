package com.uplooking.vo;

public class Users {
	public static final int BUYER = 0;
	public static final int SELLER = 1;
	
	//no: automatically increase.
	private long no;
	//account: must be unique.
	private String account;
	private String password;
	private String name;
	private String sex;
	private String phoneNo;
	//authority: 0 - buyer; 1 - seller
	private int authority;
	
	public Users() {
		
	}

	public Users(String account, String password, String name, String sex, String phoneNo, int authority) {
		super();
		this.account = account;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.phoneNo = phoneNo;
		this.authority = authority;
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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public long getNo() {
		return no;
	}
	
	public void setNo(long no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "Users [no=" + no + ", account=" + account + ", password=" + password + ", name=" + name + ", sex=" + sex
				+ ", phoneNo=" + phoneNo + ", authority=" + authority + "]";
	}
	
	
}
