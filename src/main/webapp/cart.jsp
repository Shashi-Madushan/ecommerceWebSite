<%@ page import="java.util.List" %>
<%@ page import="com.shashimadushan.ecomweb.dto.CartItemDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }

        .cart-card {
            background-color: #ffffff;
            border: 1px solid #e0e0e0;
            border-radius: 12px;
            position: relative;
            padding: 20px;
        }

        .cart-image-container {
            width: 80px;
            height: 70px;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .cart-image {
            width: 80px; /* Set a consistent width */
            height: auto; /* Maintain aspect ratio */
        }

        .quantity-controls .btn {
            width: 30px;
            height: 30px;
            font-size: 16px;
            font-weight: bold;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .quantity-display {
            font-size: 16px;
            font-weight: bold;
        }

        .btn-close {
            background: none;
            border: none;
            font-size: 18px;
            opacity: 0.6;
            position: absolute;
            top: 10px;
            right: 10px;
        }

        .btn-close:hover {
            opacity: 1;
            cursor: pointer;
        }

        .btn-checkout { background-color: #28a745; color: white; border: none; padding: 10px 20px; font-size: 16px; border-radius: 5px; width: 100%; }
        .btn-checkout:hover { background-color: #218838; }

        .cart-details {
            display: flex;
            align-items: center;
            justify-content: space-around;
        }

        .cart-info {
            flex-grow: 1;
            margin-left: 20px;
        }

        .cart-actions {
            display: flex;
            flex-direction: column;
            align-items: flex-end;
        }
    </style>
</head>
<body>
<%
   List<CartItemDTO> cartItemDTOS = (List<CartItemDTO>) request.getAttribute("cartItems");
   String errorMessage = (String) request.getAttribute("error");
%>
<div class="container cart-container">
    <h2 class="mb-4">Shopping Cart</h2>
    <div class="row">
        <div class="col-lg-8">
            <% if (errorMessage != null) { %>
                <div class="alert alert-danger" role="alert">
                    <%= errorMessage %>
                </div>
            <% } else if (cartItemDTOS != null && !cartItemDTOS.isEmpty()) { %>
                <% for (CartItemDTO item : cartItemDTOS) { %>
            <div class="cart-card mb-3 shadow-sm rounded">
                <button class="btn-close remove-item-btn" data-id="<%= item.getId() %>"></button>
                <div class="cart-details">
                    <!-- Product Image -->
                    <div class="cart-image-container">
                        <img src="<%= item.getProduct().getImagepath() %>" alt="Product Image" class="cart-image">
                    </div>

                    <!-- Product Details -->
                    <div class="cart-info">
                        <h5 class="mb-1 text-dark"><%= item.getProduct().getName() %></h5>
                        <small class="text-muted">$<%= item.getProduct().getPrice() %> / item</small>
                    </div>

                    <!-- Quantity Controls and Total Price -->
                    <div class="cart-actions">
                        <div class="quantity-controls d-flex align-items-center">
                            <button class="btn btn-light btn-sm btn-update-quantity" data-id="<%= item.getId() %>" data-action="decrease">-</button>
                            <span class="mx-2 quantity-display"><%= item.getQuantity() %></span>
                            <button class="btn btn-light btn-sm btn-update-quantity" data-id="<%= item.getId() %>" data-action="increase">+</button>
                        </div>
                        <h6 class="text-dark mt-2">$<%= item.getQuantity() * item.getProduct().getPrice() %></h6>
                    </div>
                </div>
            </div>

            <% } %>
            <% } else { %>
                <p>Your cart is empty.</p>
            <% } %>
        </div>

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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.querySelectorAll('.btn-update-quantity').forEach(button => {
        button.addEventListener('click', function () {
            const id = this.dataset.id;
            const action = this.dataset.action;
            console.log(`Update item ID: ${id}, Action: ${action}`);
        });
    });

    document.querySelectorAll('.remove-item-btn').forEach(button => {
        button.addEventListener('click', function () {
            const id = this.dataset.id;
            console.log(`Remove item ID: ${id}`);
            // Add logic to remove the item from the cart
        });
    });
</script>
</body>
</html>