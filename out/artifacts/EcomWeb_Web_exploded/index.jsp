<%@ page import="com.shashimadushan.ecomweb.dto.ProductDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enhanced JSP - Home</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }
        section{
            margin-top: 10px;
        }
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

        .category-icon {
            font-size: 2rem;
            color: #e67e22;
        }

        .product-card img {
            height: 200px;
            object-fit: cover;
        }

        .carousel-item {
            height: 400px;
            background-size: cover;
            background-position: center;
            color: white;
            text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.7);
        }

        .carousel-caption {
            bottom: 20%;
        }

        .carousel-caption h2 {
            font-size: 2.5rem;
            font-weight: bold;
        }

        .carousel-caption p {
            font-size: 1.2rem;
        }

        .carousel-caption .btn {
            margin-top: 10px;
            background-color: #e67e22;
            border: none;
            color: black;
        }

        .carousel-caption .btn:hover {
            background-color: #d35400;
        }

        .text-primary {
            color: #e67e22 !important;
        }

        .card {
            border: none;
            transition: transform 0.3s, box-shadow 0.3s;
            text-align: center;
        }

        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 15px rgba(0, 0, 0, 0.1);
        }

        .card-title {
            font-size: 1rem;
            font-weight: bold;
        }

        .btn-primary {
            background-color: #e67e22;
            border: none;
        }

        .btn-primary:hover {
            background-color: #d35400;
        }

        footer {
            background-color: #34495e;
            color: #ecf0f1;
        }

        .fixed-cart {
            position: fixed;
            bottom: 20px;
            right: 20px;
            background-color: #e67e22;
            color: white;
            border-radius: 50%;
            width: 60px;
            height: 60px;
            display: flex;
            justify-content: center;
            align-items: center;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            cursor: pointer;
            transition: transform 0.3s ease;
        }

        .fixed-cart:hover {
            transform: scale(1.1);
        }

        .cart-count {
            position: absolute;
            top: 5px;
            right: 5px;
            background-color: #d35400;
            border-radius: 50%;
            padding: 2px 6px;
            font-size: 0.8rem;
        }

        .modal-content {
            border-radius: 10px;
            width: 250%;
            height: 100%;
        }

        .modal-body {
            display: flex;
            align-items: center;
        }

        .modal-body img {
            max-width: 40%;
            height: auto;
            margin-right: 20px;
        }

        .quantity-control {
            display: flex;
            align-items: center;
        }

        .quantity-control button {
            background-color: #e67e22;
            border: none;
            color: white;
            padding: 5px 10px;
            margin: 0 5px;
            cursor: pointer;
        }

        .quantity-control input {
            width: 50px;
            text-align: center;
        }

        @media (max-width: 768px) {
            .carousel-item {
                height: 300px;
            }

            .carousel-caption h2 {
                font-size: 1.8rem;
            }

            .carousel-caption p {
                font-size: 1rem;
            }

            .carousel-caption .btn {
                font-size: 0.9rem;
                padding: 8px 16px;
            }
        }

        @media (max-width: 576px) {
            .carousel-item {
                height: 150px;
            }
            .carousel-caption h2 {
                font-size:1rem;
            }

            .carousel-caption p {
                font-size: 0.8rem;
            }

            .carousel-caption .btn {
                font-size: 0.6rem;
                padding: 5px 10px;
            }
        }
    </style>
</head>
<body>
<%@include file="navbar.jsp" %>

<%@ include file="featured-slider.jsp" %>

<section class="container my-5">
    <h2 class="text-center mb-4">Browse by Category</h2>
    <div class="row text-center">
        <div class="col-lg-2 col-md-4 col-sm-6 mb-4">
            <div class="category-icon">📱</div>
            <p>Mobile Phones</p>
        </div>
        <div class="col-lg-2 col-md-4 col-sm-6 mb-4">
            <div class="category-icon">🎧</div>
            <p>Headphones</p>
        </div>
        <div class="col-lg-2 col-md-4 col-sm-6 mb-4">
            <div class="category-icon">📷</div>
            <p>Cameras</p>
        </div>
        <div class="col-lg-2 col-md-4 col-sm-6 mb-4">
            <div class="category-icon">⌚</div>
            <p>Wearables</p>
        </div>
        <div class="col-lg-2 col-md-4 col-sm-6 mb-4">
            <div class="category-icon">🖥️</div>
            <p>Monitors</p>
        </div>
        <div class="col-lg-2 col-md-4 col-sm-6 mb-4">
            <div class="category-icon">🎮</div>
            <p>Gaming</p>
        </div>
    </div>
</section>

<section id="products" class="container my-5">
    <h2 class="text-center mb-4">Explore Our Products</h2>
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4">
        <%
            List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("products");
            if (products != null && !products.isEmpty()) {
                for (ProductDTO product : products) {
        %>
        <div class="col">
            <div class="card shadow-sm">
                <img src="<%=product.getImagepath()%>" class="card-img-top" alt="<%=product.getName()%>">
                <div class="card-body">
                    <h5 class="card-title"><%=product.getName()%></h5>
                    <p class="card-text text-muted">$<%=product.getPrice()%></p>
                    <button class="btn btn-primary w-100 add-to-cart" data-name="<%=product.getName()%>" data-productid="<%=product.getId()%>" data-price="<%=product.getPrice()%>" data-description="<%=product.getDescription()%>" data-image="<%=product.getImagepath()%>" data-stock="<%=product.getStock()%>">Add to Cart</button>
                </div>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <p class="text-center">No products available.</p>
        <%
            }
        %>
    </div>
</section>

<div class="fixed-cart">
    <i class="fas fa-shopping-cart"></i>
    <%
        Integer cartItemsCount = (Integer) request.getAttribute("cartItemsCount");
        if (cartItemsCount == null) {
            cartItemsCount = 0; // Default to 0 if the attribute is not set
        }
    %>
    <span class="cart-count"><%= cartItemsCount %></span>
</div>

<div class="modal fade" id="productModal" tabindex="-1" aria-labelledby="productModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="productModalLabel">Product Details</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <img id="productImage" src="" class="img-fluid" alt="Product Image">
                <div>
                    <h5 id="productName"></h5>
                    <p id="productDescription"></p>
                    <p ><strong>Unit Price :$</strong><span id="unitPrice"></span></p>
                    <p><strong>Stock:</strong> <span id="productStock"></span></p>
                    <div class="quantity-control">
                        <button id="decreaseQty">-</button>
                        <input type="number" id="productQty" value="1" min="1">
                        <button id="increaseQty">+</button>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-primary" id="addToCartConfirm">Add to Cart</button>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>

<script src="js/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    $(document).ready(function() {
        let cartCount = 0;
        let userId;
        let productId;
        <% if (session.getAttribute("user") != null) {
            UserDTO user = (UserDTO) session.getAttribute("user");
            String userId = String.valueOf(user.getId());
        %>
         userId = "<%= userId %>";
        <% } else { %>
         userId = null;
        <% } %>
        $('.add-to-cart').click(function() {
            const name = $(this).data('name');
            const description = $(this).data('description');
            const price = $(this).data('price')
            const image = $(this).data('image');
            const stock = $(this).data('stock');
           productId= $(this).data('productid');

            $('#productName').text(name);
            $('#productDescription').text(description);
            $('#unitPrice').text(price);
            $('#productImage').attr('src', image);
            $('#productStock').text(stock);
            $('#productModal').modal('show');
        });
        $('#addToCartConfirm').click(function() {
            const qty = parseInt($('#productQty').val());
            const productData = {
                name: $('#productName').text(),
                description: $('#productDescription').text(),
                image: $('#productImage').attr('src'),
                stock: $('#productStock').text(),
                quantity: qty,
                userId: userId,
                productId:productId
            };

            $.ajax({
                url: 'adtocart', // Replace with your servlet URL
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(productData),
                success: function(response) {
                    cartCount += qty;
                    $('.cart-count').text(cartCount);
                    $('#productModal').modal('hide');
                    alert('Product added to cart!');
                },
                error: function(xhr, status, error) {
                    console.error('Error adding product to cart:', error);
                    alert('Failed to add product to cart. Please try again.');
                }
            });
        });
        $('#increaseQty').click(function() {
            let qty = parseInt($('#productQty').val());
            $('#productQty').val(qty + 1);
        });

        $('#decreaseQty').click(function() {
            let qty = parseInt($('#productQty').val());
            if (qty > 1) {
                $('#productQty').val(qty - 1);
            }
        });
    });
</script>
</body>
</html>