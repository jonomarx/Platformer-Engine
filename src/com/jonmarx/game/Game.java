package com.jonmarx.game;

import static com.jonmarx.game.Main.PIXELSIZE;
import com.jonmarx.gfx.Screen;
import com.jonmarx.level.Level;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.*;

public class Game extends JPanel {
    Level level;

    public Game() throws IOException {
        this.level = Level.getLevel(Level.class.getResource("/Assets/example/level.csv"));
    }
    
    public Game(String jarLoc) throws IOException {
        this.level = Level.getLevelFromJar(jarLoc);
    }
    
    @Override
    public void paint(Graphics g) {
        Screen.render(g, level, 0 * PIXELSIZE, 0 * PIXELSIZE);
    }
    
    public void update() {
        Screen.update(level, 3 * PIXELSIZE, 0 * PIXELSIZE);
    }
}
