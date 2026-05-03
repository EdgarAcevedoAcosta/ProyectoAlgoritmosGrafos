/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;

import entidades.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author edgar
 */
public class Dijktra {
    
    /**
     *
     * @param gr
     * @param Inicio
     * @return
     */
    public static Map<Nodo, Integer> encontrarRMCorta(Grafo gr, Nodo Inicio){
        Map<Nodo, Integer> distancias=new HashMap<>();
        Map<Nodo, Nodo> previo= new HashMap<>();
        PriorityQueue<Nodo> cola=new PriorityQueue<>(Comparator.comparing(distancias::get));
        //iniciar distancias
        for (Nodo n : gr.getNodos().values()) {
            distancias.put(n, Integer.MAX_VALUE);
            previo.put(n, null);
        }
        distancias.put(Inicio, 0);
        cola.add(Inicio);
        while(!cola.isEmpty()){
            Nodo actual=cola.poll();
            for(Arista ar: actual.getAdyacentes()){
                Nodo vecino=ar.getOtroNodo(actual);
                int nuevaDist=distancias.get(actual)+ar.getPeso();
                if(nuevaDist< distancias.get(vecino)){
                    distancias.put(vecino, nuevaDist);
                    previo.put(vecino, actual);
                    cola.add(vecino);
                }
            }         
        }
        return distancias;
    }
    
    /**
     *
     * @param inicio
     * @param fin
     * @param previo
     * @return
     */
    public static List<Nodo> reconstruirCamino(Nodo inicio, Nodo fin, Map<Nodo, Nodo> previo){
        List<Nodo> camino=new ArrayList<>();
        Nodo actual=fin;
        while(actual !=null && !actual.equals(inicio)){
            camino.add(0, actual);
            actual=previo.get(actual);
        }if(actual !=null ){
            camino.add(0, inicio);
        }
        return camino;
    }
    
    /**
     *
     * @param gr
     * @return
     */
    public static CaminoResultado encontrarMejorSalida(Grafo gr){
        Nodo mInicio=null;
        Nodo mSalida=null;
        int distanciaMin=Integer.MAX_VALUE;
        List<Nodo> mejorCamino=null;
        for(Nodo entrada: gr.getEntradas()){
            Map<Nodo, Integer> distancias= encontrarRMCorta(gr,entrada);
            for(Nodo salida: gr.getSalidas()){
                int distancia=distancias.get(salida);
                if(distancia< distanciaMin && distancia != Integer.MAX_VALUE){
                    distanciaMin=distancia;
                    mInicio=entrada;
                    mSalida=salida;
                }
            }
        }
        if(mInicio !=null && mSalida!=null){
            Map<Nodo, Integer> distancias = encontrarRMCorta(gr, mInicio);
            Map<Nodo, Nodo> previo= new HashMap<>();
            PriorityQueue<Nodo> cola=new PriorityQueue<>(Comparator.comparingInt(distancias::get));
            cola.add(mInicio);
            while(!cola.isEmpty()){
                Nodo actual=cola.poll();
                for(Arista a: actual.getAdyacentes()){
                    Nodo vecino=a.getOtroNodo(actual);
                    if(distancias.get(vecino)== distancias.get(actual)+a.getPeso()){
                        previo.put(vecino, actual);
                        cola.add(vecino);
                    }
                }
            }
            mejorCamino=reconstruirCamino(mInicio, mSalida, previo);
        }
        return new CaminoResultado(mInicio, mSalida, distanciaMin, mejorCamino);
    }
    
    
    public static class CaminoResultado{
        public final Nodo inicio;
        public final Nodo salida;
        public final int distancia;
        public final List<Nodo> camino;

        /**
         *
         * @param inicio
         * @param salida
         * @param distancia
         * @param camino
         */
        public CaminoResultado(Nodo inicio, Nodo salida, int distancia, List<Nodo> camino) {
            this.inicio = inicio;
            this.salida = salida;
            this.distancia = distancia;
            this.camino = camino;
        }
        
        /**
         *
         * @return
         */
        public boolean hayCaminoPosible(){
            return distancia!= Integer.MAX_VALUE && camino !=null && !camino.isEmpty();
        }
    }
}
