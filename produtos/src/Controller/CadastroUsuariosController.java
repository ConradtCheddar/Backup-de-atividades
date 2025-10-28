package Controller;

import Model.Usuario;
import Model.UsuarioDAO;
import View.TelaDeCadastroUsuarios;

public class CadastroUsuariosController {
	private final TelaDeCadastroUsuarios view;
	private final UsuarioDAO model;
	private final Navegador navegador;

	public CadastroUsuariosController(TelaDeCadastroUsuarios view, UsuarioDAO model, Navegador navegador){
		this.view = view;
		this.model = model;
		this.navegador = navegador;
		
		this.view.cadastrarUsuarios(e ->{
			String nome = view.getUsuario();
			String cpf = view.getCPF();
			
			UsuarioDAO dao = new UsuarioDAO();
			Usuario u = new Usuario(nome ,cpf ,this.view.getcheckAdmin().isSelected());
				try {
					dao.cadastrarU(u);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.view.limparCampos();
			navegador.navegarPara("LOGIN");
		});
		
		this.view.voltar(e ->{
			this.view.limparCampos();
			navegador.navegarPara("LOGIN");
		});
		
	}
}