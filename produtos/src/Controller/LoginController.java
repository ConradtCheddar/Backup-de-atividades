package Controller;

import Model.Usuario;
import Model.UsuarioDAO;
import View.TelaDeLogin;

public class LoginController {
	private final UsuarioDAO model;
	private final Navegador navegador;
	private final TelaDeLogin view;
	
	
	public LoginController(TelaDeLogin view, UsuarioDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;
		
		
		String Emessage = "Usuario ou CPF incorretos";
		
		this.view.confirmar(e ->{
			String nome = view.getUsuario();
			String cpf = view.getCPF();

			UsuarioDAO dao = new UsuarioDAO(navegador);
			Usuario u = dao.login(nome, cpf);
			
			if (u != null) {
				if (u.isAdmin()) {
						navegador.navegarPara("CADPRO");
				} else if (!u.isAdmin()) {
						navegador.navegarPara("compra");
					} else {
						navegador.navegarPara("LOGIN");
					}
				}
			
		});
		
		this.view.SemCadastro(e ->{
			navegador.navegarPara("CADUSU");
		});
		
		
		
	}

}
