package Main;

import Controller.AdminController;
import Controller.CadastroController;
import Controller.LoginController;
import Controller.Navegador;
import Model.UsuarioDAO;
import View.Janela;
import View.TelaDeAdmin;
import View.TelaDeCadastroUsuarios;
import View.TelaDeLogin;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Janela janela = new Janela();
		Navegador navegador =new Navegador(janela);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		TelaDeLogin telaDeLogin = new TelaDeLogin();
		LoginController loginController = new LoginController(telaDeLogin, usuarioDAO, navegador);
		
		TelaDeAdmin telaDeAdmin = new TelaDeAdmin();
		AdminController adminController = new AdminController(telaDeAdmin,usuarioDAO,navegador);
		
		TelaDeCadastroUsuarios telaDeCadastroUsuarios = new TelaDeCadastroUsuarios();
		CadastroController cadastroController = new CadastroController(telaDeCadastroUsuarios, usuarioDAO, navegador);
		
		
		navegador.adicionarPainel("LOGIN", telaDeLogin);
		navegador.adicionarPainel("ADMIN", telaDeAdmin);
		navegador.adicionarPainel("CADASTRO", telaDeCadastroUsuarios);
		
		
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		
		navegador.navegarPara("LOGIN");
		
		

	}

}
