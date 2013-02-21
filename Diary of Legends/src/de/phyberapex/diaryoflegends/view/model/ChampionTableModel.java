package de.phyberapex.diaryoflegends.view.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;

public class ChampionTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 6832182630678830128L;
	private List<Champion> champs;
	private String[] columnNames = new String[] { "Picture", "Name" };
	transient private static Logger logger = LogManager
			.getLogger(ChampionTableModel.class.getName());

	/**
	 * Constructor for a new tablemodel
	 * 
	 * @param models
	 *            initial list of {@link Champion champions} to view
	 */
	public ChampionTableModel(List<Champion> models) {
		logger.trace("ChampionTableModel() - Entering");
		logger.debug("ChampionTableModel() - Parameter: {}", models);
		this.champs = models;
		logger.trace("ChampionTableModel() - Leaving");
	}

	/**
	 * Set the champs to display in this table model
	 * 
	 * @param champs
	 *            list of {@link Champion champions} to set
	 */
	public void setChamps(List<Champion> champs) {
		logger.trace("setChamps() - Entering");
		logger.debug("setChamps() - Parameter: {}", champs);
		this.champs = champs;
		fireTableDataChanged();
		logger.trace("setChamps() - Leaving");
	}

	/**
	 * Adds a champion to the list of champions to display
	 * 
	 * @param champ
	 *            The {@link Champion champion} to add
	 */
	public void addChamp(Champion champ) {
		logger.trace("addChamp() - Entering");
		logger.debug("addChamp() - Parameter: {}", champ);
		champs.add(champ);
		fireTableRowsInserted(champs.size() - 1, champs.size() - 1);
		logger.trace("addChamp() - Leaving");
	}

	/**
	 * Removes a champion from the list of champions to display
	 * 
	 * @param champ
	 *            The {@link Champion champion} to remove
	 */
	public void removeChamp(Champion champ) {
		logger.trace("removeChamp() - Entering");
		logger.debug("removeChamp() - Parameter: {}", champ);
		champs.remove(champ);
		fireTableRowsDeleted(1, 1);
		logger.trace("removeChamp() - Leaving");
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
		int rows = champs.size();
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
			returnValue = champs.get(rowIndex).getIcon();
			break;
		case 1:
			returnValue = champs.get(rowIndex);
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
		Champion c = champs.get(rowIndex);
		switch (columnIndex) {
		case 1:
			c.setName((String) aValue);
			break;
		}
		ChampionUtil.saveChampion(c);
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
