package com.greatdevs.Entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import com.greatdevs.Game;

public class Coin {
	public int x, y, speed;
	
	private double animation;
	
	public BufferedImage image;
	
	public Coin(int x, int y, int speed){
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, 32, 32);
	}
	
	public void render(Graphics g){
		g.drawImage(image, x, y, getRect().width, getRect().height, null);
	}
	
	public void update(Game game){	
		x -= speed;
		
		animation += 0.3;
		
		if (animation > 7){
			animation = 0;
		}
		
		image = game.icons.coin[(int) animation];
	}
}
