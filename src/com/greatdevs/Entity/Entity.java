package com.greatdevs.Entity;

import java.awt.Graphics;
import java.util.ArrayList;
import com.greatdevs.Game;
import com.greatdevs.Entity.Boss.*;
import com.greatdevs.Menu.ShopShipMenu;

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
				playerarray.remove(i);
				game.update.gameworld.lose(game);
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
		collision(game);
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
				}
				if (player.shield){
					if (player.getShieldRect().intersects(star.getRect())){
						explosionarray.add(new Explosion(star.x, star.y));
						stararray.remove(i);
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
					stararray.remove(w);
					bulletarray.remove(i);
					game.update.gameworld.SCORE += 25;
				}
			}
		}
		for (int i = 0; i < bonusarray.size(); i ++){
			Bonus bonus = (Bonus) bonusarray.get(i);
			for(int w = 0; w < playerarray.size(); w ++){
				Player player = (Player) playerarray.get(w);
				if (player.getRect().intersects(bonus.getRect())){
					bonusarray.remove(i);
					if (bonus.type == 1){
						hpbonus();
						labelarray.add(new Label("+2 HP"));
					}
					if (bonus.type == 2){
						game.update.gameworld.speeddown();
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
					game.update.gameworld.SCORE += 15;
				}
			}
		}
		for (int i = 0; i < coinarray.size(); i ++){
			Coin coin = (Coin) coinarray.get(i);
			for(int w = 0; w < playerarray.size(); w ++){
				Player player = (Player) playerarray.get(w);
				if (player.getRect().intersects(coin.getRect())){
					coinarray.remove(i);
					game.update.gameworld.COINS ++;
					game.update.gameworld.SCORE += 5;
				}
				if (player.magnet){
					if (player.getMagnetRect().intersects(coin.getRect())){
						coinarray.remove(i);
						game.update.gameworld.COINS ++;
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
					game.update.gameworld.SCORE += 10;
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
	}
}
