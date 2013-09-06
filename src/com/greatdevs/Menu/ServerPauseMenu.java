package com.greatdevs.Menu;

import java.awt.Color;
import java.awt.Graphics;

import com.greatdevs.Game;
import com.greatdevs.GameWorld.Multiplayer.EntityMP;
import com.greatdevs.GameWorld.Multiplayer.Server.ServerWork;

public class ServerPauseMenu extends Menu {

	private int select = 1;
	private int sy = 400;

	EntityMP entitymp;
	ServerWork sw;

	public ServerPauseMenu(EntityMP entitymp, ServerWork sw) {
		this.entitymp = entitymp;
		this.sw = sw;
	}

	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		g.setColor(new Color(0, 0, 0, 225));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT
				* Game.SCALE);
		g.setColor(Color.WHITE);
		g.drawString("Pause", 25, 50);
		if (select >= 2)
			select = 2;
		if (select <= 1)
			select = 1;
		g.drawString("Continue", 25, 400);
		g.drawString("Exit", 25, 450);
		g.drawString(">                      <", 3, sy);
		sy = select * 50 + 350;
	}

	public void update() {
		if (input.up.clicked)
			select--;
		if (input.down.clicked)
			select++;

		if (input.enter.clicked && select == 1)
			game.setMenu(null);
		if (input.enter.clicked && select == 2) {
			try {
				sw.client.close();
				sw.server.close();
				sw.socket.close();
				sw.in.reset();
				sw.out.reset();
			} catch (Exception e) {
				e.printStackTrace();
			}
			entitymp.removeAllArrays();
			entitymp.dead = true;
			game.setMenu(new MainMenu());
		}
	}
}
