package br.com.loja.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.olimposistema.aipa.model.Model;

@Entity
public class Categoria extends Model{
	
	@NotEmpty(message = "{categoria.nome.error}") @Size (min = 3, max = 60, message = "{categoria.nome.size}") @Column(unique = true)
	private String nomeCateg;

	@OneToMany(mappedBy = "categorias")
	private List<Produto> produto;
	
	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	public String getNomeCateg() {
		return nomeCateg;
	}

	public void setNomeCateg(String nomeCateg) {
		this.nomeCateg = nomeCateg;
	}

}
