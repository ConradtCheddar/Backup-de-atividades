package Controller;

import Model.Usuario;
import Model.UsuarioDAO;
import View.TelaDeCadastroUsuarios;
import javax.swing.JOptionPane;

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
			
			// Validações básicas antes de tentar cadastrar
			if (nome == null || nome.isEmpty() || cpf == null || cpf.isEmpty()) {
				JOptionPane.showMessageDialog(view, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String cpfNorm = cpf.replaceAll("\\D+", "");
			if (cpfNorm.length() != 11) {
				JOptionPane.showMessageDialog(view, "CPF inválido. Insira 11 dígitos.", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			// Checar se CPF já existe
			String existente = this.model.buscarNomePorCPF(cpf);
			if (existente != null && !existente.isEmpty()) {
				JOptionPane.showMessageDialog(view, "CPF já cadastrado para o usuário: " + existente, "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Usuario u = new Usuario(nome ,cpf ,this.view.getcheckAdmin().isSelected());
			try {
				this.model.cadastrarU(u);
				this.view.limparCampos();
				navegador.navegarPara("LOGIN");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		this.view.voltar(e ->{
			this.view.limparCampos();
			navegador.navegarPara("LOGIN");
		});
		
	}
}