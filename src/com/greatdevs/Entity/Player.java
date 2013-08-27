package com.greatdevs.Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.greatdevs.Game;
import com.greatdevs.Entity.SuperPower.*;
import com.greatdevs.Image.Icons;
import com.greatdevs.Sound.Sound;

public class Player{
	public int x, y, width, height, speed, maxspeed, hp, maxhp, reloadtime;
	private int shieldtime = 0, shieldanimation = 0, magnettime = 0, magnetanimation = 0;
	public int superpowertime;
	private int printhptime, regun = reloadtime;
	public boolean printhp, shield, magnet, superpower = true, superpowerinuse;
	public int type[];
	
	Icons icons = new Icons();
	
	Game game;
	SuperPower superpowerclass;
	public PlayerFireDraw playerfiredraw = new PlayerFireDraw();
	
	public BufferedImage image;
	
	public Player(int x, int y){
		this.x = x;
		this.y = y;
		type = ShipTypes.getType();
		maxhp = type[0];
		maxspeed = type[1];
		reloadtime = type[2];
		hp = maxhp;
		speed = maxspeed;
		printhp();
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, width, height);
	}
	
	public Rectangle getShieldRect(){
		return new Rectangle(x - 100 - shieldanimation / 2, y - 100 - shieldanimation / 2, width + 200 + shieldanimation, height + 200 + shieldanimation);
	}
	
	public Rectangle getMagnetRect(){
		return new Rectangle(x - 100 - magnetanimation / 2, y - 100 - magnetanimation / 2, width + 200 + magnetanimation, height + 200 + magnetanimation);
	}
	
	public void printhp(){
		printhp = true;
	}
	
	public void render(Graphics g){
		if (y < 0) y = 0;
		if (y > ((Game.HEIGHT * Game.SCALE) - getRect().height)) y = ((Game.HEIGHT * Game.SCALE) - getRect().height);
		if (x < 0) x = 0;
		if (x > ((Game.WIDTH * Game.SCALE) - getRect().width)) x = ((Game.WIDTH * Game.SCALE) - getRect().width);
		playerfiredraw.render(g, this);
		if (superpowerinuse){
			superpowerclass.render(g);
		}
		g.drawImage(image, x, y, getRect().width, getRect().height, null);
		
		if (printhp){
			g.setFont(new Font("Arial", Font.BOLD, 20));
			int strw = (int) g.getFontMetrics().getStringBounds("HP: " + hp, g).getWidth();
			g.setColor(Color.WHITE);
			g.drawString("HP: " + hp, ((x + getRect().width / 2) - (strw / 2)), (y - 15));
		}
		
		if (shield){
			g.setColor(Color.YELLOW);
			g.drawOval(getShieldRect().x, getShieldRect().y, getShieldRect().width, getShieldRect().height);
		}
		
		if (magnet){
			g.setColor(Color.GREEN);
			g.drawOval(getMagnetRect().x, getMagnetRect().y, getMagnetRect().width, getMagnetRect().height);
		}
	}

	public void setsuperpower(SuperPower superpowerclass){
		this.superpowerclass = superpowerclass;
		superpowerinuse = true;
		if (superpowerclass != null) superpowerclass.init(this);
	}
	
	public void stopsuperpower(){
		superpowerclass = null;
		superpowerinuse = false;
		superpower = false;
		superpowertime = 0;
	}
	
	public void update(Game game){	
		if (game.input.up.down) y -= speed;
		if (game.input.down.down) y += speed;
		if (game.input.left.down) x -= speed;
		if (game.input.right.down) x += speed;
		
		regun ++;
		
		if (regun > reloadtime && game.input.attack.clicked){		
		firebullet();
		regun = 0;
		Sound.shootpressed.play();
		}
		
		if (x <= 350) speed = maxspeed;
		if (x > 350 && x <= 450) speed = 3;
		if (x > 450 && x <= 550) speed = 1;
		if (x > 600) x = 600;
		
		if (!ShipTypes.ownType) image = game.icons.player[type[4]];
		else if (ShipTypes.ownType) image = ShipTypes.playerimage;
		
		width = image.getWidth();
		height = image.getHeight();
		
		if (printhp) printhptime ++;
		if (printhptime > 100) {printhp = false; printhptime = 0;}
		
		this.game = game;
		if (shield){
			shieldtime ++;
			shieldanimation ++;
			if (shieldanimation > 50){
				shieldanimation  = 0;
			}
			if (shieldtime >= 750){
				shieldtime = 0;
				shieldanimation = 0;
				shield = false;
			}
		}
		
		if (magnet){
			magnettime ++;
			magnetanimation --;
			if (magnetanimation < -50){
				magnetanimation  = 0;
			}
			if (magnettime >= 750){
				magnettime = 0;
				magnetanimation = 0;
				magnet = false;
			}
		}
		
		if (!superpower){
		superpowertime ++;
		if (superpowertime > 2500){
			superpower = true;
			superpowertime = 0;
		}
		}
		
		if (superpowerinuse){
			superpowerclass.update(game);
		}
		if (superpower) power(game);
	}
	
	public void firebullet(){
		if (!ShipTypes.ownType){
		if (ShipTypes.getIntType() == 1){
			game.update.entity.bulletarray.add(new Bullet((x + getRect().width),(y + 20),25));
			game.update.entity.bulletarray.add(new Bullet((x + getRect().width),(y + getRect().height - 20),25));
		}
		if (ShipTypes.getIntType() == 2){
			game.update.entity.bulletarray.add(new Bullet((x + getRect().width),(y + (getRect().height / 2 - 1)),25));
		}
		if (ShipTypes.getIntType() == 3){
			game.update.entity.bulletarray.add(new Bullet((x + getRect().width),(y + 10),25));
			game.update.entity.bulletarray.add(new Bullet((x + getRect().width),(y + getRect().height - 10),25));
		}
		if (ShipTypes.getIntType() == 4){
			game.update.entity.bulletarray.add(new Bullet((x + getRect().width),(y),25));
			game.update.entity.bulletarray.add(new Bullet((x + getRect().width),(y + getRect().height),25));
		}
		if (ShipTypes.getIntType() == 5){
			game.update.entity.bulletarray.add(new Bullet((x + getRect().width),(y + (getRect().height / 2 - 1)),25));
		}
		if (ShipTypes.getIntType() == 6){
			game.update.entity.bulletarray.add(new Bullet((x + getRect().width),(y + (getRect().height / 2 - 1)),25));
			game.update.entity.bulletarray.add(new Bullet((x + getRect().width),(y + 10),25));
			game.update.entity.bulletarray.add(new Bullet((x + getRect().width),(y + getRect().height - 10),25));
		}
		}
		else if (ShipTypes.ownType){
			game.update.entity.bulletarray.add(new Bullet((x + getRect().width),(y + 20),25));
			game.update.entity.bulletarray.add(new Bullet((x + getRect().width),(y + getRect().height - 20),25));
		}
	}
	
	public void magnetbonus(){
		magnet = true;
		magnettime = 0;
	}
	
	public void shieldbonus(){
		shield = true;
		shieldtime = 0;
	}
	
	public void power(Game game){
		if (game.input.power.clicked && !superpowerinuse){
			if (!ShipTypes.ownType){
			if (ShipTypes.getIntType() == 1) setsuperpower(new P1Power());
			if (ShipTypes.getIntType() == 2) setsuperpower(new P2Power());
			if (ShipTypes.getIntType() == 3) setsuperpower(new P3Power());
			if (ShipTypes.getIntType() == 4) setsuperpower(new P4Power());
			if (ShipTypes.getIntType() == 5) setsuperpower(new P5Power());
		    if (ShipTypes.getIntType() == 6) setsuperpower(new P6Power());
			}
			else if (ShipTypes.ownType){
				Random r = new Random();
				int i = r.nextInt(6) + 1;
				if (i == 1) setsuperpower(new P1Power());
				if (i == 2) setsuperpower(new P2Power());
				if (i == 3) setsuperpower(new P3Power());
				if (i == 4) setsuperpower(new P4Power());
				if (i == 5) setsuperpower(new P5Power());
			    if (i == 6) setsuperpower(new P6Power()); 
			}
			Sound.superpower.play();
		}
	}
}
