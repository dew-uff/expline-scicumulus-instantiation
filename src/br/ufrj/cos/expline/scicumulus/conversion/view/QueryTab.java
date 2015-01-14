package br.ufrj.cos.expline.scicumulus.conversion.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class QueryTab extends JPanel


{
	
	private JLabel lbSQL;
	private JTextField tfSQL;	
	
	public QueryTab(){
		
		super();
		
		initComponents();
		
		this.setVisible(true);
		
		
		
	}
	
	
	
	public void initComponents(){
		
		this.setLayout(null);

		lbSQL = new JLabel("SQL: ");
		lbSQL.setBounds(10, 10, 60, 15);
		this.add(lbSQL);

		tfSQL = new JTextField();
		tfSQL.setBounds(lbSQL.getWidth(), 10, 420, 20);
		this.add(tfSQL);
		
	}

}
