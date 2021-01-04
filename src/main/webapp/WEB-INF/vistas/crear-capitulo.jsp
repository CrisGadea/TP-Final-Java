<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Crear Capitulo</title>

    <!-- meta tags para el tipado y la vista en celulares-->
    <jsp:include page="includes/importarArchivos.jsp"></jsp:include>
</head>
<body>
<div class="container">
    <jsp:include page="includes/header.jsp"></jsp:include>
    <div class="row align-items-start">
        <!--            Formulario            -->
        <div class="containerForm col-lg-6">

            <%--@elvariable id="capitulo" type="ar.edu.unlam.tallerweb1.modelo.Capitulo"--%>
<c:url value="/crear-capitulo/${idSerie}" var="url_crearCapitulo"/>
            <form:form action="${url_crearCapitulo}" method="POST" modelAttribute="capitulo" enctype="multipart/form-data">
                <h2 >Agregar Capitulo</h2>
                <%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
                <div class="form-group">
                    <form:label path="titulo" for="titulo" class="coloroAzul">Titulo</form:label>
                    <form:input path="titulo" type="text" class="form-control col" placeholder="Ingrese el titulo..." name="titulo" id="titulo"/>
                </div>
                <div class="form-group">
                    <form:label path="horas" for="horas" class="coloroAzul">Cantidad Hora</form:label>
                    <form:input path="horas" type="number" class="form-control col" placeholder="Cantidad Horas..." name="horas" id="horas"/>
                </div>
                <div class="form-group">
                    <form:label path="minutos" for="minutos" class="coloroAzul">Cantidad de Minutos</form:label>
                    <form:input path="minutos" type="number" class="form-control col" placeholder="Cantidad de Minutos..." name="minuto" id="minutos"/>
                </div>
                <div class="form-group">
                    <label for="portada">Cargar miniatura de la serie</label>
                    <input type="file" class="form-control" name="portada" id="portada" accept="image/*"/>
                </div>
                <div class="form-group">
                    <label for="contenido">Cargar Capitulo</label>
                    <input type="file" class="form-control" name="contenido" id="contenido" accept="video/*">
                </div>
                <div class="form-group">
                    <form:label path="descripcion" for="descripcion">Descripcion</form:label>
                    <form:textarea path="descripcion" placeholder="Ingrese su descripcion..." class="form-control col" name="descripcion" id="descripcion"></form:textarea>
                </div>
                <button type="submit" class="btn btn-primary">Crear Capitulo</button>
            </form:form>

        </div>
    </div>
</div>

<!-- bootstrap js y jquery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

<jsp:include page="includes/footer.jsp"></jsp:include>
