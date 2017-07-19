package br.ufrj.cos.expline.scicumulus.conversion.view;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.Environment;

public class EnvironmentTab extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbClusterName;
	private JTextField tfClusterName;	
	private JLabel lbType;
	private JTextField tfType;
	private JLabel lbVerbose;
	private JTextField tfVerbose;
//	private GridBagLayout layout;
//	private GridBagConstraints constraints;
	
	private Environment environ;
	
	public EnvironmentTab(Environment envi)
	{
		super();
		
		this.environ = envi;
		
		initComponents();
		
		this.setVisible(true);
		
		initLayout();
	}

	private void initComponents() {
		// TODO Auto-generated method stub

//		this.setLayout(null);
//		this.setLayout(layout);

		
		lbClusterName = new JLabel("Cluster Name: ");
//		lbClusterName.setBounds(10,10,95,15);
//		this.add(lbClusterName);
		
		tfClusterName = new JTextField();
//		tfClusterName.setText("vitor");
//		tfClusterName.setEnabled(false);
//		tfClusterName.setBounds(lbClusterName.getWidth(), 10, 385, 20);
//		this.add(tfClusterName);
		
		lbType = new JLabel("Type: ");
//		lbType.setBounds(10,10+lbClusterName.getHeight()+lbClusterName.getY(),45,15);
//		this.add(lbType);
		
		tfType = new JTextField();
		tfType.setText("CLOUD");
//		tfType.setEnabled(false);
//		tfType.setBounds(lbType.getWidth(), lbType.getY(), 435, 20);
//		this.add(tfType);
		
		lbVerbose = new JLabel("Verbose: ");
//		lbVerbose.setBounds(10,10+lbType.getHeight()+lbType.getY(),65,15);
//		this.add(lbVerbose);
		
		tfVerbose = new JTextField();
		tfVerbose.setText("false");
//		tfVerbose.setEnabled(false);
//		tfVerbose.setBounds(lbVerbose.getWidth(), lbVerbose.getY(), 415, 20);
//		this.add(tfVerbose);
		
		

	}
	
	private void initLayout()
	{
		GroupLayout layout = new GroupLayout(this);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(100,100)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(lbClusterName)
								.addGap(15)
								.addComponent(lbType)
								.addGap(15)
								.addComponent(lbVerbose)
								.addGap(15)
						)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(tfClusterName)
								.addGap(15)
								.addComponent(tfType)
								.addGap(15)
								.addComponent(tfVerbose)								
						)
						.addContainerGap(100,100)
				)
		);
		
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbClusterName)
								.addComponent(tfClusterName)
						)
						.addContainerGap(15,15)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbType)
								.addComponent(tfType)
						)
						.addContainerGap(15,15)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbVerbose)
								.addComponent(tfVerbose)
						)
						.addContainerGap()
				)
		);
		
		this.setLayout(layout);
	}
	
	public boolean checkFilledOut()
	{
		
		if(tfClusterName.getText().equals(""))
		{
			System.out.println("Enviroment");
			return false;	
		}
		else if(tfType.getText().equals(""))
		{
			System.out.println("Enviroment");
			return false;
		}
		else if(tfVerbose.getText().equals(""))
		{
			System.out.println("Enviroment");
			return false;
		}
		
		return true;
	}
	
	public String getClusterName()
	{
		return tfClusterName.getText();
	}
	
	public String getType()
	{
		return tfType.getText();
	}
	
	public String getVerbose()
	{
		return tfVerbose.getText();
	}

	public void fillOut() {
		this.environ.setClusterName(getClusterName());
		this.environ.setType(getType());
		this.environ.setVerbose(getVerbose());
	}
}
