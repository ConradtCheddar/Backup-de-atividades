package Main;

import Controller.Navegador;
import Model.UsuarioDAO;
import View.Janela;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Janela janela = new Janela();
		Navegador navegador =new Navegador(janela);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		

	}

}
