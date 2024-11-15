<!-- src/main/resources/WEB-INF/jsp/index.jsp -->
<%@ include file="/WEB-INF/jsp/template/header.jsp" %>
  
  <div class="container mt-4">
    <h2>Projetos</h2>
    <button class="btn btn-success mb-3" data-toggle="modal" data-target="#modalNovoProjeto">Novo Projeto</button>

    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Nome</th>
                <th>Data Início</th>
                <th>Data Fim</th>
                <th>Risco</th>
                <th>Status</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody id="tabelaProjetos">
            <c:forEach var="projeto" items="${projetos}">
                <tr>
                    <td>${projeto.nome}</td>
                    <td>${projeto.dataInicio}</td>
                    <td>${projeto.dataFim}</td>
                    <td>${projeto.risco}</td>
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
    <div class="modal-dialog" role="document">
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
                    <div class="form-group">
                        <label for="dataInicio">Data Início</label>
                        <input type="date" class="form-control" id="dataInicio" required>
                    </div>
                    <div class="form-group">
                        <label for="dataFim">Data Fim</label>
                        <input type="date" class="form-control" id="dataFim" required>
                    </div>
                    <div class="form-group">
                        <label for="risco">Risco</label>
                        <select class="form-control" id="risco">
                            <option value="1">Baixo</option>
                            <option value="2">Médio</option>
                            <option value="3">Alto</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="status">Status</label>
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
                url: '/projeto', // Endereço do controller para obter os projetos
                method: 'GET',
                success: function(projetos) {
                    var html = '';
                    projetos.forEach(function(projeto) {
                        html += `
                            <tr>
                                <td>${projeto.nome}</td>
                                <td>${projeto.dataInicio}</td>
                                <td>${projeto.dataFim}</td>
                                <td>${projeto.risco}</td>
                                <td>${projeto.status}</td>
                                <td>
                                    <button class="btn btn-info btn-editar" data-id="${projeto.id}">Editar</button>
                                    <button class="btn btn-danger btn-excluir" data-id="${projeto.id}">Excluir</button>
                                </td>
                            </tr>
                        `;
                    });
                    $('#tabelaProjetos').html(html);
                }
            });
        }

        carregarTabela();  // Carregar tabela ao iniciar

        // Criar novo projeto
        $('#salvarProjeto').click(function() {
            var projeto = {
                nome: $('#nome').val(),
                dataInicio: $('#dataInicio').val(),
                dataFim: $('#dataFim').val(),
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
                    // Aqui você pode preencher os campos de edição com os dados do projeto
                    $('#nome').val(projeto.nome);
                    $('#dataInicio').val(projeto.dataInicio);
                    $('#dataFim').val(projeto.dataFim);
                    $('#risco').val(projeto.risco);
                    $('#status').val(projeto.status);
                    $('#modalNovoProjeto').modal('show');
                }
            });
        });
    });
</script>
  
<%@ include file="/WEB-INF/jsp/template/footer.jsp" %>
