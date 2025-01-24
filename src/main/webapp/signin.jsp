<%--
  Created by IntelliJ IDEA.
  User: Shashi
  Date: 1/17/2025
  Time: 9:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log In - Online Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .login-container {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            align-items: center;
            justify-content: center;
            padding: 15px;
        }
        .left-section {
            width: 100%;
            padding: 20px;
        }
        .right-section {
            background-color: #f8f9fa;
            color: #343a40;
            padding: 20px;
            text-align: center;
            width: 100%;
            margin-bottom: 20px;
        }
        .illustration {
            max-width: 100%;
            height: auto;
        }
        .btn-social {
            width: 50px;
            height: 50px;
            display: inline-flex;
            justify-content: center;
            align-items: center;
            margin: 5px;
            border-radius: 50%;
            border: 1px solid #ddd;
            font-size: 20px;
        }
        @media (min-width: 768px) {
            .login-container {
                flex-direction: row;
            }
            .left-section, .right-section {
                width: 50%;
                margin-bottom: 0;
            }
        }
    </style>
</head>
<body>
<div class="container login-container">
    <div class="left-section">
        <h2>Log In to Your Account</h2>
        <form action="auth" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">User Name</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Log In</button>
        </form>
        <div class="mt-3 text-center">Or Log In With</div>
        <div class="text-center">
            <a href="#" class="btn-social"><i class="bi bi-google"></i></a>
            <a href="#" class="btn-social"><i class="bi bi-facebook"></i></a>
            <a href="#" class="btn-social"><i class="bi bi-instagram"></i></a>
            <a href="#" class="btn-social"><i class="bi bi-twitter"></i></a>
            <a href="#" class="btn-social"><i class="bi bi-linkedin"></i></a>
        </div>
        <div class="mt-3 text-center">
            Don't have an account? <a href="signup.jsp">Sign Up</a>
        </div>
    </div>
    <div class="right-section">
        <h1>Welcome Back to Our Online Store</h1>
        <p>Continue shopping with amazing deals!</p>
        <img src="path-to-your-image.jpg" alt="Shopping Illustration" class="illustration">
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
