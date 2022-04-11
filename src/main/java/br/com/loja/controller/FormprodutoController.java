package br.com.loja.controller;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;
import br.com.loja.dao.CategoriaDAO;
import br.com.loja.dao.ProdutoDAO;
import br.com.loja.interceptors.SomenteLogado;
import br.com.loja.model.Produto;
import br.com.olimposistema.aipa.service.Util;

@Controller
@Path("formproduto")
public class FormprodutoController {
	
	@Inject EntityManager em;
	@Inject Result result;
	@Inject ProdutoDAO produtoDao;
	@Inject CategoriaDAO categoriaDao;
	@Inject Validator validator;
	
	@Get("") @SomenteLogado
	public void formproduto(Produto produto) {
		
		result.include("categorias", categoriaDao.selectAll());
		
		if(Util.isNotNull(produto) && Util.isPositivo(produto.getId())) {
			
			Produto produtoBanco = produtoDao.selectPorId(produto);
			result.include("produto", produtoBanco);
			
		}
		
	}
	
	@Post ("salvaProduto") @IncludeParameters
	public void salvaProduto(@Valid Produto produto) {
		
		validator.onErrorRedirectTo(this).formproduto(produto);

		produtoDao.insertOrUpdate(produto);
		result.redirectTo(ProdutosController.class).produtos();
		
	}

}