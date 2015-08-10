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


<title>Info</title>
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
<h1 class="page-title">&nbsp;${objectInfo.objectName} Info</h1>
<div class="well">
	<form class="table">
		<c:forEach items="${objectInfo}" var="object" varStatus="index">
			<label>${object.key}</label>
			<input type="text" value="${object.value }" disabled="disabled" />
		</c:forEach>
	</form>
</div>
