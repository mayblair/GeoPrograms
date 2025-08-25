public class B0_BasicLoop extends World {

    public void go() {
        plane.isTrail=true;
        plane.trailWidth=2;

        for(int x=0;x<5;x=x+1) {
            System.out.println("x: "+ x);
            plane.square(100);
        }
    }
}
