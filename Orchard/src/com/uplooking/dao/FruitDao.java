package com.uplooking.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.uplooking.vo.Fruit;
import com.uplooking.vo.Users;

public interface FruitDao {
	/*
	 * ��OBJ���������д�뵽���ݿ���Ӧ���ļ�¼��
	 * 
	 * ���أ�
	 * 	true  - д��ɹ�
	 *  false - д��ʧ��
	 */
	boolean obj2dbRecord(Fruit fruit) throws ClassNotFoundException, SQLException;
	
	/*
	 * �����ݿ���ļ�¼ȡ�������ݱ�ţ���ת��Ϊ��Ӧ��OBJ����
	 * 
	 * ���أ�
	 * 	null - �Ҳ���ָ����ŵļ�¼
	 * 	Fruit���� - �ҵ��ñ�ŵļ�¼������ת��ΪFruit����
	 */
	Fruit dbRecord2obj(long fruitNo) throws ClassNotFoundException, SQLException;
	
	ResultSet showFruit() throws ClassNotFoundException, SQLException;
	ResultSet showFruit(Users user) throws ClassNotFoundException, SQLException;
	boolean updateFruit(Fruit oldFruit, Fruit newFruit);
	boolean deleteFruit(Fruit fruit);
}
