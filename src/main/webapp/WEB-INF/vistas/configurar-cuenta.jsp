<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Configurar cuenta</title>
    <jsp:include page="includes/importarArchivos.jsp"></jsp:include>
</head>
<body>
<jsp:include page="includes/header.jsp"></jsp:include>
<div class="container">
<main class="container">
    <div class="row">
        <div class="containerForm col-lg-6">
            <form action="/proyecto_limpio_spring_war_exploded/modificar-cuenta" method="POST" >
                <h2 style="color: #2b669a">Tu Cuenta</h2>
                <div class="dropdown-divider"></div>
                <div class="form-group">
                    <input name="id" type="hidden" class="form-control" value="${idDeUsuario}" readonly/>
                </div>
                <div class="form-group">
                    <label class="text-dark">Usuario</label>
                    <input name="username"  type="text" class="form-control" placeholder="Ingrese nick nuevo" value="${nickDeUsuario}"/>
                </div>
                <div class="form-group">
                    <label class="text-dark">Password</label>
                    <input name="password" type="password"  class="form-control" placeholder="Ingrese contraseña nueva" value="${password}"/>
                </div>
                <div class="form-group">
                    <label class="text-dark">Email</label>
                    <input name="email" type="email" class="form-control" placeholder="Ingrese email nuevo" value="${emailDeUsuario}"/>
                </div>
                <div class="form-group">
                    <label class="text-dark">Telefono</label>
                    <input name="tel"  type="tel" class="form-control" placeholder="Inrgese telefono nuevo" value="${telefonoDeUsuario}"/>
                </div>
                <input type="submit" class="btn btn-info" value="Editar datos">
            </form>
        </div>
        <div class="containerPresentacion col-lg-6 ">
            <h1>Datos del usuario</h1>
            <h4>
                Usuario: ${nickDeUsuario}
            </h4>
            <h4>
                Contraseña: ${password}
            </h4>
            <h4>
                Email: ${emailDeUsuario}
            </h4>
            <h4>
                Telefono: ${telefonoDeUsuario}
            </h4>
        </div>
    </div>
</main>

<script src="js/registro.js"></script>
<script src="js/codigojs.js"></script>
<script>
    ponemosALaEscuchaLosInput();
    funcionParaVerLaPortadaSubidaPorElInput();
</script>

<jsp:include page="includes/footer.jsp"></jsp:include>
