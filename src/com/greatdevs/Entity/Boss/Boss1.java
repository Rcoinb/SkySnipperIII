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
import com.greatdevs.Entity.Star;
import com.greatdevs.Image.Icons;
import com.greatdevs.Sound.Sound;

public class Boss1 extends Boss{
	public int x = 1000, y, width = 200, height = 100, firetime = 0, gensp1 = 0, gensp2 = 0;
	public boolean canfire = false;
	
	ArrayList<bullet> bbulletarray = new ArrayList<bullet>();
	ArrayList<sp1> sp1array = new ArrayList<sp1>();
	ArrayList<sp2> sp2array = new ArrayList<sp2>();
	
	Icons icons = new Icons();
	
	public Boss1(){
		hp = 50;
	}
	
	public Rectangle getRect(){
		return new Rectangle(x, y, width, height);
	}
	
	public void render(Graphics g){
		g.drawImage(icons.boss[0], getRect().x, getRect().y, getRect().width, getRect().height, null);
		
		for (final bullet bull : bbulletarray){
			bull.render(g);
		}
		
		for (int i = 0; i < sp1array.size(); i ++){
			sp1 sp1class = (sp1) sp1array.get(i);
			sp1class.render(g);
		}
		
		for (int i = 0; i < sp2array.size(); i ++){
			sp2 sp2class = (sp2) sp2array.get(i);
			sp2class.render(g);
		}
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		int strw = (int) g.getFontMetrics().getStringBounds("HP: " + hp, g).getWidth();
		g.drawString("HP: " + hp, ((x + getRect().width / 2) - (strw / 2)), (y - 15));
	}
	
	public void returnfire(){
		firetime = 0;
		canfire = false;
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
	
	public void update(Game game){	
		gensp1 ++;
		gensp2 ++;
		for (int i = 0; i < sp1array.size(); i ++){
			sp1 sp1class = (sp1) sp1array.get(i);
			sp1class.update(game);
			if (sp1class.timer > 100){
				sp1array.remove(i);
			}
		}
		for (int i = 0; i < sp2array.size(); i ++){
			sp2 sp2class = (sp2) sp2array.get(i);
			sp2class.update(game);
			if (sp2class.spw < 150){
				sp2array.remove(i);
			}
		}
		if (gensp1 > 750){
			sp1array.add(new sp1());
			gensp1 = 0;
		}
		if (gensp2 > 1150){
			sp2array.add(new sp2());
			gensp2 = 0;
		}
		if (x > ((Game.WIDTH * Game.SCALE) - width - 50)){
			x -= 3;
		}
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
					Sound.shoot.play();
				}
			}
			if (bull.x < 0 - (bull.getRect().width)) bbulletarray.remove(i);
		}
		for(Player player : game.update.entity.playerarray){
			if (y < player.y) y += 5;
			if ((y + height) > player.y + player.height) y -= 5;		
			if ((new Rectangle(player.x, y, width, height).intersects(player.getRect())) && canfire){
				bbulletarray.add(new bullet(x, y + (height / 2) - 1, 25, this)); 
				Sound.shootpressed.play();
			}
		}
		
		for (int i = 0; i < game.update.entity.bulletarray.size(); i ++){
			Bullet bullet = (Bullet) game.update.entity.bulletarray.get(i);
			if (bullet.getRect().intersects(getRect())){
				game.update.entity.explosionarray.add(new Explosion(bullet.x, bullet.y));
				game.update.entity.bulletarray.remove(i);
				hp --;
				Sound.shoot.play();
			}
		}
	}
	
	class bullet{
		int x, y, speed;
		public bullet(int x, int y, int speed, Boss1 boss){
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
		public int spx, spy, spw, sph, timer;
		public sp1(){
			Sound.bosssuperpower.play();
		}
		public Rectangle getSPRect(){
			return new Rectangle(spx, spy, spw, sph);
		}
		public void render(Graphics g){
			g.setColor(Color.CYAN);					
			g.drawOval(getSPRect().x, getSPRect().y, getSPRect().width, getSPRect().height);			
		}		
		public void update(Game game){
			 spw += 25;
			 sph += 25;
			 spx = x + width / 2 - getSPRect().width / 2;
			 spy = y + height / 2 - getSPRect().height / 2;
			 timer ++;
			 
			 for (int i = 0; i < game.update.entity.stararray.size(); i++){
				 Star star = (Star) game.update.entity.stararray.get(i);
				 if (getSPRect().intersects(star.getRect())){
					 star.size = 4;
				 }
			 }
		}
	}
	
	class sp2{
		public int spx, spy, spw = 2000, sph = 2000;
		public sp2(){
			Sound.bosssuperpower.play();
		}
		public Rectangle getSPRect(){
			return new Rectangle(spx, spy, spw, sph);
		}
		public void render(Graphics g){
			g.setColor(Color.CYAN);					
			g.drawOval(getSPRect().x, getSPRect().y, getSPRect().width, getSPRect().height);			
		}		
		public void update(Game game){
			 spw -= 25;
			 sph -= 25;
			 spx = x + width / 2 - getSPRect().width / 2;
			 spy = y + height / 2 - getSPRect().height / 2;
			 
			 for (int i = 0; i < game.update.entity.playerarray.size(); i++){
				 Player player = (Player) game.update.entity.playerarray.get(i);
				 if (getSPRect().intersects(player.getRect())){
					 player.x += 25;
				 }
			 }
		}
	}
}