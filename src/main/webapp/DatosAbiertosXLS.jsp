<%@page import="ficheros.Objeto"%>
<%@page import="dev.amrv.xls.ExcelHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Datos abiertos XLS/XLSX</title>
<%
	ExcelHandler excelHandler = (ExcelHandler) request.getAttribute("handler");
%>
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
		<% for(Objeto obj : excelHandler.read()) {%>
			<tr>
				<td><%= obj.getDato1() %></td>
				<td><%= obj.getDato2() %></td>
				<td><%= obj.getDato3() %></td>
				<td><%= obj.getDato4() %></td>
				<td><%= obj.getDato5() %></td>
				<td><%= obj.getDato6() %></td>
			</tr>
		<% } %>
	</table>
	
	<br>
	<a href="AccesoDA">Volver</a>
</body>
</html>