package de.phyberapex.diaryoflegends.view;

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

public class MatchupTableRenderer implements TableCellRenderer {

	private JPanel panel;
	private JLabel label;
	private static Logger logger = LogManager
			.getLogger(MatchupTableRenderer.class.getName());

	public MatchupTableRenderer() {
		logger.trace("MatchupTableRenderer() - Entering");
		logger.trace("MatchupTableRenderer() - Leaving");
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component returnValue = null;
		switch (column) {
		case 0:
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