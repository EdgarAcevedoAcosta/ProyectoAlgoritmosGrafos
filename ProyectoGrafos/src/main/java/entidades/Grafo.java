/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author edgar
 */
public class Grafo {
    private Map<Integer, Nodo> nodos;
    private List<Arista> aristas;
    private List<Nodo> entradas;
    private List<Nodo> salidas;
    private int filas;
    private int colmnas;

    /**
     * Para declarar el grafo cuantas columnas y filas va a tener y se declaran las demas para que sean guiados o no guiados
     * @param filas numero de filas
     * @param colmnas numero de columnas
     */
    public Grafo(int filas, int colmnas) {
        this.filas = filas;
        this.colmnas = colmnas;
        this.aristas=new ArrayList<>();
        this.entradas=new ArrayList<>();
        this.nodos=new HashMap<>();
        this.salidas=new ArrayList<>();
                
    }  
    
    /**
     * Agrega un Nodo al grafo
     * @param nodo Nodo que se va agregar
     */
    public void agregarNodo(Nodo nodo){
        nodos.put(nodo.getIdNodo(), nodo);
        if (nodo.getColumna() == 0){
            entradas.add(nodo);
        }if(nodo.getColumna() == colmnas-1){
            salidas.add(nodo);
        }
    }
    
    /**
     * Agrega una arista a los nodos de origen y entrada
     * @param a Arista
     */
    public void agregarArista(Arista a){
        aristas.add(a);
        a.getOrigen().agregarArista(a);
        a.getDestino().agregarArista(a);   
    }
    
    /**
     * Obtiene un nodo segun la fila y columna del grafo
     * @param fila numero de fila
     * @param columna numero de columna
     * @return el nodo en el que se refiere
     */
    public Nodo getNodo(int fila, int columna){
        return nodos.get(fila*colmnas+ columna);
    }

    public Map<Integer, Nodo> getNodos() {
        return nodos;
    }

    public void setNodos(Map<Integer, Nodo> nodos) {
        this.nodos = nodos;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    public void setAristas(List<Arista> aristas) {
        this.aristas = aristas;
    }

    public List<Nodo> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<Nodo> entradas) {
        this.entradas = entradas;
    }

    public List<Nodo> getSalidas() {
        return salidas;
    }

    public void setSalidas(List<Nodo> salidas) {
        this.salidas = salidas;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColmnas() {
        return colmnas;
    }

    public void setColmnas(int colmnas) {
        this.colmnas = colmnas;
    }
    
    
}
