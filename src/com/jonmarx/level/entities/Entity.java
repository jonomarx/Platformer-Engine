package com.jonmarx.level.entities;

import com.jonmarx.level.Level;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jon
 */
public abstract class Entity {
    protected double x;
    protected double y;
    protected boolean permeable;
    protected BufferedImage b;
    
    public Entity(double x, double y, BufferedImage image, boolean permeable) {
        this.x = x;
        this.y = y;
        this.permeable = permeable;
        b = image;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public boolean getPermeable() {
        return permeable;
    }
    
    public abstract void render(Graphics g, double xLoc, double yLoc);
    public abstract void update(Level level, double xLoc, double yLoc);
}
