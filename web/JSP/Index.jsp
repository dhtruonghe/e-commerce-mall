<%-- 
    Document   : Index
    Created on : Oct 25, 2024, 1:21:10 AM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Customers,entity.Categories,
        entity.Product,java.sql.ResultSet" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <link rel="stylesheet" href="styleindex.css">
        <style>
            a{
                text-decoration:none;

            }
            nav > a{
                color: white;
            }
            nav .info .menu{
                margin: 30px;

            }
            nav {
                background-color: rgb(104,132,142);
            }
            .info {
                display: inline;
            }
            .container {
                display: flex;
                justify-content:space-around;
            }
            .content {
                display: flex;
                justify-content: space-between;
                align-item: center;
            }
            .menu .products {
                display: inline;
               margin: 10 50 0 50;
            }

        </style>
    </head>
    <body>
        <% 
//            ResultSet rsCate=(ResultSet)request.getAttribute("rsCate");
            Vector<Categories> vectorCate = (Vector<Categories>)
                    request.getAttribute("vectorCate");
            Vector<Product> vectorProducts = (Vector<Product>)
                    request.getAttribute("vectorProducts");
        %>
        <% Customers cus = (Customers)session.getAttribute("customer");%>
        <form action="IndexURL" method="GET">

            <nav>

                <div class="container">
                    <p class="info"> rollNumber :
                        <%if(cus!=null){%>
                        <%=cus.getContactName()%>
                        <%}%>
                    </p>
                    <p class="info">
                        WelCome:
                        <%if(cus!=null){%>
                        <%=cus.getCustomerID()%>
                        <%}%>
                    </p>
                </div>

                <div class="container">

                    <%if(cus==null){%>
                    <p><a href="IndexURL?service=login">login</a></p>
                    <p><a href="CustomersURL?service=Register">Register</a></p>
                    <%} else{%> 
                    <p><a href="IndexURL?service=logout">logout</a></p>
                    <%}%>

                    <p>
                        <a href="CartURL?service=cart">Show Cart</a>
                    </p>
                </div>
            </nav>



            <div class="content">
                <div class="menu">
                    <% for(Categories cate : vectorCate) {%>
                    <p><a href="IndexURL?service=getProduct&cid=<%=cate.getCategoryID()%>"><%=cate.getCategoryName()%></a></p>
                        <% }%>
                </div>
                <div class="products">
                    <table border="1">
                        
                        <tbody>
                        <%if(vectorProducts !=null){
                            for(Product pro : vectorProducts){%>
                            <tr>
                                <td><%=pro.getProductName()%></td>
                                <td><%=pro.getSupplierID()%></td>
                                <td><%=pro.getQuantityPerUnit()%></td>
                                <td><%=pro.getUnitPrice()%></td>
                                <td><a href="CartURL?service=addCart&pid=<%=pro.getProductID()%>&cid=<%=pro.getCategoryID()%>">add to cart</a></td>
                            </tr>
                        <%}
                        }%>
                        </tbody>
                    </table>

                    
                </div>
            </div>

        </form>



    </body>
</html>
