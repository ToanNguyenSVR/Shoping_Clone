<%-- 
    Document   : history
    Created on : Jun 25, 2021, 5:29:51 PM
    Author     : toann
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">

        <title>Cart Page - Ustora Demo</title>

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

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>

        <div class="header-area">
            <div class="container">
                <div class="row">
                    <div class="col-md-8">
                        <div class="user-menu">

                            <c:set var="user" value="${sessionScope.USER}"/>
                            <c:set var="admin" value="${sessionScope.USERADMIN}"/>
                            <c:if test="${empty user }">
                                <ul>
                                    <li><a href=""><i class="fa fa-user"></i> My Account</a></li>
                                    <li><a href="cart.jsp"><i class="fa fa-user"></i>My Cart</a></li>
                                    <li><a href="login.jsp"><i class="fa fa-user"></i> Login</a></li>
                                </ul>
                            </c:if> 
                            <c:if test="${not empty user}">
                                <ul>
                                    <li><a href=""><i class="fa fa-user"></i>Wellcome  : ${user.userName}</a></li>
                                    <li><a href="cart.jsp"><i class="fa fa-user"></i>My Cart</a></li>
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

        <div class="site-branding-area">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="logo">
                            <h1><a href="./"><img src="img/logo.png"></a></h1>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="shopping-item">
                            <a href="cart.html">Cart - <span class="cart-amunt">$800</span> <i class="fa fa-shopping-cart"></i> <span class="product-count">5</span></a>
                        </div>
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
                        <ul class="nav navbar-nav">
                            <li><a href="home.jsp">Home</a></li>


                            <li ><a href="cart.jsp">Cart</a></li>
                            <li><a href="checkout.jsp">Checkout</a></li>
                            <li class="active" ><a href="history.jsp">History Order</a></li>
                                <c:if test="${ empty user}">
                                    <c:redirect url="login.html"/>
                                </c:if>




                        </ul>
                    </div>  
                </div>
            </div>
        </div> <!-- End mainmenu area -->

        <div class="product-big-title-area">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="product-bit-title text-center">
                            <h2>Shopping Cart</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- End Page title area -->


        <div class="single-product-area">
            <div class="zigzag-bottom"></div>
            <div class="container">
                <div class="row">

                    <c:set var="HistoryData" value="${sessionScope.HISTORYDATA}"/>

                    <c:if test="${not empty HistoryData}">
                        <div class="col-md-8">
                            <div class="single-sidebar">
                                <h2 class="sidebar-title">Search Products</h2>
                                <h4>${requestScope.MESSES}</h4>
                                <form action="DispatcherServlet">
                                    <input type="text" placeholder="Search History Order ..." name="txtsearchHistory" value="${param.txtsearchHistory}">
                                    <input type="submit" value="search" name="btnAction">
                                </form>
                            </div>
                            <div class="product-content-right">
                                <div class="woocommerce">
                                    <table cellspacing="0" class="shop_table cart">
                                        <thead>
                                            <tr>
                                                <th class="product-remove">No;</th>
                                                <th class="product-thumbnail">Order ID ;</th>
                                                <th class="product-name">User ID</th>
                                                <th class="product-date">Date</th>
                                                <th class="product-Product">All Product</th>

                                                <th class="product-price">Total Price</th>
                                                <th class="product-address">Address</th>
                                                <th class="product-note">Note</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:set var="pros" value="${sessionScope.HISTORYPRODUCT}"/>
                                            <c:if test="${not empty HistoryData}">
                                                <c:forEach var="item" items="${HistoryData}" varStatus="count">
                                                    <tr class="cart_item">

                                                        <td class="product-remove">
                                                            ${count.count}                                    
                                                        </td>

                                                        <td class="product-thumbnail">
                                                            <span>${item.orderID}</span> 
                                                        </td>

                                                        <td class="product-name">
                                                            <span>${item.userID}</span> 
                                                        </td>

                                                        <td class="product-price">
                                                            <span >${item.dateCreate}</span> 

                                                        </td>

                                                        <td >
                                                            <c:if test="${not empty pros}">
                                                                <c:forEach var="pro" items="${pros}" >


                                                                    <c:forEach var="pro_value" items="${pro}">

                                                                        <c:if test="${pro_value.orderID == item.orderID}">

                                                                            <table cellspacing="0" class="shop_table cart">
                                                                                <thead>
                                                                                    <tr>

                                                                                        <th class="product-name">Product</th>
                                                                                        <th class="product-price">Price</th>
                                                                                        <th class="product-quantity">Quantity</th>
                                                                                        <th class="product-subtotal">Total</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <tr class="cart_item">
                                                                                        <td class="product-thumbnail">
                                                                                            <span>${pro_value.productID}</span> 
                                                                                        </td>
                                                                                        <td class="product-thumbnail">
                                                                                            <span>${pro_value.price}</span> 
                                                                                        </td>
                                                                                        <td class="product-thumbnail">
                                                                                            <span>${pro_value.quantity}</span> 
                                                                                        </td>
                                                                                        <td class="product-thumbnail">
                                                                                            <span>${pro_value.price*pro_value.quantity}</span> 
                                                                                        </td>


                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </c:forEach>
                                                            </c:if>
                                                        </td>
                                                        <td class="product-subtotal">
                                                            <span >${item.totalPrice}</span> 
                                                        </td>
                                                        <td class="product-subtotal">
                                                            <span >${item.address}</span> 
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>
                                        </tbody>
                                    </table>
                                </div>                        
                            </div>                    
                        </div>
                    </div>
                </c:if>
                <c:if test="${empty HistoryData}">
                    <div class="alert alert-info alert-dismissible " role="alert">
                        <strong>Please </strong> Buy Some One 
                    </div>
                    <c:url var="loadhistory" value="DispatcherServlet">
                        <c:param name="btnAction" value="loadHistory"/>
                        <c:param name="txtuserID" value="${user.userID}"/>

                    </c:url>
                    <c:redirect url="${loadhistory}" />
                </c:if>
            </div>
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
                                <li><a href="#">Order history</a></li>
                                <li><a href="#">Wishlist</a></li>
                                <li><a href="#">Vendor contact</a></li>
                                <li><a href="#">Front page</a></li>
                            </ul>                        
                        </div>
                    </div>

                    <div class="col-md-3 col-sm-6">
                        <div class="footer-menu">
                            <h2 class="footer-wid-title">Categories</h2>
                            <ul>
                                <li><a href="#">Mobile Phone</a></li>
                                <li><a href="#">Home accesseries</a></li>
                                <li><a href="#">LED TV</a></li>
                                <li><a href="#">Computer</a></li>
                                <li><a href="#">Gadets</a></li>
                            </ul>                        
                        </div>
                    </div>

                    <div class="col-md-3 col-sm-6">
                        <div class="footer-newsletter">
                            <h2 class="footer-wid-title">Newsletter</h2>
                            <p>Sign up to our newsletter and get exclusive deals you wont find anywhere else straight to your inbox!</p>
                            <div class="newsletter-form">
                                <form action="#">
                                    <input type="email" placeholder="Type your email">
                                    <input type="submit" value="Subscribe">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- End footer top area -->

        <div class="footer-bottom-area">
            <div class="container">
                <div class="row">
                    <div class="col-md-8">
                        <div class="copyright">
                            <p>&copy; 2015 uCommerce. All Rights Reserved. <a href="http://www.freshdesignweb.com" target="_blank">freshDesignweb.com</a></p>
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
        </div> <!-- End footer bottom area -->

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
