<!-- Import all files from com.nguyen92.web.jdbc package -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>Student Roster</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>


<body>


	<div id="wrapper">
		<div id="header">
			<h1>DZ Center</h1>
			<h5>For Kids Who Can't Read Good</h5>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
		
			<!-- Add new button: Add Student -->
			<input type="button" value="Add Student"
				onclick="window.location.href='addStudentForm.jsp'; false;"
			/>
			
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
				</tr>
					
			<c:forEach var="currentStudent" items="${studentList}">
				<tr>
					<td>${currentStudent.firstName}</td>
					<td>${currentStudent.lastName}</td>
					<td>${currentStudent.email}</td>
				</tr>
			</c:forEach>
									
			</table>
			
		</div>
	</div>




</body>


</html>