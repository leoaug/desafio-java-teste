<!DOCTYPE html>
<html lang="pt-br">
<head>
	<title>Pagina de Pesquisa</title>
</head>
<div class="container mt-5">
  <fieldset class="border p-4">
     <legend class="w-auto">Filtros</legend>	


	    <!-- Formul�rio de consulta -->
	    	 <div class="row mb-4">
               	 <div class="col-md-3">
               	 	 <label for="nome" style="float: lef;">Nome do Projeto:</label>
	            	 <input type="text" class="form-control" id="nome" placeholder="Nome do projeto" width="120px">
               	 </div>
               	 
               	 <div class="col-md-3">
              	 	<label for="dataInicio" style="float: lef;">Data de In�cio:</label>
            		<input type="date" class="form-control" id="dataInicio" width="50px">
               	 </div>
               	 
               	 <div class="col-md-3">
               	 	<label for="risco" style="float: lef;">Risco:</label>
		            <select class="form-select" id="risco">
		                <option value="">Selecione o risco</option>
		                <option value="1">Baixo</option>
		                <option value="2">M�dio</option>
		                <option value="3">Alto</option>
		            </select>
               	 </div>
               	 
               	  <div class="col-md-3">
               	 		<label for="status" style="float: lef;">Status:</label>
			            <select class="form-select" id="status">
			            	 <option value="">Selecione o status</option>
			                 <option value="1">Em An�lise</option>
			                 <option value="2">An�lise Realizada</option>
			                 <option value="3">An�lise Aprovada</option>
			                 <option value="4">Iniciado</option>
			                 <option value="5">Planejado</option>
			                 <option value="6">Em Andamento</option>
			                 <option value="7">Encerrado</option>
			                 <option value="8">Cancelado</option>
			            </select>
               	  </div>
               
	         </div>
	         
	    
	    	<div class="modal-footer" style="float: left;">
	        	<button id="idBotaoPesquisar" onclick="$('#loadingDialog')[0].showModal();" class="btn btn-primary">Consultar</button>
	   			&nbsp;
		    	<button id="idBotaoPesquisar" onclick="$('#loadingDialog')[0].showModal();window.location.href = '/projeto/index'" class="btn btn-secondary">Resetar</button>
		    </div>
    	<hr>
	</fieldset>
</div>

<script>
document.getElementById('idBotaoPesquisar').addEventListener('click', function () {
   

 // Par�metros passados como um array de objetos com chave (key) e valor (value)
    let params = [
        { key: "nome", value: document.getElementById('nome').value },
        { key: "dataInicio", value: document.getElementById('dataInicio').value },
        { key: "risco", value: document.getElementById('risco').value },
        { key: "status", value: document.getElementById('status').value }
    ];
    
    const url = buildUrl("/projeto/consultar", params);
   
    // Constr�i a URL com os path variables
    window.location.href = url;
  
   
});


</script>

</body>
</html>
