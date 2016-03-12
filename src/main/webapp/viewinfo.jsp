<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="Connector.AlchemyConnector" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title> Information Extraction Complete </title>
    </head>
    <body>
        
        <label> Information Extraction Complete </label>
        	 
        <%
				if (request.getAttribute("age") != null){
					out.println("<h1> Age: "+request.getAttribute("age")+"</h1><br/>");
				}
				
				if (request.getAttribute("gender") != null){
					out.println("<h1> Gender: "+request.getAttribute("gender")+"</h1>");
				}
		%>
    </body>
</html>