<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Ver ${pelicula.getTitulo()}</title>
    <!-- Estilos del Reproductor  -->
    <link rel="stylesheet" href="https://cdn.plyr.io/3.6.2/plyr.css" />
    <link rel="stylesheet" href="<c:url value="/css/estrellas.css"/>">
    <jsp:include page="includes/importarArchivos.jsp"></jsp:include>
</head>
<body>
<jsp:include page="includes/header.jsp"></jsp:include>

<div class="container">
    <!-- Presentacion -->
    <div class="containerPresentacion row">
        <div class="divImagen col-md-auto">
            <img src="../../image/${pelicula.getUbicacionArchivo()}" alt="Portada" id="portadaPresentacion">
        </div>
        <div class="col-md">
            <h3 class="letraAmarilla">${pelicula.getTitulo()}</h3>
            <p>Fecha de estreno: <span>${pelicula.getFecha_estreno()}</span></p>
            <p>Genero: <span>${pelicula.getGenero()}</span></p>
            <p>Sinopsis:
                <br />
                <p>${pelicula.getDescripcion()}</p><br>
            <%--            inicio valoracion--%>
            <p>
            <p>Puntaje:
                <br />
            <p><c:if test="${pelicula.getValor()==null}">Aun no hay puntaje -</c:if>${pelicula.getValor()} de 5</p><br>

            <p>
                <form action="../../calificar-pelicula" method="post">
                    <input type="hidden" value="${pelicula.getId()}" name="idPeli">
            <p>Puntuar</p>
            <span class="clasificacion" style="height: 2rem">

                            <input id="radio1" type="radio" name="estrellas" value="5">
                            <label for="radio1">★</label>
                            <input id="radio2" type="radio" name="estrellas" value="4">
                            <label for="radio2">★</label>
                            <input id="radio3" type="radio" name="estrellas" value="3">
                            <label for="radio3">★</label>
                            <input id="radio4" type="radio" name="estrellas" value="2">
                            <label for="radio4">★</label>
                            <input id="radio5" type="radio" checked name="estrellas" value="1">
                            <label for="radio5">★</label>
                            </span>
            <input type="submit" value="Votar"  class="btn btn-warning ml-5">
            </form>
            </p><br>
            <%--            fin valoracion--%>
            <%--            boton ver mas tarde--%>
            <p>
            <form action="../../ver-mas-tarde" method="post">
                <div class="custom-control custom-switch">
                    <input type="hidden" name="idObtenido" value="${pelicula.getId()}">
                    <input type="hidden" name="tipoObtenido" value="pelicula">
                    <input type="checkbox" class="custom-control-input" id="switch1" name="vermastarde">
                    <label class="custom-control-label" for="switch1">Agregar a lista de ver mas tarde</label>
                    <button type="submit" class=" ml-1 btn btn-success btn-sm">Aplicar cambios</button>
                </div>
            </form>
            </p>
            <br>
            <%--            fin boton ver mas tarde--%>
            <c:if test="${rol == 'admin'}">
                <a href="<c:url value="/actualizar-pelicula/${pelicula.getId()}"/>" class="p-3 mb-2 bg-info text-white">Modificar Pelicula </a>
            </c:if>
            </p>
        </div>
    </div>

    <!-- Reproductor -->
    <video id="player" playsinline controls data-poster="../../image/${pelicula.getUbicacionArchivo()}">
        <source src="../../image/ejemplo.mp4" type="video/mp4" />
    </video>
</div>


<!-- Codigo del reproductor  -->
<script src="https://cdn.plyr.io/3.6.2/plyr.polyfilled.js"></script>
<script>
    //Instranciamos el reproductor
    const player = new Plyr('#player');
</script>
<jsp:include page="includes/footer.jsp"></jsp:include>
