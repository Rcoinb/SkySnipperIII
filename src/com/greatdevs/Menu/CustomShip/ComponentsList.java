package com.greatdevs.Menu.CustomShip;

import java.awt.Rectangle;

import com.greatdevs.Image.Icons;
import com.greatdevs.Image.ShipIcons;

public class ComponentsList {
	public void loadStatsComponents(ShipComponents shipcomponents, Icons icons, ShipIcons shipicons){
		shipcomponents.components.add(new Component(1, 5, 5, 50000000, icons.player[2], 1));
		shipcomponents.components.add(new Component(2, 5, 5, 50, icons.player[3], 2));
		shipcomponents.components.add(new Component(3, 5, 5, 50, icons.player[3], 3));
		shipcomponents.components.add(new Component(4, 5, 5, 50, icons.player[3], 4));
		shipcomponents.components.add(new Component(5, 5, 5, 50, icons.player[3], 5));
		shipcomponents.components.add(new Component(6, 5, 5, 50, icons.player[3], 6));
		shipcomponents.components.add(new Component(7, 5, 5, 50, icons.player[3], 7));
		shipcomponents.components.add(new Component(8, 5, 5, 50, icons.player[3], 8));
		shipcomponents.components.add(new Component(9, 5, 5, 50, icons.player[3], 9));
		shipcomponents.components.add(new Component(0, 5, 5, 50, icons.player[3], 10));
		shipcomponents.components.add(new Component(23, 5, 5, 50, icons.player[4], 11));
		shipcomponents.components.add(new Component(4, 5, 5, 50, icons.player[4], 12));
		shipcomponents.components.add(new Component(5, 5, 5, 50, icons.player[4], 13));
		shipcomponents.components.add(new Component(5, 5, 5, 50, icons.player[4], 14));
		shipcomponents.components.add(new Component(5, 5, 5, 50, icons.player[4], 15));
		shipcomponents.components.add(new Component(5, 5, 5, 50, icons.player[2], 16));
		shipcomponents.components.add(new Component(5, 5, 5, 50, icons.player[3], 17));
		shipcomponents.components.add(new Component(5, 5, 5, 50, icons.player[3], 18));
		shipcomponents.components.add(new Component(5, 5, 5, 50, icons.player[3], 19));
		shipcomponents.	components.add(new Component(5, 5, 5, 50, icons.player[3], 20));
		shipcomponents.	components.add(new Component(5, 5, 5, 50, icons.player[3], 21));
		shipcomponents.	components.add(new Component(5, 5, 5, 50, icons.player[3], 22));
		shipcomponents.	components.add(new Component(5, 5, 5, 50, icons.player[3], 23));
		shipcomponents.components.add(new Component(5, 5, 5, 50, icons.player[2], 24));
		shipcomponents.components.add(new Component(5, 5, 5, 50, icons.player[3], 25));
		shipcomponents.components.add(new Component(5, 5, 5, 50, icons.player[3], 26));
		shipcomponents.components.add(new Component(5, 5, 5, 50, icons.player[3], 27));
		shipcomponents.components.add(new Component(5, 5, 5, 50, icons.player[3], 28));
		shipcomponents.components.add(new Component(5, 5, 5, 50, icons.player[3], 29));
		
		shipcomponents.components.add(new Component(0, 0, 0, 0, shipicons.component[shipicons.component.length - 1], 0));
	}
	public void loadImageComponents(CreateShipShapeMenu cssm, ShipIcons shipicons){
		for(int i = 0; i < cssm.shipicons.component.length - 1; i ++){
			cssm.imagecomponents.add(new ImageComponent(shipicons.component[i], new Rectangle(0, 0, shipicons.component[i].getWidth(), shipicons.component[i].getHeight()), i * 350));
		}
	}
}
