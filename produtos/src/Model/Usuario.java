package Model;

public class Usuario {
	
	private int id;
	private String usuario;
	private String cpf;
	private boolean admin;

	public Usuario(String usuario, String cpf, boolean admin) {
		
		this.usuario = usuario;
		this.cpf = cpf;
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
}