
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/template/header.jsp" %>
  <script>
        $(document).ready(function(){
            $('#orcamento').mask('R$ #.###.##0,00', {reverse: true});
        });
  </script>
<div class="container mt-6">
  
  <div class="card">
  	 <div class="card-body">
	    <h5 class="card-title">Manter Projeto</h5>
	    
	    <button class="btn btn-success mb-3" data-toggle="modal" id="novoProjeto">Novo Projeto</button>
	    	    
	    <!-- consulta de Projetos -->
		<%@ include file="/WEB-INF/jsp/projeto/pesquisarProjeto.jsp" %>
		
		<br/>
	    	
	    <table id="tabelaProjetos" class="table table-bordered table-hover table-striped">
	        <thead>
	            <tr>
	                <th>Nome</th>
	                <th>Data Início</th>
	                <th>Data Fim</th>
	                <th>Data Previsão</th>
	                <th>Orçamento</th>
	                <th>Gerente</th>
	                <th>Risco</th>
	                <th>Status</th>
	                <th>Descrição</th>
	                <th>Ações</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="projeto" items="${projetos}">
	                <tr>
	                    <td>${projeto.nome}</td>
	                    <td>                    	
	                    	<fmt:formatDate value="${projeto.dataInicio}" pattern="dd/MM/yyyy" />
	                    </td>
	                    <td>                   	
	                    	<fmt:formatDate value="${projeto.dataFim}" pattern="dd/MM/yyyy" />
	                    </td>
	                    <td>                 	
	                    	<fmt:formatDate value="${projeto.dataPrevisao}" pattern="dd/MM/yyyy" />
	                    </td>
	                    <td>                   	
	                    	<fmt:formatNumber value="${projeto.orcamento}" type="currency" currencySymbol="R$" />
	                    </td>
	                    <td>${projeto.gerente.nome}</td>
	                    <td>
	                   	   <c:choose>
					            <c:when test="${projeto.risco == 1}">Baixo</c:when>
					            <c:when test="${projeto.risco == 2}">Médio</c:when>
					            <c:otherwise>Alto</c:otherwise>
	      					</c:choose>
	                    </td>
	                    <td>
	                    	<c:choose>
					            <c:when test="${projeto.status == 1}">Em Análise</c:when>
					            <c:when test="${projeto.status == 2}">Análise Realizada</c:when>
					            <c:when test="${projeto.status == 3}">Análise Aprovada</c:when>
					            <c:when test="${projeto.status == 4}">Iniciado</c:when>
					            <c:when test="${projeto.status == 5}">Planejado</c:when>
					            <c:when test="${projeto.status == 6}">Em Andamento</c:when>
					            <c:when test="${projeto.status == 7}">Encerrado</c:when>
					            <c:otherwise>Cancelado</c:otherwise>
	      					</c:choose>
	                    </td>
	                     <td>${projeto.descricao}</td>
	                    <td style="width: 105px;">
	                        <button class="btn btn-info rounded-circle btn-editar" data-id="${projeto.id}" title="Editar">
	                        	<i class="fas fa-edit" ></i>
	                        </button>
	                        <c:if test="${!(projeto.status eq '4' || projeto.status eq '6' || projeto.status eq '7')}">                       
	                        	<button class="btn btn-danger rounded-circle btn-excluir" data-id="${projeto.id}" title="Excluir">
	                        		 <i class="fas fa-trash" ></i> 
	                        	</button>                      
	                        </c:if>
	                    </td>
	                </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	</div>
  </div>
</div>
	
<!-- Modal Novo Projeto -->
	<dialog id="modalNovoProjeto" style="width:70%">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalNovoProjetoLabel">Novo Projeto</h5>
	                <button type="button" class="close" onclick="$('#modalNovoProjeto')[0].close()" aria-label="Fechar">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	            </div>
	            <div class="modal-body">
	                <form id="formNovoProjeto">
	                	<input type="hidden" id="id" />
	                	
	                	 <div class="row mb-4">
			                <!-- Campo Nome -->
			                <div class="col-md-12">
			                	  <label for="nomeProjeto" style="float:left">Nome</label>
	                        	  <input type="text" class="form-control" id="nomeProjeto" class="required">
			                </div>
			             </div>
	                	
	                    <div class="row mb-4">
	                    	 <div class="col-md-3">
		                    	 <label for="dataInicioProjeto" style="float:left">Data Início</label>
		    					 <input type="date" class="form-control" id="dataInicioProjeto" class="required">
		    				 </div>
	    					 
	    					 <div class="col-md-3">
	    					 	<label for="dataFim" style="float:left">Data Fim</label>
	    					 	<input type="date" class="form-control" id="dataFim" class="required">
	    					 </div>
	    					 
	    					 <div class="col-md-3">
	                        	<label for="dataFim" style="float:left">Data Previsão</label>
	                        	<input type="date" class="form-control" id="dataPrevisao" class="required">
	                         </div>
	                         
	                          <div class="col-md-3">
	                        	<label for="orcamento" style="float:left">Orçamento</label>
	                        	<input type="text" class="form-control"  id="orcamento" class="required">
	                          </div>
	                    </div>
	                    
	                    
	                    
	                   	<div class="row mb-4">
	                   		<div class="col-md-12">
	                   	    	<label for="descricao" style="float:left">Descrição:</label>
	    						<textarea id="descricao" class="form-control" rows="5" cols="25" class="required" placeholder="Digite a descrição"></textarea>
	                   		</div>
	                   	</div>
	
	   
	                    <div class="row mb-4">	  
	                    	<div class="col-md-4">                 
		                    	<label for="risco" style="float:left">Gerente</label>
		                        <select class="form-select" id="gerente" class="required">
		                        	<option value="">Selecione o Gerente</option>
		                        	<c:forEach var="gerente" items="${gerentes}">
		                            	<option value="${gerente.id}">${gerente.nome}</option>
		                            </c:forEach>	                           
		                        </select>  
		                    </div>    
		                    <div class="col-md-2">  
		                    	 <label for="risco" style="float:left">Risco</label>
		                        <select class="form-select" id="riscoProjeto" class="required">
		                         	<option value="">Selecione o Risco</option>
		                            <option value="1">Baixo</option>
		                            <option value="2">Médio</option>
		                            <option value="3">Alto</option>
		                        </select>
		                    </div>  
		                    <div class="col-md-3">  
		                        <label for="status" style="float:left">Status</label>
		                        <select class="form-select" id="statusProjeto" class="required">
		                        	<option value="">Selecione o Status</option>
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
		                </div>
	                    
	                </form>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-secondary" onclick="$('#modalNovoProjeto')[0].close()">Fechar</button>
	                &nbsp;&nbsp;
	                <button type="button" class="btn btn-primary" id="salvarProjeto">Salvar</button>
	            </div>
	        </div>
	  </dialog> 
	
	<script>
	    $(document).ready(function() {

	    	// Criar novo projeto
	        $('#novoProjeto').click(function() {

	        	$('#id').val('');
                $('#nomeProjeto').val('');
                $('#dataInicioProjeto').val('');
                $('#dataFim').val('');
                $('#dataPrevisao').val('');
                $('#orcamento').val('');
                $('#descricao').val('');
                $('#riscoProjeto').val('');
                $('#statusProjeto').val('');
                $('#gerente').val('');
                $('#modalNovoProjeto')[0].showModal();
	        });

		       
	        // Salvar ou editar projeto
	        $('#salvarProjeto').click(function() {
	
	        	let isValido = validarCampos();
        	 
				if(isValido){

					$('#loadingDialog')[0].showModal();
		            
		            var projeto = {
						id: $('#id').val(),
		                nome: $('#nomeProjeto').val(),
		                dataInicio: $('#dataInicioProjeto').val(),
		                dataFim: $('#dataFim').val(),
		                dataPrevisao : $('#dataPrevisao').val(),
		                gerente: {
							id: parseInt($('#gerente').val(),10),
							nome: $('#gerente').text()
		                },
		                orcamento : convertStringToFloat($('#orcamento').val()) ,
		                descricao : $('#descricao').val(),
		                risco: $('#riscoProjeto').val(),
		                status: $('#statusProjeto').val()
		                                     
		            };
		            
		            $.ajax({
		                url: '/projeto/salvar',
		                method: 'POST',
		                contentType: 'application/json',
		                data: JSON.stringify(projeto),
		                success: function() {
		                	$('#modalNovoProjeto')[0].close();
		                    window.location.href = '/projeto/index'; 
		                }
		            });
				}
	        });
	
	        // Excluir projeto
	        $(document).on('click', '.btn-excluir', function() {
	        	$('#loadingDialog')[0].showModal();
	            var id = $(this).data('id');
	            $.ajax({
	                url: '/projeto/excluir/' + id,
	                method: 'DELETE',
	                success: function() {
	                	window.location.href = '/projeto/index'; 
	                },  
	                error: function (xhr, status, error) {
	                	// Captura o erro e exibe no dialog
	                	$('#loadingDialog')[0].close();
	                    const errorMessage = `Erro: ${xhr.status} - ${xhr.statusText}`;
	                    $('#errorMessage').text("Erro ao excluir, projeto já vinculado ao membro");
	                    $('#errorDialog')[0].showModal();
	                    console.log(JSON.stringify(error));
	                },
	            });
	        });
	
	        // abrir modal de Editar projeto
	        $(document).on('click', '.btn-editar', function() {
	            var id = $(this).data('id');
	            $.ajax({
	                url: '/projeto/editar/' + id,
	                method: 'GET',
	                success: function(projeto) {
	                	
	                    // Aqui você pode preencher os campos de edição com os dados do projeto
	                    $('#id').val(projeto.id);
	                    $('#nomeProjeto').val(projeto.nome);
	                    $('#dataInicioProjeto').val(convertDateFormat(new Date(projeto.dataInicio).toLocaleDateString('pt-BR', {
	                	    day: '2-digit',
	                	    month: '2-digit',
	                	    year: 'numeric'
	                	})));
	                    $('#dataFim').val(convertDateFormat(new Date(projeto.dataFim).toLocaleDateString('pt-BR', {
	                        day: '2-digit',
	                        month: '2-digit',
	                        year: 'numeric'
	                    })));
	                    $('#dataPrevisao').val(convertDateFormat(new Date(projeto.dataPrevisao).toLocaleDateString('pt-BR', {
	                        day: '2-digit',
	                        month: '2-digit',
	                        year: 'numeric'
	                    })));
	                    $('#orcamento').val(convertMoney(projeto.orcamento));
	                    $('#descricao').val(projeto.descricao);
	                    $('#riscoProjeto').val(projeto.risco);
	                    $('#statusProjeto').val(projeto.status);
	                    $('#gerente').val(projeto.gerente.id);
	                    $('#modalNovoProjeto')[0].showModal();
	                }
	            });
	        });
	    });

	    function validarCampos(){
	    	 // IDs dos campos obrigatórios
            const requiredFields = ["nomeProjeto", "dataInicioProjeto", "dataFim", "dataPrevisao","orcamento","descricao","riscoProjeto","statusProjeto","gerente"];
            let isValid = true; // Controle de validade geral
            const errorMessage = document.getElementById("errorMessage");

            // Resetar estilos de erro
            requiredFields.forEach((id) => {
                const field = document.getElementById(id);
                if(field.value.trim() === ''){
                	field.style.border = "2px solid red";
                	isValid = false;
	            } else {
	            	field.style.border = "";
		        }
            });

            // Exibir mensagem ou prosseguir
            if (isValid) {
                errorMessage.style.display = "none";             
            } else {
                errorMessage.style.display = "block";
            }

            return isValid;
		}
	</script>
  
<%@ include file="/WEB-INF/jsp/template/footer.jsp" %>
