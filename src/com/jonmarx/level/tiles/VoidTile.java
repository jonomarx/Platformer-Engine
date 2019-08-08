package com.jonmarx.level.tiles;

import static com.jonmarx.game.Main.PIXELSIZE;
import static com.jonmarx.game.Main.SIZE;
import com.jonmarx.level.Level;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class VoidTile extends Tile {

    public VoidTile(int x, int y, BufferedImage image) {
        super(x, y, image, false);
    }

    @Override
    public void render(Graphics g, double xLoc, double yLoc) {
        g.setColor(Color.BLACK);
        g.fillRect(Math.round((float) Math.abs(xLoc - x * SIZE)), Math.round(Math.abs((float) yLoc - y * SIZE)), PIXELSIZE, PIXELSIZE);
    }

    @Override
    public void update(Level l) {
        
    }
    
}
