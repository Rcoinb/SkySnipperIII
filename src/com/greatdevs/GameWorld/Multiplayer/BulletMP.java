package com.greatdevs.GameWorld.Multiplayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.greatdevs.Game;

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
	
	public void update(Game game){	
		x += speed;
	}
}
