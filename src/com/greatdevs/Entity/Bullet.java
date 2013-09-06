package com.greatdevs.Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.greatdevs.Game;

public class Bullet {	
	public int x, y, speed;
	
	public Bullet(int x, int y, int speed){
		this.x = x;
		this.y = y;
		this.speed = speed;
		
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, 50, 2);
	}
	
	public void render(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(x, y, getRect().width, getRect().height);
	}
	
	public void update(Game game){	
		game.update.entity.particlearray.add(new Particle(Color.LIGHT_GRAY, 10, 5, x, y, 2));
		x += speed;
	}
}
