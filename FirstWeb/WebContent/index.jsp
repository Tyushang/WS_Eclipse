<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.sql.ResultSet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>所有图书</h1>

<%
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "mysqlpass");
Statement state = conn.createStatement();

ResultSet rs = state.executeQuery("Select * from books");

while(rs.next()) {
	String bookname = rs.getString("bookname");
	String imagename = rs.getString("imagename");
	out.write(imagename + "<br/>");
	out.write("<img alt=\"\" src=\"./images/");
	out.print(imagename);
    out.write("\" >\r\n");
%>

<br/>

<%
}
%>

</body>
</html>