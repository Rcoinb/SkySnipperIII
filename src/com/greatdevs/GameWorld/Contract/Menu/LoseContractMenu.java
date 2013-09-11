package com.greatdevs.GameWorld.Contract.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.greatdevs.Game;
import com.greatdevs.GameWorld.SinglePlayer;
import com.greatdevs.Menu.MainMenu;
import com.greatdevs.Menu.Menu;

public class LoseContractMenu extends Menu{
	public double backgroundx = 0;
	
	public LoseContractMenu(){
		
	}
	
	public void render(Graphics g){
		BackGroundrender(g);
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		g.setColor(new Color(0,0,0,225));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.setColor(Color.WHITE);
		g.drawString("Contract failed", 25, 50);
		g.drawString("Exit", 25, 435);	
		g.drawString(">                 <", 3, 435);
	}
	
	public void update(){
		backgroundx += 0.5;
		if (input.enter.clicked){
			game.setMenu(new MainMenu());
			SinglePlayer.SCORE = 0;
		}
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
