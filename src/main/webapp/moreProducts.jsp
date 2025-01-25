<%@ page import="com.shashimadushan.ecomweb.dto.ProductDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .search-bar {
            margin-bottom: 20px;
            display: flex;
            align-items: center;
            gap: 15px;
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

        .search-bar input {
            flex: 1;
            max-width: 300px;
            border-radius: 20px;
            padding: 10px 15px;
            border: 1px solid #ccc;
        }

        .category-checkbox {
            display: none;
        }

        .category-label {
            display: inline-block;
            padding: 5px 15px;
            margin: 5px;
            border-radius: 20px;
            background-color: #f0f0f0;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .category-checkbox:checked + .category-label {
            background-color: #007bff;
            color: white;
        }

        @media (max-width: 768px) {
            .search-bar {
                flex-direction: column;
                gap: 10px;
            }
        }
    </style>
</head>

<body>
<%@include file="navbar.jsp"%>
<section id="products" class="container my-5">
    <h2 class="text-center mb-4">Explore Our Products</h2>

    <div class="search-bar">
        <input type="text" id="searchByName" class="form-control" placeholder="Search by name">
        <div id="categoryFilter" class="d-flex flex-wrap">
            <!-- Checkboxes will be dynamically generated here -->
        </div>
    </div>

    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4" id="productContainer">
        <%
            List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("products");
            if (products != null && !products.isEmpty()) {
                for (ProductDTO product : products) {
        %>
        <div class="col product-card" data-name="<%=product.getName().toLowerCase()%>" data-category="<%=product.getCategory().getCategoryId()%>">
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

<script>
    document.addEventListener('DOMContentLoaded', function() {
        fetchCategories();
    });

    const searchByName = document.getElementById('searchByName');
    const productContainer = document.getElementById('productContainer');

    searchByName.addEventListener('input', filterProducts);

    function fetchCategories() {
        // Fetch categories from the API
        fetch('/category')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(categories => {
                // Get the container for the filters
                const categoryFilter = document.getElementById('categoryFilter');
                if (!categoryFilter) {
                    console.error('Element with id "categoryFilter" not found in the DOM.');
                    return;
                }

                // Clear existing filters to avoid duplicates
                categoryFilter.innerHTML = '';

                categories.forEach(category => {

                    // Create a checkbox for the category
                    const checkbox = document.createElement('input');
                    checkbox.type = 'checkbox';
                    checkbox.value = category.categoryId; // Use categoryId as the value
                    checkbox.className = 'category-checkbox';
                    checkbox.id = category.categoryId;
                    checkbox.addEventListener('change', filterProducts); // Trigger filtering on change

                    // Create a label for the checkbox
                    const label = document.createElement('label');
                    label.textContent = category.name;
                    label.className = 'category-label';
                    label.setAttribute('for',category.categoryId);

                    // Append the checkbox and label to the filter container
                    categoryFilter.appendChild(checkbox);
                    categoryFilter.appendChild(label);
                });
            })
            .catch(error => console.error('Error fetching categories:', error));
    }

    function filterProducts() {
        const nameFilter = searchByName.value.toLowerCase();
        const selectedCategories = Array.from(document.querySelectorAll('.category-checkbox:checked')).map(cb => cb.value);

        const products = document.querySelectorAll('.product-card');
        products.forEach(product => {
            const productName = product.getAttribute('data-name');
            const productCategory = product.getAttribute('data-category');

            const matchesName = productName.includes(nameFilter);
            const matchesCategory = selectedCategories.length === 0 || selectedCategories.includes(productCategory);

            if (matchesName && matchesCategory) {
                product.style.display = 'block';
            } else {
                product.style.display = 'none';
            }
        });
    }
</script>
<script src="js/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>