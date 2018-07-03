/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava.javaee.libreria;

/**
 *
 * @author juanpab
 */
public class Revista extends Libro{
    protected int id;
    private String editorial;
    private boolean escomic;
    private String superheroe;

    public Revista() {        
    }
    
    public Revista(int id, String editorial, boolean escomic, String superheroe) {
        this.id = id;
        this.editorial = editorial;
        this.escomic = escomic;
        this.superheroe = superheroe;
    }
    
    public Revista(int id, String editorial, boolean escomic, String superheroe, String titulo, String autor, float precio) {
        super(titulo, autor, precio);
        this.id = id;
        this.editorial = editorial;
        this.escomic = escomic;
        this.superheroe = superheroe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public boolean isEscomic() {
        return escomic;
    }

    public void setEscomic(boolean escomic) {
        this.escomic = escomic;
    }

    public String getSuperheroe() {
        return superheroe;
    }

    public void setSuperheroe(String superheroe) {
        this.superheroe = superheroe;
    }
    
    
    
    
            
    
}
