<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee List</title>
</head>
<body><div align="center">
<form action="search" method="post">
<input type="text" name="search" placeholder="search here">
<input type="submit" value="go">
</form>

<table border="solid"><tr><th>Employee Id</th><th>Employee name</th><th>Company name</th><th>link</th></tr>
<c:forEach var="row" items="${list}"><tr><td>
<c:out value="${row.eid}"/></td><td>
<c:out value="${row.ename}"/></td><td>
<c:out value="${row.cname}"/></td><td>
<a href="deleteemployee?eid=${row.eid}">delete</a>
<a href="editemployee?eid=${row.eid}&cname=${row.cname}">edit</a>
</td></tr>
</c:forEach>
</table>
</body>
</div>
</html>