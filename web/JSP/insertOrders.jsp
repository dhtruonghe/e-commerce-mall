<%-- 
    Document   : insertOrders
    Created on : Oct 24, 2024, 8:37:57 PM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <% ResultSet rsCus=(ResultSet)request.getAttribute("rsCus");
          ResultSet rsEmploy=(ResultSet)request.getAttribute("rsEmploy");
          ResultSet rsShip=(ResultSet)request.getAttribute("rsShip");
         %>
        <form action="OrdersURL" method="post">
            <input type="hidden" name="service" value="insertOrders">
            <table>
                <caption>Insert Orders</caption>
                <tr>
                    <td><label for="OrderID">OrderID</label></td>
                    <td><input type="text" name="OrderID" id="OrderID" readonly></td>
                </tr>
                <tr>
                    <td><label for="CustomerID">CustomerID</label></td>
                    <td>
                        <select name="CustomerID" id="CustomerID">
                            <%while(rsCus.next()){%>
                                <option value="<%=rsCus.getString(1)%>"><%=rsCus.getString(2)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="EmployeeID">EmployeeID</label></td>
                    <td>
                        <select name="EmployeeID" id="EmployeeID">
                            <%while(rsEmploy.next()){%>
                                <option value="<%=rsEmploy.getInt(1)%>"><%=rsEmploy.getString(2)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="OrderDate">OrderDate</label></td>
                    <td><input type="text" name="OrderDate" id="OrderDate" required></td>
                </tr>
                <tr>
                    <td><label for="RequiredDate">RequiredDate</label></td>
                    <td><input type="text" name="RequiredDate" id="RequiredDate"></td>
                </tr>
                <tr>
                    <td><label for="ShippedDate">ShippedDate</label></td>
                    <td><input type="text" name="ShippedDate" id="ShippedDate"></td>
                </tr>
                <tr>
                    <td><label for="ShipVia">ShipVia</label></td>
                    <td>
                        <select name="ShipVia" id="ShipVia">
                            <%while(rsShip.next()){%>
                                <option value="<%=rsShip.getInt(1)%>"><%=rsShip.getString(2)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="Freight">Freight</label></td>
                    <td><input type="text" name="Freight" id="Freight"></td>
                </tr>
                <tr>
                    <td><label for="ShipName">ShipName</label></td>
                    <td><input type="text" name="ShipName" id="ShipName"></td>
                </tr>
                <tr>
                    <td><label for="ShipAddress">ShipAddress</label></td>
                    <td><input type="text" name="ShipAddress" id="ShipAddress"></td>
                </tr>
                <tr>
                    <td><label for="ShipCity">ShipCity</label></td>
                    <td><input type="text" name="ReorderLevel" id="ReorderLevel"></td>
                </tr>
                <tr>
                    <td><label for="ShipCity">ShipCity</label></td>
                    <td><input type="text" name="ShipCity" id="ShipCity"></td>
                </tr>
                <tr>
                    <td><label for="ShipRegion">ShipRegion</label></td>
                    <td><input type="text" name="ShipRegion" id="ShipRegion"></td>
                </tr>
                <tr>
                    <td><label for="ShipPostalCode">ShipPostalCode</label></td>
                    <td><input type="text" name="ShipPostalCode" id="ShipPostalCode"></td>
                </tr>
                <tr>
                    <td><label for="ShipCountry">ShipCountry</label></td>
                    <td><input type="text" name="ShipCountry" id="ShipCountry"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="insertOrders" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>  
    </body>
</html>
