package Assets.example;

import static com.jonmarx.game.Main.PIXELSIZE;
import static com.jonmarx.game.Main.SIZE;
import com.jonmarx.level.Level;
import com.jonmarx.level.entities.Entity;
import com.jonmarx.level.tiles.Tile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jon
 */
public class GoombaEntity extends Entity {

    public GoombaEntity(double x, double y, BufferedImage image) {
        super(x, y, image, false);
    }

    @Override
    public void render(Graphics g, double xLoc, double yLoc) {
        g.drawImage(b, Math.round((float) Math.abs(xLoc - x * SIZE)), (int) Math.round(Math.abs((float) yLoc - y * SIZE)), PIXELSIZE, PIXELSIZE, null);
    }

    @Override
    public void update(Level level, double xLoc, double yLoc) {
        double speed = level.getProperty("GRAVITY");
        Tile tile1 = level.getTileSet()[(int) Math.floor(x)][(int) Math.ceil(y + speed)];
        Tile tile2 = level.getTileSet()[(int) Math.ceil(x)][(int) Math.ceil(y + speed)];
        
        if(tile1 == null || tile2 == null) return;
        
        if(tile1.isPermeable() || tile2.isPermeable()) {
            
        } else {
            y += speed;
        }
        
        if(level.getTileSet()[(int) Math.ceil(x + direction / 2)][(int) Math.ceil(y)].isPermeable() || level.getTileSet()[(int) Math.floor(x + direction)][(int) Math.ceil(y)].isPermeable()) {
            direction *= -1;
        } else {
            x += direction;
        }       
        System.out.println(x);
    }
    double direction = 0.02;
    
}
