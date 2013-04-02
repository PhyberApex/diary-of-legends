package de.phyberapex.diaryoflegends.view.renderer;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.model.Matchup;

public class MatchupTableRenderer extends JLabel implements TableCellRenderer {

	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager
			.getLogger(MatchupTableRenderer.class.getName());

	public MatchupTableRenderer() {
		super();
		logger.trace("MatchupTableRenderer() - Entering");
		logger.trace("MatchupTableRenderer() - Leaving");
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Matchup m = (Matchup) table.getValueAt(row, 0);
		setHorizontalAlignment(JLabel.CENTER);
		setOpaque(true);
		setForeground(Color.BLACK);
		if (isSelected || hasFocus) {
			setBackground(Color.BLUE);
			setForeground(Color.WHITE);
		} else if (column == 1) {
			setBackground(m.getResult().getColor());
		} else if (row % 2 == 0 && column != 1) {
			setBackground(new Color(200, 200, 200));
		} else if (row % 2 == 1 && column != 1) {
			setBackground(new Color(255, 255, 255));
		}
		setText(value.toString());
		return this;
	}
}