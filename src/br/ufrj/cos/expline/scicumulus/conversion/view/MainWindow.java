package br.ufrj.cos.expline.scicumulus.conversion.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class MainWindow extends JFrame 
{
	public MainWindow()
	{
		super();
		
		this.setSize(400, 400);
		
		this.setLocationRelativeTo(null);
		
		this.setTitle("Scicumulus Instantiation");
		
		JTabbedPane jtp = new JTabbedPane();
		//jtp.setSize(width, height);
		this.add(jtp);
		
		JPanel aloha = new JPanel();
		JPanel aloha1 = new CredentialsTab();
		
		jtp.addTab("Claudio", aloha );
		
		jtp.addTab("Joao", aloha1 );
		
		JTextField jtf = new JTextField();
		jtf.setText("Alo meu amigo");
		jtf.setLocation(0, 0);
		
		
		
		aloha.add(jtf);
		
		this.setResizable(false);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setVisible(true);
		
	}
}
