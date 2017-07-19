package br.ufrj.cos.expline.scicumulus.conversion.view;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.ufrj.cos.expline.scicumulus.conversion.model.sciObjects.Database;

public class DataBaseTab extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbName;
	private JTextField tfName;	
	private JLabel lbUserName;
	private JTextField tfUserName;
	private JLabel lbPassword;
	private JTextField tfPassword;
	private JLabel lbPort;
	private JTextField tfPort;
	private JLabel lbServer;
	private JTextField tfServer;
	private JLabel lbPath;
	private JTextField tfPath;
	private Database database;
	
	public DataBaseTab(Database database)
	{
		super();
		this.database = database;
		
		initComponents();
		
		this.setVisible(true);
		
		initLayout();
	}

	private void initComponents() {
		// TODO Auto-generated method stub

//		this.setLayout(null);
//		this.setLayout(layout);

		
		lbName = new JLabel("Name: ");
//		lbName.setBounds(10,10,50,15);
//		this.add(lbName);
		
		tfName = new JTextField();
//		tfName.setBounds(lbName.getWidth(), 10, 430, 20);
//		this.add(tfName);
		
		lbUserName = new JLabel("User Name: ");
//		lbUserName.setBounds(10,10+lbName.getHeight()+lbName.getY(),80,15);
//		this.add(lbUserName);
		
		tfUserName = new JTextField();
//		tfUserName.setBounds(lbUserName.getWidth(), lbUserName.getY(), 400, 20);
//		this.add(tfUserName);
		
		lbPassword = new JLabel("Password: ");
//		lbPassword.setBounds(10,10+lbUserName.getHeight()+lbUserName.getY(),75,15);
//		this.add(lbPassword);
		
		tfPassword = new JTextField();
//		tfPassword.setBounds(lbPassword.getWidth(), lbPassword.getY(), 405, 20);
//		this.add(tfPassword);
		
		lbPort = new JLabel("Port: ");
//		lbPort.setBounds(10,10+lbPassword.getHeight()+lbPassword.getY(),40,15);
//		this.add(lbPort);
		
		tfPort = new JTextField();
//		tfPort.setBounds(lbPort.getWidth(), lbPort.getY(), 440, 20);
//		this.add(tfPort);
		
		lbServer = new JLabel("Server: ");
//		lbServer.setBounds(10,10+lbPort.getHeight()+lbPort.getY(),55,15);
//		this.add(lbServer);
		
		tfServer = new JTextField();
//		tfServer.setBounds(lbServer.getWidth(), lbServer.getY(), 425, 20);
//		this.add(tfServer);
		
		lbPath = new JLabel("Path: ");
//		lbPath.setBounds(10,10+lbServer.getHeight()+lbServer.getY(),45,15);
//		this.add(lbPath);
		
		tfPath = new JTextField();
//		tfPath.setBounds(lbPath.getWidth(), lbPath.getY(), 435, 20);
//		this.add(tfPath);
		
//		this.setLayout(layout);
	

	}
	
	private void initLayout()
	{
GroupLayout layout = new GroupLayout(this);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(100,100)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(lbName)
								.addComponent(lbUserName)
								.addComponent(lbPassword)
								.addComponent(lbPort)
								.addComponent(lbServer)
								.addComponent(lbPath)
						)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(tfName)
								.addComponent(tfUserName)
								.addComponent(tfPassword)
								.addComponent(tfPort)
								.addComponent(tfServer)
								.addComponent(tfPath)
						)
						.addContainerGap(100,100)
				)
		);
		
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbName)
								.addComponent(tfName)
						)
						.addContainerGap(15,15)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbUserName)
								.addComponent(tfUserName)
						)
						.addContainerGap(15,15)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbPassword)
								.addComponent(tfPassword)
						)
						.addContainerGap(15,15)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbPort)
								.addComponent(tfPort)
						)
						.addContainerGap(15,15)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbServer)
								.addComponent(tfServer)
						)
						.addContainerGap(15,15)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbPath)
								.addComponent(tfPath)
						)
						.addContainerGap()
				)
		);
		
		this.setLayout(layout);
		
	}
	
	public boolean checkFilledOut()
	{
		
		if(tfName.getText().equals(""))
		{
			System.out.println("Database_Name");
			return false;	
		}
		else if(tfUserName.getText().equals(""))
		{
			System.out.println("Database_User_Name");
			return false;
		}
		else if(tfPassword.getText().equals(""))
		{
			System.out.println("Database");
			return false;
		}
		else if(tfPort.getText().equals(""))
		{
			System.out.println("Database");
			return false;
		}
		else if(tfServer.getText().equals(""))
		{
			System.out.println("Database");
			return false;
		}
		else if(tfPath.getText().equals(""))
		{
			System.out.println("Database");
			return false;
		}
		
		return true;
	}
	
	public String getName()
	{
		return tfName.getText();
	}
	
	public String getUserName()
	{
		return tfUserName.getText();
	}
	
	public String getPassword()
	{
		return tfPassword.getText();
	}
	
	public String getPort()
	{
		return tfPort.getText();
	}
	
	public String getServer()
	{
		return tfServer.getText();
	}
	
	public String getPath()
	{
		return tfPath.getText();
	}

	public void fillOut() {
		this.database.setName(getName());
		this.database.setPassword(getPassword());
		this.database.setPath(getPath());
		this.database.setPort(getPort());
		this.database.setServer(getServer());
		this.database.setUserName(getUserName());
	}
}
