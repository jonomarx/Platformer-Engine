package com.jonmarx.game.creator;

import com.jonmarx.gfx.Screen;
import com.jonmarx.level.Level;
import com.jonmarx.level.entities.Entity;
import com.jonmarx.level.tiles.Tile;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Jon
 */
public class PaintPanel extends JPanel {
    Level l;
    
    public PaintPanel(Level l) {
        super();
        this.l = l;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Screen.render(g, l, 0, 0);
    }
}
