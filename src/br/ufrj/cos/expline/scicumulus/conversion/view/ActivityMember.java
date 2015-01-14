package br.ufrj.cos.expline.scicumulus.conversion.view;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ActivityMember extends JPanel
{
	private String keyInTheMap;
	private JLabel lbActivation;
	private JTextField tfActivation;
	private TitledBorder title;
	
	public ActivityMember(String key)
	{
		super();
		this.setLayout(null);
		
		this.setBounds(10, 10, 480, 50);
		
		initComponents();
		
		this.keyInTheMap = key;
		
		
		
		title = BorderFactory.createTitledBorder(keyInTheMap);
		this.setBorder(title);
	}

	private void initComponents()
	{
		lbActivation = new JLabel("Activation: ");
		lbActivation.setBounds(10+this.getX(),10+this.getY(),65,15);
		this.add(lbActivation);
		
		tfActivation = new JTextField();
		tfActivation.setBounds(lbActivation.getX()+lbActivation.getWidth(), lbActivation.getY(), 385, 20);
		this.add(tfActivation);
	}
	
	public String getBorderTitle()
	{
		return keyInTheMap;
	}
}
