package Controller;

import java.util.ArrayList;
import java.util.StringJoiner;

import javax.swing.JOptionPane;

import Model.Produto;
import Model.ProdutoDAO;
import View.TelaDeCarrinho;

public class CarrinhoController {
    private final ProdutoDAO model;
    private final Navegador navegador;
    private final TelaDeCarrinho view;

    public CarrinhoController(TelaDeCarrinho view, ProdutoDAO model, Navegador navegador) {
        this.view = view;
        this.model = model;
        this.navegador = navegador;

        // Carregar itens do carrinho ao iniciar
        carregarCarrinho();

        // Voltar para a tela de compra
        this.view.voltar(e -> {
            navegador.navegarPara("compra");
        });

        // Comprar: processa somente o que está no carrinho
        this.view.comprar(e -> {
            try {
                ArrayList<Produto> itens = model.getCarrinho();
                if (itens == null || itens.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Carrinho vazio", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Validações: verificar estoque disponível para cada item
                StringJoiner insuf = new StringJoiner("\n");
                for (Produto item : itens) {
                    Produto atual = model.buscarProdutoPorId(item.getId());
                    if (atual == null) {
                        insuf.add(String.format("Produto ID %d não encontrado no estoque", item.getId()));
                    } else if (atual.getQ_estoque() < item.getQ_estoque()) {
                        insuf.add(String.format("%s: disponível %d, pedido %d", atual.getNome_produto(), atual.getQ_estoque(), item.getQ_estoque()));
                    }
                }

                if (insuf.length() > 0) {
                    JOptionPane.showMessageDialog(view, "Não há estoque suficiente para:\n" + insuf.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Todas as validações ok — atualizar estoque no banco e montar nota
                double total = 0.0;
                StringBuilder nota = new StringBuilder();
                nota.append("Nota fiscal da compra:\n\n");

                for (Produto item : itens) {
                    Produto atual = model.buscarProdutoPorId(item.getId());
                    if (atual == null) continue; // já validado

                    int novoEstoque = atual.getQ_estoque() - item.getQ_estoque();
                    atual.setQ_estoque(novoEstoque);

                    boolean ok = model.atualizarProduto(atual.getId(), atual);
                    if (!ok) {
                        JOptionPane.showMessageDialog(view, "Erro ao atualizar estoque para: " + atual.getNome_produto(), "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    double subtotal = item.getPreco() * item.getQ_estoque();
                    total += subtotal;
                    nota.append(String.format("%s - qtd: %d - R$ %.2f\n", atual.getNome_produto(), item.getQ_estoque(), subtotal));
                }

                nota.append(String.format("\nValor total: R$ %.2f", total));

                // Limpar carrinho e recarregar tabelas
                model.limparCarrinho();
                carregarCarrinho();

                JOptionPane.showMessageDialog(view, nota.toString(), "Compra efetuada", JOptionPane.INFORMATION_MESSAGE);

                // Voltar/navegar para a tela de compra (que por sua vez recarrega os produtos)
                navegador.navegarPara("compra");

            } catch (Exception ex) {
                System.err.println("[ERROR] Erro ao processar compra do carrinho: " + ex.getMessage());
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, "Erro ao processar compra: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Atualizar a tabela sempre que a tela for mostrada
        navegador.addShowListener("CARRINHO", () -> carregarCarrinho());
    }

    private void carregarCarrinho() {
        try {
            var itens = model.getCarrinho();
            if (itens != null) {
                view.atualizarTable(itens);
                System.out.println("[DEBUG] Carrinho atualizado com " + itens.size() + " itens");
            } else {
                view.atualizarTable(new ArrayList<>());
                System.out.println("[DEBUG] Carrinho vazio");
            }
        } catch (Exception e) {
            System.err.println("[ERROR] Erro ao carregar carrinho: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erro ao carregar carrinho", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}