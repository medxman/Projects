

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class application2 implements ActionListener{
	
	JFrame frame;
	Graph graph;
	JPanel legend;
	JTextField userInput;
	JButton add, remove, years;
	GridBagConstraints GBC;
	
	LinkedList<Country> customCountries;
	
	public application2(){
		//Frame
		frame = new JFrame("Custom Graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 500);
		frame.setLayout(new FlowLayout());
		GBC = new GridBagConstraints();
		
		//Text Field
		userInput = new JTextField(20);
		userInput.setMinimumSize(new Dimension(200,20));
		frame.add(userInput);
		
		//Add Button
		add = new JButton("Add");
		add.addActionListener(this);
		frame.add(add);

		//Legend
		legend = new JPanel();
		legend.setBackground(Color.WHITE);
		legend.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		legend.setPreferredSize(new Dimension(200,200));
		legend.setMinimumSize(new Dimension(200,200));
		frame.add(legend);
		
		//Graph
		graph = new Graph(400,400,new LinkedList<Country>());
		graph.setBackground(Color.WHITE);
		graph.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		graph.setPreferredSize(new Dimension(400,400));
		graph.setMinimumSize(new Dimension(400,400));
		frame.add(graph);
				
		customCountries = new LinkedList<Country>();
		
		frame.setVisible(true);;
	}
	
	public void actionPerformed(ActionEvent e){
		frame.remove(graph);
		frame.remove(legend);
		//first checks to make sure country is in the table
		String [] countriesList = graph.getCountryName();
		double [][] parsedTable = graph.getParsedTable();
		int [] years = graph.getYearTable();
		int counter = 0;
		for(int i=0;i<countriesList.length;i++){
			if(userInput.getText().equals(countriesList[i])){
				userInput.setText("Country " +countriesList[i]+ " found!");
				Country newCountry = new Country(countriesList[i]);
				customCountries.add(newCountry);
				for(int j=0;j<parsedTable[i].length;j++){
					newCountry.addSubscriptionYear(years[j],parsedTable[i][j]);
					counter ++;
			}
		}
		}
		if (counter != 0){
			System.out.println(customCountries);
			graph = new Graph(400,400, customCountries);
			graph.setBackground(Color.WHITE);
			graph.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			graph.setPreferredSize(new Dimension(400,400));
			graph.setMinimumSize(new Dimension(400,400));
			graph.repaint();
			graph.validate();
			
			legend = new Legend(graph.getColors(),graph.getList(),200,200);		legend.setBackground(Color.WHITE);
			legend.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			legend.setPreferredSize(new Dimension(200,200));
			legend.setMinimumSize(new Dimension(200,200));
			legend.repaint();
			legend.validate();
			
			frame.add(legend);
			frame.add(graph);
			frame.repaint();
			frame.validate();
		}
	}
	

}
