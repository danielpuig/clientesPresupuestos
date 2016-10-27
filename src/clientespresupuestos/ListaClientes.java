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

    public ArrayList<Cliente> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Cliente> lista) {
        this.lista = lista;
    }

    public void altaCliente(Cliente c) {
        this.lista.add(c);
    }

}
