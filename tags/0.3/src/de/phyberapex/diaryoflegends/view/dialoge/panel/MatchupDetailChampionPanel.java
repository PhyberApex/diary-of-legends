package de.phyberapex.diaryoflegends.view.dialoge.panel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.extra.ImageIconFactory;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.MatchupItem;
import de.phyberapex.diaryoflegends.model.MatchupResult;
import de.phyberapex.diaryoflegends.model.SummonerSpell;

public class MatchupDetailChampionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel champIconLabel;
	private JLabel champNameLabel;
	private JTabbedPane itemsPanel;
	private JPanel startingItemsPanel;
	private JLabel startingItemsLabel;
	private JLabel startingItem1Label;
	private JLabel startingItem2Label;
	private JLabel startingItem3Label;
	private JLabel startingItem4Label;
	private JLabel startingItem5Label;
	private JLabel startingItem6Label;
	private JPanel endingItemsPanel;
	private JLabel endingItemsLabel;
	private JLabel endingItem1Label;
	private JLabel endingItem2Label;
	private JLabel endingItem3Label;
	private JLabel endingItem4Label;
	private JLabel endingItem5Label;
	private JLabel endingItem6Label;
	private JPanel spellsPanel;
	private JLabel spellsLabel;
	private JLabel spell1Label;
	private JLabel spell2Label;
	private static Logger logger = LogManager
			.getLogger(MatchupDetailChampionPanel.class.getName());
	private ImageIcon defaultImg = ImageIconFactory.resizeImageIcon(
			ImageIconFactory
					.createImageIconFromResourePath("img/empty_60x60.png"), 30,
			30);
	private ImageIcon defaultImgBig = ImageIconFactory.resizeImageIcon(
			ImageIconFactory
					.createImageIconFromResourePath("img/empty_60x60.png"), 50,
			50);

	public MatchupDetailChampionPanel() {
		super(new GridBagLayout());
		logger.trace("MatchupDetailChampionPanel() - Entering");
		createGUI();
		logger.trace("MatchupDetailChampionPanel() - Leaving");
	}

	private void createGUI() {
		logger.trace("createGUI() - Entering");
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(getChampNameLabel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(0, 1, 0, 0);
		this.add(getChampIconLabel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;
		this.add(getItemsPanel(), constraints);
		logger.trace("createGUI() - Leaving");
	}

	private JLabel getChampIconLabel() {
		logger.trace("getChampIconLabel() - Entering");
		if (champIconLabel == null) {
			champIconLabel = new JLabel();
		}
		logger.trace("getChampIconLabel() - Returning");
		logger.debug("getChampIconLabel() - Returning: {}", champIconLabel);
		return champIconLabel;
	}

	private JLabel getChampNameLabel() {
		logger.trace("getChampNameLabel() - Entering");
		if (champNameLabel == null) {
			champNameLabel = new JLabel();
			champNameLabel.setFont(new Font("", Font.PLAIN, 18));
			champNameLabel.setHorizontalAlignment(JLabel.CENTER);
		}
		logger.trace("getChampNameLabel() - Returning");
		logger.debug("getChampNameLabel() - Returning: {}", champNameLabel);
		return champNameLabel;
	}

	private JTabbedPane getItemsPanel() {
		logger.trace("getItemsPanel() - Entering");
		if (itemsPanel == null) {
			itemsPanel = new JTabbedPane();
			itemsPanel.setTabPlacement(JTabbedPane.BOTTOM);
			itemsPanel.addTab("1", getStartingItemsPanel());
			itemsPanel.addTab("2", getEndingItemsPanel());
			itemsPanel.addTab("3", getSpellsPanel());
		}
		logger.trace("getItemsPanel() - Returning");
		logger.debug("getItemsPanel() - Returning: {}", itemsPanel);
		return itemsPanel;
	}

	private JPanel getStartingItemsPanel() {
		logger.trace("getStartingItemsPanel() - Entering");
		if (startingItemsPanel == null) {
			startingItemsPanel = new JPanel(new GridBagLayout());

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 3;
			startingItemsPanel.add(getStartingItemsLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			startingItemsPanel.add(getStartingItem1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			startingItemsPanel.add(getStartingItem2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			startingItemsPanel.add(getStartingItem3Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			startingItemsPanel.add(getStartingItem4Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			startingItemsPanel.add(getStartingItem5Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			startingItemsPanel.add(getStartingItem6Label(), constraints);
		}
		logger.trace("getStartingItemsPanel() - Returning");
		logger.debug("getStartingItemsPanel() - Returning: {}",
				startingItemsPanel);
		return startingItemsPanel;
	}

	private JLabel getStartingItemsLabel() {
		logger.trace("getStartingItemsLabel() - Entering");
		if (startingItemsLabel == null) {
			startingItemsLabel = new JLabel("Starting items");
		}
		logger.trace("getStartingItemsLabel() - Returning");
		logger.debug("getStartingItemsLabel() - Returning: {}",
				startingItemsLabel);
		return startingItemsLabel;
	}

	private JLabel getStartingItem1Label() {
		logger.trace("getStartingItem1Label() - Entering");
		if (startingItem1Label == null) {
			startingItem1Label = new JLabel(defaultImg);
		}
		logger.trace("getStartingItem1Label() - Returning");
		logger.debug("getStartingItem1Label() - Returning: {}",
				startingItem1Label);
		return startingItem1Label;
	}

	private JLabel getStartingItem2Label() {
		logger.trace("getStartingItem2Label() - Entering");
		if (startingItem2Label == null) {
			startingItem2Label = new JLabel(defaultImg);
		}
		logger.trace("getStartingItem2Label() - Returning");
		logger.debug("getStartingItem2Label() - Returning: {}",
				startingItem2Label);
		return startingItem2Label;
	}

	private JLabel getStartingItem3Label() {
		logger.trace("getStartingItem3Label() - Entering");
		if (startingItem3Label == null) {
			startingItem3Label = new JLabel(defaultImg);
		}
		logger.trace("getStartingItem3Label() - Returning");
		logger.debug("getStartingItem3Label() - Returning: {}",
				startingItem3Label);
		return startingItem3Label;
	}

	private JLabel getStartingItem4Label() {
		logger.trace("getStartingItem4Label() - Entering");
		if (startingItem4Label == null) {
			startingItem4Label = new JLabel(defaultImg);
		}
		logger.trace("getStartingItem4Label() - Returning");
		logger.debug("getStartingItem4Label() - Returning: {}",
				startingItem4Label);
		return startingItem4Label;
	}

	private JLabel getStartingItem5Label() {
		logger.trace("getStartingItem5Label() - Entering");
		if (startingItem5Label == null) {
			startingItem5Label = new JLabel(defaultImg);
		}
		logger.trace("getStartingItem5Label() - Returning");
		logger.debug("getStartingItem5Label() - Returning: {}",
				startingItem5Label);
		return startingItem5Label;
	}

	private JLabel getStartingItem6Label() {
		logger.trace("getStartingItem6Label() - Entering");
		if (startingItem6Label == null) {
			startingItem6Label = new JLabel(defaultImg);
		}
		logger.trace("getStartingItem6Label() - Returning");
		logger.debug("getStartingItem6Label() - Returning: {}",
				startingItem6Label);
		return startingItem6Label;
	}

	private JPanel getEndingItemsPanel() {
		logger.trace("getEndingItemsPanel() - Entering");
		if (endingItemsPanel == null) {
			endingItemsPanel = new JPanel(new GridBagLayout());

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 3;
			endingItemsPanel.add(getEndingItemsLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			endingItemsPanel.add(getEndingItem1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			endingItemsPanel.add(getEndingItem2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			endingItemsPanel.add(getEndingItem3Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			endingItemsPanel.add(getEndingItem4Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			endingItemsPanel.add(getEndingItem5Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			endingItemsPanel.add(getEndingItem6Label(), constraints);
		}
		logger.trace("getEndingItemsPanel() - Returning");
		logger.debug("getEndingItemsPanel() - Returning: {}", endingItemsPanel);
		return endingItemsPanel;
	}

	private JLabel getEndingItemsLabel() {
		logger.trace("getEndingItemsLabel() - Entering");
		if (endingItemsLabel == null) {
			endingItemsLabel = new JLabel("End items");
		}
		logger.trace("getEndingItemsLabel() - Returning");
		logger.debug("getEndingItemsLabel() - Returning: {}", endingItemsLabel);
		return endingItemsLabel;
	}

	private JLabel getEndingItem1Label() {
		logger.trace("getEndingItem1Label() - Entering");
		if (endingItem1Label == null) {
			endingItem1Label = new JLabel(defaultImg);
		}
		logger.trace("getEndingItem1Label() - Returning");
		logger.debug("getEndingItem1Label() - Returning: {}", endingItem1Label);
		return endingItem1Label;
	}

	private JLabel getEndingItem2Label() {
		logger.trace("getEndingItem2Label() - Entering");
		if (endingItem2Label == null) {
			endingItem2Label = new JLabel(defaultImg);
		}
		logger.trace("getEndingItem2Label() - Returning");
		logger.debug("getEndingItem2Label() - Returning: {}", endingItem2Label);
		return endingItem2Label;
	}

	private JLabel getEndingItem3Label() {
		logger.trace("getEndingItem3Label() - Entering");
		if (endingItem3Label == null) {
			endingItem3Label = new JLabel(defaultImg);
		}
		logger.trace("getEndingItem3Label() - Returning");
		logger.debug("getEndingItem3Label() - Returning: {}", endingItem3Label);
		return endingItem3Label;
	}

	private JLabel getEndingItem4Label() {
		logger.trace("getEndingItem4Label() - Entering");
		if (endingItem4Label == null) {
			endingItem4Label = new JLabel(defaultImg);
		}
		logger.trace("getEndingItem4Label() - Returning");
		logger.debug("getEndingItem4Label() - Returning: {}", endingItem4Label);
		return endingItem4Label;
	}

	private JLabel getEndingItem5Label() {
		logger.trace("getEndingItem5Label() - Entering");
		if (endingItem5Label == null) {
			endingItem5Label = new JLabel(defaultImg);
		}
		logger.trace("getEndingItem5Label() - Returning");
		logger.debug("getEndingItem5Label() - Returning: {}", endingItem5Label);
		return endingItem5Label;
	}

	private JLabel getEndingItem6Label() {
		logger.trace("getEndingItem6Label() - Entering");
		if (endingItem6Label == null) {
			endingItem6Label = new JLabel(defaultImg);
		}
		logger.trace("getEndingItem6Label() - Returning");
		logger.debug("getEndingItem6Label() - Returning: {}", endingItem6Label);
		return endingItem6Label;
	}

	private JPanel getSpellsPanel() {
		logger.trace("getSpellsPanel() - Entering");
		if (spellsPanel == null) {
			spellsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 2;
			spellsPanel.add(getSpellsLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 2, 2, 3);
			spellsPanel.add(getSpell1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.insets = new Insets(3, 2, 2, 2);
			spellsPanel.add(getSpell2Label(), constraints);
		}
		logger.trace("getSpellsPanel() - Returning");
		logger.debug("getSpellsPanel() - Returning: {}", spellsPanel);
		return spellsPanel;
	}

	private JLabel getSpellsLabel() {
		logger.trace("getSpellsLabel() - Entering");
		if (spellsLabel == null) {
			spellsLabel = new JLabel("Summoner spells");
		}
		logger.trace("getSpellsLabel() - Returning");
		logger.debug("getSpellsLabel() - Returning: {}", spellsLabel);
		return spellsLabel;
	}

	private JLabel getSpell1Label() {
		logger.trace("getSpell1Label() - Entering");
		if (spell1Label == null) {
			spell1Label = new JLabel(defaultImgBig);
		}
		logger.trace("getSpell1Label() - Returning");
		logger.debug("getSpell1Label() - Returning: {}", spell1Label);
		return spell1Label;
	}

	private JLabel getSpell2Label() {
		logger.trace("getSpell2Label() - Entering");
		if (spell2Label == null) {
			spell2Label = new JLabel(defaultImgBig);
		}
		logger.trace("getSpell2Label() - Returning");
		logger.debug("getSpell2Label() - Returning: {}", spell2Label);
		return spell2Label;
	}

	public void setChampionAndItems(Champion champ,
			List<MatchupItem> matchupStartItems,
			List<MatchupItem> matchupEndItems, SummonerSpell spell1,
			SummonerSpell spell2, MatchupResult result) {
		logger.trace("setChampionAndItems() - Entering");
		logger.debug(
				"setChampionAndItems() - Parameter: {}, {}, {}, {}, {}, {}",
				champ, matchupStartItems, matchupEndItems, spell1, spell2,
				result);
		this.getChampNameLabel().setText(champ.getName());
		this.getChampNameLabel().setOpaque(true);
		this.getChampNameLabel().setBackground(result.getColor());
		this.getChampIconLabel().setIcon(champ.getIcon());
		int x = 0;
		for (MatchupItem mi : matchupStartItems) {
			x++;
			JLabel l = null;
			switch (x) {
			case 1:
				l = this.getStartingItem1Label();
				break;
			case 2:
				l = this.getStartingItem2Label();
				break;
			case 3:
				l = this.getStartingItem3Label();
				break;
			case 4:
				l = this.getStartingItem4Label();
				break;
			case 5:
				l = this.getStartingItem5Label();
				break;
			case 6:
				l = this.getStartingItem6Label();
				break;
			}
			l.setIcon(ImageIconFactory.resizeImageIcon(mi.getItem().getIcon(),
					30, 30));
			l.setToolTipText(mi.getAmount() + "x " + mi.getItem().getName());
		}
		x = 0;
		for (MatchupItem mi : matchupEndItems) {
			x++;
			JLabel l = null;
			switch (x) {
			case 1:
				l = this.getEndingItem1Label();
				break;
			case 2:
				l = this.getEndingItem2Label();
				break;
			case 3:
				l = this.getEndingItem3Label();
				break;
			case 4:
				l = this.getEndingItem4Label();
				break;
			case 5:
				l = this.getEndingItem5Label();
				break;
			case 6:
				l = this.getEndingItem6Label();
				break;
			}
			l.setIcon(ImageIconFactory.resizeImageIcon(mi.getItem().getIcon(),
					30, 30));
			l.setToolTipText(mi.getAmount() + "x " + mi.getItem().getName());
		}
		if (spell1 != null) {
			getSpell1Label().setIcon(
					ImageIconFactory.resizeImageIcon(spell1.getIcon(), 40, 40));
			getSpell2Label().setIcon(
					ImageIconFactory.resizeImageIcon(spell2.getIcon(), 40, 40));
		}
		logger.trace("setChampionAndItems() - Leaving");
	}

	public void clear() {
		// Clearing Icons of starting items
		startingItem1Label.setIcon(defaultImg);
		startingItem1Label.setToolTipText("");
		startingItem2Label.setIcon(defaultImg);
		startingItem2Label.setToolTipText("");
		startingItem3Label.setIcon(defaultImg);
		startingItem3Label.setToolTipText("");
		startingItem4Label.setIcon(defaultImg);
		startingItem4Label.setToolTipText("");
		startingItem5Label.setIcon(defaultImg);
		startingItem5Label.setToolTipText("");
		startingItem6Label.setIcon(defaultImg);
		startingItem6Label.setToolTipText("");
		// Clearing Icons of end items
		endingItem1Label.setIcon(defaultImg);
		endingItem1Label.setToolTipText("");
		endingItem2Label.setIcon(defaultImg);
		endingItem2Label.setToolTipText("");
		endingItem3Label.setIcon(defaultImg);
		endingItem3Label.setToolTipText("");
		endingItem4Label.setIcon(defaultImg);
		endingItem4Label.setToolTipText("");
		endingItem5Label.setIcon(defaultImg);
		endingItem5Label.setToolTipText("");
		endingItem6Label.setIcon(defaultImg);
		endingItem6Label.setToolTipText("");

		getItemsPanel().setSelectedIndex(0);
	}
}