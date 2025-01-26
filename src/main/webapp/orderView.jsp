<%@ page import="com.shashimadushan.ecomweb.dto.OrderDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order View</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .table-responsive {
            margin-top: 20px;
        }
        .table th, .table td {
            vertical-align: middle;
        }
        .btn {
            margin: 0 5px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <%@include file="adminNavbar.jsp"%>
    <h4 class="mb-4 text-center">Recent Orders</h4>

    <div class="table-responsive">
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
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
                        <form action="updateOrderStatus" method="post" style="display:inline;">
                            <input type="hidden" name="orderId" value="<%= order.getOrderId() %>">
                            <button type="submit" class="btn btn-warning btn-sm">Update</button>
                        </form>
                        <form action="deleteOrder" method="post" style="display:inline;">
                            <input type="hidden" name="orderId" value="<%= order.getOrderId() %>">
                            <button type="submit" class="btn btn-danger btn-sm">Delete</button>
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
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>