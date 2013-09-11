package com.greatdevs.Menu.CustomShip;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.greatdevs.Game;
import com.greatdevs.InputHandler;
import com.greatdevs.Image.ShipIcons;

public class DrawComponent extends CSMenu{
	
	public int select = 1, sy;
	public int x, y;
	
	public int colorred = 255, colorgreen = 255, colorblue = 255;
	
	ShipIcons shipicons = new ShipIcons();
	
	public BufferedImage image;
	public Rectangle rectangle;
	
	public DrawComponent(){
		rectangle = new Rectangle(((Game.WIDTH * Game.SCALE) / 2) - 10, ((Game.HEIGHT * Game.SCALE) / 2) - 10, 20, 20);
		image = new BufferedImage(rectangle.width, rectangle.height, BufferedImage.TYPE_INT_RGB);
	}
	
	public void init(Game game, InputHandler input, CustomShipMenu csm) {
		this.input = input;
		this.game = game;
		this.csm = csm;
	}
	
	public void render(Graphics g){
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
		g.setColor(new Color(0,0,0,225));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.drawString("Draw component", 25, 50);
		if (select >= 4) select = 4;
		if (select <= 1) select = 1;
		g.drawString("Red " + colorred, 25, 300);
		g.drawString("Greed " + colorgreen, 25, 350);	
		g.drawString("Blue " + colorblue, 25, 400);
		g.drawString("Place", 25, 450);		
		g.drawString(">                      <", 3, sy);
		
		renderimage();
		g.drawImage(image, rectangle.x, rectangle.y, rectangle.width, rectangle.height, null);
		
		sy = select * 50 + 250;
	}
	
	public void renderimage(){
		Graphics g = image.getGraphics();
		g.setColor(new Color(colorred, colorgreen, colorblue, 255));
		g.fillRect(0, 0, rectangle.width, rectangle.height);
		g.dispose();
	}
	
	public void update(){
		if (input.up.clicked) select --;
		if (input.down.clicked) select ++;
		
		if (input.left.clicked && select == 1) colorred --;
		if (input.right.clicked && select == 1) colorred ++;
		
		if (input.left.clicked && select == 2) colorgreen --;
		if (input.right.clicked && select == 2) colorgreen ++;
		
		if (input.left.clicked && select == 3) colorblue --;
		if (input.right.clicked && select == 3) colorblue ++;
		
		if (colorred >= 255) colorred = 255;
		if (colorred <= 0) colorred = 0;
		
		if (colorgreen >= 255) colorgreen = 255;
		if (colorgreen <= 0) colorgreen = 0;
		
		if (colorblue >= 255) colorblue = 255;
		if (colorblue <= 0) colorblue = 0;
		
		if (input.enter.clicked && select == 4){
			csm.setMenu(new PlaceComponentMenu(image));
			csm.renderShip();
		}
	}
}
