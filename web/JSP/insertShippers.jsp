<%-- 
    Document   : insertShippers
    Created on : Oct 24, 2024, 8:43:18 PM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ShippersURL" method="post">
            <input type="hidden" name="service" value="insertShippers">
            <table>
                <caption>Insert Shipperst</caption>
                <tr>
                    <td><label for="ShipperID">ShipperID</label></td>
                    <td><input type="text" name="ShipperID" id="ShipperID" readonly></td>
                </tr>
                <tr>
                    <td><label for="CompanyName">CompanyName</label></td>
                    <td><input type="text" name="CompanyName" id="CompanyName" required></td>
                </tr>
                <tr>
                    <td><label for="Phone">Phone</label></td>
                    <td><input type="text" name="Phone" id="Phone"></td>
                </tr>
                
                <tr>
                    <td><input type="submit" value="insertShippers" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>  
    </body>
</html>
