<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
        }
        .form-container {
            background-color: #fff;
            border-radius: 12px;
            padding: 30px;
            margin: 20px auto;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        }
        .section-title {
            font-weight: bold;
            color: #4a4a8c;
            margin-bottom: 15px;
        }
        .btn-primary {
            background-color: #4a4a8c;
            border: none;
        }
        .btn-primary:hover {
            background-color: #37376f;
        }
        .image-preview {
            max-width: 100%;
            max-height: 150px;
            margin: 10px 0;
            border-radius: 8px;
            object-fit: cover;
        }
        .upload-btn-wrapper {
            position: relative;
            overflow: hidden;
            display: inline-block;
        }
        .upload-btn {
            border: 1px solid #ccc;
            color: #4a4a8c;
            background-color: #f1f1f1;
            padding: 8px 12px;
            border-radius: 5px;
            cursor: pointer;
        }
        .upload-btn-wrapper input[type=file] {
            font-size: 100px;
            position: absolute;
            left: 0;
            top: 0;
            opacity: 0;
        }
    </style>
</head>
<body>
<%@include file="adminNavbar.jsp"%>
<div class="container">
    <h1 class="text-center my-4">Add New Product</h1>
    <form action="addproduct" method="post" enctype="multipart/form-data">
        <div class="row g-4">
            <!-- Upload Image -->
            <div class="col-lg-4 col-md-12 form-container">
                <h5 class="section-title">Upload Image</h5>
                <div class="upload-btn-wrapper">
                    <button class="upload-btn">Choose Image</button>
                    <input type="file" id="imageUpload" name="image" accept="image/*" onchange="previewImage(event)">
                </div>
                <img id="imagePreview" class="image-preview" src="#" alt="Image Preview" style="display: none;">
            </div>

            <!-- General Information, Pricing, Stock, and Category -->
            <div class="col-lg-8 col-md-12 form-container">
                <h5 class="section-title">General Information</h5>
                <div class="mb-3">
                    <label for="productName" class="form-label">Product Name</label>
                    <input type="text" class="form-control" id="productName" name="name" required>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Product Description</label>
                    <textarea class="form-control" id="description" name="description" rows="4" required></textarea>
                </div>

                <h5 class="section-title">Pricing and Stock</h5>
                <div class="mb-3">
                    <label for="price" class="form-label">Base Price</label>
                    <input type="number" step="0.01" class="form-control" id="price" name="price" required>
                </div>
                <div class="mb-3">
                    <label for="stock" class="form-label">Stock</label>
                    <input type="number" class="form-control" id="stock" name="stock" required>
                </div>
                <div class="mb-3">
                    <label for="discount" class="form-label">Discount</label>
                    <input type="number" step="1" class="form-control" id="discount" name="discount" placeholder="Enter Discount Percentage">
                </div>

                <h5 class="section-title">Category</h5>
                <div class="mb-3">
                    <label for="category" class="form-label">Product Category</label>
                    <select class="form-select" id="category" name="categoryId" required>
                        <option value="" disabled selected>Select a category</option>
                    </select>
                </div>

                <!-- Buttons -->
                <div class="text-center mt-4">
                    <button type="button" class="btn btn-secondary me-2">Save Draft</button>
                    <button type="submit" class="btn btn-primary">Add Product</button>
                </div>
            </div>
        </div>
    </form>
</div>

<script>
    function previewImage(event) {
        const input = event.target;
        const preview = document.getElementById('imagePreview');
        if (input.files && input.files[0]) {
            const reader = new FileReader();
            reader.onload = function (e) {
                preview.src = e.target.result;
                preview.style.display = 'block';
            };
            reader.readAsDataURL(input.files[0]);
        } else {
            preview.src = '#';
            preview.style.display = 'none';
        }
    }

    // AJAX call to fetch categories
    document.addEventListener('DOMContentLoaded', function() {
        fetchCategories();
    });

    function fetchCategories() {
        fetch('http://localhost:8080/category')
            .then(response => response.json())
            .then(data => {
                const categorySelect = document.getElementById('category');
                data.forEach(category => {
                    const option = document.createElement('option');
                    option.value = category.categoryId;
                    option.textContent = category.name;
                    categorySelect.appendChild(option);
                });
            })
            .catch(error => console.error('Error fetching categories:', error));
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>