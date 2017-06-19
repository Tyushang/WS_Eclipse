package com.uplooking.service;

import java.sql.SQLException;

import com.uplooking.vo.Fruit;
import com.uplooking.vo.Users;

public interface FruitService {
	void showFruit();
	void showFruit(Users user);
	boolean updateFruit(Fruit oldFruit, Fruit newFruit) throws ClassNotFoundException, SQLException;
	boolean addFruit(Fruit fruit);
	boolean deleteFruit(Fruit fruit);
	
	Fruit getFruitByNo(long fruitNo);
}
