package Main;

import Controller.CadastroProdutosController;
import Controller.CadastroUsuariosController;
import Controller.LoginController;
import Controller.Navegador;
import Model.ProdutoDAO;
import Model.UsuarioDAO;
import View.Janela;
import View.TelaDeCadastroProdutos;
import View.TelaDeCadastroUsuarios;
import View.TelaDeLogin;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Janela janela = new Janela();
		Navegador navegador =new Navegador(janela);
		UsuarioDAO usuarioDAO = new UsuarioDAO(navegador);
		ProdutoDAO produtoDAO = new ProdutoDAO(navegador);
		
		TelaDeLogin telaDeLogin = new TelaDeLogin();
		LoginController loginController = new LoginController(telaDeLogin, usuarioDAO, navegador);
		
		TelaDeCadastroProdutos telaDeCadastroProdutos = new TelaDeCadastroProdutos();
		CadastroProdutosController cadastroProdutosController = new CadastroProdutosController(telaDeCadastroProdutos,produtoDAO,navegador);
		
		TelaDeCadastroUsuarios telaDeCadastroUsuarios = new TelaDeCadastroUsuarios();
		CadastroUsuariosController cadastroController = new CadastroUsuariosController(telaDeCadastroUsuarios, usuarioDAO, navegador);
		
		
		navegador.adicionarPainel("LOGIN", telaDeLogin);
		navegador.adicionarPainel("CADPRO", telaDeCadastroProdutos);
		navegador.adicionarPainel("CADUSU", telaDeCadastroUsuarios);
		
		
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		
		navegador.navegarPara("LOGIN");
		
		

	}

}
