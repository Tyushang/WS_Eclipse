package com.uplooking.vo;

import java.util.Date;

public class Tran {
	// no: automatically increase.
	private long no;
	private long sellerNo;
	private long AddressNo;
	private long buyerNo;
	private long FruitNo;
	//amount: unit KG.
	private float amount;
	//expenditure: uint RMB yuan.
	private float expenditure;
	private Date transTime;
	/*
	 * transProcess:
	 * 	0 - 下单
	 * 	1 - 已发货
	 *  2 - 已签收
	 *  3 - 退货
	 */
	private int transProcess;
	
	public Tran () {
		
	}

	public Tran(long sellerNo, long addressNo, long buyerNo, long fruitNo, float amount, float expenditure, Date transTime,
			int transProcess) {
		super();
		this.sellerNo = sellerNo;
		this.AddressNo = addressNo;
		this.buyerNo = buyerNo;
		this.FruitNo = fruitNo;
		this.amount = amount;
		this.expenditure = expenditure;
		this.transTime = transTime;
		this.transProcess = transProcess;
	}
	
	public long getSellerNo() {
		return sellerNo;
	}

	public void setSellerNo(long sellerNo) {
		this.sellerNo = sellerNo;
	}

	public long getAddressNo() {
		return AddressNo;
	}

	public void setAddressNo(long addressNo) {
		AddressNo = addressNo;
	}

	public long getBuyerNo() {
		return buyerNo;
	}

	public void setBuyerNo(long buyerNo) {
		this.buyerNo = buyerNo;
	}

	public long getFruitNo() {
		return FruitNo;
	}

	public void setFruitNo(long fruitNo) {
		FruitNo = fruitNo;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(float expenditure) {
		this.expenditure = expenditure;
	}

	public Date getTransTime() {
		return transTime;
	}

	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}

	public int getTransProcess() {
		return transProcess;
	}

	public void setTransProcess(int transProcess) {
		this.transProcess = transProcess;
	}

	public long getNo() {
		return no;
	}

	@Override
	public String toString() {
		return "Transaction [no=" + no + ", sellerNo=" + sellerNo + ", AddressNo=" + AddressNo + ", FruitNo=" + FruitNo
				+ ", amount=" + amount + ", expenditure=" + expenditure + ", transTime=" + transTime + ", transProcess="
				+ transProcess + "]";
	}
	
}
