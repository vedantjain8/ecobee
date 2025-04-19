<%@ page import="java.util.*, com.ecommerce.beans.Product"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Update Product</title>
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
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

button:hover {
    background-color: #0056b3;
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

/* Modal Styles */
.modal {
    display: none;
    position: fixed;
    z-index: 999;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
}

.modal-content {
    background: #fff;
    padding: 20px;
    margin: 10% auto;
    border-radius: 10px;
    width: 400px;
    position: relative;
}

.close {
    position: absolute;
    top: 10px;
    right: 15px;
    font-size: 20px;
    cursor: pointer;
}

.form-group {
    margin-bottom: 10px;
}

label {
    font-weight: bold;
    display: block;
    margin-bottom: 5px;
}

input, textarea {
    width: 100%;
    padding: 7px;
    border-radius: 5px;
    border: 1px solid #ccc;
}
</style>
</head>
<body>
    <div class="container">
        <h2>Update Product</h2>

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
            <input type="text" name="SearchProduct" placeholder="Enter Product Name to Update" required />
            <input type="hidden" name="from" value="update">
            <button type="submit">Search</button>
        </form>

        <!-- Product List Display (if any) -->
        <div id="updateProductList">
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
                    
                <button onclick='openModal(<%=p.getPrice()%>, <%=p.getQuantity()%>, "<%=p.getName()%>", "<%=p.getDescription()%>", "<%=p.getImage_url()%>", <%=p.getId()%>)'>Update</button>
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

    <!-- Modal for Update Form -->
    <div class="modal" id="updateModal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <h3>Update Product</h3>
            <form id="updateForm" action="UpdateProduct" method="post">
                <div class="form-group">
                    <label>Product Name</label>
                    <input type="text" id="productName" name="productName" required />
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <textarea id="productDescription" name="productDescription" required></textarea>
                </div>
                <div class="form-group">
                    <label>Price</label>
                    <input type="number" name="productPrice" step="0.01" id="productPrice" required />
                </div>
                <div class="form-group">
                    <label>Quantity</label>
                    <input type="number" id="productQuantity" name="productQuantity" required />
                </div>
                <div class="form-group">
                    <label>Image</label>
                    <input type="text" id="productImage" name="productImageUrl" required />
                    <input type="hidden" id="productId" name="productId">
                </div>
                <button type="submit">Update Product</button>
            </form>
        </div>
    </div>

</body>

<script>
function openModal(price, quantity, name, description, image, id) {
    document.getElementById("productName").value = name;
    document.getElementById("productDescription").value = description;
    document.getElementById("productPrice").value = price;
    document.getElementById("productQuantity").value = quantity;
    document.getElementById("productImage").value = image;
    document.getElementById("productId").value = id;
    document.getElementById("updateModal").style.display = "block";
}

function closeModal() {
    document.getElementById("updateModal").style.display = "none";
}
</script>
</html>
