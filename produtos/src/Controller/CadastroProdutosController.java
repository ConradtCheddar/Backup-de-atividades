package Controller;

import Model.Produto;
import Model.ProdutoDAO;
import View.TelaDeCadastroProdutos;
import javax.swing.JOptionPane;

public class CadastroProdutosController {

    private TelaDeCadastroProdutos view;
    private ProdutoDAO model;
    private Navegador navegador;

    public CadastroProdutosController(TelaDeCadastroProdutos view, ProdutoDAO model, Navegador navegador) {
        this.view = view;
        this.model = model;
        this.navegador = navegador;
        
        this.view.setItems();
        
        this.view.cadastrarProdutos(e -> {
            try {
                String nome_produtos = view.getNome_Produto();
                String categoria = view.getCategoria();
                String precoStr = view.getPreco();
                String descricao = view.getDescricao();
                String estoqueStr = view.getEstoque();
                
                if (nome_produtos.isEmpty() || categoria.isEmpty() || precoStr.isEmpty() || 
                    descricao.isEmpty() || estoqueStr.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Double preco = Double.parseDouble(precoStr);
                int estoque = Integer.parseInt(estoqueStr);
                
                Produto p = new Produto(nome_produtos, categoria, preco, descricao, estoque);
                model.cadastrarProdutos(p);
                
                // Clear the fields after successful registration
                view.clearFields();
                
                // Navigate back to products screen
                navegador.navegarPara("Produtos");
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Formato inválido para preço ou estoque", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Erro ao cadastrar produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        this.view.voltar(e -> {
            navegador.navegarPara("Produtos");
        });
    }
}