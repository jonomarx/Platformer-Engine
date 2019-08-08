package com.jonmarx.level.tiles;

import com.jonmarx.level.Level;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Tile {
    protected int x;
    protected int y;
    protected boolean permeable;
    protected BufferedImage b;
    
    public Tile(int x, int y, BufferedImage image, boolean permeable) {
        this.x = x;
        this.y = y;
        this.permeable = permeable;
        b = image;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public boolean isPermeable() {
        return permeable;
    }
    
    public abstract void render(Graphics g, double xLoc, double yLoc);
    public abstract void update(Level l);
}
