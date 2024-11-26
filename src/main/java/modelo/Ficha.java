/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author bejar
 */
public class Ficha {
//0=peon,1=torre,2=caballo,3=alfil,4=rey,5=reina

    private final int TIPOFICHA;
    //1=blanco;2=negro
    private final int COLOR;

    public Ficha(int tipoFicha, int color) {
        this.TIPOFICHA = tipoFicha;
        this.COLOR = color;
    }

    public String getTIPOFICHA() {
        String nombreFicha = switch (TIPOFICHA) {
            case 0 ->
                "Peon";
            case 1 ->
                "Torre";
            case 2 ->
                "Caballo";
            case 3 ->
                "Alfil";
            case 4 ->
                "Rey";
            case 5 ->
                "Reina";
            default ->
                null;
        };
        return nombreFicha;
    }

    /**
     * @return the COLOR
     */
    public int getCOLOR() {
        return COLOR;
    }
    public int getTIPO() {
    return TIPOFICHA;
    }

    @Override
    public String toString() {
    String nombreFicha = switch (TIPOFICHA) {
            case 0 ->
                COLOR+"P";
            case 1 ->
                COLOR+"T";
            case 2 ->
                COLOR+"C";
            case 3 ->
                COLOR+"A";
            case 4 ->
                COLOR+"R";
            case 5 ->
                COLOR+"Q";
            default ->
                null;
        };
        return nombreFicha;}
    
}
