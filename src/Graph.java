

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Graph extends JPanel{

	CSVReader parser;
	String [] countryNames;
	int [] yearLabels;
	double [][] parsedTable;
	
	LinkedList<Country> customList;
	
	Color [] colors = {Color.BLUE, Color.RED,Color.YELLOW, Color.CYAN,Color.ORANGE, Color.GREEN,Color.MAGENTA, Color.PINK};
	int width,height;
	
	public Graph(int width, int height, LinkedList<Country> customList){
		//sets the data up
		final String FILENAME = "resources/cellular.csv";		
		parser = new CSVReader(FILENAME);
		countryNames = parser.getCountryNames();
		yearLabels = parser.getYearLabels();
		parsedTable = parser.getParsedTable();	
		
		//sets dimensions
		this.width = width;
		this.height = height;
		
		//creates a bank list
		this.customList = customList;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		double maxValue = 175.0;
		double xScale = width / maxValue;
		
		double yValue = 12;
		double yScale = height / 12;

		//Now must go through the list and plot each Country
		Node<Country> walker = customList.getFirst();
		int colorCount = 0;
		if(walker != null){
			while (walker.hasNext()){
				Node<SubscriptionYear> plotter = walker.getData().getSubs().getFirst();
				//Skips to year 2000
				for (int m=0;m<40;m++){
					plotter = plotter.getNext();
				}
				double x,y;
				int counter = 12;
				while(plotter.hasNext()){
					g2.setColor(colors[colorCount]);
					x = (plotter.getData().getSubscriptions())*xScale;
					y = counter*yScale;
					g2.fillOval((int)x, (int)y, 6, 6);
					g2.setFont(new Font("TimesRoman", Font.PLAIN, 6));
					String label = "(" +  plotter.getData().getYear() + "," + plotter.getData().getSubscriptions() + ")";
					g2.drawString(label, (int)x, (int)y);
					plotter = plotter.getNext();
					counter--;
				}
				walker = walker.getNext();
				colorCount++;
			}
			Node<SubscriptionYear> plotter = walker.getData().getSubs().getFirst();
			//Skips to year 2000
			for (int m=0;m<40;m++){
				plotter = plotter.getNext();
			}
			double x,y;
			int counter = 12;
			while(plotter.hasNext()){
				g2.setColor(colors[colorCount]);
				x = (plotter.getData().getSubscriptions())*xScale;
				y = counter*yScale;
				g2.fillOval((int)x, (int)y, 6, 6);
				g2.setFont(new Font("TimesRoman", Font.PLAIN, 6));
				String label = "(" +  plotter.getData().getYear() + "," + plotter.getData().getSubscriptions() + ")";
				g2.drawString(label, (int)x, (int)y);
				plotter = plotter.getNext();
				counter--;
			}
		}
	}
	
	public String [] getCountryName(){
		return countryNames;
	}

	public double[][] getParsedTable() {
		return parsedTable;
	}

	public int[] getYearTable() {
		return yearLabels;
	}

	public Color[] getColors() {
		return colors;
	}
	
	public LinkedList<Country> getList(){
		return customList;
	}
	
	
	
}
