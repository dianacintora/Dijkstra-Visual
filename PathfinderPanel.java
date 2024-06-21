/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dijkstra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PathfinderPanel extends JPanel {
    final int maxCol = 25, maxFila = 20, tamanoNodo = 80;
    final int anchoPantalla = tamanoNodo * maxCol;
    final int altoPantalla = tamanoNodo * maxFila;

    Nodo[][] nodo = new Nodo[maxCol][maxFila];
    Nodo nodoInicio, nodoMeta, nodoActual;
    
    ArrayList<Nodo> listaAbierta = new ArrayList<>();
    ArrayList<Nodo> listaChequeada = new ArrayList<>();
    
    boolean metaAlcanzada = false;
    int paso = 0;

    public PathfinderPanel() {
        this.setPreferredSize(new Dimension(anchoPantalla, altoPantalla));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(maxFila, maxCol));
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);
        
        int col = 0, fila = 0;
        
        while(col < maxCol && fila < maxFila) {
            nodo[col][fila] = new Nodo(col, fila);
            this.add(nodo[col][fila]);
            
            col++;
            if(col == maxCol) {
                col = 0;
                fila++;
            }
        }
        
        setNodoInicio(11, 8);
        setNodoMeta(20, 10);
        
        setNodoSolido(15, 7);
        setNodoSolido(15, 8);
        setNodoSolido(15, 9);
        setNodoSolido(15, 10);
        setNodoSolido(15, 11);
        setNodoSolido(15, 12);
        setNodoSolido(11, 7);
        setNodoSolido(12, 7);
        setNodoSolido(13, 7);
        setNodoSolido(14, 7);
        setNodoSolido(16, 12);
        setNodoSolido(17, 12);
        setNodoSolido(11, 6);
        
        setCostosNodos();
    }
    
    private void setNodoInicio(int col, int fila) {
        nodo[col][fila].setComoInicio();
        nodoInicio = nodo[col][fila];
        nodoActual = nodoInicio;
    }
    
    private void setNodoMeta(int col, int fila) {
        nodo[col][fila].setComoMeta();
        nodoMeta = nodo[col][fila];
    }
    
    private void setNodoSolido(int col, int fila) {
        nodo[col][fila].setComoSolido();
    }
    
    private void setCostosNodos() {
        int col = 0, fila = 0;
        
        while(col < maxCol && fila < maxFila) {
            calcularCosto(nodo[col][fila]);
            col++;
            if(col == maxCol) {
                col = 0;
                fila++;
            }
        }
    }
    
    private void calcularCosto(Nodo nodo) {
        int distanciaX = Math.abs(nodo.col - nodoInicio.col);
        int distanciaY = Math.abs(nodo.fila - nodoInicio.fila);
        nodo.costoG = distanciaX + distanciaY;
        
        distanciaX = Math.abs(nodo.col - nodoMeta.col);
        distanciaY = Math.abs(nodo.fila - nodoMeta.fila);
        nodo.costoH = distanciaX + distanciaY;
        
        nodo.costoF = nodo.costoG + nodo.costoH;
    }
    
    public void buscar() {
        if(metaAlcanzada == false && paso < 300) {
            int col = nodoActual.col, fila = nodoActual.fila;
            
            nodoActual.setComoChequeado();
            listaChequeada.add(nodoActual);
            listaAbierta.remove(nodoActual);
            
            if(fila - 1 >= 0)
                abrirNodo(nodo[col][fila - 1]);
            if(col - 1 >= 0)
                abrirNodo(nodo[col - 1][fila]);
            if(fila + 1 < maxFila)
                abrirNodo(nodo[col][fila + 1]);
            if(col + 1 < maxCol)
                abrirNodo(nodo[col + 1][fila]);
            
            int indiceMejorNodo = 0;
            int mejorCostoF = 999;
            
            for (int i = 0; i < listaAbierta.size(); i++) {
                if(listaAbierta.get(i).costoF < mejorCostoF) {
                    indiceMejorNodo = i;
                    mejorCostoF = listaAbierta.get(i).costoF;
                } else if(listaAbierta.get(i).costoF == mejorCostoF) {
                    if(listaAbierta.get(i).costoG < listaAbierta.get(indiceMejorNodo).costoG) {
                        indiceMejorNodo = i;
                    }
                }
            }
            nodoActual = listaAbierta.get(indiceMejorNodo);
            
            if(nodoActual == nodoMeta) {
                metaAlcanzada = true;
                trazarCamino();
            }
        }
        paso++;
    }
    
    public void busquedaAutomatica() {
        while(metaAlcanzada == false && paso < 300) {
            int col = nodoActual.col;
            int fila = nodoActual.fila;
            
            nodoActual.setComoChequeado();
            listaChequeada.add(nodoActual);
            listaAbierta.remove(nodoActual);
            
            if(fila - 1 >= 0)
                abrirNodo(nodo[col][fila - 1]);
            if(col - 1 >= 0)
                abrirNodo(nodo[col - 1][fila]);
            if(fila + 1 < maxFila)
                abrirNodo(nodo[col][fila + 1]);
            if(col + 1 < maxCol)
                abrirNodo(nodo[col + 1][fila]);
            
            int indiceMejorNodo = 0;
            int mejorCostoF = 999;
            
            for (int i = 0; i < listaAbierta.size(); i++) {
                if(listaAbierta.get(i).costoF < mejorCostoF) {
                    indiceMejorNodo = i;
                    mejorCostoF = listaAbierta.get(i).costoF;
                } else if(listaAbierta.get(i).costoF == mejorCostoF) {
                    if(listaAbierta.get(i).costoG < listaAbierta.get(indiceMejorNodo).costoG) {
                        indiceMejorNodo = i;
                    }
                }
            }
            nodoActual = listaAbierta.get(indiceMejorNodo);
            
            if(nodoActual == nodoMeta) {
                metaAlcanzada = true;
                trazarCamino();
            }
        }
        paso++; 
    }
    
    private void abrirNodo(Nodo nodo) {
        if(nodo.abierto == false && nodo.chequeado == false && nodo.solido == false) {
            nodo.setComoAbierto();
            nodo.padre = nodoActual;
            listaAbierta.add(nodo);
        }
    }
    
    private void trazarCamino() {
        Nodo actual = nodoMeta;
        int costo = 0;
        
        while(actual != nodoInicio) {
            actual = actual.padre;
            costo++;
            if(actual != nodoInicio) {
                actual.setComoCamino();
            }
        }
        
        JOptionPane.showConfirmDialog(this, "El costo total del recorrido fue de " + costo + " movimientos", "MEJOR RUTA ENCONTRADA...", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }
}