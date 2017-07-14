<!DOCTYPE html>
<html>

<head>
	<title>Add Student</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>

<body>
	
	<div id="wrapper">
		<div id="header">
			<h1>DZL Center</h1>
			<h5>For Kids Who Can't Read Good</h5>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
			<h3 id="addStudent">Add Student</h3>
			
			<form action="StudentControllerServlet" method="POST">
			
				<input type="hidden" name="action" value="ADD" />
				
				<table>
					<tbody>
						<tr>
							<td><label>First Name:</label></td>
							<td><input type="text" name="firstName" /></td>
						</tr>
						<tr>
							<td><label>Last Name:</label></td>
							<td><input type="text" name="lastName" /></td>
						</tr>
						<tr>
							<td><label>Email:</label></td>
							<td><input type="text" name="email" /></td>
						</tr>
						<tr>
							<td><label></label></td>
							<td><input type="submit" value="Save" class="save" /></td>
						</tr>
					</tbody>
				</table>
			</form>
			
			<p>
				<a href="StudentControllerServlet">Back to List</a>
			</p>
		</div>
	</div>
</body>

</html>