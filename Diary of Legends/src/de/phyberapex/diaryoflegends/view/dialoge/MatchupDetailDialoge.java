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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.extra.ImageIconFactory;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.Game;
import de.phyberapex.diaryoflegends.model.Matchup;
import de.phyberapex.diaryoflegends.model.MatchupDifficulty;
import de.phyberapex.diaryoflegends.model.MatchupResult;
import de.phyberapex.diaryoflegends.model.Role;
import de.phyberapex.diaryoflegends.model.util.GameUtil;
import de.phyberapex.diaryoflegends.model.util.MatchupUtil;
import de.phyberapex.diaryoflegends.view.dialoge.panel.MatchupDetailChampionPanel;

public class MatchupDetailDialoge extends JDialog implements Runnable {

	private static final long serialVersionUID = -7485492927311999351L;
	private static MatchupDetailDialoge instance;
	private JPanel matchupContentPanel;
	private JPanel headPanel;
	private JLabel headlineLabel;
	private JLabel difficultyValueLabel;
	private MatchupDetailChampionPanel myChampPanel;
	private JLabel versusLabel;
	private MatchupDetailChampionPanel enemyChampPanel;
	private JPanel gameNotesPanel;
	private JLabel gameNotesLabel;
	private JScrollPane gameNotesScrollPane;
	private JTextArea gameNotesTextarea;
	private JButton saveGameNotesButton;
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
	private JPanel myTeamPanel;
	private JLabel gameChamp1Label;
	private JLabel gameChamp2Label;
	private JLabel gameChamp3Label;
	private JLabel gameChamp4Label;
	private JPanel enemyTeamPanel;
	private JLabel gameChamp6Label;
	private JLabel gameChamp7Label;
	private JLabel gameChamp8Label;
	private JLabel gameChamp9Label;
	private JButton closeButton;
	private Matchup matchup;
	private static Logger logger = LogManager
			.getLogger(MatchupDetailDialoge.class.getName());

	private MatchupDetailDialoge() {
		super();
		createGUI();
	}

	public void setMatchup(Matchup matchup) {
		logger.trace("setMatchup() - Entering");
		logger.debug("setMatchup() - Parameter: {}", matchup);
		this.matchup = matchup;
		logger.trace("setMatchup() - Leaving");
	}

	private void createGUI() {
		this.setContentPane(getMatchupContentPanel());
		this.setUndecorated(true);
		this.setResizable(false);
		this.setModal(true);
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
			constraints.gridwidth = 5;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			matchupContentPanel.add(getHeadPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(10, 10, 10, 10);
			matchupContentPanel.add(getMyChampPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 1;
			constraints.insets = new Insets(10, 10, 10, 10);
			matchupContentPanel.add(getVersusLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 4;
			constraints.gridy = 1;
			constraints.insets = new Insets(10, 10, 10, 10);
			matchupContentPanel.add(getEnemyChampPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.gridwidth = 2;
			constraints.fill = GridBagConstraints.BOTH;
			matchupContentPanel.add(getNotesPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 2;
			constraints.gridwidth = 2;
			constraints.fill = GridBagConstraints.BOTH;
			matchupContentPanel.add(getGameNotesPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 2;
			constraints.anchor = GridBagConstraints.NORTH;
			matchupContentPanel.add(getGamePanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			matchupContentPanel.add(getMyTeamPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 1;
			matchupContentPanel.add(getEnemyTeamPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			constraints.fill = GridBagConstraints.BOTH;
			matchupContentPanel.add(getSaveNotesButton(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 3;
			constraints.fill = GridBagConstraints.BOTH;
			matchupContentPanel.add(getCloseButton(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 4;
			constraints.gridy = 3;
			constraints.fill = GridBagConstraints.BOTH;
			matchupContentPanel.add(getSaveGameNotesButton(), constraints);
		}
		logger.trace("getMatchupContentPanel() - Returning");
		logger.debug("getMatchupContentPanel() - Returning: {}",
				matchupContentPanel);
		return matchupContentPanel;
	}

	private JPanel getHeadPanel() {
		logger.trace("getHeadPanel() - Entering");
		if (headPanel == null) {
			headPanel = new JPanel(new GridBagLayout());
			headPanel.setBorder(BorderFactory.createBevelBorder(1));

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			headPanel.add(getHeadlineLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			headPanel.add(getDifficultyValueLabel(), constraints);
		}
		logger.trace("getHeadPanel() - Returning");
		logger.debug("getHeadPanel() - Returning: {}", headPanel);
		return headPanel;
	}

	private JLabel getHeadlineLabel() {
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

	private JLabel getDifficultyValueLabel() {
		logger.trace("getDifficultyValueLabel() - Entering");
		if (difficultyValueLabel == null) {
			difficultyValueLabel = new JLabel();
			difficultyValueLabel.setHorizontalAlignment(JLabel.CENTER);
		}
		logger.trace("getDifficultyValueLabel() - Returning");
		logger.debug("getDifficultyValueLabel() - Returning: {}",
				difficultyValueLabel);
		return difficultyValueLabel;
	}

	private JPanel getMyChampPanel() {
		logger.trace("getMyChampPanel() - Entering");
		if (myChampPanel == null) {
			myChampPanel = new MatchupDetailChampionPanel(false);
		}
		logger.trace("getMyChampPanel() - Returning");
		logger.debug("getMyChampPanel() - Returning: {}", myChampPanel);
		return myChampPanel;
	}

	private JLabel getVersusLabel() {
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

	private JPanel getEnemyChampPanel() {
		logger.trace("getEnemyChampPanel() - Entering");
		if (enemyChampPanel == null) {
			enemyChampPanel = new MatchupDetailChampionPanel(true);
		}
		logger.trace("getEnemyChampPanel() - Returning");
		logger.debug("getEnemyChampPanel() - Returning: {}", enemyChampPanel);
		return enemyChampPanel;
	}

	private JPanel getNotesPanel() {
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
		}
		logger.trace("getNotesPanel() - Returning");
		logger.debug("getNotesPanel() - Returning: {}", notesPanel);
		return notesPanel;
	}

	private JLabel getNotesLabel() {
		logger.trace("getNotesLabel() - Entering");
		if (notesLabel == null) {
			notesLabel = new JLabel("Notes for the matchup:");
		}
		logger.trace("getNotesLabel() - Returning");
		logger.debug("getNotesLabel() - Returning: {}", notesLabel);
		return notesLabel;
	}

	private JScrollPane getNotesScrollPane() {
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

	private JTextArea getNotesTextarea() {
		logger.trace("getNotesTextarea() - Entering");
		if (notesTextarea == null) {
			notesTextarea = new JTextArea();
			notesTextarea.setLineWrap(true);
		}
		logger.trace("getNotesTextarea() - Returning");
		logger.debug("getNotesTextarea() - Returning: {}", notesTextarea);
		return notesTextarea;
	}

	private JButton getSaveNotesButton() {
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

	private JPanel getGameNotesPanel() {
		logger.trace("getGameNotesPanel() - Entering");
		if (gameNotesPanel == null) {
			gameNotesPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			gameNotesPanel.add(getGameNotesLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			constraints.weighty = 1;
			gameNotesPanel.add(getGameNotesScrollPane(), constraints);
		}
		logger.trace("getGameNotesPanel() - Returning");
		logger.debug("getGameNotesPanel() - Returning: {}", gameNotesPanel);
		return gameNotesPanel;
	}

	private JLabel getGameNotesLabel() {
		logger.trace("getGameNotesLabel() - Entering");
		if (gameNotesLabel == null) {
			gameNotesLabel = new JLabel("Notes for the matchup:");
		}
		logger.trace("getGameNotesLabel() - Returning");
		logger.debug("getGameNotesLabel() - Returning: {}", gameNotesLabel);
		return gameNotesLabel;
	}

	private JScrollPane getGameNotesScrollPane() {
		logger.trace("getGameNotesScrollPane() - Entering");
		if (gameNotesScrollPane == null) {
			gameNotesScrollPane = new JScrollPane(getGameNotesTextarea());
			gameNotesScrollPane.setMinimumSize(new Dimension(0, 250));
			gameNotesScrollPane.setMaximumSize(new Dimension(
					(int) getMaximumSize().getWidth(), 350));
		}
		logger.trace("getGameNotesScrollPane() - Returning");
		logger.debug("getGameNotesScrollPane() - Returning: {}",
				gameNotesScrollPane);
		return gameNotesScrollPane;
	}

	private JTextArea getGameNotesTextarea() {
		logger.trace("getGameNotesTextarea() - Entering");
		if (gameNotesTextarea == null) {
			gameNotesTextarea = new JTextArea();
			gameNotesTextarea.setLineWrap(true);
		}
		logger.trace("getGameNotesTextarea() - Returning");
		logger.debug("getGameNotesTextarea() - Returning: {}",
				gameNotesTextarea);
		return gameNotesTextarea;
	}

	private JButton getSaveGameNotesButton() {
		logger.trace("getSaveGameNotesButton() - Entering");
		if (saveGameNotesButton == null) {
			saveGameNotesButton = new JButton("Save changes to notes");
			saveGameNotesButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					matchup.setNotes(getNotesTextarea().getText());
					GameUtil.saveGame(matchup.getGame());
				}
			});
		}
		logger.trace("getSaveGameNotesButton() - Returning");
		logger.debug("getSaveGameNotesButton() - Returning: {}",
				saveGameNotesButton);
		return saveGameNotesButton;
	}

	private JPanel getGamePanel() {
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

	private JLabel getGameStatsLabel() {
		logger.trace("getGameStatsLabel() - Entering");
		if (gameStatsLabel == null) {
			gameStatsLabel = new JLabel("Game statistics:");
		}
		logger.trace("getGameStatsLabel() - Returning");
		logger.debug("getGameStatsLabel() - Returning: {}", gameStatsLabel);
		return gameStatsLabel;
	}

	private JLabel getKillsLabel() {
		logger.trace("getKillsLabel() - Entering");
		if (killsLabel == null) {
			killsLabel = new JLabel("Kills:");
		}
		logger.trace("getKillsLabel() - Returning");
		logger.debug("getKillsLabel() - Returning: {}", killsLabel);
		return killsLabel;
	}

	private JLabel getKillsValueLabel() {
		logger.trace("getKillsValueLabel() - Entering");
		if (killsValueLabel == null) {
			killsValueLabel = new JLabel();
		}
		logger.trace("getKillsValueLabel() - Returning");
		logger.debug("getKillsValueLabel() - Returning: {}", killsValueLabel);
		return killsValueLabel;
	}

	private JLabel getDeathsLabel() {
		logger.trace("getDeathsLabel() - Entering");
		if (deathsLabel == null) {
			deathsLabel = new JLabel("Deaths:");
		}
		logger.trace("getDeathsLabel() - Returning");
		logger.debug("getDeathsLabel() - Returning: {}", deathsLabel);
		return deathsLabel;
	}

	private JLabel getDeathsValueLabel() {
		logger.trace("getDeathsValueLabel() - Entering");
		if (deathsValueLabel == null) {
			deathsValueLabel = new JLabel();
		}
		logger.trace("getDeathsValueLabel() - Returning");
		logger.debug("getDeathsValueLabel() - Returning: {}", deathsValueLabel);
		return deathsValueLabel;
	}

	private JLabel getAssistsLabel() {
		logger.trace("getAssistsLabel() - Entering");
		if (assistsLabel == null) {
			assistsLabel = new JLabel("Assists:");
		}
		logger.trace("getAssistsLabel() - Returning");
		logger.debug("getAssistsLabel() - Returning: {}", assistsLabel);
		return assistsLabel;
	}

	private JLabel getAssistsValueLabel() {
		logger.trace("getAssistsValueLabel() - Entering");
		if (assistsValueLabel == null) {
			assistsValueLabel = new JLabel();
		}
		logger.trace("getAssistsValueLabel() - Returning");
		logger.debug("getAssistsValueLabel() - Returning: {}",
				assistsValueLabel);
		return assistsValueLabel;
	}

	private JLabel getCsLabel() {
		logger.trace("getCsLabel() - Entering");
		if (csLabel == null) {
			csLabel = new JLabel("CS:");
			csLabel.setToolTipText("Amount of minions slain");
		}
		logger.trace("getCsLabel() - Returning");
		logger.debug("getCsLabel() - Returning: {}", csLabel);
		return csLabel;
	}

	private JLabel getCsValueLabel() {
		logger.trace("getCsValueLabel() - Entering");
		if (csLabelValue == null) {
			csLabelValue = new JLabel();
		}
		logger.trace("getCsValueLabel() - Returning");
		logger.debug("getCsValueLabel() - Returning: {}", csLabelValue);
		return csLabelValue;
	}

	private JPanel getMyTeamPanel() {
		logger.trace("getMyTeamPanel() - Entering");
		if (myTeamPanel == null) {
			myTeamPanel = new JPanel(new GridBagLayout());

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.insets = new Insets(0, 0, 2, 0);
			myTeamPanel.add(getGameChamp1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(0, 0, 2, 0);
			myTeamPanel.add(getGameChamp2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.insets = new Insets(0, 0, 2, 0);
			myTeamPanel.add(getGameChamp3Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			myTeamPanel.add(getGameChamp4Label(), constraints);
		}
		logger.trace("getMyTeamPanel() - Returning");
		logger.debug("getMyTeamPanel() - Returning: {}", myTeamPanel);
		return myTeamPanel;
	}

	private JPanel getEnemyTeamPanel() {
		logger.trace("getEnemyTeamPanel() - Entering");
		if (enemyTeamPanel == null) {
			enemyTeamPanel = new JPanel(new GridBagLayout());

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.insets = new Insets(0, 0, 2, 0);
			enemyTeamPanel.add(getGameChamp6Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(0, 0, 2, 0);
			enemyTeamPanel.add(getGameChamp7Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.insets = new Insets(0, 0, 2, 0);
			enemyTeamPanel.add(getGameChamp8Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			enemyTeamPanel.add(getGameChamp9Label(), constraints);
		}
		logger.trace("getEnemyTeamPanel() - Returning");
		logger.debug("getEnemyTeamPanel() - Returning: {}", enemyTeamPanel);
		return enemyTeamPanel;
	}

	private JLabel getGameChamp1Label() {
		logger.trace("getGameChamp1Label() - Entering");
		if (gameChamp1Label == null) {
			gameChamp1Label = new JLabel();
		}
		logger.trace("getGameChamp1Label() - Returning");
		logger.debug("getGameChamp1Label() - Returning: {}", gameChamp1Label);
		return gameChamp1Label;
	}

	private JLabel getGameChamp2Label() {
		logger.trace("getGameChamp2Label() - Entering");
		if (gameChamp2Label == null) {
			gameChamp2Label = new JLabel();
		}
		logger.trace("getGameChamp2Label() - Returning");
		logger.debug("getGameChamp2Label() - Returning: {}", gameChamp2Label);
		return gameChamp2Label;
	}

	private JLabel getGameChamp3Label() {
		logger.trace("getGameChamp3Label() - Entering");
		if (gameChamp3Label == null) {
			gameChamp3Label = new JLabel();
		}
		logger.trace("getGameChamp3Label() - Returning");
		logger.debug("getGameChamp3Label() - Returning: {}", gameChamp3Label);
		return gameChamp3Label;
	}

	private JLabel getGameChamp4Label() {
		logger.trace("getGameChamp4Label() - Entering");
		if (gameChamp4Label == null) {
			gameChamp4Label = new JLabel();
		}
		logger.trace("getGameChamp4Label() - Returning");
		logger.debug("getGameChamp4Label() - Returning: {}", gameChamp4Label);
		return gameChamp4Label;
	}

	private JLabel getGameChamp6Label() {
		logger.trace("getGameChamp6Label() - Entering");
		if (gameChamp6Label == null) {
			gameChamp6Label = new JLabel();
		}
		logger.trace("getGameChamp6Label() - Returning");
		logger.debug("getGameChamp6Label() - Returning: {}", gameChamp6Label);
		return gameChamp6Label;
	}

	private JLabel getGameChamp7Label() {
		logger.trace("getGameChamp7Label() - Entering");
		if (gameChamp7Label == null) {
			gameChamp7Label = new JLabel();
		}
		logger.trace("getGameChamp7Label() - Returning");
		logger.debug("getGameChamp7Label() - Returning: {}", gameChamp7Label);
		return gameChamp7Label;
	}

	private JLabel getGameChamp8Label() {
		logger.trace("getGameChamp8Label() - Entering");
		if (gameChamp8Label == null) {
			gameChamp8Label = new JLabel();
		}
		logger.trace("getGameChamp8Label() - Returning");
		logger.debug("getGameChamp8Label() - Returning: {}", gameChamp8Label);
		return gameChamp8Label;
	}

	private JLabel getGameChamp9Label() {
		logger.trace("getGameChamp9Label() - Entering");
		if (gameChamp9Label == null) {
			gameChamp9Label = new JLabel();
		}
		logger.trace("getGameChamp9Label() - Returning");
		logger.debug("getGameChamp9Label() - Returning: {}", gameChamp9Label);
		return gameChamp9Label;
	}

	private JButton getCloseButton() {
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

	private void fillFields() {
		if (matchup != null) {
			Matchup m = this.matchup;
			setLane(m.getLane());
			setDifficulty(m.getDifficulty());
			setLane(m.getLane());
			setMyChampionAndItems(m);
			setEnemyChampionAndItems(m);
			this.getNotesTextarea().setText(m.getNotes());
			Game g = m.getGame();
			this.getKillsValueLabel().setText(String.valueOf(g.getOwnKills()));
			this.getDeathsValueLabel()
					.setText(String.valueOf(g.getOwnDeaths()));
			this.getAssistsValueLabel().setText(
					String.valueOf(g.getOwnAssists()));
			this.getCsValueLabel().setText(String.valueOf(g.getOwnCS()));

			int x = 0;
			for (Champion champ : g.getMyTeam()) {
				if (champ != g.getMatchup().getMyChamp()) {
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
					}
					l.setIcon(ImageIconFactory.resizeImageIcon(champ.getIcon(),
							30, 30));
					l.setToolTipText(champ.getName());
				}
			}
			x = 0;
			for (Champion champ : g.getEnemyTeam()) {
				if (champ != g.getMatchup().getEnemyChamp()) {
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
					}
					l.setIcon(ImageIconFactory.resizeImageIcon(champ.getIcon(),
							30, 30));
					l.setToolTipText(champ.getName());
				}
			}
		}
	}

	public static synchronized MatchupDetailDialoge getInstance() {
		logger.trace("getInstance() - Entering");
		logger.debug("getInstance() - Instance is {}", instance);
		if (instance == null) {
			logger.debug("Creating a new instance of MatchupDetailDialoge");
			instance = new MatchupDetailDialoge();
		}
		logger.trace("getInstance() - Returning");
		logger.debug("getInstance() - Returning {}", instance);
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		fillFields();
		this.pack();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((d.width - this.getSize().width) / 2,
				(d.height - this.getSize().height) / 2);
		setVisible(true);
	}

	private void setDifficulty(MatchupDifficulty diff) {
		logger.trace("setDifficulty() - Entering");
		logger.debug("setLane() - Parameter: {}", diff);
		this.getDifficultyValueLabel().setText(diff.toString());
		logger.trace("setDifficulty() - Leaving");
	}

	private void setLane(Role lane) {
		logger.trace("setLane() - Entering");
		logger.debug("setLane() - Parameter: {}", lane);
		this.getHeadlineLabel().setText(lane.toString());
		switch (lane) {
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
		logger.trace("setLane() - Leaving");

	}

	private void setMyChampionAndItems(Matchup m) {
		logger.trace("setMyChampion() - Entering");
		logger.debug("setMyChampion() - Parameter: {}", m);
		myChampPanel.setChampionAndItems(m.getMyChamp(), m.getMyStartItems(),
				m.getMyEndItems(), m.getMySpell1(), m.getMySpell2(),
				m.getResult());
		logger.trace("setMyChampion() - Leaving");
	}

	private void setEnemyChampionAndItems(Matchup m) {
		logger.trace("setEnemyChampionAndItems() - Entering");
		logger.debug("setEnemyChampionAndItems() - Parameter: {}", m);
		MatchupResult r = MatchupResult.WIN;
		if (m.getResult() == MatchupResult.WIN) {
			r = MatchupResult.LOSS;
		}
		enemyChampPanel.setChampionAndItems(m.getEnemyChamp(),
				m.getEnemyStartItems(), m.getEnemyEndItems(),
				m.getEnemySpell1(), m.getEnemySpell2(), r);
		logger.trace("setEnemyChampionAndItems() - Leaving");
	}
}