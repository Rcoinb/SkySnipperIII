package com.greatdevs.Menu.CustomShip;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.greatdevs.Game;
import com.greatdevs.InputHandler;
import com.greatdevs.StaticGameOptions;

public class SaveShipMenu extends CSMenu{

	public String name = "";
	
	public boolean namewriting = true;
	public String listfilepath = StaticGameOptions.PATH + "SkySnipperIII//Ships//list.dat";
	public int select = 1;
	public int sy, timer;
	
	public SaveShipMenu(){

	}
	
	public void init(Game game, InputHandler input, CustomShipMenu csm) {
		this.input = input;
		this.game = game;
		this.csm = csm;
		
		game.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
		        char c = e.getKeyChar();
		        if (c != '/' && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_ENTER && name.length() < 7){ 
		        name += c;
		        }
			}
		});
	}
	
	public void render(Graphics g){
		csm.drawstats.render(g, game, csm.shiptype, csm.shipimage, "Random");
		g.setColor(new Color(0,0,0,200));
		g.setColor(new Color(0,0,0,225));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
		if (select >= 2) select = 2;
		if (select <= 1) select = 1;
		sy = select * 50 + 350;
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.drawString("Enter name", 25, 50);
		g.drawString("Name " + name, 25, 400);
		int commandw = (int) g.getFontMetrics().getStringBounds(name, g).getWidth();
		if (timer % 60 > 30 && namewriting) g.drawString("|",commandw + 100, 398);
		g.drawString("Save", 25, 450);
		g.drawString(">                               <", 3, sy);
	}
	
	public void SaveShipStats() throws Exception{
		File file = new File(StaticGameOptions.PATH + "SkySnipperIII//Ships//" + name + ".dat");
		if (!file.exists()){
			file.createNewFile();
		}
		    FileWriter saveFile = new FileWriter(file);
		    // Write the data to the file.
		    saveFile.write("\n");
		    saveFile.write(csm.shiptype[0] + "\n");
		    saveFile.write(csm.shiptype[1] + "\n");
		    saveFile.write(csm.shiptype[2] + "\n");
		    saveFile.write(csm.shiptype[3] + "\n");
		    saveFile.write("\n");	    
		    // All done, close the FileWriter.
		    saveFile.close();
	}
	
	public void updatelistfile(String addname, boolean newfile) throws Exception{ 
		BufferedWriter bw = null;
		try {
		    bw = new BufferedWriter(new FileWriter(listfilepath, true));
		    if (newfile){
		    	bw.write("\n" + addname);
		    }
		    else{
		    bw.write(addname);
		    }
		    bw.newLine();
		    bw.flush();
		} catch (IOException ioe) {
		    ioe.printStackTrace();
		} finally { // always close the file
		    if (bw != null) {
		        try {
		            bw.close();
		        } catch (IOException ioe2) {
		            // just ignore it
		        }
		    }
		}
	}

	
	public void updatelist() throws Exception{
		File file = new File(listfilepath);
		if (!file.exists()){
			file.createNewFile();
			updatelistfile(name, true);
		}
		else{
		updatelistfile(name, false);
		}
	}
	
	public void SaveShip(){
		try {
			SaveShipStats();
		    File outputfile = new File(StaticGameOptions.PATH + "SkySnipperIII//Ships//" + name + ".png");
		    ImageIO.write(csm.shipimage, "png", outputfile);
		    updatelist();
		    game.setMenu(new FirstMenu());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String removeChar(String string) {
		if (string.length() > 0) string = string.substring(0, string.length() - 1);
		return string;
	}
	
	public void update(){
		timer ++;
		if (!namewriting){
		if (input.up.clicked) select --;
		if (input.down.clicked) select ++;
		}
		
		if (select == 1){
			namewriting = true;
			if (input.backspace.clicked) name = removeChar(name);
			if (input.enter.clicked || input.menu.clicked){
				namewriting = false;
				select = 2;
			}
		}
		if (input.enter.clicked && select == 2 && !namewriting && !name.equals("")) SaveShip();
		else if (name.equals("")) select = 1;
	}
}
