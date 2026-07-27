package com.marcos.notificacao_eda.dto;

import java.io.Serializable;

public class NotificacaoRequest implements Serializable {

    private String email;
    private String mensagem;
    private String tipo; // Ex: "BOAS_VINDAS", "PEDIDO_CONFIRMADO"

    public NotificacaoRequest() {
    }

    public NotificacaoRequest(String email, String mensagem, String tipo) {
        this.email = email;
        this.mensagem = mensagem;
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}