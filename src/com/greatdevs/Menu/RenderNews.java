package com.greatdevs.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import com.greatdevs.Game;

public class RenderNews {
	
	ArrayList<info> news = new ArrayList<info>();
	
	private int change, maxchange = 500, id = 0, color = 255;

	public RenderNews(){
		news.add(new info
				(new TextContainer
						("From out there on the Moon, international politics look so petty. You want to grab a politician by the scruff of the neck and drag him a quarter of a million miles out and say, ‘Look at that, you son of a bitch.’",
								275, 75), "-Edgar Mitchel"));
		
		news.add(new info
				(new TextContainer
						("Anyone who sits on top of the largest hydrogen-oxygen fueled system in the world, knowing they’re going to light the bottom, and doesn’t get a little worried, does not fully understand the situation.",
								275, 75), "-John Young"));

		news.add(new info
				(new TextContainer
						("Mars has been flown by, orbited, smacked into, radar examined, and rocketed onto, as well as bounced upon, rolled over, shoveled, drilled into, baked and even blasted. Still to come: Mars being stepped on.",
								275, 75), "-Buzz Aldrin"));
		
		news.add(new info
				(new TextContainer
						("It doesn’t sound like there was time for the word to be there. On the other hand, I didn’t intentionally make an inane statement… certainly the ‘a’ was intended, because that’s the only way the statement makes any sense.",
								275, 75), "-Neil Armstrong"));
		
		news.add(new info
				(new TextContainer
						("There is perhaps no better a demonstration of the folly of human conceits than this distant image of our tiny world.",
								275, 75), "-Carl Sagan"));
		
		news.add(new info
				(new TextContainer
						("Space is for everybody. It's not just for a few people in science or math, or for a select group of astronauts. That's our new frontier out there, and it's everybody's business to know about space.",
								275, 75), "-Christa McAuliffe"));
		
		news.add(new info
				(new TextContainer
						("When I orbited the Earth in a spaceship, I saw for the first time how beautiful our planet is. Mankind, let us preserve and increase this beauty, and not destroy it!",
								275, 75), "-Yuri Gagarin"));
		
		news.add(new info
				(new TextContainer
						("I don't think the human race will survive the next thousand years, unless we spread into space. There are too many accidents that can befall life on a single planet. But I'm an optimist. We will reach out to the stars.",
								275, 75), "-Stephen Hawking"));
		
		news.add(new info
				(new TextContainer
						("When I consider how, after sunset, the stars come out gradually in troops from behind the hills and woods, I confess that I could not have contrived a more curious and inspiring sight.",
								275, 75), "-Henry Thoreau"));
		
		news.add(new info
				(new TextContainer
						("Looking at these stars suddenly dwarfed my own troubles and all the gravities of terrestrial life. I thought of their unfathomable distance, and the slow inevitable drift of their movements out of the unknown past into the unknown future.",
			                    275, 75), "-H. G. Wells"));
		
		id = new Random().nextInt(news.size() - 1);
	}

	public void render(Graphics g) {
		g.setFont(new Font("Arial", Font.PLAIN, 11));

		g.setColor(new Color(255, 255, 255, color));
		info infos = (info) news.get(id);
		infos.tc.paint(g, (Game.WIDTH * Game.SCALE) - infos.tc.m_width - 15, 375);
		int sw = (int) g.getFontMetrics().getStringBounds(infos.s, g).getWidth();
		g.setColor(new Color(128, 128, 128, color));
		g.drawString(infos.s, (Game.WIDTH * Game.SCALE) - sw - 15, 375 + infos.tc.m_height + 15);
	}
	
	public void update(){
		change ++;
		if (change >= maxchange){
			if (id + 1 < news.size()) id ++;
			else if (id + 1 >= news.size()) id = 0;
			change = 0;
		}
		
		if (change >= maxchange - 50){
			if (color > 0) color -= 5;
			if (color <= 0) color = 0;
		}
		if (change <= 50){
			if (color < 255) color += 5;
			if (color >= 255) color = 255;
		}
	}
	
	class info{
		TextContainer tc;
		String s;
		public info(TextContainer tc, String s){
			this.tc = tc;
			this.s = s;
		}
	}
}
