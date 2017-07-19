package br.ufrj.cos.expline.scicumulus.conversion.util.listeners;

import java.awt.Component;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import br.ufrj.cos.expline.scicumulus.conversion.view.ParametersWindow;

public class InstantiationCellEditor extends DefaultCellEditor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultComboBoxModel<String> model;
	
	public InstantiationCellEditor()
	{
		super( new JComboBox<Object>() );
		JComboBox<String> component = (JComboBox<String>)getComponent();
		JComboBox<String> jComboBox = component;
		this.model = ((DefaultComboBoxModel<String>)jComboBox.getModel());
		
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,boolean isSelected, int row, int column) {
		ParametersWindow window = (ParametersWindow)SwingUtilities.getWindowAncestor(table);
		
		
		if(column == 2)
		{
			this.model.removeAllElements();
			String choice = (String)table.getValueAt(row,column-1);
//			System.out.println(choice);
			if(choice != null )
			{
				if(choice.length() > 0)
				{
					
					Map<String,String> fakeProperlyMap = window.getFakeProperlyMap();
//					System.out.println(fakeProperlyMap.toString());
					
					String rightChoice = fakeProperlyMap.get(choice);
					Map<String,List<String>> parametersMap = window.getParameters();
					
//					System.out.println(parametersMap.toString());
					
					List<String> listOfFields = parametersMap.get(rightChoice);
					if(listOfFields != null)
					{
						for(String key:listOfFields)
						{
							this.model.addElement(key);
						}
					}
				}
			}
		}
		else if( column == 1)
		{
			this.model.removeAllElements();
			Map<String,String> fakeProperlyMap = window.getFakeProperlyMap();
			if(fakeProperlyMap != null)
			{
				if( fakeProperlyMap.size() > 0 )
				{
					for(String key:fakeProperlyMap.keySet())
					{
						this.model.addElement(key);
					}
				}
			}
		}
		
		return super.getTableCellEditorComponent(table, value, isSelected, row, column);
	}
}
