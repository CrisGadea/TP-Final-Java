<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="includes/importarArchivos.jsp"></jsp:include>
		<link rel="stylesheet" href="css/login.css"/>
	</head>
	<body>
		<div class="container">
			<section id="content">
				<form:form action="validar-login" method="POST" modelAttribute="usuario">
					<h1>Login Form</h1>
					<div>
						<form:input path="email" id="username" type="text" placeholder="Email" required="required"/>
					</div>
					<div>
						<form:input path="password" type="password" id="password" placeholder="Password" required="required"/>
					</div>
					<div>
						<input type="submit" value="Log in" />
						<a href="registro">Register</a>
					</div>
				</form:form><!-- form -->
				<%--Bloque que es visible si el elemento error no esta vacio	--%>
				<c:if test="${not empty error}">
					<h4 class="text-danger">${error}</h4>
					<br>
				</c:if>
			</section><!-- content -->
		</div><!-- container -->
<jsp:include page="includes/footer.jsp"></jsp:include>
