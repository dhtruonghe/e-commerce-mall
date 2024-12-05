<%-- 
    Document   : updateShippers
    Created on : Nov 1, 2024, 10:02:18 AM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet,java.util.Vector,entity.Shippers" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
          Vector<Shippers> vector=(Vector<Shippers>)request.getAttribute("vector");
          Shippers shipper=vector.get(0);
         %>
        <form action="ShippersURL" method="post">
            <input type="hidden" name="service" value="updateShippers">
            <table>
                <caption>update Shipperst</caption>
                <tr>
                    <td><label for="ShipperID">ShipperID</label></td>
                    <td><input type="text" name="ShipperID" id="ShipperID" value="<%=shipper.getShipperID()%>"></td>
                </tr>
                <tr>
                    <td><label for="CompanyName">CompanyName</label></td>
                    <td><input type="text" name="CompanyName" id="CompanyName" value="<%=shipper.getCompanyName()%>"></td>
                </tr>
                <tr>
                    <td><label for="Phone">Phone</label></td>
                    <td><input type="text" name="Phone" id="Phone" value="<%=shipper.getPhone()%>"></td>
                </tr>
                
                <tr>
                    <td><input type="submit" value="updateShippers" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>  
    </body>
</html>

