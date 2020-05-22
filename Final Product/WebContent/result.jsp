<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<link rel="stylesheet" href="styles2.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Code Complexity Results</title>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

</head>
<body>
<p>Results</p>
<table>
  <tr>
    <th>Code</th>
    <th>Ccs</th>
    <th>Ci</th>
    <th>Cm</th>
    <th>Cv</th>
    <th>Cc</th>
    <th>Cs</th>
  </tr>
	<c:forEach var="lines" items="${lines}" varStatus="loop">
    	<tr>
    		<td><c:out value="${lines}"></c:out></td>
    		<td><c:out value="${conditional.get(loop.index)}"></c:out></td>
    		<td><c:out value="${inheritance.get(loop.index)}"></c:out></td>
    		<td><c:out value="${method.get(loop.index)}"></c:out></td>
    		<td><c:out value="${variables.get(loop.index)}"></c:out></td>
    		<td><c:out value="${coupling.get(loop.index)}"></c:out></td>
    		<td><c:out value="${size.get(loop.index)}"></c:out></td>
    </tr>
    </c:forEach>
    <tr>
    		<th>Complexity per criteria</th>
    		<th><c:out value="${conX}"></c:out></th>
    		<th><c:out value="${inheX}"></c:out></th>
    		<th><c:out value="${methX}"></c:out></th>
    		<th><c:out value="${varX}"></c:out></th>
    		<th><c:out value="${cupX}"></c:out></th>
    		<th><c:out value="${sizeX}"></c:out></th>
    </tr>
    <tr>
    		<th>Total</th>
    		<th colspan="6"><c:out value="${total}"></c:out></th> 
    </tr>
    
</table>
<div  class="previous">	
<a href="index.html">&laquo; Previous</a>
</div>

</body>
</html>