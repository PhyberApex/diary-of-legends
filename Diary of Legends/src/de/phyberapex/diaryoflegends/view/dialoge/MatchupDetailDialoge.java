package de.phyberapex.diaryoflegends.view.dialoge;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.extra.ImageIconFactory;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.Game;
import de.phyberapex.diaryoflegends.model.Matchup;
import de.phyberapex.diaryoflegends.model.MatchupItem;
import de.phyberapex.diaryoflegends.model.MatchupResult;
import de.phyberapex.diaryoflegends.model.util.MatchupUtil;
import de.phyberapex.diaryoflegends.view.View;

public class MatchupDetailDialoge extends JDialog implements View {

	private static final long serialVersionUID = -7485492927311999351L;
	private JPanel matchupContentPanel;
	private JPanel headPanel;
	private JLabel headlineLabel;
	private JLabel difficultyLabel;
	private JLabel difficultyValueLabel;
	private JPanel myChampPanel;
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
	private JLabel versusLabel;
	private JPanel enemyChampPanel;
	private JLabel enemyChampIconLabel;
	private JLabel enemyChampNameLabel;
	private JTabbedPane enemyChampItemsPanel;
	private JPanel enemyChampStartingItemsPanel;
	private JLabel enemyStartingItemsLabel;
	private JLabel enemyChampItem1Label;
	private JLabel enemyChampItem2Label;
	private JLabel enemyChampItem3Label;
	private JLabel enemyChampItem4Label;
	private JLabel enemyChampItem5Label;
	private JLabel enemyChampItem6Label;
	private JPanel enemyChampEndingItemsPanel;
	private JLabel enemyEndingLabel;
	private JLabel enemyChampEndingItem1Label;
	private JLabel enemyChampEndingItem2Label;
	private JLabel enemyChampEndingItem3Label;
	private JLabel enemyChampEndingItem4Label;
	private JLabel enemyChampEndingItem5Label;
	private JLabel enemyChampEndingItem6Label;
	private JPanel enemyChampSpellsPanel;
	private JLabel enemyChampSpellsLabel;
	private JLabel enemyChampSpell1Label;
	private JLabel enemyChampSpell2Label;
	private JPanel notesPanel;
	private JLabel notesLabel;
	private JScrollPane notesScrollPane;
	private JTextArea notesTextarea;
	private JButton saveNotesButton;
	private JPanel gamePanel;
	private JLabel gameStatsLabel;
	private JLabel killsLabel;
	private JLabel killsValueLabel;
	private JLabel deathsLabel;
	private JLabel deathsValueLabel;
	private JLabel assistsLabel;
	private JLabel assistsValueLabel;
	private JLabel csLabel;
	private JLabel csLabelValue;
	private JPanel gameChampsPanel;
	private JLabel gameChamp1Label;
	private JLabel gameChamp2Label;
	private JLabel gameChamp3Label;
	private JLabel gameChamp4Label;
	private JLabel gameChamp5Label;
	private JLabel gameChampVersusLabel;
	private JLabel gameChamp6Label;
	private JLabel gameChamp7Label;
	private JLabel gameChamp8Label;
	private JLabel gameChamp9Label;
	private JLabel gameChamp10Label;
	private JButton closeButton;
	private Matchup matchup;
	private ImageIcon defaultImg = ImageIconFactory.resizeImageIcon(
			ImageIconFactory
					.createImageIconFromResourePath("img/empty_60x60.png"), 30,
			30);

	private ImageIcon defaultImgBig = ImageIconFactory.resizeImageIcon(
			ImageIconFactory
					.createImageIconFromResourePath("img/empty_60x60.png"), 50,
			50);

	private static Logger logger = LogManager
			.getLogger(MatchupDetailDialoge.class.getName());

	public MatchupDetailDialoge() {
		super();
		createGUI();
	}

	private void createGUI() {
		this.setContentPane(getMatchupContentPanel());
		this.setUndecorated(true);
	}

	public JPanel getMatchupContentPanel() {
		logger.trace("getMatchupContentPanel() - Entering");
		if (matchupContentPanel == null) {
			matchupContentPanel = new JPanel(new GridBagLayout());
			matchupContentPanel.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.RAISED));

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 3;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			matchupContentPanel.add(getHeadPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(10, 10, 10, 10);
			matchupContentPanel.add(getMyChampPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.insets = new Insets(10, 10, 10, 10);
			matchupContentPanel.add(getVersusLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 1;
			constraints.insets = new Insets(10, 10, 10, 10);
			matchupContentPanel.add(getEnemyChampPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.fill = GridBagConstraints.BOTH;
			matchupContentPanel.add(getNotesPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			matchupContentPanel.add(getGamePanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 2;
			matchupContentPanel.add(getGameChampsPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			constraints.gridwidth = 3;
			matchupContentPanel.add(getCloseButton(), constraints);
		}
		logger.trace("getMatchupContentPanel() - Returning");
		logger.debug("getMatchupContentPanel() - Returning: {}",
				matchupContentPanel);
		return matchupContentPanel;
	}

	public JPanel getHeadPanel() {
		logger.trace("getHeadPanel() - Entering");
		if (headPanel == null) {
			headPanel = new JPanel(new GridBagLayout());
			headPanel.setBorder(BorderFactory.createBevelBorder(1));

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 2;
			constraints.weightx = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			headPanel.add(getHeadlineLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.5;
			constraints.anchor = GridBagConstraints.EAST;
			headPanel.add(getDifficultyLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.5;
			headPanel.add(getDifficultyValueLabel(), constraints);
		}
		logger.trace("getHeadPanel() - Returning");
		logger.debug("getHeadPanel() - Returning: {}", headPanel);
		return headPanel;
	}

	public JLabel getHeadlineLabel() {
		logger.trace("getHeadlineLabel() - Entering");
		if (headlineLabel == null) {
			headlineLabel = new JLabel();
			headlineLabel.setFont(new Font("", Font.PLAIN, 24));
			headlineLabel.setOpaque(true);
			headlineLabel.setHorizontalAlignment(JLabel.CENTER);
		}
		logger.trace("getHeadlineLabel() - Returning");
		logger.debug("getHeadlineLabel() - Returning: {}", headlineLabel);
		return headlineLabel;
	}

	public JLabel getDifficultyLabel() {
		logger.trace("getDifficultyLabel() - Entering");
		if (difficultyLabel == null) {
			difficultyLabel = new JLabel("Difficulty:");
			difficultyLabel.setHorizontalAlignment(JLabel.RIGHT);
		}
		logger.trace("getDifficultyLabel() - Returning");
		logger.debug("getDifficultyLabel() - Returning: {}", difficultyLabel);
		return difficultyLabel;
	}

	public JLabel getDifficultyValueLabel() {
		logger.trace("getDifficultyValueLabel() - Entering");
		if (difficultyValueLabel == null) {
			difficultyValueLabel = new JLabel();
		}
		logger.trace("getDifficultyValueLabel() - Returning");
		logger.debug("getDifficultyValueLabel() - Returning: {}",
				difficultyValueLabel);
		return difficultyValueLabel;
	}

	public JPanel getMyChampPanel() {
		logger.trace("getMyChampPanel() - Entering");
		if (myChampPanel == null) {
			myChampPanel = new JPanel(new GridBagLayout());
			myChampPanel.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.LOWERED));
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 2;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			myChampPanel.add(getMyChampNameLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.insets = new Insets(0, 1, 0, 0);
			constraints.gridheight = 1;
			myChampPanel.add(getMyChampIconLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			constraints.weighty = 1;
			myChampPanel.add(getMyChampItemsPanel(), constraints);
		}
		logger.trace("getMyChampPanel() - Returning");
		logger.debug("getMyChampPanel() - Returning: {}", myChampPanel);
		return myChampPanel;
	}

	public JLabel getMyChampIconLabel() {
		logger.trace("getMyChampIconLabel() - Entering");
		if (myChampIconLabel == null) {
			myChampIconLabel = new JLabel();
		}
		logger.trace("getMyChampIconLabel() - Returning");
		logger.debug("getMyChampIconLabel() - Returning: {}", myChampIconLabel);
		return myChampIconLabel;
	}

	public JLabel getMyChampNameLabel() {
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

	public JTabbedPane getMyChampItemsPanel() {
		logger.trace("getMyChampItemsPanel() - Entering");
		if (myChampItemsPanel == null) {
			myChampItemsPanel = new JTabbedPane();
			myChampItemsPanel.setTabPlacement(JTabbedPane.LEFT);
			myChampItemsPanel.addTab("1", getMyChampStartingItemsPanel());
			myChampItemsPanel.addTab("2", getMyChampEndingItemsPanel());
			myChampItemsPanel.addTab("3", getMyChampSpellsPanel());
		}
		logger.trace("getMyChampItemsPanel() - Returning");
		logger.debug("getMyChampItemsPanel() - Returning: {}",
				myChampItemsPanel);
		return myChampItemsPanel;
	}

	public JPanel getMyChampStartingItemsPanel() {
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
			constraints.insets = new Insets(2, 5, 1, 1);
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
			constraints.insets = new Insets(1, 5, 5, 1);
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

	public JLabel getMyStartingItemsLabel() {
		logger.trace("getMyStartingItemsLabel() - Entering");
		if (myStartingItemsLabel == null) {
			myStartingItemsLabel = new JLabel("Starting items");
		}
		logger.trace("getMyStartingItemsLabel() - Returning");
		logger.debug("getMyStartingItemsLabel() - Returning: {}",
				myStartingItemsLabel);
		return myStartingItemsLabel;
	}

	public JLabel getMyChampItem1Label() {
		logger.trace("getMyChampItem1Label() - Entering");
		if (myChampItem1Label == null) {
			myChampItem1Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampItem1Label() - Returning");
		logger.debug("getMyChampItem1Label() - Returning: {}",
				myChampItem1Label);
		return myChampItem1Label;
	}

	public JLabel getMyChampItem2Label() {
		logger.trace("getMyChampItem2Label() - Entering");
		if (myChampItem2Label == null) {
			myChampItem2Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampItem2Label() - Returning");
		logger.debug("getMyChampItem2Label() - Returning: {}",
				myChampItem2Label);
		return myChampItem2Label;
	}

	public JLabel getMyChampItem3Label() {
		logger.trace("getMyChampItem3Label() - Entering");
		if (myChampItem3Label == null) {
			myChampItem3Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampItem3Label() - Returning");
		logger.debug("getMyChampItem3Label() - Returning: {}",
				myChampItem3Label);
		return myChampItem3Label;
	}

	public JLabel getMyChampItem4Label() {
		logger.trace("getMyChampItem4Label() - Entering");
		if (myChampItem4Label == null) {
			myChampItem4Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampItem4Label() - Returning");
		logger.debug("getMyChampItem4Label() - Returning: {}",
				myChampItem4Label);
		return myChampItem4Label;
	}

	public JLabel getMyChampItem5Label() {
		logger.trace("getMyChampItem5Label() - Entering");
		if (myChampItem5Label == null) {
			myChampItem5Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampItem5Label() - Returning");
		logger.debug("getMyChampItem5Label() - Returning: {}",
				myChampItem5Label);
		return myChampItem5Label;
	}

	public JLabel getMyChampItem6Label() {
		logger.trace("getMyChampItem6Label() - Entering");
		if (myChampItem6Label == null) {
			myChampItem6Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampItem6Label() - Returning");
		logger.debug("getMyChampItem6Label() - Returning: {}",
				myChampItem6Label);
		return myChampItem6Label;
	}

	public JPanel getMyChampEndingItemsPanel() {
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
			constraints.insets = new Insets(2, 5, 1, 1);
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
			constraints.insets = new Insets(1, 5, 5, 1);
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

	public JLabel getMyEndingItemsLabel() {
		logger.trace("getMyEndingItemsLabel() - Entering");
		if (myEndingItemsLabel == null) {
			myEndingItemsLabel = new JLabel("End items");
		}
		logger.trace("getMyEndingItemsLabel() - Returning");
		logger.debug("getMyEndingItemsLabel() - Returning: {}",
				myEndingItemsLabel);
		return myEndingItemsLabel;
	}

	public JLabel getMyChampEndingItem1Label() {
		logger.trace("getMyChampEndingItem1Label() - Entering");
		if (myChampEndingItem1Label == null) {
			myChampEndingItem1Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampEndingItem1Label() - Returning");
		logger.debug("getMyChampEndingItem1Label() - Returning: {}",
				myChampEndingItem1Label);
		return myChampEndingItem1Label;
	}

	public JLabel getMyChampEndingItem2Label() {
		logger.trace("getMyChampEndingItem2Label() - Entering");
		if (myChampEndingItem2Label == null) {
			myChampEndingItem2Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampEndingItem2Label() - Returning");
		logger.debug("getMyChampEndingItem2Label() - Returning: {}",
				myChampEndingItem2Label);
		return myChampEndingItem2Label;
	}

	public JLabel getMyChampEndingItem3Label() {
		logger.trace("getMyChampEndingItem3Label() - Entering");
		if (myChampEndingItem3Label == null) {
			myChampEndingItem3Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampEndingItem3Label() - Returning");
		logger.debug("getMyChampEndingItem3Label() - Returning: {}",
				myChampEndingItem3Label);
		return myChampEndingItem3Label;
	}

	public JLabel getMyChampEndingItem4Label() {
		logger.trace("getMyChampEndingItem4Label() - Entering");
		if (myChampEndingItem4Label == null) {
			myChampEndingItem4Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampEndingItem4Label() - Returning");
		logger.debug("getMyChampEndingItem4Label() - Returning: {}",
				myChampEndingItem4Label);
		return myChampEndingItem4Label;
	}

	public JLabel getMyChampEndingItem5Label() {
		logger.trace("getMyChampEndingItem5Label() - Entering");
		if (myChampEndingItem5Label == null) {
			myChampEndingItem5Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampEndingItem5Label() - Returning");
		logger.debug("getMyChampEndingItem5Label() - Returning: {}",
				myChampEndingItem5Label);
		return myChampEndingItem5Label;
	}

	public JLabel getMyChampEndingItem6Label() {
		logger.trace("getMyChampEndingItem6Label() - Entering");
		if (myChampEndingItem6Label == null) {
			myChampEndingItem6Label = new JLabel(defaultImg);
		}
		logger.trace("getMyChampEndingItem6Label() - Returning");
		logger.debug("getMyChampEndingItem6Label() - Returning: {}",
				myChampEndingItem6Label);
		return myChampEndingItem6Label;
	}

	public JPanel getMyChampSpellsPanel() {
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

	public JLabel getMyChampSpellsLabel() {
		logger.trace("getMyChampSpellsLabel() - Entering");
		if (myChampSpellsLabel == null) {
			myChampSpellsLabel = new JLabel("Summoner spells");
		}
		logger.trace("getMyChampSpellsLabel() - Returning");
		logger.debug("getMyChampSpellsLabel() - Returning: {}",
				myChampSpellsLabel);
		return myChampSpellsLabel;
	}

	public JLabel getMyChampSpell1Label() {
		logger.trace("getMyChampSpell1Label() - Entering");
		if (myChampSpell1Label == null) {
			myChampSpell1Label = new JLabel(defaultImgBig);
		}
		logger.trace("getMyChampSpell1Label() - Returning");
		logger.debug("getMyChampSpell1Label() - Returning: {}",
				myChampSpell1Label);
		return myChampSpell1Label;
	}

	public JLabel getMyChampSpell2Label() {
		logger.trace("getMyChampSpell2Label() - Entering");
		if (myChampSpell2Label == null) {
			myChampSpell2Label = new JLabel(defaultImgBig);
		}
		logger.trace("getMyChampSpell2Label() - Returning");
		logger.debug("getMyChampSpell2Label() - Returning: {}",
				myChampSpell2Label);
		return myChampSpell2Label;
	}

	public JLabel getVersusLabel() {
		logger.trace("getVersusLabel() - Entering");
		if (versusLabel == null) {
			versusLabel = new JLabel(
					ImageIconFactory
							.createImageIconFromResourePath("img/versus.png"));

		}
		logger.trace("getVersusLabel() - Returning");
		logger.debug("getVersusLabel() - Returning: {}", versusLabel);
		return versusLabel;
	}

	public JPanel getEnemyChampPanel() {
		logger.trace("getEnemyChampPanel() - Entering");
		if (enemyChampPanel == null) {
			enemyChampPanel = new JPanel(new GridBagLayout());
			enemyChampPanel.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.LOWERED));

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 2;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			enemyChampPanel.add(getEnemyChampNameLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(0, 0, 0, 1);
			enemyChampPanel.add(getEnemyChampIconLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			constraints.weighty = 1;
			enemyChampPanel.add(getEnemyChampItemsPanel(), constraints);
		}
		logger.trace("getEnemyChampPanel() - Returning");
		logger.debug("getEnemyChampPanel() - Returning: {}", enemyChampPanel);
		return enemyChampPanel;
	}

	public JLabel getEnemyChampIconLabel() {
		logger.trace("getEnemyChampIconLabel() - Entering");
		if (enemyChampIconLabel == null) {
			enemyChampIconLabel = new JLabel();
		}
		logger.trace("getEnemyChampIconLabel() - Returning");
		logger.debug("getEnemyChampIconLabel() - Returning: {}",
				enemyChampIconLabel);
		return enemyChampIconLabel;
	}

	public JLabel getEnemyChampNameLabel() {
		logger.trace("getEnemyChampNameLabel() - Entering");
		if (enemyChampNameLabel == null) {
			enemyChampNameLabel = new JLabel();
			enemyChampNameLabel.setFont(new Font("", Font.PLAIN, 18));
			enemyChampNameLabel.setHorizontalAlignment(JLabel.CENTER);
		}
		logger.trace("getEnemyChampNameLabel() - Returning");
		logger.debug("getEnemyChampNameLabel() - Returning: {}",
				enemyChampNameLabel);
		return enemyChampNameLabel;
	}

	public JTabbedPane getEnemyChampItemsPanel() {
		logger.trace("getEnemyChampItemsPanel() - Entering");
		if (enemyChampItemsPanel == null) {
			enemyChampItemsPanel = new JTabbedPane();
			enemyChampItemsPanel.setTabPlacement(JTabbedPane.RIGHT);
			enemyChampItemsPanel.addTab("1", getEnemyChampStartingItemsPanel());
			enemyChampItemsPanel.addTab("2", getEnemyChampEndingItemsPanel());
			enemyChampItemsPanel.addTab("2", getEnemyChampSpellsPanel());
		}
		logger.trace("getEnemyChampItemsPanel() - Returning");
		logger.debug("getEnemyChampItemsPanel() - Returning: {}",
				enemyChampItemsPanel);
		return enemyChampItemsPanel;
	}

	public JPanel getEnemyChampStartingItemsPanel() {
		logger.trace("getEnemyChampStartingItemsPanel() - Entering");
		if (enemyChampStartingItemsPanel == null) {
			enemyChampStartingItemsPanel = new JPanel(new GridBagLayout());

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 3;
			enemyChampStartingItemsPanel.add(getEnemyStartingItemsLabel(),
					constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			enemyChampStartingItemsPanel.add(getEnemyChampItem2Label(),
					constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 5, 1, 1);
			enemyChampStartingItemsPanel.add(getEnemyChampItem1Label(),
					constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			enemyChampStartingItemsPanel.add(getEnemyChampItem3Label(),
					constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 5, 5, 1);
			enemyChampStartingItemsPanel.add(getEnemyChampItem4Label(),
					constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			enemyChampStartingItemsPanel.add(getEnemyChampItem5Label(),
					constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			enemyChampStartingItemsPanel.add(getEnemyChampItem6Label(),
					constraints);
		}
		logger.trace("getEnemyChampStartingItemsPanel() - Returning");
		logger.debug("getEnemyChampStartingItemsPanel() - Returning: {}",
				enemyChampStartingItemsPanel);
		return enemyChampStartingItemsPanel;
	}

	public JLabel getEnemyStartingItemsLabel() {
		logger.trace("getEnemyStartingItemsLabel() - Entering");
		if (enemyStartingItemsLabel == null) {
			enemyStartingItemsLabel = new JLabel("Starting items");
		}
		logger.trace("getEnemyStartingItemsLabel() - Returning");
		logger.debug("getEnemyStartingItemsLabel() - Returning: {}",
				enemyStartingItemsLabel);
		return enemyStartingItemsLabel;
	}

	public JLabel getEnemyChampItem1Label() {
		logger.trace("getEnemyChampItem1Label() - Entering");
		if (enemyChampItem1Label == null) {
			enemyChampItem1Label = new JLabel(defaultImg);
		}
		logger.trace("getEnemyChampItem1Label() - Returning");
		logger.debug("getEnemyChampItem1Label() - Returning: {}",
				enemyChampItem1Label);
		return enemyChampItem1Label;
	}

	public JLabel getEnemyChampItem2Label() {
		logger.trace("getEnemyChampItem2Label() - Entering");
		if (enemyChampItem2Label == null) {
			enemyChampItem2Label = new JLabel(defaultImg);
		}
		logger.trace("getEnemyChampItem2Label() - Returning");
		logger.debug("getEnemyChampItem2Label() - Returning: {}",
				enemyChampItem2Label);
		return enemyChampItem2Label;
	}

	public JLabel getEnemyChampItem3Label() {
		logger.trace("getEnemyChampItem3Label() - Entering");
		if (enemyChampItem3Label == null) {
			enemyChampItem3Label = new JLabel(defaultImg);
		}
		logger.trace("getEnemyChampItem3Label() - Returning");
		logger.debug("getEnemyChampItem3Label() - Returning: {}",
				enemyChampItem3Label);
		return enemyChampItem3Label;
	}

	public JLabel getEnemyChampItem4Label() {
		logger.trace("getEnemyChampItem4Label() - Entering");
		if (enemyChampItem4Label == null) {
			enemyChampItem4Label = new JLabel(defaultImg);
		}
		logger.trace("getEnemyChampItem4Label() - Returning");
		logger.debug("getEnemyChampItem4Label() - Returning: {}",
				enemyChampItem4Label);
		return enemyChampItem4Label;
	}

	public JLabel getEnemyChampItem5Label() {
		logger.trace("getEnemyChampItem5Label() - Entering");
		if (enemyChampItem5Label == null) {
			enemyChampItem5Label = new JLabel(defaultImg);
		}
		logger.trace("getEnemyChampItem5Label() - Returning");
		logger.debug("getEnemyChampItem5Label() - Returning: {}",
				enemyChampItem5Label);
		return enemyChampItem5Label;
	}

	public JLabel getEnemyChampItem6Label() {
		logger.trace("getEnemyChampItem6Label() - Entering");
		if (enemyChampItem6Label == null) {
			enemyChampItem6Label = new JLabel(defaultImg);
		}
		logger.trace("getEnemyChampItem6Label() - Returning");
		logger.debug("getEnemyChampItem6Label() - Returning: {}",
				enemyChampItem6Label);
		return enemyChampItem6Label;
	}

	public JPanel getEnemyChampEndingItemsPanel() {
		logger.trace("getEnemyChampEndingItemsPanel() - Entering");
		if (enemyChampEndingItemsPanel == null) {
			enemyChampEndingItemsPanel = new JPanel(new GridBagLayout());

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 3;
			enemyChampEndingItemsPanel.add(getEnemyEndingItemsLabel(),
					constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 5, 1, 1);
			enemyChampEndingItemsPanel.add(getEnemyChampEndingItem1Label(),
					constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			enemyChampEndingItemsPanel.add(getEnemyChampEndingItem2Label(),
					constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 1, 1, 1);
			enemyChampEndingItemsPanel.add(getEnemyChampEndingItem3Label(),
					constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 5, 5, 1);
			enemyChampEndingItemsPanel.add(getEnemyChampEndingItem4Label(),
					constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			enemyChampEndingItemsPanel.add(getEnemyChampEndingItem5Label(),
					constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 1, 5, 1);
			enemyChampEndingItemsPanel.add(getEnemyChampEndingItem6Label(),
					constraints);
		}
		logger.trace("getEnemyChampEndingItemsPanel() - Returning");
		logger.debug("getEnemyChampEndingItemsPanel() - Returning: {}",
				enemyChampEndingItemsPanel);
		return enemyChampEndingItemsPanel;
	}

	public JLabel getEnemyEndingItemsLabel() {
		logger.trace("getEnemyEndingItemsLabel() - Entering");
		if (enemyEndingLabel == null) {
			enemyEndingLabel = new JLabel("End items");
		}
		logger.trace("getEnemyEndingItemsLabel() - Returning");
		logger.debug("getEnemyEndingItemsLabel() - Returning: {}",
				enemyEndingLabel);
		return enemyEndingLabel;
	}

	public JLabel getEnemyChampEndingItem1Label() {
		logger.trace("getEnemyChampEndingItem1Label() - Entering");
		if (enemyChampEndingItem1Label == null) {
			enemyChampEndingItem1Label = new JLabel(defaultImg);
		}
		logger.trace("getEnemyChampEndingItem1Label() - Returning");
		logger.debug("getEnemyChampEndingItem1Label() - Returning: {}",
				enemyChampEndingItem1Label);
		return enemyChampEndingItem1Label;
	}

	public JLabel getEnemyChampEndingItem2Label() {
		logger.trace("getEnemyChampEndingItem2Label() - Entering");
		if (enemyChampEndingItem2Label == null) {
			enemyChampEndingItem2Label = new JLabel(defaultImg);
		}
		logger.trace("getEnemyChampEndingItem2Label() - Returning");
		logger.debug("getEnemyChampEndingItem2Label() - Returning: {}",
				enemyChampEndingItem2Label);
		return enemyChampEndingItem2Label;
	}

	public JLabel getEnemyChampEndingItem3Label() {
		logger.trace("getEnemyChampEndingItem3Label() - Entering");
		if (enemyChampEndingItem3Label == null) {
			enemyChampEndingItem3Label = new JLabel(defaultImg);
		}
		logger.trace("getEnemyChampEndingItem3Label() - Returning");
		logger.debug("getEnemyChampEndingItem3Label() - Returning: {}",
				enemyChampEndingItem3Label);
		return enemyChampEndingItem3Label;
	}

	public JLabel getEnemyChampEndingItem4Label() {
		logger.trace("getEnemyChampEndingItem4Label() - Entering");
		if (enemyChampEndingItem4Label == null) {
			enemyChampEndingItem4Label = new JLabel(defaultImg);
		}
		logger.trace("getEnemyChampEndingItem4Label() - Returning");
		logger.debug("getEnemyChampEndingItem4Label() - Returning: {}",
				enemyChampEndingItem4Label);
		return enemyChampEndingItem4Label;
	}

	public JLabel getEnemyChampEndingItem5Label() {
		logger.trace("getEnemyChampEndingItem5Label() - Entering");
		if (enemyChampEndingItem5Label == null) {
			enemyChampEndingItem5Label = new JLabel(defaultImg);
		}
		logger.trace("getEnemyChampEndingItem5Label() - Returning");
		logger.debug("getEnemyChampEndingItem5Label() - Returning: {}",
				enemyChampEndingItem5Label);
		return enemyChampEndingItem5Label;
	}

	public JLabel getEnemyChampEndingItem6Label() {
		logger.trace("getEnemyChampEndingItem6Label() - Entering");
		if (enemyChampEndingItem6Label == null) {
			enemyChampEndingItem6Label = new JLabel(defaultImg);
		}
		logger.trace("getEnemyChampEndingItem6Label() - Returning");
		logger.debug("getEnemyChampEndingItem6Label() - Returning: {}",
				enemyChampEndingItem6Label);
		return enemyChampEndingItem6Label;
	}

	public JPanel getEnemyChampSpellsPanel() {
		logger.trace("getEnemyChampSpellsPanel() - Entering");
		if (enemyChampSpellsPanel == null) {
			enemyChampSpellsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 2;
			enemyChampSpellsPanel.add(getEnemyChampSpellsLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(2, 2, 2, 3);
			enemyChampSpellsPanel.add(getEnemyChampSpell1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.insets = new Insets(3, 2, 2, 2);
			enemyChampSpellsPanel.add(getEnemyChampSpell2Label(), constraints);
		}
		logger.trace("getEnemyChampSpellsPanel() - Returning");
		logger.debug("getEnemyChampSpellsPanel() - Returning: {}",
				enemyChampSpellsPanel);
		return enemyChampSpellsPanel;
	}

	public JLabel getEnemyChampSpellsLabel() {
		logger.trace("getEnemyChampSpellsLabel() - Entering");
		if (enemyChampSpellsLabel == null) {
			enemyChampSpellsLabel = new JLabel("Summoner spells");
		}
		logger.trace("getEnemyChampSpellsLabel() - Returning");
		logger.debug("getEnemyChampSpellsLabel() - Returning: {}",
				enemyChampSpellsLabel);
		return enemyChampSpellsLabel;
	}

	public JLabel getEnemyChampSpell1Label() {
		logger.trace("getEnemyChampSpell1Label() - Entering");
		if (enemyChampSpell1Label == null) {
			enemyChampSpell1Label = new JLabel(defaultImgBig);
		}
		logger.trace("getEnemyChampSpell1Label() - Returning");
		logger.debug("getEnemyChampSpell1Label() - Returning: {}",
				enemyChampSpell1Label);
		return enemyChampSpell1Label;
	}

	public JLabel getEnemyChampSpell2Label() {
		logger.trace("getEnemyChampSpell2Label() - Entering");
		if (enemyChampSpell2Label == null) {
			enemyChampSpell2Label = new JLabel(defaultImgBig);
		}
		logger.trace("getEnemyChampSpell2Label() - Returning");
		logger.debug("getEnemyChampSpell2Label() - Returning: {}",
				enemyChampSpell2Label);
		return enemyChampSpell2Label;
	}

	public JPanel getNotesPanel() {
		logger.trace("getNotesPanel() - Entering");
		if (notesPanel == null) {
			notesPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			notesPanel.add(getNotesLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			constraints.weighty = 1;
			notesPanel.add(getNotesScrollPane(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			notesPanel.add(getSaveNotesButton(), constraints);
		}
		logger.trace("getNotesPanel() - Returning");
		logger.debug("getNotesPanel() - Returning: {}", notesPanel);
		return notesPanel;
	}

	public JLabel getNotesLabel() {
		logger.trace("getNotesLabel() - Entering");
		if (notesLabel == null) {
			notesLabel = new JLabel("Notes for this matchup:");
		}
		logger.trace("getNotesLabel() - Returning");
		logger.debug("getNotesLabel() - Returning: {}", notesLabel);
		return notesLabel;
	}

	public JScrollPane getNotesScrollPane() {
		logger.trace("getNotesScrollPane() - Entering");
		if (notesScrollPane == null) {
			notesScrollPane = new JScrollPane(getNotesTextarea());
			notesScrollPane.setMaximumSize(new Dimension((int) getMaximumSize()
					.getWidth(), 200));
		}
		logger.trace("getNotesScrollPane() - Returning");
		logger.debug("getNotesScrollPane() - Returning: {}", notesScrollPane);
		return notesScrollPane;
	}

	public JTextArea getNotesTextarea() {
		logger.trace("getNotesTextarea() - Entering");
		if (notesTextarea == null) {
			notesTextarea = new JTextArea();
			notesTextarea.setLineWrap(true);
		}
		logger.trace("getNotesTextarea() - Returning");
		logger.debug("getNotesTextarea() - Returning: {}", notesTextarea);
		return notesTextarea;
	}

	public JButton getSaveNotesButton() {
		logger.trace("getSaveNotesButton() - Entering");
		if (saveNotesButton == null) {
			saveNotesButton = new JButton("Save changes to notes");
			saveNotesButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					matchup.setNotes(getNotesTextarea().getText());
					MatchupUtil.saveMatchup(matchup);
				}
			});
		}
		logger.trace("getSaveNotesButton() - Returning");
		logger.debug("getSaveNotesButton() - Returning: {}", saveNotesButton);
		return saveNotesButton;
	}

	public JPanel getGamePanel() {
		logger.trace("getGamePanel() - Entering");
		if (gamePanel == null) {
			gamePanel = new JPanel(new GridBagLayout());

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 2;
			gamePanel.add(getGameStatsLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			gamePanel.add(getKillsLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			gamePanel.add(getKillsValueLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			gamePanel.add(getDeathsLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			gamePanel.add(getDeathsValueLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			gamePanel.add(getAssistsLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 3;
			gamePanel.add(getAssistsValueLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 4;
			gamePanel.add(getCsLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 4;
			gamePanel.add(getCsValueLabel(), constraints);
		}
		logger.trace("getGamePanel() - Returning");
		logger.debug("getGamePanel() - Returning: {}", gamePanel);
		return gamePanel;
	}

	public JLabel getGameStatsLabel() {
		logger.trace("getGameStatsLabel() - Entering");
		if (gameStatsLabel == null) {
			gameStatsLabel = new JLabel("Game statistics:");
		}
		logger.trace("getGameStatsLabel() - Returning");
		logger.debug("getGameStatsLabel() - Returning: {}", gameStatsLabel);
		return gameStatsLabel;
	}

	public JLabel getKillsLabel() {
		logger.trace("getKillsLabel() - Entering");
		if (killsLabel == null) {
			killsLabel = new JLabel("Kills:");
		}
		logger.trace("getKillsLabel() - Returning");
		logger.debug("getKillsLabel() - Returning: {}", killsLabel);
		return killsLabel;
	}

	public JLabel getKillsValueLabel() {
		logger.trace("getKillsValueLabel() - Entering");
		if (killsValueLabel == null) {
			killsValueLabel = new JLabel();
		}
		logger.trace("getKillsValueLabel() - Returning");
		logger.debug("getKillsValueLabel() - Returning: {}", killsValueLabel);
		return killsValueLabel;
	}

	public JLabel getDeathsLabel() {
		logger.trace("getDeathsLabel() - Entering");
		if (deathsLabel == null) {
			deathsLabel = new JLabel("Deaths:");
		}
		logger.trace("getDeathsLabel() - Returning");
		logger.debug("getDeathsLabel() - Returning: {}", deathsLabel);
		return deathsLabel;
	}

	public JLabel getDeathsValueLabel() {
		logger.trace("getDeathsValueLabel() - Entering");
		if (deathsValueLabel == null) {
			deathsValueLabel = new JLabel();
		}
		logger.trace("getDeathsValueLabel() - Returning");
		logger.debug("getDeathsValueLabel() - Returning: {}", deathsValueLabel);
		return deathsValueLabel;
	}

	public JLabel getAssistsLabel() {
		logger.trace("getAssistsLabel() - Entering");
		if (assistsLabel == null) {
			assistsLabel = new JLabel("Assists:");
		}
		logger.trace("getAssistsLabel() - Returning");
		logger.debug("getAssistsLabel() - Returning: {}", assistsLabel);
		return assistsLabel;
	}

	public JLabel getAssistsValueLabel() {
		logger.trace("getAssistsValueLabel() - Entering");
		if (assistsValueLabel == null) {
			assistsValueLabel = new JLabel();
		}
		logger.trace("getAssistsValueLabel() - Returning");
		logger.debug("getAssistsValueLabel() - Returning: {}",
				assistsValueLabel);
		return assistsValueLabel;
	}

	public JLabel getCsLabel() {
		logger.trace("getCsLabel() - Entering");
		if (csLabel == null) {
			csLabel = new JLabel("CS:");
			csLabel.setToolTipText("Amount of minions slain");
		}
		logger.trace("getCsLabel() - Returning");
		logger.debug("getCsLabel() - Returning: {}", csLabel);
		return csLabel;
	}

	public JLabel getCsValueLabel() {
		logger.trace("getCsValueLabel() - Entering");
		if (csLabelValue == null) {
			csLabelValue = new JLabel();
		}
		logger.trace("getCsValueLabel() - Returning");
		logger.debug("getCsValueLabel() - Returning: {}", csLabelValue);
		return csLabelValue;
	}

	public JPanel getGameChampsPanel() {
		logger.trace("getGameChampsPanel() - Entering");
		if (gameChampsPanel == null) {
			gameChampsPanel = new JPanel(new GridBagLayout());

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.insets = new Insets(0, 0, 1, 0);
			gameChampsPanel.add(getGameChamp1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(1, 0, 1, 0);
			gameChampsPanel.add(getGameChamp2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 0, 1, 0);
			gameChampsPanel.add(getGameChamp3Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			constraints.insets = new Insets(1, 0, 1, 0);
			gameChampsPanel.add(getGameChamp4Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 4;
			constraints.insets = new Insets(1, 0, 0, 0);
			gameChampsPanel.add(getGameChamp5Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.gridheight = 5;
			constraints.insets = new Insets(0, 5, 0, 5);
			gameChampsPanel.add(getGameChampVersusLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 0;
			constraints.insets = new Insets(1, 0, 1, 0);
			gameChampsPanel.add(getGameChamp6Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 1;
			constraints.insets = new Insets(1, 0, 1, 0);
			gameChampsPanel.add(getGameChamp7Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.insets = new Insets(1, 0, 1, 0);
			gameChampsPanel.add(getGameChamp8Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 3;
			constraints.insets = new Insets(1, 0, 1, 0);
			gameChampsPanel.add(getGameChamp9Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 4;
			constraints.insets = new Insets(1, 0, 0, 0);
			gameChampsPanel.add(getGameChamp10Label(), constraints);
		}
		logger.trace("getGameChampsPanel() - Returning");
		logger.debug("getGameChampsPanel() - Returning: {}", gameChampsPanel);
		return gameChampsPanel;
	}

	public JLabel getGameChamp1Label() {
		logger.trace("getGameChamp1Label() - Entering");
		if (gameChamp1Label == null) {
			gameChamp1Label = new JLabel();
		}
		logger.trace("getGameChamp1Label() - Returning");
		logger.debug("getGameChamp1Label() - Returning: {}", gameChamp1Label);
		return gameChamp1Label;
	}

	public JLabel getGameChamp2Label() {
		logger.trace("getGameChamp2Label() - Entering");
		if (gameChamp2Label == null) {
			gameChamp2Label = new JLabel();
		}
		logger.trace("getGameChamp2Label() - Returning");
		logger.debug("getGameChamp2Label() - Returning: {}", gameChamp2Label);
		return gameChamp2Label;
	}

	public JLabel getGameChamp3Label() {
		logger.trace("getGameChamp3Label() - Entering");
		if (gameChamp3Label == null) {
			gameChamp3Label = new JLabel();
		}
		logger.trace("getGameChamp3Label() - Returning");
		logger.debug("getGameChamp3Label() - Returning: {}", gameChamp3Label);
		return gameChamp3Label;
	}

	public JLabel getGameChamp4Label() {
		logger.trace("getGameChamp4Label() - Entering");
		if (gameChamp4Label == null) {
			gameChamp4Label = new JLabel();
		}
		logger.trace("getGameChamp4Label() - Returning");
		logger.debug("getGameChamp4Label() - Returning: {}", gameChamp4Label);
		return gameChamp4Label;
	}

	public JLabel getGameChamp5Label() {
		logger.trace("getGameChamp5Label() - Entering");
		if (gameChamp5Label == null) {
			gameChamp5Label = new JLabel();
		}
		logger.trace("getGameChamp5Label() - Returning");
		logger.debug("getGameChamp5Label() - Returning: {}", gameChamp5Label);
		return gameChamp5Label;
	}

	public JLabel getGameChampVersusLabel() {
		logger.trace("getGameChampVersusLabel() - Entering");
		if (gameChampVersusLabel == null) {
			gameChampVersusLabel = new JLabel(ImageIconFactory.resizeImageIcon(
					ImageIconFactory
							.createImageIconFromResourePath("img/versus.png"),
					30, 30));
		}
		logger.trace("getGameChampVersusLabel() - Returning");
		logger.debug("getGameChampVersusLabel() - Returning: {}",
				gameChampVersusLabel);
		return gameChampVersusLabel;
	}

	public JLabel getGameChamp6Label() {
		logger.trace("getGameChamp6Label() - Entering");
		if (gameChamp6Label == null) {
			gameChamp6Label = new JLabel();
		}
		logger.trace("getGameChamp6Label() - Returning");
		logger.debug("getGameChamp6Label() - Returning: {}", gameChamp6Label);
		return gameChamp6Label;
	}

	public JLabel getGameChamp7Label() {
		logger.trace("getGameChamp7Label() - Entering");
		if (gameChamp7Label == null) {
			gameChamp7Label = new JLabel();
		}
		logger.trace("getGameChamp7Label() - Returning");
		logger.debug("getGameChamp7Label() - Returning: {}", gameChamp7Label);
		return gameChamp7Label;
	}

	public JLabel getGameChamp8Label() {
		logger.trace("getGameChamp8Label() - Entering");
		if (gameChamp8Label == null) {
			gameChamp8Label = new JLabel();
		}
		logger.trace("getGameChamp8Label() - Returning");
		logger.debug("getGameChamp8Label() - Returning: {}", gameChamp8Label);
		return gameChamp8Label;
	}

	public JLabel getGameChamp9Label() {
		logger.trace("getGameChamp9Label() - Entering");
		if (gameChamp9Label == null) {
			gameChamp9Label = new JLabel();
		}
		logger.trace("getGameChamp9Label() - Returning");
		logger.debug("getGameChamp9Label() - Returning: {}", gameChamp9Label);
		return gameChamp9Label;
	}

	public JLabel getGameChamp10Label() {
		logger.trace("getGameChamp10Label() - Entering");
		if (gameChamp10Label == null) {
			gameChamp10Label = new JLabel();
		}
		logger.trace("getGameChamp10Label() - Returning");
		logger.debug("getGameChamp10Label() - Returning: {}", gameChamp10Label);
		return gameChamp10Label;
	}

	public JButton getCloseButton() {
		logger.trace("getCloseButton() - Entering");
		if (closeButton == null) {
			closeButton = new JButton("Close");
			closeButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
		}
		logger.trace("getCloseButton() - Returning");
		logger.debug("getCloseButton() - Returning: {}", closeButton);
		return closeButton;
	}

	public void showDetails(Matchup m) {
		this.matchup = m;
		this.getHeadlineLabel().setText(m.getLane().toString());
		ImageIcon icon = null;
		switch (m.getDifficulty()) {
		case DRAW:
			icon = ImageIconFactory
					.createImageIconFromResourePath("img/draw.png");
			break;
		case EASY:
			icon = ImageIconFactory
					.createImageIconFromResourePath("img/hard.png");
			break;
		case HARD:
			icon = ImageIconFactory
					.createImageIconFromResourePath("img/easy.png");
			break;
		case SUPER_EASY:
			icon = ImageIconFactory
					.createImageIconFromResourePath("img/super_easy.png");
			break;
		case SUPER_HARD:
			icon = ImageIconFactory
					.createImageIconFromResourePath("img/super_hard.png");
			break;
		}
		this.getDifficultyValueLabel().setIcon(icon);
		this.getDifficultyValueLabel().setToolTipText(
				m.getDifficulty().toString());
		switch (m.getLane()) {
		case AD_CARRY:
			this.getHeadlineLabel().setBackground(Color.RED);
			break;
		case JUNGLE:
			this.getHeadlineLabel().setBackground(Color.GREEN);
			break;
		case MID:
			this.getHeadlineLabel().setBackground(Color.BLUE);
			break;
		case SUPPORT:
			this.getHeadlineLabel().setBackground(Color.YELLOW);
			break;
		case TOP:
			this.getHeadlineLabel().setBackground(Color.ORANGE);
			break;
		}

		this.getMyChampNameLabel().setText(m.getMyChamp().getName());
		Color c = Color.GREEN;
		if (matchup.getResult() == MatchupResult.LOSS) {
			c = Color.RED;
		} else if (matchup.getResult() == MatchupResult.DRAW) {
			c = Color.WHITE;
		}
		this.getMyChampNameLabel().setOpaque(true);
		this.getMyChampNameLabel().setBackground(c);
		this.getMyChampIconLabel().setIcon(m.getMyChamp().getIcon());
		int x = 0;
		for (MatchupItem mi : m.getMyStartItems()) {
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
		for (MatchupItem mi : m.getMyEndItems()) {
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
		getMyChampSpell1Label().setIcon(
				ImageIconFactory.resizeImageIcon(m.getMySpell1().getIcon(), 50,
						50));
		getMyChampSpell2Label().setIcon(
				ImageIconFactory.resizeImageIcon(m.getMySpell2().getIcon(), 50,
						50));
		this.getEnemyChampNameLabel().setText(m.getEnemyChamp().getName());
		c = Color.GREEN;
		if (matchup.getResult() == MatchupResult.WIN) {
			c = Color.RED;
		} else if (matchup.getResult() == MatchupResult.DRAW) {
			c = Color.WHITE;
		}
		this.getEnemyChampNameLabel().setOpaque(true);
		this.getEnemyChampNameLabel().setBackground(c);
		this.getEnemyChampIconLabel().setIcon(m.getEnemyChamp().getIcon());
		x = 0;
		for (MatchupItem mi : m.getEnemyStartItems()) {
			x++;
			JLabel l = null;
			switch (x) {
			case 1:
				l = this.getEnemyChampItem1Label();
				break;
			case 2:
				l = this.getEnemyChampItem2Label();
				break;
			case 3:
				l = this.getEnemyChampItem3Label();
				break;
			case 4:
				l = this.getEnemyChampItem4Label();
				break;
			case 5:
				l = this.getEnemyChampItem5Label();
				break;
			case 6:
				l = this.getEnemyChampItem6Label();
				break;
			}
			l.setIcon(ImageIconFactory.resizeImageIcon(mi.getItem().getIcon(),
					30, 30));
			l.setToolTipText(mi.getAmount() + "x " + mi.getItem().getName());
		}
		x = 0;
		for (MatchupItem mi : m.getEnemyEndItems()) {
			x++;
			JLabel l = null;
			switch (x) {
			case 1:
				l = this.getEnemyChampEndingItem1Label();
				break;
			case 2:
				l = this.getEnemyChampEndingItem2Label();
				break;
			case 3:
				l = this.getEnemyChampEndingItem3Label();
				break;
			case 4:
				l = this.getEnemyChampEndingItem4Label();
				break;
			case 5:
				l = this.getEnemyChampEndingItem5Label();
				break;
			case 6:
				l = this.getEnemyChampEndingItem6Label();
				break;
			}
			l.setIcon(ImageIconFactory.resizeImageIcon(mi.getItem().getIcon(),
					30, 30));
			l.setToolTipText(mi.getAmount() + "x " + mi.getItem().getName());
		}
		getEnemyChampSpell1Label().setIcon(
				ImageIconFactory.resizeImageIcon(m.getEnemySpell1().getIcon(),
						50, 50));
		getEnemyChampSpell2Label().setIcon(
				ImageIconFactory.resizeImageIcon(m.getEnemySpell2().getIcon(),
						50, 50));

		this.getNotesTextarea().setText(m.getNotes());
		Game g = m.getGame();
		this.getKillsValueLabel().setText(String.valueOf(g.getOwnKills()));
		this.getDeathsValueLabel().setText(String.valueOf(g.getOwnDeaths()));
		this.getAssistsValueLabel().setText(String.valueOf(g.getOwnAssists()));
		this.getCsValueLabel().setText(String.valueOf(g.getOwnCS()));

		x = 0;
		for (Champion champ : g.getMyTeam()) {
			x++;
			JLabel l = null;
			switch (x) {
			case 1:
				l = this.getGameChamp1Label();
				break;
			case 2:
				l = this.getGameChamp2Label();
				break;
			case 3:
				l = this.getGameChamp3Label();
				break;
			case 4:
				l = this.getGameChamp4Label();
				break;
			case 5:
				l = this.getGameChamp5Label();
				break;
			}
			l.setIcon(ImageIconFactory.resizeImageIcon(champ.getIcon(), 25, 25));
			l.setToolTipText(champ.getName());
		}
		x = 0;
		for (Champion champ : g.getEnemyTeam()) {
			x++;
			JLabel l = null;
			switch (x) {
			case 1:
				l = this.getGameChamp6Label();
				break;
			case 2:
				l = this.getGameChamp7Label();
				break;
			case 3:
				l = this.getGameChamp8Label();
				break;
			case 4:
				l = this.getGameChamp9Label();
				break;
			case 5:
				l = this.getGameChamp10Label();
				break;
			}
			l.setIcon(ImageIconFactory.resizeImageIcon(champ.getIcon(), 25, 25));
			l.setToolTipText(champ.getName());
		}

		this.pack();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((d.width - this.getSize().width) / 2,
				(d.height - this.getSize().height) / 2);
		this.setResizable(false);
		this.setModal(true);
		setVisible(true);
	}

	@Override
	public void refresh() {
		// Nothing to do here...
	}
}