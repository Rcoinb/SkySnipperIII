package com.greatdevs.GameWorld;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.greatdevs.Game;
import com.greatdevs.InputHandler;
import com.greatdevs.Entity.Player;
import com.greatdevs.Entity.ShopShip;
import com.greatdevs.GameWorld.Contract.Contract;
import com.greatdevs.Menu.PauseMenu;

public class ContractMode extends GameMode{
	
	public double backgroundx;
	public int shopshipspawntime = 0;
	public static final int CONTRACTMODE = 3;
	
	public Contract contract;
	
	public BufferedImage background;
	
	public ContractMode(Contract contract, Game game){
		this.contract = contract;
		contract.init();
	}
	
	public void init(Game game, InputHandler input){
		this.game = game;
		this.input = input;
		game.update.entity.removeAllArrays();
		objectspeed = 7;
		GAMEMODE = CONTRACTMODE;
		game.update.entity.playerarray.add(new Player(0, (((Game.HEIGHT * Game.SCALE) / 2)) - (game.update.entity.playerheight / 2)));
		background = game.icons.background;
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
	
	public void renderGUI(Graphics g){
		if (contract != null) contract.render(g);
		
		for (final Player player : game.update.entity.playerarray){
			if (player.superpower && !player.superpowerinuse){
					g.setColor(Color.YELLOW);
					g.setFont(new Font("Arial", Font.BOLD, 25));
					int title5w = (int) g.getFontMetrics().getStringBounds("Press x to use super power", g).getWidth();
					g.drawString("Press x to use super power", (((Game.WIDTH  * Game.SCALE) / 2) - (title5w / 2)), 30);
			}
		}
		
		g.setFont(new Font("Arial", Font.BOLD, 25));
		int strw2 = (int) g.getFontMetrics().getStringBounds("Coins " + SinglePlayer.COINS, g).getWidth();
		g.setColor(Color.WHITE);
		g.drawString("Coins " + SinglePlayer.COINS, (((Game.WIDTH  * Game.SCALE) - strw2) - 25), 490);
	}
	
	public void update(){
		backgroundx += 0.5;
		shopshipspawntime ++;
		game.update.entity.update(game);
		if (shopshipspawntime > 1000){
			game.update.entity.shopshiparray.add(new ShopShip(1000, new Random().nextInt((Game.HEIGHT * Game.SCALE)), objectspeed));
			shopshipspawntime = 0;
		}
		if (contract != null) contract.update(game);
        if (input.menu.clicked) game.setMenu(new PauseMenu());
	}
	
	public void speeddown(){
		
	}
}
