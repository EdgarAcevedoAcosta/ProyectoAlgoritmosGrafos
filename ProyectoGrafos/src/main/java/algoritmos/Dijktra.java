/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;

import entidades.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author edgar
 */
public class Dijktra {
    
    /**
     * Utiliza los Algoritmos Dijktra, BFS y Ordenamiento Topografico, no sabia si 
     * hacerlo en separado por eso los hice Juntos
     * @param gr El grafo que see va a encontrar la ruta mas corta
     * @param Inicio El Nodo Inicial
     * @return La distancia total del Nodo Inicial a uno de Salida
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
     * Regresa al camino Original, enlistando los nodos por los que paso
     * @param inicio Nodo en que Inicio el Camino
     * @param fin El Nodo en que Termina el Camino
     * @param previo El Nodo anterior que esta en el camino por el que tomo
     * @return Una Lista de el Camino que tomo
     */
    public static List<Nodo> reconstruirCamino(Nodo inicio, Nodo fin, Map<Nodo, Nodo> previo){
        List<Nodo> camino=new ArrayList<>();
        Nodo actual=fin;
        while(actual !=null){
            camino.add(0, actual);
            actual=previo.get(actual);
        }if(actual !=null ){
            camino.add(0, inicio);
        }
        return camino;
    }
    
    /**
     * Es oara encontrar una mejor salida para el grafo
     * @param gr El Grafo seleccionado
     * @return El Camino del grafo para la mejor salida y entrada, declarando su distancia y 
     * una lista del camino
     */
    public static CaminoResultado encontrarMejorSalida(Grafo gr){
        Nodo mInicio=null;
        Nodo mSalida=null;
        int distanciaMin=Integer.MAX_VALUE;
//        List<Nodo> mejorCamino=null;
        CaminoResultado MejorResult=null;
        
//        List<Nodo> mejorCamino=null;
        for(Nodo entrada: gr.getEntradas()){
            
//            Map<Nodo, Integer> distancias= encontrarRMCorta(gr,entrada);
            for(Nodo salida: gr.getSalidas()){
//                int distancia=distancias.get(salida);
                CaminoResultado res=CaminoCompleto(gr,entrada,salida);
                if(res.hayCaminoPosible()){
                   if(res.distancia<distanciaMin){
                       distanciaMin = res.distancia;
                       mInicio = entrada;
                       mSalida = salida;
                       MejorResult = res;
                   }
                }else{
                    System.out.println("sin conexion");
                }
            }
        }
        if (MejorResult != null){
            MejorResult.imprimirCamino();
        }
        return MejorResult;
    }
    
    /**
     * Utiliza los Algoritmos Dijktra, BFS y Ordenamiento Topografico, no sabia si 
     * hacerlo en separado por eso los hice Juntos
     * @param gr El grafo que see va a encontrar la ruta mas corta
     * @param Inicio El Nodo Inicial
     * @return La distancia total del Nodo Inicial a uno de Salida
     */
    public static Map<Nodo, Integer> encontrarRMCorta2(Grafo gr, Nodo Inicio) {
        Map<Nodo, Integer> distancias = new HashMap<>();
        Map<Nodo, Nodo> previo = new HashMap<>();
        PriorityQueue<Nodo> cola = new PriorityQueue<>(Comparator.comparing(distancias::get));
        //iniciar distancias
        for (Nodo n : gr.getNodos().values()) {
            distancias.put(n, Integer.MAX_VALUE);
            previo.put(n, null);
        }
        distancias.put(Inicio, 0);
        cola.add(Inicio);
        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            for (Arista ar : actual.getAdyacentes()) {
                Nodo vecino = ar.getOtroNodo(actual);
                int nuevaDist = distancias.get(actual) + ar.getPeso();
                if (nuevaDist < distancias.get(vecino)) {
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
     * @param gr
     * @param Inicio
     * @param destino
     * @return
     */
    public static CaminoResultado CaminoCompleto(Grafo gr, Nodo Inicio, Nodo destino){
        Map<Nodo, Integer> distancias=new HashMap<>();
        Map<Nodo, Nodo> previo= new HashMap<>();
        Set<Nodo> visitados = new HashSet<>();
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
            if(visitados.contains(actual))continue;
            visitados.add(actual);
            if(actual.equals(destino)){
                break;
            }
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
        
        
        //Reconstruir camino
//        List<Nodo> camino=reconstruirCamino(Inicio,destino,previo);
        List<Nodo> camino = new ArrayList<>();
        Nodo act=destino;
        while(act!=null){
            camino.add(0,act);
            act=previo.get(act);
        }
        int distT=distancias.get(destino);
        return new CaminoResultado(Inicio,destino,distT,camino,previo);
    }
    
    public static CaminoResultado encontrarMejorSalida2(Grafo gr){
        Nodo mInicio=null;
        Nodo mSalida=null;
        Map<Nodo, Nodo> previo= new HashMap<>();
        int distanciaMin=Integer.MAX_VALUE;
        List<Nodo> mejorCamino=null;
        for(Nodo entrada: gr.getEntradas()){
            Map<Nodo, Integer> distancias= encontrarRMCorta2(gr,entrada);
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
            Map<Nodo, Integer> distancias = encontrarRMCorta2(gr, mInicio);
            
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
        return new CaminoResultado(mInicio, mSalida, distanciaMin, mejorCamino, previo);
    }
    
    public static class CaminoResultado{
        public final Nodo inicio;
        public final Nodo salida;
        public final int distancia;
        public final List<Nodo> camino;
        public final Map<Nodo, Nodo> previos;

        /**
         * Contructor para declarar el camino que va a tomar el grafo, En cual inicio, 
         * Cual es su salida, la distancia que va recorrer y la lista del camino
         * @param inicio Nodo Inicial
         * @param salida Nodo de Salida
         * @param distancia Distancia Total del Camino
         * @param camino Lista de la Ruta o Camino que a reccorido
         */
        public CaminoResultado(Nodo inicio, Nodo salida, int distancia, List<Nodo> camino, Map<Nodo, Nodo> previos) {
            this.inicio = inicio;
            this.salida = salida;
            this.distancia = distancia;
            this.camino = camino;
            this.previos=previos;
        }
        
        /** 
         * Metodo para ver si el camino seleccionado realmente puede llegar a una salida
         * @return si existe una salida true, false si es contrario
         */
        public boolean hayCaminoPosible(){
            return distancia!= Integer.MAX_VALUE && camino !=null && !camino.isEmpty();
        }
        
        public String imprimirCamino(){
            String lista=" ";
            for (int i = 0; i < camino.size(); i++) {
                lista+="["+camino.get(i).getColumna()+", "+camino.get(i).getFila()+"], ";
                if(i<camino.size()-1){
                    lista+="-> ";
                }
            }
            return lista;
        }
    }
}
