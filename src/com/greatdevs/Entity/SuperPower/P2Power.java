package com.greatdevs.Entity.SuperPower;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.greatdevs.Game;
import com.greatdevs.Entity.Star;

public class P2Power extends SuperPower{
	
	public double animdouble, bhp;
	public boolean b = false;
    public int timer, twidth, theight;
	
	public Rectangle rect(){
		return new Rectangle(player.x + player.width / 2 - twidth / 2, player.y + player.height / 2 - theight / 2, twidth, theight);
	}
	
	public void render(Graphics g){
		if (!b) g.setColor(Color.BLUE);
		if (b) g.setColor(Color.RED);
		g.drawOval(rect().x, rect().y, rect().width, rect().height);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		int title5w = (int) g.getFontMetrics().getStringBounds(((int) (100 - timer / 7.5) + "%"), g).getWidth();
		g.drawString(((int) (100 - timer / 7.5) + "%"), (((Game.WIDTH  * Game.SCALE) / 2) - (title5w / 2)), 30);
	}
	
	public void setb(){
		b = false;
	}
	
	public void update(Game game){
		timer ++;
		animdouble += 0.2;
		if (animdouble > 10){
			animdouble = 0;
		}
		player.width /= 4;
		player.height /= 4;
		twidth = player.width * (int) animdouble;
		theight = player.height * (int) animdouble;
		player.playerfiredraw.p2p = true;
		if (timer > 750){
			player.width = player.image.getWidth();
			player.height = player.image.getHeight();
			player.playerfiredraw.p2p = false;
		    player.stopsuperpower();
		}
		setb();
		for (final Star star : game.update.entity.stararray){
			if (rect().intersects(star.getRect())){
				b = true;
			}
		}
		if (b){
			bhp += 0.1;
			if (bhp >= 1){
				player.hp += (int) bhp;
				bhp = 0;
			}
			player.printhp();
		}
	}
}
