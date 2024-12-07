<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@page import="java.util.Vector,entity.Cart,entity.OrderDetails,
        entity.Orders,java.sql.ResultSet,entity.Customers,entity.Product" %>
<html>
    <head>
        <title>Bill</title>
    </head>
    <body>
        <form action="CartURL" method="get">
            <h2>BILL</h2>
            <%
                Product pro = new Product();
//                HttpSession session = request.getSession(true);
                Customers cus = (Customers)session.getAttribute("customer");
                Orders o = (Orders)request.getAttribute("o");
                 
            %>
            <p>Contact Name: <%= cus.getContactName() %></p>
            <p>Order Date: <%= o.getOrderDate() %></p>
            <p>Required Date: <%= o.getRequiredDate() %></p>
            <p>Shipped Date: <%= o.getShippedDate() %></p>
            <p>Address: <%= cus.getAddress() %></p>
            <p>Phone: <%= cus.getPhone() %></p>
            <p>Fax: <%= cus.getFax() %></p>
            
        </form>
    </body>
</html>