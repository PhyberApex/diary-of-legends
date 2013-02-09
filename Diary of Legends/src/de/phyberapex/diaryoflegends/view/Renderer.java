package de.phyberapex.diaryoflegends.view;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class Renderer extends JLabel implements TableCellRenderer {

	private static final long serialVersionUID = -643483167294281726L;

	public Renderer() {
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		setText(null);
		setIcon(null);
		if (value instanceof Icon) {
			Icon ic = (Icon) value;
			setIcon(ic);
			setSize(ic.getIconHeight(), ic.getIconWidth());
		} else
			setText(value.toString());

		return this;
	}
}