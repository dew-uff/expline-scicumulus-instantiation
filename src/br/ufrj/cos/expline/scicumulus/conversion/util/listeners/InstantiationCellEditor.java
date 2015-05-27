package br.ufrj.cos.expline.scicumulus.conversion.util.listeners;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;

import br.ufrj.cos.expline.scicumulus.conversion.view.ParametersWindow;

public class InstantiationCellEditor extends DefaultCellEditor {
	
	private DefaultComboBoxModel model;
	
	public InstantiationCellEditor()
	{
		super( new JComboBox() );
		this.model = ((DefaultComboBoxModel)((JComboBox)getComponent()).getModel());
		
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		ParametersWindow window = (ParametersWindow)SwingUtilities.getWindowAncestor(table);
		
		
		if(column == 2)
		{
			String choice = (String)table.getValueAt(row, 1);
			if(choice != null)
			{
				this.model.removeAllElements();
				Map<String,String> fakeProperlyMap = window.getFakeProperlyMap();
				System.out.println(fakeProperlyMap.toString());
				
				String rightChoice = fakeProperlyMap.get(choice);
				Map<String,List<String>> parametersMap = window.getParameters();
				
				System.out.println(parametersMap.toString());
				
				List<String> listOfFields = parametersMap.get(rightChoice);
				
				for(String key:listOfFields)
				{
					this.model.addElement(key);
				}
				
//				for(String key : keySet)
//				{
//					if( ((String)parametersMap.get(key)).equals(rightChoice) )
//					{
////						fieldsList.add(key);
//						this.model.addElement(key);
//					}
//				}
				
				
			}
		}
		
		return super.getTableCellEditorComponent(table, value, isSelected, row, column);
	}   
	
	
}
