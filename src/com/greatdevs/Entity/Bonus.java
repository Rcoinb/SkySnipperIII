package com.greatdevs.Entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import com.greatdevs.Game;
import com.greatdevs.Image.Icons;

public class Bonus {
	public int x, y, speed, type;
	
	Random r1 = new Random();	
	Icons icons = new Icons();
	public BufferedImage image;
	
	public Bonus(int x, int y, int speed){
		this.x = x;
		this.y = y;
		this.speed = speed;
		
		type = (r1.nextInt(4) + 1);
		
		if (type == 1) image = icons.hpbonus; 
		if (type == 2) image = icons.speeddownbonus; 
		if (type == 3) image = icons.shieldbonus;
		if (type == 4) image = icons.magnetbonus;
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, 50, 50);
	}
	
	public void render(Graphics g){
		g.drawImage(image, x, y, getRect().width, getRect().height, null);
	}
	
	public void update(Game game){	
		x -= speed;
	}
}
