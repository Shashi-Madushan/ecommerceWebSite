<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Admin Login</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .login-container {
            margin-top: 100px;
            max-width: 400px;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .password-toggle {
            cursor: pointer;
            position: absolute;
            right: 10px;
            top: 38px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="login-container mx-auto">
        <h2 class="text-center">Admin Login</h2>
        <form id="loginForm"  method="POST">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="form-group position-relative">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
                <span class="password-toggle" onclick="togglePassword()">👁️</span>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Login</button>
            <div class="text-center mt-3">
                <a href="forgotPassword.jsp">Forgot Password?</a>
            </div>
        </form>
    </div>
</div>
<script src="js/jquery-3.7.1.min.js"></script>
<!-- JavaScript for form validation and password toggle -->
<script>
    document.getElementById('loginForm').addEventListener('submit', function(event) {

        var username = document.getElementById('username').value;
        var password = document.getElementById('password').value;
        console.log(username, password);
        if (username === '' || password === '') {
            alert('Please fill in both fields.');
        }else{
            document.getElementById('loginForm').action = `${window.location.origin}/adminLogin`;
        }
    });

    function togglePassword() {
        var passwordField = document.getElementById('password');
        var passwordToggle = document.querySelector('.password-toggle');
        if (passwordField.type === 'password') {
            passwordField.type = 'text';
            passwordToggle.textContent = '🙈';
        } else {
            passwordField.type = 'password';
            passwordToggle.textContent = '👁️';
        }
    }
</script>

<!-- Bootstrap JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>