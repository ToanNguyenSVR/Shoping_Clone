<%-- Document : home Created on : Jun 23, 2021, 7:14:01 PM Author : toann --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@page
    contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>

    <html lang="en">
        <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>Shop Page- Ustora Demo</title>

            <!-- Google Fonts -->
            <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
            <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
            <link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>

            <!-- Bootstrap -->
            <link rel="stylesheet" href="css/bootstrap.min.css">

            <!-- Font Awesome -->
            <link rel="stylesheet" href="css/font-awesome.min.css">

            <!-- Custom CSS -->
            <link rel="stylesheet" href="css/owl.carousel.css">
            <link rel="stylesheet" href="style.css">
            <link rel="stylesheet" href="css/responsive.css">

        </head>
        <body>

            <c:set var="session" value="${sessionScope}"/>
            <c:if test="${empty session}">
                <c:redirect url="DispatcherServlet"/>
            </c:if>
            <div class="header-area">
                <div class="container">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="user-menu">
                                <c:set var="user" value="${sessionScope.USER}"/>
                                <c:set var="admin" value="${sessionScope.USERADMIN}"/>
                                <c:if test="${empty user && empty admin}">
                                    <ul>
                                        <li><a href=""><i class="fa fa-user"></i> My Account</a></li>
                                        <li><a href="cart.html"><i class="fa fa-user"></i>My Cart</a></li>
                                        <li><a href="login.html"><i class="fa fa-user"></i> Login</a></li>
                                    </ul>
                                </c:if> 
                                <c:if test="${not empty user}">
                                    <ul>
                                        <li><a href=""><i class="fa fa-user"></i>Wellcome  : ${user.userName}</a></li>
                                        <li><a href="cart.html"><i class="fa fa-user"></i>My Cart</a></li>
                                        <li> <form action="DispatcherServlet">
                                                <input type="submit" value="Logout" name="btnAction" />
                                            </form>
                                        </li> 
                                    </ul>
                                </c:if>
                                <c:if test="${not empty admin}">
                                    <ul>
                                        <li><a href=""><i class="fa fa-user"></i>Wellcome Admin : ${admin.userName}</a></li>

                                        <li> <form action="DispatcherServlet">
                                                <input type="submit" value="Logout" name="btnAction" />
                                            </form>
                                        </li> 

                                    </ul>
                                </c:if>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="header-right">
                                <ul class="list-unstyled list-inline">
                                    <li class="dropdown dropdown-small">
                                        <a data-toggle="dropdown" data-hover="dropdown" class="dropdown-toggle" href="#"><span class="key">currency :</span><span class="value">USD </span><b class="caret"></b></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">USD</a></li>
                                            <li><a href="#">INR</a></li>
                                            <li><a href="#">GBP</a></li>
                                        </ul>
                                    </li>

                                    <li class="dropdown dropdown-small">
                                        <a data-toggle="dropdown" data-hover="dropdown" class="dropdown-toggle" href="#"><span class="key">language :</span><span class="value">English </span><b class="caret"></b></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="#">English</a></li>
                                            <li><a href="#">French</a></li>
                                            <li><a href="#">German</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- End header area -->

            <c:if test="${ not empty requestScope.SUCCESS_ORDER}">

                <div class="alert alert-success alert-dismissible " role="alert">
                    <strong>Success</strong>${SUCCESS_ORDER} 
                </div>

            </c:if>
            <div class="site-branding-area">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="logo">
                                <h1><a href="./"><img src="img/logo.jpg" ></a></h1>
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <div class="shopping-item">
                                <a href="cart.html">Cart - <span class="cart-amunt">$100</span> <i class="fa fa-shopping-cart"></i> <span class="product-count">5</span></a>
                            </div>
                            <c:if test="${ not empty requestScope.SUCCESS_ORDER}">

                                <div class="alert alert-success alert-dismissible " role="alert">
                                    <strong>Success</strong>${SUCCESS_ORDER} 
                                </div>

                            </c:if>
                        </div>
                    </div>
                </div>
            </div> <!-- End site branding area -->

            <div class="mainmenu-area">
                <div class="container">
                    <div class="row">

                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                        </div> 
                        <div class="navbar-collapse collapse">
                            <c:if test="${empty user && empty admin}">

                                <ul class="nav navbar-nav">
                                    <li class="active"><a href="home.jsp">Shop page</a></li>
                                    <li><a href="checkout.jsp">CheckOut</a></li>
                                    <li><a href="cart.jsp">Cart</a></li>
                                </ul>
                            </c:if>
                            <c:if test="${not empty user}">
                                <ul class="nav navbar-nav">
                                    <li class="active"><a href="home.jsp">Shop page</a></li>

                                    <li><a href="cart.jsp">Cart</a></li>
                                    <li><a href="history.jsp">History Order</a></li>
                                    <li><a href="checkout.jsp">CheckOut</a></li>

                                </ul>
                            </c:if>
                            <c:if test="${not empty admin}">
                                <ul class="nav navbar-nav">
                                    <li class="active"><a href="#"> HOME </a></li>
                                    <li ><a href="admin.jsp">Manager Product</a></li>
                                    <li><a href="admin.jsp">Manager Category</a></li>
                                    <!--<li><a href="#User">Manager User</a></li>-->
                                    <li><a href="admin.jsp"> Manager Order</a></li>
                                    <li><a href="CreateProduct.jsp"> Create </a></li>
                                </ul>
                            </c:if>
                        </div>  
                    </div>
                </div>
            </div> <!-- End mainmenu area -->

            <div class="product-big-title-area">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="product-bit-title text-center">
                                <h2>Shop</h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row ">

            </div>
            <div class="single-product-area">
                <div class="zigzag-bottom"></div>
                <div class="container">

                    <div class="row">
                        <div class="col-md-4">
                            <div class="single-sidebar">
                                <h2 class="sidebar-title">Search Products</h2>
                                <h4  >${requestScope.MESSES}</h4>
                                <form action="DispatcherServlet">
                                    <input type="text" placeholder="Search products..." name="txtsearch" value="${param.txtsearch}">
                                    <input type="submit" value="search" name="btnAction">
                                </form>
                            </div>
                            <form action="DispatcherServlet">
                                <div class="single-sidebar">
                                    <h2 class="sidebar-title">Filter Products</h2>

                                    <h3 class="sidebar-title">Category</h3>


                                    <c:set var="catergory" value="${sessionScope.CATEGORY}"/>
                                    <c:if test="${not empty catergory}">
                                        <c:forEach var="dto" items="${catergory}" >
                                            <input type="checkbox" name="cbCatergory" value="${dto.categoryID}" />
                                            ${dto.categoryName}
                                            </br>
                                        </c:forEach>
                                    </c:if>

                                    <h4>Chose the range price</h4>
                                    <div class="col-md-3" >

                                        <input type="text" placeholder="Min Price" name="priceMin" value="${param.priceMin}" />

                                    </div>

                                    <div class="col-md-3" >

                                        <input type="text" placeholder="Max Price" name="priceMax" value="${param.priceMax}" /> 
                                    </div>



                                </div>
                                <input type="hidden" name="txtsearch" value="${param.txtsearch}" />
                                <input type="submit" value="apply" name="btnAction" />
                            </form>

                        </div>
                        <div class="col-md-8">
                            <c:set var="listProduct" value="${sessionScope.PRODUCT}" />
                            <c:if test="${not empty listProduct}">
                                <c:forEach var="dto" items="${listProduct}" varStatus="count">
                                    <div class="col-md-3 col-sm-6" >
                                        <div class="single-shop-product" >
                                            <div class="product-upper">
                                                <img src="img/product-2.jpg" alt="">
                                            </div>
                                            <h2><a href=""></a>${dto.productTitle}</h2>
                                            <div class="product-carousel-price">
                                                <ins>${dto.productPrice}</ins> <del>${dto.productPrice + 1000}</del>
                                            </div> 
                                            <div class="product-option-shop">
                                                <form action="DispatcherServlet" method="POST">

                                                    <c:if test="${dto.productQuantity > 0 && empty admin }">
                                                        <a class="add_to_cart_button" >
                                                            <input type="submit" value="AddToCart" name="btnAction" />
                                                            <input type="hidden" name="txtproductID" value="${dto.productID}" />
                                                            <input type="hidden" name="txtproductTitle" value="${dto.productTitle}" />
                                                            <input type="hidden" name="txtproductPrice" value="${dto.productPrice}" />
                                                            <input type="hidden" name="txtQuantity" value="1" />
                                                            <input type="hidden"  name="priceMin" value="${param.priceMin}" />
                                                            <input type="hidden"  name="priceMax" value="${param.priceMax}" />
                                                            <input type="hidden" name="txtsearch" value="${param.txtsearch}">
                                                        </a>
                                                    </c:if>
                                                </form>

                                                <c:if test="${dto.productQuantity < 1 }">
                                                    <a class="add_to_cart_button " style="background-color: red" >
                                                        Sold Out
                                                    </a>

                                                </c:if>
                                            </div> 


                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>

                        </div>
                        <!--                        <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="product-pagination text-center">
                                                            <nav>
                                                                <ul class="pagination">
                                                                    <li>
                                                                        <a href="#" aria-label="Previous">
                                                                            <span aria-hidden="true">&laquo;</span>
                                                                        </a>
                                                                    </li>
                                                                    <li><a href="#">1</a></li>
                                                                    <li><a href="#">2</a></li>
                                                                    <li><a href="#">3</a></li>
                                                                    <li><a href="#">4</a></li>
                                                                    <li><a href="#">5</a></li>
                                                                    <li>
                                                                        <a href="#" aria-label="Next">
                                                                            <span aria-hidden="true">&raquo;</span>
                                                                        </a>
                                                                    </li>
                                                                </ul>
                                                            </nav>                        
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>-->
                    </div>


                    <div class="footer-top-area">
                        <div class="zigzag-bottom"></div>
                        <div class="container">
                            <div class="row">
                                <div class="col-md-3 col-sm-6">
                                    <div class="footer-about-us">
                                        <h2>u<span>Stora</span></h2>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Perferendis sunt id doloribus vero quam laborum quas alias dolores blanditiis iusto consequatur, modi aliquid eveniet eligendi iure eaque ipsam iste, pariatur omnis sint! Suscipit, debitis, quisquam. Laborum commodi veritatis magni at?</p>
                                        <div class="footer-social">
                                            <a href="#" target="_blank"><i class="fa fa-facebook"></i></a>
                                            <a href="#" target="_blank"><i class="fa fa-twitter"></i></a>
                                            <a href="#" target="_blank"><i class="fa fa-youtube"></i></a>
                                            <a href="#" target="_blank"><i class="fa fa-linkedin"></i></a>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-3 col-sm-6">
                                    <div class="footer-menu">
                                        <h2 class="footer-wid-title">User Navigation </h2>
                                        <ul>
                                            <li><a href="#">My account</a></li>
                                            <li><a href="">Order history</a></li>



                                        </ul>                        
                                    </div>
                                </div>




                                <div class="footer-bottom-area">
                                    <div class="container">
                                        <div class="row">
                                            <div class="col-md-8">
                                                <div class="copyright">
                                                    <p>&copy; 2021 ToanNguyenSVR. All Rights Reserved. <a href="#" ></a></p>
                                                </div>
                                            </div>

                                            <div class="col-md-4">
                                                <div class="footer-card-icon">
                                                    <i class="fa fa-cc-discover"></i>
                                                    <i class="fa fa-cc-mastercard"></i>
                                                    <i class="fa fa-cc-paypal"></i>
                                                    <i class="fa fa-cc-visa"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Latest jQuery form server -->
                                <script src="https://code.jquery.com/jquery.min.js"></script>

                                <!-- Bootstrap JS form CDN -->
                                <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

                                <!-- jQuery sticky menu -->
                                <script src="js/owl.carousel.min.js"></script>
                                <script src="js/jquery.sticky.js"></script>

                                <!-- jQuery easing -->
                                <script src="js/jquery.easing.1.3.min.js"></script>

                                <!-- Main Script -->
                                <script src="js/main.js"></script>
                                </body>
                                </html>
