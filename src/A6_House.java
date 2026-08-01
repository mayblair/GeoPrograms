public class A6_House extends World {

    public void go() {
        plane.hasTrail = true;
        plane.pausetime = 0;
        house();
        plane.hasTrail = false;
        plane.move(75);
    }

    public void big() {
        plane.move(111);
        plane.turnLeft(90);
        plane.move(111);
        plane.turnLeft(90);
        plane.move(111);
        plane.turnLeft(90);
        plane.move(111);
        plane.turnLeft(90);
    }

    public void small(int a, int b) {
        plane.move(a);
        plane.turnLeft(90);
        plane.move(b);
        plane.turnLeft(90);
        plane.move(a);
        plane.turnLeft(90);
        plane.move(b);
        plane.turnLeft(90);
    }


    public void house() {
        plane.hasTrail = true;
        plane.setAngle(0);
        plane.teleport(200,450);
        square(50);
        plane.teleport(200,400);
        plane.setAngle(0);
        equitriangle(50);
        plane.hasTrail = false;
        plane.setAngle(270);
        plane.move(200);
        plane.hasTrail = true;
        plane.pausetime = 0;
        sun(20);
    }

    public void square(int a) {
        plane.move(a);
        plane.turnLeft(90);
        plane.move(a);
        plane.turnLeft(90);
        plane.move(a);
        plane.turnLeft(90);
        plane.move(a);
        plane.turnLeft(90);
    }

    public void equitriangle(int a) {
        plane.move(a);
        plane.turnLeft(120);
        plane.move(a);
        plane.turnLeft(120);
        plane.move(a);
        plane.turnLeft(120);
    }

    public void rectangle(int a, int b) {


    }

    public void fillRec(int a, int b) {


    }

    public void tree(int a) {

    }

    public void sun(int a) {
        plane.setTrailColor(255, 255, 0);
        plane.fillCircle(a);
    }

}



