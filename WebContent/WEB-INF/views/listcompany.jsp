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
<c:forEach var="row" items="${list}">
<table border="solid"><tr><td>Company Name</td><td>Location</td></tr><tr><td>
<c:out value="${row.company_name}"/></td><td>
<c:out value="${row.location}"/></td><td>
<a href="delete?companyname=${row.company_name}">delete</a>

</td></tr>
</table>
</c:forEach>

</body>
</html>