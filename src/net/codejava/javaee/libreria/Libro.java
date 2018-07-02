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
        
        /**
        * Función que elimina acentos y caracteres especiales de
        * una cadena de texto.
        * @param input
        * @return cadena de texto limpia de acentos y caracteres especiales.
        */
        public static String remove(String input) {
            // Cadena de caracteres original a sustituir.
            String original = "áàäéèëíìïóòöúùüñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
            // Cadena de caracteres ASCII que reemplazarán los originales.
            String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
            String output = input;
            for (int i=0; i<original.length(); i++) {
                // Reemplazamos los caracteres especiales.
                output = output.replace(original.charAt(i), ascii.charAt(i));
            }//for i
            return output;
        }
        
        
        
        private static final String ORIGINAL
        = "ÁáÉéÍíÓóÚúÑñÜü";
private static final String REPLACEMENT
        = "AaEeIiOoUuNnUu";
public static String stripAccents(String str) {
    if (str == null) {
        return null;
    }
    char[] array = str.toCharArray();
    for (int index = 0; index < array.length; index++) {
        int pos = ORIGINAL.indexOf(array[index]);
        if (pos > -1) {
            array[index] = REPLACEMENT.charAt(pos);
        }
    }
    return new String(array);
}
        
}
