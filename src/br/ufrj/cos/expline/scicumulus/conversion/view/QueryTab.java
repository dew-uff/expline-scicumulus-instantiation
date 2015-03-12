package br.ufrj.cos.expline.scicumulus.conversion.view;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class QueryTab extends JPanel
{
	private JLabel lbSQL;
	private JTextArea taSQL;	
	private JScrollPane jsp;
	
	public QueryTab(){
		
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
								.addComponent(lbSQL)
						)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(jsp)
						)
						.addContainerGap(100,100)
				)
		);
		
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbSQL)
								.addComponent(jsp)
						)
						.addContainerGap()
				)
		);
		
		this.setLayout(layout);
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
		
		jsp = new JScrollPane(taSQL,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(lbSQL.getWidth(), 10, taSQL.getWidth(), taSQL.getHeight());
		
//		this.add(jsp);
		
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
