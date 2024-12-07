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
    <title>Title</title>
    <link rel="stylesheet" href="Css/edit.css">
</head>
<body>
<div class="overlay"></div>
<form method="post" action="http://localhost:8080/home?path=edit">
    <div class="container">
        <ul>
            <li>
                <h1>Edit Product</h1>
            </li>
            <li>
                <p>Id:</p>
                <label>
                    <input type="text" name="id" placeholder="ID" value="${product.id}" readonly>
                </label>
            </li>
            <li>
                <p>Name:</p>
                <label>
                    <input type="text" name="name" placeholder="Enter your name" value="${product.name}" required>
                    <p class="text-danger">${nameError}</p>
                </label>
            </li>
            <li>
                <p>Description:</p>
                <label>
                    <input type="text" name="description" placeholder="Enter your description"
                           value="${product.description}" required>
                    <p class="text-danger">${descriptionError}</p>
                </label>
            </li>
            <li>
                <p>Price:</p>
                <label>
                    <input type="number" name="price" placeholder="Enter your price" value="${product.price}" required>
                    <p class="text-danger">${priceError}</p>
                </label>
            </li>
            <li>
                <p>Image:</p>
                <label>
                    <input type="text" name="image" placeholder="Enter your image" value="${product.img}" required>
                    <p class="text-danger">${imageError}</p>
                </label>
                <%--            <input type="file" name="image" accept="image/" value="${product.image}">--%>
            </li>
            <li>
                <p>Category</p>
                <select class="form-select " name="categoryMethod" aria-label="Default select example">
                    <option value="1"${product.categoryId == 1 ? 'selected' : ''}>Nhẫn</option>
                    <option value="2"${product.categoryId == 2 ?'selected' : ''}>Dây Chuyền</option>
                </select>
            </li>
            <li>
                <div class="submit">
                    <button type="submit" class="button-submit">Submit</button>
                    <a href="home" class="button-cancel">Hủy</a>
                </div>
            </li>
        </ul>
    </div>
</form>

</body>
</html>
