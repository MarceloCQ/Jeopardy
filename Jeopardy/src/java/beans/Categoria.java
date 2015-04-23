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
public class Categoria {
    int id;
    String nombre;
    ArrayList<Pista> pistas;

    public Categoria(int id, String nombre, ArrayList<Pista> pistas) {
        this.id = id;
        this.nombre = nombre;
        this.pistas = pistas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Pista> getPistas() {
        return pistas;
    }

    public void setPistas(ArrayList<Pista> pistas) {
        this.pistas = pistas;
    }
    
    
    
}
