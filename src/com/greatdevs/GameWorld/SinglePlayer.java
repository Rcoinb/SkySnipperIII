package com.greatdevs.GameWorld;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.greatdevs.Game;
import com.greatdevs.InputHandler;
import com.greatdevs.Entity.*;
import com.greatdevs.Entity.Boss.*;
import com.greatdevs.Menu.PauseMenu;

public class SinglePlayer extends GameMode{
	
	public int gametime = 0, spawntime = 0, lowspawntime = 0, maxspawntime = 35, bonusspawntime = 0,
			maxbonusspawntime = 750, coinspawntime = 0, maxcoinspawntime = 35, speeddowntime = 0, speedcash = objectspeed,
			shopshipspawntime = 0, bossspawntime = 0;
	public double backgroundx;
	
	public boolean speeddown = false;
	public static final int SINGLEPLAYER = 1;
	public static int SCORE = 0, BESTSCORE, COINS;
	public BufferedImage background;
	
	public SinglePlayer(){
		SCORE = 0;
		objectspeed = 3;
	}
	
	public void init(Game game, InputHandler input){
		this.game = game;
		this.input = input;
		game.update.entity.removeAllArrays();
		game.update.entity.playerarray.add(new Player(0, (((Game.HEIGHT * Game.SCALE) / 2)) - (game.update.entity.playerheight / 2)));
		GAMEMODE = SINGLEPLAYER;
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
		game.update.entity.render(g);
	}
	
	public void update(){
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
            addBoss((new Random().nextInt(3) + 1), game);
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
		game.update.entity.update(game);
        if (input.menu.clicked) game.setMenu(new PauseMenu());
	}
	
	public void addBoss(int type, Game game){
		if (type == 1) game.update.entity.bossarray.add(new Boss1());
		if (type == 2) game.update.entity.bossarray.add(new Boss2());
		if (type == 3) game.update.entity.bossarray.add(new Boss3());
	}
	
	public void speeddown(){
		speedcash = objectspeed;
		speeddown = true;
		speeddowntime = 0;
	}
}