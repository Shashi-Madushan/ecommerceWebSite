<%--
  Created by IntelliJ IDEA.
  User: Shashi
  Date: 1/22/2025
  Time: 4:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- Main Content -->
<div class="container mt-5">
    <h4 class="mb-4">Recent Orders</h4>

    <!-- Responsive Table Wrapper -->
    <div class="table-responsive">
        <!-- Orders Table -->
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
            <tr>
                <th>Order ID</th>
                <th>Customer Name</th>
                <th>Product</th>
                <th>Quantity</th>
                <th>Total Price</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.customerName}</td>
                    <td>${order.productName}</td>
                    <td>${order.quantity}</td>
                    <td>${order.totalPrice}</td>
                    <td>${order.status}</td>
                    <td>
                        <form action="updateOrderStatus" method="post" style="display:inline;">
                            <input type="hidden" name="orderId" value="${order.id}">
                            <button type="submit" class="btn btn-warning btn-sm">Update</button>
                        </form>
                        <form action="deleteOrder" method="post" style="display:inline;">
                            <input type="hidden" name="orderId" value="${order.id}">
                            <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
