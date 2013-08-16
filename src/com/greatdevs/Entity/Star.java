package com.greatdevs.Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.greatdevs.Game;
import com.greatdevs.Image.Icons;

public class Star {
	public int x, y, size, speed, width, height;
	
	Random r1 = new Random();
	Random r2 = new Random();
	
	private Icons icons = new Icons();
	private BufferedImage image;
	
	public Star(int x, int y, int speed){
		this.x = x;
		this.y = y;
		this.speed = speed;
		
		size = r1.nextInt(3);
		image = icons.star[r2.nextInt(3)];
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, width, height);
	}
	
	public void render(Graphics g){
		g.setColor(Color.BLUE);
		g.drawImage(image, x, y, getRect().width, getRect().height, null);
	}
	
	public void update(Game game){	
		x -= speed;
		width = ((size + 1) * 25);
		height = ((size + 1) * 25);
	}
}
