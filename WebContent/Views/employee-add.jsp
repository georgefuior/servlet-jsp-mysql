<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit employee</title>
</head>

<link
	href="https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
	
<body>

	<div class="container">
		<div class="float-right">
			<a href="${pageContext.request.contextPath}/logout.jsp">Logout</a>
		</div>
		<h1>Employee Directory</h1>
		<hr />
		<div class="row">

			<div class="col-md-4">
				<form action="${pageContext.request.contextPath}/EmployeeController"
					method="POST">
					<div class="form-group">
						Enter name: <input type="text" name="firstname"
							value=" ${employee.name}" placeholder= "Enter name" class="form-control" /></br>
					</div>
					<div class="form-group">
						Enter date of birth: <input type="date" name="dob"
							value=" ${employee.dob}" placeholder= "Enter date of birth" class="form-control" /></br>
					</div>
					<div class="form-group">
						Enter department : <input type="text" name="department"
							value="${employee.department}" placeholder= "Enter department"	class="form-control"> </br>
							
							 <input type="hidden" name="id"
							value="${employee.id}" class="form-control" />
					</div>



					<button class="btn btn-primary" type="submit">Save
						employee</button>
				</form>
			</div>

		</div>
	</div>

</body>
</html>

