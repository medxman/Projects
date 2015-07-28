

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class application  implements ActionListener{
	
	JTextField field;
	String message;
	
	LegendView myLegend;
	GraphView myPlots;
	JFrame frame;
	
	// Mouse Listener
	JTextField messageMouseLisener;
	String messageML;
	
	
	Country [] countries;
	
	int counter;
	
	int FRAME_WIDTH = 800;
	int FRAME_HEIGHT = 600;
	
	CSVReader parser;
	String [] countryNames;
	int [] yearLabels;
	double [][] parsedTable;
	
	GridBagLayout gbl;
	GridBagConstraints gbc;
	
	public application(){
		final String FILENAME = "resources/cellular.csv";			

		parser = new CSVReader(FILENAME);

		countryNames = parser.getCountryNames();
		yearLabels = parser.getYearLabels();
		parsedTable = parser.getParsedTable();		

		Random gen = new Random();
		int rand;
		
		countries = new Country[5];
		for (int i=0;i<countries.length;i++){
			rand = gen.nextInt(countryNames.length);
			countries[i] = new Country(countryNames[rand]);
			for(int j=0;j<yearLabels.length;j++){
				countries[i].addSubscriptionYear(yearLabels[j], parsedTable[rand][j]);
			}
		}
		counter = 5;
				
		frame = new JFrame("Cellular Graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		frame.setLayout(gbl);
		
		myPlots = new GraphView((FRAME_WIDTH-200), (FRAME_HEIGHT-200), countries);
		myPlots.setBackground(Color.WHITE);
		myPlots.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		myPlots.setPreferredSize(new Dimension((FRAME_WIDTH-200),FRAME_HEIGHT-200));
		myPlots.addMouseListener(new HandlerClass());
		gbc.gridx = 20;
		gbc.gridy = 180;
		gbc.gridheight = 600;
		gbc.gridwidth = 600;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbl.setConstraints(myPlots, gbc);
		frame.add(myPlots);
	
		myLegend = new LegendView(125, 300, myPlots.getCountries(), myPlots.getColor());
		myLegend.setBackground(Color.WHITE);
		myLegend.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		myLegend.setPreferredSize(new Dimension(125,(FRAME_HEIGHT-200)));
		gbc.gridx = 650;
		gbc.gridy = 180;
		gbc.gridheight = 300;
		gbc.gridwidth = 125;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbl.setConstraints(myLegend, gbc);
		frame.add(myLegend);
		
		JButton test = new JButton("Randomize Countries");
		test.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 25;
		gbc.gridwidth = 200;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbl.setConstraints(test, gbc);
		frame.add(test);
		
		/*
		JButton clear = new JButton("Clear");
		test.addActionListener(this);
		gbc.gridx = 800;
		gbc.gridy = 600;
		gbc.gridheight = 10;
		gbc.gridwidth = 5;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbl.setConstraints(test, gbc);
		frame.add(test);
		
		/*
		JButton add = new JButton("Add");
		test.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 25;
		gbc.gridwidth = 100;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbl.setConstraints(test, gbc);
		frame.add(test);
		*/
		
		message = "";
		field = new JTextField(message, 15);
		gbc.gridx = 50;
		gbc.gridy = 30;
		gbc.gridheight = 10;
		gbc.gridwidth = 10;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbl.setConstraints(field, gbc);
		frame.add(field);
		
		//Mouse Listener
		messageML = " ";
		messageMouseLisener = new JTextField(messageML,15);
		gbc.gridx = 60;
		gbc.gridy = 30;
		gbc.gridheight = 10;
		gbc.gridwidth = 15;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbl.setConstraints(messageMouseLisener, gbc);
		frame.add(messageMouseLisener);
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.remove(myPlots);
		frame.remove(myLegend);
		//frame.validate();
		//frame.repaint();
		
		message = "Random 5!";
		field.setText(message);
		System.out.println("New Countries Test");
		
		Random gen = new Random();
		int rand;
		Country [] newCountries = new Country[counter];
		for (int i=0;i<newCountries.length;i++){
			rand = gen.nextInt(countryNames.length);
			newCountries[i] = new Country(countryNames[rand]);
			for(int j=0;j<yearLabels.length;j++){
				newCountries[i].addSubscriptionYear(yearLabels[j], parsedTable[rand][j]);
			}
			System.out.println("New Country:" + newCountries[i].getName());
		}
		
		GraphView temp = new GraphView((FRAME_WIDTH-200), (FRAME_HEIGHT-200), newCountries);
		
		temp.setBackground(Color.WHITE);
		temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		temp.setPreferredSize(new Dimension((FRAME_WIDTH-200),FRAME_HEIGHT-200));
		myPlots = temp;
		myPlots.addMouseListener(new HandlerClass());
		gbc.gridx = 20;
		gbc.gridy = 180;
		gbc.gridheight = 600;
		gbc.gridwidth = 600;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbl.setConstraints(temp, gbc);
		frame.add(temp);
		
		LegendView temp2 = new LegendView(125, 300, myPlots.getCountries(), myPlots.getColor());
		temp2.setBackground(Color.WHITE);
		temp2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		temp2.setPreferredSize(new Dimension(125,(FRAME_HEIGHT-200)));
		myLegend = temp2;
		
		gbc.gridx = 650;
		gbc.gridy = 180;
		gbc.gridheight = 300;
		gbc.gridwidth = 125;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbl.setConstraints(myLegend, gbc);
		frame.add(myLegend);
		
		
		frame.validate();
		frame.repaint();
		
		myPlots = temp;
	}
	
	
	private class HandlerClass implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			
			int xC = e.getX();
			int yC = e.getY();
			String test = "";
			test += xC;
			test += ",";
			test += yC;

			String [][] xyCords = myPlots.getCords();
			
			for(int i=0;i<5;i++){
				for (int j=0;j<xyCords[i].length;j++){
					System.out.println(xyCords[i][j] + "  ");
					if(test.equals(xyCords[i][j])){
						messageMouseLisener.setText(xyCords[i][0]);
						return;
					} 
				}
			}
			messageMouseLisener.setText(String.format("no country at %d %d", e.getX(), e.getY()));
		}

		public void mouseEntered(MouseEvent e) {
			myPlots.setBackground(Color.BLUE);
		}

		public void mouseExited(MouseEvent e) {
			myPlots.setBackground(Color.WHITE);
		}

		public void mousePressed(MouseEvent e) {
			//Nothing
		}

		public void mouseReleased(MouseEvent e) {
			//Nothing
		}
		
	}
	
	
}
