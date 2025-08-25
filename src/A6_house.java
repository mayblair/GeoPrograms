public class A6_house extends World {

    public void go() {
        plane.isTrail = true;
        plane.pausetime = 0;

      house();
    }

    public void big() {
        plane.move(111);
        plane.turn(90);
        plane.move(111);
        plane.turn(90);
        plane.move(111);
        plane.turn(90);
        plane.move(111);
        plane.turn(90);

    }

    public void small(int a, int b) {
        plane.move(a);
        plane.turn(90);
        plane.move(b);
        plane.turn(90);
        plane.move(a);
        plane.turn(90);
        plane.move(b);
        plane.turn(90);


    }


    public void house() {
        plane.isTrail = true;
        plane.startingAngle(0);
        plane.teleport(200,400);
        square(50);
        plane.teleport(200,350);
        plane.startingAngle(0);
        equitriangle(50);
        plane.isTrail = false;
        plane.startingAngle(270);
        plane.move(200);
        plane.isTrail = true;
        plane.pausetime = 0;
        sun(20);
    }

    public void square(int a) {
        plane.move(a);
        plane.turn(90);
        plane.move(a);
        plane.turn(90);
        plane.move(a);
        plane.turn(90);
        plane.move(a);
        plane.turn(90);
    }

    public void equitriangle(int a) {
        plane.move(a);
        plane.turn(120);
        plane.move(a);
        plane.turn(120);
        plane.move(a);
        plane.turn(120);

    }

    public void rectangle(int a, int b) {


    }

    public void fillRec(int a, int b) {


    }

    public void tree(int a) {

    }

    public void sun(int a) {
        plane.setColor(255, 255, 0);
        plane.fillCircle(a);
    }

}



