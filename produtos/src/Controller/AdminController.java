package Controller;

import Model.UsuarioDAO;
import View.TelaDeAdmin;
import View.TelaDeLogin;

public class AdminController {

	private TelaDeAdmin view;
	private UsuarioDAO model;
	private Navegador navegador;

	public AdminController(TelaDeAdmin view, UsuarioDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;
	}

}
