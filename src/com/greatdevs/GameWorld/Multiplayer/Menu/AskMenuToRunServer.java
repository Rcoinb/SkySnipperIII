package com.greatdevs.GameWorld.Multiplayer.Menu;

import java.awt.Color;
import java.awt.Graphics;

import com.greatdevs.Game;
import com.greatdevs.GameWorld.MultiPlayer;
import com.greatdevs.Menu.MainMenu;
import com.greatdevs.Menu.Menu;

public class AskMenuToRunServer extends Menu{
	private int select = 1;
	private int sy = 400;
	
	MultiPlayer mp;
	
	public AskMenuToRunServer(MultiPlayer mp){
		this.mp = mp;
	}
	
	public void render(Graphics g){
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		g.setColor(new Color(0,0,0,225));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
		g.setColor(Color.WHITE);
		g.drawString("Run server ?", 25, 50);
		if (select >= 3) select = 3;
		if (select <= 1) select = 1;
		g.drawString("Yes", 25, 350);	
		g.drawString("No", 25, 400);	
		g.drawString("Exit", 25, 450);
		g.drawString(">                    <", 3, sy);
		sy = select * 50 + 300;
	}
	
	public void update(){
		if (input.up.clicked) select --;
		if (input.down.clicked) select ++;
		
		if (input.enter.clicked && select == 1){
			game.setMenu(new CreateServer(mp));
		}
		if (input.enter.clicked && select == 2){
			game.setMenu(new ConnectMenu(mp));
		}
		if (input.enter.clicked && select == 3){
			game.setMenu(new MainMenu());
		}
	}
}
