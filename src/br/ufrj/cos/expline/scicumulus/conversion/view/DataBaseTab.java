package br.ufrj.cos.expline.scicumulus.conversion.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DataBaseTab extends JPanel
{
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
	
	public DataBaseTab()
	{
		super();
		
		initComponents();
		
		this.setVisible(true);
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		
		lbName = new JLabel("Name: ");
		lbName.setBounds(10,10,50,15);
		this.add(lbName);
		
		tfName = new JTextField();
		tfName.setBounds(lbName.getWidth(), 10, 430, 20);
		this.add(tfName);
		
		lbUserName = new JLabel("User Name: ");
		lbUserName.setBounds(10,10+lbName.getHeight()+lbName.getY(),80,15);
		this.add(lbUserName);
		
		tfUserName = new JTextField();
		tfUserName.setBounds(lbUserName.getWidth(), lbUserName.getY(), 400, 20);
		this.add(tfUserName);
		
		lbPassword = new JLabel("Password: ");
		lbPassword.setBounds(10,10+lbUserName.getHeight()+lbUserName.getY(),75,15);
		this.add(lbPassword);
		
		tfPassword = new JTextField();
		tfPassword.setBounds(lbPassword.getWidth(), lbPassword.getY(), 405, 20);
		this.add(tfPassword);
		
		lbPort = new JLabel("Port: ");
		lbPort.setBounds(10,10+lbPassword.getHeight()+lbPassword.getY(),40,15);
		this.add(lbPort);
		
		tfPort = new JTextField();
		tfPort.setBounds(lbPort.getWidth(), lbPort.getY(), 440, 20);
		this.add(tfPort);
		
		lbServer = new JLabel("Server: ");
		lbServer.setBounds(10,10+lbPort.getHeight()+lbPort.getY(),55,15);
		this.add(lbServer);
		
		tfServer = new JTextField();
		tfServer.setBounds(lbServer.getWidth(), lbServer.getY(), 425, 20);
		this.add(tfServer);
		
		lbPath = new JLabel("Path: ");
		lbPath.setBounds(10,10+lbServer.getHeight()+lbServer.getY(),45,15);
		this.add(lbPath);
		
		tfPath = new JTextField();
		tfPath.setBounds(lbPath.getWidth(), lbPath.getY(), 435, 20);
		this.add(tfPath);
	}
}
