package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface BaseDaoInterface {
	ResultSet exeQuery(Statement sta, String sql) throws SQLException;
	int exeUpdate(Statement sta, String sql) throws SQLException;
}
