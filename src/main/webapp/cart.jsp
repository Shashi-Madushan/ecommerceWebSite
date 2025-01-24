<%--
  Created by IntelliJ IDEA.
  User: Shashi
  Date: 1/20/2025
  Time: 9:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .cart-container {
            max-width: 1200px;
            margin: 20px auto;
        }
        .cart-card {
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 20px;
            background-color: #fff;
        }
        .cart-image {
            height: 80px;
            width: auto;
            border-radius: 10px;
        }
        .btn-update-quantity {
            width: 40px;
            height: 40px;
            font-size: 18px;
        }
        .quantity-input {
            width: 50px;
            text-align: center;
            font-weight: bold;
        }
        .remove-item-btn {
            background-color: #dc3545;
            color: #fff;
            border: none;
            font-size: 14px;
            padding: 8px 12px;
            border-radius: 5px;
        }
        .order-summary {
            border-radius: 10px;
            background-color: #fff;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        .btn-checkout {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            width: 100%;
        }
        .btn-checkout:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

<div class="container cart-container">
    <h2 class="mb-4">Shopping Cart</h2>
    <div class="row">
        <!-- Cart Items Section -->
        <div class="col-lg-8">
            <c:forEach var="item" items="${cartItems}">
                <div class="cart-card mb-3">
                    <div class="row align-items-center">
                        <div class="col-md-2 text-center">
                            <img src="${item.image}" alt="Product Image" class="cart-image">
                        </div>
                        <div class="col-md-4">
                            <h5 class="mb-1">${item.productName}</h5>
                            <p class="text-muted mb-0">Set: Colour ${item.color}</p>
                        </div>
                        <div class="col-md-2 text-center">
                            <div class="input-group">
                                <button class="btn btn-outline-secondary btn-update-quantity" data-id="${item.id}" data-action="decrease">-</button>
                                <input type="text" class="form-control quantity-input" value="${item.quantity}" data-id="${item.id}" readonly>
                                <button class="btn btn-outline-secondary btn-update-quantity" data-id="${item.id}" data-action="increase">+</button>
                            </div>
                        </div>
                        <div class="col-md-2 text-center">
                            <h6 class="mb-0">$${item.totalPrice}</h6>
                        </div>
                        <div class="col-md-2 text-center">
                            <button class="remove-item-btn" data-id="${item.id}">Remove</button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Order Summary Section -->
        <div class="col-lg-4">
            <div class="order-summary">
                <h5>Order Summary</h5>
                <hr>
                <p class="d-flex justify-content-between">
                    <span>Subtotal:</span>
                    <span>$${subTotal}</span>
                </p>
                <p class="d-flex justify-content-between">
                    <span>Discount (10%):</span>
                    <span>-$${discount}</span>
                </p>
                <p class="d-flex justify-content-between">
                    <span>Delivery Fee:</span>
                    <span>$${deliveryFee}</span>
                </p>
                <hr>
                <h6 class="d-flex justify-content-between">
                    <span>Total:</span>
                    <span>$${total}</span>
                </h6>
                <button class="btn-checkout mt-3">Checkout Now</button>
            </div>
        </div>
    </div>
</div>

<!-- Include Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- Include AJAX for Dynamic Updates (Optional) -->
<script>
    document.querySelectorAll('.btn-update-quantity').forEach(button => {
        button.addEventListener('click', function () {
            const id = this.dataset.id;
            const action = this.dataset.action;

            // Call your backend to update quantity
            console.log(`Update item ID: ${id}, Action: ${action}`);
        });
    });

    document.querySelectorAll('.remove-item-btn').forEach(button => {
        button.addEventListener('click', function () {
            const id = this.dataset.id;

            // Call your backend to remove item
            console.log(`Remove item ID: ${id}`);
        });
    });
</script>
</body>
</html>
