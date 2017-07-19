package br.ufrj.cos.expline.scicumulus.conversion.view;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.Binary;

public class BinaryTab extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbDirectory;
	private JTextField tfDirectory;	
	private JLabel lbConceptualVersion;
	private JTextField tfConceptualVersion;
	private JLabel lbExecutionVersion;
	private JTextField tfExecutionVersion;
	private JLabel lbStarterVersion;
	private JTextField tfStarterVersion;
	private JLabel lbQueryVersion;
	private JTextField tfQueryVersion;
	private Binary binary;
	
	public BinaryTab(Binary binary)
	{
		super();
		
		this.binary = binary;
		
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
								.addComponent(lbDirectory)
								.addComponent(lbConceptualVersion)
								.addComponent(lbExecutionVersion)
								.addComponent(lbStarterVersion)
								.addComponent(lbQueryVersion)
						)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(tfDirectory)
								.addComponent(tfConceptualVersion)
								.addComponent(tfExecutionVersion)
								.addComponent(tfStarterVersion)
								.addComponent(tfQueryVersion)
						)
						.addContainerGap(100,100)
				)
		);
		
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbDirectory)
								.addComponent(tfDirectory)
						)
						.addContainerGap(15,15)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbConceptualVersion)
								.addComponent(tfConceptualVersion)
						)
						.addContainerGap(15,15)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbExecutionVersion)
								.addComponent(tfExecutionVersion)
						)
						.addContainerGap(15,15)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbStarterVersion)
								.addComponent(tfStarterVersion)
						)
						.addContainerGap(15,15)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbQueryVersion)
								.addComponent(tfQueryVersion)
						)
						.addContainerGap()
				)
		);
		
		this.setLayout(layout);
	}

	private void initComponents() {
		// TODO Auto-generated method stub
//		this.setLayout(new GridLayout(5,2));
//		this.setLayout(null);
		
		lbDirectory = new JLabel("Directory: ");
//		lbDirectory.setBounds(10,10,70,15);
//		this.add(lbDirectory);
		
		tfDirectory = new JTextField();
//		tfDirectory.setBounds(lbDirectory.getWidth(), 10, 410, 20);
//		this.add(tfDirectory);
		
		lbConceptualVersion = new JLabel("Conceptual Version: ");
//		lbConceptualVersion.setBounds(10,10+lbDirectory.getHeight()+lbDirectory.getY(),130,15);
//		this.add(lbConceptualVersion);
		
		tfConceptualVersion = new JTextField();
//		tfConceptualVersion.setBounds(lbConceptualVersion.getWidth(), lbConceptualVersion.getY(), 350, 20);
//		this.add(tfConceptualVersion);
		
		lbExecutionVersion = new JLabel("Execution Version: ");
//		lbExecutionVersion.setBounds(10,10+lbConceptualVersion.getHeight()+lbConceptualVersion.getY(),120,15);
//		this.add(lbExecutionVersion);
		
		tfExecutionVersion = new JTextField();
//		tfExecutionVersion.setBounds(lbExecutionVersion.getWidth(), lbExecutionVersion.getY(), 360, 20);
//		this.add(tfExecutionVersion);
		
		lbStarterVersion = new JLabel("Starter Version: ");
//		lbStarterVersion.setBounds(10,10+lbExecutionVersion.getHeight()+lbExecutionVersion.getY(),105,15);
//		this.add(lbStarterVersion);
		
		tfStarterVersion = new JTextField();
//		tfStarterVersion.setBounds(lbStarterVersion.getWidth(), lbStarterVersion.getY(), 375, 20);
//		this.add(tfStarterVersion);
		
		lbQueryVersion = new JLabel("Query Version: ");
//		lbQueryVersion.setBounds(10,10+lbStarterVersion.getHeight()+lbStarterVersion.getY(),100,15);
//		this.add(lbQueryVersion);
		
		tfQueryVersion = new JTextField();
//		tfQueryVersion.setBounds(lbQueryVersion.getWidth(), lbQueryVersion.getY(), 380, 20);
//		this.add(tfQueryVersion);
		
	}
	
	public boolean checkFilledOut()
	{
		if(tfDirectory.getText().equals(""))
		{
			System.out.println("Binary");
			return false;	
		}
		else if(tfConceptualVersion.getText().equals(""))
		{
			System.out.println("Binary");
			return false;
		}
		else if(tfExecutionVersion.getText().equals(""))
		{
			System.out.println("Binary");
			return false;
		}
		else if(tfStarterVersion.getText().equals(""))
		{
			System.out.println("Binary");
			return false;
		}
		else if(tfQueryVersion.getText().equals(""))
		{
			System.out.println("Binary");
			return false;
		}
		
		return true;
	}
	
	public String getDirectory()
	{
		return tfDirectory.getText();
	}
	
	public String getConceptualVersion()
	{
		return tfConceptualVersion.getText();
	}
	
	public String getExecutionVersion()
	{
		return tfExecutionVersion.getText();
	}
	
	public String getStarterVersion()
	{
		return tfStarterVersion.getText();
	}
	
	public String getQueryVersion()
	{
		return tfQueryVersion.getText();
	}

	public void fillOut() {
		this.binary.setConceptualVersion(getConceptualVersion());
		this.binary.setDirectory(getDirectory());
		this.binary.setExecutionVersion(getExecutionVersion());
		this.binary.setQueryVersion(getQueryVersion());
		this.binary.setStarterVersion(getStarterVersion());
	}
}
