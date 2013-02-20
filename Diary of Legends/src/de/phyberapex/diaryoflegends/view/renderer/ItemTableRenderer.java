package de.phyberapex.diaryoflegends.view.renderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ItemTableRenderer implements TableCellRenderer {

	private JPanel panel;
	private JLabel label;
	private static Logger logger = LogManager
			.getLogger(ItemTableRenderer.class.getName());

	public ItemTableRenderer() {
		logger.trace("ChampionTableRenderer() - Entering");
		logger.trace("ChampionTableRenderer() - Leaving");
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component returnValue = null;
		table.getColumnModel().getColumn(0).setMaxWidth(60);
		table.setRowHeight(60);
		switch (column) {
		case 0:
			label = new JLabel();
			Icon ic = (Icon) value;
			label.setIcon(ic);
			label.setSize(ic.getIconHeight(), ic.getIconWidth());
			returnValue = label;
			break;
		case 1:
			panel = new JPanel();
			panel.setLayout(new BorderLayout());
			label = new JLabel();
			if (isSelected || hasFocus) {
				panel.setBackground(Color.ORANGE);
			}
			label.setText(value.toString());
			panel.add(label);
			returnValue = panel;
			break;
		case 2:
			panel = new JPanel();
			panel.setLayout(new BorderLayout());
			label = new JLabel();
			if (isSelected || hasFocus) {
				panel.setBackground(Color.ORANGE);
			}
			label.setText(value.toString());
			panel.add(label);
			returnValue = panel;
			break;
		}
		return returnValue;
	}
}