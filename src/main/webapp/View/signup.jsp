<%--
  Created by IntelliJ IDEA.
  User: bipro
  Date: 6/12/2024
  Time: 12:32 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

    <link rel="stylesheet" href="/Css/login.css">
    <title>Title</title>
</head>
<body>
<div class="login-container">
    <h1>LOGIN</h1>
    <p>Please enter your login and password!</p>
    <form>
        <div class="mb-3">
            <input type="email" class="form-control" placeholder=User>
        </div>
        <div class="mb-3">
            <input type="password" class="form-control" placeholder="Password">
        </div>
        <div class="mb-3">
            <input type="password" class="form-control" placeholder="Create Password">
        </div>
        <div class="forgot-password mb-3">
            <a href="#">Forgot password?</a>
        </div>
        <button type="submit" class="btn btn-login">SIGN UP</button>
    </form>
    <div class="social-icons">
        <a href="#"><i class="fab fa-facebook-f"></i></a>
        <a href="#"><i class="fab fa-twitter"></i></a>
        <a href="#"><i class="fab fa-google"></i></a>
    </div>
<%--    <div class="signup mt-3">--%>
<%--        <p>Don't have an account? <a href="#">Sign Up</a></p>--%>
<%--    </div>--%>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
<!-- Pills content -->
</body>
</html>
