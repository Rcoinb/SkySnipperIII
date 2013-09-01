package com.greatdevs.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import com.greatdevs.Game;
import com.greatdevs.Entity.Player;
import com.greatdevs.Entity.Boss.Boss;

public class ShopShipMenu extends Menu{
	private int select = 1;
	private int sy = 400;
	private int hpadded = 0;
	public int u1p = 25, u2p = 50, u3p = 75, u4p = 65, u5p = 100, u6p = 250;
	
	public ShopShipMenu(){
		
	}
	
	public void render(Graphics g){
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		g.setColor(new Color(0,0,0,225));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.setColor(Color.WHITE);
		g.drawString("Shop ship", 25, 50);
		
		if (u1p != 0) g.setColor(Color.WHITE);
		if (u1p == 0) g.setColor(Color.GRAY);
		g.drawString("HP bonus " + u1p, 25, 150);
		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("You've already bought " + hpadded + " HP", 27, 165);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		if (u2p != 0) g.setColor(Color.WHITE);
		if (u2p == 0) g.setColor(Color.GRAY);
		g.drawString("Speed down " + u2p, 25, 200);	
		if (u3p != 0) g.setColor(Color.WHITE);
		if (u3p == 0) g.setColor(Color.GRAY);
		g.drawString("Shield " + u3p, 25, 250);
		if (u4p != 0) g.setColor(Color.WHITE);
		if (u4p == 0) g.setColor(Color.GRAY);
		g.drawString("Magnet " + u4p, 25, 300);
		if (u5p != 0) g.setColor(Color.WHITE);
		if (u5p == 0) g.setColor(Color.GRAY);
		g.drawString("Super power " + u5p, 25, 350);
		if (u6p != 0) g.setColor(Color.WHITE);
		if (u6p == 0) g.setColor(Color.GRAY);
		g.drawString("Kill boss " + u6p, 25, 400);	
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		int titlew = (int) g.getFontMetrics().getStringBounds("Coins " + game.update.gameworld.COINS, g).getWidth();
		g.drawString("Coins " + game.update.gameworld.COINS, (((Game.WIDTH  * Game.SCALE) / 2) - (titlew / 2)), 470);
		
		if (game.update.gameworld.COINS < getprice()){
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 25));
			int title2w = (int) g.getFontMetrics().getStringBounds("No money", g).getWidth();
			g.drawString("No money", (((Game.WIDTH  * Game.SCALE) / 2) - (title2w / 2)), 30);
		}
		
		g.setColor(Color.WHITE);
		g.drawString("Exit", 25, 450);	
		g.drawString(">                              <", 3, sy);
		if (select >= 7) select = 7;
		if (select <= 1) select = 1;
		sy = select * 50 + 100;
	}
	
	public int getprice(){
		if (select == 1) return u1p;
		if (select == 2) return u2p;
		if (select == 3) return u3p;
		if (select == 4) return u4p;
		if (select == 5) return u5p;
		if (select == 6) return u6p;
		return 0;
	}
	
	public void buy(){
		if (input.enter.clicked && select == 1 && game.update.gameworld.COINS >= u1p && u1p != 0){
			game.update.gameworld.COINS -= u1p;
			game.update.entity.hpbonus();
			hpadded += 2;
		}
		
		if (input.enter.clicked && select == 2 && game.update.gameworld.COINS >= u2p && u2p != 0){ 
			game.update.gameworld.COINS -= u2p;
			game.update.gameworld.speeddown();	
			u2p = 0;
		}
		
		if (input.enter.clicked && select == 3 && game.update.gameworld.COINS >= u3p && u3p != 0){ 
			game.update.gameworld.COINS -= u3p;
			game.update.entity.shieldbonus();
			u3p = 0;
		}
		
		if (input.enter.clicked && select == 4 && game.update.gameworld.COINS >= u4p && u4p != 0){ 
			game.update.gameworld.COINS -= u4p;
			game.update.entity.magnetbonus();
			u4p = 0;
		}
		
		if (input.enter.clicked && select == 5 && game.update.gameworld.COINS >= u5p && u5p != 0){ 
			game.update.gameworld.COINS -= u5p;
			for (final Player player : game.update.entity.playerarray){
				player.superpower = true;
				player.superpowertime = 0;
			}
			u5p = 0;
		}
		
		if (input.enter.clicked && select == 6 && game.update.gameworld.COINS >= u6p && u6p != 0){ 
			game.update.gameworld.COINS -= u6p;
			for (int i = 0; i < game.update.entity.bossarray.size(); i ++){
				Boss boss = (Boss) game.update.entity.bossarray.get(i);
				boss.die(game);
			}
			u6p = 0;
		}
	}
	
	public void update(){
		for (final Player player : game.update.entity.playerarray){
			if (player.superpower || player.superpowerinuse){
				u5p = 0;
			}
			else if (!player.superpower && !player.superpowerinuse){
				u5p = 100;
			}
		}
		
			if (game.update.entity.bossarray.size() <= 0){
				u6p = 0;
			}
			else if (game.update.entity.bossarray.size() > 0){
				u6p = 250;
			}
		
		if (input.up.clicked) select--;
		if (input.down.clicked) select++;
		buy();
		if (input.enter.clicked && select == 7) game.setMenu(null);
		if (input.menu.clicked) game.setMenu(null);
	}
}
