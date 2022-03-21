<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

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
h2{
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
.form-control{
	border-radius:40px;
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
		<a class="navbar-brand" href="/"><i class="material-icons">add_location_alt</i> Mail Order Pharmacy</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#gg">
			<span class="navbar-toggler-icon"></span></button>
		<div class="collapse navbar-collapse" id="gg">
			<ul class="navbar-nav ml-auto">
						<li class="navbar-item"><div class="dropdown">
						<button class="btn btn-primary user-tab dropdown-toggle"
							type="button" id="dropdownMenuButton" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							<i class="material-icons">account_circle</i> Signup
						</button>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							<a class="dropdown-item" href="/login">Login</a> <a
								class="dropdown-item" href="/register">Signup</a>
						</div>
					</div></li>
			</ul>
		</div>
	</nav>
	<div class="container mt-5">
		<h2>Sign Up</h2>
		
<div class="wrapper fadeInDown">
  <div id="formContent">
   
   <form:form action="/register" method="post" modelAttribute="signup" class="mt-3">
        <div class="form-group">
            <form:label path="username">Name</form:label>
            <form:input path="username" class="form-control"/>
        </div>

        <div class="form-group">
            <form:label path="password">Password</form:label>
            <form:password path="password" class="form-control"/>
        </div>

        <input type="submit" value="Signup" class="btn btn-success mr-2" id="btn"/>
        <input type="reset" value="Clear" class="btn btn-danger" id="btn2"/>
    </form:form>

  </div>
</div>

	<div class="footer py-2">
		<span class="ml-3 foot-text">Copyright&copy; 2020 PBM</span>
	</div>
</body>



</html>