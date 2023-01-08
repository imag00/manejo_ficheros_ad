<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
String error = "Ha ocurrido un error";
Object errorObj = request.getAttribute("error");
if (errorObj != null)
	error = (String) errorObj;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
<style type="text/css">
h2 {
	color: red;
}
</style>
</head>
<body>
	<h1>TRATAMIENTO FICHEROS</h1>
	<hr>

	<h2>
		<%=request.getAttribute("error")%>
	</h2>
	<br>

	<a href="AccesoDA">Volver</a>
</body>
</html>