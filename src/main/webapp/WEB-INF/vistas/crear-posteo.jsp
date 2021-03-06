<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%--    <title>Title</title>--%>
<%--    <!-- meta tags para el tipado y la vista en celulares-->--%>
<%--    <meta charset="utf-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">--%>
<%--    <!-- bootstrap css -->--%>
<%--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">--%>
<%--    <link rel="stylesheet" href="../css/estilo.css"/>--%>
    <jsp:include page="includes/importarArchivos.jsp"></jsp:include>
</head>
<body>
<jsp:include page="includes/header.jsp"></jsp:include>
<div class="container">
    <div class="row align-items-start">
        <!--            Formulario            -->
        <div class="containerForm col-lg-6">

            <%--@elvariable id="posteo" type="ar.edu.unlam.tallerweb1.modelo.Posteo"--%>
            <form:form action="crear-posteo" method="POST" modelAttribute="posteo" >
                <h2 >Crear Posteo</h2>
                <%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
                <div class="form-group">
                    <form:label path="titulo" for="titulo" class="coloroAzul">Titulo</form:label>
                    <form:input path="titulo" type="text" class="form-control col" placeholder="Ingrese el titulo..." name="titulo" id="titulo" value="${ posteo.getTitulo()}"/>
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
                    <form:label path="informacion" for="informacion">Informacion</form:label>
                    <form:textarea path="informacion" placeholder="Ingrese su informacion..." class="form-control col" name="informacion" id="informacion" value="${ posteo.getInformacion()}"></form:textarea>
                </div>
                <button type="submit" class="btn btn-primary">Crear Posteo</button>
            </form:form>

        </div>
    </div>
</div>

<!-- bootstrap js y jquery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

</body>
</html>
