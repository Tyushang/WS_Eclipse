package com.uplooking.service;

import com.uplooking.vo.Address;
import com.uplooking.vo.Users;

public interface AddressService {
	boolean addAddress(Address address);
	boolean deleteAddress(Address address);
	
	void showAddress(Users user);
	
	Address getAddressByNo(long addressNo);
	Address getAddressByNo(Users user, long addressNo);
}
