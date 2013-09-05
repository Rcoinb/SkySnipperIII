package com.greatdevs.GameWorld.Multiplayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.greatdevs.Game;
import com.greatdevs.GameWorld.MultiPlayer;
import com.greatdevs.Image.Icons;

public class StarMP {
	public int x, y, size, speed, width, height;
	
	Random r1 = new Random();
	Random r2 = new Random();
	
	private Icons icons = new Icons();
	private BufferedImage image;
	
	public StarMP(int x, int y, int speed){
		this.x = x;
		this.y = y;
		this.speed = speed;
		size = r1.nextInt(3);
		image = icons.star[size];
	}
	
	public StarMP(MultiPlayer world, int x, int y, int speed, int size){
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.size = size;
		image = icons.star[size];
		if (world.SERVER){
			world.serverwork.spawn = 1;
			world.serverwork.stary = y;
			world.serverwork.starsize = size;
		}
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
