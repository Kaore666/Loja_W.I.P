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
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.loja.dao.CategoriaDAO;
import br.com.loja.interceptors.SomenteLogado;
import br.com.loja.model.Categoria;
import br.com.olimposistema.aipa.service.Util;

@Controller
@Path("formcategoria")
public class FormcategoriaController {
	
	@Inject EntityManager em;
	@Inject Result result;
	@Inject CategoriaDAO categoriasDao;
	@Inject Validator validator;
	
	@Get("") @SomenteLogado
	public void formcategoria(Categoria categoria) {
		
		if(Util.isNotNull(categoria) && Util.isPositivo(categoria.getId())) {
			
			Categoria categoriaBanco = categoriasDao.selectPorId(categoria);
			result.include("categoria", categoriaBanco);
			
		}
		
	}
	
	@IncludeParameters
	@Post ("salvaCategoria")
	public void salvaCategoria(@Valid Categoria categoria) {
		
		validator.onErrorRedirectTo(this).formcategoria(categoria);
		
		boolean categExiste = true;
		if (categoriasDao.existeCategoria(categoria.getNomeCateg()) != null) {
			
			categExiste = false;
			
		}
		categoria.setNomeCateg(categoria.getNomeCateg().toLowerCase());
		String categNome = categoria.getNomeCateg();
		categNome = categNome.substring(0,1).toUpperCase().concat(categNome.substring(1));	
		categoria.setNomeCateg(categNome);
		
		validator.ensure(categExiste, new SimpleMessage("ERROR", "A categoria já existe."));
		validator.onErrorRedirectTo(this).formcategoria(categoria);
		
		categoriasDao.insertOrUpdate(categoria);
		result.redirectTo(CategoriasController.class).categorias();
		
		
	} 
	
}