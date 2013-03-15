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
	private JTabbedPane champItemsPanel;
	private JPanel champStartingItemsPanel;
	private JLabel startingItemsLabel;
	private JLabel champItem1Label;
	private JLabel champItem2Label;
	private JLabel champItem3Label;
	private JLabel champItem4Label;
	private JLabel champItem5Label;
	private JLabel champItem6Label;
	private JPanel champEndingItemsPanel;
	private JLabel endingItemsLabel;
	private JLabel champEndingItem1Label;
	private JLabel champEndingItem2Label;
	private JLabel champEndingItem3Label;
	private JLabel champEndingItem4Label;
	private JLabel champEndingItem5Label;
	private JLabel champEndingItem6Label;
	private JPanel champSpellsPanel;
	private JLabel champSpellsLabel;
	private JLabel champSpell1Label;
	private JLabel champSpell2Label;
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
		this.add(getChampItemsPanel(), constraints);
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

	private JTabbedPane getChampItemsPanel() {
		logger.trace("getChampItemsPanel() - Entering");
		if (champItemsPanel == null) {
			champItemsPanel = new JTabbedPane();
			champItemsPanel.setTabPlacement(JTabbedPane.BOTTOM);
			champItemsPanel.addTab("1", getChampStartingItemsPanel());
			champItemsPanel.addTab("2", getChampEndingItemsPanel());
			champItemsPanel.addTab("3", getChampSpellsPanel());
		}
		logger.trace("getChampItemsPanel() - Returning");
		logger.debug("getChampItemsPanel() - Returning: {}", champItemsPanel);
		return champItemsPanel;
	}

	private JPanel getChampStartingItemsPanel() {
		logger.trace("getChampStartingItemsPanel() - Entering");
		if (champStartingItemsPanel == null) {
			champStartingItemsPanel = new JPanel(new GridBagLayout());

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 3;
			champStartingItemsPanel.add(getStartingItemsLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			champStartingItemsPanel.add(getChampItem1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			champStartingItemsPanel.add(getChampItem2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			champStartingItemsPanel.add(getChampItem3Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			champStartingItemsPanel.add(getChampItem4Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			champStartingItemsPanel.add(getChampItem5Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			champStartingItemsPanel.add(getChampItem6Label(), constraints);
		}
		logger.trace("getChampStartingItemsPanel() - Returning");
		logger.debug("getChampStartingItemsPanel() - Returning: {}",
				champStartingItemsPanel);
		return champStartingItemsPanel;
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

	private JLabel getChampItem1Label() {
		logger.trace("getChampItem1Label() - Entering");
		if (champItem1Label == null) {
			champItem1Label = new JLabel(defaultImg);
		}
		logger.trace("getChampItem1Label() - Returning");
		logger.debug("getChampItem1Label() - Returning: {}", champItem1Label);
		return champItem1Label;
	}

	private JLabel getChampItem2Label() {
		logger.trace("getChampItem2Label() - Entering");
		if (champItem2Label == null) {
			champItem2Label = new JLabel(defaultImg);
		}
		logger.trace("getChampItem2Label() - Returning");
		logger.debug("getChampItem2Label() - Returning: {}", champItem2Label);
		return champItem2Label;
	}

	private JLabel getChampItem3Label() {
		logger.trace("getChampItem3Label() - Entering");
		if (champItem3Label == null) {
			champItem3Label = new JLabel(defaultImg);
		}
		logger.trace("getChampItem3Label() - Returning");
		logger.debug("getChampItem3Label() - Returning: {}", champItem3Label);
		return champItem3Label;
	}

	private JLabel getChampItem4Label() {
		logger.trace("getChampItem4Label() - Entering");
		if (champItem4Label == null) {
			champItem4Label = new JLabel(defaultImg);
		}
		logger.trace("getChampItem4Label() - Returning");
		logger.debug("getChampItem4Label() - Returning: {}", champItem4Label);
		return champItem4Label;
	}

	private JLabel getChampItem5Label() {
		logger.trace("getChampItem5Label() - Entering");
		if (champItem5Label == null) {
			champItem5Label = new JLabel(defaultImg);
		}
		logger.trace("getChampItem5Label() - Returning");
		logger.debug("getChampItem5Label() - Returning: {}", champItem5Label);
		return champItem5Label;
	}

	private JLabel getChampItem6Label() {
		logger.trace("getChampItem6Label() - Entering");
		if (champItem6Label == null) {
			champItem6Label = new JLabel(defaultImg);
		}
		logger.trace("getChampItem6Label() - Returning");
		logger.debug("getChampItem6Label() - Returning: {}", champItem6Label);
		return champItem6Label;
	}

	private JPanel getChampEndingItemsPanel() {
		logger.trace("getChampEndingItemsPanel() - Entering");
		if (champEndingItemsPanel == null) {
			champEndingItemsPanel = new JPanel(new GridBagLayout());

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 3;
			champEndingItemsPanel.add(getEndingItemsLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			champEndingItemsPanel.add(getChampEndingItem1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			champEndingItemsPanel.add(getChampEndingItem2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			champEndingItemsPanel.add(getChampEndingItem3Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			champEndingItemsPanel.add(getChampEndingItem4Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			champEndingItemsPanel.add(getChampEndingItem5Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			champEndingItemsPanel.add(getChampEndingItem6Label(), constraints);
		}
		logger.trace("getChampEndingItemsPanel() - Returning");
		logger.debug("getChampEndingItemsPanel() - Returning: {}",
				champEndingItemsPanel);
		return champEndingItemsPanel;
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

	private JLabel getChampEndingItem1Label() {
		logger.trace("getChampEndingItem1Label() - Entering");
		if (champEndingItem1Label == null) {
			champEndingItem1Label = new JLabel(defaultImg);
		}
		logger.trace("getChampEndingItem1Label() - Returning");
		logger.debug("getChampEndingItem1Label() - Returning: {}",
				champEndingItem1Label);
		return champEndingItem1Label;
	}

	private JLabel getChampEndingItem2Label() {
		logger.trace("getChampEndingItem2Label() - Entering");
		if (champEndingItem2Label == null) {
			champEndingItem2Label = new JLabel(defaultImg);
		}
		logger.trace("getChampEndingItem2Label() - Returning");
		logger.debug("getChampEndingItem2Label() - Returning: {}",
				champEndingItem2Label);
		return champEndingItem2Label;
	}

	private JLabel getChampEndingItem3Label() {
		logger.trace("getChampEndingItem3Label() - Entering");
		if (champEndingItem3Label == null) {
			champEndingItem3Label = new JLabel(defaultImg);
		}
		logger.trace("getChampEndingItem3Label() - Returning");
		logger.debug("getChampEndingItem3Label() - Returning: {}",
				champEndingItem3Label);
		return champEndingItem3Label;
	}

	private JLabel getChampEndingItem4Label() {
		logger.trace("getChampEndingItem4Label() - Entering");
		if (champEndingItem4Label == null) {
			champEndingItem4Label = new JLabel(defaultImg);
		}
		logger.trace("getChampEndingItem4Label() - Returning");
		logger.debug("getChampEndingItem4Label() - Returning: {}",
				champEndingItem4Label);
		return champEndingItem4Label;
	}

	private JLabel getChampEndingItem5Label() {
		logger.trace("getChampEndingItem5Label() - Entering");
		if (champEndingItem5Label == null) {
			champEndingItem5Label = new JLabel(defaultImg);
		}
		logger.trace("getChampEndingItem5Label() - Returning");
		logger.debug("getChampEndingItem5Label() - Returning: {}",
				champEndingItem5Label);
		return champEndingItem5Label;
	}

	private JLabel getChampEndingItem6Label() {
		logger.trace("getChampEndingItem6Label() - Entering");
		if (champEndingItem6Label == null) {
			champEndingItem6Label = new JLabel(defaultImg);
		}
		logger.trace("getChampEndingItem6Label() - Returning");
		logger.debug("getChampEndingItem6Label() - Returning: {}",
				champEndingItem6Label);
		return champEndingItem6Label;
	}

	private JPanel getChampSpellsPanel() {
		logger.trace("getChampSpellsPanel() - Entering");
		if (champSpellsPanel == null) {
			champSpellsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 2;
			champSpellsPanel.add(getChampSpellsLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 2, 2, 3);
			champSpellsPanel.add(getChampSpell1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.insets = new Insets(3, 2, 2, 2);
			champSpellsPanel.add(getChampSpell2Label(), constraints);
		}
		logger.trace("getChampSpellsPanel() - Returning");
		logger.debug("getChampSpellsPanel() - Returning: {}", champSpellsPanel);
		return champSpellsPanel;
	}

	private JLabel getChampSpellsLabel() {
		logger.trace("getChampSpellsLabel() - Entering");
		if (champSpellsLabel == null) {
			champSpellsLabel = new JLabel("Summoner spells");
		}
		logger.trace("getChampSpellsLabel() - Returning");
		logger.debug("getChampSpellsLabel() - Returning: {}", champSpellsLabel);
		return champSpellsLabel;
	}

	private JLabel getChampSpell1Label() {
		logger.trace("getChampSpell1Label() - Entering");
		if (champSpell1Label == null) {
			champSpell1Label = new JLabel(defaultImgBig);
		}
		logger.trace("getChampSpell1Label() - Returning");
		logger.debug("getChampSpell1Label() - Returning: {}", champSpell1Label);
		return champSpell1Label;
	}

	private JLabel getChampSpell2Label() {
		logger.trace("getChampSpell2Label() - Entering");
		if (champSpell2Label == null) {
			champSpell2Label = new JLabel(defaultImgBig);
		}
		logger.trace("getChampSpell2Label() - Returning");
		logger.debug("getChampSpell2Label() - Returning: {}", champSpell2Label);
		return champSpell2Label;
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
				l = this.getChampItem1Label();
				break;
			case 2:
				l = this.getChampItem2Label();
				break;
			case 3:
				l = this.getChampItem3Label();
				break;
			case 4:
				l = this.getChampItem4Label();
				break;
			case 5:
				l = this.getChampItem5Label();
				break;
			case 6:
				l = this.getChampItem6Label();
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
				l = this.getChampEndingItem1Label();
				break;
			case 2:
				l = this.getChampEndingItem2Label();
				break;
			case 3:
				l = this.getChampEndingItem3Label();
				break;
			case 4:
				l = this.getChampEndingItem4Label();
				break;
			case 5:
				l = this.getChampEndingItem5Label();
				break;
			case 6:
				l = this.getChampEndingItem6Label();
				break;
			}
			l.setIcon(ImageIconFactory.resizeImageIcon(mi.getItem().getIcon(),
					30, 30));
			l.setToolTipText(mi.getAmount() + "x " + mi.getItem().getName());
		}
		if (spell1 != null) {
			getChampSpell1Label().setIcon(
					ImageIconFactory.resizeImageIcon(spell1.getIcon(), 40, 40));
			getChampSpell2Label().setIcon(
					ImageIconFactory.resizeImageIcon(spell2.getIcon(), 40, 40));
		}
		logger.trace("setChampionAndItems() - Leaving");
	}

	public void clear() {
		// Clearing Icons of starting items
		champItem1Label.setIcon(defaultImg);
		champItem2Label.setIcon(defaultImg);
		champItem3Label.setIcon(defaultImg);
		champItem4Label.setIcon(defaultImg);
		champItem5Label.setIcon(defaultImg);
		champItem6Label.setIcon(defaultImg);
		// Clearing Icons of end items
		champEndingItem1Label.setIcon(defaultImg);
		champEndingItem2Label.setIcon(defaultImg);
		champEndingItem3Label.setIcon(defaultImg);
		champEndingItem4Label.setIcon(defaultImg);
		champEndingItem5Label.setIcon(defaultImg);
		champEndingItem6Label.setIcon(defaultImg);

		getChampItemsPanel().setSelectedIndex(0);
	}
}