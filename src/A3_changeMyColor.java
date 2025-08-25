public class A3_changeMyColor extends World {

    public void go() {

        System.out.println("This message will be printed to the window below.");
        plane.startingAngle(45);
        plane.isTrail = true;
        plane.trailWidth = 10;
        plane.setColor(130, 0, 130);
        plane.move(100);

    }


}
