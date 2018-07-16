package net.codejava.javaee.libreria;

/** 
 * Este es la clase modelo que representa a la entidad libro
 * @author GRUPO 5 UNLAM
 *
 */
public class Libro {
	protected int id;
	protected String titulo;
	protected String autor;
	protected float precio;

	public Libro() {
	}

	public Libro(int id) {
		this.id = id;
	}

	public Libro(int id, String titulo, String autor, float precio) {
		this(titulo, autor, precio);
		this.id = id;
	}
	
	public Libro(String titulo, String autor, float precio) {
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
        
}
