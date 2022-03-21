<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<title>Mail Order Pharmacy</title>


<style>
.navbar {
	background-color: #9575cd;
	background-image: linear-gradient(to bottom right, #776CF7, #4037A9);
}

.navbar-dark .navbar-nav .nav-link {
	color: rgba(255, 255, 255, .8);
}

.navbar-dark .navbar-nav .nav-link:hover, .navbar-dark .navbar-nav .nav-item.active .nav-link
	{
	color: rgba(255, 255, 255, 1);
}

.footer {
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	background-color: #4037A9;
	color: white;
	text-align: left;
}

.landing {
	color: #2D2D2D;
	font-weight: bold;
	font-size: 3rem;
}

h2 {
	color: #2D2D2D;
}

.material-icons {
	vertical-align: middle;
}

#btn {
	background-color: #2C248D;
	border-radius: 40px;
	width: 10rem;
	border-color: #2C248D;
	border-width: 1px;
}

.form-control {
	border-radius: 40px;
}

#btn2 {
	color: #2C248D;
	border-radius: 40px;
	width: 10rem;
	background-color: #fff;
	border-color: #2C248D;
	border-width: 1px;
}

.user-tab {
	background-color: rgba(0, 0, 0, 0) !important;
	border-color: rgba(0, 0, 0, 0);
	border-left-color: rgba(255, 255, 255, .5) !important;
	border-radius: 0;
}
</style>

</head>


<body>

	<nav class="navbar navbar-expand-md navbar-dark">
		<a class="navbar-brand" href="/"><i class="material-icons">add_location_alt</i>
			Mail Order Pharmacy</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#gg">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="gg">
			<ul class="navbar-nav ml-auto">
				<li class="navbar-item"><a class="nav-link" href="subscribe">Subscribe</a>
				</li>
				<li class="navbar-item"><a class="nav-link" href="drugs">Drug
						Details</a></li>
				<li class="navbar-item"><a class="nav-link" href="refillStatus">Refill
						Status</a></li>
				<li class="navbar-item"><a class="nav-link" href="refillDue">Refill
						Due</a></li>

				<li class="navbar-item mr-1"><a class="nav-link"
					href="requestAdhoc">Request Adhoc Refills</a></li>

				<li class="navbar-item"><div class="dropdown">
						<button class="btn btn-secondary user-tab dropdown-toggle"
							type="button" id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							<i class="material-icons">account_circle</i>
							${login.getUsername()}
						</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="#">Details</a> <a
								class="dropdown-item" href="#">Logout</a>
						</div>
					</div></li>
			</ul>
		</div>
	</nav>
	<div class="container my-5">
		<h2>Prescription Details</h2>

		<div class="wrapper fadeInDown">
			<div id="formContent">



				<form:form action="subscribedo" modelAttribute="pres" method="POST">
					
					<div class="form-group">
						<form:label path="insurancePolicyNumber">Insurance Policy No.</form:label>
						<form:input path="insurancePolicyNumber" class="form-control" />
					</div>

					<div class="form-group">
						<form:label path="insuranceProvider">Insurance Provider</form:label>
						<form:input path="insuranceProvider" class="form-control" />
					</div>

					<div class="form-group">
						<form:label path="prescriptionDate">Prescription Date</form:label>
						<form:input path="prescriptionDate" class="form-control" />
					</div>


					<table>
						<c:forEach items="${drugs.allDrugsList}" var="drug"
							varStatus="tagStatus">
							<tr>
								<td><input type="text" value="${drug.drugName}" readonly="true" /></td>
								<td><input type="text" value="${drug.quantity}"
									readonly="true" /></td>
							</tr>
						</c:forEach>
					</table>

					<div class="form-group">
						<form:label path="dosage">Dosage</form:label>
						<form:input path="dosage" class="form-control" />
					</div>
					<div class="form-group">
						<form:label path="presCourse">Prescription Course</form:label>
						<form:input path="presCourse" class="form-control" />
					</div>
					<div class="form-group">
						<form:label path="doctorName">Doctor Name</form:label>
						<form:input path="doctorName" class="form-control" />
					</div>
					<div class="form-group">
						<label >Location</label>
						<input name="location" id="location" class="form-control" />
					</div>
					<div class="form-group">
						<label path="frequency">Frequency</label>
						<input name="frequency" class="form-control" />
					</div>

					<button type="submit" class="btn btn-primary" id="btn">Subscribe</button>
					<button type="button" class="btn btn-primary"
						onclick="window.location.href = 'unsubscribe';" id="btn2">Unsubscribe</button>
				</form:form>

			</div>
		</div>
	</div>
	<div class="footer py-2">
		<span class="ml-3 foot-text">Copyright&copy; 2020 PBM</span>
	</div>
</body>



</html>