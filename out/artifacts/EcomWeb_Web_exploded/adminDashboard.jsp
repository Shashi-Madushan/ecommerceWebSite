<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Dashboard</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .card {
      height: 100%;
    }
    .card-body {
      display: flex;
      flex-direction: column;
      justify-content: space-between;
    }
    .card-icon {
      width: 50px;
      height: 50px;
      margin-bottom: 15px;
    }
    .analytics-section {
      height: 20vh;
      display: flex;
      justify-content: space-around;
      align-items: center;
      background-color: #f8f9fa;
      margin-bottom: 20px;
    }
    .analytics-card {
      text-align: center;
    }
  </style>
</head>
<body>
  <!-- Navigation Bar -->
  <%@include file="adminNavbar.jsp"%>

  <!-- Analytics Section -->
  <section class="analytics-section">
    <div class="analytics-card">
      <h5>Total Orders</h5>
      <p>1234</p>
    </div>
    <div class="analytics-card">
      <h5>Total Users</h5>
      <p>567</p>
    </div>
    <div class="analytics-card">
      <h5>Total Products</h5>
      <p>890</p>
    </div>
  </section>

  <!-- Management Cards Section -->
  <section style="height: 80vh;">
    <div class="container mt-4">
      <div class="row row-cols-1 row-cols-md-4 g-4">
        <div class="col">
          <div class="card h-100">
            <div class="card-body text-center">
              <svg class="card-icon" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h18v18H3V3z" />
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 9h6v6H9V9z" />
              </svg>
              <h5 class="card-title">Product Management</h5>
              <p class="card-text">Manage all products in the inventory.</p>
              <a href="product-list" class="btn btn-primary mt-auto">Go to Product Management</a>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="card h-100">
            <div class="card-body text-center">
              <svg class="card-icon" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 10h16M4 14h16M4 18h16" />
              </svg>
              <h5 class="card-title">Category Management</h5>
              <p class="card-text">Organize products into categories.</p>
              <a href="categorylist" class="btn btn-primary mt-auto">Go to Category Management</a>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="card h-100">
            <div class="card-body text-center">
              <svg class="card-icon" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
              </svg>
              <h5 class="card-title">User Management</h5>
              <p class="card-text">Manage user accounts and permissions.</p>
              <a href="manageuser" class="btn btn-primary mt-auto">Go to User Management</a>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="card h-100">
            <div class="card-body text-center">
              <svg class="card-icon" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 7h18M3 11h18M3 15h18M3 19h18" />
              </svg>
              <h5 class="card-title">Order Management</h5>
              <p class="card-text">Track and manage customer orders.</p>
              <a href="viewOrder" class="btn btn-primary mt-auto">Go to Order Management</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>

  <%@include file="footer.jsp"%>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>