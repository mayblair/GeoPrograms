public class B0_IfThenLoop extends World {

    public void go() {
        plane.hasTrail = true;
        plane.trailWidth = 3;

        for(int x=0;x<5;x=x+1) {
            // use an if/then!
            System.out.println("x: " + x);
            plane.square(100);
        }
    }
}