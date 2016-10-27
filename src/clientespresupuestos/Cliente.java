/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientespresupuestos;

/**
 *
 * @author danielpuig
 */
public class Cliente {
    
    private String nombre;
    private String apellidos;
    private String telefono;
    private boolean vip;
    private ListaPresupuestos lista;

    public Cliente() {
    }

    public Cliente(String nombre, String apellidos, String telefono, boolean vip, ListaPresupuestos lista) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.vip = vip;
        this.lista = lista;
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
    
}
