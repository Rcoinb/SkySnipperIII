package com.greatdevs.GameWorld.Multiplayer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.greatdevs.Game;
import com.greatdevs.InputHandler;
import com.greatdevs.GameWorld.MultiPlayer;
import com.greatdevs.Image.Icons;
import com.greatdevs.Sound.Sound;

public class PlayerMP {
	public int x, y, width, height, speed, maxspeed, hp, maxhp, reloadtime, printhptime;
	private int regun = reloadtime;
	public boolean printhp, shield, magnet, superpower = true, superpowerinuse, fire = false;
	public int type[];

	Icons icons = new Icons();

	MultiPlayer mp;
	Game game;
	InputHandler input;

	public BufferedImage image;

	public PlayerMP(MultiPlayer mp, int[] type, InputHandler input, int x, int y) {
		this.mp = mp;
		this.input = input;
		this.x = x;
		this.y = y;
		this.type = type;
		image = icons.player[type[4]];
		width = image.getWidth();
		height = image.getHeight();
		maxhp = type[0];
		maxspeed = type[1];
		reloadtime = type[2];
		hp = maxhp;
		speed = maxspeed;
	}

	public void changeType(int[] type){
		this.type = type;
		image = icons.player[type[4]];
		width = image.getWidth();
		height = image.getHeight();
		maxhp = type[0];
		maxspeed = type[1];
		reloadtime = type[2];
	}
	
	public void changeImageType(int type){
		image = icons.player[type];
		width = image.getWidth();
		height = image.getHeight();
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}

	public void printhp(){
		printhp = true;
	}
	
	public void render(Graphics g) {
		if (y < 0)
			y = 0;
		if (y > ((Game.HEIGHT * Game.SCALE) - getRect().height))
			y = ((Game.HEIGHT * Game.SCALE) - getRect().height);
		if (x < 0)
			x = 0;
		if (x > ((Game.WIDTH * Game.SCALE) - getRect().width))
			x = ((Game.WIDTH * Game.SCALE) - getRect().width);
		g.drawImage(image, x, y, getRect().width, getRect().height, null);
		
		if (printhp){
			g.setFont(new Font("Arial", Font.BOLD, 20));
			int strw = (int) g.getFontMetrics().getStringBounds("HP: " + hp, g).getWidth();
			g.setColor(Color.WHITE);
			g.drawString("HP: " + hp, ((x + getRect().width / 2) - (strw / 2)), (y - 15));
		}
	}

	public void update(Game game) {
		if (input != null) {
			if (input.up.down) y -= speed;
			if (input.down.down) y += speed;
			if (input.left.down) x -= speed;
			if (input.right.down) x += speed;
		}

		regun++;
		
		if (printhp) printhptime ++;
		if (printhptime > 100) {printhp = false; printhptime = 0;}
		
		if (x <= 350) speed = maxspeed;
		if (x > 350 && x <= 450) speed = 3;
		if (x > 450 && x <= 550) speed = 1;
		if (x > 600) x = 600;

		if (regun > reloadtime && game.input.attack.clicked && input != null) {
			firebullet();
			regun = 0;
			Sound.play("shootpressed.wav");
			fire = false;
		}
	}

	public void firebullet() {
		mp.entity.bulletarray.add(new BulletMP((x + getRect().width),
				(y + 20), 25));
		mp.entity.bulletarray.add(new BulletMP((x + getRect().width), (y
				+ getRect().height - 20), 25));
		fire = true;
	}
}
