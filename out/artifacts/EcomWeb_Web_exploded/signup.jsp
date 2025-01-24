<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up - Online Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .signup-container {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            align-items: center;
            justify-content: center;
            padding: 15px;
        }
        .left-section {
            background-color: #f8f9fa;
            color: #343a40;
            padding: 20px;
            text-align: center;
            width: 100%;
            margin-bottom: 20px;
        }
        .right-section {
            width: 100%;
            padding: 20px;
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
            .signup-container {
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
<div class="container signup-container">
    <div class="left-section">
        <h1>Welcome to Our Online Store</h1>
        <p>Discover amazing products and deals!</p>
        <img src="path-to-your-image.jpg" alt="Shopping Illustration" class="illustration">
    </div>
    <div class="right-section">
        <h2>Create Your Account</h2>
        <form action="signupAction" method="post">
            <div class="mb-3">
                <label for="fullName" class="form-label">Full Name</label>
                <input type="text" class="form-control" id="fullName" name="fullName" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email Address</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="form-check mb-3">
                <input type="checkbox" class="form-check-input" id="terms" required>
                <label class="form-check-label" for="terms">I agree to the <a href="#">terms of service</a> and <a href="#">privacy policy</a></label>
            </div>
            <button type="submit" class="btn btn-primary w-100">Sign Up</button>
        </form>
        <div class="mt-3 text-center">Or Sign Up With</div>
        <div class="text-center">
            <a href="#" class="btn-social"><i class="bi bi-google"></i></a>
            <a href="#" class="btn-social"><i class="bi bi-facebook"></i></a>
            <a href="#" class="btn-social"><i class="bi bi-instagram"></i></a>
            <a href="#" class="btn-social"><i class="bi bi-twitter"></i></a>
            <a href="#" class="btn-social"><i class="bi bi-linkedin"></i></a>
        </div>
        <div class="mt-3 text-center">
            Already have an account? <a href="signin.jsp">Sign In</a>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>