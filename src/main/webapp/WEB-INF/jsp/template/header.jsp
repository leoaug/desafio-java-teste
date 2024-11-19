<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt_br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Desafio Java</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="<c:url value="/static/js/desafioJava.js"/>"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>	
    <link href="<c:url value="/static/node_modules/bootstrap/dist/css/bootstrap.min.css"/>"
          rel="stylesheet">
	<link href="<c:url value="/static/css/desafioJava.css"/>"
          rel="stylesheet">
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
	
	<script type="text/javascript">

		function abrirModal(idLink){
			sessionStorage.setItem("idLinkSession",idLink);
			$('#loadingDialog')[0].showModal();
		}

		function carregarCorVisited(){
			 // Get the element by ID
			removeAllVisitedClasses();
            const link = document.getElementById(sessionStorage.getItem("idLinkSession"));
			if(link){
				 // Add the "visited" CSS class
	            link.classList.add("visited");
			}
           
		}

		function removeAllVisitedClasses() {
			
            // Get all elements with the "visited" class
            const visitedElements = document.querySelectorAll('.visited');

            if(visitedElements){
            	// Remove the "visited" class from each element
                visitedElements.forEach(element => {
                    element.classList.remove('visited');
                });
            }
            
        }

	</script>

</head>
<body onload="carregarCorVisited()">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
	    <div class="container-fluid">
	        <a class="navbar-brand" onclick="$('#loadingDialog')[0].showModal()" href="/">Desafio Java</a>
	        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
	            <span class="navbar-toggler-icon"></span>
	        </button>
	        <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
	            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	                <li class="nav-item custom">
	                    <a id="linkProjetos" class="nav-link active" aria-current="page" onclick="abrirModal(this.id)" href="/projeto/index">Manter Projeto</a>
	                </li>
	                <li class="nav-item custom">
	                    <a id="linkMembros" class="nav-link active" aria-current="page" onclick="abrirModal(this.id)" href="/membro/index">Manter Membro</a>
	                </li>
	                
	                <li class="nav-item custom">
	                    <a id="linkPessoas" class="nav-link active" aria-current="page" onclick="abrirModal(this.id)" href="/pessoa/index">Manter Gerente/Funcionário</a>
	                </li>
	                
	            </ul>
	            <form class="d-flex">
	                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
	                <button class="btn btn-outline-success" type="submit">Search</button>
	            </form>
	        </div>
	    </div>
	</nav>
	
	<!-- Dialog para mostrar erros -->
    <dialog id="errorDialog">
        <div id="errorMessage">Ocorreu um erro inesperado.</div>
        <button id="closeDialog" onclick="$('#errorDialog')[0].close()">Fechar</button>
    </dialog>
	
	<dialog id="loadingDialog">Carregando...</dialog>
	
	<br/>

