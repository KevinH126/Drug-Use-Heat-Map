package dataVisualization;

import processing.core.PApplet;
import processing.core.PImage;

public class BlackAndWhite extends PApplet {

	PImage original;
	PImage bw;
	
	public static void main(String[] args) {
		PApplet.main("dataVisualization.BlackAndWhite");
	}
	
	
	public void setup() {
		original = loadImage("images/usmapcolored.png");
		encode();
		original.save("images/black-and-white-u-s-map.png");
	}
	
	public void settings() {
		size(2000, 1326);
	}
	
	
	public void draw() {
		image(original, 0, 0);
	}
	
	public void encode() {
		int threshold = 120;
		
		for(int x = 0; x < original.width; x++) {
            for(int y = 0; y < original.height; y++){
            	int pixel = original.get(x, y);
            	
            	if(red(pixel) < threshold && green(pixel) < threshold && blue(pixel) < threshold) {
            		original.set(x, y, color(0));
            	}else {
            		original.set(x, y, color(255));
            	}
            	
            }
        }
	}

}
