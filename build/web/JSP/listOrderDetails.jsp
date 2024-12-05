<%-- 
    Document   : listOrderDetails
    Created on : Oct 24, 2024, 6:35:07 PM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.OrderDetails" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Vector<OrderDetails> vector = (Vector<OrderDetails>)request.getAttribute("dataOrderDetails");
        String title =(String)request.getAttribute("titleTable");
        %>
        <form action="OrderDetailsURL" method="get">
            <p>Order ID <input type="text" name="oid" id="">
                <input type="hidden" name="service" value="listAll">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="OrderDetailsURL?service=insertOrderDetails">insert OrderDetails</a></p>
        <table border=1>
            <caption><%=title%></caption>
            <tr>
                <th>OrderID</th>
                <th>ProductID</th>
                <th>UnitPrice</th>
                <th>Quantity</th>
                <th>Discount</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <%for(OrderDetails detail : vector){%>
            <tr>
                <td><%=detail.getOrderID()%></td>
                <td><%=detail.getProductID()%></td>
                <td><%=detail.getUnitPrice()%></td>
                <td><%=detail.getQuantity()%></td>
                <td><%=detail.getDiscount()%></td>
                <td><a href="OrderDetailsURL?service=updateOrderDetails&oid=<%=detail.getOrderID()%>">update</a></td>
                <td><a href="OrderDetailsURL?service=deleteOrderDetails&oid=<%=detail.getOrderID()%>">delete</a></td>
            </tr>
            <%}%>
    </body>
</html>
