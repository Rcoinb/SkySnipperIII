package com.greatdevs.Entity.Boss;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import com.greatdevs.Game;
import com.greatdevs.Entity.Bullet;
import com.greatdevs.Entity.Explosion;
import com.greatdevs.Entity.Player;
import com.greatdevs.Entity.Star;
import com.greatdevs.GameWorld.SinglePlayer;
import com.greatdevs.Image.Icons;
import com.greatdevs.Sound.Sound;

public class Boss2 extends Boss{
	public int x = 1000, y, width = 150, height = 150, firetime = 0, gensp1 = 0;
	public boolean canfire = false;
	
	ArrayList<bullet> bbulletarray = new ArrayList<bullet>();
	ArrayList<sp1> sp1array = new ArrayList<sp1>();
	
	Icons icons = new Icons();
	
	public Boss2(){
		hp = 75;
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, width, height);
	}
	
	public void render(Graphics g){
		g.drawImage(icons.boss[1], getRect().x, getRect().y, getRect().width, getRect().height, null);
		
		for (final bullet bull : bbulletarray){
			bull.render(g);
		}
		
		for (int i = 0; i < sp1array.size(); i ++){
			sp1 sp1class = (sp1) sp1array.get(i);
			sp1class.render(g);
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
		SinglePlayer.SCORE += 1250;
	}
	
	public void returnfire(){
		firetime = 0;
		canfire = false;
	}
	
	public void update(Game game){
		gensp1 ++;
		for (int i = 0; i < sp1array.size(); i ++){
			sp1 sp1class = (sp1) sp1array.get(i);
			sp1class.update(game);
			if (sp1class.finish){
				sp1array.remove(i);
			}
		}
		if (gensp1 > 250){
			sp1array.add(new sp1());
			gensp1 = 0;
		}
		if (hp <= 0){
			die(game);
		}	
		if (x > ((Game.WIDTH * Game.SCALE) - width - 50)){
			x -= 3;
		}
		firetime ++;
		if (firetime >= 35){
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
					Sound.play("shoot.wav");
				}
			}
			if (bull.x < 0 - (bull.getRect().width)) bbulletarray.remove(i);
		}
		for(Player player : game.update.entity.playerarray){
			if (y < player.y) y += 3;
			if ((y + height) > player.y + player.height) y -= 3;			
			if ((new Rectangle(player.x, y, width, height).intersects(player.getRect())) && canfire){
				bbulletarray.add(new bullet(x, y + (height / 2) - 25, 25, this)); 
				bbulletarray.add(new bullet(x, y + (height / 2) + 25, 25, this)); 
				Sound.play("shootpressed.wav");
			}
		}
		
		for (int i = 0; i < game.update.entity.bulletarray.size(); i ++){
			Bullet bullet = (Bullet) game.update.entity.bulletarray.get(i);
			if (bullet.getRect().intersects(getRect())){
				game.update.entity.explosionarray.add(new Explosion(bullet.x, bullet.y));
				game.update.entity.bulletarray.remove(i);
				hp --;
				Sound.play("shoot.wav");
			}
		}
	}
	
	class bullet{
		int x, y, speed;
		
		public bullet(int x, int y, int speed, Boss2 boss){
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
	
	class sp1{
		boolean finish = false;
		public sp1(){
			Sound.play("bosssuperpower.wav");
		}
		public void render(Graphics g){
		
		}		
		public void update(Game game){		 
			 for (int i = 0; i < game.update.entity.playerarray.size(); i++){
				 Player player = (Player) game.update.entity.playerarray.get(i);
				 if (new Rectangle(player.x, y, width, height).intersects(player.getRect())){
					 game.update.entity.stararray.add(new Star(x + 50, y, (new Random().nextInt(10) + 15)));
					 game.update.entity.stararray.add(new Star(x + 50, y + height, (new Random().nextInt(10) + 15)));
					 game.update.entity.stararray.add(new Star(x + 50, y + (height / 2) - 25, (new Random().nextInt(10) + 15)));
					 game.update.entity.stararray.add(new Star(x + 50, y + (height / 2) + 25, (new Random().nextInt(10) + 15)));
					 finish = true;
				 }
			 }
		}
	}
}