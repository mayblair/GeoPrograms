public class HowManyRed extends World {
    public int red;
    public int blue;
    public int green;
    public int count;
    public int total;

    public void go() {
        System.out.println("This will be printed to the  window. ");
        plane.teleport(0, 0);
        plane.loadBackGround("waldobig.png");
        plane.showBackGround();
        count=0;
        total=0;
        for (int row = 1; row < 488; row = row + 1) {
            for (int col = 1; col < 488; col++) {
                total=total+1;
                plane.teleport(col, row);

                red = plane.howMuchRed();
                green = plane.howMuchGreen();
                blue = plane.howMuchBlue();
//                plane.teleport(col, 488 - row);
                if(red>170 && green<100 && blue<100) {
                    plane.setPixelColor(0, 200, 0);
                    count=count+1;
                }
            }
        }

        System.out.println("this is how many red "+count);
        System.out.println("this is how many total "+total);


    }
}