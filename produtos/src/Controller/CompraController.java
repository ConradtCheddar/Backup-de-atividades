package Controller;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import Model.Produto;
import Model.ProdutoDAO;
import View.TelaDeCompra;

public class CompraController {
    private final ProdutoDAO model;
    private final Navegador navegador;
    private final TelaDeCompra view;
    
    public CompraController(TelaDeCompra view, ProdutoDAO model, Navegador navegador) {
        this.view = view;
        this.model = model;
        this.navegador = navegador;
        
        // Carregar produtos iniciais
        carregarProdutos();
        
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
        
        // Configurar ação do botão comprar
        this.view.comprar(e -> {
            Integer id = view.getSelectedId();
            if (id == null) {
                JOptionPane.showMessageDialog(view, "Selecione um produto para comprar", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Integer quantidadeDisponivel = view.getSelectedQuantity();
            if (quantidadeDisponivel == null || quantidadeDisponivel <= 0) {
                JOptionPane.showMessageDialog(view, "Produto fora de estoque!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String quantidade = JOptionPane.showInputDialog(view, 
                "Quantidade desejada (disponível: " + quantidadeDisponivel + "):", 
                "Quantidade", 
                JOptionPane.QUESTION_MESSAGE);
            
            if (quantidade == null) {
                return; // Usuário cancelou
            }
            
            try {
                int qtd = Integer.parseInt(quantidade);
                if (qtd <= 0) {
                    JOptionPane.showMessageDialog(view, "Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (qtd > quantidadeDisponivel) {
                    JOptionPane.showMessageDialog(view, "Quantidade indisponível em estoque!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Buscar produto atual
                Produto produto = model.buscarProdutoPorId(id);
                if (produto == null) {
                    JOptionPane.showMessageDialog(view, "Erro ao buscar produto!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Calcular valor total
                double valorTotal = produto.getPreco() * qtd;
                
                // Confirmar compra
                int confirm = JOptionPane.showConfirmDialog(
                    view,
                    String.format("Confirmar compra de %d unidades de %s?\nValor total: R$ %.2f", 
                        qtd, produto.getNome_produto(), valorTotal),
                    "Confirmar Compra",
                    JOptionPane.YES_NO_OPTION
                );
                
                if (confirm == JOptionPane.YES_OPTION) {
                    // Atualizar estoque
                    produto.setQ_estoque(produto.getQ_estoque() - qtd);
                    if (model.atualizarProduto(id, produto)) {
                        JOptionPane.showMessageDialog(view, 
                            String.format("Compra realizada com sucesso!\nValor total: R$ %.2f", valorTotal),
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);
                            
                        // Recarregar a tabela imediatamente após a compra
                        carregarProdutos();
                    } else {
                        JOptionPane.showMessageDialog(view, "Erro ao processar compra!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Erro ao processar compra: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Configurar ação do botão voltar
        this.view.voltar(e -> {
            navegador.navegarPara("LOGIN");
        });
        
        // Registrar para receber notificações quando a tela for exibida
        navegador.addShowListener("compra", () -> carregarProdutos());
    }
    
    private void carregarProdutos() {
        try {
            var produtos = model.buscarTodosProdutos();
            if (produtos != null) {
                view.atualizarTable(produtos);
                System.out.println("[DEBUG] Tabela atualizada com " + produtos.size() + " produtos");
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao carregar produtos", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.err.println("[ERROR] Erro ao carregar produtos: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao carregar produtos", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}