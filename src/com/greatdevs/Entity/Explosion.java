package com.greatdevs.Entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.greatdevs.Game;

public class Explosion {
	public int x, y;
	public boolean explosionfinished = false;
	
	private double animation;
	
	public BufferedImage image;
	
	public Explosion(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, 50, 50);
	}
	
	public void render(Graphics g){
		g.drawImage(image, x, y, getRect().width, getRect().height, null);
	}
	
	public void update(Game game){	
		animation += 0.3;
		
		if (animation > 5){
			explosionfinished = true;
		}
		
		image = game.icons.explosion[(int) animation];
	}
}
