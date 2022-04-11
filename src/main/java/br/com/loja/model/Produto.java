package br.com.loja.model;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.olimposistema.aipa.imagem.Imagem;
import br.com.olimposistema.aipa.model.Model;

@Entity
public class Produto extends Model{
	
	@NotEmpty(message = "{produto.nome.error}") @Size(min = 3, max = 60, message = "{produto.nome.size}")
	private String nome;
	
	@NotNull(message = "{produto.valor.error}")
	private Double valor;
	
	@NotEmpty(message = "{produto.desc.error}") @Type(type="text") @Column(name="descricao")
	private String desc;

	@ManyToOne @NotNull(message = "{produto.categ.error}")
	private Categoria categorias;

	@Temporal(TemporalType.DATE) @NotNull(message = "{produto.data.error}")
	private Calendar dataValidade;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER, orphanRemoval = true)
	private Imagem imagem;
	
	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}

	public Calendar getDataValidade() {
		return dataValidade;
	}
	
	public String getDataValidadeFormatada() {
		
		String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(dataValidade.getTime());
		return dataFormatada;
		
	}

	public void setDataValidade(Calendar dataValidade) {
		this.dataValidade = dataValidade;
	}
	
	public void setDataValidadeEn(String data) {
		
		if (data == null) return;
		
		String[] dataParts = data.split("-");//divisor da data
		
		Integer ano = Integer.parseInt(dataParts[0]);
		Integer mes = Integer.parseInt(dataParts[1]);
		Integer dia = Integer.parseInt(dataParts[2]);

		Calendar calendar = new GregorianCalendar(ano, (mes-1), dia);
		this.dataValidade = calendar;
		
	}

	public Categoria getCategorias() {
		return categorias;
	}

	public void setCategorias(Categoria categorias) {
		this.categorias = categorias;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public String getValorDinDin() {
		
		String valorFormatado = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor);
		return valorFormatado;
		
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
