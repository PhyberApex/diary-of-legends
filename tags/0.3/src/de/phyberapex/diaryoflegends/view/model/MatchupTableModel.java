package de.phyberapex.diaryoflegends.view.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.model.Matchup;

public class MatchupTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -7653762677922814674L;
	private List<Matchup> matchups;
	private String[] columnNames = new String[] { "Date", "Lane", "Me",
			"Opponent", "Game" };
	transient private static Logger logger = LogManager
			.getLogger(MatchupTableModel.class.getName());

	/**
	 * Constructor for a new tablemodel
	 * 
	 * @param models
	 *            initial list of {@link Matchup models} to view
	 */
	public MatchupTableModel(List<Matchup> models) {
		logger.trace("MatchupTableModel() - Entering");
		logger.debug("MatchupTableModel() - Parameter: {}", models);
		this.matchups = models;
		logger.trace("MatchupTableModel() - Leaving");
	}

	/**
	 * Set the matchups to display in this table model
	 * 
	 * @param matchups
	 *            list of {@link Matchup matchups} to set
	 */
	public void setMatchups(List<Matchup> matchups) {
		logger.trace("setMatchups() - Entering");
		logger.debug("setMatchups() - Parameter: {}", matchups);
		this.matchups = matchups;
		fireTableDataChanged();
		logger.trace("setMatchups() - Leaving");
	}

	/**
	 * Adds a matchup to the list of matchups to display
	 * 
	 * @param matchup
	 *            The {@link Matchup matchup} to add
	 */
	public void addMatchup(Matchup matchup) {
		logger.trace("addMatchup() - Entering");
		logger.debug("addMatchup() - Parameter: {}", matchup);
		matchups.add(matchup);
		fireTableRowsInserted(matchups.size() - 1, matchups.size() - 1);
		logger.trace("addMatchup() - Leaving");
	}

	/**
	 * Removes a matchup from the list of matchups to display
	 * 
	 * @param matchup
	 *            The {@link Matchup matchup} to remove
	 */
	public void removeMatchup(Matchup matchup) {
		logger.trace("removeMatchup() - Entering");
		logger.debug("removeMatchup() - Parameter: {}", matchup);
		matchups.remove(matchup);
		fireTableRowsDeleted(1, 1);
		logger.trace("removeMatchup() - Leaving");
	}

	/**
	 * Returns the amount of columns
	 * 
	 * @return {@link int} the amount of columns
	 */
	@Override
	public int getColumnCount() {
		int columns = 5;
		return columns;
	}

	/**
	 * Returns the amount of rows
	 * 
	 * @return {@link int} the amount of rows
	 */
	@Override
	public int getRowCount() {
		int rows = matchups.size();
		return rows;
	}

	/**
	 * Returns the value thats in the table at the given row in the given column
	 * 
	 * @param rowIndex
	 *            {@link int} The index of the row
	 * @param columnIndex
	 *            {@link int} The index of the column
	 * @return {@link Object} The value at the specified cell
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object returnValue = null;
		switch (columnIndex) {
		case 0:
			returnValue = matchups.get(rowIndex);
			break;
		case 1:
			returnValue = matchups.get(rowIndex).getLane();
			break;
		case 2:
			returnValue = matchups.get(rowIndex).getMyChamp();
			break;
		case 3:
			returnValue = matchups.get(rowIndex).getEnemyChamp();
			break;
		case 4:
			returnValue = matchups.get(rowIndex).getGame().getResult();
			break;
		}
		return returnValue;
	}

	/**
	 * Set the value at the specified cell
	 * 
	 * @param aValue
	 *            {@link Object} The value to set the cell to
	 * @param rowIndex
	 *            {@link int} The index of the row
	 * @param columnIndex
	 *            {@link int} The index of the column
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}

	/**
	 * Returns wether the specified cell is editable or not
	 * 
	 * @param rowIndex
	 *            {@link int} The index of the row
	 * @param columnIndex
	 *            {@link int} The index of the column
	 * @return {@link boolean}
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		boolean editable = false;
		return editable;
	}

	/**
	 * Returns the name for the column with the given index
	 * 
	 * @param columnIndex
	 *            {@link int} The index of the column
	 * @return {@link String} The name of the column
	 */
	@Override
	public String getColumnName(int column) {
		String columnName = columnNames[column];
		return columnName;
	}
}