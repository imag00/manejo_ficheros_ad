<%@page import="ficheros.IOFichero"%>
<%@page import="ficheros.DataRow"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>Dato1</td>
			<td>Dato2</td>
			<td>Dato3</td>
			<td>Dato4</td>
			<td>Dato5</td>
			<td>Dato6</td>
		</tr>
		<% 
		IOFichero handler = (IOFichero) request.getAttribute("handler");
		for(DataRow obj : handler.read()) {
		%>
		<tr>
			<%for (int i = 0; i < obj.getData().length; i++) { %>
			<td><%=obj.getData()[i] %></td>
			<%} %>
		</tr>
		<% } %>
	</table>

	<br>
	<a href="AccesoDA">Volver</a>
</body>
</html>