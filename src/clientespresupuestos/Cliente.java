/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientespresupuestos;

import java.io.Serializable;

/**
 *
 * @author danielpuig
 */
public class Cliente implements Serializable {
    
    private String nombre;
    private String apellidos;
    private String telefono;
    private boolean vip;
    private ListaPresupuestos lista;

    public Cliente() {
    }

    public Cliente(String nombre, String apellidos, String telefono, boolean vip) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.vip = vip;
        lista = new ListaPresupuestos();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public ListaPresupuestos getLista() {
        return lista;
    }

    public void setLista(ListaPresupuestos lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono + ", vip=" + vip + ", lista=" + lista + '}';
    }
    
}
