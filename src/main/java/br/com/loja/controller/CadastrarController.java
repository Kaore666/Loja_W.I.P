package br.com.loja.controller;  

import javax.inject.Inject;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;



import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.loja.dao.UsuarioDAO;
import br.com.loja.model.Usuario;

@Controller
@Path("cadastrar")
public class CadastrarController {
	
	@Inject EntityManager em;
	@Inject Result result;
	@Inject UsuarioDAO usuarioDao;
	@Inject Validator validator;
	@Inject HttpSession session;
	
	@Get("")
	public void cadastrar() {
		
		session.removeAttribute("usuarioLogado");

	}
	
	@IncludeParameters
	@Post("salvaUsuario")
	public void salvaUsuario(@Valid Usuario usuario, String confirmSenha) {
		
		boolean emailExiste = true;
		if (usuarioDao.existeEmailCom(usuario.getEmail()) != null) {
			
			emailExiste = false;
			
		}
		validator.ensure(emailExiste, new SimpleMessage("ERROR", "O email já existe."));
		validator.onErrorRedirectTo(this).cadastrar();
		
		boolean mesmaSenha = usuario.getSenha().equals(confirmSenha);
		validator.ensure(mesmaSenha, new SimpleMessage("ERROR", "As senhas não conferem."));
		validator.onErrorRedirectTo(this).cadastrar();
		
		usuarioDao.insert(usuario);
		result.redirectTo(LoginController.class).login();
		
	}

}