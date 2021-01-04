<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="includes/importarArchivos.jsp"></jsp:include>
</head>
<body>
<jsp:include page="includes/header.jsp"></jsp:include>
<main class="container">
    <h1>Foro</h1>

    <h3>Ultimos posteos</h3>
    <!-- <a href="crear-posteo" class="btn btn-warning">Agregar post</a>-->

     <!-- Button trigger modal -->
    <a  href="<c:url value="/crear-posteo"/>" class="btn btn-warning btn-lg cta" id="myBtn">Agregar Post</a>

   <div class="container bg-secondary my-4 p-2">
            <c:forEach items="${posteos}" var="posteo">
                <a href="ver-posteo/${posteo.id}" class="bg-dark mb-2 p-4 d-block text-white sin-estilo">
                    <p>Posteado el  ${posteo.fecha_creacion} por <strong>${posteo.creador.username}</strong> </p>
                    <h2 class="text-warning">${posteo.titulo}</h2>
                    <div class="">
                        <strong>
                            <p class="d-inline mr-2">100 Comentarios</p>
                            <p class="d-inline mr-2">Ultimo comentario el ${posteo.fecha_creacion}</p>
                        </strong>
                    </div>
                </a>
            </c:forEach>
    </div>

</main>

<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>
