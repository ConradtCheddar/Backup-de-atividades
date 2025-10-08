package Model;

public class Produto {
	
	private String nome_produto;
	private String categoria;
	private Double preco;
	private String descricao;
	private int q_estoque;

	public Produto(String nome_produto, String categoria, Double preco, String descricao, int q_estoque) {
		this.nome_produto = nome_produto;
		this.categoria = categoria;
		this.preco = preco;
		this.descricao = descricao;
		this.q_estoque = q_estoque;
		
	}

	public String getNome_produto() {
		return nome_produto;
	}

	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQ_estoque() {
		return q_estoque;
	}

	public void setQ_estoque(int q_estoque) {
		this.q_estoque = q_estoque;
	}

	


	
	
	

	
	
	
}