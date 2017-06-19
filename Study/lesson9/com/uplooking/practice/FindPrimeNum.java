package com.uplooking.practice;

public class FindPrimeNum implements Runnable {

	private int numBegin;
	private int numEnd;
	
	public FindPrimeNum(int begin, int end)
	{
		this.numBegin = begin;
		this.numEnd = end;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=numBegin; i<=numEnd; i++) {
			if(isPrimeNum(i)) {
				System.out.printf("[%4d - %4d]: %4d\n", numBegin, numEnd, i);
			}
		}
	}
	public boolean isPrimeNum(int n) {
		for (int j = 2; j <= Math.sqrt(n); j++) {
			if (n%j == 0) {
				return false;
			}
		}
		return true;
	}
}
