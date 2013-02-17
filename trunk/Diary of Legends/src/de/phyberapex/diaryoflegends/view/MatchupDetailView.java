package de.phyberapex.diaryoflegends.view;

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
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.extra.ImageIconFactory;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.Game;
import de.phyberapex.diaryoflegends.model.Matchup;
import de.phyberapex.diaryoflegends.model.MatchupItem;
import de.phyberapex.diaryoflegends.model.util.MatchupUtil;

public class MatchupDetailView extends JDialog implements View {

	private static final long serialVersionUID = -7485492927311999351L;
	private static MatchupDetailView instance;
	private JPanel matchupContentPanel;
	private JLabel headlineLabel;
	private JPanel myChampPanel;
	private JLabel myChampIconLabel;
	private JLabel myChampNameLabel;
	private JPanel myChampItemsPanel;
	private JLabel myChampItem1Label;
	private JLabel myChampItem2Label;
	private JLabel myChampItem3Label;
	private JLabel myChampItem4Label;
	private JLabel myChampItem5Label;
	private JLabel myChampItem6Label;
	private JLabel myChampResultLabel;
	private JLabel versusLabel;
	private JPanel enemyChampPanel;
	private JLabel enemyChampIconLabel;
	private JLabel enemyChampNameLabel;
	private JPanel enemyChampItemsPanel;
	private JLabel enemyChampItem1Label;
	private JLabel enemyChampItem2Label;
	private JLabel enemyChampItem3Label;
	private JLabel enemyChampItem4Label;
	private JLabel enemyChampItem5Label;
	private JLabel enemyChampItem6Label;
	private JLabel enemyChampResultLabel;
	private JPanel notesPanel;
	private JLabel notesLabel;
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

	private static Logger logger = LogManager.getLogger(MatchupDetailView.class
			.getName());

	private MatchupDetailView() {
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
			matchupContentPanel.add(getHeadlineLabel(), constraints);

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
			myChampPanel.add(getMyChampNameLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.insets = new Insets(0, 1, 5, 5);
			myChampPanel.add(getMyChampIconLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
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
		}
		logger.trace("getMyChampNameLabel() - Returning");
		logger.debug("getMyChampNameLabel() - Returning: {}", myChampNameLabel);
		return myChampNameLabel;
	}

	public JPanel getMyChampItemsPanel() {
		logger.trace("getMyChampItemsPanel() - Entering");
		if (myChampItemsPanel == null) {
			myChampItemsPanel = new JPanel(new GridBagLayout());

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.insets = new Insets(2, 5, 1, 1);
			myChampItemsPanel.add(getMyChampItem1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.insets = new Insets(2, 1, 1, 1);
			myChampItemsPanel.add(getMyChampItem2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 0;
			constraints.insets = new Insets(2, 1, 1, 1);
			myChampItemsPanel.add(getMyChampItem3Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(1, 5, 5, 1);
			myChampItemsPanel.add(getMyChampItem4Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.insets = new Insets(1, 1, 5, 1);
			myChampItemsPanel.add(getMyChampItem5Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 1;
			constraints.insets = new Insets(1, 1, 5, 1);
			myChampItemsPanel.add(getMyChampItem6Label(), constraints);
		}
		logger.trace("getMyChampItemsPanel() - Returning");
		logger.debug("getMyChampItemsPanel() - Returning: {}",
				myChampItemsPanel);
		return myChampItemsPanel;
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

	public JLabel getMyChampResultLabel() {
		logger.trace("getMyChampResultLabel() - Entering");
		if (myChampResultLabel == null) {
			myChampResultLabel = new JLabel();
		}
		logger.trace("getMyChampResultLabel() - Returning");
		logger.debug("getMyChampResultLabel() - Returning: {}",
				myChampResultLabel);
		return myChampResultLabel;
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
			enemyChampPanel.add(getEnemyChampNameLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(0, 5, 5, 1);
			enemyChampPanel.add(getEnemyChampIconLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
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
		}
		logger.trace("getEnemyChampNameLabel() - Returning");
		logger.debug("getEnemyChampNameLabel() - Returning: {}",
				enemyChampNameLabel);
		return enemyChampNameLabel;
	}

	public JPanel getEnemyChampItemsPanel() {
		logger.trace("getEnemyChampItemsPanel() - Entering");
		if (enemyChampItemsPanel == null) {
			enemyChampItemsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.insets = new Insets(2, 1, 1, 1);
			enemyChampItemsPanel.add(getEnemyChampItem1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.insets = new Insets(2, 1, 1, 1);
			enemyChampItemsPanel.add(getEnemyChampItem2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 0;
			constraints.insets = new Insets(2, 1, 1, 5);
			enemyChampItemsPanel.add(getEnemyChampItem3Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(1, 1, 5, 1);
			enemyChampItemsPanel.add(getEnemyChampItem4Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.insets = new Insets(1, 1, 5, 1);
			enemyChampItemsPanel.add(getEnemyChampItem5Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 1;
			constraints.insets = new Insets(1, 1, 5, 5);
			enemyChampItemsPanel.add(getEnemyChampItem6Label(), constraints);
		}
		logger.trace("getEnemyChampItemsPanel() - Returning");
		logger.debug("getEnemyChampItemsPanel() - Returning: {}",
				enemyChampItemsPanel);
		return enemyChampItemsPanel;
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

	public JLabel getEnemyChampResultLabel() {
		logger.trace("getEnemyChampResultLabel() - Entering");
		if (enemyChampResultLabel == null) {
			enemyChampResultLabel = new JLabel();
		}
		logger.trace("getEnemyChampResultLabel() - Returning");
		logger.debug("getEnemyChampResultLabel() - Returning: {}",
				enemyChampResultLabel);
		return enemyChampResultLabel;
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
			notesPanel.add(getNotesTextarea(), constraints);

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

	public JTextArea getNotesTextarea() {
		logger.trace("getNotesTextarea() - Entering");
		if (notesTextarea == null) {
			notesTextarea = new JTextArea();
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
		// TODO
		this.matchup = m;
		this.getHeadlineLabel().setText(m.getLane().toString());
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
			l.setToolTipText(mi.getAmount() + "x " + mi.getItem().getPrice());
		}
		this.getEnemyChampNameLabel().setText(m.getEnemyChamp().getName());
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
			l.setToolTipText(mi.getAmount() + "x " + mi.getItem().getPrice());
		}
		this.getNotesTextarea().setText(m.getNotes());
		Game g = m.getGame();
		this.getKillsValueLabel().setText(String.valueOf(g.getOwnKills()));
		this.getDeathsValueLabel().setText(String.valueOf(g.getOwnDeaths()));
		this.getAssistsValueLabel().setText(String.valueOf(g.getOwnAssists()));

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

	public static synchronized MatchupDetailView getInstance() {
		logger.trace("getInstance() - Entering");
		logger.debug("getInstance() - Instance is {}", instance);
		if (instance == null) {
			logger.debug("Creating a new instance of MatchupDetailView");
			instance = new MatchupDetailView();
		}
		logger.trace("getInstance() - Returning");
		logger.debug("getInstance() - Returning {}", instance);
		return instance;
	}

	@Override
	public void refresh() {
		// Nothing to do here...
	}
}