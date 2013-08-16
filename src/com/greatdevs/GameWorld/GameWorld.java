package com.greatdevs.GameWorld;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import com.greatdevs.Game;
import com.greatdevs.Entity.*;
import com.greatdevs.Entity.Boss.*;
import com.greatdevs.Menu.LoseMenu;

public class GameWorld {
	
	public int gametime = 0, spawntime = 0, lowspawntime = 0, maxspawntime = 35, objectspeed = 3, bonusspawntime,
			maxbonusspawntime = 750, coinspawntime, maxcoinspawntime = 35, speeddowntime = 0, speedcash,
			shopshipspawntime = 0, bossspawntime;
	public double backgroundx;
	
	public boolean speeddown = false;
	
	public int SCORE, BESTSCORE, COINS;
	public BufferedImage background;
	
	public GameWorld(){
		
	}
	
	public void render(Graphics g){
		if (backgroundx > 1000){
			backgroundx = 0;
		}
		for (int h = 0; h < 2; h ++){
			for (int w = 0; w < 4; w ++){
		         g.drawImage(background, 250 * w - (int) backgroundx, 250 * h, null);
			}
		}
		for (int h = 0; h < 2; h ++){
			for (int w = 0; w < 4; w ++){
		         g.drawImage(background, 250 * w - (int) backgroundx + 1000, 250 * h, null);
			}
		}
	}
	
	public void update(Game game){
		backgroundx += 0.5;
		gametime ++;
		spawntime ++;
		lowspawntime ++;
		bonusspawntime ++;
		coinspawntime ++;
		shopshipspawntime ++;
		if (game.update.entity.bossarray.size() <= 0){
			bossspawntime ++;
		}
		if (bossspawntime >= 3500){
            addBoss((new Random().nextInt(1) + 1), game);
			bossspawntime = 0;
		}
		if (spawntime > maxspawntime){
			for (int i = 0;i < (new Random().nextInt(5) + 1); i ++){
		         game.update.entity.stararray.add(new Star(1000, new Random().nextInt((Game.HEIGHT * Game.SCALE)), objectspeed));
			}
		spawntime = 0;
		}
		if (lowspawntime > 1000) {
			SCORE += 10;
			if (maxspawntime > 20){
			maxspawntime--; 
			if (objectspeed < 10) objectspeed++;
			lowspawntime = 0;
			}
		}
		if (shopshipspawntime > 1000){
			game.update.entity.shopshiparray.add(new ShopShip(1000, new Random().nextInt((Game.HEIGHT * Game.SCALE)), objectspeed));
			shopshipspawntime = 0;
		}
		if (bonusspawntime > maxbonusspawntime){
			game.update.entity.bonusarray.add(new Bonus(1000, new Random().nextInt((Game.HEIGHT * Game.SCALE)), objectspeed));
			bonusspawntime = 0;
		}
		if (coinspawntime > maxcoinspawntime){
			game.update.entity.coinarray.add(new Coin(1000, new Random().nextInt((Game.HEIGHT * Game.SCALE)), objectspeed));
			coinspawntime = 0;
		}
		if (speeddown){
			speeddowntime ++;
			objectspeed = 1;
			if (speeddowntime > 500){
				speeddowntime = 0;
				speeddown = false;
				objectspeed = speedcash;
			}
		}
		background = game.icons.background;
	}
	
	public void addBoss(int type, Game game){
		if (type == 1) game.update.entity.bossarray.add(new Boss1());
	}
	
	public void restartGame(Game game){
		game.update.entity.removeAllArrays();
		gametime = 0; spawntime = 0; lowspawntime = 0; maxspawntime = 35; objectspeed = 3; backgroundx = 0;
		SCORE = 0; bonusspawntime = 0; maxbonusspawntime = 750; coinspawntime = 0; maxcoinspawntime = 35;
		speeddown = false; speeddowntime = 0; speedcash = objectspeed; shopshipspawntime = 0; bossspawntime = 0;
		game.setMenu(null);
		game.update.entity.playerarray.add(new Player(0, (((Game.HEIGHT * Game.SCALE) / 2)) - (game.update.entity.playerheight / 2)));
	
		game.update.entity.bossarray.add(new Boss2());
	}
	
	public void lose(Game game){
		game.setMenu(new LoseMenu());
	}
	
	public void speeddown(){
		speedcash = objectspeed;
		speeddown = true;
		speeddowntime = 0;
	}
}