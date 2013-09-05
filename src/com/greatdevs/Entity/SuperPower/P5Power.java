package com.greatdevs.Entity.SuperPower;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.greatdevs.Game;
import com.greatdevs.Entity.*;
import com.greatdevs.Sound.Sound;

public class P5Power extends SuperPower{
	public int width, height, timer;
	
	public Rectangle rect(){
		return new Rectangle(player.x + player.width / 2 - width / 2, player.y + player.height / 2 - height / 2, width, height);
	}
	
	public void render(Graphics g){
		g.setColor(Color.YELLOW);
		g.drawOval(rect().x, rect().y, rect().width, rect().height);
	}
	
	public void update(Game game){
		timer ++;
		width += timer;
		height += timer;
		if (timer > 200){
			player.stopsuperpower();	
		}
		for (int i = 0;i < game.update.entity.stararray.size(); i ++){
			Star star = (Star) game.update.entity.stararray.get(i);
			if (rect().intersects(star.getRect())){
				game.update.entity.coinarray.add(new Coin(star.x, star.y, game.gamemode.objectspeed));
				game.update.entity.stararray.remove(i);
				Sound.play("coin.wav");
			}
		}
	}
}
