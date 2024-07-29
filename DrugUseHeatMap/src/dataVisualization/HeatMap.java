package dataVisualization;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PImage;

public class HeatMap extends PApplet{
	
	private PImage codedMap;
	private Map<String, Integer> stateColors;
	private Map<Integer, String> colorStates;
	private int year;
	private File data;
	private Slider slider;
	private Button[] ageButtons;
	
	private final int BOTTOM_PIXEL = 1251;
	private final int RIGHT_PIXEL = 1900;
	
	public static void main(String[] args) {
		PApplet.main("dataVisualization.HeatMap");
	}
	
	public void settings() {
		fullScreen();
	}
	
	public void setup() {
		year = 2002;
		data = new File("data/drugs.csv");
		slider = new Slider(this, 1500, 960, 400, 100, "Year", 2002, 2018);
		slider.setValue(2002);
		ageButtons = new Button[4];
		ageButtons[0] = new Button(this, 1600, 100, 50, "Ages 12-17", "Circle", "12-17", true);
		ageButtons[1] = new Button(this, 1600, 250, 50, "Ages 18-25", "Circle", "18-25", false);
		ageButtons[2] = new Button(this, 1600, 400, 50, "Ages 26+", "Circle", "26+", false);
		ageButtons[3] = new Button(this, 1800, 250, 50, "All Ages", "Circle", "All", false);
		codedMap = loadImage("images/codedmap.png");
		stateColors = createHashMap1();
		colorStates = createHashMap2();
		try {
			scanMap();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		codedMap.resize(1500, 995);
	}
	
	public void draw() {
		background(144, 215, 253);
		image(codedMap, 0, 0);	
		text(mouseX, 50, 50);
		text(mouseY, 50, 100);
		
		if(year != slider.getValue() && !mousePressed) {
			loadMap();
		}
		for(Button button : ageButtons) {
			if(button != null)
				button.draw();
		}
		slider.draw();
		line(1480, 0, 1480, 1080);
	}
	
	public void loadMap() {
		codedMap = loadImage("images/codedmap.png");
		year = slider.getValue();
		try {
			scanMap();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		codedMap.resize(1500, 995);
	}
	
	public void mouseClicked() {
		for(Button button : ageButtons) {
			if(button != null) {
				button.mouseClicked();
			}
		}
	}
	
	public void clearButtons() {
		for(Button button : ageButtons) {
			if(button != null) {
				button.clearClick();
			}
		}
	}
	
	public Map<String, Integer> createHashMap1() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Alabama", color(255, 255, 255));
		map.put("Alaska", color(254, 255, 255));
		map.put("Arizona", color(254, 254, 255));
		map.put("Arkansas", color(254, 254, 254));
		map.put("California", color(253, 254, 254));
		map.put("Colorado", color(253, 253, 254));
		map.put("Connecticut", color(253, 253, 253));
		map.put("Delaware", color(252, 253, 253));
		map.put("Florida", color(252, 252, 253));
		map.put("Georgia", color(252, 252, 252));
		map.put("Hawaii", color(251, 252, 252));
		map.put("Idaho", color(251, 251, 252));
		map.put("Illinois", color(251, 251, 251));
		map.put("Indiana", color(250, 251, 251));
		map.put("Iowa", color(250, 250, 251));
		map.put("Kansas", color(250, 250, 250));
		map.put("Kentucky", color(249, 250, 250));
		map.put("Louisiana", color(249, 249, 250));
		map.put("Maine", color(249, 249, 249));
		map.put("Maryland", color(248, 249, 249));
		map.put("Massachusetts", color(248, 248, 249));
		map.put("Michigan", color(248, 248, 248));
		map.put("Minnesota", color(247, 248, 248));
		map.put("Mississippi", color(247, 247, 248));
		map.put("Missouri", color(247, 247, 247));
		map.put("Montana", color(246, 247, 247));
		map.put("Nebraska", color(246, 246, 247));
		map.put("Nevada", color(246, 246, 246));
		map.put("New Hampshire", color(245, 246, 246));
		map.put("New Jersey", color(245, 245, 246));
		map.put("New Mexico", color(245, 245, 245));
		map.put("New York", color(244, 245, 245));
		map.put("North Carolina", color(244, 244, 245));
		map.put("North Dakota", color(244, 244, 244));
		map.put("Ohio", color(243, 244, 244));
		map.put("Oklahoma", color(243, 243, 244));
		map.put("Oregon", color(243, 243, 243));
		map.put("Pennsylvania", color(242, 243, 243));
		map.put("Rhode Island", color(242, 242, 243));
		map.put("South Carolina", color(242, 242, 242));
		map.put("South Dakota", color(241, 242, 242));
		map.put("Tennessee", color(241, 241, 242));
		map.put("Texas", color(241, 241, 241));
		map.put("Utah", color(240, 241, 241));
		map.put("Vermont", color(240, 240, 241));
		map.put("Virginia", color(240, 240, 240));
		map.put("Washington", color(239, 240, 240));
		map.put("West Virginia", color(239, 239, 240));
		map.put("Wisconsin", color(239, 239, 239));
		map.put("Wyoming", color(238, 239, 239));
		return map;
	}
	
	public Map<Integer, String> createHashMap2() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(color(255, 255, 255), "Alabama");
		map.put(color(254, 255, 255), "Alaska");
		map.put(color(254, 254, 255), "Arizona");
		map.put(color(254, 254, 254), "Arkansas");
		map.put(color(253, 254, 254), "California");
		map.put(color(253, 253, 254), "Colorado");
		map.put(color(253, 253, 253), "Connecticut");
		map.put(color(252, 253, 253), "Delaware");
		map.put(color(252, 252, 253), "Florida");
		map.put(color(252, 252, 252), "Georgia");
		map.put(color(251, 252, 252), "Hawaii");
		map.put(color(251, 251, 252), "Idaho");
		map.put(color(251, 251, 251), "Illinois");
		map.put(color(250, 251, 251), "Indiana");
		map.put(color(250, 250, 251), "Iowa");
		map.put(color(250, 250, 250), "Kansas");
		map.put(color(249, 250, 250), "Kentucky");
		map.put(color(249, 249, 250), "Louisiana");
		map.put(color(249, 249, 249), "Maine");
		map.put(color(248, 249, 249), "Maryland");
		map.put(color(248, 248, 249), "Massachusetts");
		map.put(color(248, 248, 248), "Michigan");
		map.put(color(247, 248, 248), "Minnesota");
		map.put(color(247, 247, 248), "Mississippi");
		map.put(color(247, 247, 247), "Missouri");
		map.put(color(246, 247, 247), "Montana");
		map.put(color(246, 246, 247), "Nebraska");
		map.put(color(246, 246, 246), "Nevada");
		map.put(color(245, 246, 246), "New Hampshire");
		map.put(color(245, 245, 246), "New Jersey");
		map.put(color(245, 245, 245), "New Mexico");
		map.put(color(244, 245, 245), "New York");
		map.put(color(244, 244, 245), "North Carolina");
		map.put(color(244, 244, 244), "North Dakota");
		map.put(color(243, 244, 244), "Ohio");
		map.put(color(243, 243, 244), "Oklahoma");
		map.put(color(243, 243, 243), "Oregon");
		map.put(color(242, 243, 243), "Pennsylvania");
		map.put(color(242, 242, 243), "Rhode Island");
		map.put(color(242, 242, 242), "South Carolina");
		map.put(color(241, 242, 242), "South Dakota");
		map.put(color(241, 241, 242), "Tennessee");
		map.put(color(241, 241, 241), "Texas");
		map.put(color(240, 241, 241), "Utah");
		map.put(color(240, 240, 241), "Vermont");
		map.put(color(240, 240, 240), "Virginia");
		map.put(color(239, 240, 240), "Washington");
		map.put(color(239, 239, 240), "West Virginia");
		map.put(color(239, 239, 239), "Wisconsin");
		map.put(color(238, 239, 239), "Wyoming");
		return map;
	}
	
	public void scanMap() throws FileNotFoundException {
		Point start = new Point(110,110);
		String age = "";
		for(Button button : ageButtons) {
			if(button.getValue()) {
				age = button.getType();
			}
		}
		
		
		
		for(int x = start.x; x <= RIGHT_PIXEL; x+=10) {
			for(int y = start.y; y <= BOTTOM_PIXEL; y+=10) {
				int pixel = codedMap.get(x,y);
				if(stateColors.containsValue(pixel)) {
					fillState(x, y, pixel, getHeat(pixel, "Alcohol", age));
				}
			}
		}
		//Hawaii
		int hawaiiHeat = getHeat(stateColors.get("Hawaii"), "Alcohol", age);
		fillState(40, 850, codedMap.get(40, 850), hawaiiHeat);
		fillState(67, 862, codedMap.get(67, 862), hawaiiHeat);
		fillState(95, 875, codedMap.get(95, 875), hawaiiHeat);
		fillState(110, 890, codedMap.get(110, 890), hawaiiHeat);
	}
	
	public int getHeat(int color, String dataType, String ageGroup) throws FileNotFoundException {
		String state = '"' + colorStates.get(color) + '"';
		Scanner sc = new Scanner(data);
		int index = (year - 2002)*50;
		for(int i = 0; i < index; i++) {
			sc.nextLine();
		}

		sc.useDelimiter(",");
		String currentState = sc.next();
		while(!currentState.equals(state)) {
			sc.nextLine();
			currentState = sc.next();
		}
		
		int column = getColumnIndex(dataType, ageGroup);
		String value = "";
		for(int i = 0; i < column; i++) {
			value = sc.next();
		}
		
		value = value.substring(1, value.length() - 1);
		float denominator = 0;
		if(dataType.equals("Alcohol"))
			denominator = 273;
		
		int val = (int) (510*(Float.parseFloat(value) / denominator));
		int heatVal = 0;
		if(val > 255) {
			heatVal = color(255, 255 - (val-255) , 0);
		}else if(val > 200){
			heatVal = color(255, 255, (255-val));
		}else {
			heatVal = color(255, 255, (int) (255-val/1.5));
		}
		
		sc.close();
		return heatVal;
	}
	
	public int getColumnIndex(String dataType, String ageGroup) {
		int column = 0;
		if(dataType.equals("Alcohol")) {
			if(ageGroup.equals("12-17")) {
				column = 8;
			}else if(ageGroup.equals("18-25")) {
				column = 9;
			}else if(ageGroup.equals("26+")) {
				column = 10;
			}
		}
		return column;
	}
	
	public void fillState(int x, int y, int curColor, int fillColor) {
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(x, y));
		while(q.size() > 0) {
			Point cur = q.remove();
			int curx = cur.x;
			int cury = cur.y;
			int pixel = codedMap.get(curx, cury);
			if(pixel != color(0) && pixel != fillColor && pixel == curColor && curx >= 0 && curx <= codedMap.width && cury >= 0 && cury <= codedMap.height) {
				codedMap.set(curx, cury, fillColor);
				q.add(new Point(curx-1, cury));
				q.add(new Point(curx+1, cury));
				q.add(new Point(curx, cury-1));
				q.add(new Point(curx, cury+1));
			}
		}
	}
}
