<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.util.HashSet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

String ipAddr = request.getRemoteAddr();

HashSet<String> hs = (HashSet<String>)application.getAttribute("hash_set");

if(hs == null) {
	hs = new HashSet<String>();
	session.setAttribute("hash_set", hs);
}

if((hs.contains(ipAddr))) {
	response.sendRedirect("index.jsp");
}

Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.2.101:3306/mydb", "root", "mysqlpass");
Statement sta = conn.createStatement();

int id = Integer.valueOf(request.getParameter("id"));

ResultSet rs = sta.executeQuery("select * from student where id=" + id);

if(rs.next()){
int num = rs.getInt("num") + 1;
sta.executeUpdate("update student set num=" + num + " where id=" + id);
}

response.sendRedirect("index.jsp");

%>
</body>
</html>