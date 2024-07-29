package dataVisualization;

import processing.core.PApplet;
import processing.core.PConstants;

public class Button {

	private HeatMap parent;
	private int x, y, size;
	private String name;
	private String shape;
	private String type;
	private boolean pressed;
	
	public Button(HeatMap parent, int x, int y, int size, String name, String shape, String type, boolean pressed) {
		this.parent = parent;
		this.size = size;
		this.name = name;
		this.shape = shape;
		if(shape.equals("Circle")) {
			this.x = x+size/2;
			this.y = y+size/2;
		}else {
			this.x = x;
			this.y = y;
		}
		
		this.type = type;
		
		this.pressed = pressed;
	}
	
	public boolean getValue() {return pressed;}
	
	public String getType() {return type;}
	
	public void setValue(boolean pressed) {this.pressed = pressed;}
	
	public void mouseClicked() {
		if(shape.equals("Square")) {
			if(parent.mouseX > x && parent.mouseX < x + size && parent.mouseY > y && parent.mouseY < y + size) {
				if(!pressed) {
					parent.clearButtons();
					pressed = true;
					parent.loadMap();
				}
			}
		}
		else if(shape.equals("Circle")) {
				if(Math.sqrt(Math.pow((parent.mouseX - x), 2) + Math.pow((parent.mouseY - y), 2)) < size/2) {
					if(!pressed) {
						parent.clearButtons();
						pressed = true;
						parent.loadMap();
					}
				}
		}
	}
	
	public void draw() {
		if(shape.equals("Square"))
			drawSquare();
		else if(shape.equals("Circle"))
			drawCircle();
		
	}
	
	public void drawSquare() {
		parent.rect(x, y, size, size);
		if(pressed) {
			parent.fill(0);
			parent.rect(x+5, y+5, size-10, size-10);
			parent.fill(255);
		}
		parent.text(name, x+size/2, y-10);
	}
	
	public void drawCircle() {
		parent.ellipse(x, y, size, size);
		if(pressed) {
			parent.fill(0);
			parent.ellipse(x, y, size-10, size-10);
			parent.fill(255);
		}
		parent.text(name, x, (y-size/2) - 8);
	}
	
	public void clearClick() {
		pressed = false;
	}
}
