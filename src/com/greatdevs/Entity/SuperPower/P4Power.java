package com.greatdevs.Entity.SuperPower;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.greatdevs.Game;
import com.greatdevs.Entity.Explosion;
import com.greatdevs.Entity.Star;

public class P4Power extends SuperPower{
	
	public int timer;
	
	public Rectangle rect(){
		return new Rectangle(0, 0, 950, 500);
	}
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		int title5w = (int) g.getFontMetrics().getStringBounds(((100 - timer / 5) + "%"), g).getWidth();
		g.drawString(((100 - timer / 5) + "%"), (((Game.WIDTH  * Game.SCALE) / 2) - (title5w / 2)), 30);
	}
	
	public void update(Game game){	
		timer ++;
		
		for (int i = 0;i < game.update.entity.stararray.size(); i ++){
			Star star = (Star) game.update.entity.stararray.get(i);
			if (rect().intersects(star.getRect())){
				game.update.entity.explosionarray.add(new Explosion(star.x, star.y));
				game.update.entity.stararray.remove(i);
			}
		}
		
		if (timer > 500){
		player.stopsuperpower();
		}
	}
}
