package Controller;

import javax.swing.JOptionPane;

import Model.UsuarioDAO;
import View.TelaDeLogin;
import produtos.Tela_Cadastro;
import produtos.Tela_Produtos;

public class LoginController {
	private final UsuarioDAO model;
	private final Navegador navegador;
	private final TelaDeLogin view;
	
	
	public LoginController(TelaDeLogin view, UsuarioDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;
		
		String usuarioA = "henrique";
		String usuarioN = "gustavo";
		String cpfA = "321";
		String cpfN = "123";
		String Emessage = "Usuario ou CPF incorretos";
		
		this.view.confirmar(e ->{
			if(this.view.getcheckAdmin().isSelected()) {
				if(this.view.getUsuario().equals(usuarioA)&&this.view.getCPF().equals(cpfA)) {
					navegador.navegarPara("");
				}else {
					JOptionPane.showMessageDialog(null, Emessage, "Erro!!!",JOptionPane.ERROR_MESSAGE);
				}
			}else if (!this.view.getcheckAdmin().isSelected()) {
				if(this.view.getUsuario().equals(usuarioN)&&this.view.getCPF().equals(cpfN)) {
					navegador.navegarPara("");
				}else {
					JOptionPane.showMessageDialog(null, Emessage, "Erro!!!",JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
	}

}
