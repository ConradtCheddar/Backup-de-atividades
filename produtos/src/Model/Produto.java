package Model;

public class Produto {
	
	private String nome_produto;
	private String categoria;
	private Double preco;
	private String descricao;
	private int q_estoque;
	private int id;

	public Produto(String nome_produto, String categoria, Double preco, String descricao, int q_estoque) {
		this.nome_produto = nome_produto;
		this.categoria = categoria;
		this.preco = preco;
		this.descricao = descricao;
		this.q_estoque = q_estoque;
		
	}
	
	public Produto(int id, String nome_produto, String categoria, Double preco, String descricao, int q_estoque) {
        this.id = id;
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
		if (nome_produto == null || nome_produto.length() > 20) {
            throw new IllegalArgumentException("Nome do produto não pode ser nulo e deve ter no máximo 20 caracteres");
        }
        this.nome_produto = nome_produto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		if (categoria == null || categoria.length() > 20) {
            throw new IllegalArgumentException("Categoria não pode ser nula e deve ter no máximo 20 caracteres");
        }
		this.categoria = categoria;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		if (preco == null || preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser nulo ou negativo");
        }
        // Ensure price string representation won't exceed VARCHAR(10)
        String precoStr = String.format("%.2f", preco);
        if (precoStr.length() > 10) {
            throw new IllegalArgumentException("Preço excede o tamanho máximo permitido");
        }
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		if (descricao == null) {
            throw new IllegalArgumentException("Descrição não pode ser nula");
        }
		this.descricao = descricao;
	}

	public int getQ_estoque() {
		return q_estoque;
	}

	public void setQ_estoque(int q_estoque) {
		if (q_estoque < 0) {
            throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa");
        }
		this.q_estoque = q_estoque;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	


	
	
	

	
	
	
}