<%@ page import="com.shashimadushan.ecomweb.dto.CategoryDTO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Categories</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<!-- Navigation Bar -->
<%@include file="adminNavbar.jsp"%>
<div class="container my-5">
    <h1 class="text-center mb-4">Manage Categories</h1>

    <!-- Add Category Form -->
    <div class="card mb-4">
        <div class="card-body">
            <h5 class="card-title">Add New Category</h5>
            <form action="addcategory" method="post">
                <div class="mb-3">
                    <label for="categoryName" class="form-label">Category Name</label>
                    <input type="text" class="form-control" id="categoryName" name="categoryName" required>
                </div>
                <button type="submit" class="btn btn-primary">Add Category</button>
            </form>
        </div>
    </div>

    <!-- Category Table -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Category List</h2>
        <button type="button" class="btn btn-primary" onclick="refresh()">Refresh List</button>
    </div>

    <table class="table table-striped table-responsive-md">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<CategoryDTO> categories = (List<CategoryDTO>) request.getAttribute("categorie-list");
            if (categories != null && !categories.isEmpty()) {
                for (CategoryDTO category : categories) {
        %>
        <tr>
            <td><%=category.getCategoryId()%></td>
            <td><%=category.getName()%></td>
            <td>
                <button class="btn btn-warning btn-sm" onclick="showUpdateModal('<%=category.getCategoryId()%>', '<%=category.getName()%>')">Update</button>
                <button class="btn btn-danger btn-sm" onclick="confirmDelete('<%=category.getCategoryId()%>')">Delete</button>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="3" class="text-center">No categories available.</td>
        </tr>
        <%
            }
        %>
        <% if (request.getAttribute("errorMessage") != null) { %>
        <div class="alert alert-danger" role="alert">
            <%= request.getAttribute("errorMessage") %>
        </div>
        <% } %>
        </tbody>
    </table>
</div>

<!-- Update Category Modal -->
<div class="modal fade" id="updateCategoryModal" tabindex="-1" aria-labelledby="updateCategoryModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="updatecategory" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="updateCategoryModalLabel">Update Category</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="updateCategoryId" name="categoryId">
                    <div class="mb-3">
                        <label for="updateCategoryName" class="form-label">Category Name</label>
                        <input type="text" class="form-control" id="updateCategoryName" name="categoryName" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary">Update Category</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
function refresh() {
    window.location.href = "categorylist";
}

function showUpdateModal(categoryId, categoryName) {
    document.getElementById('updateCategoryId').value = categoryId;
    document.getElementById('updateCategoryName').value = categoryName;
    var updateModal = new bootstrap.Modal(document.getElementById('updateCategoryModal'));
    updateModal.show();
}

function confirmDelete(categoryId) {
    if (confirm('Are you sure you want to delete this category?')) {
        var form = document.createElement('form');
        form.method = 'post';
        form.action = 'deletecategory';
        var input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'categoryId';
        input.value = categoryId;
        form.appendChild(input);
        document.body.appendChild(form);
        form.submit();
    }
}
</script>
</body>
</html>