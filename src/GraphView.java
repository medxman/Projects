
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class GraphView extends JPanel{

	int width,height; //variables for the width and height of the JPanel
	double plottedXmin, plottedXmax, plottedYmin, plottedYmax; //used to map data point to the drawing area
	Font font; //use to set the font of Graphics Object
	Country [] data;
	Color [] colorData;
	
	//ML 
	String [][] cords;
	
	
	//constructor that takes in width, height, and 1-d array of type countries, sets up the instance variables
	public GraphView(int width, int height, Country[] countries){
		font = new Font("Serif", Font.PLAIN, 11);
		colorData = new Color[countries.length];
		this.width = width;
		this.height = height;
		this.data = countries;	
		//sets up the plotted min/max variables
		this.plottedXmax = 0;
		this.plottedXmin = 1000;
		this.plottedYmin = 2013;
		this.plottedYmax = 0;
		//goes through countries and check's max/mins to current max and mins
		for(int i=0;i<countries.length;i++){
			if (countries[i].getMaxYear() > plottedYmax){
				plottedYmax = countries[i].getMaxYear();
			}
			if (countries[i].getMinYear() < plottedYmin){
				plottedYmin = countries[i].getMinYear();
			}
			if (countries[i].getMaxData() > plottedXmax){
				plottedXmax = countries[i].getMaxData();
			}
			if (countries[i].getMinData() < plottedYmax){
				plottedXmin = countries[i].getMinData();
			}
		}
	}
	
	//method which takes in object of type Graphics
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		cords = new String[5][15];
		
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 12));
		String graphLabel = "Graph for ";
		for(int i=0;i<data.length;i++){
			graphLabel += data[i].getName();
			if(i != (data.length-1)){
				graphLabel += ", ";
			}			
		}
		g2.drawString(graphLabel, 10, 10);

		double totalYs = (plottedYmax - plottedYmin) - 40; // = 12		
		
		for(int i=0;i<data.length;i++){
			int counter = (int)totalYs;
			int c = 0;
			Node<SubscriptionYear> walker = data[i].getSubs().getFirst();

			for (int m=0;m<40;m++){
				walker = walker.getNext();
			}
	
			Color rand = this.getRandomColor();
			g2.setColor(rand);
			colorData[i] = rand;
			double x,y;
			cords[i][c] = data[i].getName();
			while(walker.hasNext()){
				x = ((walker.getData().getSubscriptions() / plottedXmax) * width);
				y = ((height/totalYs)*counter);
				
				// **** colorData[i] = rand;
				g2.fillOval((int)x, (int)y, 6, 6);
				g2.setFont(new Font("TimesRoman", Font.PLAIN, 6));
				String label = "(" +  walker.getData().getYear() + "," + walker.getData().getSubscriptions() + ")";
				g2.drawString(label, (int)x, (int)y);
				walker = walker.getNext();
				counter--;
				c++;
				String msg = "";
				msg += (int)x;
				msg += ",";
				msg += (int)y;
				cords[i][c] = msg;
			}
		}		
	}
	
	public Country [] getCountries(){
		return data;
	}
	
	public Color [] getColor(){
		return colorData;
	}
	
	public Color getRandomColor() {
		Random numGen = new Random();
		return new Color(numGen.nextInt(256), numGen.nextInt(256), numGen.nextInt(256));
	}
	
	public String[][] getCords(){
		return cords;
	}	
}
