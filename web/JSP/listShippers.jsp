<%-- 
    Document   : listShippers
    Created on : Oct 24, 2024, 6:45:24 PM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Shippers" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Vector<Shippers> vector = (Vector<Shippers>)request.getAttribute("dataShippers");
        String title =(String)request.getAttribute("titleTable");
        %>
        <form action="ShippersURL" method="get">
            <p>CompanyName <input type="text" name="cname" id="">
                <input type="hidden" name="service" value="listAll">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="ShippersURL?service=insertShippers">insert Shippers</a></p>
        <table border=1>
            <caption><%=title%></caption>
            <tr>
                <th>ShipperID</th>
                <th>CompanyName</th>
                <th>Phone</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <%for(Shippers shipper : vector){%>
            <tr>
                <td><%=shipper.getShipperID()%></td>
                <td><%=shipper.getCompanyName()%></td>
                <td><%=shipper.getPhone()%></td>
                <td><a href="ShippersURL?service=updateShippers&sid=<%=shipper.getShipperID()%>">update</a></td>
                <td><a href="ShippersURL?service=deleteShippers&sid=<%=shipper.getShipperID()%>">delete</a></td>
            </tr>
            <%}%>
    </body>
</html>
