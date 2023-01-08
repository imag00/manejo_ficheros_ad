<%@page import="interfaz.ServletAccesoDA"%>
<%@page import="ficheros.XMLReaderWriter"%>
<%@page import="ficheros.Dato"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    // Aquí puedes llamar al método de leer tu arcivho:
    	ArrayList<Dato> objetos = new ArrayList<>();
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