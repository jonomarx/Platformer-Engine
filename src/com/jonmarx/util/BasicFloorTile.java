package com.jonmarx.util;

import static com.jonmarx.game.Main.PIXELSIZE;
import static com.jonmarx.game.Main.SIZE;
import com.jonmarx.level.Level;
import com.jonmarx.level.tiles.Tile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BasicFloorTile extends Tile {

    public BasicFloorTile(int x, int y, BufferedImage image) {
        super(x, y, image, false);
    }

    @Override
    public void render(Graphics g, double xLoc, double yLoc) {
        g.drawImage(b, x * SIZE, y * SIZE, PIXELSIZE, PIXELSIZE, null);
    }

    @Override
    public void update(Level level) {
        
    }
    
}
