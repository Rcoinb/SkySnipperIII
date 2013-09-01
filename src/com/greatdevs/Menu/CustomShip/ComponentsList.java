package com.greatdevs.Menu.CustomShip;

import java.awt.Rectangle;
import java.util.Random;

import com.greatdevs.Image.ShipIcons;

public class ComponentsList {
	public void loadStatsComponents(ShipComponents shipcomponents,ShipIcons shipicons){
		//hp, speed, reloadspeed, price
		
		shipcomponents.components.add(new Component(1, 1, -1, 50, shipicons.chip[new Random().nextInt(3)], 1));
		shipcomponents.components.add(new Component(2, 1, -1, 70, shipicons.chip[new Random().nextInt(3)], 2));
		shipcomponents.components.add(new Component(-1, -1, -5, 75, shipicons.chip[new Random().nextInt(3)], 3));
		shipcomponents.components.add(new Component(4, 5, 2, 25, shipicons.chip[new Random().nextInt(3)], 4));
		shipcomponents.components.add(new Component(1, 1, -2, 50, shipicons.chip[new Random().nextInt(3)], 5));
		shipcomponents.components.add(new Component(2, 1, 0, 50, shipicons.chip[new Random().nextInt(3)], 6));
		shipcomponents.components.add(new Component(0, 0, -5, 25, shipicons.chip[new Random().nextInt(3)], 7));
		shipcomponents.components.add(new Component(5, 5, 5, 75, shipicons.chip[new Random().nextInt(3)], 8));
		shipcomponents.components.add(new Component(2, -5, 0, 10, shipicons.chip[new Random().nextInt(3)], 9));
		shipcomponents.components.add(new Component(0, 2, 0, 25, shipicons.chip[new Random().nextInt(3)], 10));
		shipcomponents.components.add(new Component(15, 0, 10, 25, shipicons.chip[new Random().nextInt(3)], 11));
		shipcomponents.components.add(new Component(4, 5, -1, 75, shipicons.chip[new Random().nextInt(3)], 12));
		shipcomponents.components.add(new Component(5, 5, -4, 250, shipicons.chip[new Random().nextInt(3)], 13));
		shipcomponents.components.add(new Component(1, 4, 0, 50, shipicons.chip[new Random().nextInt(3)], 14));
		shipcomponents.components.add(new Component(15, -5, -5, 50, shipicons.chip[new Random().nextInt(3)], 15));
		shipcomponents.components.add(new Component(3, 3, 3, 50, shipicons.chip[new Random().nextInt(3)], 16));
		shipcomponents.components.add(new Component(-7, -7, -7, 50, shipicons.chip[new Random().nextInt(3)], 17));
		shipcomponents.components.add(new Component(2, 2, 2, 15, shipicons.chip[new Random().nextInt(3)], 18));
		shipcomponents.components.add(new Component(5, 5, 5, 25, shipicons.chip[new Random().nextInt(3)], 19));
		shipcomponents.	components.add(new Component(8, 5, 5, 35, shipicons.chip[new Random().nextInt(3)], 20));
		shipcomponents.	components.add(new Component(5, 5, 0, 90, shipicons.chip[new Random().nextInt(3)], 21));
		shipcomponents.	components.add(new Component(3, 2, 0, 35, shipicons.chip[new Random().nextInt(3)], 22));
		shipcomponents.	components.add(new Component(50, 0, 0, 350, shipicons.chip[new Random().nextInt(3)], 23));
		shipcomponents.components.add(new Component(-1, 15, 0, 100, shipicons.chip[new Random().nextInt(3)], 24));
		shipcomponents.components.add(new Component(1, 5, 4, 50, shipicons.chip[new Random().nextInt(3)], 25));
		shipcomponents.components.add(new Component(2, 2, -10, 350, shipicons.chip[new Random().nextInt(3)], 26));
		shipcomponents.components.add(new Component(6, 25, 0, 290, shipicons.chip[new Random().nextInt(3)], 27));
		shipcomponents.components.add(new Component(-2, 5, 5, 50, shipicons.chip[new Random().nextInt(3)], 28));
		shipcomponents.components.add(new Component(4, 8, -1, 75, shipicons.chip[new Random().nextInt(3)], 29));
		
		//Part of GUI
		shipcomponents.components.add(new Component(0, 0, 0, 0, shipicons.component[shipicons.component.length - 1], 0));
	}
	public void loadImageComponents(CreateShipShapeMenu cssm, ShipIcons shipicons){
		cssm.imagecomponents.add(new ImageComponent(shipicons.component[0], new Rectangle(0, 0, shipicons.component[0].getWidth(), shipicons.component[0].getHeight()), 50));
		cssm.imagecomponents.add(new ImageComponent(shipicons.component[1], new Rectangle(0, 0, shipicons.component[1].getWidth(), shipicons.component[1].getHeight()), 50));
		cssm.imagecomponents.add(new ImageComponent(shipicons.component[2], new Rectangle(0, 0, shipicons.component[2].getWidth(), shipicons.component[2].getHeight()), 150));
		cssm.imagecomponents.add(new ImageComponent(shipicons.component[3], new Rectangle(0, 0, shipicons.component[3].getWidth(), shipicons.component[3].getHeight()), 25));
		cssm.imagecomponents.add(new ImageComponent(shipicons.component[4], new Rectangle(0, 0, shipicons.component[4].getWidth(), shipicons.component[4].getHeight()), 250));
		cssm.imagecomponents.add(new ImageComponent(shipicons.component[5], new Rectangle(0, 0, shipicons.component[5].getWidth(), shipicons.component[5].getHeight()), 200));
		cssm.imagecomponents.add(new ImageComponent(shipicons.component[6], new Rectangle(0, 0, shipicons.component[6].getWidth(), shipicons.component[6].getHeight()), 150));
		cssm.imagecomponents.add(new ImageComponent(shipicons.component[7], new Rectangle(0, 0, shipicons.component[7].getWidth(), shipicons.component[7].getHeight()), 150));
		cssm.imagecomponents.add(new ImageComponent(shipicons.component[8], new Rectangle(0, 0, shipicons.component[8].getWidth(), shipicons.component[8].getHeight()), 100));
		cssm.imagecomponents.add(new ImageComponent(shipicons.component[9], new Rectangle(0, 0, shipicons.component[9].getWidth(), shipicons.component[9].getHeight()), 100));
		cssm.imagecomponents.add(new ImageComponent(shipicons.component[10], new Rectangle(0, 0, shipicons.component[10].getWidth(), shipicons.component[10].getHeight()), 25));
		cssm.imagecomponents.add(new ImageComponent(shipicons.component[11], new Rectangle(0, 0, shipicons.component[11].getWidth(), shipicons.component[11].getHeight()), 150));
		cssm.imagecomponents.add(new ImageComponent(shipicons.component[12], new Rectangle(0, 0, shipicons.component[12].getWidth(), shipicons.component[12].getHeight()), 150));
		cssm.imagecomponents.add(new ImageComponent(shipicons.component[13], new Rectangle(0, 0, shipicons.component[13].getWidth(), shipicons.component[13].getHeight()), 350));
		cssm.imagecomponents.add(new ImageComponent(shipicons.component[14], new Rectangle(0, 0, shipicons.component[14].getWidth(), shipicons.component[14].getHeight()), 400));
		cssm.imagecomponents.add(new ImageComponent(shipicons.component[15], new Rectangle(0, 0, shipicons.component[15].getWidth(), shipicons.component[15].getHeight()), 350));
		cssm.imagecomponents.add(new ImageComponent(shipicons.component[16], new Rectangle(0, 0, shipicons.component[16].getWidth(), shipicons.component[16].getHeight()), 350));
		cssm.imagecomponents.add(new ImageComponent(shipicons.component[17], new Rectangle(0, 0, shipicons.component[17].getWidth(), shipicons.component[17].getHeight()), 500));
		
		//Part of GUI
		cssm.imagecomponents.add(new ImageComponent(shipicons.component[shipicons.component.length - 2], new Rectangle(0, 0, shipicons.component[shipicons.component.length - 2].getWidth(), shipicons.component[shipicons.component.length - 2].getHeight()), 25));
		cssm.imagecomponents.add(new ImageComponent(shipicons.component[shipicons.component.length - 1], new Rectangle(0, 0, shipicons.component[shipicons.component.length - 1].getWidth(), shipicons.component[shipicons.component.length - 1].getHeight()), 0));
	}
}
