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
public class Presupuesto {
    
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
    
    
    
}
