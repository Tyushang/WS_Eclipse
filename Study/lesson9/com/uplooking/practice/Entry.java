package com.uplooking.practice;

public class Entry {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		//第1题
//		FindPrimeNum f0 = new FindPrimeNum(1, 1000);
//		FindPrimeNum f1 = new FindPrimeNum(1001, 2000);
//		FindPrimeNum f2 = new FindPrimeNum(2001, 3000);
//		Thread t0 = new Thread(f0);
//		Thread t1 = new Thread(f1);
//		Thread t2 = new Thread(f2);
//		
//		t0.start();
//		t1.start();
//		t2.start();

//		//第2题
//		BankAccount ba = new BankAccount(0, 100, 0);
//		Thread t0 = new Thread(ba);
//		Thread t1 = new Thread(ba);
//		Thread t2 = new Thread(ba);
//		t0.start();
//		t1.start();
//		t2.start();
		
		//question 3/4
		TicketPool childWindow = new TicketPool("Child Pool", 100);
		TicketPool adultWindow = new TicketPool("Adult Pool", 100);
		TicketPool olderWindow = new TicketPool("Older Pool", 100);
		
		TicketWindow r0 = new TicketWindow("Child Window", 1, adultWindow);
		TicketWindow r1 = new TicketWindow("Adult Window", 1, adultWindow);
		TicketWindow r2 = new TicketWindow("Older Window", 1, adultWindow);
		
		Thread t0 = new Thread(r0, "Child");
		Thread t1 = new Thread(r1, "Adult");
		Thread t2 = new Thread(r2, "Older");
		
		t0.start();
		t1.start();
		t2.start();
	}

}
