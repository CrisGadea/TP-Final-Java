<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <title>Actualizar Serie</title>

    <jsp:include page="includes/importarArchivos.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="includes/header.jsp"></jsp:include>
    <div class="container">
        <div class="row align-items-start">
            <!--            Formulario            -->
            <div class="containerForm col-lg-6">
                <%--@elvariable id="serie" type="ar.edu.unlam.tallerweb1.modelo.Serie"--%>
                <form:form action="../actualizar-serie/${ serie.getId() }" method="POST" modelAttribute="serie" enctype="multipart/form-data">
                    <h2 >Actualizar serie</h2>
                    <%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
                    <div class="form-group">
                        <form:label path="titulo" for="titulo" class="coloroAzul">Titulo</form:label>
                        <form:input path="titulo" type="text" class="form-control col" placeholder="Ingrese el titulo..." name="titulo" id="titulo" value="${ serie.getTitulo() }"/>
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
                        <label for="contenido">Cargar serie</label>
                        <input type="file" class="form-control" name="contenido" id="contenido" accept="video/*">
                    </div>
                    <div class="form-group">
                        <form:label path="descripcion" for="descripcion">Sinopsis</form:label>
                        <form:textarea path="descripcion" placeholder="Ingrese su descripcion..." class="form-control col" name="descripcion" id="descripcion" value="${ serie.getDescripcion() }"></form:textarea>
                    </div>

                    <button type="submit" class="btn btn-primary">Actualizar</button>
                </form:form>

                <!--            Presentacion            -->
                <div class="containerPresentacion col-lg-6">
                    <div class="divImagen">
                        <img src="../image/${ serie.getUbicacionArchivo() }" alt="Portada" id="portadaPresentacion">
                    </div>
                    <div>
                        <h3 id="tituloPresentacion" class="letraAmarilla">${ serie.getTitulo() }</h3>
                        <p>Fecha de estreno: <span  id="fecha-estrenoPresentacion">${ serie.getFecha_estreno() }</span></p>
                        <p>Genero: <span id="generoPresentacion">${ serie.getGenero() }</span></p>
                        <p>Sinopsis:
                            <br />
                            <span  id="descripcionPresentacion">${ serie.getDescripcion() }</span>
                        </p>

                        <p>Temporadas:
                            <div class="d-flex justify-content-start flex-wrap">
                                <c:forEach items="${temporada}" var="temporada">
                                <div class="contenido mx-2 my-4">
                        <p> ${temporada.getTitulo()}</p>
                    </div>

                    </c:forEach>
                </div>
                </p>
            </div>
        </div>
    </div>

</body>


<!--            codigo Javascript            -->
<script src="../js/codigojs.js">
</script>
<script>
    ponemosALaEscuchaLosInput();
    funcionParaVerLaPortadaSubidaPorElInput();
</script>

<jsp:include page="includes/footer.jsp"></jsp:include>

