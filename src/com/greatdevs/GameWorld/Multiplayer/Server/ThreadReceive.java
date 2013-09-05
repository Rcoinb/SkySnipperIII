package com.greatdevs.GameWorld.Multiplayer.Server;

import java.io.IOException;
import java.io.ObjectInputStream;

import com.greatdevs.GameWorld.MultiPlayer;
import com.greatdevs.GameWorld.Multiplayer.StarMP;
import com.greatdevs.GameWorld.Multiplayer.Menu.ErrorMenu;

public class ThreadReceive extends Thread {

	ObjectInputStream in;
	MultiPlayer world;
	
	boolean doloop = true;
	
	public ThreadReceive(MultiPlayer w, ObjectInputStream i){
		world = w;
		in = i;
		start();
	}
	
	public void run(){
		while(doloop){
			try {
				world.entity.opponentdead = Boolean.parseBoolean((String)in.readObject());
				int type = Integer.parseInt((String)in.readObject());
				world.opponent.changeImageType(type);
			    world.opponent.x = Integer.parseInt((String)in.readObject());
			    world.opponent.y = Integer.parseInt((String)in.readObject());
			    boolean fire = Boolean.parseBoolean((String)in.readObject());
			    if (!world.SERVER){
			    int star = Integer.parseInt((String)in.readObject());
			    int stary = Integer.parseInt((String)in.readObject());
			    int stars = Integer.parseInt((String)in.readObject());
			    if (star != 0){
			     world.entity.stararray.add(new StarMP(world, 1000, stary, world.objectspeed, stars));
			     star = 0;
			    }
		    	}
			    if (fire){
			    	 world.opponent.firebullet();
			    }
			} catch (IOException e) {
				e.printStackTrace();
				doloop = false;
				world.game.setMenu(new ErrorMenu());
			} catch (NumberFormatException e) {
				e.printStackTrace();
				doloop = false;
				world.game.setMenu(new ErrorMenu());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				doloop = false;
				world.game.setMenu(new ErrorMenu());
			}
		}
	}
}
