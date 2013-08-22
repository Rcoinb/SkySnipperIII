package com.greatdevs.Entity.SuperPower;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.greatdevs.Game;
import com.greatdevs.Entity.Coin;
import com.greatdevs.Entity.Star;

public class P3Power extends SuperPower{
	
	public int timer;
	
	public Rectangle getRect(){
		return new Rectangle(player.x + player.width / 2 - 75, player.y + player.height / 2 - 375, 150, 750);
	}
	
	public void render(Graphics g){
		g.setColor(Color.BLUE);
		g.drawOval(getRect().x, getRect().y, getRect().width, getRect().height);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		int title5w = (int) g.getFontMetrics().getStringBounds(((100 - timer / 5) + "%"), g).getWidth();
		g.drawString(((100 - timer / 10) + "%"), (((Game.WIDTH  * Game.SCALE) / 2) - (title5w / 2)), 30);
	}
	
	public void update(Game game){
		timer ++;
		
		for (int i = 0; i < game.update.entity.stararray.size(); i ++){
			Star starclass = (Star) game.update.entity.stararray.get(i);
			if (getRect().intersects(starclass.getRect())){
				if (starclass.y < getRect().height / 2) starclass.y -= 15;
				if (starclass.y > getRect().height / 2) starclass.y += 15;
			}
		}
		
		for (int i = 0; i < game.update.entity.coinarray.size(); i ++){
			Coin coinclass = (Coin) game.update.entity.coinarray.get(i);
			if (getRect().intersects(coinclass.getRect())){
				game.update.gameworld.COINS ++;
				game.update.entity.coinarray.remove(i);
			}
		}
		
		if (timer >= 1000){
		player.stopsuperpower();
		}
	}
}
