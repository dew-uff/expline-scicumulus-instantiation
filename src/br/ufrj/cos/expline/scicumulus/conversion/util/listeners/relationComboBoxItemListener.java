package br.ufrj.cos.expline.scicumulus.conversion.util.listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

public class relationComboBoxItemListener implements ItemListener{

	@Override
	public void itemStateChanged(ItemEvent e) {
		JComboBox<?> cb = (JComboBox<?>)e.getSource();
		cb.setSelectedIndex(0);
	}

}
