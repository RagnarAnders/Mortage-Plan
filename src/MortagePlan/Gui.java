package MortagePlan;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Gui extends JFrame{

	private JTextField [] textList;
	private Writer writer;
	
	public static void main(String[] args) {
		new Gui();
	}
	
	public Gui() {
		JPanel center = new JPanel();
		textList = new JTextField[4];
		JLabel [] jList = {new JLabel("Name: "), new JLabel("Loan: "), new JLabel("Interest: "), new JLabel("Year: ")};
		GridBagConstraints c = new GridBagConstraints();
		center.setLayout(new GridBagLayout());
		writer = new Writer();
		c.fill = GridBagConstraints.HORIZONTAL;
		for(int i = 0; i < textList.length; i++) {
			c.gridx = 1;
			c.gridy = i;
			textList[i] = new JTextField(10);
			center.add(textList[i], c);
		}
		
		for(int i = 0; i < jList.length; i++) {
			c.gridx = 0;
			c.gridy = i;
			center.add(jList[i], c);
		}

		JButton addCustomer = new JButton("Addera");
		JButton createList = new JButton("Skapa lista");
		JButton readList = new JButton("Läs skapad lista");
		
		c.gridx = 1;
		c.gridy = 4;
		center.add(addCustomer, c);
		
		c.gridx = 0;
		c.gridy = 4;
		center.add(createList, c);
		
		c.gridx = 0;
		c.gridy = 5;
		center.add(readList, c);
		add(center, BorderLayout.CENTER);
		
		addCustomer.addActionListener(new AddCustomer());
		createList.addActionListener(new CreateCustomerList());
		readList.addActionListener(new ReadFile());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		setTitle("Mortage Plan");
		setSize(300,200); 
		setVisible(true);
	}
	
	/**
	 * This Inner class takes the information from the GUI fields, creates a
	 * customer and adds it to the list
	 * 
	 * @author Anders Ragnar
	 *
	 */
	class AddCustomer implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				Integer[] values = new Integer[textList.length-1];
				for(int i = 1; i < textList.length; i++) {
					values[i-1] = Integer.parseInt(textList[i].getText());
					textList[i].setText("");
				}
				writer.addCustomer(textList[0].getText(), values[0], values[1],values[2]);
				textList[0].setText("");
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(Gui.this, "Fel indata!");
			}	
		}	
	}
	
	class CreateCustomerList implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			writer.writeCustomers();
		}
		
		
	}
	
	/**
	 * An action listener which adds customers from a file
	 * to the customer list.
	 * @author Anders Ragnar
	 *
	 */
	class ReadFile implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
		 writer.saveCustomers();
		}
		
	}

}
