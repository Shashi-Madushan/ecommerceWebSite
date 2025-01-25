<%@ page import="com.shashimadushan.ecomweb.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>My Account</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f9f9f9;
    }

    .container {
      max-width: 600px;
      margin: 50px auto;
      background: #fff;
      border-radius: 10px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
      padding: 20px;
    }

    .header {
      text-align: center;
      margin-bottom: 20px;
    }

    .header h1 {
      font-size: 24px;
      color: #333;
    }

    .user-info {
      margin: 20px 0;
    }

    .user-info div {
      display: flex;
      align-items: center;
      margin-bottom: 15px;
    }

    .user-info svg {
      width: 24px;
      height: 24px;
      margin-right: 10px;
      fill: #555;
    }

    .user-info span {
      font-size: 16px;
      color: #555;
    }

    .user-info input {
      flex: 1;
      padding: 8px;
      font-size: 16px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }

    .update-btn {
      display: block;
      width: 100%;
      padding: 10px;
      background-color: #007bff;
      color: #fff;
      text-align: center;
      font-size: 16px;
      font-weight: bold;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      transition: background-color 0.3s;
    }

    .update-btn:hover {
      background-color: #0056b3;
    }

    .alert {
      margin-top: 20px;
      padding: 10px;
      border-radius: 5px;
      text-align: center;
    }

    .alert-success {
      background-color: #d4edda;
      color: #155724;
    }

    .alert-error {
      background-color: #f8d7da;
      color: #721c24;
    }
  </style>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container">
  <div class="header">
    <h1>My Account</h1>
  </div>
<%
  UserDTO user = (UserDTO) session.getAttribute("user");
  if (user == null) {
      user = new UserDTO(); // Fallback in case the session attribute is not set
  }

  String status = request.getParameter("status");
  if ("success".equals(status)) {
%>
    <div class="alert alert-success">Your data has been updated successfully!</div>
<%
  } else if ("error".equals(status)) {
%>
    <div class="alert alert-error">There was an error updating your data. Please try again.</div>
<%
  }
%>
  <form id="updateForm" action="updateuser" method="post" onsubmit="return confirmUpdate()">
    <div class="user-info">
      <div>
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/></svg>
        <input type="text" name="username" value="<%= user.getUsername() %>" placeholder="Username">
      </div>

      <div>
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-10-10-10S17.52 2 12 2zm-1 17h-2v-6h2v6zm1-8.18c-.59 0-1.17-.26-1.59-.68-.42-.42-.68-1-.68-1.59s.26-1.17.68-1.59c.42-.42 1-.68 1.59-.68s1.17.26 1.59.68c.42.42.68 1 .68 1.59s-.26 1.17-.68 1.59c-.42.42-1 .68-1.59.68z"/></svg>
        <input type="email" name="email" value="<%= user.getEmail() %>" placeholder="Email">
      </div>

      <div>
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-10-10-10S17.52 2 12 2zm-1 17h-2v-6h2v6zm0-8h-2V7h2v4z"/></svg>
        <input type="password" name="password" placeholder="New Password">
      </div>

      <div>
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-10-10-10S17.52 2 12 2zm-1 17h-2v-6h2v6zm0-8h-2V7h2v4z"/></svg>
        <span>Status: <%= user.getStatus() ? "Active" : "Inactive" %></span>
      </div>
    </div>

    <button type="submit" class="update-btn">Update Data</button>
  </form>
</div>

<script>
  function confirmUpdate() {
    return confirm("Are you sure you want to update your data?");
  }
</script>
</body>
</html>