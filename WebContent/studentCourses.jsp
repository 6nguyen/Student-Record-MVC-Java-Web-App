<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>

<head>
	<title>View Courses</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
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
			<div style="overflow-x:auto;">
				<table>
					<tr>
						<th>Ticket Number</th>
						<th>Course Name</th>
						<th>Instructor</th>
						<th>Meeting Times</th>
					</tr>
					<tr>
						<td>CS213</td>
						<td>Artificial Intelligence</td>
						<td>George Nguyen</td>
						<td>M W F</td>
					</tr>
					<tr>
						<td>BIO166</td>
						<td>Evolution and Ecology</td>
						<td>Charleton Doorwin</td>
						<td>M W</td>
					</tr>
					<tr>
						<td>PHYS110B</td>
						<td>Universe Formation</td>
						<td>Neil DeGrass Armstrong</td>
						<td>Tu Th</td>
					</tr>
					<tr>
						<td>CS110</td>
						<td>Responsive Web Design</td>
						<td>Elizabeth Ho</td>
						<td>Tu</td>
					</tr>
				</table>
			</div>
			
			<br/><br/>
			<a href="StudentControllerServlet" class="backLink">Back to List</a>
			
		</div>
	</div>

</body>


</html>