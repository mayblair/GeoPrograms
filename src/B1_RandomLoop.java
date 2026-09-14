public class B1_RandomLoop extends World {

    public int randNum;

    public void go() {
        plane.hasTrail = true;
        plane.pausetime = 2 ;

        for (int i = 0; i < 15; i = i+1) {
            plane.teleport(i * 100, 400);
            randNum = plane.random(0, 3);
            if (randNum == 0) {
                square();
            }
            if (randNum == 1) {
                rectangle(200, 100);
            }
            if (randNum == 2) {
                rectangle(60, 100);
            }
        }
    }

    public void square() {
        plane.setAngle(90);
        plane.move(100);
        plane.turnLeft(90);
        plane.move(100);
        plane.turnLeft(90);
        plane.move(100);
        plane.turnLeft(90);
        plane.move(100);
        plane.turnLeft(90);
    }

    public void rectangle(int a, int b) {
        plane.setAngle(90);
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


    public void fillRec(int a, int b) {


    }

    public void tree(int a) {

    }

    public void sun(int a) {
        plane.setTrailColor(255, 255, 0);
        plane.fillCircle(a);
    }

}



