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
public class RevistaDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
        private Libro libro;
	
	public RevistaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
			jdbcConnection = DriverManager.getConnection(
										jdbcURL, jdbcUsername, jdbcPassword);
		}
	}
	
	public void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
        
        public List<Revista> listarRevista() throws SQLException {
		List<Revista> listRevista = new ArrayList<>();
		
		String sql = "SELECT * FROM revista";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("revista_id");
			String editorial = resultSet.getString("editorial");
			boolean escomic = resultSet.getBoolean("escomic");
			String superheroe = resultSet.getString("superheroe");
			Revista revista = new Revista(id,editorial,escomic,superheroe);
			listRevista.add(revista);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listRevista;
	}
        
        public boolean ingresarRevista(Revista revista, Libro libro) throws SQLException {
		
            libro = new Libro();
            
            String sql = "INSERT INTO Revista (titulo, autor, precio) VALUES (?, ?, ?)";
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
        
}




