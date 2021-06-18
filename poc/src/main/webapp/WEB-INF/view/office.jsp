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

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<title>Oficinas</title>
</head>
<body>
  <h3>Oficinas</h3>
  <table class="table">
    <thead>
      <tr>
        <th scope="col">C&oacute;digo</th>
        <th scope="col">Ciudad</th>
        <th scope="col">Tel�fono</th>
        <th scope="col">Direcci�n 1</th>
        <th scope="col">Direcci�n 2</th>
        <th scope="col">State</th>
        <th scope="col">Country</th>
        <th scope="col">CP</th>
        <th scope="col">Territorio</th>
        <th scope="col">Acciones</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="office" items="${offices}">
        <tr>
          <td>${office.officeCode}</td>
          <td>${office.city}</td>
          <td>${office.phone}</td>
          <td>${office.addressLine1}</td>
          <td>${office.addressLine2}</td>
          <td>${office.state}</td>
          <td>${office.country}</td>
          <td>${office.postalCode}</td>
          <td>${office.territory}</td>
          <td>
            <button type="button" class="btn btn-info">
              <span class="fa fa-edit" aria-hidden="true"></span>
            </button>

            <button type="button" class="btn btn-danger">
              <span class="fa fa-remove" aria-hidden="true"></span>
            </button>
          </td>
        </tr>
      </c:forEach>
    <tbody>
  </table>


  <p>
    <a href="./">
      <button type="button" class="btn btn-primary" aria-label="Left Align">
        <span class="fa fa-undo" aria-hidden="true"></span> Regresar
      </button>
    </a>
</body>
</html>