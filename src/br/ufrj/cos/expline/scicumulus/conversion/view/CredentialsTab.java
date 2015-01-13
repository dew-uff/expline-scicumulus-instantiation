package br.ufrj.cos.expline.scicumulus.conversion.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CredentialsTab extends JPanel
{
	
	private JLabel labelAccessKey;
	private JTextField textFieldAccessKey;
	private JLabel labelSecretAccessKey;
	private JTextField textFieldSecretAccessKey;
	
	public CredentialsTab()
	{
		super();
		
		initComponents();
		
		this.setVisible(true);
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		
		labelAccessKey = new JLabel("Access Key: ");
		labelAccessKey.setLocation(10, 10);
		
		textFieldAccessKey = new JTextField(20);
		textFieldAccessKey.setLocation(labelAccessKey.getWidth() + 10, 10);
		
		labelSecretAccessKey = new JLabel("Secret Access Key: ");
		labelSecretAccessKey.setLocation(10,labelAccessKey.getY() + labelAccessKey.getHeight() + 10);
		
		textFieldSecretAccessKey = new JTextField(20);
		textFieldSecretAccessKey.setLocation(labelSecretAccessKey.getWidth()+10,labelAccessKey.getY() + labelAccessKey.getHeight() + 10);
		
		this.add(labelAccessKey);
		this.add(textFieldAccessKey);
		this.add(labelSecretAccessKey);
		this.add(textFieldSecretAccessKey);
		
	}
	
}
