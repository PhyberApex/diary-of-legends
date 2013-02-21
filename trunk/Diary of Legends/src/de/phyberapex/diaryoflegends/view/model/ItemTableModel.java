package de.phyberapex.diaryoflegends.view.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.model.Item;
import de.phyberapex.diaryoflegends.model.util.ItemUtil;

public class ItemTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -7653762677922814674L;
	private List<Item> items;
	private String[] columnNames = new String[] { "Picture", "Name", "Price" };
	transient private static Logger logger = LogManager
			.getLogger(ItemTableModel.class.getName());

	/**
	 * Constructor for a new tablemodel
	 * 
	 * @param models
	 *            initial list of {@link Item items} to view
	 */
	public ItemTableModel(List<Item> models) {
		logger.trace("ItemTableModel() - Entering");
		logger.debug("ItemTableModel() - Parameter: {}", models);
		this.items = models;
		logger.trace("ItemTableModel() - Leaving");
	}

	/**
	 * Set the items to display in this table model
	 * 
	 * @param items
	 *            list of {@link Item items} to set
	 */
	public void setItems(List<Item> items) {
		logger.trace("setItems() - Entering");
		logger.debug("setItems() - Parameter: {}", items);
		this.items = items;
		fireTableDataChanged();
		logger.trace("setItems() - Leaving");
	}

	/**
	 * Adds a item to the list of items to display
	 * 
	 * @param item
	 *            The {@link Item item} to add
	 */
	public void addItem(Item item) {
		logger.trace("addItem() - Entering");
		logger.debug("addItem() - Parameter: {}", item);
		items.add(item);
		fireTableRowsInserted(items.size() - 1, items.size() - 1);
		logger.trace("addItem() - Leaving");
	}

	/**
	 * Removes a item from the list of items to display
	 * 
	 * @param item
	 *            The {@link Item item} to remove
	 */
	public void removeItem(Item item) {
		logger.trace("removeItem() - Entering");
		logger.debug("removeItem() - Parameter: {}", item);
		items.remove(item);
		fireTableRowsDeleted(1, 1);
		logger.trace("removeItem() - Leaving");
	}

	/**
	 * Returns the amount of columns
	 * 
	 * @return {@link int} the amount of columns
	 */
	@Override
	public int getColumnCount() {
		int columns = 3;
		return columns;
	}

	/**
	 * Returns the amount of rows
	 * 
	 * @return {@link int} the amount of rows
	 */
	@Override
	public int getRowCount() {
		int rows = items.size();
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
			returnValue = items.get(rowIndex).getIcon();
			break;
		case 1:
			returnValue = items.get(rowIndex);
			break;
		case 2:
			returnValue = items.get(rowIndex).getPrice();
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
		Item i = items.get(rowIndex);
		switch (columnIndex) {
		case 1:
			i.setName((String) aValue);
			break;
		case 2:
			try {
				int price = Integer.parseInt((String) aValue);
				if (price > 0) {
					i.setPrice(price);
				}
			} catch (NumberFormatException e) {
				logger.info("{} is not a Number", aValue);
			}
			break;
		}
		ItemUtil.saveItem(i);
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