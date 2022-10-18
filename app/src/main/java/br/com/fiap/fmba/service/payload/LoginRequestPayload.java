package br.com.fiap.fmba.service.payload;

import java.io.Serializable;

public class LoginRequestPayload implements Serializable {

    /**
     * Serial
     */
    private static final long serialVersionUID = -5966013488267819053L;

    private String usuario;
    private String senha;

    public LoginRequestPayload() {
        super();
    }

    public LoginRequestPayload(String usuario, String senha) {
        super();
        this.usuario = usuario;
        this.senha = senha;
    }

    // Getters and setters

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
