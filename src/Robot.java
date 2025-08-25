// this is our Robot class **** WITH TRIG

import java.awt.*;// we need this so we can change Color
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;


public class Robot {
    /**
     * DX is a real number used for how large a step the Robot takes in the x direction
     */
    public double DX = 0;
    /**
     * DY is a real number used for how large a step the Robot takes in the y direction
     */
    public double DY = 0;
    /**
     * width is how wide the image that represents the Robot is
     */
    public int width = 40;
    /**
     * height represents how tall the Robot is
     */
    public int height = 40;
    /**
     * isAlive is currently not used in the program
     */
    boolean isAlive;

    /**
     * c represents the Color of the robot's trail
     */
    public Color c;
    /**
     * anglefacing is the angle that the Robot is facing
     */
    public int anglefacing;
    /**
     * xpos is the x coordinate of the Robot
     */
    public double xpos;
    /**
     * ypos is the the y coordinate of the Robot
     */
    public double ypos;
    /**
     * yposold is the last y location
     */
    public double yposold;
    /**
     * xposold is the last x location
     */
    public double xposold;
    /**
     * Left is used to tell if it is moving to the left
     */
    public boolean Left = false;

    private World myWorld;
    /**
     * isTrail sets if the robot is leaving a trail
     */
    public boolean isTrail = false;
    /**
     * sets the width of the trail
     */
    public int trailWidth = 1;
    /**
     * slows down the speed to the program
     */

    public double destX, destY;
    public boolean doneMoving;


    public int pausetime = 5;
    public int red, green, blue;
    public int backGroundHeight, backGroundWidth;
    public int size;


    /**
     * contructor that takes in x location, y location and a reference to the applet
     */
    public int getBackGroundHeight() {
        return (myWorld.image.getHeight(null));

    }

    public int getBackGroundWidth() {
        return (myWorld.image.getWidth());

    }

    public void loadBackGround(String imgname) {
        myWorld.loadFile = new File(imgname);
        try {
            myWorld.image = ImageIO.read(myWorld.loadFile);                        // get the image from loadFile and put it into varable image
        } catch (IOException e) {
            System.out.println("wrong file type");
        }


    }

    public Robot(int x, int y, World w) {
        isAlive = true;
        xpos = x;
        ypos = y;
        myWorld = w;
        backGroundHeight = myWorld.image.getHeight(null);
        backGroundWidth = myWorld.image.getWidth(null);

    }

    public Robot(int x, int y, Color co, int speedX, int speedY) {
        isAlive = true;
        xpos = x;
        ypos = y;
        c = co;
        DX = speedX;
        DY = speedY;
        backGroundHeight = myWorld.image.getHeight(null);
        backGroundWidth = myWorld.image.getWidth(null);
    }

    public void setPixelColor(int r, int g, int b) {
        c = new Color(r, g, b);
        myWorld.trailGraphics.setColor(c);
        myWorld.trailGraphics.fillRect((int) xpos, (int) ypos, 1, 1);
        c = new Color(red, green, blue);
        myWorld.trailGraphics.setColor(c);


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

    public void showBackGround() {
        myWorld.trailGraphics.drawImage(myWorld.image, 0, 0, myWorld.image.getWidth(null), myWorld.image.getHeight(null), myWorld); // draw the picture to be analized

    }

    public void hideBackGround() {
        myWorld.showBack = false;
    }

    /**
     * random() creates a random number. It takes in two perameters, a starting value and an ending value. If you send it random(3,10) it will create a random number from
     * 3 to 10 not inclusive of 10. It will return an int
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

    /**
     * move() will move the Robot forward by however many pixels you enter as a parameter
     * Redbarron.move(10); will move the Robot forward 10 pixels
     */
    public void move(int distance) {
        size = distance;
        //set the endpoints
        destX = xpos + (distance * DX);
        destY = ypos + (distance * DY);

        doneMoving = false;

        moveItThread movePlane = new moveItThread();

        movePlane.start();
        try {
            movePlane.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupt Occurred");
            e.printStackTrace();
        }

        if (anglefacing >= 90 && anglefacing <= 270) {
            Left = true;
        } else {
            Left = false;
        }

    }

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


                if (isTrail == true) {
                    myWorld.trailGraphics.setColor(c);
                    myWorld.trailGraphics.fillRect((int) xpos, (int) ypos, trailWidth, trailWidth);
                    //myWorld.trailGraphics.setColor(Color.black);
                }

                try {
                    Thread.sleep(pausetime);
                } catch (InterruptedException e) {
                }

            }
            exit = true;
        }

    }

//this method currently DOES NOT use the moveIt thread
    public void move(double distance) {
        int w = (int) distance;
        //System.out.println("move");
        for (int q = 0; q < w; q++) {
            myWorld.render();
            //myWorld.repaint();

            //System.out.print("q"+q);
            xpos = xpos + DX;
            ypos = ypos + DY;

            if (isTrail == true) {
                myWorld.trailGraphics.setColor(c);
                myWorld.trailGraphics.fillRect((int) xpos, (int) ypos, trailWidth, trailWidth);
                //myWorld.trailGraphics.setColor(Color.black);

            }


            if (anglefacing >= 90 && anglefacing <= 270) {
                Left = true;
            } else {
                Left = false;
            }


            try {
                Thread.sleep(pausetime);
            } catch (InterruptedException e) {
            }


        }

    }

    /**
     * redbarron.setPos( 111,20); will move the robot to the location (111,20)
     * you must send it two int
     */
    public void setPos(int x, int y) {
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

    /**
     * /**
     * redbarron.circle(10) will draw a circle with a radius 10 -- need to put updated code in
     */
    public void circle(int radius) {
        for (int x = 0; x < 360; x++) {
            isTrail = false;
            move(radius);
            isTrail = true;
            move(1);
            isTrail = false;
            turn(180);
            move(radius + 1);
            turn(180);
            turn(1);
        }
        isTrail = true;

    }

    public void circle(double r) {
        int radius = (int) r;
        for (int x = 0; x < 360; x++) {
            isTrail = false;
            move(radius);
            isTrail = true;
            move(1);
            isTrail = false;
            turn(180);
            move(radius + 1);
            turn(180);
            turn(1);
        }
        isTrail = true;


    }

    public void fillCircle(int radius) {
        for (int x = 0; x < 360; x++) {
            isTrail = true;
            move(radius);
            isTrail = true;
            move(1);
            isTrail = false;
            turn(180);
            move(radius + 1);
            turn(180);
            turn(1);
        }
        isTrail = true;


    }

    public void fillCircle(double r) {
        int radius = (int) r;
        for (int x = 0; x < 360; x++) {
            isTrail = true;
            move(radius);
            isTrail = true;
            move(1);
            isTrail = false;
            turn(180);
            move(radius + 1);
            turn(180);
            turn(1);
        }
        isTrail = true;


    }


    /**
     * redbarron.square(30) will draw a square that has sides 30 pixles long
     * it will be facing the same direction when it is done
     */
    public void square(int distance) {

        isTrail = true;
        move(distance);
        turn(90);
        move(distance);
        turn(90);
        move(distance);
        turn(90);
        move(distance);
        turn(90);


    }

    public void square(double d) {
        int distance = (int) d;
        isTrail = true;
        move(distance);
        turn(90);
        move(distance);
        turn(90);
        move(distance);
        turn(90);
        move(distance);
        turn(90);


    }


    /**
     * redbarron.setColor( 33,44,55) will set the Color of the trail of the robot. It does this using RGB. It take a range of  0 - 255
     */
    public void setColor(int r, int g, int b) {
        c = new Color(r, g, b);
        red = r;
        green = g;
        blue = b;

    }

    /**
     * returns the angle you are facing - curently does not work correclty
     */
    public void startingAngle(int angle) {

        anglefacing = angle;
        DY = (Math.sin(Math.toRadians(anglefacing)));
        DX = (Math.cos(Math.toRadians(anglefacing)));

    }

    public void house(int x) {
        square(x);
        turn(90);
        triangle(x);
        teleport((int) (xpos + (x / 5)), (int) (ypos + x / 5));
        turn(30);
        square((int) (x / 5));
        teleport((int) (xpos + 2 * (x / 5)), (int) ypos);
        square((int) (x / 5));
        teleport((int) (xpos - (x / 5)), (int) (ypos + (x / 3)));
        square(x / 5);
        teleport((int) xpos, (int) (ypos + (x / 5)));
        square(x / 5);

    }

    public void house(double z) {
        int x = (int) z;
        square(x);
        turn(90);
        triangle(x);
        teleport((int) (xpos + (x / 5)), (int) (ypos + x / 5));
        turn(30);
        square((int) (x / 5));
        teleport((int) (xpos + 2 * (x / 5)), (int) ypos);
        square((int) (x / 5));
        teleport((int) (xpos - (x / 5)), (int) (ypos + (x / 3)));
        square(x / 5);
        teleport((int) xpos, (int) (ypos + (x / 5)));
        square(x / 5);

    }

    public void triangle(int length) {
        isTrail = true;
        move(length);
        turn(120);
        //setColor(0,200,0);
        move(length);
        turn(120);
        // setColor(0,0,200);
        move(length);


    }

    public void triangle(double l) {
        int length = (int) l;
        isTrail = true;
        move(length);
        turn(120);
        //  setColor(0,200,0);
        move(length);
        turn(120);
        //  setColor(0,0,200);
        move(length);


    }


    public void drawWords(String s) {


        myWorld.trailGraphics.drawString(s, (int) xpos, (int) ypos);

    }

    /**
     * moveLeft(35) will turn redbarron left 35 degrees
     */
    public void moveLeft(int degrees) {
        anglefacing = anglefacing + degrees;
        DY = (Math.sin(Math.toRadians(anglefacing)));
        DX = (Math.cos(Math.toRadians(anglefacing)));
    }

    /**
     * turn(50) will turn the robot 50 degrees
     */
    public void turn(int degrees) {
        //System.out.println("turn");
        anglefacing = anglefacing - degrees;
        DY = (Math.sin(Math.toRadians(anglefacing)));
        DX = (Math.cos(Math.toRadians(anglefacing)));

    }

    public void turn(double degrees) {
        int w = (int) degrees;
        //System.out.println("turn");
        anglefacing = anglefacing - w;
        DY = (Math.sin(Math.toRadians(anglefacing)));
        DX = (Math.cos(Math.toRadians(anglefacing)));

    }


    /**
     * dont use this now
     */
    public void MoveDistance(int x) {
        DX = 5;
        DY = 5;
        yposold = ypos;
        xposold = xpos;
        if (((Math.abs(yposold - ypos) + (Math.abs(xposold - xpos)))) >= x) {
            DX = 0;
            DY = 0;
        }
    }

    /**
     * this will give you the anglefacing - curently has a flaw
     */
    public int GetDirection() {

        return (anglefacing);


    }


}