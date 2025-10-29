package Controller;

import javax.swing.JOptionPane;
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
        
        this.view.confirmar(e -> {
            String nome = view.getUsuario();
            String cpf = view.getCPF();
            
            if (nome.isEmpty() || cpf.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Por favor, preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Usuario u = this.model.login(nome, cpf);
            
            if (u != null) {
                navegador.setCurrentUser(u);
                if (u.isAdmin() && this.view.getcheckAdmin().isSelected()) {
                    navegador.navegarPara("Produtos");
                } else if (!u.isAdmin() && !this.view.getcheckAdmin().isSelected()) {
                    navegador.navegarPara("compra");
                } else {
					JOptionPane.showMessageDialog(view, "Tipo de usuário incorreto selecionado", "Erro de Login", JOptionPane.ERROR_MESSAGE);
				}
            } else {
                // Se o login falhou, checar se o CPF existe associado a outro usuário para dar uma mensagem mais clara
                String nomeCadastrado = this.model.buscarNomePorCPF(cpf);
                if (nomeCadastrado != null && !nomeCadastrado.isEmpty() && !nomeCadastrado.equals(nome)) {
                    JOptionPane.showMessageDialog(view, "CPF já cadastrado para outro usuário: " + nomeCadastrado, "Erro de Login", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(view, "Usuário ou CPF incorretos", "Erro de Login", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            this.view.limparCampos();
        });
        
        this.view.SemCadastro(e -> {
        	this.view.limparCampos();
            navegador.navegarPara("CADUSU");
        });
    }
}