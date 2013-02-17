package de.phyberapex.diaryoflegends.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.model.Game;

public class GameTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -7653762677922814674L;
	private List<Game> games;
	private String[] columnNames = new String[] { "Date", "Notes" };
	transient private static Logger logger = LogManager
			.getLogger(GameTableModel.class.getName());

	/**
	 * Constructor for a new tablemodel
	 * 
	 * @param models
	 *            initial list of {@link Game games} to view
	 */
	public GameTableModel(List<Game> games) {
		logger.trace("GameTableModel() - Entering");
		logger.debug("GameTableModel() - Parameter: {}", games);
		this.games = games;
		logger.trace("GameTableModel() - Leaving");
	}

	/**
	 * Set the games to display in this table model
	 * 
	 * @param games
	 *            list of {@link Game games} to set
	 */
	public void setGamess(List<Game> games) {
		logger.trace("setGamess() - Entering");
		logger.debug("setGamess() - Parameter: {}", games);
		this.games = games;
		fireTableDataChanged();
		logger.trace("setGamess() - Leaving");
	}

	/**
	 * Adds a game to the list of games to display
	 * 
	 * @param game
	 *            The {@link Game game} to add
	 */
	public void addGame(Game game) {
		logger.trace("addGame() - Entering");
		logger.debug("addGame() - Parameter: {}", game);
		games.add(game);
		fireTableRowsInserted(games.size() - 1, games.size() - 1);
		logger.trace("addGame() - Leaving");
	}

	/**
	 * Removes a game from the list of games to display
	 * 
	 * @param matchup
	 *            The {@link Game game} to remove
	 */
	public void removeGame(Game game) {
		logger.trace("removeGame() - Entering");
		logger.debug("removeGame() - Parameter: {}", game);
		games.remove(game);
		fireTableRowsDeleted(1, 1);
		logger.trace("removeGame() - Leaving");
	}

	/**
	 * Returns the amount of columns
	 * 
	 * @return {@link int} the amount of columns
	 */
	@Override
	public int getColumnCount() {
		int columns = 2;
		return columns;
	}

	/**
	 * Returns the amount of rows
	 * 
	 * @return {@link int} the amount of rows
	 */
	@Override
	public int getRowCount() {
		int rows = games.size();
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
			Date d = games.get(rowIndex).getDate();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String str = df.format(d);
			returnValue = str;
			break;
		case 1:
			returnValue = games.get(rowIndex);
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