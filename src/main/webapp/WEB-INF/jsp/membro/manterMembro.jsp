<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/template/header.jsp" %>
  <div class="container mt-4">
    <h2>Membros</h2>
    <button class="btn btn-success mb-3" data-toggle="modal" data-target="#modalNovoMembro">Novo Membro</button>

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
                   
                    <td>
                        <button class="btn btn-info btn-editar" data-id="${membro.id}">Editar</button>
                        <button class="btn btn-danger btn-excluir" data-id="${membro.id}">Excluir</button>                      
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<!-- Modal Novo Membro -->
<div class="modal fade" id="modalNovoMembro" tabindex="-1" role="dialog" aria-labelledby="modalNovoMembroLabel" aria-hidden="true">
    <div class="modal-dialog" role="document" style="width: 600px !important">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalNovoMembroLabel">Novo Membro</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="formNovoMembro">
                	<input type="hidden" id="id" />
                    <div class="form-group">
                        <label for="nome">Nome</label>
                        <input type="text" class="form-control" id="nome" required>
                    </div>
                  
                   	<div class="form-group"> 
                   	    <label for="atribuicao">Atribuição:</label>
    					<textarea id="atribuicao" class="form-control" rows="5" cols="25" placeholder="Digite a atribuição"></textarea>
                   	</div>

					<div class="form-group">	                   
                    	<label for="risco">Projeto</label>
                        <select class="form-select" id="projeto">
                        	<c:forEach var="projeto" items="${projetos}">
                            	<option value="${projeto.id}">${projeto.nome}</option>
                            </c:forEach>	                           
                        </select>                 
	                </div>
   
                    <div class="form-group">	                   
                    	<label for="risco">Funcionário</label>
                        <select class="form-select" id="funcionario">
                        	<c:forEach var="funcionario" items="${funcionarios}">
                            	<option value="${funcionario.id}">${funcionario.nome}</option>
                            </c:forEach>	                           
                        </select>                 
	                </div>
                    
                    
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                <button type="button" class="btn btn-primary" id="salvarMembro">Salvar</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
       
        // Criar novo Membro
        $('#salvarMembro').click(function() {
           
            var Membro = {
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
                data: JSON.stringify(Membro),
                success: function() {
                    $('#modalNovoMembro').modal('hide');
                    window.location.href = '/membro/index'; 
                }
            });
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
                    $('#modalNovoMembro').modal('show');
                }
            });
        });
    });
</script>
  
<%@ include file="/WEB-INF/jsp/template/footer.jsp" %>
