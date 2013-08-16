package com.greatdevs.Entity.Boss;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import com.greatdevs.Game;
import com.greatdevs.Entity.Bullet;
import com.greatdevs.Entity.Explosion;
import com.greatdevs.Entity.Player;

public class Boss3 extends Boss{
	public int x = 600, y, width = 150, height = 150, firetime = 0;
	public boolean canfire = false;
	
	ArrayList<bullet> bbulletarray = new ArrayList<bullet>();
	
	public Boss3(){
		hp = 75;
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, width, height);
	}
	
	public void render(Graphics g){
		g.fillRect(getRect().x, getRect().y, getRect().width, getRect().height);
		for (final bullet bull : bbulletarray){
			bull.render(g);
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		int strw = (int) g.getFontMetrics().getStringBounds("HP: " + hp, g).getWidth();
		g.drawString("HP: " + hp, ((x + getRect().width / 2) - (strw / 2)), (y - 15));
	}
	
	public void die(Game game){
		hp = 0;
		for (int i = 0; i < game.update.entity.bossarray.size(); i ++){
			game.update.entity.explosionarray.add(new Explosion(x, y));
			game.update.entity.explosionarray.add(new Explosion(x + width, y));
			game.update.entity.explosionarray.add(new Explosion(x, y + height));
			game.update.entity.explosionarray.add(new Explosion(x + width, y + height));
			game.update.entity.bossarray.remove(this);
		}
		game.update.gameworld.SCORE += 1250;
	}
	
	public void returnfire(){
		firetime = 0;
		canfire = false;
	}
	
	public void update(Game game){	
		if (hp <= 0){
			die(game);
		}	
		firetime ++;
		if (firetime >= 25){
			canfire = true;
		}
		for (int i = 0; i < bbulletarray.size(); i ++){
			bullet bull = (bullet) bbulletarray.get(i);
			bull.update(game);
			for(Player player : game.update.entity.playerarray){
				if (bull.getRect().intersects(player.getRect())){
					game.update.entity.explosionarray.add(new Explosion(bull.x, bull.y));
					bbulletarray.remove(i);
					player.hp --;
					player.printhp();
				}
			}
			if (bull.x < 0 - (bull.getRect().width)) bbulletarray.remove(i);
		}
		for(Player player : game.update.entity.playerarray){
			if (y < player.y) y += 5;
			if ((y + height) > player.y + player.height) y -= 5;			
			if ((new Rectangle(player.x, y, width, height).intersects(player.getRect())) && canfire) bbulletarray.add(new bullet(x, y + (height / 2) - 1, 25, this)); 
		}
		
		for (int i = 0; i < game.update.entity.bulletarray.size(); i ++){
			Bullet bullet = (Bullet) game.update.entity.bulletarray.get(i);
			if (bullet.getRect().intersects(getRect())){
				game.update.entity.explosionarray.add(new Explosion(bullet.x, bullet.y));
				game.update.entity.bulletarray.remove(i);
				hp --;
			}
		}
	}
	
	class bullet{
		int x, y, speed;
		public bullet(int x, int y, int speed, Boss3 boss){
			this.x = x;
			this.y = y;
			this.speed = speed;
			boss.returnfire();
		}
		public Rectangle getRect(){
			return new Rectangle(x, y, 50, 2);
		}
		public void render(Graphics g){
			g.setColor(Color.BLUE);
			g.fillRect(getRect().x, getRect().y, getRect().width, getRect().height);
		}
		public void update(Game game){
			x -= speed;
		}
	}
}