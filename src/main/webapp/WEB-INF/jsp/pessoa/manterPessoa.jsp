
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/template/header.jsp" %>
  <script>
        $(document).ready(function(){
            $('#orcamento').mask('R$ #.###.##0,00', {reverse: true});
            $('#cpf').mask('000.000.000-00');
            
        });
  </script>
<div class="container mt-6">
  
  <div class="card">
  	 <div class="card-body">
	    <h5 class="card-title">Gerente/Funcion�rio</h5>
	    
	    <button class="btn btn-success mb-3" data-toggle="modal" onclick="$('#modalNovoPessoa')[0].showModal()">Novo Gerente/Funcion�rio</button>
	
	    <table id="tabelapessoas" class="table table-bordered">
	        <thead>
	            <tr>
	                <th>Nome</th>
	                <th>Data Nascimento</th>
	                <th>CPF</th>
	                <th>Funcion�rio</th>               
	                <th>Gerente</th>
	                <th>A��es</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="pessoa" items="${pessoas}">
	                <tr>
	                    <td>${pessoa.nome}</td>
	                    <td>                    	
	                    	<fmt:formatDate value="${pessoa.dataNascimento}" pattern="dd/MM/yyyy" />
	                    </td>
	                    <td>                   	
	                    	${pessoa.cpf}
	                    </td>
	                    <td>                 		                    
	                    	<c:choose>
					            <c:when test="${pessoa.funcionario == true}">Sim</c:when>
					            <c:otherwise>N�o</c:otherwise>
	      					</c:choose>
	                    </td>
	                    <td>                 		                    
	                    	<c:choose>
					            <c:when test="${pessoa.gerente == true}">Sim</c:when>
					            <c:otherwise>N�o</c:otherwise>
	      					</c:choose>
	                    </td>	                         
	                    <td>
	                        <button class="btn btn-info btn-editar" data-id="${pessoa.id}">Editar</button>
	                        <c:if test="${!(Pessoa.status eq '4' || Pessoa.status eq '6' || Pessoa.status eq '7')}">                       
	                        	<button class="btn btn-danger btn-excluir" data-id="${pessoa.id}">Excluir</button>                      
	                        </c:if>
	                    </td>
	                </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	</div>
  </div>
</div>
	
<!-- Modal Novo Pessoa -->
		<dialog id="modalNovoPessoa" style="width:70%">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalNovoPessoaLabel">Novo Pessoa</h5>
	                <button type="button" class="close" onclick="$('#modalNovoPessoa')[0].close()" aria-label="Fechar">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	            </div>
	            <div class="modal-body">
	                <form id="formNovoPessoa">
	                	 
	                	 <input type="hidden" id="id" />
	                	
	                	 <div class="row mb-3">
			                <!-- Campo Nome -->
			                <div class="col-md-4">
			                    <label for="nome" class="form-label" style="float: left">Nome</label>
			                    <input type="text" class="form-control" id="nome" placeholder="Digite o nome">
			                </div>
			               
			                <!-- Campo Gerente -->
			                <div class="col-md-3" style="padding-top: 35px">
			                        <input class="form-check-input" type="checkbox" id="gerente">
			                        <label class="form-check-label" for="gerente">� gerente?</label>
			                    
			                </div>
			                 <!-- Campo Funcion�rio -->
			                <div class="col-md-3" style="padding-top: 35px">
			                    <input class="form-check-input" type="checkbox" id="funcionario">
			                     <label class="form-check-label" for="funcionario">� funcion�rio?</label>
			                </div>
			            </div>
			           
			            <div class="row mb-3">
			                <!-- Campo CPF -->
			                <div class="col-md-4">
			                    <label for="cpf" class="form-label" style="float: left">CPF</label>
			                    <input type="text" class="form-control" id="cpf" placeholder="Digite o CPF">
			                </div>
			                
			                 <!-- Campo Data de Nascimento -->
			                <div class="col-md-3">
			                    <label for="dataNascimento" class="form-label" style="float: left">Data de Nascimento</label>
			                    <input type="date" class="form-control" id="dataNascimento">
			                </div>
			            </div>
			           
	                </form>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-secondary" onclick="$('#modalNovoPessoa')[0].close()">Fechar</button>
	                <button type="button" class="btn btn-primary" id="salvarPessoa">Salvar</button>
	            </div>
	     </div>	
	</dialog>
	
	<script>
	    $(document).ready(function() {
	       
	        // Criar novo Pessoa
	        $('#salvarPessoa').click(function() {
	
	        	$('#loadingDialog')[0].showModal();
	            
	            var pessoa = {
					id: $('#id').val(),
	                nome: $('#nome').val(),
	                dataNascimento: $('#dataNascimento').val(),	                
	                cpf : $('#cpf').val(),
	                funcionario: $('#funcionario').is(':checked'),
	                gerente: $('#gerente').is(':checked')
	                                     
	            };

	            console.log(JSON.stringify(pessoa));
	            
	            $.ajax({
	                url: '/pessoa/salvar',
	                method: 'POST',
	                contentType: 'application/json',
	                data: JSON.stringify(pessoa),
	                success: function() {
	                	$('#modalNovoPessoa')[0].close();
	                    window.location.href = '/pessoa/index'; 
	                }
	            });
	        });
	
	        // Excluir Pessoa
	        $(document).on('click', '.btn-excluir', function() {
	        	$('#loadingDialog')[0].showModal();
	            var id = $(this).data('id');
	            $.ajax({
	                url: '/pessoa/excluir/' + id,
	                method: 'DELETE',
	                success: function() {
	                	window.location.href = '/pessoa/index'; 
	                },  
	                error: function (xhr, status, error) {
	                	// Captura o erro e exibe no dialog
	                	$('#loadingDialog')[0].close();
	                    const errorMessage = `Erro: ${xhr.status} - ${xhr.statusText}`;
	                    $('#errorMessage').text("Erro ao excluir, Pessoa j� vinculado ao membro");
	                    $('#errorDialog')[0].showModal();
	                    console.log(JSON.stringify(error));
	                },
	            });
	        });
	
	        // abrir modal de Editar Pessoa
	        $(document).on('click', '.btn-editar', function() {
	            var id = $(this).data('id');
	            $.ajax({
	                url: '/pessoa/editar/' + id,
	                method: 'GET',
	                success: function(pessoa) {
		                	
	                    // Aqui voc� pode preencher os campos de edi��o com os dados do Pessoa
	                    $('#id').val(pessoa.id);
	                    $('#nome').val(pessoa.nome);
	                    $('#dataNascimento').val(convertDateFormat(new Date(pessoa.dataNascimento).toLocaleDateString('pt-BR', {
	                	    day: '2-digit',
	                	    month: '2-digit',
	                	    year: 'numeric'
	                	})));	                   
	                    $('#cpf').val(pessoa.cpf);
	                    pessoa.funcionario === true ? $("#funcionario").prop("checked", true) : $("#funcionario").prop("checked", false);
	                    pessoa.gerente === true ? $("#gerente").prop("checked", true) : $("#gerente").prop("checked", false);
	                    
	                    $('#modalNovoPessoa')[0].showModal();
	                }
	            });
	        });
	    });
	</script>
  
<%@ include file="/WEB-INF/jsp/template/footer.jsp" %>