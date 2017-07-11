<!DOCTYPE html>
<html>

<head>
	<title>Edit Student</title>
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
		<h3>Edit Student</h3>
		
		<form action="StudentControllerServlet" method="POST">
		
		<!-- Add hidden field to track studentID -->
			<input type="hidden" name="action" value="EDIT" />
			<input type="hidden" name="studentID" value="${THE_STUDENT.id}" />
			
			<table>
				<tbody>
					<tr>
						<!-- THE_STUDENT comes from StudentControllerServlet
							loadStudent() -->
						<td><label>First Name:</label></td>
						<td><input type="text" name="firstName" 
								   value="${THE_STUDENT.firstName}"/></td>
						
					</tr>
					<tr>
						<td><label>Last Name:</label></td>
						<td><input type="text" name="lastName" 
									value="${THE_STUDENT.lastName}"/></td>
					</tr>
					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email"
									value="${THE_STUDENT.email}"/></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>
		</form>

		<div style="clear: both;"></div>
	
		<p>
			<a href="StudentControllerServlet">Back to List</a>
		</p>
	</div>
</body>

</html>