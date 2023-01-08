<%@page import="interfaz.ServletAccesoDA"%>
<%@page import="ficheros.JSONReaderWriter"%>
<%@page import="ficheros.Dato"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
ArrayList<Dato> objetos = new ArrayList<>();
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
			<td>A�o</td>
			<td>N�mero de plazas</td>
			<td>N�mero de alumnos 0-1 a�os</td>
			<td>N�mero de alumnos 1-2 a�os</td>
			<td>N�mero de alumnos 2-3 a�os</td>
			<td>N�mero total de alumnos</td>
		</tr>
		<%
		for(Dato obj : objetos) {
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