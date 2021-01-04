
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Actualizar Capitulo</title>
    <!-- meta tags para el tipado y la vista en celulares-->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- bootstrap css -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/estilo.css"/>
</head>
<body>
<div class="container">
    <div class="row align-items-start">

        ${ capitulo.getSerie().getTitulo()}
        <!--            Formulario            -->
        <div class="containerForm col-lg-6">

            <%--@elvariable id="capitulo" type="ar.edu.unlam.tallerweb1.modelo.Capitulo"--%>
            <form:form action="../actualizar-capitulo/${capitulo.getId()}" method="POST" modelAttribute="capitulo" enctype="multipart/form-data">
                <h2 >Actualizar Capitulo</h2>
                <%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
                <div class="form-group">
                    <form:label path="titulo" for="titulo" class="coloroAzul">Titulo</form:label>
                    <form:input path="titulo" type="text" class="form-control col" placeholder="Ingrese el titulo..." name="titulo" id="titulo" value="${ capitulo.getTitulo()}"/>
                </div>
                <div class="form-group">
                    <form:label path="horas" for="horas" class="coloroAzul">Cantidad Hora</form:label>
                    <form:input path="horas" type="number" class="form-control col" placeholder="Cantidad Horas..." name="horas" id="horas" value="${ capitulo.getHoras()}"/>
                </div>
                <div class="form-group">
                    <form:label path="minutos" for="minutos" class="coloroAzul">Cantidad de Minutos</form:label>
                    <form:input path="minutos" type="number" class="form-control col" placeholder="Cantidad de Minutos..." name="minuto" id="minutos" value="${ capitulo.getMinutos()}"/>
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
                    <form:textarea path="descripcion" placeholder="Ingrese su descripcion..." class="form-control col" name="descripcion" id="descripcion" value="${ capitulo.getDescripcion()}"></form:textarea>
                </div>
                <button type="submit" class="btn btn-primary">Actualizar Capitulo</button>
            </form:form>

        </div>

    </div>
</div>
</body>
<!-- bootstrap js y jquery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

<jsp:include page="includes/footer.jsp"></jsp:include>
