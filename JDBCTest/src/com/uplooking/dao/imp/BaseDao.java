package com.uplooking.dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
	private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/mydb?characterEncoding=utf8&useUnicode=true";
	private static final String MYSQL_USERNAME = "root";
	private static final String MYSQL_PASSWORD = "mysqlpass";
	public static Connection con = null;
	public static Statement sta = null;
	public static ResultSet rs = null;

	public static void openCon() throws ClassNotFoundException, SQLException {
		Class.forName(MYSQL_DRIVER);
		con = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
		
	}

	public static int update(String sql) throws ClassNotFoundException, SQLException {
		sta = con.createStatement();
		int num = sta.executeUpdate(sql);
		return num;
	}
	public static ResultSet query(String sql)throws ClassNotFoundException, SQLException {
		sta = con.createStatement();
		rs = sta.executeQuery(sql);
		return rs;
	}

	public static void closeAll(ResultSet rs, Statement sta, Connection con) {
		try {
			if (rs != null) {
				rs.close();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs = null;
			try {
				if (sta != null) {
					sta.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sta = null;
				try {
					if (con != null) {
						con.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					con = null;
				}
			}
		}
	}
}
