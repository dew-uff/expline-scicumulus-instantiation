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
		
		textFieldAccessKey = new JTextField();
		textFieldAccessKey.setSize(100, 10);
		
		labelSecretAccessKey = new JLabel("Secret Access Key: ");
		
		textFieldSecretAccessKey = new JTextField();
		textFieldSecretAccessKey.setSize(100, 10);
		
		this.add(labelAccessKey);
		this.add(textFieldAccessKey);
		this.add(labelSecretAccessKey);
		this.add(textFieldSecretAccessKey);
	}
	
}
