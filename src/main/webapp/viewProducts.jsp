<%@ page import="java.util.List" %>
<%@ page import="com.shashimadushan.ecomweb.dto.ProductDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@include file="adminNavbar.jsp"%>
<div class="container mt-5">
    <h2 class="text-center mb-4">Product Management</h2>

    <!-- Product Table -->
    <table class="table table-bordered table-striped  table-responsive">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Category</th>
            <th>Price</th>
            <th>Stock</th>
            <th>Image URL</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("products");
            if (products != null && !products.isEmpty()) {
                for (ProductDTO product : products) {
        %>
        <tr>
            <td><%=product.getId()%></td>
            <td><%=product.getName()%></td>
            <td><%=product.getDescription()%></td>
            <td><%=product.getCategory().getName()%></td>
            <td><%=product.getPrice()%></td>
            <td><%=product.getStock()%></td>
            <td><%=product.getImagepath()%></td>
            <td>
                <button type="button" class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#updateModal<%=product.getId()%>">Update</button>
                <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteModal<%=product.getId()%>">Delete</button>
            </td>
        </tr>

        <!-- Update Modal -->
        <div class="modal fade" id="updateModal<%=product.getId()%>" tabindex="-1" aria-labelledby="updateModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="updateModalLabel">Update Product</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="updateproduct" method="post">
                        <div class="modal-body">
                            <input type="hidden" name="productId" value="<%=product.getId()%>">
                            <div class="mb-3">
                                <label for="productName<%=product.getId()%>" class="form-label">Product Name</label>
                                <input type="text" class="form-control" id="productName<%=product.getId()%>" name="productName" value="<%=product.getName()%>" required>
                            </div>
                            <div class="mb-3">
                                <label for="productDescription<%=product.getId()%>" class="form-label">Description</label>
                                <textarea class="form-control" id="productDescription<%=product.getId()%>" name="productDescription" required><%=product.getDescription()%></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="productCategory<%=product.getId()%>" class="form-label">Category</label>
                                <input type="text" class="form-control" id="productCategory<%=product.getId()%>" name="productCategory" value="<%=product.getCategory().getCategoryId()%>" required>
                            </div>
                            <div class="mb-3">
                                <label for="productPrice<%=product.getId()%>" class="form-label">Price</label>
                                <input type="number" class="form-control" id="productPrice<%=product.getId()%>" name="productPrice" value="<%=product.getPrice()%>" required>
                            </div>
                            <div class="mb-3">
                                <label for="productStock<%=product.getId()%>" class="form-label">Stock</label>
                                <input type="number" class="form-control" id="productStock<%=product.getId()%>" name="productStock" value="<%=product.getStock()%>" required>
                            </div>
                            <div class="mb-3">
                                <label for="productImageUrl<%=product.getId()%>" class="form-label">Image URL</label>
                                <input type="url" class="form-control" id="productImageUrl<%=product.getId()%>" name="productImageUrl" value="<%=product.getImagepath()%>" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            <button type="submit" class="btn btn-warning">Update</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Delete Modal -->
        <div class="modal fade" id="deleteModal<%=product.getId()%>" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteModalLabel">Delete Product</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Are you sure you want to delete this product?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <form action="deleteproduct" method="post" style="display:inline;">
                            <input type="hidden" name="productId" value="<%=product.getId()%>">
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="8" class="text-center">No Products available.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <!-- Add Product Button -->
    <div class="text-end">
        <a href="addProduct.jsp" class="btn btn-primary">Add New Product</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>