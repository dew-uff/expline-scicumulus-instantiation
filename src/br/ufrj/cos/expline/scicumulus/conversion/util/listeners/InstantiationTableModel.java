package br.ufrj.cos.expline.scicumulus.conversion.util.listeners;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class InstantiationTableModel extends DefaultTableModel {

	
	public InstantiationTableModel(String[] columnsNames, int i) 
	{
		// TODO Auto-generated constructor stub
		super(columnsNames, i);
	}
	
	@Override
	public void setValueAt(Object aValue, int row, int column) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, row, column);
	}
	
	@Override
	public void fireTableCellUpdated(int row, int column) {
		// TODO Auto-generated method stub
		
		super.fireTableCellUpdated(row, column);
		
	}
}
