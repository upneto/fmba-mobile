package br.com.fiap.fmba.service.payload;

import java.io.Serializable;

public class LoginPayload implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8966181192632870974L;

	private String usuario;
	private String senha;
	private String nome;
	private String token;

	public LoginPayload() {
		super();
	}

	public LoginPayload(String usuario, String senha) {
		super();
		this.usuario = usuario;
		this.senha = senha;
	}

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
