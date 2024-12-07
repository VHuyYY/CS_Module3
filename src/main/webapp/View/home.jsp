<%@ taglib prefix="e" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: bipro
  Date: 3/12/2024
  Time: 8:14 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>

    <link rel="stylesheet" href="Css/home.css">
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
                    <li class="nav-item">
                        <a class="nav-link" href="http://localhost:8080/View/login.jsp">
                            <i class="fas fa-user">
                            </i>
                            Tài Khoản Của Tôi
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <i class="fas fa-shopping-bag">
                            </i>
                            Giỏ Hàng
                            <span class="badge">
         0
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
                    <input class="form-control" type="search" placeholder="Tìm kiếm nhanh" aria-label="Search">
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
<div class="title">
    <p>Danh Mục Sản Phẩm</p>
    <a href="http://localhost:8080/home?path=create">
        <button>Thêm Sản Phẩm Mới</button>
    </a>
</div>
<div class="main">
    <div class="main-left">
        <div class="row">
            <div class="col-4">
                <e:forEach var="item" items="${categories}">
                    <div class="list-group" id="list-tab" role="tablist">
                        <a class="list-group-item list-group-item-action ${tag == item.id ? "active":""}"
                           id=" list-home-list"
                           href="${pageContext.request.contextPath}/home?path=category&id=${item.id}"
                           role="tab" aria-controls="list-home">${item.name}</a>
                    </div>
                </e:forEach>
            </div>
        </div>
    </div>
    <div class="main-right">
        <div class="row">
            <e:forEach items="${products}" var="product" varStatus="loop">
                <div class="card " style="width: 15rem;  margin-left: 20px; margin-bottom: 20px">
                    <img src="${product.img}" class="card-img-top" alt="..." height="200px">
                    <div class="card-body">
                        <h5 class="card-title">${product.name}</h5>
                        <h6 class="card-title"> $ ${product.price}</h6>
                        <p class="card-text">${product.description}</p>
                        <a href="http://localhost:8080/home?path=edit&id=${product.id}" class="btn btn-primary">Edit</a>
                        <label for="delete-${loop.index}" class="btn btn-primary red">Xóa</label>
                        <input type="checkbox" id="delete-${loop.index}" class="confirm-checkbox"/>
                        <div class="confirm-modal">
                            <div class="modal-content">
                                <p>Bạn có chắc chắn muốn xóa?</p>
                                <a href="http://localhost:8080/home?path=delete&id=${product.id}"
                                   class="btn btn-danger">Xác nhận</a>
                                <label for="delete-${loop.index}" class="btn btn-secondary">Hủy</label>
                            </div>
                        </div>
                    </div>
                </div>

            </e:forEach>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>
