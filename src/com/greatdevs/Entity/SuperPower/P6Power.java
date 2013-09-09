package com.greatdevs.Entity.SuperPower;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.greatdevs.Game;
import com.greatdevs.Entity.Explosion;
import com.greatdevs.Entity.RenderPlus;
import com.greatdevs.Entity.Star;
import com.greatdevs.Sound.Sound;

public class P6Power extends SuperPower{
	
	public int timer;
	public double hpadded;
	
	BufferedImage image;
	
	public Rectangle getDrakulaRect(){
		return new Rectangle(player.x + 35, player.y + player.height / 2 - 10, 1000, 20);
	}
	
	public void render(Graphics g){
		g.drawImage(image, getDrakulaRect().x, getDrakulaRect().y, getDrakulaRect().width, getDrakulaRect().height, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		int title5w = (int) g.getFontMetrics().getStringBounds(((100 - timer / 5) + "%"), g).getWidth();
		g.drawString(((100 - timer / 5) + "%"), (((Game.WIDTH  * Game.SCALE) / 2) - (title5w / 2)), 30);
	}
	
	public void update(Game game){
		image = game.icons.p6sp;
		timer ++;
		for (int i = 0;i < game.update.entity.stararray.size(); i ++){
			Star star = (Star) game.update.entity.stararray.get(i);
			if (getDrakulaRect().intersects(star.getRect())){
		       game.update.entity.stararray.remove(i);
		       game.update.entity.explosionarray.add(new Explosion(star.x, star.y));
		       hpadded += 0.2;
		       if (hpadded >= 1){
		    	   player.hp += (int) hpadded;
				   game.update.entity.renderplusarray.add(new RenderPlus("+1 HP", player.x, player.y));
		    	   hpadded = 0;
		       }
		       player.printhp();
		       Sound.play("explosion.wav");
			}
		}
		if (timer > 500){
		player.stopsuperpower();
		}
	}
}
