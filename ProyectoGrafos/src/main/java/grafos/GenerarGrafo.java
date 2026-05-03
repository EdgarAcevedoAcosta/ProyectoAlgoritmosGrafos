/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos;

import entidades.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author edgar
 */
public class GenerarGrafo {
    private Random loco;

    /**
     * Constructor para crear el randomizador para poder declarar posteriormente 
     */
    public GenerarGrafo() {
        this.loco=new Random();
    }
    
    /**
     * Generador del grafo en si, crea los nodos y las aristas, tambien crea las 
     * conexiones con sus vecinos y Asignando un peso del 1 al 100 en la arista 
     * @param filas el numero de filas que va a tener el grafo (Lista)
     * @param columnas el numero de columnas que va a tener el grafo
     * @return el grafo generado
     */
    public Grafo generarGrafo(int filas, int columnas){
        Grafo grafo= new Grafo(filas, columnas);
        for(int i=0; i<filas; i++){
            for(int j=0; j<columnas;j++){
                Nodo nodo=new Nodo(i*columnas+j ,i,j);
                grafo.agregarNodo(nodo);
            }
        }
        // Para las conexiones
        boolean[][] conexHorizontal=new boolean[filas][columnas -1];
        boolean[][] conexVertical=new boolean[filas-1][columnas];
        //Algoritmo dfs
        boolean[][] visitando=new boolean[filas][columnas];
        Stack<int[]> pila=new Stack<>();
        
        int inicioFila=loco.nextInt(filas);
        int inicioColumn=loco.nextInt(columnas);
        pila.push(new int[]{inicioFila,inicioColumn});
        visitando[inicioFila][inicioColumn]=true;
        while(!pila.isEmpty()){
            int[] actual=pila.peek();
            int fila=actual[0];
            int columna=actual[1];
            //Obtener vecinos no vicitados
            List<int[]> vecinos= new ArrayList<>();
            /*Las distintas direcciones (arriba, abajo, izq y derecha)
            Alfinal de donde se agrega es cual es ==
            arriba -> 0
            abajo-> 1
            izq-> 2
            der-> 3
            */
            if(fila>0 && !visitando[fila-1][columna]){
                vecinos.add(new int[]{fila-1, columna,0});
            }if(fila< filas-1 && !visitando[fila+1][columna]){
                vecinos.add(new int[]{fila+1, columna,1});
            }if(columna>0 && !visitando[fila][columna-1]){
                vecinos.add(new int[]{fila, columna-1,2});
            }if(columna<columnas-1 && !visitando[fila][columna+1]){
                vecinos.add(new int[]{fila, columna+1,3});
            }
            if(!vecinos.isEmpty()){
                int[] vecino=vecinos.get(loco.nextInt(vecinos.size()));
                int nodoFila=vecino[0];
                int nodoColumna=vecino[1];
                int dir=vecino[2];
                
                //conexion nodos
                Nodo nodoActual=grafo.getNodo(fila, columna);
                Nodo nodoVecino=grafo.getNodo(nodoFila, nodoColumna);
                int peso= loco.nextInt(100)+1;
                Arista a=new Arista(nodoActual,nodoVecino,peso);
                grafo.agregarArista(a);
                
                if(dir==0 || dir==1){
                    int minFila= Math.min(fila, nodoFila);
                    conexVertical[minFila][columna]=true;
                }else{
                    int minColumna=Math.min(columna, nodoColumna);
                    conexHorizontal[fila][minColumna]=true;
                }
                
                visitando[nodoFila][nodoColumna]=true;
                pila.push(new int[]{nodoFila, nodoColumna});
            }else{
                pila.pop();
            }
        }
        
        int otrasConexiones= (filas*columnas)/4;
        for(int i=0; i<otrasConexiones; i++){
            int fila= loco.nextInt(filas);
            int columna= loco.nextInt(columnas);
            //intentar conectar con un vecino
            List<int[]> posiblesV=new ArrayList<>();
            if(columna <columnas-1 && !conexHorizontal[fila][columna]){
                posiblesV.add(new int[]{fila, columna+1});
            }if(fila<filas-1 && !conexVertical[fila][columna]){
                posiblesV.add(new int[]{fila+1,columna});
            }if(!posiblesV.isEmpty()){
                int[] vecino=posiblesV.get(loco.nextInt(posiblesV.size()));
                Nodo nodoActual=grafo.getNodo(fila, columna);
                Nodo nodoVecino= grafo.getNodo(vecino[0], vecino[1]);
                int peso=loco.nextInt(100)+1;
                Arista ar=new Arista(nodoActual, nodoVecino, peso);
                grafo.agregarArista(ar);
                if(vecino[1]==columna+1){
                    conexHorizontal[fila][columna]=true;
                }else{
                    conexVertical[fila][columna]=true;
                }
            }
        }
        return grafo;
    } 
}
