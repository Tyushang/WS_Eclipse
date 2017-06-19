package com.uplooking.practice;

public class BankAccount implements Runnable{

	private int balance;
	private int depositAmount;
	private int withdrawAmount;
	
	public BankAccount(int balance, int da, int wa)
	{
		this.balance = balance;
		this.depositAmount = da;
		this.withdrawAmount = wa;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		balance += depositAmount;
		System.out.println("Balance:" + balance);
	}

}
