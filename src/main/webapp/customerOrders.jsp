<%@ page import="com.shashimadushan.ecomweb.dto.OrderDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Order View</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      font-family: Arial, sans-serif;
    }
    .navbar {
      margin: 0;
      width: 100%;
      padding: 10px 20px;
      border-radius: 0;
      z-index: 1000;
      background-color: #34495e;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      transition: transform 0.3s ease, box-shadow 0.3s ease;
    }
    .container {
      padding: 15px;
    }
    .order-table {
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      border-radius: 8px;
      overflow: hidden;
    }
    .order-table th, .order-table td {
      vertical-align: middle;
    }
    .order-table th {
      background-color: #343a40;
      color: #fff;
    }
    .order-table tbody tr:hover {
      background-color: #f1f1f1;
    }
    .btn-custom {
      margin-right: 5px;
    }
  </style>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container">
  <h4 class="mb-4 text-center">Recent Orders</h4>

  <div class="table-responsive">
    <table class="table table-bordered table-striped order-table">
      <thead>
      <tr>
        <th>Order ID</th>
        <th>Customer Name</th>
        <th>Date</th>
        <th>Total Price</th>
        <th>Status</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <%
        List<OrderDTO> orders = (List<OrderDTO>) request.getAttribute("orders");
        if (orders != null && !orders.isEmpty()) {
          for (OrderDTO order : orders) {
      %>
      <tr>
        <td><%= order.getOrderId() %></td>
        <td><%= order.getUser().getUsername() %></td>
        <td><%= order.getCreatedAt() %></td>
        <td><%= order.getTotalAmount() %></td>
        <td><%= order.getStatus() %></td>
        <td>
          <form action="deleteOrder" method="post" style="display:none;">
            <input type="hidden" name="orderId" value="<%= order.getOrderId() %>">
            <button type="submit" class="btn btn-danger btn-sm btn-custom">Delete</button>
          </form>
        </td>
      </tr>
      <%
          }
        }
      %>
      </tbody>
    </table>
  </div>
</div>
<script src="js/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
