package br.com.loja.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.loja.dao.ProdutoDAO;
import br.com.loja.interceptors.SomenteLogado;

@Controller
@Path("produtos")
public class ProdutosController {
	
	@Inject HttpSession session;
	@Inject Result result;
	@Inject ProdutoDAO produtosDao;
	
	@Get("") @SomenteLogado
	public void produtos() {
		
		result.include("produtos", produtosDao.selectAll());
		
	}

}