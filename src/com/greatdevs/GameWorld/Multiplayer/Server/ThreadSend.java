package com.greatdevs.GameWorld.Multiplayer.Server;

import java.io.IOException;
import java.io.ObjectOutputStream;

import com.greatdevs.GameWorld.MultiPlayer;
import com.greatdevs.GameWorld.Multiplayer.Menu.ErrorMenu;

public class ThreadSend extends Thread {

	ObjectOutputStream out;
	MultiPlayer world;
	
	boolean doloop = true;
	
	public ThreadSend(MultiPlayer w, ObjectOutputStream o){
		world = w;
		out = o;
		start();
	}
	
	public void run(){
		while(doloop){
			try {
				out.writeObject(""+world.entity.dead);
				
				out.writeObject(""+world.thisplayer.type[4]);
				out.writeObject(""+world.thisplayer.x);
				out.writeObject(""+world.thisplayer.y);
				out.writeObject(""+world.thisplayer.fire);
				
				if (world.SERVER){
				out.writeObject(""+world.serverwork.spawn);
				out.writeObject(""+world.serverwork.stary);
				out.writeObject(""+world.serverwork.starsize);
				world.serverwork.spawn = 0;
				}
				
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
				doloop = false;
				world.game.setMenu(new ErrorMenu());
			}
		}
	}
}
