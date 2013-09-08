package com.greatdevs.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.greatdevs.Game;
import com.greatdevs.Verify;

public class MainMenu extends Menu{
	
	private int select = 1;
	private int sy = 0;
	private double backgroundx = 0;
	
	RenderNews rendernews = new RenderNews();
	
	public MainMenu(){
		
	}
	
	public void render(Graphics g){
		BackGroundrender(g);
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		/*g.setColor(new Color(0,0,0,225));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);*/
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 100));
		int titlew = (int) g.getFontMetrics().getStringBounds("Sky Snipper III", g).getWidth();
		g.drawString("Sky Snipper III", (((Game.WIDTH  * Game.SCALE) / 2) - (titlew / 2)), 150);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.drawString("Play", 25, 250);
		g.drawString("Shop", 25, 300);
		g.drawString("Stats", 25, 350);
		g.drawString("Options", 25, 400);
		g.drawString("Exit", 25, 450);	
		g.drawString(">                 <", 3, sy);
		
		rendernews.render(g);
		
		if (select >= 5) select = 5;
		if (select <= 1) select = 1;
		sy = select * 50 + 200;
		
		if (!Verify.isJava7()){
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 25));
			int title2w = (int) g.getFontMetrics().getStringBounds("Warning: Your java version is not 1.7", g).getWidth();
			g.drawString("Warning: Your java version is not 1.7", (((Game.WIDTH  * Game.SCALE) / 2) - (title2w / 2)), 30);
		}
	}
	
	public void update(){
		backgroundx += 0.5;
		rendernews.update();
		if (input.up.clicked) select --;
		if (input.down.clicked) select ++;
		if (input.enter.clicked && select == 1) {
			game.setMenu(new SelectGameMode());
		}
		if (input.enter.clicked && select == 2) game.setMenu(new ShopMenu());
		if (input.enter.clicked && select == 3) game.setMenu(new StatsMenu());
		if (input.enter.clicked && select == 4) game.setMenu(new OptionsMenu());
		if (input.enter.clicked && select == 5) System.exit(0);
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
