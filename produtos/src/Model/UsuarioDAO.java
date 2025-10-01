package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Controller.Navegador;

public class UsuarioDAO {
	
	Navegador n = new Navegador(null);
	
	public Usuario login(String nome, String cpf) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/MercadoBD",
				    "mercado",
				    "1234"
				);

			String sql = "SELECT * FROM Usuarios WHERE nome_usuario = ? AND CPF = ?";
			var stmt = conn.prepareStatement(sql);

			stmt.setString(1, nome);
			stmt.setString(2, cpf);

			var rs = stmt.executeQuery();

			if (rs.next()) {
				Usuario u = new Usuario(rs.getString("nome_usuario"), rs.getString("CPF"),rs.getBoolean("admin"));
				rs.close();
				stmt.close();
				conn.close();
				return u;
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public void cadastrar(Usuario u) throws ClassNotFoundException {

			if (u.getUsuario().isEmpty() || u.getCpf().isEmpty()) {

				JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);

			} else {

					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = DriverManager.getConnection(
								"jdbc:mysql://localhost:3306/MercadoBD",
							    "mercado",
							    "1234"
							);

						String sql = "INSERT INTO Usuarios (nome_usuario, CPF, admin) VALUES (?, ?, ?)";
						var stmt = conn.prepareStatement(sql);
						
						stmt.setString(2, u.getUsuario()); // Nome
						stmt.setString(3, u.getCpf()); // CPF
						stmt.setBoolean(6, u.isAdmin()); // Admin

						stmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "Sucesso!",
								JOptionPane.PLAIN_MESSAGE);
						n.navegarPara("LOGIN");

						stmt.close();
						conn.close();
					} catch (SQLException e) {
					    e.printStackTrace();
					    JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage());
				;
			}

		} 
	}
	}