package de.phyberapex.diaryoflegends.view.dialoge.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.model.MatchupItem;

public class NewEntryItemBuildPanel extends JPanel {

	private List<NewEntryItemPanel> itemPanelList;
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager
			.getLogger(NewEntryItemBuildPanel.class.getName());

	public NewEntryItemBuildPanel() {
		super(new GridBagLayout());
		createGUI();
	}

	private void createGUI() {
		GridBagConstraints constraints;
		int x = 0;
		for (NewEntryItemPanel panel : getItemPanelList()) {
			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = x;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			add(panel, constraints);
			x++;
		}
	}

	private List<NewEntryItemPanel> getItemPanelList() {
		logger.trace("getItemPanelList() - Entering");
		if (itemPanelList == null) {
			itemPanelList = new ArrayList<>();
			for (int i = 0; i < 6; i++) {
				itemPanelList.add(new NewEntryItemPanel(i + 1));
			}
		}
		logger.trace("getItemPanelList() - Returning");
		logger.debug("getItemPanelList() - Returning: {}", itemPanelList);
		return itemPanelList;
	}

	public void clear() {
		logger.trace("clear() - Entering");
		for (NewEntryItemPanel panel : getItemPanelList()) {
			panel.clear();
		}
		logger.trace("clear() - Leaving");
	}

	public List<MatchupItem> getItems() {
		logger.trace("getItems() - Entering");
		List<MatchupItem> returnValue = new ArrayList<>();
		for (NewEntryItemPanel panel : getItemPanelList()) {
			MatchupItem mi = panel.getMatchupItem();
			if (mi != null) {
				returnValue.add(mi);
			}

		}
		logger.trace("getItems() - Returning");
		logger.debug("getItems() - Returning: {}", returnValue);
		return returnValue;
	}

	public void setItems(List<MatchupItem> items) {
		logger.trace("setItems() - Entering");
		logger.debug("setItems() - Parameter: {}", items);
		for (int i = 0; i < items.size() && i < 6; i++) {
			getItemPanelList().get(i).setMatchupItem(items.get(i));
		}
		logger.trace("setItems() - Leaving");
	}
}
