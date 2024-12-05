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
            <p>Order ID: <%= o.getOrderID() %></p>
            <p>Order Date: <%= o.getOrderDate() %></p>
            <p>Required Date: <%= o.getRequiredDate() %></p>
            <p>Shipped Date: <%= o.getShippedDate() %></p>
            <p>Contact Name: <%= cus.getContactName() %></p>
            <p>Address: <%= cus.getAddress() %></p>
            <p>Phone: <%= cus.getPhone() %></p>
            <p>Fax: <%= cus.getFax() %></p>
            <%
                
                double generalTotal = 0;
            %>
            <table border="1">
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Unit Price</th>
                    <th>Quantity</th>
                    <th>Discount</th>
                    <th>Subtotal</th>
                </tr>
                <%
                    List<OrderDetails> ods = (List<OrderDetails>) 
                            request.getAttribute("ods");
                if (ods != null && !ods.isEmpty()) {
                    
                        for (OrderDetails od : ods) {
                            double subTotal = od.getUnitPrice() * od.getQuantity() * (1 - od.getDiscount());
                            generalTotal += subTotal;
                            
                %>
                <tr>
                    <td><%= od.getProductID() %></td>
                    <td><%=pro.getProductByID(od.getProductID())  %></td>
                    <td><%= od.getUnitPrice() %></td>
                    <td><%= od.getQuantity() %></td>
                    <td><%= od.getDiscount() %></td>
                    <td><%= subTotal %></td>
                </tr>
                <%
                        }
                    }
                %>
            </table>
            <h3>General Total: <%= generalTotal %></h3>
        </form>
    </body>
</html>