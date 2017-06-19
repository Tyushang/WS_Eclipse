package com.uplooking.service;

import com.uplooking.vo.Address;
import com.uplooking.vo.Fruit;
import com.uplooking.vo.Users;

public interface TranService {
	void showTransaction(Users user);
	boolean submitOrder(Users user, Fruit fruit, Address address, float amount);
	void generateReport(Users user);
}
