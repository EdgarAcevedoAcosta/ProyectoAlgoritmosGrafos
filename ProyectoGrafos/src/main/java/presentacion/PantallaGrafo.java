/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import algoritmos.Dijktra;
import entidades.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;

/**
 *
 * @author edgar
 */
public class PantallaGrafo extends JPanel{
    private Grafo grafo;
    private Dijktra.CaminoResultado resultado;
    private int cAncho;
    private int cAlto;

    /**
     * panel del grafo para mostrar el grafo
     */
    public PantallaGrafo() {
         setBackground(new java.awt.Color(239, 239, 239));
         setPreferredSize(new Dimension(842, 369));
         addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint();
            }
        });
    }
    
    /**
     * selecciona el grado para poder pintarlo
     * @param gr el grafo seleccinado
     */
    public void setGrafo(Grafo gr){
        this.grafo=gr;
        this.resultado =Dijktra.encontrarMejorSalida(gr);
        repaint();
    }

    /**
     * para pintar toda los cuadros y la imagen por asi decirlo del grafo y su solucion
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D gr2= (Graphics2D) g;
        //dibujar celdas
        gr2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(grafo==null)return;
        cAncho=getWidth()/grafo.getColmnas();
        cAlto=getHeight()/grafo.getFilas();
        //dibuar celdas
        for(int i=0;i<grafo.getFilas();i++){
            for(int j = 0; j < grafo.getColmnas(); j++){
                Rectangle rect=new Rectangle(j*cAncho,i*cAlto,cAncho,cAlto);
                Nodo nodo=grafo.getNodo(i, j);
                if(resultado!=null && resultado.camino!=null && resultado.camino.contains(nodo)){
                    gr2.setColor(new Color(144, 238, 144, 100)); 
                }else if(j == 0 && grafo.getEntradas().contains(nodo)){
                    gr2.setColor(new Color(173, 216, 230, 100)); 
                }else if(j == grafo.getColmnas() - 1 && grafo.getSalidas().contains(nodo)){
                    gr2.setColor(new java.awt.Color(153, 102, 255)); 
                }else{
                    gr2.setColor(Color.WHITE);
                }
                gr2.fill(rect);
                gr2.setColor(Color.LIGHT_GRAY);
                gr2.draw(rect);
            }
        }
        //dibujar aristas
        gr2.setStroke(new BasicStroke(2));
        for(Arista arista:grafo.getAristas()){
            Nodo origen=arista.getOrigen();
            Nodo destino=arista.getDestino();
            Point p1=new Point(origen.getColumna()*cAncho+cAncho/2,origen.getFila()*cAlto+cAlto/2);
            Point p2=new Point(destino.getColumna()*cAncho+cAncho/2,destino.getFila()*cAlto+cAlto/2);
            if(resultado!=null && resultado.camino!=null && resultado.camino.contains(origen) 
                    && resultado.camino.contains(destino)){
                gr2.setColor(new Color(34, 139, 34)); 
                gr2.setStroke(new BasicStroke(4));
            }else{
                gr2.setColor(Color.BLACK);
                gr2.setStroke(new BasicStroke(2));
            }
            gr2.drawLine(p1.x, p1.y, p2.x, p2.y);
            if(cAncho>50){
                gr2.setColor(Color.RED);
                gr2.setFont(new Font("Segoe UI Emoji", 0, 14));
                int medioX=(p1.x+p2.x)/ 2;
                int medioY=(p1.y+p2.y)/ 2;
                gr2.drawString(String.valueOf(arista.getPeso()), medioX, medioY);
            }
        }
        //Dibujar aristas
        int radio = Math.min(cAncho, cAlto)/6;
        for(Nodo nodo:grafo.getNodos().values()){
            Point centro=new Point(nodo.getColumna()*cAncho+cAncho/2,
                    nodo.getFila()*cAlto+cAlto/2);

            if(nodo.getColumna() == 0 && grafo.getEntradas().contains(nodo)){
                gr2.setColor(Color.BLUE);
                gr2.fillOval(centro.x - radio,centro.y-radio,radio*2,radio*2);
                gr2.setColor(Color.WHITE);
                gr2.drawString("E",centro.x-3,centro.y+4);
            }else if(nodo.getColumna()==grafo.getColmnas()-1 && grafo.getSalidas().contains(nodo)){
                gr2.setColor(Color.RED);
                gr2.fillOval(centro.x - radio, centro.y - radio, radio * 2, radio * 2);
                gr2.setColor(Color.WHITE);
                gr2.drawString("S", centro.x - 3, centro.y + 4);
            }else{
                gr2.setColor(Color.BLACK);
                gr2.fillOval(centro.x - radio / 2, centro.y - radio / 2, radio, radio);
            }
        }
        // mostrar info del camino
        if (resultado != null && resultado.hayCaminoPosible()) {
            gr2.setFont(new Font("Segoe UI Emoji", 0, 14));
            gr2.setColor(new Color(0, 100, 0));
            String info = String.format("Ruta encontrada! Distancia: ", resultado.distancia);
            gr2.drawString(info, 10, 25);
        } else if (resultado != null && !resultado.hayCaminoPosible()) {
            gr2.setFont(new Font("Segoe UI Emoji", 0, 14));
            gr2.setColor(Color.RED);
            gr2.drawString("No hay camino hacia la salida", 10, 25);
        }
    }
}
