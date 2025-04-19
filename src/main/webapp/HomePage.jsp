<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.beans.Product" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Products</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f2f2f2;
        }

        .container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: center;
        }

        .card {
            background-color: white;
            width: 250px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            overflow: hidden;
            transition: transform 0.2s ease;
        }

        .card:hover {
            transform: scale(1.03);
        }

        .card img {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }

        .card-body {
            padding: 15px;
            text-align: center;
        }

        .card-body h3 {
            margin: 10px 0;
        }

        .price {
            color: green;
            font-weight: bold;
            font-size: 1.2em;
        }

        /* Style for the search form */
        form {
            text-align: center;
            margin-bottom: 20px;
        }

        input[type="text"] {
            padding: 10px;
            width: 300px;
            font-size: 16px;
        }

        button {
            padding: 10px 20px;
            font-size: 16px;
        }
    </style>
</head>
<body>

<!-- Search Bar -->
<form action="SearchProductServlet" method="get">
    <input type="text" name="SearchProduct" placeholder="Search products..." required>
    <input type="hidden" name="from" value="homeSearch">
    <button type="submit">Search</button>
</form>

<h1 style="text-align: center;">Welcome to Our Marketplace</h1>

<!-- Product List -->
<div class="container">
    <%
        List<Product> products = (List<Product>) request.getAttribute("productList");
        if (products != null && !products.isEmpty()) {
            for (Product p : products) {
    %>
    <div class="card">
        <img src="<%= p.getImage_url() %>" alt="Product Image">
        <div class="card-body">
            <h3><%= p.getName() %></h3>
            <p class="price">₹<%= String.format("%.2f", p.getPrice()) %></p>
        </div>
    </div>
    <%
            }
        } else {
    %>
        <p>No products found for the search.</p>
    <%
        }
    %>
</div>

</body>
</html>
