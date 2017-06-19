package dao;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import vo.Student;

public interface StudentDaoInterface {
	int delete() throws ClassNotFoundException, SQLException;
	int insert(Student stu) throws ClassNotFoundException, SQLException;
	int update(Student stu) throws ClassNotFoundException, SQLException;
	void select(Student std, CachedRowSet crs) throws ClassNotFoundException, SQLException;
}
