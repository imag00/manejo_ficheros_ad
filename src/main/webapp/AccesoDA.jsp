<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	boolean errorCamposVacios = false;
	
	Object camposVaciosObj = request.getAttribute("camposVacios");
	if (camposVaciosObj != null)
		errorCamposVacios = (boolean) camposVaciosObj;
	
	request.removeAttribute("camposVacios");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Tratamiento de ficheros</title>
    <style>
        .col {
            display: inline-block;
            margin: 5px;
        }
    
        td:first-child {
            min-width: 70px;
        }
        
    </style>
</head>
<body>
    <h1>TRATAMIENTO FICHEROS</h1>

    <form action="ServletAccesoDA" method="post">
        <div class="col">
            <table>
                <tr>
                    <td>Formato del fichero:</td>
                    <td>
                        <select name="fichero">
                            <option value="xls">XLS</option>
                            <option value="csv">CSV</option>
                            <option value="json">JSON</option>
                            <option value="xml">XML</option>
                        </select>
                    </td>
                </tr>
                <tr><td colspan="2"><hr></td></tr>
                <tr><td colspan="2">¿Qué quiere hacer con el fichero?</td></tr>
                <tr><td colspan="2"><br></td></tr>
                <tr>
                    <td>Lectura:</td>
                    <td><input type="radio" name="accion" value="lectura" checked></td>
                </tr>
                <tr>
                    <td>Escritura:</td>
                    <td><input type="radio" name="accion" value="escritura"></td>
                </tr>
            </table>
        </div>

        <div class="col">
            <table>
                <tr>
                    <td>DATO1:</td>
                    <td><input type="text" name="dato1"></td>
                </tr>
                <tr>
                    <td>DATO2:</td>
                    <td><input type="text" name="dato2"></td>
                </tr>
                <tr>
                    <td>DATO3:</td>
                    <td><input type="text" name="dato3"></td>
                </tr>
                <tr>
                    <td>DATO4:</td>
                    <td><input type="text" name="dato4"></td>
                </tr>
                <tr>
                    <td>DATO5:</td>
                    <td><input type="text" name="dato5"></td>
                </tr>
                <tr>
                    <td>DATO6:</td>
                    <td><input type="text" name="dato6"></td>
                </tr>
            </table>
            <% if (errorCamposVacios) {%>
            	<b style="color: red">(*) Los campos no pueden estar vacíos</b>
            <% } %>
        </div>
        <br><br>
        <input type="submit" value="Enviar">
    </form>
</body>
</html>