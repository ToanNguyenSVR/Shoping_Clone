<%-- 
    Document   : checkout
    Created on : Jun 25, 2021, 8:29:41 PM
    Author     : toann
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Checkout Page - Ustora Demo</title>

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
        <c:set var="session" value="${sessionScope}"/>

        <c:if test="${empty session}">
            <c:redirect url="login.html"/>
        </c:if>
        <c:set var="user" value="${sessionScope.USER}"/>
        <c:if test="${empty user}">
            <c:redirect url="login.html"/>
        </c:if>
        <c:if test="${not empty user}">


            <div class="header-area">
                <div class="container">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="user-menu">
                                <ul>
                                    <li><a href=""><i class="fa fa-user"></i>Wellcome  : ${user.userName}</a></li>
                                    <li><a href="cart.html"><i class="fa fa-user"></i>My Cart</a></li>
                                    <li> 
                                        <form action="DispatcherServlet">
                                            <input type="submit" value="Logout" name="btnAction" />
                                        </form>
                                    </li> </ul>
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

                        <!-- End site branding area -->

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


                                            <li><a href="cart.jsp">Cart</a></li>
                                            <li class="active"><a href="checkout.jsp">Checkout</a></li>
                                            <li><a href="#">Category</a></li>
                                            <li><a href="#">Others</a></li>
                                            <li><a href="#">Contact</a></li>
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
                        </div>


                        <div class="single-product-area">
                            <div class="zigzag-bottom"></div>
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-4">
                                        <div class="single-sidebar">
                                            <h2 class="sidebar-title">Search Products</h2>
                                            <form action="">
                                                <input type="text" placeholder="Search products...">
                                                <input type="submit" value="Search">
                                            </form>
                                        </div>

                                        <div class="single-sidebar">
                                            <h2 class="sidebar-title">Products</h2>
                                            <div class="thubmnail-recent">
                                                <img src="img/product-thumb-1.jpg" class="recent-thumb" alt="">
                                                <h2><a href="single-product.html">Sony Smart TV - 2015</a></h2>
                                                <div class="product-sidebar-price">
                                                    <ins>$700.00</ins> <del>$100.00</del>
                                                </div>                             
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-md-8">
                                        <div class="product-content-right">
                                            <div class="woocommerce">   
                                                <div class="woocommerce-info">Have a Voucher? <a class="showcoupon" data-toggle="collapse" href="#coupon-collapse-wrap" aria-expanded="false" aria-controls="coupon-collapse-wrap">Click here to enter your code</a>
                                                </div>

                                                <form id="coupon-collapse-wrap" method="post" class="checkout_coupon collapse" action="DispatcherServlet">
                                                    <p class="form-row form-row-first">
                                                        <input type="text" value="" id="coupon_code" placeholder="Coupon code" class="input-text" name="coupon_code">
                                                    </p>

                                                    <p class="form-row form-row-last">
                                                        <input type="submit" value="Apply Coupon" name="apply_coupon" class="button">
                                                    </p>

                                                    <div class="clear"></div>
                                                </form>

                                                <form action="DispatcherServlet" class="checkout" method="post">

                                                    <div id="customer_details" class="col2-set">

                                                        <div class="col-2">
                                                            <div class="woocommerce-shipping-fields">
                                                                
                                                                <div class="shipping_address" style="display: block;">


                                                                    <p id="shipping_first_name_field" class="form-row form-row-first validate-required">
                                                                        <label class="" for="shipping_first_name">Full Name <abbr title="required" class="required">*</abbr>
                                                                        </label>
                                                                        <input type="text" value="" placeholder="" id="shipping_first_name" name="txtuserName" class="input-text ">
                                                                    </p>


                                                                    <div class="clear"></div>



                                                                    <p id="shipping_address_1_field" class="form-row form-row-wide address-field validate-required">
                                                                        <label class="" for="shipping_address_1">Address <abbr title="required" class="required">*</abbr>
                                                                        </label>
                                                                        <input type="text" value="" placeholder="Street address" id="shipping_address_1" name="txtaddress" class="input-text ">
                                                                    </p>

                                                                    <p id="shipping_postcode_field" class="form-row form-row-last address-field validate-required validate-postcode" data-o_class="form-row form-row-last address-field validate-required validate-postcode">
                                                                        <label class="" for="shipping_postcode">Postcode <abbr title="required" class="required">*</abbr>
                                                                        </label>
                                                                        <input type="text" value="" placeholder="Postcode / Zip" id="shipping_postcode" name="shipping_postcode" class="input-text ">
                                                                    </p>

                                                                    <div class="clear"></div>


                                                                </div>





                                                                <p id="order_comments_field" class="form-row notes">
                                                                    <label class="" for="order_comments">Order Notes</label>
                                                                    <textarea cols="5" rows="2" placeholder="Notes about your order, e.g. special notes for delivery." id="order_comments" class="input-text " name="order_comments"></textarea>
                                                                </p>


                                                            </div>

                                                        </div>

                                                    </div>

                                                    <h3 id="order_review_heading">Your order</h3>
                                                    <c:set var="product" value="${sessionScope.CART}"/>
                                                    <c:if test="${not empty product}">
                                                        <c:set var="total" value="0"/>
                                                        <div id="order_review" style="position: relative;">
                                                            <table class="shop_table">
                                                                <thead>
                                                                    <tr>
                                                                        <th class="product-name">Product</th>
                                                                        <th class="product-total">Total</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>

                                                                    <c:forEach var="item" items="${product.icons}">
                                                                        <tr class="cart_item">
                                                                            <td class="product-name">
                                                                                ${item.productTitle} <strong class="product-quantity">× ${item.productQuantity}</strong> </td>
                                                                            <td class="product-total">
                                                                                <span class="amount">${item.productQuantity * item.productPrice} VND</span> </td>
                                                                                <c:set var="total" value="${total + item.productQuantity * item.productPrice}"/>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </tbody>
                                                                <tfoot>

                                                                    <tr class="cart-subtotal">
                                                                        <th>Cart Subtotal</th>
                                                                        <td><span class="amount">${total} VND</span>
                                                                        </td>
                                                                    </tr>

                                                                    <tr class="shipping">
                                                                        <th>Shipping and Handling</th>
                                                                        <td>

                                                                            Free Shipping
                                                                            <input type="hidden" class="shipping_method" value="free_shipping" id="shipping_method_0" data-index="0" name="shipping_method[0]">
                                                                        </td>
                                                                    </tr>
                                                                    <tr class="discounting">
                                                                        <th>Discount</th>
                                                                        <td>
                                                                            <c:set var="percent" value="${sessionScope.DISCOUNT}"/>
                                                                            <c:if test="${not empty percent}">
                                                                                ${percent} %
                                                                            </c:if>
                                                                            <c:if test="${ empty percent}">
                                                                                0
                                                                            </c:if>
                                                                        </td>
                                                                    </tr>
                                                                    <tr class="shipping">
                                                                        <th>Money Discount</th>
                                                                        <td>
                                                                            <c:set var="percent" value="${sessionScope.DISCOUNT}"/>
                                                                            <c:if test="${not empty percent}">
                                                                                ${total*percent/100} 
                                                                            </c:if>
                                                                            <c:if test="${ empty percent}">
                                                                                0
                                                                            </c:if>
                                                                        </td>
                                                                    </tr>


                                                                    <tr class="order-total">
                                                                        <th>Order Total</th>
                                                                        <td><strong><span class="amount">${total - total*percent/100} VND</span></strong> </td>
                                                                    </tr>

                                                                </tfoot>
                                                            </table>


                                                            <div id="payment">
                                                                <ul class="payment_methods methods">
                                                                    <li class="payment_method_bacs">
                                                                        <input type="radio" data-order_button_text="" checked="checked" value="bacs" name="payment_method" class="input-radio" id="payment_method_bacs">
                                                                        <label for="payment_method_bacs">Direct Bank Transfer </label>
                                                                        <div class="payment_box payment_method_bacs">
                                                                            <p>Make your payment directly into our bank account. Please use your Order ID as the payment reference. Your order won’t be shipped until the funds have cleared in our account.</p>
                                                                        </div>
                                                                    </li>

                                                                </ul>

                                                                <div class="form-row place-order">
                                                                    <c:set var="voucher" value="${sessionScope.VOUCHER}"/>
                                                                    <c:if test="${not empty voucher}">
                                                                        <input type="hidden" name="txtvoucherID" value="${voucher.voucherID}" />
                                                                        <input type="hidden" name="discount" value="${percent}" />
                                                                    </c:if>
                                                                    <input type="submit" data-value="Place order" value="PAY" id="place_order" name="btnAction" class="button alt">


                                                                </div>

                                                                <div class="clear"></div>

                                                            </div>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${ empty product}">


                                                        <div class="alert alert-info alert-dismissible " role="alert">
                                                            <strong>Please </strong> Add Some thing in your cart 
                                                            <button>
                                                                <a href="home.jsp">Shoping</a>
                                                            </button>

                                                        </div>


                                                    </c:if>
                                                </form>

                                            </div>                       
                                        </div>                    
                                    </div>
                                </div>
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
                                                <li><a href="">My account</a></li>
                                                <li><a href="">Order history</a></li>
                                                <li><a href="">Wishlist</a></li>
                                                <li><a href="">Vendor contact</a></li>
                                                <li><a href="">Front page</a></li>
                                            </ul>                        
                                        </div>
                                    </div>

                                    <div class="col-md-3 col-sm-6">
                                        <div class="footer-menu">
                                            <h2 class="footer-wid-title">Categories</h2>
                                            <ul>
                                                <li><a href="">Mobile Phone</a></li>
                                                <li><a href="">Home accesseries</a></li>
                                                <li><a href="">LED TV</a></li>
                                                <li><a href="">Computer</a></li>
                                                <li><a href="">Gadets</a></li>
                                            </ul>                        
                                        </div>
                                    </div>

                                    <div class="col-md-3 col-sm-6">
                                        <div class="footer-newsletter">
                                            <h2 class="footer-wid-title">Newsletter</h2>
                                            <p>Sign up to our newsletter and get exclusive deals you wont find anywhere else straight to your inbox!</p>
                                            <div class="newsletter-form">
                                                <input type="email" placeholder="Type your email">
                                                <input type="submit" value="Subscribe">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
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
                    </c:if>
                    </body>
                    </html>
