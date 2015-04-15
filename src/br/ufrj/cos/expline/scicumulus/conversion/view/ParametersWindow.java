package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import br.ufrj.cos.expline.scicumulus.conversion.Conversion_reader;
import br.ufrj.cos.expline.scicumulus.conversion.util.Util;

@SuppressWarnings("serial")
public class ParametersWindow extends JFrame
{
	private JPanel mainPanel;
	
	private JTable table;
	private JButton addButton;
	private JButton removeButton;
	private JButton okButton;
	private JButton cancelButton;
	private TableModel dataModel;
	
	private JScrollPane scrollPane;
	
	private JTable tableTemp;
	
	private String title;
	
	private ActivityMember activityMember;
	
	private MainWindow mw;
	
	
	
	public ParametersWindow(String title, ActivityMember member)
	{
		initClass(title, member);
	}
	
	private void initClass(String title, ActivityMember member)
	{
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		this.title = title;
		
		this.activityMember = member;
		this.mw = (MainWindow) SwingUtilities.getWindowAncestor(activityMember);
		
		initComponents();
		
		this.setResizable(false);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setVisible(true);
		
		initLayout();
	}
	
	private void initLayout() {
		// TODO Auto-generated method stub
		GroupLayout gl = new GroupLayout(mainPanel);
	    
	    scrollPane = new JScrollPane(tableTemp);
	    
	    gl.setHorizontalGroup(gl.createSequentialGroup()
			.addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addGroup(gl.createSequentialGroup()
		    			.addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(scrollPane)    						
		    			)
						.addGroup(gl.createSequentialGroup()
							.addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(addButton)
//							)
								.addGap(5)
//							.addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(removeButton)
							)
						)
					)
					.addGroup(gl.createSequentialGroup()
						.addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(cancelButton)
						)
						.addGap(5)
						.addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(okButton)
						)
						.addGap(50)
					)
			)
		);
	    
	    gl.setVerticalGroup(gl.createSequentialGroup()
			.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(scrollPane)
					.addGroup(gl.createSequentialGroup()//gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addGap(50)
						.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(addButton)
						)
						.addGap(5)
						.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(removeButton)
						)
				    )
			)
			.addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(cancelButton)
					.addGap(5)
					.addComponent(okButton)
			)
	    );
	    
	    mainPanel.setLayout(gl);
	    
	    this.add(mainPanel);
	}
	private void initComponents() {
		this.setTitle(this.title);	
		
		mainPanel = new JPanel();
		
//		dataModel = new AbstractTableModel() 
//		{
//		      public int getColumnCount() { return 2; }
//		      public int getRowCount() { return 10;}
//		      public Object getValueAt(int row, int col) { return new Integer(row*col); }
//		};
		
		tableTemp = new JTable();
		tableTemp.setRowHeight(20);
		
	    DefaultTableModel model = (DefaultTableModel)tableTemp.getModel();        // Adiciona algumas colunas
	    model.addColumn("Param Name");
	    model.addColumn("Rel Name"); 
	    model.addColumn("Field");// Este são os valores do combobox
	    
	    Object[] values = new Object[]{"item1", "item2", "item3"};        // Configura o combobox na primeira coluna visível
	    
	    List<String> listOfRelName = Util.getListOfRelNameByActivity(mw.getProperties(),Util.getActivityTag(title));
	    
	    values = listOfRelName.toArray();
	    
	    TableColumn col = tableTemp.getColumnModel().getColumn(1);
	    col.setCellEditor(new MyComboBoxEditor(values));        
	    col.setCellRenderer(new MyComboBoxRenderer(values));
	    
	    
	    values = new Object[]{"item1", "item2", "item3"};
	    
	    TableColumn col1 = tableTemp.getColumnModel().getColumn(2);
	    col1.setCellEditor(new MyComboBoxEditor(values));        
	    col1.setCellRenderer(new MyComboBoxRenderer(values));
	    
	    addButton = new JButton("Add");
	    addButton.addActionListener(new AddListener());
	    removeButton = new JButton("Remove");
	    removeButton.addActionListener(new RemoveListener());
	    
	    okButton = new JButton("OK");
	    okButton.addActionListener(new OkListener());
	    cancelButton = new JButton("Cancel");
	    cancelButton.addActionListener(new CancelListener());
	    
//	    DefaultTableModel modeltemp = (DefaultTableModel) table.getModel();
	    
	}
	
	@SuppressWarnings({ "serial", "rawtypes" })
	public class MyComboBoxRenderer extends JComboBox implements TableCellRenderer {
        /**
		 * 
		 */

		@SuppressWarnings("unchecked")
		public MyComboBoxRenderer(Object[] items) {
            super(items);
        }   
       
		@Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {            
        	if (isSelected) {
                setForeground(table.getSelectionForeground());
               super.setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }                
        	setSelectedItem(value);
            return this;
        }
    }   
    
    @SuppressWarnings("serial")
	public class MyComboBoxEditor extends DefaultCellEditor {
    	
        @SuppressWarnings({ "unchecked", "rawtypes" })
		public MyComboBoxEditor(Object[] items) {
            super(new JComboBox(items));
        }
    }
    
    public JTable getTable()
    {
    	return tableTemp;
    }
    
    private class AddListener implements ActionListener
    {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			JButton b = (JButton)e.getSource();
			ParametersWindow frame = (ParametersWindow)SwingUtilities.getWindowAncestor(b);
			
			JTable tbModel = frame.getTable();
			
			DefaultTableModel model = (DefaultTableModel)tbModel.getModel();
			model.addRow(new Object[]{""});
		}
    	
    }
    
    private class RemoveListener implements ActionListener
    {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton b = (JButton)e.getSource();
			ParametersWindow frame = (ParametersWindow)SwingUtilities.getWindowAncestor(b);
			
			JTable tbModel = frame.getTable();
			
			int pos = tbModel.getSelectedRow();
			if(pos >= 0)
			{
				DefaultTableModel model = (DefaultTableModel)tbModel.getModel();
				model.removeRow(pos);
			}
		}
    }
    
    private class CancelListener implements ActionListener
    {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton b = (JButton)arg0.getSource();
			ParametersWindow frame = (ParametersWindow)SwingUtilities.getWindowAncestor(b);
			
			frame.dispose();
		}
    	
    }
    
    
    
    private class OkListener implements ActionListener
    {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JButton b = (JButton)arg0.getSource();
			ParametersWindow frame = (ParametersWindow)SwingUtilities.getWindowAncestor(b);
			
//			String value = getOneParamString();
			
			frame.dispose();
		}
    	
    }
	
}
