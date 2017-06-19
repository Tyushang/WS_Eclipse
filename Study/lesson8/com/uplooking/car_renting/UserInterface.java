package com.uplooking.car_renting;

public interface UserInterface {
	String getKeyStrByObj(User user);
	String getKeyStrByItem(String item);
	String obj2str(User user);
	User str2obj(String str);
}
