<%@ page import="ex0930.dao.DBManager" %>
<%@ page import="java.sql.Connection" %><%--
  Created by IntelliJ IDEA.
  User: swift
  Date: 2024-09-30
  Time: 오전 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>DB 연결 테스트</h1>
<%
    Connection con = DBManager.getConnection();
%>
연결 : <%=con%>
</body>
</html>
