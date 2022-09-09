package com.gabriel.os.model.enums;

public enum Prioridade {

    BAIXA(0, "BAIXA"),
    MEDIA(1, "MEDIA"),
    ALTA(2, "ALTA");

    private Integer cod;
    private String descricao;

    private Prioridade(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Prioridade toEnum(Integer cod) throws IllegalAccessException {
        if(cod == null){
            return  null;
        }
        for (Prioridade p : Prioridade.values()){
            if(cod.equals(p.getCod())){
                return p;
            }
        }
        throw new IllegalAccessException("Prioridade inválida! " + cod);
    }
}
