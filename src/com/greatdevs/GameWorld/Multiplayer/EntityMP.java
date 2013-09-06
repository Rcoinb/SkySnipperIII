package com.greatdevs.GameWorld.Multiplayer;

import java.awt.Graphics;
import java.util.ArrayList;

import com.greatdevs.Game;
import com.greatdevs.Entity.Explosion;
import com.greatdevs.GameWorld.MultiPlayer;
import com.greatdevs.Menu.LoseMenu;
import com.greatdevs.Sound.Sound;

public class EntityMP {
	public ArrayList<PlayerMP> playerarray = new ArrayList<PlayerMP>();
	public ArrayList<StarMP> stararray = new ArrayList<StarMP>();
	public ArrayList<BulletMP> bulletarray = new ArrayList<BulletMP>();
	public ArrayList<Explosion> explosionarray = new ArrayList<Explosion>();
	
	public int playerheight;
	
	public boolean dead = false, opponentdead = false;
	
	MultiPlayer mp;
	
	public EntityMP(MultiPlayer mp){
		this.mp = mp;
	}
	
	public void render(Graphics g){
		for(final StarMP star : stararray){
			star.render(g);
		}
		for(final BulletMP bullet : bulletarray){
			bullet.render(g);
		}
		for(final Explosion explosion : explosionarray){
			explosion.render(g);
		}
		
		//keep it here!
		for(final PlayerMP player : playerarray){
			player.render(g);
		}
	}
	
	public void update(Game game){
		System.out.println(opponentdead);
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
			star.update(game);
			if (star.x < 0 - star.getRect().width) stararray.remove(i);
		}
		for (int i = 0; i < explosionarray.size(); i ++){
			Explosion explosion = (Explosion) explosionarray.get(i);
			explosion.update(game);
			if (explosion.explosionfinished) explosionarray.remove(i);
		}
		for (int i = 0; i < bulletarray.size(); i ++){
			BulletMP bullet = (BulletMP) bulletarray.get(i);
			bullet.update(game);
			if (bullet.x > (Game.WIDTH * Game.SCALE)) bulletarray.remove(i);
		}
		collision(game);
	}
	
	public void collision(Game game){
		for(int i = 0; i < stararray.size(); i ++){
			StarMP star = (StarMP) stararray.get(i);
				if (mp.thisplayer.getRect().intersects(star.getRect())){
					explosionarray.add(new Explosion(star.x, star.y));
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
	}
}
