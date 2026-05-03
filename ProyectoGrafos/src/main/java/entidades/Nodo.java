/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edgar
 */
public class Nodo {
    private int idNodo;
    private int fila;
    private int columna;
    private List<Arista> adyacentes;
    private Point posicion;

    /**
     *
     * @param idNodo
     * @param fila
     * @param columna
     */
    public Nodo(int idNodo, int fila, int columna) {
        this.idNodo = idNodo;
        this.fila = fila;
        this.columna = columna;
        this.adyacentes=new ArrayList<>();
        this.posicion= new Point(columna*40+20,fila*40+20);
    }

    /**
     *
     * @param arista
     */
    public void agregarArista(Arista arista){
        adyacentes.add(arista);
    }

    public int getIdNodo() {
        return idNodo;
    }

    public void setIdNodo(int idNodo) {
        this.idNodo = idNodo;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public List<Arista> getAdyacentes() {
        return adyacentes;
    }

    public void setAdyacentes(List<Arista> adyacentes) {
        this.adyacentes = adyacentes;
    }

    public Point getPosicion() {
        return posicion;
    }

    public void setPosicion(Point posicion) {
        this.posicion = posicion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.idNodo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nodo other = (Nodo) obj;
        return true;
    }
    
    
}
