

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Legend extends JPanel{

	LinkedList<Country> customCountries;
	Color [] colors;
	int width, height;
	
	public Legend(Color [] c, LinkedList<Country> l, int w, int h){
		this.colors = c;
		this.customCountries = l;
		this.width = w;
		this.height = h;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 12));
		String legendLabel = "Legend";
		g2.drawString(legendLabel,15, 15);
		
		int colorCount = 0;
	
		Node<Country> walker = customCountries.getFirst();
		String name;
		while(walker.hasNext()){
			name = walker.getData().getName();
			g2.setColor(colors[colorCount]);
			colorCount++;
			g2.drawString(name, (width/2), 15*(colorCount+1));
			g2.fillOval((width/2), 15*(colorCount+1), 6, 6);
			walker = walker.getNext();
		}
		name = walker.getData().getName();
		g2.setColor(colors[colorCount]);
		g2.drawString(name, (width/2), 15);
		g2.fillOval((width/2), 15, 6, 6);
	}
}
