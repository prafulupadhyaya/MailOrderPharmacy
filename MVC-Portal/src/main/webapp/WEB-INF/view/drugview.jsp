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
			<i class="material-icons"> drag_handle</i>
		</button>
		<div class="collapse navbar-collapse" id="gg">
			<ul class="navbar-nav ml-auto">
				<li class="navbar-item"><a class="nav-link" href="subscribe">Subscribe</a>
				</li>
				<li class="navbar-item"><a class="nav-link" href="unsubscribe">Unsubscribe</a>
				</li>
				<li class="navbar-item"><a class="nav-link" href="drugs">Drug
						Details</a></li>
				<li class="navbar-item"><a class="nav-link" href="refillStatus">Refill
						Status</a></li>
				<li class="navbar-item"><a class="nav-link" href="refillDue">Refill
						Due</a></li>

				<li class="navbar-item mr-1"><a class="nav-link"
					href="beforeAdhoc">Request Adhoc Refills</a></li>
				<li class="navbar-item"><div class="dropdown">
						<button class="btn btn-secondary user-tab dropdown-toggle"
							type="button" id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							<i class="material-icons">account_circle</i> ${login.username}
						</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="subscriptionIdsOfMember">Details</a> <a
								class="dropdown-item" href="/login">Logout</a>
						</div>
					</div></li>
			</ul>
		</div>
	</nav>
	<div class="container mt-5">
		<h2>Drug Details</h2>
		<table class="table table-hover mt-4">

			<tbody>
				<tr>
					<th>Drug Id</th>
					<td>${drugs.getId()}</td>
				</tr>
				<tr>
					<th>Drug Name</th>
					<td>${drugs.getName()}</td>
				</tr>
				<tr>
					<th>Cost per unit</th>
					<td>${drugs.getCost()}</td>
				</tr>
				<tr>
					<th>Manufacturer</th>
					<td>${drugs.getManufacturer()}</td>
				</tr>

				<tr>
					<th>Manufactured Date</th>
					<td>${drugs.getManufacturedDate()}</td>
				</tr>
				<tr>
					<th>Expiration Date</th>
					<td>${drugs.getExpiryDate()}</td>
				</tr>
				<tr>
					<th>Units Package</th>
					<td>${drugs.getUnitsPackage()}</td>
				</tr>
				<tr>
					<th>Quantity per location</th>
					<td>
					<tr>
							<th>Location</th>
							<th>Quantity</th>
						</tr>
					<c:forEach var="details" items="${drugs.getDrugDetails()}">
						<tr>
							<th>${details.getLocation()}</th>
							<td>${details.getQuantity()}</td>
						</tr>
					</c:forEach></td>
				</tr>

			</tbody>
		</table>



	</div>
	</div>

	<div class="footer py-2">
		<span class="ml-3 foot-text">Copyright&copy; 2020 PBM</span>
	</div>
</body>



</html>