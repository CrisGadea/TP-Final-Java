
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ver ${ serie.getTitulo() }</title>
    <!-- Estilos del Reproductor  -->
    <link rel="stylesheet" href="https://cdn.plyr.io/3.6.2/plyr.css" />
    <link rel="stylesheet" href="<c:url value="/css/estrellas.css"/>">
    <jsp:include page="includes/importarArchivos.jsp"></jsp:include>
</head>
<body>
<jsp:include page="includes/header.jsp"></jsp:include>
<div class="container">
    <div class="containerPresentacion row">
        <!-- se obtiene la portada guardada en la ubicacion /webapp/WEB-INF/image/-->
        <div class="divImagen col-md-auto">
            <img src="../../image/${serie.getUbicacionArchivo()}" alt="Portada" id="portadaPresentacion" >
        </div>

        <div class="col-md">
            <!-- se obtiene titulo seteado desde el form-->
            <h2>Titulo: ${serie.getTitulo()}</h2><br>

            <!-- se obtiene fecha seteado desde el form-->
            <p> Fecha de Estreno: ${ serie.getFecha_estreno()}</p><br>

            <p>Genero: ${serie.getGenero()}</p><br>

            <!-- se obtiene descripcion seteado desde el form-->
            <p>Descripcion:
                <br/>
                ${serie.getDescripcion()}</p><br>
            <%--            inicio valoracion--%>
            <p>
            <p>Puntaje:
                <br />
            <p><c:if test="${serie.getValor()==null}">Aun no hay puntaje -</c:if>${serie.getValor()} de 5</p><br>

            <p>
                <form action="../../calificar-serie" method="post">
                    <input type="hidden" value="${serie.getId()}" name="idPeli">
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
                    <input type="hidden" name="idObtenido" value="${serie.getId()}">
                    <input type="hidden" name="tipoObtenido" value="serie">
                    <input type="checkbox" class="custom-control-input" id="switch1" name="vermastarde">
                    <label class="custom-control-label" for="switch1">Agregar a lista de ver mas tarde</label>
                    <button type="submit" class=" ml-1 btn btn-success btn-sm">Aplicar cambios</button>
                </div>
            </form>
            </p>
            <br>
            <%--            fin boton ver mas tarde--%>
            <c:if test="${rol == 'admin'}">
            <a href="<c:url value="/actualizar-serie/${serie.getId()}"/>" class="p-3 mb-2 bg-info text-white">Modificar Serie </a>
            <br> <br> <br> <br>
            </c:if>
        </div>
    </div>

    <div class="container" style="padding-left: 0px;">


    <c:forEach items="${capitulos}" var="capitulos">
        <div class="card mb-3 " style="max-width: 945px;max-height: 200px; min-height:200px; margin-bottom: 3px!important; background-color: rgba(0,0,0,0.5)">
            <div class="row no-gutters" style="height: 180px;">
                <div class="col-md-4" >
                    <a href="<c:url value="/reproducirCapitulo/${capitulos.getId()}"/>">
                        <img style="height:99%;"  src="../../image/${capitulos.getUbicacionArchivo()}" class="card-img" alt="...">
                    </a>
                </div>
                <div class="col-md-8">
                    <div class="card-body" style="height: 200px;">
                        <a  style="text-decoration:none; color:white;" href="<c:url value="/reproducirCapitulo/${capitulos.getId()}"/>">
                            <h4 class="card-text">${capitulos.getTitulo()}  </h4>
                            <p class="card-text">${capitulos.getDescripcion()}This is a wider card with supporting text below as a
                                natural lead-in to additional content. This content is a little bit longer</p>
                            <p class="card-text">
                                <small class="text-muted">Duracion : ${capitulos.getHoras()}:${capitulos.getMinutos()} hs</small>
                            </p>
                        </a>
                    </div>
                </div>

            </div>

        </div>
        <c:if test="${rol == 'admin'}">
            <a  href="<c:url value="/eliminarCapitulo/${capitulos.getId()}"/>" class="p-3 mb-2 bg-danger text-white" style="display: inline-block; margin-bottom: 15px!important;">
                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z"/>
                </svg>
            </a>
            <a  href="<c:url value="/actualizar-capitulo/${capitulos.getId()}"/>" class="p-3 mb-2 bg-warning text-white" style="display: inline-block; margin-bottom: 15px!important;">
                <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                    <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                </svg>
            </a>

        </c:if>
    </c:forEach>
   <c:if test="${rol == 'admin'}">
        <a  href="<c:url value="/crear-capitulo/${serie.getId()}"/>" class="p-3 mb-2 bg-success text-white"
            style="display: block;text-align: center;width: 30%; margin: auto;">
            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-plus-circle-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3v-3z"/>
            </svg>
        </a>
   </c:if>
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>


</div>


<!-- Codigo del reproductor  -->
<script src="https://cdn.plyr.io/3.6.2/plyr.polyfilled.js"></script>
<script>
    //Instranciamos el reproductor
    const player = new Plyr('#player');
</script>
<jsp:include page="includes/footer.jsp"></jsp:include>