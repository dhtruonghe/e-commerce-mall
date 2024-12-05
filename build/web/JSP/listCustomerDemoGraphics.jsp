<%-- 
    Document   : listCustomerDemoGraphics
    Created on : Oct 24, 2024, 6:56:43 PM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.CustomerDemoGraphics" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Vector<CustomerDemoGraphics> vector = (Vector<CustomerDemoGraphics>)request.getAttribute("dataCustomerDemoGraphics");
        String title =(String)request.getAttribute("titleTable");
        %>
        <form action="CustomerDemoGraphicsURL" method="get">
            <p>Customer type id <input type="text" name="ctid" id="">
                <input type="hidden" name="service" value="listAll">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="CustomerDemoGraphicsURL?service=insertCustomerDemoGraphics">insert CustomerDemoGraphics</a></p>
        <table border=1>
            <caption><%=title%></caption>
            <tr>
                <th>CustomerTypeID</th>
                <th>CustomerDesc</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <%for(CustomerDemoGraphics cdGraphic : vector){%>
            <tr>
                <td><%=cdGraphic.getCustomerTypeID()%></td>
                <td><%=cdGraphic.getCustomerDesc()%></td>
                <td><a href="CustomerDemoGraphicsURL?service=updateCustomerDemoGraphics&ctid=<%=cdGraphic.getCustomerTypeID()%>">update</a></td>
                <td><a href="CustomerDemoGraphicsURL?service=deleteCustomerDemoGraphics&ctid=<%=cdGraphic.getCustomerTypeID()%>">delete</a></td>
            </tr>
            <%}%>
    </body>
</html>
