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
		this.setLayout(null);
		
		labelAccessKey = new JLabel("Access Key: ");
		labelAccessKey.setBounds(10, 10, 80, 15);
		
		textFieldAccessKey = new JTextField(20);
		textFieldAccessKey.setBounds(10+labelAccessKey.getWidth(), 10,390, 20);
		
		labelSecretAccessKey = new JLabel("Secret Access Key: ");
		labelSecretAccessKey.setBounds(10, 10+labelAccessKey.getHeight()+labelAccessKey.getY(), 125, 15);
		
		textFieldSecretAccessKey = new JTextField(20);
		textFieldSecretAccessKey.setBounds(10 + labelSecretAccessKey.getWidth(), labelSecretAccessKey.getY(), 345, 20);
		
		this.add(labelAccessKey);
		this.add(textFieldAccessKey);
		this.add(labelSecretAccessKey);
		this.add(textFieldSecretAccessKey);
		
	}
	
}
