package com.greatdevs.Entity;

import java.awt.Color;
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
		if (animation == 0) {
			game.update.entity.particlearray.add(new Particle(Color.RED, 25, 25, x + 25, y + 25, 3));
			game.update.entity.particlearray.add(new Particle(Color.LIGHT_GRAY, 30, 25, x + 25, y + 25, 2));
		}
		animation += 0.3;
		
		if (animation > 5){
			explosionfinished = true;
		}
		
		image = game.icons.explosion[(int) animation];
	}
}
