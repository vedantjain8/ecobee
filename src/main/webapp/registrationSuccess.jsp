<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Successful</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 20px;
	background-color: #f4f7f6;
}

.container {
	max-width: 500px;
	margin: 0 auto;
	background-color: white;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	text-align: center;
}

h2 {
	color: #2c7873;
}

.success-message {
	color: #28a745;
	margin: 20px 0;
}

.user-details {
	text-align: left;
	margin: 20px 0;
	padding: 10px;
	background-color: #f8f9fa;
	border-radius: 4px;
}

a {
	display: inline-block;
	background-color: #2c7873;
	color: white;
	text-decoration: none;
	padding: 10px 15px;
	border-radius: 4px;
	margin-top: 15px;
}

a:hover {
	background-color: #205e5a;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Registration Successful!</h2>
		<div class="success-message">
			<p>Your account has been created successfully.</p>
		</div>

		<div class="user-details">
			<p>
				<strong>Username:</strong> ${requestScope.username}
			</p>
			<p>
				<strong>Email:</strong> ${requestScope.email}
			</p>
		</div>

		<a href="index.html">Back to Registration</a>
	</div>
</body>
</html>