<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.shashimadushan.ecomweb.dto.UserDTO" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
<%@include file="adminNavbar.jsp"%>
<div class="container mt-5">
    <h1 class="text-center mb-4">User Management</h1>
    <table class="table table-striped table-bordered table-responsive">
        <thead class="thead-dark">
        <tr>
            <th>User ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Role</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<UserDTO> users = (List<UserDTO>) request.getAttribute("userList");
            if (users != null && !users.isEmpty()) {
                for (UserDTO user : users) {
        %>
        <tr>
            <td><%=user.getId() %></td>
            <td><%=user.getUsername()%></td>
            <td><%=user.getEmail()%></td>
            <td><%=user.getRole()%></td>
            <td><%=user.getStatus() ? "Active" : "Inactive"%></td>
            <td>
                <button class="btn <%=user.getStatus() ? "btn-danger" : "btn-success"%>"
                        onclick="confirmAction('<%=user.getStatus() ? "deactivate" : "activate"%>', '<%=user.getId()%>')">
                        <%=user.getStatus() ? "Deactivate" : "Activate"%>
                </button>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5" class="text-center">No users available.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
    function confirmAction(action, userId) {
        var message = action === 'deactivate' ? 'Are you sure you want to deactivate this user?' : 'Are you sure you want to activate this user?';
        if (confirm(message)) {
            window.location.href = "manageuser?action=" + action + "&userId=" + userId;
        }
    }
</script>
</body>
</html>