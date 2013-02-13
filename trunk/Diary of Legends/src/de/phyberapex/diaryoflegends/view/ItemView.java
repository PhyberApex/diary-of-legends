package de.phyberapex.diaryoflegends.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.controller.ItemController;
import de.phyberapex.diaryoflegends.model.Item;
import de.phyberapex.diaryoflegends.model.util.ItemUtil;

public class ItemView extends JPanel implements View {

	private static final long serialVersionUID = 5737240943659740168L;
	private ItemController controller;
	private JTextField searchTextField;
	private JButton searchButton;
	private JButton clearSearchButton;
	private JButton newButton;
	private JButton deleteButton;
	private JScrollPane itemTablePane;
	private JTable itemTable;
	private NewItemDialoge newItemDialoge = new NewItemDialoge(this);
	private GridBagConstraints constraints;
	private static Logger logger = LogManager.getLogger(ChampionView.class
			.getName());

	public ItemView(ItemController controller) {
		logger.trace("ChampionView() - Entering");
		logger.debug("ChampionView() - Parameter: {}", controller);
		this.controller = controller;
		createGUI();
		logger.trace("ChampionView() - Leaving");
	}

	/**
	 * Creates the GUI
	 */
	private void createGUI() {
		logger.trace("createGUI() - Entering");
		this.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		logger.debug("Adding searchTextfield to panel with constraints: {}",
				constraints);
		this.add(getSearchTextfield(), constraints);
		
		constraints = new GridBagConstraints();
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 2;
		constraints.gridy = 0;
		logger.debug("Adding searchButton to panel with constraints: {}",
				constraints);
		this.add(getSearchButton(), constraints);
		
		constraints = new GridBagConstraints();
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 3;
		constraints.gridy = 0;
		logger.debug("Adding clearSearchButton to panel with constraints: {}",
				constraints);
		this.add(getClearSearchButton(), constraints);
		
		constraints = new GridBagConstraints();
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 0;
		constraints.gridy = 1;
		logger.debug("Adding newButton to panel with constraints: {}",
				constraints);
		this.add(getNewButton(), constraints);
		
		constraints = new GridBagConstraints();
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.gridx = 1;
		constraints.gridy = 1;
		logger.debug("Adding deleteButton to panel with constraints: {}",
				constraints);
		this.add(getDeleteButton(), constraints);
		
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridwidth = 4;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridx = 0;
		constraints.gridy = 2;
		logger.debug("Adding championTable to panel with constraints: {}",
				constraints);
		this.add(getItemTablePane(), constraints);
		
		logger.trace("createGUI() - Leaving");
	}

	/**
	 * Returns the searchTextfield
	 * 
	 * @return {@link JTextField} The searchTextfield
	 */
	private JTextField getSearchTextfield() {
		logger.trace("getSearchTextfield() - Entering");
		if (searchTextField == null) {
			searchTextField = new JTextField();
			searchTextField.setPreferredSize(new Dimension(150, 30));
			searchTextField.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					getSearchButton().doClick();
				}
			});
		}
		logger.trace("getSearchTextfield() - Returning");
		logger.debug("getSearchTextfield() - Returning: {}", searchTextField);
		return searchTextField;
	}

	/**
	 * Returns the searchButton
	 * 
	 * @return {@link JButton} The searchButton
	 */
	private JButton getSearchButton() {
		logger.trace("getSearchButton() - Entering");
		if (searchButton == null) {
			searchButton = new JButton("Search");
			searchButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					List<Item> champs = controller.searchItems(searchTextField
							.getText());
					MainView.getInstance().setStatusText(
							champs.size() + " Search results");
					((ItemTableModel) itemTable.getModel()).setItems(champs);
				}
			});
		}
		logger.trace("getSearchButton() - Returning");
		logger.debug("getSearchButton() - Returning: {}", searchButton);
		return searchButton;
	}

	/**
	 * Returns the clearSearchButton
	 * 
	 * @return {@link JButton} The clearSearchButton
	 */
	private JButton getClearSearchButton() {
		logger.trace("getClearSearchButton() - Entering");
		if (clearSearchButton == null) {
			clearSearchButton = new JButton("Clear Filter");
			clearSearchButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					((ItemTableModel) itemTable.getModel()).setItems(controller
							.getItems());
				}
			});
		}
		logger.trace("getClearSearchButton() - Returning");
		logger.debug("getClearSearchButton() - Returning: {}",
				clearSearchButton);
		return clearSearchButton;
	}

	/**
	 * Returns the newButton
	 * 
	 * @return {@link JButton} The newButton
	 */
	private JButton getNewButton() {
		logger.trace("getNewButton() - Entering");
		if (newButton == null) {
			newButton = new JButton("new Item");
			newButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					newItemDialoge.setVisible(true);
				}
			});
		}
		logger.trace("getNewButton() - Returning");
		logger.debug("getNewButton() - Returning: {}", newButton);
		return newButton;
	}

	/**
	 * Returns the deleteButton
	 * 
	 * @return {@link JButton} The deleteButton
	 */
	private JButton getDeleteButton() {
		logger.trace("getDeleteButton() - Entering");
		if (deleteButton == null) {
			deleteButton = new JButton("delete Item");
			deleteButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (itemTable.getSelectedRow() != -1) {
						Item item = (Item) itemTable.getValueAt(
								itemTable.getSelectedRow(), 1);
						String message = "Are you sure to delete: "
								+ item.getName() + "?";
						String title = "Information";
						int ok = JOptionPane.showConfirmDialog(null, message,
								title, JOptionPane.YES_NO_OPTION);
						if (ok == JOptionPane.OK_OPTION) {
							ItemUtil.deleteItem(item);
							((ItemTableModel) itemTable.getModel())
									.removeItem(item);
							MainView.getInstance().setStatusText(
									"Item " + item + " removed");
						}
					} else {
						// TODO schöner!
						JOptionPane.showMessageDialog(MainView.getInstance(),
								"No Item selected");
					}
				}
			});
		}
		logger.trace("getDeleteButton() - Returning");
		logger.debug("getDeleteButton() - Returning: {}", deleteButton);
		return deleteButton;
	}

	/**
	 * Returns the itemTablePane
	 * 
	 * @return {@link JScrollPane} The itemTablePane
	 */
	private JScrollPane getItemTablePane() {
		logger.trace("getItemTablePane() - Entering");
		if (itemTablePane == null) {
			itemTablePane = new JScrollPane(getItemTable());
		}
		logger.trace("getItemTablePane() - Returning");
		logger.debug("getItemTablePane() - Returning: {}", itemTablePane);
		return itemTablePane;
	}

	/**
	 * Returns the itemTable
	 * 
	 * @return {@link JTable} The itemTable
	 */
	private JTable getItemTable() {
		logger.trace("getItemTable() - Entering");
		if (itemTable == null) {
			itemTable = new JTable();
			ItemTableModel m = new ItemTableModel(controller.getItems());
			itemTable.setModel(m);
			itemTable.setDefaultRenderer(Object.class,
					new ItemTableRenderer());
		}
		logger.trace("getItemTable() - Returning");
		logger.debug("getItemTable() - Returning: {}", itemTable);
		return itemTable;
	}

	/**
	 * Adds a new Item
	 * 
	 * @param item
	 *            The {@link Item item} to add
	 */
	public void addItem(Item item) {
		logger.trace("addItem() - Entering");
		logger.debug("addItem() - Parameter: {}", item);
		((ItemTableModel) itemTable.getModel()).addItem(item);
		logger.trace("addItem() - Leaving");
	}

	@Override
	public void refresh() {
		((ItemTableModel) itemTable.getModel()).fireTableDataChanged();
	}
}