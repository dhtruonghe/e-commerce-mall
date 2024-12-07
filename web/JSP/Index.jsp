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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <style>
           
            
            a{
                text-decoration: none;
                color: black;
            }
            body {
                background-color: rgb(204, 202, 202);
                overflow-x: hidden;
            }

            .content-details {
                margin-top: 1%;
                background-color: white;
                padding: 1%;
            }

            .paragraph {
                font-size: 1.5vw;
                margin-left: 10vw;
                margin-top: 10vw ;
                text-align: left;
            }

            .info {
                display: inline;
            }

            #img-content{
                width: 90%;
            }

            .banner{
                margin-left: 10%;
            }

            #image-banner{

                width: 80%;
                height: 20vw;
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
        <% 
            Vector<Categories> vectorCate = (Vector<Categories>)
                    request.getAttribute("vectorCate");
            Vector<Product> vectorProducts = (Vector<Product>)
                    request.getAttribute("vectorProducts");
        %>
        <% Customers cus = (Customers)session.getAttribute("customer");%>

        <form action="IndexURL" method="GET">
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
                        <a href="CartURL?service=cart">Show Cart</a>
                    </p>

                    <p><a href="IndexURL?service=logout">logout</a></p>
                    <%}%>
                </div>
                
                <div class="topnav">
                    <a class="active" href="IndexURL">Trang chủ</a>
                    <a href="ProductURL">Sản Phẩm</a>
                    <div class="search-container">
                        <form action="ProductURL" method="get">
                            <input type="hidden" name="service" value="listAll">
                            <input type="text" placeholder="Search Product Name" name="pname">
                            <button type="submit" value="Search" name="submit"><i class="fa fa-search"></i></button>
                       
                    </div>
                </div>
                
            </nav> 
               

            <div class="content">
                
                <div class="row g-0 text-center content-details">
                    <div class="col-sm-6 col-md-9">
                        <p class="paragraph">Chào mừng Giáng Sinh an lành 🎄🎁<br>

                            Mùa lễ hội đã đến! Đừng bỏ lỡ siêu khuyến mãi Noel chỉ diễn ra một lần trong năm:<br>
                            ✨ Giảm giá lên đến 50% cho hàng loạt sản phẩm hot.<br>
                            ✨ Mua 1 tặng 1 cho các mặt hàng đặc biệt.<br>
                            🎅 Nhanh tay – Ưu đãi có hạn đến 24h đêm Noel! 🎁<br>

                            Hãy Mua đây và mang yêu thương đến cho gia đình, bạn bè mùa lễ này!</p>
                    </div>
                    <div class="col-6 col-md-3">
                        <img src="./image/noen.jpg" id="img-content">
                    </div>
                </div>
                <div class="row g-0 text-center content-details">
                    <div class="col-sm-6 col-md-3">
                        <img src="./image/Dori.jpg" id="img-content">
                    </div>
                    <div class="col-6 col-md-9">
                        <div class="card" style="width: 18rem;">
                            <img src="data:image/jpeg;base64,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" class="card-img-top" alt="">
                            <div class="card-body">
                                <h5 class="card-title">Tofu</h5>
                                <p class="card-text" >23.25$ </p>
                                <a href="ProductURL?service=listAll&pname=tofu&submit=Search" class="btn btn-primary">Mua ngay</a>
                            </div>
                        </div>
                        <div class="card" style="width: 18rem;">
                            <img src="https://maludesign.vn/wp-content/uploads/2023/04/p-1-90875506-fanta.webp" class="card-img-top" alt="">
                            <div class="card-body">
                                <h5 class="card-title">Guaraná Fantástica</h5>
                                <p class="card-text">4.5$</p>
                                <a href="ProductURL?service=listAll&pname=Guaraná Fantástica&submit=Search" class="btn btn-primary">Mua ngay</a>
                            </div>
                        </div>
                        <div class="card" style="width: 18rem;">
                            <img src="https://www.wog.ch/nas/cover_xl/ko/ko_9783039021383.jpg" class="card-img-top" alt="">
                            <div class="card-body">
                                <h5 class="card-title">Schoggi Schokolade</h5>
                                <p class="card-text">43.9$</p>
                                <a href="ProductURL?service=listAll&pname=Schoggi+Schokolade&submit=Search" class="btn btn-primary">Mua ngay</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </form>



    </body>
</html>
