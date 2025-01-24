<%@ page import="com.shashimadushan.ecomweb.dto.UserDTO" %>
<style>
    /* Custom CSS for padding */

    .nav-item-custom {
        padding-left: 15px;
        padding-right: 15px;
    }
    .navbar {

        background-color: #f1f1f1;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .navbar-brand svg {
        width: 35px;
        height: 35px;
    }
    .profile-icon {
        width: 35px;
        height: 35px;
        object-fit: cover;
        border-radius: 50%;
        margin: 0 10px;
    }
    @media (max-width: 768px) {
        .navbar-nav {
            flex-direction: column;
            align-items: flex-start;
        }
        .navbar {
            height: auto;
            flex-direction: column;
        }
    }
</style>

<nav class="navbar navbar-expand-lg navbar-light">
    <div class="container-fluid">
        <a class="navbar-brand p-2" href="#">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 2l9 4.5-9 4.5-9-4.5L12 2z" />
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 12l9 4.5-9 4.5-9-4.5L12 12z" />
            </svg>
        </a>

        <button class="navbar-toggler mr-1" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item nav-item-custom"><a class="nav-link" href="#products">Products</a></li>
                <li class="nav-item nav-item-custom"><a class="nav-link" href="cart">Cart</a></li>
                <li class="nav-item nav-item-custom"><a class="nav-link" href="orders.jsp">Orders</a></li>
            </ul>

            <div class="d-flex p-2">
                <form class="d-flex">
                    <% if (session.getAttribute("user") != null) {
                        UserDTO user = (UserDTO) session.getAttribute("user");
                        String userName = user.getUsername();
                        String userId = String.valueOf(user.getId());
                        String userEmail = user.getEmail();
                    %>
                    <div class="dropdown">
                        <svg class="profile-icon dropdown-toggle" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" fill="currentColor" id="profileDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                            <!-- Test account icon path -->
                            <path d="M8 0a8 8 0 1 0 8 8A8 8 0 0 0 8 0zm0 14.5A6.5 6.5 0 1 1 14.5 8 6.5 6.5 0 0 1 8 14.5zM8 4a2 2 0 1 0 2 2 2 2 0 0 0-2-2zm0 8a5.978 5.978 0 0 0-4.472-2.09c.03-.34.1-.67.2-1H12.27c.1.33.17.66.2 1A5.978 5.978 0 0 0 8 12z"/>
                        </svg>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="profileDropdown">
                            <li><span class="dropdown-item-text">Name: <%= userName %></span></li>
                            <li><span class="dropdown-item-text">ID: <%= userId %></span></li>
                            <li><span class="dropdown-item-text">Email: <%= userEmail %></span></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="myAccount.jsp">My Account</a></li>
                            <li><a class="dropdown-item" href="logout">Logout</a></li>
                        </ul>
                    </div>
                    <% } else { %>
                    <button class="btn btn-outline-success" type="button" onclick="window.location.href='signin.jsp'">Login</button>
                    <% } %>
                </form>
            </div>
        </div>
    </div>
</nav>
