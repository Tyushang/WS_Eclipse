package com.uplooking.vo;

public class Address {
	// no: automatically increase.
	private long no;
	private String userAccount;
	private String address;

	public Address() {

	}

	public Address(String userAccount, String address) {
		super();
		this.userAccount = userAccount;
		this.address = address;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Address [no=" + no + ", userAccount=" + userAccount + ", address=" + address + "]";
	}

}
