package com.jonmarx.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class Main {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final int SIZE = 16;
    public static final int PIXELSIZE = 16;
    public static final KeyObserver KEYLISTENER = new KeyObserver();
    
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        
        Game game;
        
        if(args.length == 1) {
            game = new Game(args[0]);
        } else if(args.length > 1) {
            JOptionPane.showMessageDialog(null, "Use:\n args.length = 0: default level\n args.length = 1: args[0] is path to jar/zip to level");
            System.exit(-1);
            game = null;
        } else {
            game = new Game();
        }
        
        frame.setContentPane(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.addKeyListener(KEYLISTENER);
        
        Timer t1 = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.update();
            }
        });
        t1.start();
        Timer t2 = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.repaint(); // Repaint the Entire Frame
            }
        });
        t2.start();
    }
    
}
