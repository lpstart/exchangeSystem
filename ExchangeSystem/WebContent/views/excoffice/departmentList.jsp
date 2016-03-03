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
<link rel="shortcut icon" href="../resources/assets/ico/favicon.ico">
<title>department list</title>
<body>
	<div class="span12">
		<h1 class="page-title">Departments</h1>
		<div class="btn-toolbar">
			<button class="btn btn-primary" id="addDepartment">
				<i class="icon-plus"></i>
				New Department
			</button>
		</div>
		<div class="well">
			<table class="table">
				<thead>
					<tr>
						<th>id</th>
						<th>Department</th>
						<th>DepartmentAdmin</th>
						<th>Set Admin</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${departments}" var="deps" varStatus="status">
						<tr>
							<td>${deps.id}</td>
							<td><a href="UserManage.htm?action=view&userId=${deps.departmentadmin_id}">${deps.departmentname}</a></td>
							<td>${deps.username}</td>
							<td><button class="btn" onclick="setAdmin('${deps.id}','${deps.departmentadmin_id}')">Set Admin</button></td>
							<td>
								<a href="SaveOrUpdateDepartment.htm?id=${deps.id}&action=edit">
									<i class="icon-pencil"></i>
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pagination">
			<ul>
				<li><span>当前页码${stuCoursesPage.pageIndex}，每页${stuCoursesPage.pageSize}条记录，共${stuCoursesPage.totleSize}条记录</span></li>
				<c:if test="${stuCoursesPage.pageIndex > 1}">
					<li><a href="DepartmentList.htm?pageIndex=${stuCoursesPage.pageIndex - 1}">Prev</a></li>
				</c:if>
				<c:forEach var="i" begin="1" end="${stuCoursesPage.totalPage}">
					<li><a href="DepartmentList.htm?pageIndex=${i}">${i}</a></li>
				</c:forEach>
				<c:if test="${stuCoursesPage.pageIndex != stuCoursesPage.totalPage}">
					<li><a href="DepartmentList.htm?pageIndex=${stuCoursesPage.pageIndex + 1}">Next</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	<div id="selectBox" class="dialog span4 white_content">
		<div class="block">
			<div class="block-heading">Edit Department<a class="pull-right" href="javascript:void(0)" onclick="closeSelectBox()">X</a></div>
		    
		    <div class="block-body">
		    	<input type="hidden" id="departmentId" name="departmentId">
				<label>Department Name:<input type="text" id="departmentname" name="departmentname"></label>
			</div>
			<button class="btn pull-right" onclick="submitDepartment()">Confirm</button>
		</div>
	</div>
	<div id="selectBg" class="black_overlay"></div>
<script src="resources/lib/bootstrap/js/bootstrap.js"></script>
<script>
	function setAdmin(departmentId,departmentadmin_id){
		window.location.href="SaveOrUpdateDepadmin.htm?departmentId="+departmentId+"&departmentadmin_id="+departmentadmin_id;
	}
	$('#addDepartment').click(function(event) {
		window.location.href="SaveOrUpdateDepartment.htm?action=add";
	});
</script>
</body>
</html>