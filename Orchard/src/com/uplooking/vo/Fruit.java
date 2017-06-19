package com.uplooking.vo;

public class Fruit {
	// no: automatically increase.
	private long no;
	private long sellerNo;
	private String picAddr;
	private String name;
	//price: RMB yuan per KG.
	private float price;
	//amount: unit KG.
	private float amount;
	//discount: 0~1.
	private float discount;
	
	public Fruit() {
		
	}

	public Fruit(long sellerNo, String picAddr, String name, float price, float amount, float discount) {
		super();
		this.sellerNo = sellerNo;
		this.picAddr = picAddr;
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.discount = discount;
	}

	public long getNo() {
		return no;
	}
	
	public void setNo(long no) {
		this.no = no;
	}

	public long getSellerNo() {
		return sellerNo;
	}

	public void setSellerNo(long sellerNo) {
		this.sellerNo = sellerNo;
	}

	public String getPicAddr() {
		return picAddr;
	}

	public void setPicAddr(String picAddr) {
		this.picAddr = picAddr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "Fruit [no=" + no + ", sellerNo=" + sellerNo + ", picAddr=" + picAddr + ", name=" + name + ", price="
				+ price + ", amount=" + amount + ", discount=" + discount + "]";
	}
	
}
