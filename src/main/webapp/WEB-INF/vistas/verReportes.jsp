<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reportes</title>
    <!-- meta tags para el tipado y la vista en celulares-->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- bootstrap css -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/estilo.css"/>
</head>
<body>
<jsp:include page="includes/header.jsp"></jsp:include>
<div class="container">
    <div class="row align-items-start">
        <div class="containerForm col-lg-12" style="background-color: rgba(0, 0, 0, 0.5)!important; border: none!important; display: flex;">
        <c:forEach items="${reportes}" var="reportes">
            <div class="card" style="width: 18rem; margin: 0 10px;">
                <div class="card-body" style="margin: 5px;">
                    <h5 class="card-title">${reportes.getTitulo()}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">Razon :${reportes.getRazon()}</h6>
                    <p class="card-text">${reportes.getDescripcion()}</p>
                    <br><br>
                    <p class="card-text">Hora del Reporte: ${reportes.getFecha_estreno()}</p>
                    <a href="../ver-posteo/${reportes.getPosteo().getId()}" class="btn btn-primary">Ir al Post</a>

                </div>
            </div>
        </c:forEach>
        </div>
    </div>
</div>

<!-- bootstrap js y jquery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

</body>
</html>
