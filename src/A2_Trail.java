public class A2_Trail extends World {

    public void go() {

        System.out.println("Change the thickness of the trail behind the plane");
        plane.setAngle(90);
        plane.hasTrail = true;
        plane.trailWidth = 10;
        plane.move(100);

    }

}