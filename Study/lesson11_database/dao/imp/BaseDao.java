package dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;

import dao.BaseDaoInterface;

public class BaseDao {
	private static String MYSQL_DRV = "com.mysql.jdbc.Driver";
	private static String MYSQL_URL = "jdbc:mysql://localhost/mysql?useUnicode=true&characterEncoding=utf8";
	private static String MYSQL_USERNAME = "root";
	private static String MYSQL_PASSWORD = "mysqlpass";
	
	public static void querry(BaseDaoInterface bdi, String sql, CachedRowSet crs) throws ClassNotFoundException, SQLException {
		Class.forName(MYSQL_DRV);
		Connection conn = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
		Statement sta = conn.createStatement();
		
		ResultSet rs = bdi.exeQuery(sta, sql);
		crs.populate(rs);
		
		rs.close();
		sta.close();
		conn.close();
	}
	
	public static int update(BaseDaoInterface bdi, String sql) throws ClassNotFoundException, SQLException {
		Class.forName(MYSQL_DRV);
		Connection conn = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
		Statement sta = conn.createStatement();
		
		int ret = bdi.exeUpdate(sta, sql);
		
		sta.close();
		conn.close();
		return ret;
	}
}
