package services;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import dao.StudentDaoInterface;
import dao.imp.StudentDaoImp;
import vo.Student;

public class Entry {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudentDaoInterface sdi = new StudentDaoImp();
		
		try {
			RowSetFactory factory = RowSetProvider.newFactory();  
			CachedRowSet crs = factory.createCachedRowSet();  
			sdi.select(new Student(), crs);
			while (crs.next()) {
				System.out.println(crs.getString(1) + crs.getString(2) + crs.getString(3));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
