public class A4_Turn extends World {

    public void go() {

        System.out.println("Turn the plane.");
        plane.setAngle(90);
        plane.hasTrail = true;
        plane.trailWidth = 3;
        plane.setTrailColor(200, 0, 0);
        plane.move(100);
        plane.turnLeft(90);
        plane.setTrailColor(0, 200, 0);
        plane.move(100);

    }


}
