public class A3_Color extends World {

    public void go() {

        System.out.println("Change the color of the trail behind the plane");
        plane.setAngle(45);
        plane.hasTrail = true;
        plane.trailWidth = 10;
        plane.setTrailColor(170, 0, 170);
        plane.move(100);

    }


}
