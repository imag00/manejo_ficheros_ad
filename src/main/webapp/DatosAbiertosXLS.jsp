<%@page import="ficheros.xls.ExcelHandler"%>
<%@page import="ficheros.Dato"%>
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
		<%
		for(Dato obj : excelHandler.read()) {
		%>
			<tr>
				<td><%=obj.getAnio()%></td>
				<td><%=obj.getPlazas()%></td>
				<td><%=obj.getAlumnosCeroUno()%></td>
				<td><%=obj.getAlumnosUnoDos()%></td>
				<td><%=obj.getAlumnosDosTres()%></td>
				<td><%=obj.getAlumnosTotal()%></td>
			</tr>
		<% } %>
	</table>
	
	<br>
	<a href="AccesoDA">Volver</a>
</body>
</html>