package br.com.loja.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import br.com.olimposistema.aipa.model.Model;

@Entity
public class Usuario extends Model{
	
	@NotEmpty(message = "{usuario.nome.error}") @Size (min = 3, max = 60, message = "{usuario.nome.size}")
	private String nome;
	
	@NotEmpty(message = "{usuario.email.error}") @Email (message = "{usuario.email.email}") @Column(unique = true)
	private String email;
	
	@NotNull(message = "{usuario.senha.error}") @Size (min = 8, max = 30, message = "{usuario.senha.size}")
	private String senha;
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}