<%-- 
    Document   : showCart
    Created on : Oct 25, 2024, 1:08:30 AM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Cart" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Vector<Cart> vector=(Vector<Cart>)session.getAttribute("vectorCart");
        
        %>
        <form action="ProductURL" method="get">
            <input type="hidden" name="service" value="listAll">
            <p>Product Name <input type="text" name="pname" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="ProductURL">continue shopping</a></p>
        
        <table border="1">
            <caption>list products</caption>
            <tr>
                <th>ProductID</th>
                <th>ProductName</th>
                 <th>Quantity</th>
                <th>UnitPrice</th>
                <th>Discount</th>
                <th>subTotal</th>
                <th>Remove</th>
              </tr>
           <% for(Cart product:vector){%>
            <tr>
                <td><%=product.getProductID()%></td>
                <td><%=product.getProductName()%></td>
                <td><%=product.getQuantity()%></td>
                <td><%=product.getUnitPrice()%></td>
                <td><%=product.getDiscount()%></td>
                <td></td>
               <td><a href="CartURL?service=removeCart&pid=<%=product.getProductID()%>">remove</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
