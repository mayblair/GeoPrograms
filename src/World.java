// my robot 2

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

//Image Rotation
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
import java.awt.Image;


public class World extends JFrame implements Runnable, KeyListener, MouseMotionListener, MouseListener {

    public Image dubbuff;
    public Image planePic;
    public Graphics offscreen;
    public Robot plane;
    public Image trail;
    public Graphics trailGraphics;
    public int worldHeight;
    public int worldWidth;

    public File loadFile;                // declare a variable of type File to load the picture into
    public int planeHeight, planeWidth;  // declare ints to hold the width and height of the robot picture
    public int backHeight, backWidth;  // declare ints to hold the width and height of the background picture
    public boolean showBack = false;
    public int WIDTH = 800;             // Changing these will not change the width and height.  These are set in RunMyProgram.java
    public int HEIGHT = 400;

    public JFrame frame;
    public Canvas canvas;
    public BufferStrategy bufferStrategy;
    public BufferedImage image;

    private int xpos = 470;
    private int ypos = 310;
    private boolean left, right, up, down;
    private int x, y;
    public String pictureFileName = "sky.jpg";
    public String planeIcon = "plane.png";

    //picture rotation variables
    public AffineTransform trans;
    public AffineTransform identity;
    //Variables for position and angle
    public double theta = 0;

    Thread thread;


    public void loadBackGround(String imgname) {
        loadFile = new File("images/" + imgname);
        try {
            image = ImageIO.read(loadFile);    // get the image from loadFile and put it into variable image
        } catch (IOException e) {
            System.out.println("wrong file type");
        }
    }

    public void render() {

        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        if (showBack) {
            g.drawImage(image, 0, 0, backWidth, backHeight, null);
        }
        g.drawImage(trail, 0, 0, WIDTH, HEIGHT, null);
        rotateImage();
        g.drawImage(planePic, trans, null );
        g.dispose();
        bufferStrategy.show();
    }

    public void Refresh() {
        worldHeight = HEIGHT;
        worldWidth = WIDTH;
        setSize(worldWidth, worldHeight);

        frame = new JFrame("Basic Game");

        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(null);

        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);

        canvas.addKeyListener(this);
        canvas.addMouseListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        // load plane image
        loadFile = new File(planeIcon);
        //rotational stuff
        trans = new AffineTransform();	//construct new transformations
        identity = new AffineTransform();
        try {
            planePic = ImageIO.read(new File("images/" + planeIcon));
        } catch (IOException e) {
            System.out.println("Could not load robot image");
        }
        planeWidth = planePic.getWidth(null);
        planeHeight = planePic.getHeight(null);

        // load background image
        loadBackGround(pictureFileName);
        // Set the size of the square we are looking for
        backHeight = image.getHeight(null);    // get the dimensions of the background image
        backWidth = image.getWidth(null);

        dubbuff = canvas.createImage(WIDTH, HEIGHT);
        trail = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);

        //System.out.println(trail.toString());
        trailGraphics = trail.getGraphics();
        offscreen = dubbuff.getGraphics();
        plane = new Robot(xpos, ypos, this);
        addKeyListener(this);
        thread = new Thread(this);

        plane.setAngle(0);
        addMouseMotionListener(this);
        addMouseListener(this);
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();

        canvas.requestFocus();

        thread.start();
        go();
    }

    private void rotateImage() {

        //Calculate the angle and use an offset
        //theta = plane.anglefacing + 3.1459 / 2;
        theta = Math.toRadians(plane.anglefacing);

        //set up new transforms
        trans = new AffineTransform();
        trans.setTransform(identity);

        //set the position of the picture on the offscreen image
        trans.translate(plane.xpos, plane.ypos);

        //rotate the picture - use radians
        trans.rotate(theta);

        // Resize from original dimensions to plane.width and plane.height
        trans.scale(plane.width / (double) planeWidth, plane.height / (double) planeHeight);

        //translate back. Use half the width and height
        trans.translate(-planeWidth /2.0, -planeHeight /2.0);
    }


    public void go() {

    }


    public void update(Graphics g) {
        paint(g);
    }

    public void run() {

        while (true) {
            render();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {

            }
        }
    }


    public void keyPressed(KeyEvent event) {
        String keyin; // define a non-public variable to hold the string representing the key input
        keyin = "" + event.getKeyText(event.getKeyCode()); //getKeyCode returns the key code, then change it to a String.
        System.out.println("Key pressed " + keyin);
    }

    public void keyReleased(KeyEvent event) {
        String keyin;
        keyin = "" + event.getKeyText(event.getKeyCode());

    }

    public void keyTyped(KeyEvent event) {
        //keyTyped() only runs if a printable key is pressed.
        //It does not respond to arrow keys, space, tab, etc.
        char keyin;
        keyin = event.getKeyChar(); //getKeyChar() returns the character of the printable key pressed

    }

//********************************************************************************************

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

        System.out.println("(" + e.getX() + "," + e.getY() + ")");
        try {
            System.out.print("RED = " + plane.howMuchRed(e.getX(), e.getY()));
            System.out.print("   GREEN = " + plane.howMuchGreen(e.getX(), e.getY()));
            System.out.println("   Blue = " + plane.howMuchBlue(e.getX(), e.getY()));
        } catch (Exception name) {
            System.out.println("no picture at this point to get color of");
        }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }


    //********************************************************************************************
    public void mouseMoved(MouseEvent e) {


    }

    public void mouseDragged(MouseEvent e) {


    }

}