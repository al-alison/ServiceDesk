package br.usjt.arqsw.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Alison Almeida RA 818119557 SI
 *
 */
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Size(min = 5, max=50, message="O nome de usuario deve estar entre 5 e 50 caracteres.")
	private String username;
	@NotNull
	@Size(min = 5, max=50, message="A senha deve estar entre 5 e 50 caracteres.")
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
