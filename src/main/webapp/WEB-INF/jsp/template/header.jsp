<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt_br">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Desafio Java</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>	
    <link href="<c:url value="/static/node_modules/bootstrap/dist/css/bootstrap.min.css"/>"
          rel="stylesheet">

</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
	    <div class="container-fluid">
	        <a class="navbar-brand" href="#">Projetos</a>
	        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
	            <span class="navbar-toggler-icon"></span>
	        </button>
	        <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
	            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	                <li class="nav-item">
	                    <a class="nav-link active" aria-current="page" href="/projeto/index">Manter Projetos</a>
	                </li>
	            </ul>
	            <form class="d-flex">
	                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
	                <button class="btn btn-outline-success" type="submit">Search</button>
	            </form>
	        </div>
	    </div>
	</nav>
