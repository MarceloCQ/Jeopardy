/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;

/**
 *
 * @author Marcelo
 */
public class Perfil {
    int id;
    String nombre;
    Materia materia;
    ArrayList<Categoria> categorias;

    public Perfil(int id, String nombre, Materia materia, ArrayList<Categoria> categorias) {
        this.nombre = nombre;
        this.materia = materia;
        this.categorias = categorias;
        this.id = id;
    }
    
    public Perfil(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
    
    
    
}
