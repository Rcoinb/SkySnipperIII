package com.greatdevs.Image;

import java.awt.image.BufferedImage;

public class SpriteSheet {

BufferedImage spriteSheet;
	
public SpriteSheet(BufferedImage ss){
	this.spriteSheet = ss;
}
public BufferedImage grabSprite(int x , int y , int w, int h){
	BufferedImage sprite = spriteSheet.getSubimage(x, y, w, h);
	return sprite;
	}
    
}
