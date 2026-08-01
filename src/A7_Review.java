public class A7_Review extends World {

    public int sizeoftree;     // This declares a variable of type int named sizeoftree.
    // variables are used so that we can use them repeatedly later in the program.

    /**
     * Go() method is called by World -- the Go() method basically tells the program what to do and where and when to do it.
     */

    public void go() {

        plane.pausetime = 0; // sets a variable in the Robot class so program pauses for that number of milliseconds. 0 = no pause

        sizeoftree = 50;     // Assigns the value of 50 to sizeoftree - now the variable that we made above has a value

        System.out.println("This will be printed to the  window." + sizeoftree); // Prints to the lower window
        //we use System.out to tell us information we need to know about each time the program is run.

        plane.teleport(200, 300);    // Moves plane to the coordinate (200,300)
        // remember that (0,0) is in the top left of the applet
        ///  FIX ME!!!
        plane.house(100);        // uses a method from the Robot class to have plane draw a house of size 100

        plane.teleport(374, 274);
        plane.setTrailColor(0, 210, 10);
        //Every color that you see on this screen is made from the three colors, red, green, and blue.
        plane.fillCircle( sizeoftree / 2);    // uses fillCircle method to draw a shaded circle of size 25
        plane.setTrailColor(10, 150, 10);
        plane.circle(sizeoftree / 2);

        plane.teleport(364, 301);
        plane.setAngle(90);        // Sets the starting angle so plane is pointing down
        plane.trailWidth = 20;
        plane.setTrailColor(0, 0, 0);
        plane.move(sizeoftree);        // Uses the move method to move forward 50, since sizeoftree is 50

        plane.teleport(242, 402);
        plane.trailWidth = 20;
        plane.setTrailColor(200, 200, 0);
        plane.move(700);
        plane.teleport(600, 200);
    }
}

