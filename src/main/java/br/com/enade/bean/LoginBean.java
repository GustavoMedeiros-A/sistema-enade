package br.com.enade.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.enade.dao.UsuarioDao;
import br.com.enade.model.Tbusuario;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Tbusuario usuario;

	@Inject
	UsuarioDao dao;

	@Inject
	FacesContext context;

	private Tbusuario usuarioLogado;

	public Tbusuario getUsuario() {
		return usuario;
	}

	public String efetuaLogin() {
		System.out.println("fazendo login do usuario " + this.usuario.getEmailUsuario());

		boolean existe = dao.existe(this.usuario);
		if (existe) {
			usuarioLogado = dao.recuperarUsuario(this.usuario);
			context.getExternalContext().getSessionMap().put("usuarioLogado", usuarioLogado);			
			return "index?faces-redirect=true";
		}

		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuario nao encontrado"));

		return "login?faces-redirect=true";
	}

	public String deslogar() {
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}
	
	public Tbusuario getUsuarioLogado() {
		return usuarioLogado;
	}

}
