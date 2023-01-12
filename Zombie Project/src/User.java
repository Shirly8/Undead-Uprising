import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class User {

    //Declare variables
    private int x, y, width, height;
    private Color col;
    private ImageIcon imgplayer;
    private boolean drawImage;
    private int direction;
    public static final int EAST = 0, WEST = 1;

    //Set no-arg constructor
    public User() {
        x = 0;
        x = 0;
        width = 120;
        height = 120;
        col = Color.BLUE;
        imgplayer = null;
        drawImage = false;
        direction = EAST;

        if (direction == EAST)
            imgplayer = new ImageIcon("Player_east.png");
        else {
            imgplayer = new ImageIcon("Player_west.png");
        }
    }

    public User(String img, int xp, int yp, int w, int h) {
        x = xp;
        y = yp;
        width = w;
        height = h;
        col = null;
        imgplayer = new ImageIcon(img);
        drawImage = true;
    }

    // Method to allow user to draw the player
    public void drawuser(Graphics2D g2) {

        g2.drawImage(imgplayer.getImage(), x, y, width,
                height, null);
    }

    //Set or Return properties of the player
    public void setImage(String img) {
        imgplayer = new ImageIcon(img);
    }

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

    //This method allows the user in various of direction
    public void move() {
        if (direction == EAST)
            x += 10;
        else
            x -= 10;
    }

    //This method allows the user to set how much they want to move
    public void move(int xPixels, int yPixels) {
        x += xPixels;
        y += yPixels;
    }


    //This method allows the user to stop moving at JPanel height
    public void move(JPanel panel, int xPixels, int yPixels) {

        if (y + height >= panel.getHeight()) {
            move(0, 0);
        }
        else {
            move(xPixels, yPixels);
        }
    }

    //Set a getBound method to check for intersection
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);

    }

    //Set Direction allows the images to be horizontally flipped so they are facing at different direction
    public void setDirection(int dir) {

        direction = dir;

        if (direction == EAST) {
            imgplayer = new ImageIcon("Player_east.png");
        }
        else if (direction == WEST){
            imgplayer = new ImageIcon("Player_west.png");
        }
    }

    public int getDirection() {
        return direction;
    }






}