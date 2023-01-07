<%@page import="interfaz.ServletAccesoDA"%>
<%@page import="ficheros.XMLReaderWriter"%>
<%@page import="ficheros.Objeto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
	// Aquí puedes llamar al método de leer tu arcivho:
	ArrayList<Objeto> objetos = new ArrayList<>();
	try {
		 objetos = XMLReaderWriter.read();
	 } catch (Exception e) {
	   ServletAccesoDA.throwError(request, response, e.getMessage());
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