package br.ufrj.cos.expline.scicumulus.conversion.view;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import br.ufrj.cos.expline.scicumulus.conversion.util.Util;

public class ActivityMember extends JPanel
{
	private String keyInTheMap;
	private JLabel lbActivation;
	private JTextField tfActivation;
	private JComboBox cbLanguage;
	private JButton btParameters;
	private TitledBorder title;
	
	private String parameters;
	
	public ActivityMember(String key)
	{
		super();
//		this.setLayout(null);
		
		this.setBounds(0, 0, 560, 50);
		
		initComponents();
		
		this.keyInTheMap = key;
		
		
		
		title = BorderFactory.createTitledBorder(Util.getActivityTag(keyInTheMap));
		this.setBorder(title);
		
		initLayout();
	}

	private void initLayout() {
		// TODO Auto-generated method stub
		GroupLayout layout = new GroupLayout(this);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(10,10)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(lbActivation)
						)
						.addContainerGap(10,10)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(tfActivation)
						)
						.addContainerGap(10,10)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(btParameters)
						)
//						.addContainerGap(10,10)
//						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
//								.addComponent(cbLanguage)
//						)
						.addContainerGap(10,10)
				)				
		);
		
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(lbActivation)
								.addComponent(tfActivation)
								.addComponent(btParameters)
//								.addComponent(cbLanguage)
						)
				)
		);
		
		this.setLayout(layout);
	}

	private void initComponents()
	{
		lbActivation = new JLabel("Exec: ");
//		lbActivation.setBounds(10+this.getX(),10+this.getY(),65,15);
//		this.add(lbActivation);
		
		tfActivation = new JTextField();
		
		Object argr[] = {"","Java","C/C++","Pascal","Python","Fortran","C#"};
		cbLanguage = new JComboBox(argr);
		
		btParameters = new JButton("Parameters");
//		tfActivation.setBounds(lbActivation.getX()+lbActivation.getWidth(), lbActivation.getY(), 385, 20);
//		this.add(tfActivation);
	}
	
	public String getBorderTitle()
	{
		return keyInTheMap;
	}
	
	public boolean fieldIsEmpty()
	{
		return tfActivation.getText().equals("");
	}
	
	public String getActivation()
	{
		// TODO
		return tfActivation.getText();
	}
	
	public void setActivation(String act)
	{
		this.parameters = act;
	}
}
