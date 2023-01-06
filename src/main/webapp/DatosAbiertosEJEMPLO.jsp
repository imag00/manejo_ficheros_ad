<%@page import="java.util.ArrayList"%>
<%@page import="ficheros.EjemploReaderWriter"%>
<%@page import="ficheros.Objeto"%>
<%@page import="interfaz.ServletAccesoDA"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	// Aquí puedes llamar al método de leer tu arcivho:
	// try {
	//    El método devuelve un ArrayList:
	ArrayList<Objeto> objetos = EjemploReaderWriter.read();
	// } catch (Exception e) {
	//    Te da error algo? Lanza:
	//    ServletAccesoDA.throwError(request, response, "Descripción del error");
	//    Y él solo te redirigirá a la página de error
	// }	
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>NOMBRE DEL JSP</title>
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