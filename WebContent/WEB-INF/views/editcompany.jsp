<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Enter the new details</h1>
<div align="center">
<form action="editsuc" method="post">
Company Id:<input type="text" name="id"><br>
Company Name :<input  type="text" name="cname" placeholder="${m}"><br>
Location : <input type="text" name="loc"><br>
<input type="submit" value="Edit"> 
</form>
</div>
</body>
</html>