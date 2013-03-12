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
	private JLabel myChampIconLabel;
	private JLabel myChampNameLabel;
	private JTabbedPane myChampItemsPanel;
	private JPanel myChampStartingItemsPanel;
	private JLabel myStartingItemsLabel;
	private JLabel myChampItem1Label;
	private JLabel myChampItem2Label;
	private JLabel myChampItem3Label;
	private JLabel myChampItem4Label;
	private JLabel myChampItem5Label;
	private JLabel myChampItem6Label;
	private JPanel myChampEndingItemsPanel;
	private JLabel myEndingItemsLabel;
	private JLabel myChampEndingItem1Label;
	private JLabel myChampEndingItem2Label;
	private JLabel myChampEndingItem3Label;
	private JLabel myChampEndingItem4Label;
	private JLabel myChampEndingItem5Label;
	private JLabel myChampEndingItem6Label;
	private JPanel myChampSpellsPanel;
	private JLabel myChampSpellsLabel;
	private JLabel myChampSpell1Label;
	private JLabel myChampSpell2Label;
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
		this.add(getMyChampNameLabel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(0, 1, 0, 0);
		this.add(getMyChampIconLabel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;
		this.add(getMyChampItemsPanel(), constraints);
		logger.trace("createGUI() - Leaving");
	}

	private JLabel getMyChampIconLabel() {
		logger.trace("getMyChampIconLabel() - Entering");
		if (myChampIconLabel == null) {
			myChampIconLabel = new JLabel();
		}
		logger.trace("getMyChampIconLabel() - Returning");
		logger.debug("getMyChampIconLabel() - Returning: {}", myChampIconLabel);
		return myChampIconLabel;
	}

	private JLabel getMyChampNameLabel() {
		logger.trace("getMyChampNameLabel() - Entering");
		if (myChampNameLabel == null) {
			myChampNameLabel = new JLabel();
			myChampNameLabel.setFont(new Font("", Font.PLAIN, 18));
			myChampNameLabel.setHorizontalAlignment(JLabel.CENTER);
		}
		logger.trace("getMyChampNameLabel() - Returning");
		logger.debug("getMyChampNameLabel() - Returning: {}", myChampNameLabel);
		return myChampNameLabel;
	}

	private JTabbedPane getMyChampItemsPanel() {
		logger.trace("getMyChampItemsPanel() - Entering");
		if (myChampItemsPanel == null) {
			myChampItemsPanel = new JTabbedPane();
			myChampItemsPanel.setTabPlacement(JTabbedPane.BOTTOM);
			myChampItemsPanel.addTab("1", getMyChampStartingItemsPanel());
			myChampItemsPanel.addTab("2", getMyChampEndingItemsPanel());
			myChampItemsPanel.addTab("3", getMyChampSpellsPanel());
		}
		logger.trace("getMyChampItemsPanel() - Returning");
		logger.debug("getMyChampItemsPanel() - Returning: {}",
				myChampItemsPanel);
		return myChampItemsPanel;
	}

	private JPanel getMyChampStartingItemsPanel() {
		logger.trace("getMyChampStartingItemsPanel() - Entering");
		if (myChampStartingItemsPanel == null) {
			myChampStartingItemsPanel = new JPanel(new GridBagLayout());

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 3;
			myChampStartingItemsPanel.add(getMyStartingItemsLabel(),
					constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			myChampStartingItemsPanel.add(getMyChampItem1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			myChampStartingItemsPanel.add(getMyChampItem2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			myChampStartingItemsPanel.add(getMyChampItem3Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			myChampStartingItemsPanel.add(getMyChampItem4Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			myChampStartingItemsPanel.add(getMyChampItem5Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			myChampStartingItemsPanel.add(getMyChampItem6Label(), constraints);
		}
		logger.trace("getMyChampStartingItemsPanel() - Returning");
		logger.debug("getMyChampStartingItemsPanel() - Returning: {}",
				myChampStartingItemsPanel);
		return myChampStartingItemsPanel;
	}

	private JLabel getMyStartingItemsLabel() {
		logger.trace("getMyStartingItemsLabel() - Entering");
		if (myStartingItemsLabel == null) {
			myStartingItemsLabel = new JLabel("Starting items");
		}
		logger.trace("getMyStartingItemsLabel() - Returning");
		logger.debug("getMyStartingItemsLabel() - Returning: {}",
				myStartingItemsLabel);
		return myStartingItemsLabel;
	}

	private JLabel getMyChampItem1Label() {
		logger.trace("getMyChampItem1Label() - Entering");
		if (myChampItem1Label == null) {
			myChampItem1Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampItem1Label() - Returning");
		logger.debug("getMyChampItem1Label() - Returning: {}",
				myChampItem1Label);
		return myChampItem1Label;
	}

	private JLabel getMyChampItem2Label() {
		logger.trace("getMyChampItem2Label() - Entering");
		if (myChampItem2Label == null) {
			myChampItem2Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampItem2Label() - Returning");
		logger.debug("getMyChampItem2Label() - Returning: {}",
				myChampItem2Label);
		return myChampItem2Label;
	}

	private JLabel getMyChampItem3Label() {
		logger.trace("getMyChampItem3Label() - Entering");
		if (myChampItem3Label == null) {
			myChampItem3Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampItem3Label() - Returning");
		logger.debug("getMyChampItem3Label() - Returning: {}",
				myChampItem3Label);
		return myChampItem3Label;
	}

	private JLabel getMyChampItem4Label() {
		logger.trace("getMyChampItem4Label() - Entering");
		if (myChampItem4Label == null) {
			myChampItem4Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampItem4Label() - Returning");
		logger.debug("getMyChampItem4Label() - Returning: {}",
				myChampItem4Label);
		return myChampItem4Label;
	}

	private JLabel getMyChampItem5Label() {
		logger.trace("getMyChampItem5Label() - Entering");
		if (myChampItem5Label == null) {
			myChampItem5Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampItem5Label() - Returning");
		logger.debug("getMyChampItem5Label() - Returning: {}",
				myChampItem5Label);
		return myChampItem5Label;
	}

	private JLabel getMyChampItem6Label() {
		logger.trace("getMyChampItem6Label() - Entering");
		if (myChampItem6Label == null) {
			myChampItem6Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampItem6Label() - Returning");
		logger.debug("getMyChampItem6Label() - Returning: {}",
				myChampItem6Label);
		return myChampItem6Label;
	}

	private JPanel getMyChampEndingItemsPanel() {
		logger.trace("getMyChampEndingItemsPanel() - Entering");
		if (myChampEndingItemsPanel == null) {
			myChampEndingItemsPanel = new JPanel(new GridBagLayout());

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 3;
			myChampEndingItemsPanel.add(getMyEndingItemsLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			myChampEndingItemsPanel.add(getMyChampEndingItem1Label(),
					constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			myChampEndingItemsPanel.add(getMyChampEndingItem2Label(),
					constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			myChampEndingItemsPanel.add(getMyChampEndingItem3Label(),
					constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			myChampEndingItemsPanel.add(getMyChampEndingItem4Label(),
					constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			myChampEndingItemsPanel.add(getMyChampEndingItem5Label(),
					constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			myChampEndingItemsPanel.add(getMyChampEndingItem6Label(),
					constraints);
		}
		logger.trace("getMyChampEndingItemsPanel() - Returning");
		logger.debug("getMyChampEndingItemsPanel() - Returning: {}",
				myChampEndingItemsPanel);
		return myChampEndingItemsPanel;
	}

	private JLabel getMyEndingItemsLabel() {
		logger.trace("getMyEndingItemsLabel() - Entering");
		if (myEndingItemsLabel == null) {
			myEndingItemsLabel = new JLabel("End items");
		}
		logger.trace("getMyEndingItemsLabel() - Returning");
		logger.debug("getMyEndingItemsLabel() - Returning: {}",
				myEndingItemsLabel);
		return myEndingItemsLabel;
	}

	private JLabel getMyChampEndingItem1Label() {
		logger.trace("getMyChampEndingItem1Label() - Entering");
		if (myChampEndingItem1Label == null) {
			myChampEndingItem1Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampEndingItem1Label() - Returning");
		logger.debug("getMyChampEndingItem1Label() - Returning: {}",
				myChampEndingItem1Label);
		return myChampEndingItem1Label;
	}

	private JLabel getMyChampEndingItem2Label() {
		logger.trace("getMyChampEndingItem2Label() - Entering");
		if (myChampEndingItem2Label == null) {
			myChampEndingItem2Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampEndingItem2Label() - Returning");
		logger.debug("getMyChampEndingItem2Label() - Returning: {}",
				myChampEndingItem2Label);
		return myChampEndingItem2Label;
	}

	private JLabel getMyChampEndingItem3Label() {
		logger.trace("getMyChampEndingItem3Label() - Entering");
		if (myChampEndingItem3Label == null) {
			myChampEndingItem3Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampEndingItem3Label() - Returning");
		logger.debug("getMyChampEndingItem3Label() - Returning: {}",
				myChampEndingItem3Label);
		return myChampEndingItem3Label;
	}

	private JLabel getMyChampEndingItem4Label() {
		logger.trace("getMyChampEndingItem4Label() - Entering");
		if (myChampEndingItem4Label == null) {
			myChampEndingItem4Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampEndingItem4Label() - Returning");
		logger.debug("getMyChampEndingItem4Label() - Returning: {}",
				myChampEndingItem4Label);
		return myChampEndingItem4Label;
	}

	private JLabel getMyChampEndingItem5Label() {
		logger.trace("getMyChampEndingItem5Label() - Entering");
		if (myChampEndingItem5Label == null) {
			myChampEndingItem5Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampEndingItem5Label() - Returning");
		logger.debug("getMyChampEndingItem5Label() - Returning: {}",
				myChampEndingItem5Label);
		return myChampEndingItem5Label;
	}

	private JLabel getMyChampEndingItem6Label() {
		logger.trace("getMyChampEndingItem6Label() - Entering");
		if (myChampEndingItem6Label == null) {
			myChampEndingItem6Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampEndingItem6Label() - Returning");
		logger.debug("getMyChampEndingItem6Label() - Returning: {}",
				myChampEndingItem6Label);
		return myChampEndingItem6Label;
	}

	private JPanel getMyChampSpellsPanel() {
		logger.trace("getMyChampSpellsPanel() - Entering");
		if (myChampSpellsPanel == null) {
			myChampSpellsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 2;
			myChampSpellsPanel.add(getMyChampSpellsLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 2, 2, 3);
			myChampSpellsPanel.add(getMyChampSpell1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.insets = new Insets(3, 2, 2, 2);
			myChampSpellsPanel.add(getMyChampSpell2Label(), constraints);
		}
		logger.trace("getMyChampSpellsPanel() - Returning");
		logger.debug("getMyChampSpellsPanel() - Returning: {}",
				myChampSpellsPanel);
		return myChampSpellsPanel;
	}

	private JLabel getMyChampSpellsLabel() {
		logger.trace("getMyChampSpellsLabel() - Entering");
		if (myChampSpellsLabel == null) {
			myChampSpellsLabel = new JLabel("Summoner spells");
		}
		logger.trace("getMyChampSpellsLabel() - Returning");
		logger.debug("getMyChampSpellsLabel() - Returning: {}",
				myChampSpellsLabel);
		return myChampSpellsLabel;
	}

	private JLabel getMyChampSpell1Label() {
		logger.trace("getMyChampSpell1Label() - Entering");
		if (myChampSpell1Label == null) {
			myChampSpell1Label = new JLabel(defaultImgBig);
		}
		logger.trace("getMyChampSpell1Label() - Returning");
		logger.debug("getMyChampSpell1Label() - Returning: {}",
				myChampSpell1Label);
		return myChampSpell1Label;
	}

	private JLabel getMyChampSpell2Label() {
		logger.trace("getMyChampSpell2Label() - Entering");
		if (myChampSpell2Label == null) {
			myChampSpell2Label = new JLabel(defaultImgBig);
		}
		logger.trace("getMyChampSpell2Label() - Returning");
		logger.debug("getMyChampSpell2Label() - Returning: {}",
				myChampSpell2Label);
		return myChampSpell2Label;
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
		this.getMyChampNameLabel().setText(champ.getName());
		this.getMyChampNameLabel().setOpaque(true);
		this.getMyChampNameLabel().setBackground(result.getColor());
		this.getMyChampIconLabel().setIcon(champ.getIcon());
		int x = 0;
		for (MatchupItem mi : matchupStartItems) {
			x++;
			JLabel l = null;
			switch (x) {
			case 1:
				l = this.getMyChampItem1Label();
				break;
			case 2:
				l = this.getMyChampItem2Label();
				break;
			case 3:
				l = this.getMyChampItem3Label();
				break;
			case 4:
				l = this.getMyChampItem4Label();
				break;
			case 5:
				l = this.getMyChampItem5Label();
				break;
			case 6:
				l = this.getMyChampItem6Label();
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
				l = this.getMyChampEndingItem1Label();
				break;
			case 2:
				l = this.getMyChampEndingItem2Label();
				break;
			case 3:
				l = this.getMyChampEndingItem3Label();
				break;
			case 4:
				l = this.getMyChampEndingItem4Label();
				break;
			case 5:
				l = this.getMyChampEndingItem5Label();
				break;
			case 6:
				l = this.getMyChampEndingItem6Label();
				break;
			}
			l.setIcon(ImageIconFactory.resizeImageIcon(mi.getItem().getIcon(),
					30, 30));
			l.setToolTipText(mi.getAmount() + "x " + mi.getItem().getName());
		}
		if (spell1 != null) {
			getMyChampSpell1Label().setIcon(
					ImageIconFactory.resizeImageIcon(spell1.getIcon(), 40, 40));
			getMyChampSpell2Label().setIcon(
					ImageIconFactory.resizeImageIcon(spell2.getIcon(), 40, 40));
		}
		logger.trace("setChampionAndItems() - Leaving");
	}
}