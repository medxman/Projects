import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class welcome implements ActionListener{

	JFrame main;
	JButton custom;
	JButton random;
	
	public welcome(){
		main = new JFrame("Welcome");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setSize(300, 300);
		main.setLayout(new FlowLayout());
		
		custom= new JButton("Custom");
		custom.addActionListener(this);
		main.add(custom);
		
		random = new JButton("Random");
		random.addActionListener(this);
		main.add(random);
		
		main.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getSource() == custom){
			application2 app2 = new application2();
		} else if(a.getSource() == random){
			application app = new application();
		}


		
	}
}
