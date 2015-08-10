<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/stylesheets/theme.css">
<link rel="stylesheet" href="resources/lib/font-awesome/css/font-awesome.css">
<script src="resources/lib/jquery-1.8.1.min.js" type="text/javascript"></script>


<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="javascripts/html5.js"></script>
    <![endif]-->


<title>Select courses</title>
<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.choose {
	width: 13px;
	height: 13px;
	line-height: 13px;
	margin-right: 10px;
	vertical-align: -2px;
	*vertical-align: middle;
	_vertical-align: 3px;
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
<h1 class="page-title">&nbsp; Select courses</h1>
<div class="well">
	<form method="post" action="InsertSelectedCourses.htm">
		<table>
			<tr>
				<td><b>select department:</b></td>
				<td><select name="departmentID" id="departmentID">
						<c:forEach items="${departments}" var="dep" varStatus="status">
							<option value="${dep.id}">${dep.departmentname}</option>
						</c:forEach>
				</select></td>
			</tr>
		</table>
		<table id="courseTable" class="table">

		</table>
	</form>
</div>
<script>
	$(document).ready(function() {
		$("#departmentID").change(function() {
			selectedDep($(this).val());
		})
	})
	function selectedDep(departmentID) {
		$
				.ajax //检查新短消息
				({
					type : "POST",
					url : "/base/GetCoursesByDepartID.htm",
					data : "departmentID=" + departmentID,
					dataType : "JSON",
					timeout : 5000,
					beforeSend : function() {
					},
					async : true,
					success : function(responseJson) {
						var item = "<tr><td><b>#</b></td><td><b>courseName</b></td><td><b>choose</b></td>";
						if (responseJson.length == 0) {
							item = item
									+ "<tr><td colspan='3'>There is no course in this departmenet.</td></tr>";
						} else {
							for (i = 0; i < responseJson.length; i++) {
								var courseID = responseJson[i].id;
								item = item
										+ "<tr><td>"
										+ (i + 1)
										+ "</td><td>"
										+ "<a href=GetInfo.htm?objectName='course'&objectID="
										+ courseID
										+ ">"
										+ responseJson[i].coursename
										+ "</a>"
										+ "</td><td>choose<input type=checkbox class='choose' name=**course"+i+" value="+courseID+"></td></tr>";
							}
							item = item
									+ "<tr><td colspan=3 style='text-align:center'><input type='submit' value='submit'></td></tr>";
						}
						$("#courseTable").html(item);

					}
				});
	}
</script>

