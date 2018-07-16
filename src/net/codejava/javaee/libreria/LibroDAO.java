package net.codejava.javaee.libreria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractDAO.java
 * La clase DAO provee todas las operaciones CRUD de la tabla libro in la Base de datos 
 * @author GRUPO 5 UNLAM
 *
 */
public class LibroDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public LibroDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	public void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}
	
	public void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	public boolean ingresarLibro(Libro libro) throws SQLException {
		String sql = "INSERT INTO libro (titulo, autor, precio) VALUES (?, ?, ?)";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, libro.getTitulo());
		statement.setString(2, libro.getAutor());
		statement.setFloat(3, libro.getPrecio());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}
	
	public List<Libro> listarLibros() throws SQLException {
		List<Libro> listLibro = new ArrayList<>();
		
		String sql = "SELECT * FROM libro";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("libro_id");
			String titulo = resultSet.getString("titulo");
			String autor = resultSet.getString("autor");
			float precio = resultSet.getFloat("precio");
			
			Libro libro = new Libro(id, titulo, autor, precio);
			listLibro.add(libro);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listLibro;
	}
	
	public boolean borrarLibro(Libro libro) throws SQLException {
		String sql = "DELETE FROM libro where libro_id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, libro.getId());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;		
	}
	
	public boolean actualizarLibro(Libro libro) throws SQLException {
		String sql = "UPDATE libro SET titulo = ?, autor = ?, precio = ?";
		sql += " WHERE libro_id = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, libro.getTitulo());
		statement.setString(2, libro.getAutor());
		statement.setFloat(3, libro.getPrecio());
		statement.setInt(4, libro.getId());
		
                boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;		
	}
	
	public Libro getLibro(int id) throws SQLException {
		Libro libro = null;
		String sql = "SELECT * FROM libro WHERE libro_id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String titulo = resultSet.getString("titulo");
			String autor = resultSet.getString("autor");
			float precio = resultSet.getFloat("precio");
			
			libro = new Libro(id, titulo, autor, precio);
		}                
		
		resultSet.close();
		statement.close();
		
		return libro;
	}
}
