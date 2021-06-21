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
<title>Oficinas::Edit</title>
</head>
<body class="px-1">
  <h3>Editar oficina</h3>

  <c:if test="${error}">
    <div class="alert alert-danger" role="alert">
      <h3>Error:</h3>
      <br>C&oacute;digo: ${errorCode.code} <br>Descripci&oacute;n: ${errorMessage}
    </div>
  </c:if>

  <form method="post" action="<c:url value="/office_edit" />">
    <input type="hidden" value="${office.officeCode}" id="officeCode" name="officeCode">

    <div class="form-group row">
      <label for="city" class="col-sm-2 col-form-label">Ciudad</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="city" name="city" required maxlength="50" value="${office.city}">
      </div>
    </div>

    <div class="form-group row">
      <label for="phone" class="col-sm-2 col-form-label">Tel&eacute;fono</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="phone" name="phone" required maxlength="50" value="${office.phone}">
      </div>
    </div>

    <div class="form-group row">
      <label for="addressLine1" class="col-sm-2 col-form-label">Direcci&oacute;n 1</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="addressLine1" name="addressLine1" required maxlength="50" value="${office.addressLine1}">
      </div>
    </div>

    <div class="form-group row">
      <label for="addressLine2" class="col-sm-2 col-form-label">Direcci&oacute;n 2</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="addressLine2" name="addressLine2" maxlength="50" value="${office.addressLine2}">
      </div>
    </div>

    <div class="form-group row">
      <label for="state" class="col-sm-2 col-form-label">Estado</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="state" name="state" maxlength="50" value="${office.state}">
      </div>
    </div>

    <div class="form-group row">
      <label for="country" class="col-sm-2 col-form-label">Pa&iacute;s</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="country" name="country" required maxlength="50" value="${office.country}">
      </div>
    </div>

    <div class="form-group row">
      <label for="postalCode" class="col-sm-2 col-form-label">C&oacute;digo Postal</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="postalCode" name="postalCode" required maxlength="15" value="${office.postalCode}">
      </div>
    </div>

    <div class="form-group row">
      <label for="territory" class="col-sm-2 col-form-label">Territorio</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" id="territory" name="territory" required maxlength="10" value="${office.territory}">
      </div>
    </div>

    <br>

    <div class="row">
      <div class="col-sm-2">&nbsp;</div>
      <div class="col-sm-2">
        <a href="./office">
          <button type="button" class="btn btn-outline-danger" aria-label="Left Align">
            <span class="fa fa-undo" aria-hidden="true"></span>Cancelar
          </button>
        </a>
      </div>
      <div class="col-sm-2">
        <button type="submit" class="btn btn-outline-info">
          <span class="fa fa-edit" aria-hidden="true"></span> Editar
        </button>
      </div>
    </div>




  </form>

  <br>
  <br>

  <p>
</body>
</html>