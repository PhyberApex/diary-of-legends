package de.phyberapex.diaryoflegends.view.dialoge.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.model.Item;
import de.phyberapex.diaryoflegends.model.MatchupItem;
import de.phyberapex.diaryoflegends.model.util.ItemUtil;
import de.phyberapex.diaryoflegends.view.dialoge.comboBox.ItemComboBox;

public class NewEntryItemPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int numberOfItem;
	private JSpinner itemAmountSpinner;
	private ItemComboBox itemBox;
	private JLabel itemXLabel;
	private JLabel xLabel;
	private static List<Item> allItems = new ArrayList<>();;
	private Logger logger = LogManager.getLogger(NewEntryItemPanel.class
			.getName());

	static {
		Item i = new Item(0, "no item");
		allItems.add(i);
		allItems.addAll(ItemUtil.getAllItems());
	}

	public NewEntryItemPanel(int numberOfItem) {
		super(new GridBagLayout());
		this.numberOfItem = numberOfItem;
		createGUI();
	}

	private void createGUI() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.add(getItemXLabel());

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 0;
		this.add(getItemAmountSpinner(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 0;
		this.add(getxLabel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 3;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		this.add(getItemBox(), constraints);
	}

	private ItemComboBox getItemBox() {
		logger.trace("getItemBox() - Entering");
		if (itemBox == null) {
			itemBox = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getItemBox() - Returning");
		logger.debug("getItemBox() - Returning: {}", itemBox);
		return itemBox;
	}

	private JSpinner getItemAmountSpinner() {
		logger.trace("getItemAmountSpinner() - Entering");
		if (itemAmountSpinner == null) {
			itemAmountSpinner = new JSpinner();
			itemAmountSpinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		}
		logger.trace("getItemAmountSpinner() - Returning");
		logger.debug("getItemAmountSpinner() - Returning: {}",
				itemAmountSpinner);
		return itemAmountSpinner;
	}

	private JLabel getItemXLabel() {
		logger.trace("getItemXLabel() - Entering");
		if (itemXLabel == null) {
			itemXLabel = new JLabel("Item slot " + numberOfItem);
		}
		logger.trace("getItemXLabel() - Returning");
		logger.debug("getItemXLabel() - Returning: {}", itemXLabel);
		return itemXLabel;
	}

	private JLabel getxLabel() {
		logger.trace("getxLabel() - Entering");
		if (xLabel == null) {
			xLabel = new JLabel("x");
		}
		logger.trace("getxLabel() - Returning");
		logger.debug("getxLabel() - Returning: {}", xLabel);
		return xLabel;
	}

	public MatchupItem getMatchupItem() {
		logger.trace("setMatchupItem() - Entering");
		MatchupItem mi = null;
		if ((int) getItemAmountSpinner().getValue() != 0) {
			mi = new MatchupItem(getItemBox().getItemAt(
					getItemBox().getSelectedIndex()),
					(int) getItemAmountSpinner().getValue());
		}
		logger.trace("setMatchupItem() - Returning");
		logger.debug("getItemAmountSpinner() - Returning: {}", mi);
		return mi;
	}

	public void setMatchupItem(MatchupItem item) {
		logger.trace("setMatchupItem() - Entering");
		logger.debug("getItemAmountSpinner() - Parameter: {}", item);
		getItemAmountSpinner().setValue(item.getAmount());
		getItemBox().setSelectedItem(item.getItem());
		logger.trace("setMatchupItem() - Leaving");
	}

	public void clear() {
		getItemAmountSpinner().setValue(0);
		getItemBox().setSelectedIndex(0);
	}
}
