<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Employee</title>
<script type="text/javascript">
function checkinput(){
	var x=document.forms["form1"]["eid"].value;
	if(!(x=="/[1-9]*/")){
		alert("Employee Id should be an integer");
		return false;
	}
}
</script>
</head>
<body>
<form name="form1" onsubmit="return checkinput()" action="emp" method="post">
<div align="center">
<table border="solid"><tr><td>
Employee Id:<input type="text" name="eid"></td></tr><tr><td>
Employee Name:<input type="text" name="ename"></td></tr><tr><td>
<input type="submit" value="Add Eemployee"></td></tr></table>
</div>
</form>
</body>
</html>