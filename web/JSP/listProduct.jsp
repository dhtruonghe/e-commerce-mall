<%-- 
    Document   : listProduct.jsp
    Created on : Oct 4, 2024, 11:02:05 AM
    Author     : haitr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Product,entity.Customers,entity.Categories" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <title>JSP Page</title>
        <style>
            .info{
                display: inline;
                margin-left: 30%;
                color: black;
            }
            body{
                overflow-x: hidden;
            }
            .container {
                position: relative;
                width: 50%;
                top: 0;
                right:  30%;
                height: 50%;
            }

            .image {
                display: block;
                width: 50%;
                height: auto;
            }

            .overlay {
                position: absolute;
                bottom: 0;
                left: 0;
                right: 0;
                background-color: #008CBA;
                overflow: hidden;
                width: 100%;
                height: 0;
                transition: .5s ease;
            }

            .container:hover .overlay {
                height: 100%;
            }

            .text {
                color: white;
                font-size: 20px;
                position: absolute;
                top: 50%;
                left: 50%;
                -webkit-transform: translate(-50%, -50%);
                -ms-transform: translate(-50%, -50%);
                transform: translate(-50%, -50%);
                text-align: center;
            }
            /*            .cate-item{
                            max-width: 20%;
                        }*/
            .cate-item img{
                width: 15vw;
            }

            .cate{
                padding-top: 15px;
                background-color: #e9e9e9;
            }
            a{
                text-decoration: none;
                color: black;
            }
            .col-10{
                margin-top: 3%;
            }
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                padding: 10px;
            }
            colgroup col:nth-child(1) {
                padding-right: 20px;
            }
            colgroup col:nth-child(2) {
                padding-left: 20px;
            }
            .topnav {
                overflow: hidden;
                background-color: #e9e9e9;
            }

            .topnav a {
                float: left;
                display: block;
                color: black;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
                font-size: 17px;
            }

            .topnav a:hover {
                background-color: #ddd;
                color: black;
            }

            .topnav a.active {
                background-color: #2196F3;
                color: white;
            }

            .topnav .search-container {
                float: right;
            }

            .topnav input[type=text] {
                padding: 6px;
                margin-top: 8px;
                font-size: 17px;
                border: none;
            }

            .topnav .search-container button {
                float: right;
                padding: 6px 10px;
                margin-top: 8px;
                margin-right: 16px;
                background: #ddd;
                font-size: 17px;
                border: none;
                cursor: pointer;
            }

            .topnav .search-container button:hover {
                background: #ccc;
            }

            @media screen and (max-width: 600px) {
                .topnav .search-container {
                    float: none;
                }
                .topnav a, .topnav input[type=text], .topnav .search-container button {
                    float: none;
                    display: block;
                    text-align: left;
                    width: 100%;
                    margin: 0;
                    padding: 14px;
                }
                .topnav input[type=text] {
                    border: 1px solid #ccc;
                }
            }
            .card{
                display: inline-block;
                margin-top: 5vw;
                margin-left: 5%;
            }
            .search-container{
                margin-right: 5%;
            }

            .login{
                display: inline-block;
                margin-left: 30%;
                color: white;
            }
            
        </style>
    </head>
    <body>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-aGFCz43lBchvnh6sEX8HwoIgHjljVqZev44IlFwD3gGtsjIoP04FfYTLVog8E9pF" crossorigin="anonymous">

        </script>
        <% Customers cus = (Customers)session.getAttribute("customer");
        Vector<Categories> vectorCate = (Vector<Categories>)
                    request.getAttribute("vectorCate");%>

        <% Vector<Product> vector = (Vector<Product>)request.getAttribute("dataProduct");
        String title =(String)request.getAttribute("titleTable");
        %>
        <form action="ProductURL" method="get">
            <!--            <p>Product Name <input type="text" name="c" id="">
                            <input type="hidden" name="service" value="listAll">
                            <input type="submit" value="Search" name="submit">
                            <input type="reset" value="Clear">
                        </p>-->
            <nav>
                <div class="container">
                    <%if(cus==null){%>
                    <p class="login"><a href="IndexURL?service=login">login</a></p>
                    <p class="login"><a href="CustomersURL?service=Register">Register</a></p>
                    <%} else{%> 

                    <p class="info">
                        WelCome:
                        <%if(cus!=null){%>
                        <%=cus.getCustomerID()%>
                        <%}%>
                        <a class="info" href="CartURL?service=cart">Show Cart</a>
                    </p>

                    <p><a class="info" href="IndexURL?service=logout">logout</a></p>
                    <%}%>
                </div>

                <div class="topnav">
                    <a href="IndexURL">Trang chủ</a>
                    <a class="active" href="ProductURL">Sản Phẩm</a>
                    <div class="search-container">
                        <form action="ProductURL" method="get">
                            <input type="hidden" name="service" value="listAll">
                            <input type="text" placeholder="Search Product Name" name="pname">
                            <button type="submit" value="Search" name="submit"><i class="fa fa-search"></i></button>

                    </div>
                </div>

            </nav> 

        </form>
        <!--        <p><a href="ProductURL?service=insertProduct">insert products</a></p>
                <p align="left"><a href="CartURL?service=showCart">show Cart</a></p>
                <p align="left"><a href="IndexURL">index</a></p>-->


        <div class="row">
            <div class="col-2">
                <div id="simple-list-example" class="d-flex flex-column gap-2 simple-list-example-scrollspy text-center">
                    <div class="cate"><p>Danh Mục</p></div>
                    <% for(Categories cate : vectorCate) {%>
                    <a class="p-1 rounded" href="ProductURL?service=searchCateID&cid=<%=cate.getCategoryID()%>">
                        <div class="container cate-item">
                            <img src="<%=cate.getPicture()%>" alt="">
                            <div class="overlay">
                                <div class="text"><%=cate.getCategoryName()%></div>
                            </div>
                        </div>
                    </a>
                    <% }%>


                </div>
            </div>
            <div class="col-10">
                <div data-bs-spy="scroll" data-bs-target="#simple-list-example" data-bs-offset="0" data-bs-smooth-scroll="true" class="scrollspy-example" tabindex="0">
                    <table border=0>
                        <caption><%=title%></caption>
                        <tr>
                            <th>ProductID</th>
                            <th>ProductName</th>
                            <th>SupplierID</th>
                            <th>CategoryID</th>
                            <th>QuantityPerUnit</th>
                            <th>UnitPrice</th>
                            <th>UnitsInStock</th>
                            <th>add2Cart</th>
                        </tr>
                        <%for(Product product : vector){%>
                        <tr>
                            <td><%=product.getProductID()%></td>
                            <td><%=product.getProductName()%></td>
                            <td><%=product.getSupplierID()%></td>
                            <td><%=product.getCategoryID()%></td>
                            <td><%=product.getQuantityPerUnit()%></td>
                            <td><%=product.getUnitPrice()%></td>
                            <td><%=product.getUnitsInStock()%></td>
                            <td><a href="CartURL?service=addCart&pid=<%=product.getProductID()%>&cid=<%=product.getCategoryID()%>">addCart</a></td>
                        </tr>
                        <%}%>
                </div>
            </div>
        </div>



    </body>
</html>
