package br.ufrj.cos.expline.scicumulus.conversion.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class QueryTab extends JPanel


{
	
	private JLabel lbSQL;
	private JTextArea taSQL;	
	
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

		taSQL = new JTextArea();
		taSQL.setBounds(lbSQL.getWidth(), 10, 420, 185);
		taSQL.setLineWrap(true);
		taSQL.setWrapStyleWord(true);
		
		JScrollPane jsp = new JScrollPane(taSQL,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(lbSQL.getWidth(), 10, taSQL.getWidth(), taSQL.getHeight());
		
		this.add(jsp);
		
	}
	
	public boolean checkFilledOut()
	{
		if(taSQL.getText().equals("")) 
		{
			System.out.println("SQL");
			return false;
		}
		return true;
	}
	
	public String getSql()
	{
		return taSQL.getText();
	}

}
