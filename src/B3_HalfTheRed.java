public class B3_HalfTheRed extends World {
    public int red;
    public int blue;
    public int green;

    public void go() {
        plane.loadBackGround("AdventureTime.png");
        plane.showBackGround();

        for (int y = 1; y < 488; y = y + 1) {
            for (int x = 1; x < 488; x = x + 1) {
                plane.teleport(x, y);

                red = plane.howMuchRed();
                green = plane.howMuchGreen();
                blue = plane.howMuchBlue();
//                plane.teleport(x, 488 - y);
                plane.setPixelColor(red / 2, green, blue);
            }
        }
    }
}

