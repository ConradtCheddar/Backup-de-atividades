package Controller;

import Model.Usuario;
import Model.UsuarioDAO;
import View.TelaDeCadastroUsuarios;

public class CadastroController {
	private final TelaDeCadastroUsuarios view;
	private final UsuarioDAO model;
	private final Navegador navegador;

	public CadastroController(TelaDeCadastroUsuarios view, UsuarioDAO model, Navegador navegador){
		this.view = view;
		this.model = model;
		this.navegador = navegador;
		
		this.view.cadastrar(e ->{
			String nome = view.getUsuario();
			String cpf = view.getCPF();
			
			UsuarioDAO dao = new UsuarioDAO();
			Usuario u = new Usuario(nome ,cpf ,this.view.getcheckAdmin().isSelected());


			navegador.navegarPara("LOGIN");
		});
		
	}
}