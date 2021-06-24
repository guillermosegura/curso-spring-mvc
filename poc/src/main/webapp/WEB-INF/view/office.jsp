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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="<c:url value="/resources/js/office.js" />"></script>
<title>Oficinas</title>
</head>
<body class="px-1">
  <h3>Oficinas</h3>


  <p>
    <a href="<c:url value="/office_add" />">
      <button type="button" class="btn btn-primary" aria-label="Left Align">
        <span class="fa fa-plus" aria-hidden="true"></span> Agregar oficina
      </button>
    </a> <a href="<c:url value="/office?view=xml"/>" target="_blank">
      <button type="button" class="btn btn-success" aria-label="Left Align">
        <span class="fa fa-file-text-o" aria-hidden="true"></span> Ver XML
      </button>
    </a> <a href="<c:url value="/office?view=json"/>" target="_blank">
      <button type="button" class="btn btn-info" aria-label="Left Align">
        <span class="fa fa-file-text-o" aria-hidden="true"></span> Ver JSON
      </button>
    </a>
  <table class="table">
    <caption>Oficinas</caption>
    <thead>
      <tr>
        <th scope="col">C&oacute;digo</th>
        <th scope="col">Ciudad</th>
        <th scope="col">Teléfono</th>
        <th scope="col">Dirección 1</th>
        <th scope="col">Dirección 2</th>
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
          <td><a href="<c:url value="/office_edit?officeCode=${office.officeCode}" />">
              <button id="editOffice${office.officeCode}" type="button" class="btn btn-info" data-toggle="tooltip"
                data-placement="bottom" title="Editar">
                <span class="fa fa-edit" aria-hidden="true"></span>
              </button>
          </a> <a href="<c:url value="/office_delete?officeCode=${office.officeCode}" />">
              <button type="button" class="btn btn-warning" data-toggle="tooltip" data-placement="bottom"
                title="Eliminar">
                <span class="fa fa-remove" aria-hidden="true"></span>
              </button> <a href="<c:url value="/office_view?officeCode=${office.officeCode}&view=xml"/>" target="_blank">
                <button type="button" class="btn btn-success" aria-label="Left Align">
                  <span class="fa fa-file-text-o" aria-hidden="true"></span> Ver XML
                </button>
            </a> <a href="<c:url value="/office_view?officeCode=${office.officeCode}&view=json"/>" target="_blank">
                <button type="button" class="btn btn-info" aria-label="Left Align">
                  <span class="fa fa-file-text-o" aria-hidden="true"></span> Ver JSON
                </button>
            </a>


          </a></td>
        </tr>
      </c:forEach>
    <tbody>
  </table>


  <p>
    <a href="<c:url value="/" />">
      <button type="button" class="btn btn-primary" aria-label="Left Align">
        <span class="fa fa-undo" aria-hidden="true"></span> Regresar
      </button>
    </a>
</body>
</html>