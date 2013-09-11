package com.greatdevs.GameWorld.Contract;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import com.greatdevs.Game;
import com.greatdevs.Entity.Star;
import com.greatdevs.Entity.Boss.Boss1;
import com.greatdevs.Entity.Boss.Boss2;
import com.greatdevs.Entity.Boss.Boss3;
import com.greatdevs.GameWorld.SinglePlayer;
import com.greatdevs.GameWorld.Contract.Menu.WinContractMenu;

public class Contract {
	public int currectwaypoint = 0, currectbosstype = 0, currectstartype = 0, timer = 0, starspawned = 0;
	public boolean doing = false, doingBoss = false, doingStar = false;
	public int pay, get;
	public int[] bosstypes;
	public int[] stars;
	public int[] way;
	
	Game game;
	
	public Contract(int[] bosstypes, int[] stars, int[] way, int pay, int get, Game game){
		this.stars = stars;
		this.bosstypes = bosstypes;
		this.way = way;
		this.pay = pay;
		this.get = get;
		this.game = game;
	}
	
	public void init(){
		updateWay(game);
	}
	
	public void render(Graphics g){

	}
	
	public void renderWay(Graphics g, int x, int y){
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		
		for (int i = 0; i < bosstypes.length; i ++){
			//g.drawString("" + bosstypes[i], x + 150 + i * 25, y);
			g.drawImage(game.icons.boss[bosstypes[i] - 1], x + i * 75, y - 25, 25, 25, null);
			g.drawString("X1" , x + 25 + i * 75, y - 2);
		}
		
		for (int i = 0; i < stars.length; i ++){
			if (stars[i] != 0){
			g.drawImage(game.icons.star[0], x - 85 + i * 85, y, 25, 25, null);
			g.drawString("X" + stars[i], x - 60 + i * 85, y + 22);
			}
		}
	}
	
	public void updateWay(Game game){
		if (currectwaypoint >= way.length){
			game.setGameMode(null);
			SinglePlayer.COINS += get;
			game.setMenu(new WinContractMenu(get));
			return;
		}
		if (!doing && way[currectwaypoint] == 1){
			spawnBoss(bosstypes[currectbosstype], game);
		}
		else if (!doing && way[currectwaypoint] == 2){
			spawnStar();
		}
	}
	
	public void update(Game game){
		if (doingBoss){
			if (game.update.entity.bossarray.size() == 0){
				doing = false;
				doingBoss = false;
				if (currectbosstype < bosstypes.length) currectbosstype ++;
				currectwaypoint ++;
				updateWay(game);
			}
		}
		
		if (doingStar){
			timer ++;
			if (timer >= 15){
				game.update.entity.stararray.add(new Star(1000, new Random().nextInt((Game.HEIGHT * Game.SCALE)), game.gamemode.objectspeed));
				timer = 0;
				starspawned ++;
			}
			if (starspawned >= stars[currectstartype]){
				doingStar = false;
				doing = false;
				timer = 0;
				starspawned = 0;
				currectwaypoint ++;
				if (currectstartype < stars.length) currectstartype ++;
				updateWay(game);
			}
		}
	}
	
	public void spawnBoss(int type, Game game){
		if (type == 1) game.update.entity.bossarray.add(new Boss1());
		if (type == 2) game.update.entity.bossarray.add(new Boss2());
		if (type == 3) game.update.entity.bossarray.add(new Boss3());
		doing = true;
		doingBoss = true;
	}
	
	public void spawnStar(){
		doing = true;
		doingStar = true;
	}
}
