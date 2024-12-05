<%-- 
    Document   : updateOrders
    Created on : Nov 1, 2024, 9:04:03 AM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet,java.util.Vector,entity.Orders" %>
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
          Vector<Orders> vector=(Vector<Orders>)request.getAttribute("vector");
          Orders od=vector.get(0);
         %>
        <form action="OrdersURL" method="post">
            <input type="hidden" name="service" value="insertOrders">
            <table>
                <caption>update Orders</caption>
                <tr>
                    <td><label for="OrderID">OrderID</label></td>
                    <td><input type="text" name="OrderID" id="OrderID" value="<%=od.getOrderID()%>"></td>
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
                    <td><input type="text" name="OrderDate" id="OrderDate" value="<%=od.getOrderDate()%>"></td>
                </tr>
                <tr>
                    <td><label for="RequiredDate">RequiredDate</label></td>
                    <td><input type="text" name="RequiredDate" id="RequiredDate" value="<%=od.getRequiredDate()%>"></td>
                </tr>
                <tr>
                    <td><label for="ShippedDate">ShippedDate</label></td>
                    <td><input type="text" name="ShippedDate" id="ShippedDate" value="<%=od.getShippedDate()%>"></td>
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
                    <td><input type="text" name="Freight" id="Freight" value="<%=od.getFreight()%>"></td>
                </tr>
                <tr>
                    <td><label for="ShipName">ShipName</label></td>
                    <td><input type="text" name="ShipName" id="ShipName" value="<%=od.getShipName()%>"></td>
                </tr>
                <tr>
                    <td><label for="ShipAddress">ShipAddress</label></td>
                    <td><input type="text" name="ShipAddress" id="ShipAddress" value="<%=od.getShipAddres()%>"></td>
                </tr>
                <tr>
                    <td><label for="ShipCity">ShipCity</label></td>
                    <td><input type="text" name="ReorderLevel" id="ReorderLevel" value="<%=od.getShipCity()%>"></td>
                </tr>
                
                <tr>
                    <td><label for="ShipRegion">ShipRegion</label></td>
                    <td><input type="text" name="ShipRegion" id="ShipRegion" value="<%=od.getShipRegion()%>"></td>
                </tr>
                <tr>
                    <td><label for="ShipPostalCode">ShipPostalCode</label></td>
                    <td><input type="text" name="ShipPostalCode" id="ShipPostalCode" value="<%=od.getShipPostalCode()%>"></td>
                </tr>
                <tr>
                    <td><label for="ShipCountry">ShipCountry</label></td>
                    <td><input type="text" name="ShipCountry" id="ShipCountry" value="<%=od.getShipCountry()%>"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="updateOrders" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>  
    </body>
</html>
