<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Exchange System</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/stylesheets/theme.css">
<link rel="stylesheet" href="resources/lib/font-awesome/css/font-awesome.css">

<script src="resources/lib/jquery-1.8.1.min.js" type="text/javascript"></script>

<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.brand {
	font-family: georgia, serif;
}

.brand .first {
	color: #ccc;
	font-style: italic;
}

.brand .second {
	color: #fff;
	font-weight: bold;
}
</style>
</head>

<body>
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<ul class="nav pull-right">
					<li id="fat-menu" class="dropdown">
						<a href="sign-up.jsp" id="drop3" role="button" class="dropdown-toggle">
							<i>Student Register</i>
						</a>
					</li>
				</ul>
				<a class="brand">
					<span class="first">Exchange System</span>
				</a>
			</div>
		</div>
	</div>


	<div class="container-fluid">

		<div class="row-fluid">
			<div class="dialog span4">
					<div class="block">
					<div class="block-heading">Sign In</div>
					<div class="block-body">
						<form id="loginform" action="login.htm" method="post">
							<label>Email</label>
							<input type="text" class="span12" name="email">
							<label>Password</label>
							<input type="password" class="span12" name="password">
							<label style="color:red">${msg}</label>
							<button type="submit" class="btn btn-primary pull-right">Sign In</button>
							<div class="clearfix"></div>
						</form>
					</div>
				</div>
				<p class="pull-right">
					<a href="sign-up.jsp">Student Register</a>
				</p>
			</div>
		</div>
	</div>

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="resources/lib/bootstrap/js/bootstrap.js"></script>

</body>
</html>


