package com.uplooking.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.uplooking.vo.Tran;
import com.uplooking.vo.Users;

public interface TranDao {
	/*
	 * ��OBJ���������д�뵽���ݿ���Ӧ���ļ�¼��
	 * 
	 * ���أ�
	 * 	true  - д��ɹ�
	 *  false - д��ʧ��
	 */
	boolean obj2dbRecord(Tran transaction) throws ClassNotFoundException, SQLException;
	
	/*
	 * �����ݿ���ļ�¼ȡ�������ݱ�ţ���ת��Ϊ��Ӧ��OBJ����
	 * 
	 * ���أ�
	 * 	null - �Ҳ���ָ����ŵļ�¼
	 * 	Transaction���� - �ҵ��ñ�ŵļ�¼������ת��ΪTransaction����
	 */
	Tran dbRecord2obj(long transactionNo) throws ClassNotFoundException, SQLException;
	
	ResultSet showTransaction(Users user) throws ClassNotFoundException, SQLException;
	
}
