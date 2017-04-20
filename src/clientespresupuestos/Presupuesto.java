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
public class Presupuesto implements Serializable{
    
    private int numPres;
    private String concepto;
    private double precioFinal;
    private String estado;

    public Presupuesto() {
    }

    public Presupuesto(int numPres, String concepto, double precioFinal, String estado) {
        this.numPres = numPres;
        this.concepto = concepto;
        this.precioFinal = precioFinal;
        this.estado = estado;
    }

    public int getNumPres() {
        return numPres;
    }

    public void setNumPres(int numPres) {
        this.numPres = numPres;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public double calcPrecioFinal(Cliente cliente){
        double precioDefinitivo;
        double precioVip;
        if(cliente.isVip()){
           precioVip = ((this.precioFinal)-(this.precioFinal*0.05));
           precioDefinitivo = ((precioVip*0.21)+(precioVip));
           
        }else{
            precioDefinitivo = ((this.precioFinal)+(this.precioFinal*0.21));
        }
        return precioDefinitivo;
    }
    
}
