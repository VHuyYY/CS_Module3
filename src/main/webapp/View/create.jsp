<%@ taglib prefix="e" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: bipro
  Date: 5/12/2024
  Time: 7:36 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="Css/create.css">
    <title>Title</title>

</head>
<body>
<div class="overlay"></div>
<div class="overlay"></div>
<form method="post" action="http://localhost:8080/home?path=create">
    <div class="container">
        <ul>
            <li>
                <h1>Add Product</h1>
            </li>
            <li>
                <p>Id:</p>
                <label>
                    <input type="text" name="id" placeholder="ID" readonly>
                </label>
            </li>
            <li>
                <p>Name:</p>
                <label>
                    <input type="text" name="name" placeholder="Enter your name" required>
                    <p class="text-danger">${nameError}</p>
                </label>
            </li>
            <li>
                <p>Description:</p>
                <label>
                    <input type="text" name="description" placeholder="Enter your description"
                           value="${product.description}"
                           required>
                    <p class="text-danger">${descriptionError}</p>
                </label>
            </li>
            <li>
                <p>Price:</p>
                <label>
                    <input type="number" name="price" placeholder="Enter your price" required>
                    <p class="text-danger">${priceError}</p>
                </label>
            </li>
            <li>
                <p>Image:</p>
                <label>
                    <input type="text" name="image" placeholder="Enter your image" required>
                    <p class="text-danger">${imageError}</p>
                </label>
                <%--            <input type="file" name="image" accept="image/" value="${product.image}">--%>
            </li>
            <li>
                <p>Category</p>
                <select class="form-select" name="categoryMethod" id="categoryMethod"
                        aria-label="Default select example" onchange="handleSelectChange()">
                    <e:forEach items="${categories}" var="category">
                    <option value="${category.id}">${category.name}</option>
                    </e:forEach>
                    <input type="text" id="otherInput" style="display:none;" placeholder="Nhập vào">
            </li>
            <li>
                <div class="submit">
                    <button type="submit" class="button-submit">Submit</button>
                    <a href="add.jsp" class="button-cancel">Hủy</a>
                </div>
            </li>
        </ul>
    </div>
</form>
<script>
    function handleSelectChange() {
        var select = document.getElementById('categoryMethod');
        var input = document.getElementById('otherInput');

        if (select.value == "3") {
            input.style.display = "block"; // Hiển thị ô nhập liệu khi chọn "Khác"
        } else {
            input.style.display = "none"; // Ẩn ô nhập liệu nếu không chọn "Khác"
        }
    }
</script>
</body>
</html>
