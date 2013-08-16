package com.greatdevs.GameUpdate;

import com.greatdevs.Game;
import com.greatdevs.Entity.*;
import com.greatdevs.GameWorld.GameWorld;

public class Update {
	
	public Entity entity = new Entity();
	public Render render = new Render();
	public GameWorld gameworld = new GameWorld();
	
	public void update(Game game){
		entity.update(game);
		gameworld.update(game);
	}
}
