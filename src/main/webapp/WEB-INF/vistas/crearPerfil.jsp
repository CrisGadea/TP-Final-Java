<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crear Perfil</title>
    <!-- Estilos del Reproductor  -->
    <link rel="stylesheet" href="https://cdn.plyr.io/3.6.2/plyr.css" />
    <link rel="stylesheet" href="<c:url value="/css/estrellas.css"/>">
    <jsp:include page="includes/importarArchivos.jsp"></jsp:include>
</head>
<body>
<div class="containerForm col-lg-6">

    <%--@elvariable id="perfil" type="ar.edu.unlam.tallerweb1.modelo.Perfil"--%>
    <c:url value="/crearPerfil/${idUsuario}" var="url_crearPerfil"/>
    <form:form action="${url_crearPerfil}" method="POST" modelAttribute="perfil" enctype="multipart/form-data">
    <h2 >Agregar perfil</h2>
        <%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
    <div class="form-group">
        <label path="nombre" for="nombre" class="coloroAzul">Nombre</label>
        <input path="nombre" type="text" class="form-control col" placeholder="Ingrese el nombre..." name="nombre" id="nombre"/>
    </div>

    <div class="form-group">
        <label for="fotoPerfil">Cargar imagen de perfil</label>
        <input type="file" class="form-control" name="fotoPerfil" id="fotoPerfil" accept="image/*"/>
    </div>

    <button type="submit" class="btn btn-primary">Crear perfil</button>
    </form:form>
        <jsp:include page="includes/footer.jsp"></jsp:include>
</html>
