<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="ex0930.dto.MemberDTO"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>회원 정보</title>
    <style>
        table {border: 5px groove pink; width:500px}
        td, th {border: 1px black solid; padding: 10px}
        input {border: solid 1px gray}
    </style>
</head>
<body>

<c:if test="${not empty member}">
    <table cellspacing="0" align="center">
        <caption><h2>${member.name} 님의 회원정보</h2></caption>
        <tr>
            <td width="100px">ID</td>
            <td width="400px">${member.id}</td>
        </tr>
        <tr>
            <td>PWD</td>
            <td>${member.pwd}</td>
        </tr>
        <tr>
            <td>NAME</td>
            <td>${member.name}</td>
        </tr>
        <tr>
            <td>AGE</td>
            <td>${member.age}</td>
        </tr>
        <tr>
            <td>Phone</td>
            <td>${member.phone}</td>
        </tr>
        <tr>
            <td>Addr</td>
            <td>${member.addr}</td>
        </tr>
        <tr>
            <td>JoinDate</td>
            <td>${member.joinDate}</td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center; background-color: pink">
                <a href="index.jsp">홈으로</a>
            </td>
        </tr>
    </table>
</c:if>



</body>
</html>
