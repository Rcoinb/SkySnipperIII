package com.greatdevs.GameWorld.Multiplayer.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.greatdevs.Game;
import com.greatdevs.GameWorld.MultiPlayer;
import com.greatdevs.Menu.MainMenu;
import com.greatdevs.Menu.Menu;

public class ErrorMenu extends Menu{
	public String ip = "", port = "";
	public int select = 1;
	public int sy;
	
	MultiPlayer mp;
	
	public ErrorMenu(){

	}
	
	public void render(Graphics g){
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE), Game.HEIGHT * Game.SCALE);
		g.setColor(new Color(0,0,0,225));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 3, Game.HEIGHT * Game.SCALE);
		if (select >= 1) select = 1;
		if (select <= 1) select = 1;
		sy = select * 50 + 400;
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.drawString("Exit", 25, 450);
		g.drawString(">                                        <", 3, sy);
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		int title2w = (int) g.getFontMetrics().getStringBounds("Oops.. Something went wrong..", g).getWidth();
		g.drawString("Oops.. Something went wrong..", (((Game.WIDTH  * Game.SCALE) / 2) - (title2w / 2)), 30);
	}

	public void update(){
		if (input.enter.clicked){
			game.setGameMode(null);
			game.setMenu(new MainMenu());
		}
	}
}
