package com.jonmarx.util;

import static com.jonmarx.game.Main.PIXELSIZE;
import static com.jonmarx.game.Main.SIZE;
import com.jonmarx.level.Level;
import com.jonmarx.level.tiles.Tile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jon
 */
public class BasicSolidTile extends Tile {

    public BasicSolidTile(int x, int y, BufferedImage image) {
        super(x, y, image, true);
    }

    @Override
    public void render(Graphics g, double xLoc, double yLoc) {
        g.drawImage(b, (int) Math.round((float) Math.abs(xLoc - x * SIZE)), (int) Math.round(Math.abs((float) yLoc - y * SIZE)), PIXELSIZE, PIXELSIZE, null);
    }

    @Override
    public void update(Level l) {
        
    }
    
}
