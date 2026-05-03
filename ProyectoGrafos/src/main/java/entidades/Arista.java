/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author edgar
 */
public class Arista {
    private Nodo origen;
    private Nodo destino;
    private int peso;

    /**
     *
     * @param origen
     * @param destino
     * @param peso
     */
    public Arista(Nodo origen, Nodo destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public Nodo getOrigen() {
        return origen;
    }

    public void setOrigen(Nodo origen) {
        this.origen = origen;
    }

    public Nodo getDestino() {
        return destino;
    }

    public void setDestino(Nodo destino) {
        this.destino = destino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    public Nodo getOtroNodo(Nodo nodo) {
        if (nodo.equals(origen)) {
            return destino;
        }
        if (nodo.equals(destino)) {
            return origen;
        }
        return null;
    }
}
