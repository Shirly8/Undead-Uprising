import java.awt.Rectangle;
import javax.swing.JPanel;
import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.Color;

//
// Decompiled by Procyon v0.5.36
//

public class Zombie
{
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private Color col;
    private ImageIcon imgZombie;
    private boolean drawImage;
    private int xspeed;
    private int yspeed;
    private int direction;
    public static final int EAST = 0;
    public static final int WEST = 1;
    private Random rnd;

    public Zombie() {
        this.rnd = new Random();
        this.xPos = 0;
        this.yPos = 0;
        this.width = 100;
        this.height = 100;
        this.col = Color.BLUE;
        this.imgZombie = null;
        this.drawImage = false;
        this.direction = 0;
        this.xspeed = this.rnd.nextInt(16) + 5;
        this.yspeed = this.rnd.nextInt(16) + 5;
        final int randomY = this.rnd.nextInt(2);
        if (randomY == 1) {
            this.yspeed = -this.yspeed;
        }
        if (this.direction == 0) {
            this.imgZombie = new ImageIcon("zombieeast.png");
        }
        else {
            this.imgZombie = new ImageIcon("zombiewest.png");
            this.xspeed = -this.xspeed;
        }
    }

    public Zombie(final String img, final int x, final int y, final int w, final int h) {
        this.xPos = x;
        this.yPos = y;
        this.width = w;
        this.height = h;
        this.col = null;
        this.imgZombie = new ImageIcon(img);
        this.drawImage = true;
    }

    public void drawmonster(final Graphics2D g2) {
        g2.drawImage(this.imgZombie.getImage(), this.xPos, this.yPos, this.width, this.height, null);
    }

    public void setImage(final String img) {
        this.imgZombie = new ImageIcon(img);
    }

    public void setX(final int x) {
        this.xPos = x;
    }

    public void setY(final int y) {
        this.yPos = y;
    }

    public void setWidth(final int w) {
        this.width = w;
    }

    public void setHeight(final int h) {
        this.height = h;
    }

    public void setDimensions(final int w, final int h) {
        this.width = w;
        this.height = h;
    }

    public void setColor(final Color c) {
        this.col = c;
    }

    public int getX() {
        return this.xPos;
    }

    public int getY() {
        return this.yPos;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Color getColor() {
        return this.col;
    }

    public void setLocation(final int x, final int y) {
        this.xPos = x;
        this.yPos = y;
    }

    public void moveVertical(final int ypixels) {
        this.yPos += ypixels;
    }

    public void moveHorizontal(final int xpixels) {
        this.xPos += xpixels;
    }

    public int getyspeed() {
        return this.yspeed;
    }

    public int getxspeed() {
        return this.xspeed;
    }

    public void move() {
        if (this.direction == 0) {
            this.xPos += 10;
        }
        else {
            this.xPos -= 10;
        }
        this.yPos += this.yspeed;
    }

    public void move(final int xPixels, final int yPixels) {
        this.xPos += xPixels;
        this.yPos += yPixels;
    }

    public void move(final JPanel panel, final int xPixels, final int yPixels) {
        if (this.yPos + this.height >= panel.getHeight()) {
            this.move(0, 0);
        }
        else {
            this.move(xPixels, yPixels);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(this.xPos, this.yPos, this.width, this.height);
    }

    public void setdirection(final int dir) {
        this.direction = dir;
        if (this.direction == 0) {
            this.imgZombie = new ImageIcon("zombieeast.png");
            if (this.xspeed < 0) {
                this.xspeed = -this.xspeed;
            }
            this.yspeed = -this.yspeed;
        }
        else if (this.direction == 1) {
            this.imgZombie = new ImageIcon("zombiewest.png");
            if (this.xspeed > 0) {
                this.xspeed = -this.xspeed;
            }
            this.yspeed = -this.yspeed;
        }
    }

    public int getDirection() {
        return this.direction;
    }

    public void setyspeed(final int x) {
        this.yspeed = x;
    }
}
