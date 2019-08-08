package com.jonmarx.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Jon
 */
public class KeyObserver implements KeyListener {
    HashMap<Character, Boolean> map = new HashMap<>();
    {
        for(char c : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
            map.put(c, false);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        map.put(e.getKeyChar(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        map.put(e.getKeyChar(), false);
    }
    public boolean isPressed(char c) {
        return map.get(c);
    }
}
