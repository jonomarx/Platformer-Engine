package com.jonmarx.game;

import static com.jonmarx.game.Main.PIXELSIZE;
import com.jonmarx.gfx.Screen;
import com.jonmarx.level.Level;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.*;

public class Game extends JPanel {
    Level level;
    public static double xLoc = 0;
    public static double yLoc = 0;

    public Game(String jarLoc) throws IOException {
        this.level = Level.getLevelFromJar(jarLoc);
    }
    
    @Override
    public void paint(Graphics g) {
        Screen.render(g, level, xLoc * PIXELSIZE, yLoc * PIXELSIZE);
    }
    
    public void update() {
        Screen.update(level, xLoc * PIXELSIZE, yLoc * PIXELSIZE);
    }
}
