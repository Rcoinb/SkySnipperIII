package com.greatdevs.GameWorld.Multiplayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.greatdevs.Game;
import com.greatdevs.Entity.Particle;

public class BulletMP {
	public int x, y, speed;
	
	public BulletMP(int x, int y, int speed){
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
	
	public void update(Game game, EntityMP e){	
		e.particlearray.add(new Particle(Color.LIGHT_GRAY, 10, 5, x, y, 2));
		x += speed;
	}
}
