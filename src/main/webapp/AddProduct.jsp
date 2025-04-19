<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Add Product</title>
<style>
/* Your existing CSS styles */
body {
	font-family: Arial, sans-serif;
	background: #f2f2f2;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.form-container {
	background-color: #fff;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	width: 400px;
}

.form-container h2 {
	text-align: center;
	margin-bottom: 20px;
}

.form-group {
	margin-bottom: 15px;
}

label {
	display: block;
	margin-bottom: 6px;
	font-weight: bold;
}

input[type="text"], input[type="number"], textarea {
	width: 100%;
	padding: 8px;
	border-radius: 5px;
	border: 1px solid #ccc;
}

button {
	width: 100%;
	padding: 10px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 5px;
	font-size: 16px;
	cursor: pointer;
}

button:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<div class="form-container">
		<h2>Add Product</h2>
<form action="AddProductServlet" method="post">
			<div class="form-group">
				<label for="productName">Product Name</label> <input type="text"
					id="productName" name="productName" required />
			</div>
			<div class="form-group">
				<label for="productDescription">Product Description</label>
				<textarea id="productDescription" name="productDescription" required></textarea>
			</div>
			<div class="form-group">
				<label for="productPrice">Product Price ($)</label> <input
					type="number" id="productPrice" name="productPrice" step="0.01"
					required />
			</div>
			<div class="form-group">
				<label for="productQuantity">Product Quantity</label> <input
					type="number" id="productQuantity" name="productQuantity" required />
			</div>
			<div class="form-group">
				<label for="productImage">Product Image</label> <input type="text"
					id="productImage" name="productImage" required />
			</div>
			<button type="submit">Submit</button>
		</form>
	</div>

	<script>
    window.onload = function () {
        const status = "<%= request.getAttribute("status") != null ? request.getAttribute("status") : "" %>";
        if (status) {
            alert(status);
            window.location.href = "AddProduct.jsp"; // Reload page after alert
        }
    };
</script>

</body>
</html>
