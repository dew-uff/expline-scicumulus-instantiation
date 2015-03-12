package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.awt.GridLayout;

import javax.swing.GroupLayout;
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
		
		initLayout();
	}

	private void initLayout() {
		// TODO Auto-generated method stub
		GroupLayout layout = new GroupLayout(this);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(100,100)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(lbAccessKey)
								.addComponent(lbSecretAccessKey)
						)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(tfAccessKey)
								.addComponent(tfSecretAccessKey)
						)
						.addContainerGap(100,100)
				)
		);
		
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbAccessKey)
								.addComponent(tfAccessKey)
						)
						.addContainerGap(15,15)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbSecretAccessKey)
								.addComponent(tfSecretAccessKey)
						)
						.addContainerGap()
				)
		);
		
		this.setLayout(layout);
	}

	private void initComponents() {
//		this.setLayout(new GridLayout(2,2));
		this.setLayout(null);
		
		lbAccessKey = new JLabel("Access Key: ");
//		lbAccessKey.setBounds(10, 10, 80, 15);
		
		tfAccessKey = new JTextField(20);
//		tfAccessKey.setBounds(10+lbAccessKey.getWidth(), 10,390, 20);
		
		lbSecretAccessKey = new JLabel("Secret Access Key: ");
//		lbSecretAccessKey.setBounds(10, 10+lbAccessKey.getHeight()+lbAccessKey.getY(), 125, 15);
		
		tfSecretAccessKey = new JTextField(20);
//		tfSecretAccessKey.setBounds(10 + lbSecretAccessKey.getWidth(), lbSecretAccessKey.getY(), 345, 20);
		
//		this.add(lbAccessKey);
//		this.add(tfAccessKey);
//		this.add(lbSecretAccessKey);
//		this.add(tfSecretAccessKey);
		
	}
	
	public boolean checkFilledOut()
	{
		if(tfAccessKey.getText().equals(""))
		{
			System.out.println("Credentials");
			return false;	
		}
		else if(tfSecretAccessKey.getText().equals(""))
		{
			System.out.println("Credentials");
			return false;
		}
			
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
