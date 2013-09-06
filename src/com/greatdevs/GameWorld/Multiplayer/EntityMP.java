package com.greatdevs.GameWorld.Multiplayer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.greatdevs.Game;
import com.greatdevs.Entity.Explosion;
import com.greatdevs.Entity.Particle;
import com.greatdevs.GameWorld.MultiPlayer;
import com.greatdevs.Menu.LoseMenu;
import com.greatdevs.Sound.Sound;

public class EntityMP {
	public ArrayList<PlayerMP> playerarray = new ArrayList<PlayerMP>();
	public ArrayList<StarMP> stararray = new ArrayList<StarMP>();
	public ArrayList<BulletMP> bulletarray = new ArrayList<BulletMP>();
	public ArrayList<Explosion> explosionarray = new ArrayList<Explosion>();
	public ArrayList<Particle> particlearray = new ArrayList<Particle>();
	
	public int playerheight;
	
	public boolean dead = false, opponentdead = false;
	
	MultiPlayer mp;
	
	public EntityMP(MultiPlayer mp){
		this.mp = mp;
	}
	
	public void render(Graphics g){
		for(int i = 0; i < stararray.size(); i ++){
			if (stararray.get(i) != null) stararray.get(i).render(g);
		}
		for(int i = 0; i < bulletarray.size(); i ++){
			if (bulletarray.get(i) != null) bulletarray.get(i).render(g);
		}
		for(int i = 0; i < explosionarray.size(); i ++){
			if (explosionarray.get(i) != null) explosionarray.get(i).render(g);
		}
		for(int i = 0; i < particlearray.size(); i ++){
			if (particlearray.get(i) != null) particlearray.get(i).render(g);
		}
		
		//keep it here!
		for(int i = 0; i < playerarray.size(); i ++){
			if (playerarray.get(i) != null) playerarray.get(i).render(g);
		}
	}
	
	public void update(Game game){
		if (mp.thisplayer.hp <= 0){
			game.setGameMode(null);
			game.setMenu(new LoseMenu());
			dead = true;
		}
		if (opponentdead){
			game.setGameMode(null);
			game.setMenu(new LoseMenu());
			dead = true;
		}
		for(int i = 0; i < stararray.size(); i ++){
			StarMP star = (StarMP) stararray.get(i);
			if (star != null) star.update(game);
			if (star.x < 0 - star.getRect().width) stararray.remove(i);
		}
		for (int i = 0; i < explosionarray.size(); i ++){
			Explosion explosion = (Explosion) explosionarray.get(i);
			if (explosion != null) explosion.update(game);
			if (explosion.explosionfinished) explosionarray.remove(i);
		}
		for (int i = 0; i < bulletarray.size(); i ++){
			BulletMP bullet = (BulletMP) bulletarray.get(i);
			if (bullet != null) bullet.update(game, this);
			if (bullet.x > (Game.WIDTH * Game.SCALE)) bulletarray.remove(i);
		}
		for (int i = 0; i < particlearray.size(); i ++){
			Particle particle = (Particle) particlearray.get(i);
			particle.update(game);
			if (particle.removed) particlearray.remove(i);
		}
		collision(game);
	}
	
	public void collision(Game game){
		for(int i = 0; i < stararray.size(); i ++){
			StarMP star = (StarMP) stararray.get(i);
				if (mp.thisplayer.getRect().intersects(star.getRect())){
					explosionarray.add(new Explosion(star.x, star.y));
					particlearray.add(new Particle(Color.RED, 25, 25, star.x + 25, star.y + 25, 3));
					particlearray.add(new Particle(Color.LIGHT_GRAY, 30, 25, star.x + 25, star.y + 25, 2));
					mp.thisplayer.hp --;
					mp.thisplayer.printhp();
					stararray.remove(i);
					Sound.play("explosion.wav");
				}
				if (mp.opponent.getRect().intersects(star.getRect())){
					explosionarray.add(new Explosion(star.x, star.y));
					stararray.remove(i);
					Sound.play("explosion.wav");
				}
		}
		for (int i = 0; i < bulletarray.size(); i ++){
			BulletMP bullet = (BulletMP) bulletarray.get(i);
			for(int w = 0; w < stararray.size(); w ++){
				StarMP star = (StarMP) stararray.get(w);
				if (bullet.getRect().intersects(star.getRect())){
					explosionarray.add(new Explosion(star.x, star.y));
					particlearray.add(new Particle(Color.RED, 25, 25, star.x + 25, star.y + 25, 3));
					particlearray.add(new Particle(Color.LIGHT_GRAY, 30, 25, star.x + 25, star.y + 25, 2));
					stararray.remove(w);
					if (i < bulletarray.size()){
						bulletarray.remove(i);
					}
					MultiPlayer.SCORE += 25;
				}
			}
		}
	}
	
	public void removeAllArrays(){
		playerarray.removeAll(playerarray);
		stararray.removeAll(stararray);
		explosionarray.removeAll(explosionarray);
		bulletarray.removeAll(bulletarray);
		particlearray.removeAll(particlearray);
	}
}
