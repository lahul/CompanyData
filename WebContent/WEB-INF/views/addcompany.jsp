<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<script type="text/javascript">
function checkinpu(){
	var x=document.forms["form1"]["id"].value;
	//var regx=/[1-9]*/;
	if(!(x=="/[1-9]*/")){
		alert("id should be a integer");
		return false;
	}
}
</script>
</head>
<body>
<div align="center">
<form name="form1"  onsubmit="return checkinpu()" action="addcomp" method="post">
<table><tr><td>
Company Id<input type="text" name="id"></td></tr><tr><td>
Company Name:<input type="text" name="cname"></td></tr><tr><td>
Location:<input type="text" name="location"></td></tr><tr><td>
<input type="submit" value="Add"></td></tr>
</table>
</form>
</div>
</body>
</html>