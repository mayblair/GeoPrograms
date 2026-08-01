public class A5_Square extends World {

    public void go() {
        plane.trailWidth = 5;
        plane.pausetime = 3;
        plane.setTrailColor(100, 10, 200);

        plane.setAngle(90);
        square();
    }


    public void square() {
        plane.setAngle(90);
        plane.hasTrail = true;
        plane.trailWidth = 10;
        plane.move(111);
        plane.turnLeft(90);
        plane.move(111);
        plane.turnLeft(90);
        plane.move(111);
        plane.turnLeft(90);
        plane.move(111);
        //notice this final turn "resets" the plane to its original position
        plane.turnLeft(90);
    }

}


