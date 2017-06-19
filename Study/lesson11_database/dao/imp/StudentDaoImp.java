package dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;

import dao.BaseDaoInterface;
import dao.StudentDaoInterface;
import vo.Student;

public class StudentDaoImp implements StudentDaoInterface {

	private class BaseDaoImpl implements BaseDaoInterface {

		@Override
		public ResultSet exeQuery(Statement sta, String sql) throws SQLException {
			return sta.executeQuery(sql);
		}

		@Override
		public int exeUpdate(Statement sta, String sql) throws SQLException {
			return sta.executeUpdate(sql);
		}
		
	}
	
	@Override
	public int insert(Student stu) throws ClassNotFoundException, SQLException {
		String sql = String.format("insert into student(%s, %s, %s) values('%s', %d, '%s')",
				"name", "age", "sex",
				stu.getName(),
				stu.getAge(),
				stu.getSex());
		System.out.println(sql);
		BaseDaoImpl bdi = new BaseDaoImpl();
		//返回受影响的行数
		return BaseDao.update(bdi, sql);
	}

	@Override
	public int delete() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Student stu) throws ClassNotFoundException, SQLException {
		String sql = String.format("update name='%s', age=%d, sex='%s' where",
				stu.getName(),
				stu.getAge(),
				stu.getSex());
		
		BaseDaoImpl bdi = new BaseDaoImpl();
		return BaseDao.update(bdi, sql);
	}

	@Override
	public void select(Student stu, CachedRowSet crs) throws ClassNotFoundException, SQLException {
		String sql = String.format("select * from student where name='%s'",
				stu.getName());
		BaseDaoImpl bdi = new BaseDaoImpl();
		BaseDao.querry(bdi, sql, crs);
	}
}
