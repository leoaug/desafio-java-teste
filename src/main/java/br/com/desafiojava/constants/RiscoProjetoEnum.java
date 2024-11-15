package br.com.desafiojava.constants;

public enum RiscoProjetoEnum {

    BAIXO_RISCO("Baixo Risco", 1),
    MEDIO_RISCO("Médio Risco", 2),
    ALTO_RISCO("Alto Risco", 3);

    private final String label;
    private final int value;

    RiscoProjetoEnum(String label, int value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }

    // Método para obter o enum a partir do valor
    public static RiscoProjetoEnum fromValue(int value) {
        for (RiscoProjetoEnum nivel : RiscoProjetoEnum.values()) {
            if (nivel.value == value) {
                return nivel;
            }
        }
        throw new IllegalArgumentException("Valor inválido para NivelRisco: " + value);
    }
}
