package br.com.desafiojava.constants;

public enum StatusProjetoEnum {


    EM_ANALISE("Em Análise", 1),
    ANALISE_REALIZADA("Análise Realizada", 2),
    ANALISE_APROVADA("Análise Aprovada", 3),
    INICIADO("Iniciado", 4),
    PLANEJADO("Planejado", 5),
    EM_ANDAMENTO("Em Andamento", 6),
    ENCERRADO("Encerrado", 7),
    CANCELADO("Cancelado", 8);

    private final String label;
    private final Integer value;

    // Construtor
    StatusProjetoEnum(String label, Integer value) {
        this.label = label;
        this.value = value;
    }

    // Getter para o label
    public String getLabel() {
        return label;
    }

    // Getter para o value
    public Integer getValue() {
        return value;
    }

    // Método estático para obter o enum pelo value
    public static StatusProjetoEnum fromValue(String value) {
        for (StatusProjetoEnum status : StatusProjetoEnum.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Valor inválido para StatusProjeto: " + value);
    }
}
