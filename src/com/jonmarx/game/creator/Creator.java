package com.jonmarx.game.creator;

import com.jonmarx.level.Level;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.Timer;

/**
 *
 * @author Jon
 */
public class Creator {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        boolean done = false;
        int x = 0;
        int y = 0;
        
        while(!done) {
            try {
                x = Integer.parseInt(JOptionPane.showInputDialog("How wide should the level be?"));
                done = true;
            } catch(Exception e) {
                
            }
        }
        done = false;
        while(!done) {
            try {
                y = Integer.parseInt(JOptionPane.showInputDialog("How tall should the level be?"));
                done = true;
            } catch(Exception e) {
                
            }
        }
        Level level = Level.createLevel(x, y);
        PaintPanel pp = new PaintPanel(level);
        frame.setContentPane(new JScrollPane(pp));
        
        Timer t = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.repaint();
            }
            
        });
        t.start();
        
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save code
            }
        });
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open code
            }
        });
        
        file.add(save);
        file.add(open);
        bar.add(file);
        
        JMenu modify = new JMenu("Modify");
        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean done = false;
                int x = 0;
                int y = 0;
        
                while(!done) {
                    try {
                        x = Integer.parseInt(JOptionPane.showInputDialog("How wide should the level be?"));
                        done = true;
                    } catch(Exception ex) {
                
                    }
               }
               done = false;
               while(!done) {
                   try {
                       y = Integer.parseInt(JOptionPane.showInputDialog("How tall should the level be?"));
                       done = true;
                   } catch(Exception ex) {
                       
                   }
               }
               while(!done) {
                   try {
                       String[] values = new String[] {"com.jonmarx.level.tiles.VoidTile", "Assets.util.BasicFloorTile", "Assets.util.BasicSolidTile"};
                       
                       done = true;
                   } catch(Exception ex) {
                       
                   }
               }
            }
            
        });
        
        bar.add(modify);
        frame.setJMenuBar(bar);
        
        frame.setVisible(true);
    }
}
