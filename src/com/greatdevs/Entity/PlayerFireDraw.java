package com.greatdevs.Entity;

import java.awt.Graphics;

public class PlayerFireDraw {
	private double animation;
	public boolean p2p = false;
	public void render(Graphics g, Player player){
		animation += 0.25;
		if (animation >= 4){
			animation = 0;
		}
		if (ShipTypes.getIntType() == 1){
			g.drawImage(player.icons.fire[(int) animation], player.x - 25, player.y + player.height / 2 - 23, 35, 20, null);
			g.drawImage(player.icons.fire[(int) animation], player.x - 25, player.y + player.height / 2 - 13, 35, 25, null);
			g.drawImage(player.icons.fire[(int) animation], player.x - 25, player.y + player.height / 2 + 3, 35, 20, null);
		}
		if (ShipTypes.getIntType() == 2){
			if (!p2p){
			g.drawImage(player.icons.fire[(int) animation], player.x - 25, player.y + player.height / 2 - 18, 35, 15, null);
			g.drawImage(player.icons.fire[(int) animation], player.x - 25, player.y + player.height / 2 + 2, 35, 15, null);
			}
			else if (p2p){
				g.drawImage(player.icons.fire[(int) animation], player.x - 25 / 4, player.y + player.height / 2 - 18 / 4, 35 / 4, 15 / 4, null);
				g.drawImage(player.icons.fire[(int) animation], player.x - 25 / 4, player.y + player.height / 2 + 2, 35 / 4, 15 / 4, null);
			}
		}
		if (ShipTypes.getIntType() == 3){
			g.drawImage(player.icons.fire[(int) animation], player.x - 20, player.y + player.height / 2 - 15, 30, 30, null); 
		}
		if (ShipTypes.getIntType() == 4){
			g.drawImage(player.icons.fire[(int) animation], player.x - 25, player.y + player.height / 2 - 18, 35, 15, null);
			g.drawImage(player.icons.fire[(int) animation], player.x - 25, player.y + player.height / 2 + 2, 35, 15, null);
		}
		if (ShipTypes.getIntType() == 5){
			g.drawImage(player.icons.fire[(int) animation], player.x - 25, player.y + 5, 35, 15, null);
			g.drawImage(player.icons.fire[(int) animation], player.x - 25, player.y + player.height / 2 + 5, 35, 15, null);
		}
		if (ShipTypes.getIntType() == 6){
			g.drawImage(player.icons.fire[(int) animation], player.x - 35, player.y + player.height / 2 - 25, 50, 50, null); 
		}
	}
}
