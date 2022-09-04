package br.com.fiap.fmba.service.payload;


import java.io.Serializable;

public class TokenPayload implements Serializable {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -5966013488267819053L;
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
