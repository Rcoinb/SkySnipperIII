package com.greatdevs.Image;

import java.awt.image.BufferedImage;

public class ShipIcons {
	SpriteSheet ss;
	
	public BufferedImage bg;
	public BufferedImage[] component = new BufferedImage[19];
	
	public ShipIcons(){
	   BufferedIL loader = new  BufferedIL();
       BufferedImage spriteSheet;
       spriteSheet = loader.loadImage("/shipicons.png");
       
       ss = new SpriteSheet(spriteSheet);
       
       try{
    	   component[0] = ss.grabSprite(0, 0, 50, 30);
    	   component[1] = ss.grabSprite(0, 30, 50, 30);
    	   component[2] = ss.grabSprite(50, 0, 100, 50);
    	   component[3] = ss.grabSprite(50, 50, 10, 10);
    	   component[4] = ss.grabSprite(150, 0, 30, 30);
    	   component[5] = ss.grabSprite(150, 30, 25, 15);
    	   component[6] = ss.grabSprite(180, 0, 30, 30);
    	   component[7] = ss.grabSprite(180, 30, 30, 30);
    	   component[8] = ss.grabSprite(210, 0, 20, 20);
    	   component[9] = ss.grabSprite(210, 20, 20, 20);
    	   component[10] = ss.grabSprite(210, 40, 15, 10);
    	   component[11] = ss.grabSprite(230, 0, 50, 15);
    	   component[12] = ss.grabSprite(230, 15, 50, 15);
    	   component[13] = ss.grabSprite(230, 30, 40, 40);
    	   component[14] = ss.grabSprite(210, 50, 20, 50);
    	   component[15] = ss.grabSprite(0, 60, 100, 50);
    	   component[16] = ss.grabSprite(0, 110, 100, 50);
    	   component[17] = ss.grabSprite(100, 60, 100, 50);
    	   component[component.length - 1] = ss.grabSprite(950, 0, 50, 50);
    	   
    	   bg = ss.grabSprite(999, 999, 1, 1);
       }
       catch(Exception e){
    	   e.printStackTrace();
       }
   }
}
