<%@ page import="java.util.*, com.ecommerce.beans.Product"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Delete Product</title>
<style>
body {
    font-family: Arial, sans-serif;
    background: #f2f2f2;
    padding: 20px;
}

.container {
    max-width: 600px;
    margin: auto;
    background: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.search-group {
    display: flex;
    gap: 10px;
    margin-bottom: 20px;
}

input[type="text"] {
    flex: 1;
    padding: 8px;
    border-radius: 5px;
    border: 1px solid #ccc;
}

button {
    padding: 8px 15px;
    background-color: #dc3545;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

button:hover {
    background-color: #c82333;
}

.product-item {
    padding: 10px;
    margin: 10px 0;
    background: #f9f9f9;
    border: 1px solid #ddd;
    border-radius: 5px;
}

.no-product-message {
    font-size: 16px;
    color: #ff0000;
    font-weight: bold;
    text-align: center;
}
</style>
</head>
<body>
    <div class="container">
        <h2>Delete Product</h2>

        <!-- Show Message Alert using JavaScript -->
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
                // Use JavaScript to show alert
                out.println("<script type='text/javascript'>");
                if (message.contains("successfully")) {
                    out.println("alert('Success: " + message + "');");
                } else {
                    out.println("alert('Error: " + message + "');");
                }
                out.println("</script>");
            }
        %>

        <!-- Product Search Form -->
        <form method="get" action="SearchProductServlet" class="search-group">
            <input type="text" name="SearchProduct" placeholder="Enter Product Name to Delete" required />
            <input type="hidden" name="from" value="delete">
            <button type="submit">Search</button>
        </form>

        <!-- Product List Display (if any) -->
        <div id="deleteProductList">
            <% 
            // Display list of products (if available)
            List<Product> productList = (List<Product>) request.getAttribute("productList");
            if (productList != null && !productList.isEmpty()) {
                for (Product p : productList) {
            %>
            <div class="product-item">
                <p>
                    <strong><%= p.getName() %></strong><br /> Description:
                    <%= p.getDescription() %><br /> Price: $<%= p.getPrice() %><br />
                    Quantity: <%= p.getQuantity() %><br /> Image:
                    <%= p.getImage_url() %></p>
                    
            
                <form method="post" action="DeleteProductServlet"
                    onsubmit="return confirm('Are you sure you want to delete this product?')">
                    <input type="hidden" name="productId" value="<%= p.getId() %>">
                    <button type="submit">Delete</button>
                </form>
            </div>
            <% 
                }
            } else if (request.getParameter("productName") != null) {
            %>
            <p>No products found with that name.</p>
            <% 
            }
            %>
        </div>
    </div>
</body>
</html>