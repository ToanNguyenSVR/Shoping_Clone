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
                                    <li><a href="home.jsp"> HOME </a></li>
                                    <li class="active"><a href="#Product">Manager Product</a></li>
                                    <li><a href="#Category">Manager Category</a></li>
                                    <!--<li><a href="#User">Manager User</a></li>-->
                                    <li><a href="#Order"> Manager Order</a></li>
                                    <li><a href="CreateProduct.jsp"> Create </a></li>


                                </ul>
                            </div>  
                        </div>
                    </div>
                </div> <!-- End mainmenu area -->
                <!--                <div class="container">-->
                <c:set var="errorUpdate" value="${requestScope.ERROR_UPDATE}"/>
                <c:if test="${ not empty errorUpdate}">
                    <div class="alert alert-danger alert-dismissible " role="alert">
                        <strong>Error</strong> You should input ${errorUpdate.productUpdateNullError} fields below.
                    </div>
                </c:if>
                <c:if test="${ not empty requestScope.SUCCESS_UPDATE}">

                    <div class="alert alert-success alert-dismissible " role="alert">
                        <strong>Success</strong>${SUCCESS_UPDATE} 
                    </div>

                </c:if>
                <c:if test="${ not empty requestScope.SUCCESS_DELETE}">

                    <div class="alert alert-success alert-dismissible " role="alert">
                        <strong>Success</strong>${SUCCESS_DELETE} 
                    </div>

                </c:if>
                <div class="col-md-8 header-area">     
                    <div id="Product">
                        <c:set var="listProduct" value="${sessionScope.PRODUCT}" />
                        <c:if test="${not empty listProduct}">
                            <table border="1">

                                <thead>
                                    <tr>
                                        <th>NO</th>
                                        <th>Product ID</th>
                                        <th>Product Title</th>
                                        <th>Product Imager</th>
                                        <th>Product Description</th>
                                        <th>Product Author</th>
                                        <th>Category ID</th>
                                        <th>Product Status</th>
                                        <th>Product Quantity</th>
                                        <th>Product Price</th>
                                        <th>Update</th>
                                        <th>Delete</th>
                                    </tr>
                                </thead>
                                <tbody>



                                    <c:forEach var="dto" items="${listProduct}" varStatus="count">
                                        <!--<form action="UpdateProductServlet" method="POST" enctype="multipart/form-data">-->
                                    <form action="DispatcherServlet" method="POST">

                                        <tr>
                                            <td>${count.count}</td>
                                            <td>

                                                <input type="text" name="txtproductID" value="${dto.productID}" readonly="readonly" />

                                            </td>


                                            <td><input type="text" name="txtproductTitle" value="${dto.productTitle}" />
                                            </td>
                                            <td>
                                                <img src="data:image/jpg;base64,${dto.productImg}" width="70" height="70"/>
                                                <input type="file" name="changeImage" value="" />
                                            </td>
                                            <td>
                                                <textarea name="txtproductDescription"  rows="4" cols="50">${dto.productDescription}</textarea>

                                            </td>
                                            <td><input type="text" name="txtproductAuthor" value="${dto.productAuthor}"  />
                                            </td>
                                            <td>
                                                <c:set var="catergorylist" value="${sessionScope.CATEGORY}"/>
                                                <c:if test="${not empty catergorylist}">
                                                    <select name="cbcategory" >
                                                        <c:forEach var="category" items="${catergorylist}" >
                                                            <option value="${category.categoryID}"
                                                                    <c:if test="${dto.catergoryID == category.categoryID}">
                                                                        selected
                                                                    </c:if>
                                                                    >${category.categoryName}</option>

                                                        </c:forEach>

                                                    </select>
                                                </c:if>
                                            </td>
                                            <td>
                                                <select name="cbstatus" >

                                                    <option value="ACTIVE"
                                                            <c:if test="${dto.productStatus == "ACTIVE"}" >
                                                                selected
                                                            </c:if>
                                                            >ACTIVE</option>

                                                    <option value="INACTIVE"
                                                            <c:if test="${dto.productStatus == "INACTIVE"}">
                                                                selected
                                                            </c:if>
                                                            >INACTIVE</option>

                                                </select>

                                            </td>

                                            <td>
                                                <input type="number" id="tentacles" name="quantity" value="${dto.productQuantity}"
                                                       min="0" max="100">


                                            </td>
                                            <td><input type="text" name="txtproductPrice" value="${dto.productPrice}"   />
                                            </td>
                                            <td>

                                                <input type="submit" value="Update" name="btnAction" />
                                            </td>
                                            <!--</form>-->
                                    </form>
                                    <td>
                                        <c:url var="deleteLink" value="DispatcherServlet">
                                            <c:param name="btnAction" value="deleteProduct"/>
                                            <c:param name="txtproductID" value="${dto.productID}"/>

                                        </c:url>

                                        <button>
                                            <a href="${deleteLink}">Delete</a>
                                        </button>
                                    </td>
                                    </tr>

                                </c:forEach>
                                </tbody>

                            </table>
                        </c:if>
                        <c:if test="${ empty listProduct}">
                            <h4>Don't Have any Product in Shop </h4>
                            <h4>Please Create new  </h4>
                        </c:if>



                    </div>
                    <div id="Category">
                        <c:set var="catergory" value="${sessionScope.CATEGORY}"/>
                        <c:if test="${not empty catergory}">
                            <table border="1">

                                <thead>
                                    <tr>
                                        <th>NO</th>
                                        <th>Category ID</th>
                                        <th>Category Name</th>
                                        <th>Create Date</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="dto" items="${catergory}" varStatus="count">
                                        <tr>
                                            <td>
                                                ${count.count}
                                            </td>

                                            <td>
                                                ${dto.categoryID}
                                            </td>
                                            <td>
                                                ${dto.categoryName}

                                            </td>
                                            <td>
                                                ${dto.createDate}
                                            </td>

                                        </tr>
                                    </c:forEach>
                                </tbody>

                            </table>
                        </c:if>
                        <c:if test="${ empty listProduct}">
                            <h4>Don't Have any Product in Shop </h4>
                            <h4>Please Create new  </h4>
                        </c:if>



                    </div>


                </div>
            </c:if>   
        </c:if>
    </body>
</html>
