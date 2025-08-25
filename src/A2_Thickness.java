public class A2_Thickness extends World {

    public void go() {

        System.out.println("This message will be printed to the window below.");
        System.out.println("A2 Thickness");
        plane.startingAngle(90);
        plane.isTrail = true;
        plane.trailWidth = 10;
        plane.move(100);

    }

}