<!-- src/main/resources/WEB-INF/jsp/index.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/template/header.jsp" %>
  <script>
        $(document).ready(function(){
            $('#orcamento').mask('R$ #.###.##0,00', {reverse: true});
        });
  </script>
  <div class="container mt-4">
    <h2>Projetos</h2>
    <button class="btn btn-success mb-3" data-toggle="modal" data-target="#modalNovoProjeto">Novo Projeto</button>

    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Nome</th>
                <th>Data In�cio</th>
                <th>Data Fim</th>
                <th>Data Previs�o</th>
                <th>Or�amento</th>
                <th>Gerente</th>
                <th>Risco</th>
                <th>Status</th>
                <th>A��es</th>
            </tr>
        </thead>
        <tbody id="tabelaProjetos">
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
				            <c:when test="${projeto.risco == 2}">M�dio</c:when>
				            <c:otherwise>Alto</c:otherwise>
      					</c:choose>
                    </td>
                    <td>${projeto.status}</td>
                    <td>
                        <button class="btn btn-info btn-editar" data-id="${projeto.id}">Editar</button>
                        <button class="btn btn-danger btn-excluir" data-id="${projeto.id}">Excluir</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<!-- Modal Novo Projeto -->
<div class="modal fade" id="modalNovoProjeto" tabindex="-1" role="dialog" aria-labelledby="modalNovoProjetoLabel" aria-hidden="true">
    <div class="modal-dialog" role="document" style="width: 600px !important">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalNovoProjetoLabel">Novo Projeto</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="formNovoProjeto">
                    <div class="form-group">
                        <label for="nome">Nome</label>
                        <input type="text" class="form-control" id="nome" required>
                    </div>
                    <div class="form-group-inline">
                    	 <label for="dataInicio">Data In�cio</label>
    					 <input type="date" class="form-control" id="dataInicio" required>
    					 
    					 <label for="dataFim">Data Fim</label>
    					 <input type="date" class="form-control" id="dataFim" required>
                    </div>
                    
                     <div class="form-group">
                        <label for="dataFim">Data Previs�o</label>
                        <input type="date" class="form-control" id="dataPrevisao" required>
                    </div>
                    
                    
                    <div class="form-group">
                        <label for="orcamento">Or�amento</label>
                        <input type="text" class="form-control"  id="orcamento" required>
                    </div>
                    
                   	<div class="form-group"> 
                   	    <label for="descricao">Descri��o:</label>
    					<textarea id="descricao" class="form-control" rows="5" cols="25" placeholder="Digite a descri��o"></textarea>
                   	</div>

   
                    <div class="form-group">	                   
                    	<label for="risco">Gerente</label>
                        <select class="form-control" id="gerente">
                        	<c:forEach var="gerente" items="${gerentes}">
                            	<option value="${gerente.id}">${gerente.nome}</option>
                            </c:forEach>	                           
                        </select>                 
	                </div>
                    
                    <div class="form-group">
                        <label for="risco">Risco</label>
                        <select class="form-control" id="risco">
                            <option value="1">Baixo</option>
                            <option value="2">M�dio</option>
                            <option value="3">Alto</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="status">Status</label>
                        <select class="form-control" id="status">
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
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                <button type="button" class="btn btn-primary" id="salvarProjeto">Salvar</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        // Carregar lista de projetos
        function carregarTabela() {
            $.ajax({
                url: '/projeto/index', // Endere�o do controller para obter os projetos
                method: 'GET',
                success: function(projetos) {
                    var html = '';
                    if (projetos && Array.isArray(projetos)) {
	                    projetos.forEach(function(projeto) {

	                        const risco = projeto.risco === 1 ? "Baixo" : projeto.risco === 2 ? 'M�dio' : 'Alto';
							
	                        html += `
	                            <tr>
	                                <td>${projeto.nome}</td>
	                                <td>${projeto.dataInicio}</td>
	                                <td>${projeto.dataFim}</td>
	                                <td>${projeto.dataPrevisao}</td>
	                                <td>${projeto.orcamento}</td>
	                                <td>${projeto.descricao}</td>
	                                <td> 
	                                	${risco}
	                                </td>
	                                <td>${projeto.status}</td>
	                                <td>${projeto.gerente.nome}</td>
	                                <td>
	                                    <button class="btn btn-info btn-editar" data-id="${projeto.id}">Editar</button>
	                                    <button class="btn btn-danger btn-excluir" data-id="${projeto.id}">Excluir</button>
	                                </td>
	                            </tr>
	                        `;
	                    });
                    }
                    $('#tabelaProjetos').html(html);
                }
            });
        }

        // Criar novo projeto
        $('#salvarProjeto').click(function() {


        	 const cleanedString = $('#orcamento').val().replace(/[^\d,.-]/g, '');
        	 const normalizedString = cleanedString.replace('.', '').replace(',', '.');
        	 const orcamentoFloat = parseFloat(normalizedString);
            
            var projeto = {
                nome: $('#nome').val(),
                dataInicio: $('#dataInicio').val(),
                dataFim: $('#dataFim').val(),
                dataPrevisao : $('#dataPrevisao').val(),
                gerente: {
					id: parseInt($('#gerente').val(),10),
					nome: $('#gerente').text()
                },
                orcamento : orcamentoFloat ,
                descricao : $('#descricao').val(),
                risco: $('#risco').val(),
                status: $('#status').val()
                           
               
            };
            
            $.ajax({
                url: '/projeto/salvar',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(projeto),
                success: function() {
                    $('#modalNovoProjeto').modal('hide');
                    carregarTabela();  // Recarregar a tabela
                }
            });
        });

        // Excluir projeto
        $(document).on('click', '.btn-excluir', function() {
            var id = $(this).data('id');
            $.ajax({
                url: '/projeto/excluir/' + id,
                method: 'DELETE',
                success: function() {
                    carregarTabela();  // Recarregar a tabela
                }
            });
        });

        // Editar projeto
        $(document).on('click', '.btn-editar', function() {
            var id = $(this).data('id');
            $.ajax({
                url: '/projeto/editar/' + id,
                method: 'GET',
                success: function(projeto) {
                	
                    // Aqui voc� pode preencher os campos de edi��o com os dados do projeto
                    $('#nome').val(projeto.nome);
                    $('#dataInicio').val(convertDateFormat(new Date(projeto.dataInicio).toLocaleDateString('pt-BR', {
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
                    $('#risco').val(projeto.risco);
                    $('#status').val(projeto.status);
                    $('#gerente').val(projeto.gerente.id);
                    $('#modalNovoProjeto').modal('show');
                }
            });
        });
    });
</script>
  
<%@ include file="/WEB-INF/jsp/template/footer.jsp" %>
