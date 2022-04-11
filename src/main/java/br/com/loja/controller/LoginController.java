package br.com.loja.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.NotEmpty;
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
@Path("login")
public class LoginController {
	
	@Inject Result result;
	@Inject Validator validator;
	@Inject UsuarioDAO usuarioDao;
	@Inject HttpSession session;
	
	@Get("")
	public void login() {
		
		session.removeAttribute("usuarioLogado");
		
	}
	
	@IncludeParameters
	@Post("puxaUsuario")
	public void puxaUsuario(@NotEmpty(message = "{login.email.error}") String email , @NotEmpty(message = "{login.senha.error}") String senha) {
		
		validator.onErrorRedirectTo(this).login(); //se der erro
		
		
		Usuario usuario = usuarioDao.existeUsuarioCom(email, senha); //comparar usuario
		
		session.setAttribute("usuarioLogado", usuario);
		
		validator.addIf(usuario == null, new SimpleMessage("ERROR", "email ou senha são invalidos."));
		
		validator.onErrorRedirectTo(this).login();
		
		result.redirectTo(ProdutosController.class).produtos();

	}

}