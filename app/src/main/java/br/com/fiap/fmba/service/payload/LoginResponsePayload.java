package br.com.fiap.fmba.service.payload;

import java.io.Serializable;

public class LoginResponsePayload implements Serializable {

    /**
     * Serial
     */
    private static final long serialVersionUID = -8966181192632870974L;

    private String usuario;
    private String nome;
    private String token;

    public LoginResponsePayload() {
        super();
    }

    public LoginResponsePayload(String usuario, String nome, String token) {
        super();
        this.usuario = usuario;
        this.nome = nome;
        this.token = token;
    }

    // Getters and Setters

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
