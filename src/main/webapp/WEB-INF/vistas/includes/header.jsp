<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark">
        <!-- Logo -->
        <c:url value="/home" var="url_home" />
        <%--<a class="navbar-brand logotipo" href="${url_home}"><h2>Magio's TV</h2></a>--%>
        <a class="navbar-brand logotipo" href="<c:url value="/home"/>"><h2>Magio's TV</h2></a>

        <!-- Menu -->

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${url_home}">Inicio <span class="sr-only">(current)</span></a>
                </li>
                <c:if test="${rol == 'admin'}">
                    <li class="nav-item active">
                        <a class="nav-link" href="<c:url value="/crear-pelicula"/>">Crear pelicula</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="<c:url value="/crear-serie"/>">Crear serie</a>
                    </li>
                <!--li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-light" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Admin
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">

                        <a class="dropdown-item" href="<c:url value="/crear-pelicula"/>">Crear pelicula</a>

                        <a class="dropdown-item" href="<c:url value="/crear-serie"/>">Crear serie</a>
                    </div>
                </li-->
                </c:if>
                <!--li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-light" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Cuenta
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <c:url value="/perfil" var="url_perfil"/>
                        <a class="dropdown-item" href="${url_perfil}">Perfil</a>
                        <a class="dropdown-item" href="<c:url value="/peliculas-vistas"/>">Contenido visto</a>
                        <a class="dropdown-item" href="<c:url value="/ver-mas-tarde"/>">Ver lista Mas Tarde</a>
                        <a class="dropdown-item" href="<c:url value="/configurar-cuenta"/>">Configuracion</a>
                        <div class="dropdown-divider"></div>

                        <a class="dropdown-item" href="<c:url value="/cerrar-sesion"/>">Cerrar sesion</a>
                    </div>
                </li-->
                <li class="nav-item active">
                    <a class="nav-link" href="${url_perfil}">Perfil</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/foro"/>">Foro</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/peliculas-vistas"/>">Contenido visto</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/ver-mas-tarde"/>">Ver lista Mas Tarde</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/configurar-cuenta"/>">Configuracion</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/cerrar-sesion"/>">Cerrar sesion</a>
                </li>
            </ul>
        </div>

        <!-- Formulario de busqueda -->
        <form action="<c:url value="/buscar"/>" method="post">
            <div class="row">
                <div class="form-group">
                    <input type="text" name="titulo" class="form-control col" placeholder="Buscar...">
                </div>
                <div>
                    <select name="genero" id="genero" class="form-control col">
                        <option value="" selected>Todas</option>
                        <option value="Drama">Drama</option>
                        <option value="Accion">Accion</option>
                        <option value="Suspenso">Suspenso</option>
                        <option value="Ciencia ficcion">Ciencia ficcion</option>
                        <option value="Comedia">Comedia</option>
                        <option value="Aventura">Aventura</option>
                        <option value="Terror">Terror</option>
                    </select>
                </div>
                <div>
                    <select name="valor" id="valor" class="form-control col">
                        <option value="" selected>Todas</option>
                        <option value="1">1 estrella</option>
                        <option value="2">2 estrellas</option>
                        <option value="3">3 estrellas</option>
                        <option value="4">4 estrellas</option>
                        <option value="5">5 estrellas</option>
                    </select>
                </div>
            </div>
            <div>
                <div class="form-check form-check-inline">
                    <input id="sinOrden" type="radio" name="order" value="" checked class="form-check-input">
                    <label for="sinOrden" class="form-check-label text-light">Sin orden</label>
                </div>
                <div class="form-check form-check-inline">
                    <input id="asc" type="radio" name="order" value="desc" class="form-check-input">
                    <label for="asc" class="form-check-label text-light">Mas nuevas</label>
                </div>
                <div class="form-check form-check-inline">
                    <input id="desc" type="radio" name="order" value="asc" class="form-check-input">
                    <label for="desc" class="form-check-label text-light">Mas viejas</label>
                </div><br>
                <div class="form-check form-check-inline">
                    <input id="visualAsc" type="radio" name="order" value="visualAsc" class="form-check-input">
                    <label for="visualAsc" class="form-check-label text-light">Mas visualizaciones</label>
                </div>
                <div class="form-check form-check-inline">
                    <input id="visualDesc" type="radio" name="order" value="visualDesc" class="form-check-input">
                    <label for="visualDesc" class="form-check-label text-light">Menos visualizaciones</label>
                </div>
                <button type="submit" class="btn btn-warning">Buscar</button>
            </div>
        </form>
    </nav>
</header>