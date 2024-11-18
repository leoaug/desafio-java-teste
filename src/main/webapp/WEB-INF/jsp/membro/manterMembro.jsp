<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/template/header.jsp" %>
<div class="container mt-6">
	 <div class="card">
	  	 <div class="card-body">
		    <h5 class="card-title">Gerente/Funcionário</h5>
	    
			    <button class="btn btn-success mb-3" data-toggle="modal" onclick="$('#modalNovoMembro')[0].showModal()">Novo Membro</button>
			
			    <table class="table table-bordered">
			        <thead>
			            <tr>
			                <th>Nome</th>
			                <th>Atribuição</th>
			                <th>Projeto</th>
			                <th>Funcionário</th>             
			                <th>Ações</th>
			            </tr>
			        </thead>
			        <tbody id="tabelamembros">
			            <c:forEach var="membro" items="${membros}">
			                <tr>
			                    <td>${membro.nome}</td>
			                    <td>                    	
			                    	${membro.atribuicao}
			                    </td>
			                    <td>                   	
			                    	${membro.projeto.nome}
			                    </td>
			                    <td>                 	
			                    	${membro.funcionario.nome}
			                    </td>               
			                   
			                    <td style="width: 105px;">
			                        <button class="btn btn-info rounded-circle btn-editar" data-id="${membro.id}" title="Editar">
			                        	<i class="fas fa-edit" ></i>
			                         </button>
			                        <button class="btn btn-danger rounded-circle btn-excluir" data-id="${membro.id}" title="Exclur">
			                        	<i class="fas fa-trash" ></i> 
			                        </button>                       	                        
	                    		</td>
			                </tr>
			            </c:forEach>
			        </tbody>
			    </table>
		</div>
	</div>
</div>

<!-- Modal Novo Projeto -->
	<dialog id="modalNovoMembro" style="width:70%">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalNovoMembroLabel">Novo Membro</h5>
                <button type="button" class="close" onclick="$('#modalNovoMembro')[0].close()" aria-label="Fechar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="formNovoMembro">
                	<input type="hidden" id="id" />
                	
                	  <div class="row mb-4">
                    	 <div class="col-md-6">
                   	 	    <label for="nome" style="float: left">Nome</label>
                       		<input type="text" class="form-control" id="nome" required>
                    	 </div>
                    	 
                    	 <div class="col-md-6">
                    	 	<label for="atribuicao" style="float: left">Atribuição:</label>
    						<textarea id="atribuicao" class="form-control" rows="5" cols="25" placeholder="Digite a atribuição"></textarea>
                    	 </div>
	                  </div>
                		
                	  <div class="row mb-4">
                    	 <div class="col-md-6">
                    	 	<label for="risco" style="float: left">Projeto</label>
	                        <select class="form-select" id="projeto">
	                        	<option value="">Selecione o Projeto</option>
	                        	<c:forEach var="projeto" items="${projetos}">
	                            	<option value="${projeto.id}">${projeto.nome}</option>
	                            </c:forEach>	                           
	                        </select>
                    	 </div>
                    	 
                    	 <div class="col-md-6">
                    	 	<label for="risco" style="float: left">Funcionário</label>
	                        <select class="form-select" id="funcionario">
	                        	<option value="">Selecione o Funcionário</option>
	                        	<c:forEach var="funcionario" items="${funcionarios}">
	                            	<option value="${funcionario.id}">${funcionario.nome}</option>
	                            </c:forEach>	                           
	                        </select> 
                    	 </div>
                      </div>
                	
                 
                    
                    
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" onclick="$('#modalNovoMembro')[0].close()">Fechar</button>
                <button type="button" class="btn btn-primary" id="salvarMembro">Salvar</button>
            </div>
      </div>
 </dialog>

<script>
    $(document).ready(function() {
       
        // Criar novo Membro
        $('#salvarMembro').click(function() {

        	let isValido = validarCampos();
        	 
			if(isValido){

        		$('#loadingDialog')[0].showModal();
        	
	            var membro = {
					id: $('#id').val(),
	                nome: $('#nome').val(),      
	                atribuicao: $('#atribuicao').val(),        
	                funcionario: {
						id: parseInt($('#funcionario').val(),10),
						nome: $('#funcionario').text()
	                },
	                projeto: {
						id: parseInt($('#projeto').val(),10),
						nome: $('#projeto').text()
	                }
	                                     
	            };
	            
	            $.ajax({
	                url: '/membro/salvar',
	                method: 'POST',
	                contentType: 'application/json',
	                data: JSON.stringify(membro),
	                success: function() {
	                	$('#modalNovoMembro')[0].close();
	                    window.location.href = '/membro/index'; 
	                }
	            });
			}
        });

        // Excluir Membro
        $(document).on('click', '.btn-excluir', function() {
            var id = $(this).data('id');
            $.ajax({
                url: '/membro/excluir/' + id,
                method: 'DELETE',
                success: function() {
                	window.location.href = '/membro/index'; 
                },  
                error: function (xhr, status, error) {
                	// Captura o erro e exibe no dialog
                	$('#loadingDialog')[0].close();
                    const errorMessage = `Erro: ${xhr.status} - ${xhr.statusText}`;
                    $('#errorMessage').text(errorMessage);
                    $('#errorDialog')[0].showModal();
                },
            });
        });

        // abrir modal de Editar Membro
        $(document).on('click', '.btn-editar', function() {
            var id = $(this).data('id');
            $.ajax({
                url: '/membro/editar/' + id,
                method: 'GET',
                success: function(membro) {
                	
                    // Aqui você pode preencher os campos de edição com os dados do Membro
                    $('#id').val(membro.id);
                    $('#nome').val(membro.nome);
                    $('#atribuicao').val(membro.atribuicao);
                    $('#projeto').val(membro.projeto.id);
                    $('#funcionario').val(membro.funcionario.id);                  
                    $('#modalNovoMembro')[0].showModal();
                }
            });
        });
    });


    function validarCampos(){
        // IDs dos campos obrigatórios
       const requiredFields = ["nome", "atribuicao", "projeto","funcionario"];
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
