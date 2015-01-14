package br.ufrj.cos.expline.scicumulus.conversion.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CredentialsTab extends JPanel
{
	
	private JLabel lbAccessKey;
	private JTextField tfAccessKey;
	private JLabel lbSecretAccessKey;
	private JTextField tfSecretAccessKey;
	
	public CredentialsTab()
	{
		super();
		
		initComponents();
		
		this.setVisible(true);
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		
		lbAccessKey = new JLabel("Access Key: ");
		lbAccessKey.setBounds(10, 10, 80, 15);
		
		tfAccessKey = new JTextField(20);
		tfAccessKey.setBounds(10+lbAccessKey.getWidth(), 10,390, 20);
		
		lbSecretAccessKey = new JLabel("Secret Access Key: ");
		lbSecretAccessKey.setBounds(10, 10+lbAccessKey.getHeight()+lbAccessKey.getY(), 125, 15);
		
		tfSecretAccessKey = new JTextField(20);
		tfSecretAccessKey.setBounds(10 + lbSecretAccessKey.getWidth(), lbSecretAccessKey.getY(), 345, 20);
		
		this.add(lbAccessKey);
		this.add(tfAccessKey);
		this.add(lbSecretAccessKey);
		this.add(tfSecretAccessKey);
		
	}
	
	public boolean checkFilledOut()
	{
		if(tfAccessKey.getText().equals("")) return false;	
		else if(tfSecretAccessKey.getText().equals("")) return false;
			
		return true;
		
	}
	
	public String getAccessKey()
	{
		return tfAccessKey.getText();
	}
	
	public String getSecretAccessKey()
	{
		return tfSecretAccessKey.getText();
	}
	
}
