package com.uplooking.practice;

public class TicketWindow implements Runnable {
	private String windowName;
	private int soldNum;
	private TicketPool pool;

	public TicketWindow(String name, int soldNum, TicketPool pool) {
		this.windowName = name;
		this.soldNum = soldNum;
		this.pool = pool;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean res;
		while (res = pool.soldTicket(soldNum)) {
			if (res) {
//				System.out.println("Sold Success!");
			} else {
//				System.out.println("Sold Fail!");
			}
			System.out.println(windowName + " Remains:" + pool.getTicketsRemain());
		}
	}
}
