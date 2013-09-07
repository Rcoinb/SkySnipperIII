package com.greatdevs.Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import com.greatdevs.Game;
import com.greatdevs.Entity.Boss.*;
import com.greatdevs.GameWorld.SinglePlayer;
import com.greatdevs.Menu.LoseMenu;
import com.greatdevs.Menu.ShopShipMenu;
import com.greatdevs.Sound.Sound;

public class Entity {
	public ArrayList<Player> playerarray = new ArrayList<Player>();
	public ArrayList<Bonus> bonusarray = new ArrayList<Bonus>();
	public ArrayList<Star> stararray = new ArrayList<Star>();
	public ArrayList<Bullet> bulletarray = new ArrayList<Bullet>();
	public ArrayList<Coin> coinarray = new ArrayList<Coin>();
	public ArrayList<Explosion> explosionarray = new ArrayList<Explosion>();
	public ArrayList<Label> labelarray = new ArrayList<Label>();
	public ArrayList<ShopShip> shopshiparray = new ArrayList<ShopShip>();
	public ArrayList<Boss> bossarray = new ArrayList<Boss>();
	public ArrayList<Particle> particlearray = new ArrayList<Particle>();
	
	public int playerheight;
	
	public Entity(){

	}
	
	public void render(Graphics g){
		for(final Bonus bonus : bonusarray){
			bonus.render(g);
		}
		for(final Star star : stararray){
			star.render(g);
		}
		for(final Bullet bullet : bulletarray){
			bullet.render(g);
		}
		for(final Coin coin : coinarray){
			coin.render(g);
		}
		for(final Explosion explosion : explosionarray){
			explosion.render(g);
		}
		for(final ShopShip shopship : shopshiparray){
			shopship.render(g);
		}
		for(final Boss boss : bossarray){
			boss.render(g);
		}
		for(final Particle particle : particlearray){
			particle.render(g);
		}
		
		//keep it here!
		for(final Player player : playerarray){
			player.render(g);
		}
		for(final Label label : labelarray){
			label.render(g);
		}
	}
	
	public void update(Game game){
		for(int i = 0; i < playerarray.size(); i ++){
			Player player = (Player) playerarray.get(i);
			player.update(game);
			playerheight = player.height;
			if (player.hp <= 0){
				Sound.play("explosion.wav");
				playerarray.remove(i);
				game.setMenu(new LoseMenu());
			}
		}
		for(int i = 0; i < bonusarray.size(); i ++){
			Bonus bonus = (Bonus) bonusarray.get(i);
			bonus.update(game);
			if (bonus.x < 0 - bonus.getRect().width) bonusarray.remove(i);
		}
		for(int i = 0; i < stararray.size(); i ++){
			Star star = (Star) stararray.get(i);
			star.update(game);
			if (star.x < 0 - star.getRect().width) stararray.remove(i);
		}
		for (int i = 0; i < bulletarray.size(); i ++){
			Bullet bullet = (Bullet) bulletarray.get(i);
			bullet.update(game);
			if (bullet.x > (Game.WIDTH * Game.SCALE)) bulletarray.remove(i);
		}
		for (int i = 0; i < coinarray.size(); i ++){
			Coin coin = (Coin) coinarray.get(i);
			coin.update(game);
			if (coin.x < 0 - coin.getRect().width) coinarray.remove(i);
		}
		for (int i = 0; i < explosionarray.size(); i ++){
			Explosion explosion = (Explosion) explosionarray.get(i);
			explosion.update(game);
			if (explosion.explosionfinished) explosionarray.remove(i);
		}
		for (int i = 0; i < labelarray.size(); i ++){
			Label label = (Label) labelarray.get(i);
			label.update(game);
			if (label.finished) labelarray.remove(i);
		}
		for (int i = 0; i < shopshiparray.size(); i ++){
			ShopShip shopship = (ShopShip) shopshiparray.get(i);
			shopship.update(game);
			if (shopship.x < 0 - shopship.getRect().width) shopshiparray.remove(i);
		}
		for (int i = 0; i < bossarray.size(); i ++){
			Boss boss = (Boss) bossarray.get(i);
			boss.update(game);
		}
		for (int i = 0; i < particlearray.size(); i ++){
			Particle particle = (Particle) particlearray.get(i);
			particle.update(game);
			if (particle.removed) particlearray.remove(i);
		}
		if (!Game.MENU) collision(game);
	}
	
	public void collision(Game game){
		for(int i = 0; i < stararray.size(); i ++){
			Star star = (Star) stararray.get(i);
			for(int w = 0;w < playerarray.size(); w ++){
				Player player = (Player) playerarray.get(w);
				if (player.getRect().intersects(star.getRect())){
					explosionarray.add(new Explosion(star.x, star.y));
					player.hp --;
					player.printhp();
					stararray.remove(i);
					Sound.play("explosion.wav");
				}
				if (player.shield){
					if (player.getShieldRect().intersects(star.getRect())){
						explosionarray.add(new Explosion(star.x, star.y));
						stararray.remove(i);
						Sound.play("explosion.wav");
					}
				}
			}
		}
		for (int i = 0; i < bulletarray.size(); i ++){
			Bullet bullet = (Bullet) bulletarray.get(i);
			for(int w = 0; w < stararray.size(); w ++){
				Star star = (Star) stararray.get(w);
				if (bullet.getRect().intersects(star.getRect())){
					explosionarray.add(new Explosion(star.x, star.y));
					if (i < bulletarray.size()){
						if (new Random().nextInt(4) == 0){
							coinarray.add(new Coin(star.x, star.y, game.gamemode.objectspeed));
							game.update.entity.particlearray.add(new Particle(Color.YELLOW, 35, 25, star.x, star.y, 2));
						}
						bulletarray.remove(i);
					}
					if (stararray.get(w) != null) stararray.remove(w);
					SinglePlayer.SCORE += 25;
					Sound.play("explosion.wav");
				}
			}
		}
		for (int i = 0; i < bonusarray.size(); i ++){
			Bonus bonus = (Bonus) bonusarray.get(i);
			for(int w = 0; w < playerarray.size(); w ++){
				Player player = (Player) playerarray.get(w);
				if (player.getRect().intersects(bonus.getRect())){
					game.update.entity.particlearray.add(new Particle(Color.GREEN, 25, 25, bonus.x, bonus.y, 2));
					bonusarray.remove(i);
					Sound.play("coin.wav");
					if (bonus.type == 1){
						hpbonus();
						labelarray.add(new Label("+2 HP"));
					}
					if (bonus.type == 2){
						game.gamemode.speeddown();
						labelarray.add(new Label("Speed down"));
					}
					if (bonus.type == 3){
						shieldbonus();
						labelarray.add(new Label("Shield bonus"));
					}
					if (bonus.type == 4){
						magnetbonus();
						labelarray.add(new Label("Magnet bonus"));
					}
					SinglePlayer.SCORE += 15;
				}
			}
		}
		for (int i = 0; i < coinarray.size(); i ++){
			Coin coin = (Coin) coinarray.get(i);
			for(int w = 0; w < playerarray.size(); w ++){
				Player player = (Player) playerarray.get(w);
				if (player.getRect().intersects(coin.getRect())){
					game.update.entity.particlearray.add(new Particle(Color.YELLOW, 25, 25, coin.x + 16, coin.y + 16, 2));
					coinarray.remove(i);
					SinglePlayer.COINS ++;
					SinglePlayer.SCORE += 5;
					Sound.play("coin.wav");
				}
				if (player.magnet){
					if (player.getMagnetRect().intersects(coin.getRect())){
						game.update.entity.particlearray.add(new Particle(Color.YELLOW, 25, 25, coin.x + 16, coin.y + 16, 2));
						coinarray.remove(i);
						Sound.play("coin.wav");
						SinglePlayer.COINS ++;
					}
				}
			}
		}
		for (int i = 0; i < shopshiparray.size(); i ++){
			ShopShip shopship = (ShopShip) shopshiparray.get(i);
			for(int w = 0; w < playerarray.size(); w ++){
				Player player = (Player) playerarray.get(w);
				if (player.getRect().intersects(shopship.getRect())){
					shopshiparray.remove(i);
					game.setMenu(new ShopShipMenu());
					SinglePlayer.SCORE += 10;
					Sound.play("coin.wav");
				}
			}
		}
	}
	
	public void hpbonus(){
		for(final Player player : playerarray){
			player.hp += 2;
			player.printhp();
		}
	}
	
	public void shieldbonus(){
		for(final Player player : playerarray){
			player.shieldbonus();
		}
	}
	
	public void magnetbonus(){
		for(final Player player : playerarray){
			player.magnetbonus();
		}
	}
	
	public void removeAllArrays(){
		playerarray.removeAll(playerarray);
		bonusarray.removeAll(bonusarray);
		stararray.removeAll(stararray);
		bulletarray.removeAll(bulletarray);
		coinarray.removeAll(coinarray);
		explosionarray.removeAll(explosionarray);
		labelarray.removeAll(labelarray);
		shopshiparray.removeAll(shopshiparray);
		bossarray.removeAll(bossarray);
		particlearray.removeAll(particlearray);
	}
}
