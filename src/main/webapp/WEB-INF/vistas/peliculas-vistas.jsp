<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="includes/importarArchivos.jsp"></jsp:include>
</head>
<body>
<jsp:include page="includes/header.jsp"></jsp:include>

<div class="pelicula-principal">
    <div class="contenedor">
        <h3 class="titulo">Peliculas Vistas</h3>
        <div class="contenedor-carousel">
            <div class="d-flex justify-content-start flex-wrap">
                <c:forEach items="${peliculas}" var="pelicula">
                    <div class="contenido mx-2 my-4">
                        <a href="reproducir/pelicula/${pelicula.getId()}" class="thumbnail">
                            <img src="image/${pelicula.getUbicacionArchivo()}"
                                 alt="${pelicula.getUbicacionArchivo()}"
                                 class="imagenPortadaHome"></a>
                        <a href="reproducir/pelicula/${pelicula.getId()}" class="tituloPelicula">${pelicula.getTitulo()}</a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div><br><br><br>
    <div class="contenedor">
        <h3 class="titulo">Series vistas</h3>
        <div class="contenedor-carousel">
            <div class="d-flex justify-content-start flex-wrap">
                <c:forEach items="${series}" var="serie">
                    <div class="contenido mx-2 my-4">
                        <a href="reproducir/serie/${serie.getId()}" class="thumbnail">
                            <img src="image/${serie.getUbicacionArchivo()}"
                                 alt="${serie.getUbicacionArchivo()}"
                                 class="imagenPortadaHome"></a>
                        <a href="reproducir/serie/${serie.getId()}" class="tituloPelicula">${serie.getTitulo()}</a>
                    </div>
                </c:forEach>

            </div>
        </div>
    </div>
</div>


<jsp:include page="includes/footer.jsp"></jsp:include>
