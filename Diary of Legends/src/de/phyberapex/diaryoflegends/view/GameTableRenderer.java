package de.phyberapex.diaryoflegends.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.model.Game;
import de.phyberapex.diaryoflegends.model.GameResult;

public class GameTableRenderer implements TableCellRenderer {

	private JPanel panel;
	private JLabel label;
	private static Logger logger = LogManager.getLogger(GameTableRenderer.class
			.getName());

	public GameTableRenderer() {
		logger.trace("GameTableRender() - Entering");
		logger.trace("GameTableRender() - Leaving");
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component returnValue = null;
		Game g = (Game) table.getValueAt(row, 1);
		switch (column) {
		case 0:
			panel = new JPanel();
			panel.setLayout(new BorderLayout());
			label = new JLabel();
			if (isSelected || hasFocus) {
				panel.setBackground(Color.ORANGE);
			} else if (g.getResult() == GameResult.WIN) {
				panel.setBackground(Color.GREEN);
			} else if (g.getResult() == GameResult.LOSS) {
				panel.setBackground(Color.RED);
			} else {
				panel.setBackground(Color.WHITE);
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
			} else if (g.getResult() == GameResult.WIN) {
				panel.setBackground(Color.GREEN);
			} else if (g.getResult() == GameResult.LOSS) {
				panel.setBackground(Color.RED);
			} else {
				panel.setBackground(Color.WHITE);
			}
			label.setText(value.toString());
			panel.add(label);
			returnValue = panel;
			break;
		}
		return returnValue;
	}
}