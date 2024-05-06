import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class Gun {

    //Declare variables

    private int x, y, width, height;
    private Color col;
    private ImageIcon imggun;

    //Initiate variables
    public Gun() {
        x = 0;
        x = 0;
        width = 230;
        height = 230;
        col = Color.BLUE;
        imggun =  new ImageIcon("Gunroom.png");

    }

    //Set no-arg constructor
    public Gun(String img, int xp, int yp, int w, int h) {
        x = xp;
        y = yp;
        width = w;
        height = h;
        col = null;
        imggun = new ImageIcon(img);
    }

    //Initiate Graphics2D to draw
    public void drawGun(Graphics2D g2) {
        g2.drawImage(imggun.getImage(), x, y, width,
                height, null);
    }

    //Set or Return properties of the Gun
    public void setImage(String img) {
        imggun = new ImageIcon(img);
    }

    //Set and Return properties of the gun
    public void setX(int xp) {
        x = xp;
    }

    public void setY(int yp) {
        y = yp;
    }

    public void setWidth(int w) {
        width = w;
    }

    public void setHeight(int h) {
        height = h;
    }

    public void setDimensions(int w, int h) {
        width = w;
        height = h;
    }

    public void setColor(Color c) {
        col = c;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return col;
    }

    public void setLocation(int xp, int yp) {
        x = xp;
        y = yp;
    }


    public void moveVertical (int ypixels) {
        y += ypixels;
    }
    public void moveHorizontal(int xpixels) {
        x += xpixels;
    }

    //Set a getBound method to check for intersection
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }









}