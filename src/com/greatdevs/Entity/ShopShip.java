package com.greatdevs.Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.greatdevs.Game;

public class ShopShip {
	public int x, y, speed;
	
	public BufferedImage image;
	
	public ShopShip(int x, int y, int speed){
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, 100, 50);
	}
	
	public void render(Graphics g){
		g.setColor(Color.RED);
		g.drawImage(image, x, y, getRect().width, getRect().height, null);
	}
	
	public void update(Game game){	
		x -= speed;
		
		image = game.icons.shopship;
	}
}
