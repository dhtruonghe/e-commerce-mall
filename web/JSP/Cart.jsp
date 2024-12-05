<%-- 
    Document   : Cart
    Created on : Nov 5, 2024, 6:12:32 AM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Cart,entity.Categories,
        entity.Product,java.sql.ResultSet,entity.Customers" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Vector<Cart> vector = (Vector<Cart>)session.getAttribute("vectorCart");
        double grandTotal = 0;
        
        %>
        <form action="IndexURL" method="get">

        </form>
        <p><a href="IndexURL">continue shopping</a></p>

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
            <% if(vector!= null){
             for(Cart product:vector){
            double subTotal = product.getQuantity() * product.getUnitPrice();
                     grandTotal += subTotal;
            %>
            <tr>
                <td><%=product.getProductID()%></td>
                <td><%=product.getProductName()%></td>
                <td><%=product.getQuantity()%></td>
                <td><%=product.getUnitPrice()%></td>
                <td><%=product.getDiscount()%></td>
                <td><%=subTotal%></td>
                <td><a href="CartURL?service=remove&pid=<%=product.getProductID()%>">remove</a></td>
            </tr>
            <%}
}%>

            <tr>
                <td></td>
                <td colspan="4" align="right"><strong>Grand Total</strong></td>
                <td><%= grandTotal %></td>
                <td><a href="CartURL?service=removeAll">Remove All</a></td>
            </tr>



        </table>
        <a href="CartURL?service=checkOut">check out</a>
    </body>
</html>


