<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="includes/importarArchivos.jsp"></jsp:include>
</head>
<body>
<jsp:include page="includes/header.jsp"></jsp:include>
<div class="container">
    <div class="row justify-content-md-center">
        <c:forEach items="${perfiles}" var="perfiles">
            <div class="col col-lg-3 text-center " style="display: inline-block;">
                <div class="rounded-circle" style="background-color: palevioletred;height: 150px; width: 150px;margin: auto;">
                    <img style="height:100%; width: 100%;background-position: center; background-repeat: no-repeat; background-size: cover;"
                         src="image/${perfiles.getUbicacionArchivo()}" class="rounded-circle" alt="...">
                </div>

                <h4 class="text-white text-center">${perfiles.getNombre()}</h4>
                <a href="perfil/eliminar/${perfiles.getId()}" class="btn btn-danger">Eliminar</a>
            </div>
        </c:forEach>
        <a  href="<c:url value="/crearPerfil/${usuario.getId()}"/>" class="p-3 mb-2 bg-success text-white rounded-circle"
            style="display:inline-block;text-align: center;height: 150px; width: 150px;">
            <svg style=" width: 100%;height: 100%;" viewBox="0 0 16 16" class="bi bi-plus-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" class="bg-success" />
                <path fill-rule="evenodd" d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
            </svg>
        </a>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
<jsp:include page="includes/footer.jsp"></jsp:include>

</body>

</html>