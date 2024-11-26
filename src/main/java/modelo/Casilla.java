/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author bejar
 */
public class Casilla {
    private Ficha ficha;
    public Casilla(Ficha ficha) {
        this.ficha = ficha;
    }

    public Casilla() {
    ficha=null;
    }

    @Override
    public String toString() {
        return getFicha()!=null?getFicha().toString():"__"; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    /**
     * @return the ocupado
     */
    public boolean isOcupado() {
        return getFicha()!=null;
    }

    /**
     * @param ocupado the ocupado to set
     */
    public void setOcupado(boolean ocupado) {
    }

    /**
     * @return the ficha
     */
    public Ficha getFicha() {
        return ficha;
    }

    /**
     * @param ficha the ficha to set
     */
    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
    
}
