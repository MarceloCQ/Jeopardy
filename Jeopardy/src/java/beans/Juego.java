/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Timestamp;

/**
 *
 * @author Marcelo
 */


public class Juego {
    private int id;
    private Timestamp fecha;
    private int idPerfil;
    
    public Juego(int id, Timestamp fecha, int idPerfil) {
        this.id = id;
        this.fecha = fecha;
        this.idPerfil = idPerfil;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }
    
    
    
    
    
    
    
    
    
}



