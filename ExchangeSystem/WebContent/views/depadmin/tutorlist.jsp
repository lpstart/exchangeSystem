<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

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

.black_overlay{  display: none;  position: absolute;  top: 0%;  left: 0%;  width: 100%;  height: 100%;  background-color: black;  z-index:1001;  -moz-opacity: 0.8;  opacity:.80;  filter: alpha(opacity=80);  }  
.white_content {  display: none;  position: absolute;  top: 15%;  left: 25%;  width: 50%;  height: 40%;  padding: 16px; background-color: white;  z-index:1002;  overflow: auto;  }  
</style>
<title>StudentsList</title>
</head>
<body>
	<div class="span12">
		<h1 class="page-title">Tutor List</h1>
		
		<div class="btn-toolbar">
			<!-- <c:if test=""></c:if> -->
				<button class="btn btn-primary" id="addTutor">
					<i class="icon-plus"></i>
					New Tutor
				</button>
			
		</div>
		<div class="well">
			<table class="table">
				<thead>
					<tr>
						<th>id</th>
						<th>tutorUserName</th>
						<th>nationality</th>
						<th>sex</th>
						<th>birthday</th>
						<th>email</th>
						<th>tutorName</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tutorList}" var="tutor" varStatus="status">
						<tr>
							<td>${tutor.id}</td>
							<td>${tutor.username}</td>
							<td>${tutor.nationality}</td>
							<c:if test="${tutor.sex == 'false'}"><td>Male</td></c:if>
							<c:if test="${tutor.sex == 'true'}"><td>Female</td></c:if>
							<td>${tutor.birthday}</td>
							<td>${tutor.email}</td>
							<td>${tutor.first_name} ${tutor.last_name} </td>
							<td>
								<a href="UserManage.htm?userId=${tutor.id}&action=edit">
									<i class="icon-pencil"></i>
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
	<script src="resources/lib/bootstrap/js/bootstrap.js"></script>
	<script>
		$('#addTutor').click(function(event) {
			window.location.href="UserManage.htm?action=add";
		});
	</script>
</body>
</html>