package Assets.example;

import static com.jonmarx.game.Main.KEYLISTENER;
import static com.jonmarx.game.Main.PIXELSIZE;
import static com.jonmarx.game.Main.SIZE;
import com.jonmarx.level.Level;
import com.jonmarx.level.entities.Entity;
import com.jonmarx.level.tiles.Tile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

/**
 *
 * @author Jon
 */
public class BasicPlayer extends Entity {

    public BasicPlayer(double x, double y, BufferedImage image) {
        super(x, y, image, false);
    }

    @Override
    public void render(Graphics g, double xLoc, double yLoc) {
        g.drawImage(b, Math.round((float) Math.abs(xLoc - x * SIZE)), (int) Math.round(Math.abs((float) yLoc - y * SIZE)), PIXELSIZE, PIXELSIZE, null);
    }

    int jumping = 0;
    @Override
    public void update(Level level, double xLoc, double yLoc) {
        double speed = level.getProperty("GRAVITY");
        boolean canJump = false;
        x = (double)Math.round(x * 10000d) / 10000d;
        y = (double)Math.round(y * 10000d) / 10000d;
        if(jumping > 0) {
            
            Tile tile1 = level.getTileSet()[(int) Math.floor(x)][(int) Math.floor(y - speed * 1.5)];
            Tile tile2 = level.getTileSet()[(int) Math.ceil(x)][(int) Math.floor(y - speed * 1.5)];
            if(tile1.isPermeable() || tile2.isPermeable()) {
                jumping = 0;
            } else {
                y -= speed * 1.5;
            }
            jumping--;
        } else {
            Tile tile1 = level.getTileSet()[(int) Math.floor(x)][(int) Math.ceil(y + speed)];
            Tile tile2 = level.getTileSet()[(int) Math.ceil(x)][(int) Math.ceil(y + speed)];
        
            if(tile1 == null || tile2 == null) return;
        
            if(tile1.isPermeable() || tile2.isPermeable()) {
                canJump = true;
            } else {
                y += speed;
            }
            System.out.println(x + ", " + y);
        }
        if(KEYLISTENER.isPressed('w')) {
            if(canJump) {
                jumping = 40;
            }
        }
        if(KEYLISTENER.isPressed('a')) {
            if(level.getTileSet()[(int) Math.ceil(x + speed * -0.5)][(int) Math.ceil(y)].isPermeable() || level.getTileSet()[(int) Math.floor(x + speed * -0.5)][(int) Math.ceil(y)].isPermeable()) {
                
            } else {
                x -= speed;
            }
        }
        if(KEYLISTENER.isPressed('s')) {
            //
        }
        if(KEYLISTENER.isPressed('d')) {
            if(level.getTileSet()[(int) Math.ceil(x + speed / 2)][(int) Math.ceil(y)].isPermeable() || level.getTileSet()[(int) Math.floor(x + speed / 2)][(int) Math.ceil(y)].isPermeable()) {
                
            } else {
                x += speed;
            }
        }
    }
    
}
