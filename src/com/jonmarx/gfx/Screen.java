package com.jonmarx.gfx;

import com.jonmarx.game.Main;
import com.jonmarx.level.Level;
import com.jonmarx.level.entities.Entity;
import com.jonmarx.level.tiles.Tile;
import java.awt.Graphics;

public class Screen {
    public static void render(Graphics g, Level l, double xLoc, double yLoc) {
        for(Tile[] ti : l.getTileSet()) {
            for(Tile t : ti) {
                if(t == null) continue;
                t.render(g, xLoc, yLoc);
            }
        }
        
        int rightX = ((int) xLoc) + Main.WIDTH;
        int leftX = ((int) xLoc);
        
        int topY = ((int) yLoc);
        int bottomY = ((int) yLoc) + Main.HEIGHT;
        
        for(Entity e : l.getEntities()) {
            if(e.getX() > leftX && e.getX() < rightX) {
                e.render(g, xLoc, yLoc);
            }
            if(e.getY() > topY && e.getY() < bottomY) {
                e.render(g, xLoc, yLoc);
            }
        }
    }
    
    public static void update(Level l, double xLoc, double yLoc) {
        for(Tile[] ti : l.getTileSet()) {
            for(Tile t : ti) {
                t.update(l);
            }
        }
        
        int rightX = ((int) xLoc) + Main.WIDTH / Main.PIXELSIZE + 4;
        int leftX = ((int) xLoc) - 4;
        
        int topY = ((int) yLoc) - 4;
        int bottomY = ((int) yLoc) + Main.HEIGHT / Main.PIXELSIZE + 4;
        
        for(Entity e : l.getEntities()) {
            /*if(e.getX() > leftX && e.getX() < rightX) {
                e.update(l);
            }
            if(e.getY() > topY && e.getY() < bottomY) {
                e.update(l);
            }*/
            e.update(l, xLoc, yLoc);
        }
    }
}
