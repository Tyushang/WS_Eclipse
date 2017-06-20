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

<h1 align="center">最帅男人</h1>

<%
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.2.101:3306/mydb", "root", "mysqlpass");
Statement sta = conn.createStatement();
ResultSet rs = sta.executeQuery("select * from student");

while(rs.next()) {
	int id = rs.getInt("id");
	String name = rs.getString("name");
	int num  = rs.getInt("num");
	%>
	<table align="center" width=300 border=1>
	<tr>
	<td><%=id%></td>
	<td><%=name%></td>
	<td><%=num%></td>
	<td><a href="do_vote.jsp?id=<%=id%>">投票</a>
	</tr>
	</table>
<%
}
%>

</body>
</html>