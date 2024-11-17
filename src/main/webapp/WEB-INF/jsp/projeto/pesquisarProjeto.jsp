<!DOCTYPE html>
<html lang="pt-br">
<head>
	<title>Pagina de Pesquisa</title>
</head>
<div class="container mt-5">
  <fieldset class="border p-4">
     <legend class="w-auto">Filtros</legend>	


	    <!-- Formulário de consulta -->
	    
	      	<div class="form-group-inline">
	            <label for="nome">Nome do Projeto:</label>
	            <input type="text" class="form-control" id="nome" placeholder="Nome do projeto" width="120px">
	        
	            <label for="dataInicio">Data de Início:</label>
	            <input type="date" class="form-control" id="dataInicio" width="50px">
	        	            
	        </div>
	
	       <div class="form-group-inline">
	        
	        	<label for="risco">Risco:</label>
	            <select class="form-control" id="risco">
	                <option value="">Selecione o risco</option>
	                <option value="1">Baixo</option>
	                <option value="2">Médio</option>
	                <option value="3">Alto</option>
	            </select>
	        
	            <label for="status">Status:</label>
	            <select class="form-control" id="status">
	                 <option value="1">Em Análise</option>
	                 <option value="2">Análise Realizada</option>
	                 <option value="3">Análise Aprovada</option>
	                 <option value="4">Iniciado</option>
	                 <option value="5">Planejado</option>
	                 <option value="6">Em Andamento</option>
	                 <option value="7">Encerrado</option>
	                 <option value="8">Cancelado</option>
	            </select>
	        </div>
	
	        <button id="idBotaoPesquisar" onclick="$('#loadingDialog')[0].showModal();" class="btn btn-primary">Consultar</button>
	   

    	<hr>
	</fieldset>
</div>

<script>
document.getElementById('idBotaoPesquisar').addEventListener('click', function () {
   

 // Parâmetros passados como um array de objetos com chave (key) e valor (value)
    let params = [
        { key: "nome", value: document.getElementById('nome').value },
        { key: "dataInicio", value: document.getElementById('dataInicio').value },
        { key: "risco", value: document.getElementById('risco').value },
        { key: "status", value: document.getElementById('status').value }
    ];
    
    const url = buildUrl("/projeto/consultar", params);
   
    // Constrói a URL com os path variables
    window.location.href = url;
  
   
});


</script>

</body>
</html>
