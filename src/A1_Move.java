public class A1_Move extends World {

    public void go() {

        System.out.println("This message will be printed to the window below.");
        plane.pausetime = 2;
        plane.setAngle(0);
        plane.hasTrail = true;
        plane.move(200);
        plane.hasTrail = false;
        plane.move(100);
        plane.hasTrail = true;
        plane.move(200);

    }

}

