package com.uplooking.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.uplooking.vo.Address;
import com.uplooking.vo.Users;

public interface AddressDao {
	/*
	 * ��OBJ���������д�뵽���ݿ���Ӧ���ļ�¼��
	 * 
	 * ���أ�
	 * 	true  - д��ɹ�
	 *  false - д��ʧ��
	 */
	
	boolean obj2dbRecord(Address address) throws ClassNotFoundException, SQLException;
	
	/*
	 * �����ݿ���ļ�¼ȡ�������ݱ�ţ���ת��Ϊ��Ӧ��OBJ����
	 * 
	 * ���أ�
	 * 	null - �Ҳ���ָ����ŵļ�¼
	 * 	Address���� - �ҵ��ñ�ŵļ�¼������ת��ΪAddress����
	 */
	Address dbRecord2obj(long addressNo) throws ClassNotFoundException, SQLException;

	ResultSet showAddress(Users user) throws ClassNotFoundException, SQLException;
	boolean updateAddress(Address oldAddress, Address newAddress);
	boolean deleteAddress(Address address) throws ClassNotFoundException, SQLException;
}
