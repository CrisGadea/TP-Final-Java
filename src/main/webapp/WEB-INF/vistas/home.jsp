<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<link href="https://fonts.googleapis.com/css?family=Roboto:300&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="css/slider.css">
		<jsp:include page="includes/importarArchivos.jsp"></jsp:include>
		<title>Home</title>
	</head>
	<body>
	<jsp:include page="includes/header.jsp"></jsp:include>
	<div class="container">
	<div class="sliderPrincipalContenedor" style="margin-bottom: 20px;">
		<div class="slider-contenedor">

				<section class="contenido-slider">
					<a style="width: 100%;height:100%;"href="reproducir/serie/${ultimasCincoSeries.get(0).getId()}" >
						<img src="image/${ultimasCincoSeries.get(0).getUbicacionArchivo()}" alt="" style="z-index: 1;height:100%;
                        width: 100%;background-position: center; background-repeat: no-repeat; background-size: cover; position: relative!important;">

					</a>
				</section>
			<section class="contenido-slider">
				<a style="width: 100%;height:100%;"href="reproducir/serie/${ultimasCincoSeries.get(1).getId()}" >
					<img src="image/${ultimasCincoSeries.get(1).getUbicacionArchivo()}" alt="" style="z-index: 1;height:100%;
                        width: 100%;background-position: center; background-repeat: no-repeat; background-size: cover; position: relative!important;">
				</a>
			</section>

			<section class="contenido-slider">
				<a style="width: 100%;height:100%;"href="reproducir/serie/${ultimasCincoSeries.get(2).getId()}" >
					<img src="image/${ultimasCincoSeries.get(2).getUbicacionArchivo()}" alt="" style="z-index: 1;height:100%;
                        width: 100%;background-position: center; background-repeat: no-repeat; background-size: cover; position: relative!important;">
				</a>
			</section>
			<section class="contenido-slider">
				<a style="width: 100%;height:100%;"href="reproducir/serie/${ultimasCincoSeries.get(3).getId()}" >
					<img src="image/${ultimasCincoSeries.get(3).getUbicacionArchivo()}" alt="" style="z-index: 1;height:100%;
                        width: 100%;background-position: center; background-repeat: no-repeat; background-size: cover; position: relative!important;">
				</a>
			</section>
			<section class="contenido-slider">
				<a style="width: 100%;height:100%;"href="reproducir/serie/${ultimasCincoSeries.get(4).getId()}" >
					<img src="image/${ultimasCincoSeries.get(4).getUbicacionArchivo()}" alt="" style="z-index: 1;height:100%;
                        width: 100%;background-position: center; background-repeat: no-repeat; background-size: cover; position: relative!important;">
				</a>
			</section>

		</div>
	</div>
	</div>
	<div class="pelicula-principal">
<c:if test="${suscripcion == false}">
			<div class="row">
				<div class="col-sm-4">
					<div class="card text-white bg-dark">
						<div class="card-body">
							<h5 class="card-title">Suscripción mensual</h5>
							<p class="card-text">$500</p>
							<a href="suscribirse/1/500" class="btn btn-primary">Contratar</a>
						</div>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="card text-white bg-dark">
						<div class="card-body">
							<h5 class="card-title">Suscripción semestral</h5>
							<p class="card-text">$2300</p>
							<a href="suscribirse/6/2300" class="btn btn-primary">Contratar</a>
						</div>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="card text-white bg-dark">
						<div class="card-body">
							<h5 class="card-title">Suscripción anual</h5>
							<p class="card-text">$4500</p>
							<a href="suscribirse/12/4500" class="btn btn-primary">Contratar</a>
						</div>
					</div>
				</div>
			</div>
</c:if>
			<div class="contenedor">
				<h3 class="titulo">Peliculas</h3>
				<div class="contenedor-carousel">
					<div class="d-flex justify-content-start flex-wrap">
						<c:forEach items="${peliculas}" var="pelicula">
							<div class="contenido mx-2 my-4">
								<a href="reproducir/pelicula/${pelicula.getId()}" class="thumbnail">
									<img src="image/${pelicula.getUbicacionArchivo()}"
										 alt="${pelicula.getUbicacionArchivo()}"
										class="imagenPortadaHome"></a>
								<a href="reproducir/pelicula/${pelicula.getId()}" class="tituloPelicula">${pelicula.getTitulo()}</a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div><br><br><br>
			<div class="contenedor">
				<h3 class="titulo">Series</h3>
				<div class="contenedor-carousel">
					<div class="d-flex justify-content-start flex-wrap">
						<c:forEach items="${series}" var="serie">
							<div class="contenido mx-2 my-4">
								<a href="reproducir/serie/${serie.getId()}" class="thumbnail">
									<img src="image/${serie.getUbicacionArchivo()}"
										 alt="${serie.getUbicacionArchivo()}"
										 class="imagenPortadaHome"></a>
								<a href="reproducir/serie/${serie.getId()}" class="tituloPelicula">${serie.getTitulo()}</a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
	<script src="js/slider.js"></script>
	<!-- bootstrap js y jquery -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

	<jsp:include page="includes/footer.jsp"></jsp:include>
</body>