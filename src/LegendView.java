
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class LegendView extends JPanel{

	Color [] colorsForCountry;
	Country [] countries;
	int width;
	int height;
	
	public LegendView(int w,int h,Country [] c, Color[] colors){
		this.width = w;
		this.height = h;
		this.colorsForCountry = colors;
		this.countries = c;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 12));
		String legendLabel = "Legend";
		g2.drawString(legendLabel, (width/2), 20);
	
		for (int i=0; i<colorsForCountry.length;i++){
			g2.setColor(colorsForCountry[i]);
			g2.drawString(countries[i].getName(), (width/2), (40+(20*i)));
			g2.fillOval(((width/2)-30),(40+(15*i)),6,6);
		}
		
	}	
}
