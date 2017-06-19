package com.uplooking.practice;

public class TicketPool {
	private String poolName;
	private int ticketsRemain;
	
	public TicketPool(String windowName, int totalTickets) {
		this.poolName = windowName;
		this.ticketsRemain = totalTickets;
	}
	
	public boolean soldTicket(int tickets) {
		if(tickets > ticketsRemain) {
			return false;
		}
		ticketsRemain -= tickets;
		return true;
	}

	public String getPoolName() {
		return poolName;
	}

	public int getTicketsRemain() {
		return ticketsRemain;
	}
}
