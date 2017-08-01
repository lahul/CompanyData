<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company Lists</title>
</head>
<body>
<div align="center">
<form action="addordelete" method="post">
<input style="width:150px;height:30px" type="submit" name="action1" value="Add Company" >
</form>
</div>
<div align="center">
<form action="listemp" method="post">
<input style="width:150px;height:30px" type="submit" name="listkey" value="Employee list" >
</form>
</div>
<table border="solid"><tr><th>Company Id</th><th>Company Name</th><th>Location</th><th></th></tr>
<c:forEach var="row" items="${list}"><tr><td>
<c:out value="${row.id}"/></td><td>
<c:out value="${row.company_name}"/></td><td>
<c:out value="${row.location}"/></td><td>
<a href="delete?cid=${row.id}">delete</a>
<a href="edit?cid=${row.id}">edit</a>
<a href="addemp?cid=${row.id}">AddEmployee</a>
</td></tr>
</c:forEach>
</table>
</body>
</html>