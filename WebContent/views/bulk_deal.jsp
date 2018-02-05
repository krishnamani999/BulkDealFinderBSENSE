<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Bulk Deal Details</title>
		<jsp:useBean id="ResponseModel" class="modules.bulkdeal.BulkDealModel" scope="request"></jsp:useBean>
		<script>
			var mainModel = "<jsp:getProperty property="a" name="ResponseModel"/>";
			alert(mainModel);
		</script>
	</head>
	<body>
	
	</body>
</html>