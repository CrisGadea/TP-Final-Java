<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <title>Actualizar Pelicula</title>
    <jsp:include page="includes/importarArchivos.jsp"></jsp:include>
</head>
<body>
<jsp:include page="includes/header.jsp"></jsp:include>
<div class="container">
    <div class="row align-items-start">
        <!--            Formulario            -->
        <div class="containerForm col-lg-6">
            <%--@elvariable id="pelicula" type="ar.edu.unlam.tallerweb1.modelo.Pelicula"--%>
            <form:form action="../actualizar-pelicula/${ pelicula.getId() }" method="POST" modelAttribute="pelicula" enctype="multipart/form-data">
                <h2 >Actualizar pelicula</h2>
                <%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
                <div class="form-group">
                    <form:label path="titulo" for="titulo" class="coloroAzul">Titulo</form:label>
                    <form:input path="titulo" type="text" class="form-control col" placeholder="Ingrese el titulo..." name="titulo" id="titulo" value="${ pelicula.getTitulo() }"/>
                </div>
                <div class="form-group">
                        <%-- No lo mandamos como fecha_estreno porque el modelo no lo reconoce como un objeto Date y tira error 400 bad request--%>
                    <label  for="fecha-estreno">Fecha de estreno</label>
                    <input  type="date" class="form-control" placeholder="Ingrese su fecha de estreno..." name="fecha-estreno" id="fecha-estreno"/>
                </div>
                <div class="form-group">
                    <form:label path="genero" for="genero" class="coloroAzul">Genero</form:label>
                    <form:select path="genero" id="genero" class="form-control col">
                        <form:option value="Drama">Drama</form:option>
                        <form:option value="Accion">Accion</form:option>
                        <form:option value="Suspenso">Suspenso</form:option>
                        <form:option value="Ciencia ficcion">Ciencia ficcion</form:option>
                        <form:option value="Comedia">Comedia</form:option>
                        <form:option value="Aventura">Aventura</form:option>
                        <form:option value="Terror">Terror</form:option>
                    </form:select>
                </div>
                <div class="form-group">
                    <label for="portada">Cambiar imagen de la portada</label>
                    <input type="file" class="form-control" name="portada" id="portada" accept="image/*"/>
                </div>
                <div class="form-group">
                    <label for="contenido">Cargar pelicula</label>
                    <input type="file" class="form-control" name="contenido" id="contenido" accept="video/*">
                </div>
                <div class="form-group">
                    <form:label path="descripcion" for="descripcion">Sinopsis</form:label>
                    <form:textarea path="descripcion" placeholder="Ingrese su descripcion..." class="form-control col" name="descripcion" id="descripcion" value="${ pelicula.getDescripcion() }"></form:textarea>
                </div>
                <button type="submit" class="btn btn-primary">Actualizar</button>
            </form:form>

        </div>

        <!--            Presentacion            -->
        <div class="containerPresentacion col-lg-6">
            <div class="divImagen">
                <img src="../image/${ pelicula.getUbicacionArchivo() }" alt="Portada" id="portadaPresentacion">
            </div>
            <div>
                <h3 id="tituloPresentacion" class="letraAmarilla">${ pelicula.getTitulo() }</h3>
                <p>Fecha de estreno: <span  id="fecha-estrenoPresentacion">${ pelicula.getFecha_estreno() }</span></p>
                <p>Genero: <span id="generoPresentacion">${ pelicula.getGenero() }</span></p>
                <p>Sinopsis:
                    <br />
                    <span  id="descripcionPresentacion">${ pelicula.getDescripcion() }</span>
                </p>
            </div>
        </div>
    </div>
</div>


<!--            codigo Javascript            -->
<script src="../js/codigojs.js">
</script>
<script>
    ponemosALaEscuchaLosInput();
    funcionParaVerLaPortadaSubidaPorElInput();
</script>

<jsp:include page="includes/footer.jsp"></jsp:include>
