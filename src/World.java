// my robot 2

import java.applet.*;
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

    public int DISTANCE = 70;
    public Image tank1;
    public Image tankright;
    public Image dubbuff;
    public Image kjcPic;
    public Graphics offscreen;
    public Robot plane;
    public Image trail;
    public Graphics trailGraphics;
    public int worldHeight;
    public int worldWidth;

    public File loadFile;                // declare a variable of type File to load the picture into
    public int height, width;           // declare the variable of type int to hold the width and height of the picture
    public int startred, startgreen, startblue;
    public boolean showBack = false;
    public int WIDTH = 800;             // Changing these will not change the width and height.  These are set in RunMyProgram.java
    public int HEIGHT = 400;

    public JFrame frame;
    public Canvas canvas;
    public BufferStrategy bufferStrategy;
    public BufferedImage image;

    private int xpos = 300;
    private int ypos = 300;
    private boolean left, right, up, down;
    private int x, y;
    public String pictureFileName = "eye.jpg";
    public String planeIcon = "plane.png";

    //picture rotation variables
    public AffineTransform trans;
    public AffineTransform identity;
    //Variables for position and angle
    public double theta =0;

    //public double zAngle = 0;

    Thread thread;


    public void loadBackGround(String imgname) {
        loadFile = new File(imgname);
        try {
            image = ImageIO.read(loadFile);                        // get the image from loadFile and put it into varable image
        } catch (IOException e) {
            System.out.println("wrong file type1");
        }


    }

    public void render() {

        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(trail, 0, 0, WIDTH, HEIGHT, null);

        if (showBack == true) {
            g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null); // draw the picture to be analized
        }
/*
        if (plane.Left == false) {
            g.drawImage(tankright, (int) plane.xpos, (int) plane.ypos, plane.width, plane.height, null);
        } else {
            g.drawImage(tank1, (int) plane.xpos, (int) plane.ypos, plane.width, plane.height, null);
        }
*/
        rotateImage();
        g.drawImage(kjcPic, trans, null );
        g.dispose();
        bufferStrategy.show();
    }

    public void Refresh() {
        worldHeight = HEIGHT;
        worldWidth = WIDTH;
        setSize(worldHeight, worldWidth);

        //setSize(worldWidth, worldHeight);
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
        loadFile = new File(planeIcon);
 /*       try {
            tank1 = ImageIO.read(loadFile);                        // get the image from loadFile and put it into varable image
        } catch (IOException e) {
            System.out.println("wrong file type2");
        }  */
        //rotational stuff
        trans = new AffineTransform();	//construct new transformations
        identity = new AffineTransform();
        kjcPic = Toolkit.getDefaultToolkit().getImage(planeIcon);
        loadFile = new File(pictureFileName);
        //loadFile= new File("manycolordots.png");					// load the file into varable LoadFile
        //	loadFile= new File("reddots4.png");					// load the file into varable LoadFile


        try {
            image = ImageIO.read(loadFile);                        // get the image from loadFile and put it into varable image
        } catch (IOException e) {
            System.out.println("wrong file type5");
        }

        // Set the size of the square we are looking for
        height = image.getHeight(null);                    // get the width of the image
        width = image.getWidth(null);


        tankright = tank1;
        //tankright= getImage(getDocumentBase(), "test.png");
        //tankright= getImage(getDocumentBase(), "tankleft.gif");

        dubbuff = canvas.createImage(WIDTH, HEIGHT);
        trail = canvas.createImage(WIDTH, HEIGHT);

        //System.out.println(trail.toString());
        trailGraphics = trail.getGraphics();
        offscreen = dubbuff.getGraphics();
        plane = new Robot(200, 250, this);
        addKeyListener(this);
        thread = new Thread(this);

        plane.startingAngle(90);
        //plane.moveLeft(73);
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

        theta = Math.toRadians(plane.anglefacing)+ 3.1459 / 2;


        //set up new transforms
        trans = new AffineTransform();
        trans.setTransform(identity);

        //set the position of the picture on the offscreen image
        trans.translate(plane.xpos, plane.ypos);

        //rotate the picture - use radians
        trans.rotate(theta);

        //translate back. Use half the width and height
        trans.translate(-20, -20);
    }


    public void moveEverything() {

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


        if (keyin.equals("Left")) {


        }
    }

    public void keyReleased(KeyEvent event) {
        String keyin;
        keyin = "" + event.getKeyText(event.getKeyCode());

    }//keyReleased()

    public void keyTyped(KeyEvent event) {
        //keyTyped() only runs if a printable key is pressed.
        //It does not respond to arrow keys, space, tab, etc.
        //Use keyPressed.
        char keyin;
        keyin = event.getKeyChar(); //getKeyChar() returns the character of the printable key pressed

    }//keyTyped()

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