package com.greatdevs.Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import com.greatdevs.Game;
import com.greatdevs.StaticGameOptions;

public class Particle {

	public boolean removed = false;
	
	ArrayList<particle> particlearray = new ArrayList<particle>();
	
	public Particle(Color color, int value, int life, int x, int y, int size){
		for (int i = 0; i < value; i ++){
			if (StaticGameOptions.PARTICLES) particlearray.add(new particle(color, life, x, y, size));
			else if (!StaticGameOptions.PARTICLES){
				removed = true;
			}
		}
	}
	
	public void render(Graphics g){
		for (particle p : particlearray){
			p.render(g);
		}
	}
	
	public void update(Game game){
		for (int i = 0; i < particlearray.size(); i ++){
			particlearray.get(i).update(game);
			if (particlearray.get(i).life <= 0){
				particlearray.remove(i);
			}
		}
		if (particlearray.size() <= 0){
			removed = true;
		}
	}
	
	class particle{
		public Color color;
		public int size, life, x, y;
		private double xs, ys;
		public particle(Color color, int life, int x, int y, int size){
			this.color = color;
			this.life = life + (new Random().nextInt(10) * (new Random().nextInt(3) - 1));
			this.x = x;
			this.y = y;
			xs = new Random().nextGaussian();
			ys = new Random().nextGaussian();
			this.size = size + new Random().nextInt(4) - 1;
		}
		
		public void render(Graphics g){
			g.setColor(color);
			g.fillRect(x, y, size, size);
		}
		
		public void update(Game game){
			x += xs + new Random().nextGaussian();
			y += ys + new Random().nextGaussian();
			life --;
		}
	}
}
