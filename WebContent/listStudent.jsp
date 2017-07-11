<!-- Import all files from com.nguyen92.web.jdbc package -->
<%@ page import="java.util.*, com.nguyen92.web.jdbc.*" %>


<!DOCTYPE html>
<html>
<head>
	<title>Student Roster</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<%
	// Step 1: get the students from the request object (sent by servlet)
	// Attribute name found in StudentControllServlet in listStudents()
	List<Student> studentList = (List<Student>) request.getAttribute("studentList");
%>


<body>

<!-- Step 2:  Make sure the request is being processed correctly before 
	continuing, then comment out -->
<!-- <%= studentList %> -->


	<div id="wrapper">
		<div id="header">
			<h2>DZ Center For Kids Who Can't Read Good</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
		
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
				</tr>
				
			<% for (Student x : studentList) { %>
				<tr>
					<td><%= x.getFirstName() %></td>
					<td><%= x.getLastName() %></td>
					<td><%= x.getEmail() %></td>
				</tr>
			<% } %>
									
			</table>
			
		</div>
	</div>




</body>


</html>