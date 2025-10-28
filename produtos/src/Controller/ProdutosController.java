package Controller;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import Model.Produto;
import Model.ProdutoDAO;
import View.TelaDeProdutos;

public class ProdutosController {
    private final ProdutoDAO model;
    private final Navegador navegador;
    private final TelaDeProdutos view;
    
    public ProdutosController(TelaDeProdutos view, ProdutoDAO model, Navegador navegador) {
        this.view = view;
        this.model = model;
        this.navegador = navegador;
        
        // Carregar produtos iniciais
        this.carregarProdutos();
        
        // Configurar ações dos botões
        this.view.visualizar(e -> {
            Integer id = this.view.getSelectedId();
            if (id != null) {
                Produto p = this.model.buscarProdutoPorId(id);
                if (p != null) {
                    String info = String.format(
                        "Detalhes do Produto:\n\n" +
                        "ID: %d\n" +
                        "Nome: %s\n" +
                        "Categoria: %s\n" +
                        "Preço: R$ %.2f\n" +
                        "Descrição: %s\n" +
                        "Quantidade em Estoque: %d",
                        p.getId(), p.getNome_produto(), p.getCategoria(),
                        p.getPreco(), p.getDescricao(), p.getQ_estoque()
                    );
                    JOptionPane.showMessageDialog(this.view, info, "Visualizar Produto", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this.view, "Selecione um produto para visualizar", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        this.view.adicionar(e -> {
            navegador.navegarPara("CADPRO");
        });
        
        this.view.editar(e -> {
            JTable table = view.getTable();
            if (table.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this.view, "Selecione um produto para editar", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (table.isEditing()) {
                table.getCellEditor().stopCellEditing();
            }
            
            try {
                int row = table.getSelectedRow();
                Integer id = (Integer)table.getValueAt(row, 0);
                String nome = (String)table.getValueAt(row, 1);
                String categoria = (String)table.getValueAt(row, 2);
                Double preco = Double.valueOf(table.getValueAt(row, 3).toString());
                String descricao = (String)table.getValueAt(row, 4);
                Integer estoque = Integer.valueOf(table.getValueAt(row, 5).toString());
                
                Produto p = new Produto(nome, categoria, preco, descricao, estoque);
                p.setId(id);
                
                if (model.atualizarProduto(id, p)) {
                    JOptionPane.showMessageDialog(this.view, "Produto atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    carregarProdutos(); // Recarrega a tabela após atualização
                } else {
                    JOptionPane.showMessageDialog(this.view, "Erro ao atualizar produto", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.view, "Erro ao ler dados da tabela: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        this.view.deletar(e -> {
            Integer id = this.view.getSelectedId();
            if (id != null) {
                int confirm = JOptionPane.showConfirmDialog(
                    this.view,
                    "Tem certeza que deseja deletar este produto?",
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    if (model.deletarProduto(id)) {
                        JOptionPane.showMessageDialog(this.view, "Produto deletado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        carregarProdutos(); // Recarrega a tabela após deleção
                    } else {
                        JOptionPane.showMessageDialog(this.view, "Erro ao deletar produto", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this.view, "Selecione um produto para deletar", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        this.view.voltar(e -> {
            navegador.navegarPara("LOGIN");
        });
        
        // Registrar para receber notificações quando a tela for exibida
        navegador.addShowListener("Produtos", () -> carregarProdutos());
    }
    
    private void carregarProdutos() {
        try {
            ArrayList<Produto> produtos = model.buscarTodosProdutos();
            view.atualizarTable(produtos);
            System.out.println("[DEBUG] Carregados " + (produtos != null ? produtos.size() : 0) + " produtos");
        } catch (Exception e) {
            System.err.println("[ERROR] Erro ao carregar produtos: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(this.view, "Erro ao carregar produtos", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}