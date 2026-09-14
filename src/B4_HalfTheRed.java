public class B4_HalfTheRed extends World {
    public int red;
    public int blue;
    public int green;

    public void go() {
        plane.showBackGround();

        for (int y = 1; y < 488; y = y + 1) {
            for (int x = 1; x < 488; x = x + 1) {
                plane.teleport(x, y);

                red = plane.howMuchRed();
                green = plane.howMuchGreen();
                blue = plane.howMuchBlue();

                plane.setPixelColor(red / 2, green, blue);
            }
        }
    }
}

