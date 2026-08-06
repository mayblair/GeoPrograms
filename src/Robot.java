// Created kchun and chales
// Edited mcblair 07/26
// Robot class defines a movable, turnable robot which can draw a colorful trail

import java.awt.*; // we need this so we can change Color
//import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;


public class Robot {
    // DX is a real number used for how large a step the Robot takes in the x direction
    public double DX = 0;
    // DY is a real number used for how large a step the Robot takes in the y direction
    public double DY = 0;
    // width is how wide the image that represents the Robot is
    public int width;
    // height represents how tall the Robot is
    public int height;
    // isAlive is currently not used in the program, but is often used in other implementations of Robot
    boolean isAlive;

    // anglefacing is the angle that the Robot is facing
    public double anglefacing;
    // xpos is the x coordinate of the Robot
    public double xpos;
    // ypos is the y coordinate of the Robot
    public double ypos;

    // World is an instance of the World class, a JFrame defined in another file
    private final World myWorld;
    // hasTrail sets if the robot is leaving a trail
    public boolean hasTrail = false;
    // sets the width of the trail
    public int trailWidth = 3;

    public double destX, destY;
    public int size;

    // slows down the speed to the program
    public int pausetime = 5;
    public int red = 0;
    public int green = 0;
    public int blue = 0;
    // c represents the Color of the robot's trail to black initially
    public Color c = new Color(red, green, blue);

    /**
     * constructor that takes in x location, y location and a reference to the applet
     */
    public Robot(int x, int y, World w) {
        isAlive = true;
        xpos = x;
        ypos = y;
        myWorld = w;
        height = myWorld.planePic.getHeight(null);
        width = myWorld.planePic.getWidth(null);
    }

    public Robot(int x, int y, int p_width, int p_height, World w) {
        isAlive = true;
        xpos = x;
        ypos = y;
        width = p_width;
        height = p_height;
        myWorld = w;
        height = myWorld.planePic.getHeight(null);
        width = myWorld.planePic.getWidth(null);
    }

    // BACKGROUND GRAPHICS
    public void showBackGround() {
        myWorld.showBack = true;
    }

    public void hideBackGround() {
        myWorld.showBack = false;
    }

    public void loadBackGround(String imgname) {
        myWorld.loadFile = new File("images/" + imgname);
        try {
            // get the image from loadFile and put it into variable image
            myWorld.image = ImageIO.read(myWorld.loadFile);
        } catch (IOException e) {
            System.out.println("wrong file type");
        }
    }

    // COLOR SETTING AND GETTING
    public void setPixelColor(int r, int g, int b) {
        c = new Color(r, g, b);
        myWorld.trailGraphics.setColor(c);
        myWorld.trailGraphics.fillRect((int) xpos, (int) ypos, 1, 1);
        c = new Color(red, green, blue);
        myWorld.trailGraphics.setColor(c);
    }

    /**
     * plane.setColor( 33,44,55) will set the Color of the trail of the robot. It uses RGB which has a range of  0 - 255
     */
    public void setTrailColor(int r, int g, int b) {
        c = new Color(r, g, b);
        red = r;
        green = g;
        blue = b;
    }

    public int howMuchRed(int x, int y) {
        try {
            int clr = myWorld.image.getRGB(x, y);
            return ((clr & 0x00ff0000) >> 16);
        } catch (Exception e) {
            System.out.println(x + "  outside range  " + y);
            return (0);
        }
    }

    public int howMuchGreen(int x, int y) {
        try {
            int clr = myWorld.image.getRGB(x, y);
            return ((clr & 0x0000ff00) >> 8);
        } catch (Exception e) {
            return (0);
        }
    }

    public int howMuchBlue(int x, int y) {
        try {
            int clr = myWorld.image.getRGB(x, y);
            return (clr & 0x000000ff);
        } catch (Exception e) {
            return (0);
        }
    }

    public int howMuchRed() {
        try {
            int clr = myWorld.image.getRGB((int) xpos, (int) ypos);
            return ((clr & 0x00ff0000) >> 16);
        } catch (Exception e) {
            return (0);
        }
    }

    public int howMuchGreen() {
        try {
            int clr = myWorld.image.getRGB((int) xpos, (int) ypos);
            return ((clr & 0x0000ff00) >> 8);
        } catch (Exception e) {
            return (0);
        }
    }

    public int howMuchBlue() {
        try {
            int clr = myWorld.image.getRGB((int) xpos, (int) ypos);
            return (clr & 0x000000ff);
        } catch (Exception e) {
            return (0);
        }
    }


    // MATH FUNCTIONS
    /**
     * random() creates a random number. It takes in two perameters, a starting value and an ending value, and returns an int.
     * If you send it random(3,10) it will create a random number from 3.0 to 9.99999 (it is not inclusive of 10).
     */
    public int random(int start, int end) {
        return ((int) (start + (Math.random() * (end - start))));
    }

    public double sin(double a) {
        return ((Math.sin(Math.toRadians(a))));
    }

    public double cos(int a) {
        return ((Math.cos(Math.toRadians(a))));
    }

    public double tan(int a) {
        return ((Math.tan(Math.toRadians(a))));
    }

    // MOVING METHODS
    class moveItThread extends Thread {

        private volatile boolean exit = false;

        public void run() {

            int counter = 0;
            //set the endpoints
            destX = xpos + (size * DX);
            destY = ypos + (size * DY);

            while (counter <= size) {
                xpos = xpos + DX;
                ypos = ypos + DY;
                counter++;
                //System.out.println(destX + "  " + xpos + "   y " + destY + "   " + ypos + "  size:" + size + "  counter:" + counter);

                if (hasTrail) {
                    myWorld.trailGraphics.setColor(c);
                    myWorld.trailGraphics.fillRect((int) xpos, (int) ypos, trailWidth, trailWidth);
                }

                try {
                    Thread.sleep(pausetime);
                } catch (InterruptedException e) {
                }

            }
            exit = true;
        }
    }

    /**
     * move() will move the Robot forward by however many pixels you enter as a parameter
     */
    public void move(int distance) {
        size = distance;
        //set the endpoints
        destX = xpos + (distance * DX);
        destY = ypos + (distance * DY);

        moveItThread movePlane = new moveItThread();

        movePlane.start();
        try {
            movePlane.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupt Occurred");
            e.printStackTrace();
        }

    }

    //this method currently DOES NOT use the moveIt thread
    public void move(double distance) {
        int w = (int) distance;
        //System.out.println("move");
        for (int q = 0; q < w; q++) {
            myWorld.render();

            //System.out.print("q"+q);
            xpos = xpos + DX;
            ypos = ypos + DY;

            if (hasTrail) {
                myWorld.trailGraphics.setColor(c);
                myWorld.trailGraphics.fillRect((int) xpos, (int) ypos, trailWidth, trailWidth);
            }

            try {
                Thread.sleep(pausetime);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
        }
    }

    /**
     * plane.setPos( 111,20); will move the robot to the location (111,20)
     */
    public void setPosition(int x, int y) {
        xpos = x;
        ypos = y;
    }

    public void setPosition(double x, double y) {
        xpos = x;
        ypos = y;
    }

    public void teleport(int x, int y) {
        xpos = x;
        ypos = y;
    }

    public void teleport(double x, double y) {
        xpos = x;
        ypos = y;
    }


    public void setAngle(int angle) {
        anglefacing = angle;
        DY = (Math.sin(Math.toRadians(anglefacing)));
        DX = (Math.cos(Math.toRadians(anglefacing)));
    }

    public void setAngle(double angle) {
        anglefacing = angle;
        DY = (Math.sin(Math.toRadians(anglefacing)));
        DX = (Math.cos(Math.toRadians(anglefacing)));
    }


    public void turnLeft(int degrees) {
        //System.out.println("turn");
        anglefacing = anglefacing - degrees;
        DY = (Math.sin(Math.toRadians(anglefacing)));
        DX = (Math.cos(Math.toRadians(anglefacing)));

        try {
            Thread.sleep(pausetime);
        } catch (InterruptedException e) {
        }
    }

    public void turnLeft(double degrees) {
        //System.out.println("turn");
        anglefacing = anglefacing - degrees;
        DY = (Math.sin(Math.toRadians(anglefacing)));
        DX = (Math.cos(Math.toRadians(anglefacing)));
        try {
            Thread.sleep(pausetime);
        } catch (InterruptedException e) {
        }
    }


    // RESIZING METHODS
    public void setSize(int p_width, int p_height){
        height = p_height;
        width = p_width;
        try {
            Thread.sleep(pausetime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getImageHeight() {
        return height;
    }
    public int getImageWidth() {
        return width;
    }


    // DRAWING SHAPES
    public void circle(int radius) {
        if (radius <= 0) {
            return;
        }
        int diameter = radius * 2;
        int left = (int) Math.round(xpos - radius);
        int top  = (int) Math.round(ypos - radius);

        Graphics2D g2 = (Graphics2D) myWorld.trailGraphics;
        Stroke previousStroke = g2.getStroke();

        g2.setColor(c);
        g2.setStroke(new BasicStroke(trailWidth));
        g2.drawOval(left, top, diameter, diameter);

        g2.setStroke(previousStroke);

        myWorld.render();
    }

    public void circle(double radius) {
        if (radius <= 0) {
            return;
        }
        int diameter = (int) Math.round(radius * 2);
        int left = (int) Math.round(xpos - radius);
        int top  = (int) Math.round(ypos - radius);

        Graphics2D g2 = (Graphics2D) myWorld.trailGraphics;

        Stroke previousStroke = g2.getStroke();

        g2.setColor(c);
        g2.setStroke(new BasicStroke(trailWidth));
        g2.drawOval(left, top, diameter, diameter);

        g2.setStroke(previousStroke);

        myWorld.render();
    }

    public void fillCircle(int radius) {
        if (radius <= 0) {
            return;
        }

        int diameter = radius * 2;
        int left = (int) Math.round(xpos - radius);
        int top = (int) Math.round(ypos - radius);

        Graphics2D g2 = (Graphics2D) myWorld.trailGraphics;

        g2.setColor(c);
        g2.fillOval(left, top, diameter, diameter);

        myWorld.render();
    }

    public void fillCircle(double radius) {
        if (radius <= 0) {
            return;
        }

        int diameter = (int) Math.round(radius * 2.0);
        int left = (int) Math.round(xpos - radius);
        int top = (int) Math.round(ypos - radius);

        Graphics2D g2 = (Graphics2D) myWorld.trailGraphics;

        g2.setColor(c);
        g2.fillOval(left, top, diameter, diameter);

        myWorld.render();
    }


    /**
     * plane.square(30) will draw a square that has sides 30 pixels long
     * plane will be facing the same direction when it is done drawing
     */
    public void square(int distance) {
        hasTrail = true;
        move(distance);
        turnLeft(90);
        move(distance);
        turnLeft(90);
        move(distance);
        turnLeft(90);
        move(distance);
        turnLeft(90);
    }

    public void square(double distance) {
        hasTrail = true;
        move(distance);
        turnLeft(90);
        move(distance);
        turnLeft(90);
        move(distance);
        turnLeft(90);
        move(distance);
        turnLeft(90);
    }

    public void drawWords(String s) {
        myWorld.trailGraphics.drawString(s, (int) xpos, (int) ypos);
    }

    public void house(int x) {
        square(x);
        turnLeft(90);
        triangle(x);
        teleport((xpos + (x / 5.0)), (ypos + x / 5.0));
        turnLeft(30);
        square(x / 5);
        teleport(xpos + 2 * (x / 5.0), ypos);
        square( x / 5);
        teleport(xpos - (x / 5.0), ypos + (x / 3.0));
        square(x / 5);
        teleport(xpos, ypos + (x / 5.0));
        square(x / 5);

    }

    public void house(double x) {
        square(x);
        turnLeft(90);
        triangle(x);
        teleport(xpos + (x / 5), ypos + x / 5);
        turnLeft(30);
        square(x / 5);
        teleport(xpos + 2 * (x / 5), ypos);
        square(x / 5);
        teleport(xpos - (x / 5), ypos + (x / 3));
        square(x / 5);
        teleport(xpos, ypos + (x / 5));
        square(x / 5);
    }

    public void triangle(int length) {
        hasTrail = true;
        move(length);
        turnLeft(120);
        move(length);
        turnLeft(120);
        move(length);
    }

    public void triangle(double length) {
        hasTrail = true;
        move(length);
        turnLeft(120);
        move(length);
        turnLeft(120);
        move(length);
    }

    /**
     * this will give you the anglefacing - curently has a flaw
     */
    public double GetDirection() {

        return (anglefacing);

    }


}