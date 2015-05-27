package br.ufrj.cos.expline.scicumulus.conversion.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import br.ufrj.cos.expline.scicumulus.conversion.util.Util;
import br.ufrj.cos.expline.scicumulus.conversion.util.listeners.InstantiationCellEditor;
import br.ufrj.cos.expline.scicumulus.conversion.util.listeners.InstantiationTableModel;

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
	
	private HashMap<String,List<String>> parameters;
	
	private Map<String,String> properlyMap;
	
	public ParametersWindow(String title, ActivityMember member)
	{
		parameters = null;
		initClass(title, member);
	}
	
	public ParametersWindow(String title,ActivityMember member,HashMap<String,List<String>> parameters)
	{
		
		this.parameters = parameters;
		System.out.println("\n"+parameters.toString()+"\n");
		initClass(title, member);
	}
	
	public ParametersWindow(String keyInTheMap, ActivityMember member,HashMap<String, List<String>> parameters2, Vector dataVector) {
		// TODO Auto-generated constructor stub
		this(keyInTheMap,member,parameters2);
		
		carregTabela(dataVector);
	}

	private void carregTabela(Vector dataVector) {
		// TODO Auto-generated method stub
		DefaultTableModel model = (DefaultTableModel)tableTemp.getModel();
		for(Object row:dataVector)
		{
			Vector infos = (Vector)row;
			Object[] rowInfo = {infos.get(0),infos.get(1),infos.get(2)};
			model.addRow(rowInfo);
		}
	}

	private void initClass(String title, ActivityMember member)
	{
		this.setSize(500, 300);
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
								.addComponent(addButton,100,100,100)
//							)
								.addGap(5)
//							.addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(removeButton,100,100,100)
							)
						)
					)
					.addGroup(gl.createSequentialGroup()
						.addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(cancelButton,100,100,100)
						)
						.addGap(5)
						.addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(okButton,100,100,100)
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
		
		
		
		
		String[] columnsNames = {"Parameter Name","Relation","Field"};
		
		DefaultTableModel model = new InstantiationTableModel(columnsNames,0);
		
//		model.addTableModelListener(new TableModelListener() {
//			
//			@Override
//			public void tableChanged(TableModelEvent e) {
//				// TODO Auto-generated method stub
//				int column = e.getColumn();
//				int row = e.getLastRow();
//				
//				if(column == 1)
//				{
//					TableModel model = (TableModel)e.getSource();
//					
//					String choice = (String)model.getValueAt(e.getLastRow(), column);
//					Object[] values = {"2"};
//					JComboBox op = new JComboBox(values);
//					
//					model.setValueAt(op, row, 2);
//					System.out.println(choice+"  ");
//				}
//			}
//		});
		
		tableTemp = new JTable(model);
		tableTemp.setRowHeight(20);
		
//	    DefaultTableModel model = (DefaultTableModel)tableTemp.getModel();        // Adiciona algumas colunas
	    
//		model.addColumn("Parameter Name");
//	    model.addColumn("Relation"); 
//	    model.addColumn("Field");// Este s�o os valores do combobox
	    
	    Object[] values = new Object[]{"item1", "item2", "item3"};        // Configura o combobox na primeira coluna vis�vel
	    
	    List<String> listOfRelName = Util.getListOfRelNameByActivity(mw.getProperties(),Util.getActivityTag(title));
	    
//	    values = listOfRelName.toArray();
	    if(listOfRelName.size() > 0)
	    {
	    	this.properlyMap = getProperlyMap(listOfRelName);
	    }
	    
	    values = this.properlyMap.keySet().toArray();
	    
	    TableColumn relationColumn = tableTemp.getColumnModel().getColumn(1);
	    JComboBox relation = new JComboBox(values);
//	    relation.addItemListener(new relationComboBoxItemListener());
	    relationColumn.setCellEditor(new DefaultCellEditor( relation ) );
//	    relationColumn.setCellRenderer(cellRenderer);
	    relationColumn.setCellRenderer(new MyComboBoxRenderer(values));
	    
//	    listOfRelName.clear();
//	    for (String p : parameters.keySet()) {
//			listOfRelName.add(parameters.get(p));
//		}
//	    values = new Object[]{"item1", "item2", "item3"};
	    values = parameters.keySet().toArray();
	    
	    TableColumn fieldColumn = tableTemp.getColumnModel().getColumn(2);
//	    fieldColumn.setCellEditor(new DefaultCellEditor( new JComboBox(values) ) );
	    fieldColumn.setCellEditor(new InstantiationCellEditor() );
	    fieldColumn.setCellRenderer(new MyComboBoxRenderer(values));
	    
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
	
	public Map<String,String> getFakeProperlyMap()
	{
		return this.properlyMap;
	}
	
	public Map<String,String> getProperlyMap(List<String> lista)
	{
		Collections.sort(lista);
		int id =1;
		
		HashMap<String,String> properlyMap = new HashMap<>();
		System.out.println(lista.size());
		
		for(String temp : lista)
		{
			properlyMap.put("Port "+ id, temp);
			id++;
		}
		
		//System.out.println(properlyMap.toString());
		
		return properlyMap;
	}
	
	public void setActivation(Vector activation)
	{
		this.activityMember.setActivation(activation);
	} 
	
	public HashMap<String, List<String>> getParameters() {
		return parameters;
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
			ParametersWindow window = (ParametersWindow)SwingUtilities.getWindowAncestor(table);
			
			if(column == 2)
			{
				String relation = (String)table.getValueAt(row, 1);
				if(relation != null)
				{
					Map<String,String> fakeProperlyMap = window.getFakeProperlyMap();
					
					String rightChoice = fakeProperlyMap.get(relation);
					List<String> pp = window.getParameters().get(rightChoice);
					DefaultComboBoxModel tr = new DefaultComboBoxModel();
					for(String h:pp)
					{
						tr.addElement(h);
					}
					this.setModel(tr);
				}
			}
			
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
			if(tbModel.getRowCount() < ( frame.getProperlyMapPorta().size() * frame.getParameters().size() ) )
			{
				DefaultTableModel model = (DefaultTableModel)tbModel.getModel();
				model.addRow(new Object[]{""});
			}
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
    
    public  Map<String,String> getProperlyMapPorta()
    {
    	return this.properlyMap;
    }
    
    
    
    private class OkListener implements ActionListener
    {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JButton b = (JButton)arg0.getSource();
			ParametersWindow frame = (ParametersWindow)SwingUtilities.getWindowAncestor(b);
			
//			String value = getOneParamString();
			JTable table = frame.getTable();
			
			int rowsNumber = table.getRowCount();
			
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			Vector dataVector = model.getDataVector();
			
//			String params = "";
//			for (Object object : dataVector) {
////				System.out.println(object.toString());
//				Vector temp = (Vector)object;
//				
//				String param = (String)temp.get(0);
//				String relType = (String)temp.get(1);
//				relType = frame.getProperlyMapPorta().get(relType);
//				
//				String field = (String)temp.get(2);
//				
//					
//				params += param + "=%=" +field+"% ";
//			}
			
//			System.out.println("------>  "+params);
			
			if(dataVector.size() > 0)
			{
				frame.setActivation(dataVector);
			}
			else
			{
				frame.setActivation(null);
			}
			
			
			
			frame.dispose();
		}
    	
    }
	
}
