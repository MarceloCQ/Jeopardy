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
    private boolean bloqueada;

    public Usuario(String usuario, String contraseña, boolean cambioContraseña, boolean bloqueada) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.cambioContraseña = cambioContraseña;
        this.bloqueada = bloqueada;
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

    public boolean isBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(boolean bloqueada) {
        this.bloqueada = bloqueada;
    }
    
    
    
    
    
}
