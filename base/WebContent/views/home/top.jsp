<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/stylesheets/theme.css">
<link rel="stylesheet" href="resources/lib/font-awesome/css/font-awesome.css">

<script src="resources/lib/jquery-1.8.1.min.js" type="text/javascript"></script>
<title>top</title>
</head>

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
<body>
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<ul class="nav pull-right">
					<li id="fat-menu" class="dropdown">
						<a href="UserManage.htm?action=view&userId=${user.id}" target='content' id="drop3" role="button" >
							<i class="icon-user"></i>
							${user.username }
						</a>
					</li>

					<li id="fat-menu" class="dropdown">
						<a href="logout.htm" target="_parent"> Logout</a>
						<!-- <a href="logout.htm" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown">
						logout
						</a> -->
					</li>
				</ul>
				<a href="Welcome.htm" class="brand" target="content">
					<span class="first">Exchange System</span>
				</a>
			</div>
		</div>
	</div>

	<script src="resources/lib/bootstrap/js/bootstrap.js"></script>
</body>
</html>