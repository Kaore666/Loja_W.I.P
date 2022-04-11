package br.com.loja.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import br.com.loja.model.Produto;
import br.com.olimposistema.aipa.dao.DAO;

@RequestScoped
public class ProdutoDAO extends DAO<Produto>{
	
@Deprecated public ProdutoDAO(){
		
		super(null, null);
		
	}
	
	@Inject
	public ProdutoDAO(EntityManager em) {
		
		super(em, Produto.class);
		
	}

}
