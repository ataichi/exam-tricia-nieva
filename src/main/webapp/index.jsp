<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="Connector.AlchemyConnector" %>
<%@ page import="Connector.CloudantClientClass" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>AlchemyAPI Image Analysis</title>
    </head>
    <body>
        <div class ="main">
            <label for="tab2"> Enter an Image URL </label>
            <div class ="content">
                <div id="content2">
                    <form action="IServlet" method="GET">
                        <input type="text" name="gurl" size="80">
                        <input type="submit" value="Extract Information">
                    </form> <br/>
					
                </div>
            </div>
			 
        </div>
    </body>
</html>