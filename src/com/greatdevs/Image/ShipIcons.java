package com.greatdevs.Image;

import java.awt.image.BufferedImage;

public class ShipIcons {
	SpriteSheet ss;
	
	public BufferedImage bg;
	
	public ShipIcons(){
	   BufferedIL loader = new  BufferedIL();
       BufferedImage spriteSheet;
       spriteSheet = loader.loadImage("/shipicons.png");
       
       ss = new SpriteSheet(spriteSheet);
       
       try{
    	   bg = ss.grabSprite(999, 999, 1, 1);
       }
       catch(Exception e){
    	   e.printStackTrace();
       }
   }
}
