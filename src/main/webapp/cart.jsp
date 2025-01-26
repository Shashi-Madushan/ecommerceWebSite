<%@ page import="java.util.List" %>
<%@ page import="com.shashimadushan.ecomweb.dto.CartItemDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .cart-card { background-color: #ffffff; border: 1px solid #e0e0e0; border-radius: 12px; position: relative; padding: 20px; }
        .cart-image-container { width: 80px; height: 70px; display: flex; align-items: center; justify-content: center; }
        .cart-image { width: 80px; height: auto; }
        .quantity-controls .btn { width: 30px; height: 30px; font-size: 16px; font-weight: bold; padding: 0; display: flex; align-items: center; justify-content: center; }
        .quantity-display { font-size: 16px; font-weight: bold; }
        .btn-close { background: none; border: none; font-size: 18px; opacity: 0.6; position: absolute; top: 10px; right: 10px; }
        .btn-close:hover { opacity: 1; cursor: pointer; }
        .btn-checkout { background-color: #28a745; color: white; border: none; padding: 10px 20px; font-size: 16px; border-radius: 5px; width: 100%; }
        .btn-checkout:hover { background-color: #218838; }
        .cart-details { display: flex; align-items: center; justify-content: space-around; }
        .cart-info { flex-grow: 1; margin-left: 20px; }
        .cart-actions { display: flex; flex-direction: column; align-items: flex-end; }
        .navbar {
            margin: 5px auto;
            width: 98%;
            padding: 10px 20px;
            border-radius: 10px;
            z-index: 1000;
            background-color: #34495e;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
    </style>
</head>
<body>
<%@include file="navbar.jsp"%>
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
                <div id="cartItemsContainer">
                    <% for (CartItemDTO item : cartItemDTOS) { %>
                        <div class="cart-card mb-3 shadow-sm rounded">
                            <button class="btn-close remove-item-btn" data-id="<%= item.getId() %>"></button>
                            <div class="cart-details">
<!-- HTML: Ensure the data-productId attribute is correctly set -->
<input type="checkbox" class="select-item" name="selectedItems" value="<%= item.getId() %>" data-price="<%= item.getProduct().getPrice() %>" data-quantity="<%= item.getQuantity() %>" data-productid="<%= item.getProduct().getProductId() %>">                                <div class="cart-image-container">
                                    <img src="<%= item.getProduct().getImagepath() %>" alt="Product Image" class="cart-image">
                                </div>
                                <div class="cart-info">
                                    <h5 class="mb-1 text-dark"><%= item.getProduct().getName() %></h5>
                                    <small class="text-muted">$<%= item.getProduct().getPrice() %> / item</small>
                                    <small class="text-muted">$<%= item.getProduct().getProductId() %> / item</small>
                                </div>
                                <div class="cart-actions">
                                    <div class="quantity-controls d-flex align-items-center">
                                        <button type="button" class="btn btn-light btn-sm btn-update-quantity" data-id="<%= item.getId() %>" data-action="decrease">-</button>
                                        <span class="mx-2 quantity-display"><%= item.getQuantity() %></span>
                                        <button type="button" class="btn btn-light btn-sm btn-update-quantity" data-id="<%= item.getId() %>" data-action="increase">+</button>
                                    </div>
                                    <h6 class="text-dark mt-2">$<%= item.getQuantity() * item.getProduct().getPrice() %></h6>
                                </div>
                            </div>
                        </div>
                    <% } %>
                </div>
            <% } else { %>
                <p>Your cart is empty.</p>
            <% } %>
        </div>
        <div class="col-lg-4">
    <div class="order-summary">
        <h5>Order Summary</h5>
        <hr>
        <h6 class="d-flex justify-content-between">
            <span>Total:</span>
            <span id="total">$0.00</span>
        </h6>
        <form action="placeorder" method="post" id="checkoutForm">
            <button type="submit" class="btn-checkout mt-3" id="checkoutButton">Checkout Now</button>
        </form>
    </div>
</div>
    </div>
</div>
<script src="js/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function updateHiddenInputs() {
        const form = document.getElementById('checkoutForm');
        form.innerHTML = ''; // Clear the form

        document.querySelectorAll('.select-item:checked').forEach(checkbox => {
            const itemId = checkbox.value;
            const quantity = checkbox.dataset.quantity;
            const productId = checkbox.dataset.productid;

                console.log(itemId + quantity+ productId)
            // Create hidden inputs for selected items
            const itemIdInput = document.createElement('input');
            itemIdInput.type = 'hidden';
            itemIdInput.name = 'selectedItems';
            itemIdInput.value = itemId;
            form.appendChild(itemIdInput);

            const quantityInput = document.createElement('input');
            quantityInput.type = 'hidden';
            quantityInput.name = 'quantities';
            quantityInput.value = quantity;
            form.appendChild(quantityInput);

            const productIdInput = document.createElement('input');
            productIdInput.type = 'hidden';
            productIdInput.name = 'productIds';
            productIdInput.value = productId;
            form.appendChild(productIdInput);
        });


        const totalInput = document.createElement('input');
        totalInput.type = 'hidden';
        totalInput.name = 'total';

        const hiddenTotalElement = document.getElementById('total');
        totalInput.value = (hiddenTotalElement && hiddenTotalElement.value) || 0;
        form.appendChild(totalInput);
        // Append the checkout button
        const checkoutButton = document.createElement('button');
        checkoutButton.type = 'submit';
        checkoutButton.className = 'btn-checkout mt-3';
        checkoutButton.id = 'checkoutButton';
        checkoutButton.textContent = 'Checkout Now';
        form.appendChild(checkoutButton);
    }
    updateHiddenInputs();

    function calculateSubtotal() {
        let subtotal = 0;
        document.querySelectorAll('.select-item:checked').forEach(checkbox => {
            const price = parseFloat(checkbox.dataset.price);
            const quantity = parseInt(checkbox.dataset.quantity);
            subtotal += price * quantity;
        });
        return subtotal;
    }

    function calculateTotal(subtotal) {
        return subtotal;
    }

    function updateOrderSummary() {
        const subtotal = calculateSubtotal();
        const total = calculateTotal(subtotal);

        document.getElementById('total').textContent = total;


        document.getElementById('total').value = total.toFixed(2);
        updateHiddenInputs()
    }

    function setupEventListeners() {
        document.querySelectorAll('.select-item').forEach(checkbox => {
            checkbox.addEventListener('change', updateOrderSummary);
        });

        document.querySelectorAll('.btn-update-quantity').forEach(button => {
            button.addEventListener('click', function () {
                const card = this.closest('.cart-card');
                const quantityDisplay = card.querySelector('.quantity-display');
                const checkbox = card.querySelector('.select-item');
                let quantity = parseInt(quantityDisplay.textContent);

                if (this.dataset.action === 'increase') {
                    quantity++;
                } else if (this.dataset.action === 'decrease' && quantity > 1) {
                    quantity--;
                }

                quantityDisplay.textContent = quantity;
                checkbox.dataset.quantity = quantity;
                updateOrderSummary();
            });
        });

        document.querySelectorAll('.remove-item-btn').forEach(button => {
            button.addEventListener('click', function () {
                const card = this.closest('.cart-card');
                card.remove();
                updateOrderSummary();
            });
        });


    }

    document.addEventListener('DOMContentLoaded', setupEventListeners);
</script>
</body>
</html>