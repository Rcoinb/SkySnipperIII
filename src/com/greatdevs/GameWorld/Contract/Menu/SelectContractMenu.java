package com.greatdevs.GameWorld.Contract.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import com.greatdevs.Game;
import com.greatdevs.InputHandler;
import com.greatdevs.GameWorld.ContractMode;
import com.greatdevs.GameWorld.SinglePlayer;
import com.greatdevs.GameWorld.Contract.Contract;
import com.greatdevs.Menu.Menu;
import com.greatdevs.Menu.SelectGameMode;

public class SelectContractMenu extends Menu{

	ContractMode cm;
	
	private double backgroundx;
	
	public int select, sy;
	public int yOffset, currectcontract;
	
	public ArrayList<Contract> contractarray = new ArrayList<Contract>();
	
	public SelectContractMenu(){
		
	}
	
	public void init(Game game, InputHandler input){
		this.game = game;
		this.input = input;
		//bosses - 1; stars - 2;
		contractarray.add(new Contract(new int[]{},  new int[]{0, 100}, new int[]{2, 2}, 0, 25, game));
		contractarray.add(new Contract(new int[]{1},  new int[]{0, 50}, new int[]{2, 2, 1}, 25, 125, game));
		contractarray.add(new Contract(new int[]{1},  new int[]{0, 75}, new int[]{2, 1, 2}, 50, 250, game));
		contractarray.add(new Contract(new int[]{1, 1},  new int[]{0, 50, 50}, new int[]{2, 2, 1, 2, 1}, 75, 375, game));
		contractarray.add(new Contract(new int[]{1, 2},  new int[]{0, 25, 50}, new int[]{2, 1, 2, 1, 2}, 100, 600, game));
		contractarray.add(new Contract(new int[]{1, 3},  new int[]{0, 50, 50}, new int[]{2, 1, 2, 1, 2}, 125, 875, game));
		contractarray.add(new Contract(new int[]{2, 2},  new int[]{0, 75, 50}, new int[]{2, 2, 1, 2, 1}, 150, 1150, game));
		contractarray.add(new Contract(new int[]{2, 3},  new int[]{0, 75, 75}, new int[]{2, 2, 1, 2, 1}, 160, 1260, game));
		contractarray.add(new Contract(new int[]{3, 3},  new int[]{0, 50, 50}, new int[]{2, 2, 1, 2, 1}, 170, 1670, game));
		contractarray.add(new Contract(new int[]{3, 3, 3},  new int[]{0, 25, 25, 25}, new int[]{2, 2, 1, 2, 1, 2, 1}, 200, 2200, game));
		contractarray.add(new Contract(new int[]{2, 2, 3, 3},  new int[]{0, 25, 25, 25, 25, 25}, new int[]{2, 2, 1, 2, 1, 2, 1, 2, 1, 2}, 250, 2750, game));
		contractarray.add(new Contract(new int[]{1, 2, 3, 3, 3},  new int[]{0, 10, 20, 30, 40, 50}, new int[]{2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2}, 300, 3800, game));
		contractarray.add(new Contract(new int[]{2, 2, 3, 3, 3},  new int[]{0, 20, 30, 40, 50, 60}, new int[]{2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1}, 350, 4350, game));
		contractarray.add(new Contract(new int[]{3, 3, 3, 3, 3},  new int[]{0, 50, 50, 50, 50, 50}, new int[]{2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1}, 400, 4900, game));
		contractarray.add(new Contract(new int[]{3, 3, 3, 3, 3},  new int[]{0, 50, 75, 50, 75, 50}, new int[]{2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2}, 500, 5500, game));
	}
	
	public void render(Graphics g){
		BackGroundrender(g);
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		g.setColor(new Color(0,0,0,225));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
		
		g.setColor(new Color(0,0,0,225));
 		g.fillRect(485, 0, 500, 100);
 		g.fillRect(650, 425, 350, 100);
 		
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.setColor(Color.WHITE);
		
		if (select <= -1){
			if (yOffset != 0){
			yOffset -= 50;
			}
			select = 0;
		}
		if (select >= 10){
			if (yOffset < (contractarray.size() * 50 - 500)){
			yOffset += 50;
			}
			select = 9;
		}
		
		contractarray.get(currectcontract).renderWay(g, 500, 50);
		
		g.drawString("Pay " + contractarray.get(currectcontract).pay, 675, 450);
		g.drawString("Get " + (contractarray.get(currectcontract).get - contractarray.get(currectcontract).pay) + " (if completed)", 675, 475);
		
		for (int i = 0; i < contractarray.size(); i ++){
			g.drawString("Contract #" + (i + 1), 25, i * 50 + 50 - yOffset);
		}
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		int titlew = (int) g.getFontMetrics().getStringBounds("Coins " + SinglePlayer.COINS, g).getWidth();
		g.drawString("Coins " + SinglePlayer.COINS, (((Game.WIDTH  * Game.SCALE) / 2) - (titlew / 2)), 470);
		
		if (SinglePlayer.COINS < contractarray.get(currectcontract).pay){
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 25));
			int title2w = (int) g.getFontMetrics().getStringBounds("No money", g).getWidth();
			g.drawString("No money", (((Game.WIDTH  * Game.SCALE) / 2) - (title2w / 2)), 440);
		}
		
		sy = select * 50 + 50;
		g.setColor(Color.WHITE);
		g.drawString(">                           <", 3, sy);
	}
	
	public void update(){
		backgroundx += 0.5;
		if (input.up.clicked){
			select --;
			currectcontract --;
		}
		if (input.down.clicked){
			select ++;
			currectcontract ++;
		}
		
		if (currectcontract <= 0){
			currectcontract = 0;
		}
		
		if (currectcontract >= contractarray.size() - 1){
			currectcontract = contractarray.size() - 1;
		}
		
		if (input.enter.clicked) {
			if (SinglePlayer.COINS >= contractarray.get(currectcontract).pay){
				SinglePlayer.COINS -= contractarray.get(currectcontract).pay;
				game.setMenu(null);
			    game.setGameMode(new ContractMode(contractarray.get(currectcontract), game));
			}
		}
		if (input.menu.clicked) game.setMenu(new SelectGameMode());
	}
	
	public void BackGroundrender(Graphics g){
		if (backgroundx > 1000){
			backgroundx = 0;
		}
		for (int h = 0; h < 2; h ++){
			for (int w = 0; w < 4; w ++){
		         g.drawImage(game.icons.background, 250 * w - (int) backgroundx, 250 * h, null);
			}
		}
		for (int h = 0; h < 2; h ++){
			for (int w = 0; w < 4; w ++){
		         g.drawImage(game.icons.background, 250 * w - (int) backgroundx + 1000, 250 * h, null);
			}
		}
	}
	
}
