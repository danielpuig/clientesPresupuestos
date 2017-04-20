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
public class ListaPresupuestos implements Serializable{
    
    private ArrayList<Presupuesto> lista;
    
    public ListaPresupuestos() {
        lista = new ArrayList<>();
    }
    
    public boolean existsPresupuestoNum(int numPresupuesto){
        boolean exists = false;
        for(Presupuesto presupuestoActual:lista){
            if(presupuestoActual.getNumPres() == numPresupuesto){
                exists = true;
                break;
            }
        }
        return exists;
    }
    
    public void addPresupuesto(Presupuesto presupuesto){
        boolean exists = false;
        for(Presupuesto presupuestoActual:lista){
            if(presupuestoActual.getNumPres() == presupuesto.getNumPres()){
                System.out.println("Ya hay un presupuesto con ese numero");
                exists = true;
                break;
            }
        }
        if(!exists){
            lista.add(presupuesto);
        }
    }
    
    public void removePresupuesto(Presupuesto presupuesto){
        lista.remove(presupuesto);
    }

    public ArrayList<Presupuesto> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Presupuesto> lista) {
        this.lista = lista;
    }
    
    public ArrayList<Presupuesto> getPresupuestoByEstado(String estado){
        ArrayList<Presupuesto> getEstadoArray = new ArrayList<>();
        for(Presupuesto pres:this.lista){
            if(pres.getEstado().equalsIgnoreCase(estado)){
                getEstadoArray.add(pres);
            }
        }
        return getEstadoArray;
    }
    
}
