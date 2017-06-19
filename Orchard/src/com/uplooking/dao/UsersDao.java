package com.uplooking.dao;

import java.sql.SQLException;

import com.uplooking.vo.Users;

public interface UsersDao {
	/*
	 * ��OBJ���������д�뵽���ݿ���Ӧ���ļ�¼��
	 * 
	 * ���أ�
	 * 	true  - д��ɹ�
	 *  false - д��ʧ��
	 */
	boolean obj2dbRecord(Users user) throws ClassNotFoundException, SQLException;
	
	/*
	 * �����ݿ���ļ�¼ȡ�������ݱ�ţ���ת��Ϊ��Ӧ��OBJ����
	 * 
	 * ���أ�
	 * 	null - �Ҳ���ָ����ŵļ�¼
	 * 	Users���� - �ҵ��ñ�ŵļ�¼������ת��ΪUsers����
	 */
	Users dbRecord2obj(long userNo) throws ClassNotFoundException, SQLException;
	
	/*
	 * �����ݿ���ļ�¼ȡ���������˺ţ���ת��Ϊ��Ӧ��OBJ����
	 * 
	 * ���أ�
	 * 	null - �Ҳ���ָ���˺ŵļ�¼
	 * 	Users���� - �ҵ����˺ŵļ�¼������ת��ΪUsers����
	 */
	Users dbRecord2obj(String account) throws ClassNotFoundException, SQLException;

	boolean login(Users user) throws ClassNotFoundException, SQLException;

	boolean isLogin(Users user);

	boolean updateInfo(Users oldInfo, Users newInfo);

	boolean logout(Users user);
}
