/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dijkstra;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author macbookpro
 */
public class Nodo extends JButton {
    public Nodo padre;
    public int col, fila, costoG, costoH, costoF;
    public boolean inicio, meta, solido, abierto, chequeado;

    public Nodo(int col, int fila) {
        this.col = col;
        this.fila = fila;
        
        setBackground(Color.white);
        setForeground(Color.black);
    }
    
    public void setComoInicio() {
        setBackground(Color.darkGray);
        setForeground(Color.white);
        setText("INICIO");
        inicio = true;
    }
    
    public void setComoMeta() {
        setBackground(Color.green);
        setForeground(Color.black);
        setText("META");
        meta = true;
    }
    
    public void setComoSolido() {
        setBackground(Color.black);
        setForeground(Color.black);
        solido = true;
    }
    
    public void setComoAbierto() {
        abierto = true;
    }
    
    public void setComoChequeado() {
        if(inicio == false && meta == false) {
            setBackground(Color.lightGray);
            setForeground(Color.black);
        }
        chequeado = true;
    }
    
    public void setComoCamino() {
        setBackground(Color.green);
        setForeground(Color.black);
    }
}