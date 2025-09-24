package Main;

import Controller.Navegador;
import Controller.AdminController;
import Controller.LoginController;
import Model.UsuarioDAO;
import View.Janela;
import View.TelaDeAdmin;
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
		
		navegador.adicionarPainel("LOGIN", telaDeLogin);
		navegador.adicionarPainel("ADMIN", telaDeAdmin);
		
		
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		
		navegador.navegarPara("LOGIN");
		
		

	}

}
