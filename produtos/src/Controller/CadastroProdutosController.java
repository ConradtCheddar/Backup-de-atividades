package Controller;

import Model.Produto;
import Model.ProdutoDAO;
import Model.Usuario;
import Model.UsuarioDAO;
import View.TelaDeCadastroProdutos;

public class CadastroProdutosController {

	private TelaDeCadastroProdutos view;
	private ProdutoDAO model;
	private Navegador navegador;

	public CadastroProdutosController(TelaDeCadastroProdutos view, ProdutoDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;
		
		this.view.setItems();
		
		this.view.cadastrarProdutos(e ->{
			String nome_produtos = view.getNome_Produto();
			String categoria = view.getCategoria();
			Double preco = Double.parseDouble(view.getPreco());
			String descricao = view.getDescricao();
			int estoque = Integer.parseInt(view.getEstoque());
			
			
			ProdutoDAO dao = new ProdutoDAO(navegador);
			Produto p = new Produto(nome_produtos ,categoria ,preco, descricao, estoque);
				try {
					dao.cadastrarProdutos(p);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

		});
		this.view.voltar(e ->{
			navegador.navegarPara("LOGIN");
		});
	}

}
