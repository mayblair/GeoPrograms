public class B1_MoreLoop extends World {

    public void go() {
        plane.pausetime = 1;

        for (int x = 0; x < 200; x = x + 50) {
            plane.teleport(x, 150);
            square(35);
            System.out.println("x: " + x);
        }
        plane.teleport(300, 300);
    }

    public void square(int distance) {
        for (int z = 0; z < 4; z=z+1){
            plane.isTrail = true;
            plane.move(distance);
            plane.turn(90);
            System.out.println("z: " + z);
        }
    }
}

