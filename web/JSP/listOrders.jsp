<%-- 
    Document   : listOrder
    Created on : Oct 16, 2024, 8:26:46 PM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Orders" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Vector<Orders> vector = (Vector<Orders>)request.getAttribute("dataOrders");
        String title =(String)request.getAttribute("titleTable");
        %>
        <form action="OrdersURL" method="get">
            <p>Order ID<input type="text" name="ordersID" id="">
                <input type="hidden" name="service" value="listAll">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="OrdersURL?service=insertOrders">insert Orders</a></p>
        <table border=1>
            <caption><%=title%></caption>
            <tr>
                <th>OrderID</th>
                <th>CustomerID</th>
                <th>EmployeeID</th>
                <th>OrderDate</th>
                <th>RequiredDate</th>
                <th>ShippedDate</th>
                <th>ShipVia</th>
                <th>Freight</th>
                <th>ShipName</th>
                <th>ShipAddress </th>
                <th>ShipCity</th>
                <th>ShipRegion</th>
                <th>ShipPostalCode</th>
                <th>ShipCountry</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <%for(Orders order : vector){%>
            <tr>
                <td><a href=OrderDetailURL?service=searchOrderID&oid=<%=order.getOrderID()%>"><%=order.getOrderID()%></a></td>
                <td><%=order.getCustomerID()%></td>
                <td><%=order.getEmployeeID()%></td>
                <td><%=order.getOrderDate()%></td>
                <td><%=order.getRequiredDate()%></td>
                <td><%=order.getShippedDate()%></td>
                <td><%=order.getShipVia()%></td>
                <td><%=order.getFreight()%></td>
                <td><%=order.getShipName()%></td>
                <td><%=order.getShipAddres()%></td>
                <td><%=order.getShipCity()%></td>
                <td><%=order.getShipRegion()%></td>
                <td><%=order.getShipPostalCode()%></td>
                <td><%=order.getShipCountry()%></td>
                <td><a href="OrdersURL?service=updateOrders&oid=<%=order.getOrderID()%>">update</a></td>
                <td><a href="OrdersURL?service=deleteOrders&oid=<%=order.getOrderID()%>">delete</a></td>
            </tr>
            <%}%>
    </body>
</html>
