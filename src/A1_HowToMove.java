public class A1_HowToMove extends World {

    public void go() {

        System.out.println("This message will be printed to the window below.");
        plane.pausetime = 2;
        plane.startingAngle(0);
        plane.isTrail = true;
        plane.move(200);
        plane.isTrail = false;
        plane.move(100);
        plane.isTrail = true;
        plane.move(200);

    }

}

