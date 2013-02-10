package de.phyberapex.diaryoflegends.view;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class ChampionTableRenderer extends JLabel implements TableCellRenderer {

	private static final long serialVersionUID = -643483167294281726L;
	private JPanel panel;

	public ChampionTableRenderer() {
		
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		setText(null);
		setIcon(null);
		table.getColumnModel().getColumn(0).setMaxWidth(60);
		table.setRowHeight(60);
		if (value instanceof Icon) {
			Icon ic = (Icon) value;
			setIcon(ic);
			setSize(ic.getIconHeight(), ic.getIconWidth());
		} else {
			panel = new JPanel();
			panel.add(this);
			setText(value.toString());
			return panel;
		}
		return this;
	}
}