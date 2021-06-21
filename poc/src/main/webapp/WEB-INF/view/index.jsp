<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
  integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

 <link rel="stylesheet" href="<c:url value="/resources/css/my.css" />">
 
<script src="<c:url value="/resources/js/office.js" />" type="text/javascript"></script>


 
<title>Spring MVC</title>
</head>
<body class="px-1">
  <h3>Spring MVC</h3>
  
  <p>${hello}
  
  <ul>
    <li><a href="<c:url value="/office" />">Oficinas</a>
  </ul>

</body>
</html>