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
    int materiaID;

    public Categoria(int id, String nombre, int materiaID) {
        this.id = id;
        this.nombre = nombre;
        this.materiaID = materiaID;
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

    public int getMateriaID() {
        return materiaID;
    }

    public void setMateriaID(int materiaID) {
        this.materiaID = materiaID;
    }
    
    
    
}
