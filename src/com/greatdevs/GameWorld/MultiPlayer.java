package com.greatdevs.GameWorld;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.greatdevs.Game;
import com.greatdevs.InputHandler;
import com.greatdevs.Entity.ShipTypes;
import com.greatdevs.GameWorld.Multiplayer.EntityMP;
import com.greatdevs.GameWorld.Multiplayer.PlayerMP;
import com.greatdevs.GameWorld.Multiplayer.StarMP;
import com.greatdevs.GameWorld.Multiplayer.Menu.AskMenuToRunServer;
import com.greatdevs.GameWorld.Multiplayer.Server.ServerWork;
import com.greatdevs.Menu.PauseMenu;

public class MultiPlayer extends GameMode{

	
	public int starspawntime, maxstarspawntime = 35;
	public EntityMP entity = new EntityMP(this);
	
	public static final int MULTIPLAYER = 2;
	
	public boolean spawnstar = false;
	public double backgroundx;
	
	public int stary, size;
	
	public boolean SERVER = false;
	
	public static int SCORE;
	public static int BESTSCORE;
	
	public BufferedImage background;
	
	public PlayerMP opponent;
	public PlayerMP thisplayer;
	
	public ServerWork serverwork;
	
	public MultiPlayer(){
		objectspeed = 3;
	}
	
	public void init(Game game, InputHandler input){
		this.game = game;
		this.input = input;
		GAMEMODE = MULTIPLAYER;
		SCORE = 0;
		game.setMenu(new AskMenuToRunServer(this));
		serverwork = new ServerWork();
		thisplayer = new PlayerMP(this, ShipTypes.type1, input,  0, 0);
		opponent = new PlayerMP(this, ShipTypes.type1, null,  0, 0);
		background = game.icons.background;
	}
	
	public void runServer(String ip, int port){
		SERVER = true;
		serverwork.runServer(this, ip, port);
	}
	
	public void Connect(String ip, int port){
		SERVER = false;
		serverwork.Connect(this, ip, port);
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
		entity.render(g);
		opponent.render(g);
		thisplayer.render(g);
	}
	
	public void update(){
		backgroundx += 0.5;
		
		if (SERVER){
		starspawntime++;
		
		if (starspawntime > maxstarspawntime){
			System.out.println("SPAWN");
			for (int i = 0;i < (new Random().nextInt(5) + 1); i ++){
				spawnstar = true;
				stary =  new Random().nextInt((Game.HEIGHT * Game.SCALE));
				size =  new Random().nextInt(3);
		        entity.stararray.add(new StarMP(this, 1000, stary, objectspeed, size));
			}
			starspawntime = 0;
			spawnstar = false;
		}
		}
		
		entity.update(game);
		opponent.update(game);
		thisplayer.update(game);
        if (input.menu.clicked) game.setMenu(new PauseMenu());
	}
	
}
