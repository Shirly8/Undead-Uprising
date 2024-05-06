
import java.awt.Color;
        import java.awt.Graphics2D;
        import java.awt.Rectangle;
        import javax.swing.JPanel;

public class Bullets {

    //Declare variables
    private int x, y, width, height;
    private Color c;
    boolean visible;


    //Initiate variables
    public Bullets(int i, int j, int k, int l, Color co) {
        x = i;
        y= j;
        width = k;
        height = l;
        c = co;

    }

    //Set no-arg constructor
    public Bullets() {
        x = 100;
        y = 50;
        width = 10;
        height = 5;
        c = Color.BLACK;
    }

    // Method to allow user to draw bullets
    public void drawBullets (int xp, int yp, int w, int h, Color Co) {
        x = xp;
        y = yp;
        width = w;
        height = h;
        c = Co;
    }

    //Initiate Graphics2D to draw
    public void drawBullets(Graphics2D g2) {
        g2.setColor(c);
        g2.fillRect(x, y, width, height);
    }

    //Set and return Properties of the Bullet
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setY(int yp) {
        y = yp;
    }
    public void setX(int xp) {
        x = xp;
    }

    public void move(int xPixels, int yPixels) {
        x += xPixels;
        y += yPixels;

    }
    public void moveHorizontal (int xpixels) {
        x += xpixels;
    }
    public void moveVertical(int ypixels) {
        y += ypixels;
    }

    public void setLocation(int xp, int yp) {
        x = xp;
        y = yp;
    }

    //Set a getBound method to check for intersection
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }



}
