<%-- 
    Document   : manager
    Created on : Jun 8, 2021, 2:55:35 PM
    Author     : toann
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MY_APP</title>
        <script type="text/javascript">
            function disableBack() {
                window.history.forward();
            }
            setTimeout("disableBack()", 0);
            window.onunload = function () {
                null
            };
        </script>
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
        <c:if test="${not empty session}">
            <c:set var="admin" value="${sessionScope.USERADMIN}"/>

            <c:if test="${empty admin}">
                <c:redirect url="login.html"/>
            </c:if>
            <c:if test="${not empty admin}">
                <font color ="red">Welcome, ${admin.userName}</font>

                <form action="DispatcherServlet">
                    <input type="submit" value="Logout" name="btnAction" />
                </form>


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

                                    <li ><a href="admin.jsp">Manager Product</a></li>
                                    <li><a href="admin.jsp">Manager Category</a></li>
                                    <!--<li><a href="#User">Manager User</a></li>-->
                                    <li><a href="#Order"> Manager Order</a></li>
                                    <li class="active"><a href="CreateProduct.jsp"> Create </a></li>

                                </ul>
                            </div>  
                        </div>
                    </div>
                </div>
                <!-- End mainmenu area -->
                <!--                <div class="container">-->
                <c:set var="errorCreate" value="${requestScope.ERROR_CREATE}"/>
                <c:if test="${ not empty errorCreate}">
                    <c:if test="${ not empty errorCreate.productCreateNullError}">
                        <div class="alert alert-warning alert-dismissible " role="alert">
                            <strong>Error</strong> You should input ${errorCreate.productCreateNullError} fields below.
                        </div>
                    </c:if>
                </c:if>
                <div class="col-md-8 header-area">
                    <div id="Product">
                        <h2>Create Product</h2>
                        <form action="DispatcherServlet" method="POST">
                            <c:if test="${ not empty requestScope.SUCCESS_PRODUCT}">

                                <div class="alert alert-success alert-dismissible " role="alert">
                                    <strong>Success</strong>${SUCCESS_PRODUCT} 
                                </div>

                            </c:if>
                            Product ID : <input type="text" name="txtproductID" value="" /> </br>
                            <c:if test="${ not empty errorCreate}">
                                <c:if test="${ not empty errorCreate.productDuplicate}">
                                    <div class="alert alert-warning alert-dismissible " role="alert">
                                        <strong>Error</strong>${errorCreate.productDuplicate} 
                                    </div>
                                </c:if>
                            </c:if>

                            Product Title  :<input type="text" name="txtproductTitle" value="" /> </br>
                            Product Description :<textarea name="txtproductDescription" rows="4" cols="50"></textarea> </br>
                            Category       :<c:set var="catergorylist" value="${sessionScope.CATEGORY}"/>
                            <c:if test="${not empty catergorylist}">
                                <select name="cbcategory" >
                                    <c:forEach var="category" items="${catergorylist}" >
                                        <option value="${category.categoryID}"
                                                <c:if test="${dto.catergoryID == category.categoryID}">
                                                    selected
                                                </c:if>
                                                >${category.categoryName}</option>

                                    </c:forEach>

                                </select></br>
                            </c:if>   
                            Product Author   :<input type="text" name="txtproductAuthor" value="" /> </br>

                            Product Price     :<input type="text" name="txtproductPrice" value="" />
                            </br>
                            Quantity              :<input type="number" name="quantity" value="1" min="1" max="1000" /></br>
                            <input type="submit" value="Create" name="btnAction" />
                        </form>
                    </div>     
                    <div id="Voucher">
                        <h2>Create Voucher</h2>
                        <c:set var="errorCreateVoucher" value="${requestScope.ERROR_CREATE_VOUCHER}"/>
                        <c:if test="${ not empty errorCreateVoucher}">
                            <c:if test="${ not empty errorCreateVoucher.voucherCreateNullError}">
                                <div class="alert alert-warning alert-dismissible " role="alert">
                                    <strong>Error</strong> You should input ${errorCreateVoucher.voucherCreateNullError} fields below.
                                </div>
                            </c:if>
                        </c:if>
                        <c:if test="${ not empty requestScope.SUCCESS_VOUCHER}">

                            <div class="alert alert-success alert-dismissible " role="alert">
                                <strong>Success</strong>${SUCCESS_VOUCHER} 
                            </div>

                        </c:if>
                        <form action="DispatcherServlet" method="POST">
                            Voucher ID  :<input type="text" name="txtVoucherID" value="" /> </br>
                            <c:if test="${ not empty errorCreateVoucher}">
                                <c:if test="${ not empty errorCreateVoucher.voucherDuplicate}">
                                    <div class="alert alert-warning alert-dismissible " role="alert">
                                        <strong>Error</strong>${errorCreateVoucher.voucherDuplicate} 
                                    </div>
                                </c:if>
                            </c:if>
                            CODE :<input type="text" name="CODE" value="" /></br>
                            <c:if test="${ not empty errorCreateVoucher}">
                                <c:if test="${ not empty errorCreateVoucher.CODEDuplicate}">
                                    <div class="alert alert-warning alert-dismissible " role="alert">
                                        <strong>Error</strong>${errorCreateVoucher.CODEDuplicate} 
                                    </div>
                                </c:if>
                            </c:if>


                            Percent : <input type="number" name="txtpercent" value="1" min="1" max="100" /></br>
                            <input type="submit" value="CreateVoucher" name="btnAction" />
                        </form>
                    </div>     
                </div>

            </c:if>   
        </c:if>
    </body>
</html>
