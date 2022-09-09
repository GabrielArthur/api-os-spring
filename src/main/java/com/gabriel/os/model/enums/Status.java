package com.gabriel.os.model.enums;

public enum Status {

    ABERTO(0, "ABERTO"),
    ANDAMENTO(1, "ANDAMENTO"),
    ENCERRADO(2, "ENCERRADO");

    private Integer cod;
    private String descricao;

    private Status(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status toEnum(Integer cod) throws IllegalAccessException {
        if(cod == null){
            return  null;
        }
        for (Status s : Status.values()){
            if(cod.equals(s.getCod())){
                return s;
            }
        }
        throw new IllegalAccessException("Status inválido! " + cod);
    }
}
