<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registro</title>

    <link rel="stylesheet" href="css/registro.css"/>

    <jsp:include page="includes/importarArchivos.jsp"></jsp:include>
</head>
<body>
    <%--@elvariable id="usuario" type="ar.edu.unlam.tallerweb1.modelo.Usuario"&ndash;%&gt;--%>
    <form:form action="validar-registro" method="POST" modelAttribute="usuario" class="my-form">
        <div class="container">
            <h1>¡Registrate Ahora!</h1>
            <ul>
                <li>
                    <div class="grid grid-2">
                        <form:input path="username" id="username" type="text" class="form-control" required="required" placeholder="Username"/>
                        <form:input path="password" type="password" id="password" class="form-control" placeholder="Password" required="required"/>
                    </div>
                </li>
                <li>
                    <div class="grid grid-2">
                        <form:input path="email" id="email" type="email" class="form-control" placeholder="Email" required="required"/>
                        <form:input path="tel" id="tel" type="tel" class="form-control" placeholder="Phone"/>
                    </div>
                </li>

                <li>
                    <input type="checkbox" id="terms">
                    <label for="terms">I have read and agreed with <a href="">the terms and conditions.</a></label>
                </li>
                <li>
                    <div class="grid grid-3">
                        <div class="required-msg">REQUIRED FIELDS</div>
                <button class="btn-grid" type="submit" disabled>
                    <span class="back">
                      <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/162656/email-icon.svg" alt="">
                    </span>
                    <span class="front">SUBMIT</span>
                </button>
                <button class="btn-grid" type="reset" disabled>
                <span class="back">
                  <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/162656/eraser-icon.svg" alt="">
                </span>
                            <span class="front">RESET</span>
                        </button>
                    </div>
                </li>
            </ul>
        </div>
    </form:form>
    <%--Bloque que es visible si el elemento error no esta vacio	--%>
    <c:if test="${not empty error}">
    <h4 class="text-danger">${error}</h4>
    <br>
    </c:if>
    <script src="js/registro.js"></script>
<jsp:include page="includes/footer.jsp"></jsp:include>