<%@page import="interfaz.ServletAccesoDA"%>
<%@page import="ficheros.JSONReaderWriter"%>
<%@page import="ficheros.Objeto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	ArrayList<Objeto> objetos = new ArrayList<>();
	try {
		objetos = JSONReaderWriter.read();
	} catch (Exception e) {
		ServletAccesoDA.throwError(request, response, "No se han podido cargar los datos");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
		<% for(Objeto obj : objetos) {%>
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