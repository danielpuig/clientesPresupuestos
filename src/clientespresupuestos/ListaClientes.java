/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientespresupuestos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author danielpuig
 */
public class ListaClientes implements Serializable {

    private ArrayList<Cliente> lista;

    public ListaClientes() {
        lista = new ArrayList<>();
    }
    
    public void altaCliente(Cliente c) {
        boolean exists = false;
        for(Cliente clienteActual:lista) {
            if(clienteActual.getTelefono().equalsIgnoreCase(c.getTelefono())) {
                System.out.println("Ya hay un cliente con este telefono");
                exists = true;
            }
        }
        if(!exists) {
            lista.add(c);
        }
    }
    
    public boolean existsPresupuesto(int numPresupuesto){
        boolean exists = false;
        for(Cliente clienteActual: lista){
            if(clienteActual.getLista().existsPresupuestoNum(numPresupuesto)){
                exists = true;
                break;
            }
        }
        return exists;
    }
    
    public boolean clienteExists (String numero) {
        boolean exists = false;
        for(Cliente clienteActual:lista) {
            if(clienteActual.getTelefono().equalsIgnoreCase(numero)) {
                exists = true;
            }
        }
        return exists;
    }
    
    public Cliente getByNum(String numero) {
        for(Cliente clienteActual:lista) {
            if(clienteActual.getTelefono().equalsIgnoreCase(numero)) {
                return clienteActual;
            }
        }
        return null;
    }
    
    public void removeCliente(Cliente c) {
        lista.remove(c);
    }

    public ArrayList<Cliente> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Cliente> lista) {
        this.lista = lista;
    }

}