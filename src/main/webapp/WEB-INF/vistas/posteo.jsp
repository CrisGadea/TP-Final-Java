<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="includes/importarArchivos.jsp"></jsp:include>
</head>
<body>
<jsp:include page="includes/header.jsp"></jsp:include>
    <main class="container container bg-secondary text-white p-1">
        <div class="bg-dark p-2 d-flex align-items-center">
            <i class="fas fa-edit h2 p-2 text-warning"></i>
            <div>
                <p>Posteado el  ${posteo.fecha_creacion} por <strong>${posteo.creador.username}</strong> </p>
                <h2 class="text-warning">${posteo.titulo}</h2>
                <p>${posteo.genero}</p>
                <p>${posteo.informacion}</p>
                <c:if test="${rol == 'admin'}">
                    <a href="<c:url value="/ver-reportes/${posteo.getId()}"/>"> Ver reportes</a>
                </c:if>
            </div>
            <div style="display: inline-block;margin-left: auto; ">
                <c:if test="${rol == 'admin'}">
                    <a  href="<c:url value="/crear-reporte/${posteo.getId()}"/>" class="p-3 mb-2 bg-warning" style="display: block; margin-bottom: 5px!important;  margin-bottom: auto!important; color: white; background-color: #e81aff!important;">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-exclamation-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                            <path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0zM7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 4.995z"/>
                        </svg>
                    </a>
                    <a  href="<c:url value="/actualizar-posteo/${posteo.getId()}"/>" class="p-3 mb-2 bg-warning text-white" style="display: block; margin-bottom: 5px!important;  margin-bottom: auto!important;">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                            <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                        </svg>
                    </a>
                    <a  href="<c:url value="/eliminarPosteo/${posteo.getId()}"/>" class="p-3 mb-2 bg-danger text-white" style="display: block; margin-bottom: 5px!important; margin-left: auto; margin-bottom: auto!important;">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z"/>
                        </svg>
                    </a>
                </c:if>
            </div>

        </div>
        <div class="dropdown-divider"></div>
        <c:forEach items="${mensajes}" var="mensaje">
            <div class="bg-dark p-2 mb-1 d-flex align-items-center">
                <i class="fas fa-comments h3 p-2 text-warning"></i>
                <div>
                    <p><strong class="mr-2">${mensaje.creador.username}</strong> ${mensaje.fecha_creacion}</p>
                    <p>${mensaje.mensaje}</p>
                </div>
                <div style="display: inline-block;margin-left: auto; ">

                    <a  href="<c:url value="/eliminarMensaje/${mensaje.getId()}"/>" class="p-3 mb-2 bg-danger text-white" style="display: block; margin-bottom: 5px!important; margin-left: auto; margin-bottom: auto!important;">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z"/>
                        </svg>
                    </a>
                    <a  href="<c:url value="/actualizar-mensaje/${mensaje.getId()}"/>" class="p-3 mb-2 bg-warning text-white" style="display: block; margin-bottom: 5px!important;  margin-bottom: auto!important;">
                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                            <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                            <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                        </svg>
                    </a>
                </div>

            </div>
        </c:forEach>
        <form action="../crear-mensaje/${posteo.id}" method="post">
            <textarea type="text" placeholder="Comentar..." name="comentario" class="bg-dark p-2 my-1 w-100 text-white"></textarea>
            <input type="submit" value="Comentar" class="btn btn-warning">
        </form>
    </main>

<jsp:include page="includes/footer.jsp"></jsp:include>
</body>
</html>
