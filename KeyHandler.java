/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dijkstra;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author macbookpro
 */
public class KeyHandler implements KeyListener {
    private final PathfinderPanel dp;
    
    public KeyHandler(PathfinderPanel dp) {
        this.dp = dp;
    }
    
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        
        if(key == KeyEvent.VK_ENTER)
            dp.busquedaAutomatica();
        else if(key == KeyEvent.VK_BACK_SPACE)
            dp.buscar();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
}