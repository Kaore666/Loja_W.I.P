package br.com.loja.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.loja.dao.CategoriaDAO;
import br.com.loja.interceptors.SomenteLogado;
import br.com.loja.model.Categoria;

@Controller
@Path("deletacategoria")
public class DeletacategoriaController {
	
	@Inject CategoriaDAO categoriaDAO;
	@Inject Result result;

	@Get("/{categoria.id}") @SomenteLogado
	public void deletaCategoria(Categoria categoria) {
		System.out.println("Entrou no metodo deletaCategoria");
		categoriaDAO.delete(categoria);
		System.out.println("Deletou a categoria");
		result.redirectTo(CategoriasController.class).categorias();
		System.out.println("Redirecionou para o CategoriaController");
	}
}
