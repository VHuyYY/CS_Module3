<%--
  Created by IntelliJ IDEA.
  User: bipro
  Date: 7/12/2024
  Time: 7:19 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/Css/home.css">
    <title>Title</title>
</head>
<body>

<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-white">
        <div class="container-fluid">
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <i class="fas fa-users">
                            </i>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <i class="fas fa-map-marker-alt">
                            </i>
                            Cửa Hàng
                        </a>
                    </li>
                </ul>
                <span class="navbar-text">
      <i class="fas fa-headset">
      </i>
      0931 950 503
     </span>
                <a class="navbar-brand mx-auto" href="#">
                    <img alt="Company Logo" height="40"
                         src="https://storage.googleapis.com/a1aa/image/brrRCoM00mI3PZLMAyOK6RfBwzXGS0pNCoIcxmsxwrwfMN3TA.jpg"
                         width="40"/>
                </a>
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <c:if test="${sessionScope.account != null}">
                        <span>
                            Hello ${sessionScope.account.account}
                        </span>
                    </c:if>
                    <c:if test="${sessionScope.account != null}">
                        <a href="logout">Đăng Xuất</a>
                    </c:if>
                    <c:if test="${sessionScope.account == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/user?path=login">
                                <i class="fas fa-user">
                                </i>
                                Tài Khoản Của Tôi
                            </a>
                        </li>
                    </c:if>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <i class="fas fa-shopping-bag">
                            </i>
                            Giỏ Hàng
                            <span class="badge">
                                ${sessionScope.totalQuantity}
                            </span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="#">Trang Sức</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Trang Sức Cưới</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Đồng Hồ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Quà Tặng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Thương Hiệu</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Blog</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-danger" href="#">Khuyến Mãi</a>
                </li>
            </ul>
            <form class="d-flex">
                <div class="input-group">
                    <input type="hidden" value="search" name="path">
                    <input class="form-control" name="keyword" type="search" placeholder="Tìm kiếm nhanh"
                           aria-label="Search" value="${txt}">
                    <span class="input-group-text"><i class="fas fa-search"></i></span>
                </div>
            </form>
        </div>
    </div>
</nav>
<section>
    <div class="container-fluid">

        <img src="https://cdn.pnj.io/images/promo/231/tabsale-chung-t12-24-1972x640.jpg" class="w-100" alt="">

    </div>
</section>
<br>

<div class="container">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>STT</th>
            <th>Image</th>
            <th>Sản phẩm</th>
            <th>Giá</th>
            <th>Số lượng</th>
            <th>Tổng</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="total" value="0"/>
        <c:if test="${not empty sessionScope.cart}">
            <c:forEach var="item" items="${sessionScope.cart.items}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td><img src="${item.product.img}" width="300px" height="200px" alt=""></td>
                    <td>${item.product.name}</td>
                    <td>$${item.price}</td>
                    <td>${item.quantity}</td>
                    <td>${item.price * item.quantity}</td>
                    <c:set var="total" value="${total + (item.price*item.quantity)}"/>
                    <td>
                        <a href="${pageContext.request.contextPath}/order?remove=${item.product.id}"
                           class="btn btn-danger">Remove</a>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="4"></td>
                <td>Tổng tiền</td>
                <td>${total}</td>
            </tr>
        </c:if>
        <c:if test="${empty sessionScope.cart}">
            <tr>
                <td colspan="6">Giỏ hàng trống</td>
            </tr>
        </c:if>
        </tbody>
    </table>
    <div class="payment">
        <a href="${pageContext.request.contextPath}/products?path=payment">
            <button class="btn btn-primary">Thanh toán</button>
        </a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>
