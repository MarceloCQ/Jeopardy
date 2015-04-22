/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Marcelo
 */
public class Usuario {
    
    private String usuario;
    private String contraseña;
    private boolean cambioContraseña;

    public Usuario(String usuario, String contraseña, boolean cambioContraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.cambioContraseña = cambioContraseña;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean isCambioContraseña() {
        return cambioContraseña;
    }

    public void setCambioContraseña(boolean cambioContraseña) {
        this.cambioContraseña = cambioContraseña;
    }


}
