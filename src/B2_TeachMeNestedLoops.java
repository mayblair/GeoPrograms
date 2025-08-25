public class B2_TeachMeNestedLoops extends World {

   public void go() {
      System.out.println("This will be printed to the  window. ");
      plane.teleport(0,0);
   
      // This outer for loop will repeat the inner loop
      for(int y=0; y<500; y=y+1){
         // This inner for loop repeats the teleport and the setPixelColor
         for(int x=0; x<750; x=x+1){
            plane.teleport(x, y);
            plane.setPixelColor(200,x/4,y/3);
            System.out.println("Pixel ("+x+ ", "+y+") has r: " +200+", g: " + (x/4) +", b: "+ (y/3));
         }        	
      }
      plane.teleport(600,200);
   }
}