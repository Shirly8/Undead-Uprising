import java.awt.event.MouseEvent;
import javax.swing.Icon;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import java.awt.FontFormatException;
import java.io.IOException;
import java.awt.GraphicsEnvironment;
import java.awt.Font;
import java.io.File;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Container;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.36
// 

public class Main extends JPanel implements ActionListener, KeyListener, MouseMotionListener, MouseListener
{
    private Timer ZombieTimer;
    private Timer timer;
    private Timer bullettimer;
    private Timer bullettimer2;
    private int seconds;
    public User p1;
    public int controls;
    public int endgame;
    public int life;
    public int spawnhouse;
    public int numbullets;
    public Random rnd;
    Bullets b1;
    public boolean start;
    public boolean bullet;
    public boolean gameover;
    private int sectimer;
    public Zombie[] m1;
    int zombiecount;
    public Gun g1;

    public static void main(final String[] args) {
        new Main();
    }

    public Main() {
        this.life = 5;
        this.numbullets = 20;
        this.rnd = new Random();
        this.b1 = new Bullets();
        this.start = false;
        this.bullet = false;
        this.gameover = false;
        this.g1 = new Gun();
        this.zombiecount = Integer.parseInt(JOptionPane.showInputDialog(null, "WELCOME TO UNDEAD UPRISING!\n\nHow many zombies would you like to spawn?\n\n(10-20 zombies recommended)", "UNDEAD UPRISING", 1));
        this.m1 = new Zombie[this.zombiecount];
        this.sectimer = Integer.parseInt(JOptionPane.showInputDialog(null, "WELCOME TO UNDEAD UPRISING!\n\nPlease enter the speed of the zombie between 50 to 100\n\n(The lower the speed, the faster the zombies go)", "UNDEAD UPRISING", 1));
        (this.b1 = new Bullets()).drawBullets(this.b1.getX(), this.b1.getY(), 30, 10, Color.RED);
        this.seconds = 3;
        this.timer = new Timer(1000, this);
        this.ZombieTimer = new Timer(this.sectimer, this);
        this.bullettimer = new Timer(1, this);
        this.bullettimer2 = new Timer(1, this);
        for (int index = 0; index < this.m1.length; ++index) {
            (this.m1[index] = new Zombie()).setX(this.rnd.nextInt(1100) + 200);
            this.m1[index].setY(this.rnd.nextInt(500) + 200);
        }
        this.p1 = new User();
        this.g1 = new Gun();
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.setFocusable(true);
        final JFrame frame = new JFrame();
        frame.setContentPane(this);
        frame.setSize(1400, 800);
        frame.setTitle("UNDEAD UPRISING");
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D)g;
        Font customFont = null;
        try {
            customFont = Font.createFont(0, new File("src/Minecraft.ttf")).deriveFont(50.0f);
            final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (FontFormatException e2) {
            e2.printStackTrace();
        }
        if (!this.start) {
            final ImageIcon startpage = new ImageIcon("STARTPAGE.png");
            g2.drawImage(startpage.getImage(), 0, 0, this);
        }
        else if (this.start) {
            g2.setColor(Color.WHITE);
            g2.setFont(customFont);
            if (this.seconds != 0) {
                g2.drawString(Integer.toString(this.seconds), this.getWidth() / 2, 40);
                final ImageIcon zomb = new ImageIcon("zombie.gif");
                g2.drawImage(zomb.getImage(), this.getWidth() / 2 - zomb.getIconWidth() / 2, this.getHeight() / 2 - 300, this);
            }
            else {
                final ImageIcon img = new ImageIcon("background.png");
                g2.drawImage(img.getImage(), 0, 0, this);
                g2.setColor(Color.WHITE);
                g.drawString("Life:", 325, 50);
                g.drawString("Remaining Zombies:", this.getWidth() / 2 - 400, this.getHeight() - 20);
                g.drawString(Integer.toString(this.zombiecount), this.getWidth() / 2 + 100, this.getHeight() - 20);
                if (this.numbullets <= 0) {
                    g2.setColor(Color.RED);
                    g2.setStroke(new BasicStroke(10.0f));
                    g.drawString("Bullets:", 800, 50);
                    g.drawString(Integer.toString(this.numbullets), 1000, 50);
                    g.drawRect(this.g1.getX() - 5, this.g1.getY() - 5, this.g1.getWidth() + 5, this.g1.getHeight() + 5);
                }
                else if (this.numbullets >= 0) {
                    g2.setColor(Color.WHITE);
                    g.drawString("Bullets:", 800, 50);
                    g.drawString(Integer.toString(this.numbullets), 1000, 50);
                }
                this.g1.drawGun(g2);
                this.p1.drawuser(g2);
                for (int index = 0; index < this.m1.length; ++index) {
                    this.m1[index].drawmonster(g2);
                }
                final ImageIcon image = new ImageIcon("heart.png");
                if (this.life == 5) {
                    g2.drawImage(image.getImage(), this.getWidth() / 2 - 250, 15, this);
                    g2.drawImage(image.getImage(), this.getWidth() / 2 - 150, 15, this);
                    g2.drawImage(image.getImage(), this.getWidth() / 2 - 50, 15, this);
                    g2.drawImage(image.getImage(), this.getWidth() / 2 - 200, 15, this);
                    g2.drawImage(image.getImage(), this.getWidth() / 2 - 100, 15, this);
                }
                else if (this.life == 4) {
                    g2.drawImage(image.getImage(), this.getWidth() / 2 - 250, 15, this);
                    g2.drawImage(image.getImage(), this.getWidth() / 2 - 150, 15, this);
                    g2.drawImage(image.getImage(), this.getWidth() / 2 - 100, 15, this);
                    g2.drawImage(image.getImage(), this.getWidth() / 2 - 200, 15, this);
                }
                else if (this.life == 3) {
                    g2.drawImage(image.getImage(), this.getWidth() / 2 - 250, 15, this);
                    g2.drawImage(image.getImage(), this.getWidth() / 2 - 200, 15, this);
                    g2.drawImage(image.getImage(), this.getWidth() / 2 - 150, 15, this);
                }
                else if (this.life == 2) {
                    g2.drawImage(image.getImage(), this.getWidth() / 2 - 250, 15, this);
                    g2.drawImage(image.getImage(), this.getWidth() / 2 - 200, 15, this);
                }
                else if (this.life == 1) {
                    g2.drawImage(image.getImage(), this.getWidth() / 2 - 250, 15, this);
                }
                if (this.bullet) {
                    this.b1.drawBullets(g2);
                }
            }
        }
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == this.timer) {
            if (this.seconds != 0) {
                --this.seconds;
            }
        }
        else if (e.getSource() == this.ZombieTimer) {
            for (int index = 0; index < this.m1.length; ++index) {
                this.m1[index].move();
                if (this.m1[index].getDirection() == 0 && this.m1[index].getX() + this.m1[index].getWidth() >= this.getWidth()) {
                    this.m1[index].setdirection(1);
                }
                if (this.m1[index].getDirection() == 1 && this.m1[index].getX() <= 0) {
                    this.m1[index].setdirection(0);
                }
                if (this.g1.getBounds().intersects(this.m1[index].getBounds())) {
                    this.m1[index].setLocation(this.getWidth() / 2, this.getHeight() / 2);
                    this.m1[index].setdirection(1);
                }
                if (this.m1[index].getY() + this.m1[index].getHeight() >= this.getHeight()) {
                    this.m1[index].setY(this.getHeight() - this.m1[index].getHeight());
                    this.m1[index].setyspeed(-this.m1[index].getyspeed());
                }
                if (this.m1[index].getY() <= 0) {
                    this.m1[index].setY(0);
                    this.m1[index].setyspeed(-this.m1[index].getyspeed());
                }
                if (this.p1.getBounds().intersects(this.m1[index].getBounds())) {
                    --this.life;
                    this.numbullets = 20;
                    this.m1[index].setLocation(this.getWidth() / 2, this.getHeight() / 2);
                }
                if (this.b1.getBounds().intersects(this.m1[index].getBounds())) {
                    this.m1[index].setLocation(100000, 100000);
                    this.m1[index].move(0, 0);
                    this.b1.setLocation(2000, 2000);
                    this.bullettimer.stop();
                    this.bullettimer2.stop();
                    --this.zombiecount;
                }
            }
            if (this.p1.getY() <= 0) {
                this.p1.setY(0);
            }
            else if (this.p1.getY() + this.p1.getHeight() >= this.getHeight()) {
                this.p1.setY(this.getHeight() - this.p1.getHeight());
            }
            if (this.p1.getX() <= 0) {
                this.p1.setX(0);
            }
            else if (this.p1.getX() + this.p1.getWidth() >= this.getWidth()) {
                this.p1.setX(this.getWidth() - this.p1.getWidth());
            }
            if (this.b1.getBounds().intersects(this.g1.getBounds())) {
                this.b1.setLocation(0, 2000);
                this.bullettimer.stop();
                this.bullettimer2.stop();
            }
        }
        else if (e.getSource() == this.bullettimer) {
            this.b1.setX(this.b1.getX() - 10);
            if (this.b1.getX() + this.b1.getWidth() < 0) {
                this.bullettimer.stop();
            }
        }
        else if (e.getSource() == this.bullettimer2) {
            this.b1.setX(this.b1.getX() + 10);
            if (this.b1.getX() > this.getWidth()) {
                this.bullettimer2.stop();
            }
        }
        this.repaint();
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == 38) {
            this.p1.moveVertical(-40);
        }
        if (e.getKeyCode() == 82) {
            if (this.p1.getBounds().intersects(this.g1.getBounds())) {
                this.numbullets += 5;
            }
            else {
                this.numbullets += 0;
            }
        }
        if (e.getKeyCode() == 40) {
            this.p1.moveVertical(40);
        }
        if (e.getKeyCode() == 39) {
            this.p1.moveHorizontal(40);
        }
        if (e.getKeyCode() == 37) {
            this.p1.moveHorizontal(-40);
        }
        if (e.getKeyCode() == 65) {
            this.p1.setDirection(1);
            if (this.numbullets > 0) {
                --this.numbullets;
                this.bullettimer2.stop();
                this.bullet = true;
                if (!this.bullettimer.isRunning()) {
                    this.b1.setX(this.p1.getX());
                    this.b1.setY(this.p1.getY() + 10);
                    this.bullettimer.start();
                }
            }
            else {
                this.bullet = false;
            }
        }
        if (e.getKeyCode() == 68) {
            this.p1.setDirection(0);
            if (this.numbullets > 0) {
                --this.numbullets;
                this.bullettimer.stop();
                this.bullet = true;
                if (!this.bullettimer2.isRunning()) {
                    this.b1.setX(this.p1.getX() + this.p1.getWidth());
                    this.b1.setY(this.p1.getY() + 10);
                    this.bullettimer2.restart();
                }
            }
            else {
                this.bullet = false;
            }
        }
        if (e.getKeyCode() == 32) {
            this.start = true;
            this.timer.restart();
            this.ZombieTimer.restart();
        }
        this.repaint();
        if (this.life == 0 || this.zombiecount == 0) {
            if (this.life <= 0) {
                this.ZombieTimer.stop();
                this.timer.stop();
                final int Canceloption = JOptionPane.showConfirmDialog(null, "UNFORTUNATELY, YOU HAVE NO REMAINING LIVES LEFT!\n\nThanks for playing UNDEAD UPRISING!", "UNDEAD UPRISING", -1, -1, new ImageIcon("Icon.png"));
                if (Canceloption == 0) {
                    System.exit(0);
                }
            }
            if (this.zombiecount == 0) {
                this.ZombieTimer.stop();
                this.timer.stop();
                final int Canceloption = JOptionPane.showConfirmDialog(null, "YOU HAVE SUCCESSFULLY SURVIVED AND KILLED ALL THE ZOMBIES!\n\nThanks for playing UNDEAD UPRISING!", "UNDEAD UPRISING", -1, -1, new ImageIcon("surivoricon.jpg"));
                if (Canceloption == 0) {
                    System.exit(0);
                }
            }
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void mouseDragged(final MouseEvent e) {
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
    }

    @Override
    public void mousePressed(final MouseEvent e) {
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
    }

    @Override
    public void mouseExited(final MouseEvent e) {
    }
}
