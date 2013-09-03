package com.greatdevs.Image;

import java.awt.image.BufferedImage;


public class Icons {

	SpriteSheet ss;
	
	public static BufferedImage frameicon;
	
	public BufferedImage focuslable, background, hpbonus, speeddownbonus, shopship, shieldbonus, magnetbonus;
	public BufferedImage p6sp;
	public BufferedImage[] star = new BufferedImage[3];
	public BufferedImage[] coin = new BufferedImage[8];
	public BufferedImage[] explosion = new BufferedImage[6];
	public BufferedImage[] player = new BufferedImage[6];
	public BufferedImage[] fire = new BufferedImage[4];
	public BufferedImage[] boss = new BufferedImage[3];
	
	public Icons() {
		BufferedIL loader = new BufferedIL();
		BufferedImage spriteSheet;
		
		frameicon = loader.loadImage("/FrameIcon.png");
		
		spriteSheet = loader.loadImage("/icons.png");

		ss = new SpriteSheet(spriteSheet);

		try {
			player[0] = ss.grabSprite(0, 0, 100, 100);
			player[1] = ss.grabSprite(100, 0, 40, 30);
			player[2] = ss.grabSprite(100, 30, 30, 70);
			player[3] = ss.grabSprite(240, 50, 100, 50);
			player[4] = ss.grabSprite(240, 0, 50, 50);
			player[5] = ss.grabSprite(140, 0, 100, 100);

			focuslable = ss.grabSprite(0, 900, 400, 100);
			background = ss.grabSprite(750, 750, 250, 250);

			shopship = ss.grabSprite(300, 0, 100, 50);

			hpbonus = ss.grabSprite(850, 0, 50, 50);
			speeddownbonus = ss.grabSprite(850, 50, 50, 50);
			shieldbonus = ss.grabSprite(850, 100, 50, 50);
			magnetbonus = ss.grabSprite(850, 150, 50, 50);

			p6sp = ss.grabSprite(400, 0, 50, 50);

			star[0] = ss.grabSprite(900, 0, 100, 100);
			star[1] = ss.grabSprite(900, 100, 100, 100);
			star[2] = ss.grabSprite(900, 200, 100, 100);

			coin[0] = ss.grabSprite(32 * 0, 100, 32, 32);
			coin[1] = ss.grabSprite(32 * 1, 100, 32, 32);
			coin[2] = ss.grabSprite(32 * 2, 100, 32, 32);
			coin[3] = ss.grabSprite(32 * 3, 100, 32, 32);
			coin[4] = ss.grabSprite(32 * 4, 100, 32, 32);
			coin[5] = ss.grabSprite(32 * 5, 100, 32, 32);
			coin[6] = ss.grabSprite(32 * 6, 100, 32, 32);
			coin[7] = ss.grabSprite(32 * 7, 100, 32, 32);

			explosion[0] = ss.grabSprite(50 * 0, 132, 50, 50);
			explosion[1] = ss.grabSprite(50 * 1, 132, 50, 50);
			explosion[2] = ss.grabSprite(50 * 2, 132, 50, 50);
			explosion[3] = ss.grabSprite(50 * 3, 132, 50, 50);
			explosion[4] = ss.grabSprite(50 * 4, 132, 50, 50);
			explosion[5] = ss.grabSprite(50 * 4, 132, 50, 50);

			fire[0] = ss.grabSprite(50 * 0, 200, 50, 50);
			fire[1] = ss.grabSprite(50 * 1, 200, 50, 50);
			fire[2] = ss.grabSprite(50 * 2, 200, 50, 50);
			fire[3] = ss.grabSprite(50 * 3, 200, 50, 50);

			boss[0] = ss.grabSprite(0, 250, 200, 100);
			boss[1] = ss.grabSprite(0, 350, 100, 100);
			boss[2] = ss.grabSprite(0, 450, 100, 70);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}