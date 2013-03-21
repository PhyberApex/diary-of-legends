package de.phyberapex.diaryoflegends.view.dialoge;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.toedter.calendar.JDateChooser;
import de.phyberapex.diaryoflegends.controller.MainController;
import de.phyberapex.diaryoflegends.extra.ImageIconFactory;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.Game;
import de.phyberapex.diaryoflegends.model.GameResult;
import de.phyberapex.diaryoflegends.model.Matchup;
import de.phyberapex.diaryoflegends.model.MatchupDifficulty;
import de.phyberapex.diaryoflegends.model.MatchupResult;
import de.phyberapex.diaryoflegends.model.Role;
import de.phyberapex.diaryoflegends.model.util.GameUtil;
import de.phyberapex.diaryoflegends.model.util.MatchupUtil;
import de.phyberapex.diaryoflegends.view.MainView;
import de.phyberapex.diaryoflegends.view.dialoge.panel.NewEntryChampionPanel;
import de.phyberapex.diaryoflegends.view.dialoge.panel.NewEntryTeamListPanel;

public class NewEntryDialog extends JDialog implements Runnable {

	private static final long serialVersionUID = 2162451784302955479L;
	private static NewEntryDialog instance;
	private List<Champion> myTeamChampions;
	private List<Champion> enemyTeamChampions;
	private JTabbedPane newEntryContentPane;
	private Game toEdit = null;
	private boolean isImport = false;
	private JPanel gameContentPanel;
	private JPanel datePanel;
	private JLabel dateLabel;
	private JDateChooser dateChooser;
	private JPanel lengthPanel;
	private JLabel lengthLabel;
	private JLabel lengthMinutesLabel;
	private JSpinner lengthMinutesSpinner;
	private JLabel lengthSecondsLabel;
	private JSpinner lengthSecondsSpinner;
	private JPanel teamsPanel;
	private JTabbedPane teamsPane;
	private NewEntryTeamListPanel myTeamPanel;
	private JLabel myTeamLabel;
	private NewEntryTeamListPanel enemyTeamPanel;
	private JLabel enemyTeamLabel;
	private JPanel gameResultPanel;
	private JLabel gameResultWonLabel;
	private ButtonGroup winLossGroup = new ButtonGroup();
	private JRadioButton gameResultWonBox;
	private JLabel gameResultLossLabel;
	private JRadioButton gameResultLossBox;
	private JPanel gameStatsPanel;
	private JLabel ownKillsLabel;
	private JSpinner ownKillsTextField;
	private JLabel ownDeathsLabel;
	private JSpinner ownDeathsTextField;
	private JLabel ownAssistsLabel;
	private JSpinner ownAssistsTextField;
	private JLabel ownCSLabel;
	private JSpinner ownCSTextField;
	private JTabbedPane notesPane;
	private JLabel gameNotesLabel;
	private JScrollPane gameNotesScrollPane;
	private JTextArea gameNotesTextArea;
	private JButton goToMatchupButton;

	private JPanel matchupContentPanel;
	private JPanel championsPanel;
	private JTabbedPane championsPane;
	private NewEntryChampionPanel myChampionPanel;
	private JPanel matchupResultPanel;
	private JLabel matchupResultLabel;
	private JComboBox<MatchupResult> matchupResultBox;
	private NewEntryChampionPanel enemyChampionPanel;
	private JPanel difficultyPanel;
	private JLabel difficultyLabel;
	private JComboBox<MatchupDifficulty> matchupDifficultyBox;
	private JPanel lanePanel;
	private JLabel laneLabel;
	private JComboBox<Role> laneBox;
	private JLabel matchupNotesLabel;
	private JScrollPane matchupNotesScrollPane;
	private JTextArea matchupNotesTextArea;

	private JButton goToGameButton;
	private JButton finishButton;

	private static Logger logger = LogManager.getLogger(NewEntryDialog.class
			.getName());

	private NewEntryDialog() {
		super(MainView.getInstance());
		logger.trace("NewEntryDialoge() - Entering");
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		createGUI();
		logger.trace("NewEntryDialoge() - Leaving");
	}

	private JTabbedPane getNewEntryContentPane() {
		logger.trace("getNewEntryContentPane() - Entering");
		if (newEntryContentPane == null) {
			newEntryContentPane = new JTabbedPane();
			newEntryContentPane.addTab("Game", getGameContentPanel());
			newEntryContentPane.addTab("Matchup", getMatchupContentPanel());
			newEntryContentPane.setEnabledAt(1, false);
		}
		logger.trace("getNewEntryContentPane() - Returning");
		logger.debug("getNewEntryContentPane() - Returning: {}",
				newEntryContentPane);
		return newEntryContentPane;
	}

	private JPanel getGameContentPanel() {
		logger.trace("getGameContentPanel() - Entering");
		if (gameContentPanel == null) {
			gameContentPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			constraints.gridwidth = 2;
			constraints.insets = new Insets(5, 10, 0, 10);
			gameContentPanel.add(getDatePanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			constraints.insets = new Insets(5, 10, 0, 10);
			gameContentPanel.add(getLengthPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.insets = new Insets(5, 10, 5, 0);
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			constraints.gridwidth = 2;
			gameContentPanel.add(getTeamsPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.insets = new Insets(5, 5, 0, 10);
			gameContentPanel.add(getGameResultPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			constraints.gridwidth = 2;
			constraints.insets = new Insets(5, 10, 5, 10);
			gameContentPanel.add(getGameStatsPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 4;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			constraints.gridwidth = 2;
			constraints.insets = new Insets(0, 10, 0, 10);
			gameContentPanel.add(getNotesPane(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 5;
			constraints.anchor = GridBagConstraints.EAST;
			constraints.insets = new Insets(10, 0, 10, 10);
			gameContentPanel.add(getGoToMatchupButton(), constraints);
		}
		logger.trace("getGameContentPanel() - Returning");
		logger.debug("getGameContentPanel() - Returning: {}", gameContentPanel);
		return gameContentPanel;
	}

	private JPanel getDatePanel() {
		logger.trace("getDatePanel() - Entering");
		if (datePanel == null) {
			datePanel = new JPanel(new GridBagLayout());
			datePanel.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.LOWERED));

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			datePanel.add(getDateLabel(), constraints);
			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			datePanel.add(getDateChooser(), constraints);
		}
		logger.trace("getDatePanel() - Returning");
		logger.debug("getDatePanel() - Returning: {}", datePanel);
		return datePanel;
	}

	private JLabel getDateLabel() {
		logger.trace("getDateLabel() - Entering");
		if (dateLabel == null) {
			dateLabel = new JLabel("Date of game:");
		}
		logger.trace("getDateLabel() - Returning");
		logger.debug("getDateLabel() - Returning: {}", dateLabel);
		return dateLabel;
	}

	private JDateChooser getDateChooser() {
		logger.trace("getDateChooser() - Entering");
		if (dateChooser == null) {
			dateChooser = new JDateChooser();
			dateChooser.setDateFormatString("yyyy-MM-dd HH:mm");
		}
		logger.trace("getDateChooser() - Returning");
		logger.debug("getDateChooser() - Returning: {}", dateChooser);
		return dateChooser;
	}

	private JPanel getLengthPanel() {
		logger.trace("getLengthPanel() - Entering");
		if (lengthPanel == null) {
			lengthPanel = new JPanel(new GridBagLayout());
			lengthPanel.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.LOWERED));

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			lengthPanel.add(getLengthLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 0;
			lengthPanel.add(getLengthMinutesLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			lengthPanel.add(getLengthMinutesSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 4;
			constraints.gridy = 0;
			lengthPanel.add(getLengthSecondsLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 0;
			lengthPanel.add(getLengthSecondsSpinner(), constraints);
		}
		logger.trace("getLengthPanel() - Returning");
		logger.debug("getLengthPanel() - Returning: {}", lengthPanel);
		return lengthPanel;
	}

	private JLabel getLengthLabel() {
		logger.trace("getLengthLabel() - Entering");
		if (lengthLabel == null) {
			lengthLabel = new JLabel("Length:");
		}
		logger.trace("getLengthLabel() - Returning");
		logger.debug("getLengthLabel() - Returning: {}", lengthLabel);
		return lengthLabel;
	}

	private JLabel getLengthMinutesLabel() {
		logger.trace("getLengthMinutesLabel() - Entering");
		if (lengthMinutesLabel == null) {
			lengthMinutesLabel = new JLabel("minutes");
		}
		logger.trace("getLengthMinutesLabel() - Returning");
		logger.debug("getLengthMinutesLabel() - Returning: {}",
				lengthMinutesLabel);
		return lengthMinutesLabel;
	}

	private JSpinner getLengthMinutesSpinner() {
		logger.trace("getLengthMinutesSpinner() - Entering");
		if (lengthMinutesSpinner == null) {
			lengthMinutesSpinner = new JSpinner();
			lengthMinutesSpinner.setModel(new SpinnerNumberModel(0, 0, 239, 1));
		}
		logger.trace("getLengthMinutesSpinner() - Returning");
		logger.debug("getLengthMinutesSpinner() - Returning: {}",
				lengthMinutesSpinner);
		return lengthMinutesSpinner;
	}

	private JLabel getLengthSecondsLabel() {
		logger.trace("getLengthSecondsLabel() - Entering");
		if (lengthSecondsLabel == null) {
			lengthSecondsLabel = new JLabel("seconds");
		}
		logger.trace("getLengthSecondsLabel() - Returning");
		logger.debug("getLengthSecondsLabel() - Returning: {}",
				lengthSecondsLabel);
		return lengthSecondsLabel;
	}

	private JSpinner getLengthSecondsSpinner() {
		logger.trace("getLengthSecondsSpinner() - Entering");
		if (lengthSecondsSpinner == null) {
			lengthSecondsSpinner = new JSpinner();
			lengthSecondsSpinner.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		}
		logger.trace("getLengthSecondsSpinner() - Returning");
		logger.debug("getLengthSecondsSpinner() - Returning: {}",
				lengthSecondsSpinner);
		return lengthSecondsSpinner;
	}

	private JPanel getTeamsPanel() {
		logger.trace("getTeamsPanel() - Entering");
		if (teamsPanel == null) {
			teamsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			teamsPanel.add(getTeamsPane(), constraints);
		}
		logger.trace("getTeamsPanel() - Returning");
		logger.debug("getTeamsPanel() - Returning: {}", teamsPanel);
		return teamsPanel;
	}

	private JTabbedPane getTeamsPane() {
		logger.trace("getTeamsPane() - Entering");
		if (teamsPane == null) {
			teamsPane = new JTabbedPane();
			teamsPane.addTab(getMyTeamLabel().getText(), getMyTeamPanel());
			teamsPane
					.addTab(getEnemyTeamLabel().getText(), getEnemyTeamPanel());
		}
		logger.trace("getTeamsPane() - Returning");
		logger.debug("getTeamsPane() - Returning: {}", teamsPane);
		return teamsPane;
	}

	private NewEntryTeamListPanel getMyTeamPanel() {
		logger.trace("getMyTeamPanel() - Entering");
		if (myTeamPanel == null) {
			myTeamPanel = new NewEntryTeamListPanel();
		}
		logger.trace("getMyTeamPanel() - Returning");
		logger.debug("getMyTeamPanel() - Returning: {}", myTeamPanel);
		return myTeamPanel;
	}

	private JLabel getMyTeamLabel() {
		logger.trace("getMyTeamLabel() - Entering");
		if (myTeamLabel == null) {
			myTeamLabel = new JLabel("My Team");
		}
		logger.trace("getMyTeamLabel() - Returning");
		logger.debug("getMyTeamLabel() - Returning: {}", myTeamLabel);
		return myTeamLabel;
	}

	private NewEntryTeamListPanel getEnemyTeamPanel() {
		logger.trace("getEnemyTeamPanel() - Entering");
		if (enemyTeamPanel == null) {
			enemyTeamPanel = new NewEntryTeamListPanel();
		}
		logger.trace("getEnemyTeamPanel() - Returning");
		logger.debug("getEnemyTeamPanel() - Returning: {}", enemyTeamPanel);
		return enemyTeamPanel;
	}

	private JLabel getEnemyTeamLabel() {
		logger.trace("getEnemyTeamLabel() - Entering");
		if (enemyTeamLabel == null) {
			enemyTeamLabel = new JLabel("Enemy Team");
		}
		logger.trace("getEnemyTeamLabel() - Returning");
		logger.debug("getEnemyTeamLabel() - Returning: {}", enemyTeamLabel);
		return enemyTeamLabel;
	}

	private JPanel getGameResultPanel() {
		logger.trace("getGameResultPanel() - Entering");
		if (gameResultPanel == null) {
			gameResultPanel = new JPanel(new GridBagLayout());

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			gameResultPanel.add(getGameResultWonLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			gameResultPanel.add(getGameResultWonBox(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 0;
			gameResultPanel.add(getGameResultLossLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 0;
			gameResultPanel.add(getGameResultLossBox(), constraints);
		}
		logger.trace("getGameResultPanel() - Returning");
		logger.debug("getGameResultPanel() - Returning: {}", gameResultPanel);
		return gameResultPanel;
	}

	private JLabel getGameResultWonLabel() {
		logger.trace("getGameResultWonLabel() - Entering");
		if (gameResultWonLabel == null) {
			gameResultWonLabel = new JLabel("Win");
		}
		logger.trace("getGameResultWonLabel() - Returning");
		logger.debug("getGameResultWonLabel() - Returning: {}",
				gameResultWonLabel);
		return gameResultWonLabel;
	}

	private JRadioButton getGameResultWonBox() {
		logger.trace("getGameResultWonBox() - Entering");
		if (gameResultWonBox == null) {
			gameResultWonBox = new JRadioButton();
			winLossGroup.add(gameResultWonBox);
		}
		logger.trace("getGameResultWonBox() - Returning");
		logger.debug("getGameResultWonBox() - Returning: {}", gameResultWonBox);
		return gameResultWonBox;
	}

	private JLabel getGameResultLossLabel() {
		logger.trace("getGameResultLossLabel() - Entering");
		if (gameResultLossLabel == null) {
			gameResultLossLabel = new JLabel("Loss");
		}
		logger.trace("getGameResultLossLabel() - Returning");
		logger.debug("getGameResultLossLabel() - Returning: {}",
				gameResultLossLabel);
		return gameResultLossLabel;
	}

	private JRadioButton getGameResultLossBox() {
		logger.trace("getGameResultLossBox() - Entering");
		if (gameResultLossBox == null) {
			gameResultLossBox = new JRadioButton();
			winLossGroup.add(gameResultLossBox);
		}
		logger.trace("getGameResultLossBox() - Returning");
		logger.debug("getGameResultLossBox() - Returning: {}",
				gameResultLossBox);
		return gameResultLossBox;
	}

	private JPanel getGameStatsPanel() {
		logger.trace("getGameStatsPanel() - Entering");
		if (gameStatsPanel == null) {
			gameStatsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			gameStatsPanel.add(getOwnKillsLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.25;
			gameStatsPanel.add(getOwnKillsSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 0;
			gameStatsPanel.add(getOwnDeathsLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.25;
			gameStatsPanel.add(getOwnDeathsSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 4;
			constraints.gridy = 0;
			gameStatsPanel.add(getOwnAssistsLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 5;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.25;
			gameStatsPanel.add(getOwnAssistsSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 6;
			constraints.gridy = 0;
			gameStatsPanel.add(getOwnCSLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 7;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.25;
			gameStatsPanel.add(getOwnCSSpinner(), constraints);
		}
		logger.trace("getGameStatsPanel() - Returning");
		logger.debug("getGameStatsPanel() - Returning: {}", gameStatsPanel);
		return gameStatsPanel;
	}

	private JLabel getOwnKillsLabel() {
		logger.trace("getOwnKillsLabel() - Entering");
		if (ownKillsLabel == null) {
			ownKillsLabel = new JLabel("Kills:");
		}
		logger.trace("getOwnKillsLabel() - Returning");
		logger.debug("getOwnKillsLabel() - Returning: {}", ownKillsLabel);
		return ownKillsLabel;
	}

	private JSpinner getOwnKillsSpinner() {
		logger.trace("getOwnKillsSpinner() - Entering");
		if (ownKillsTextField == null) {
			ownKillsTextField = new JSpinner();
			ownKillsTextField.setModel(new SpinnerNumberModel(0, 0, 99, 1));
			ownKillsTextField.setPreferredSize(new Dimension(50,
					(int) ownKillsTextField.getPreferredSize().getHeight()));
		}
		logger.trace("getOwnKillsSpinner() - Returning");
		logger.debug("getOwnKillsSpinner() - Returning: {}", ownKillsTextField);
		return ownKillsTextField;
	}

	private JLabel getOwnDeathsLabel() {
		logger.trace("getOwnDeathsLabel() - Entering");
		if (ownDeathsLabel == null) {
			ownDeathsLabel = new JLabel("Deaths:");
		}
		logger.trace("getOwnDeathsLabel() - Returning");
		logger.debug("getOwnDeathsLabel() - Returning: {}", ownDeathsLabel);
		return ownDeathsLabel;
	}

	private JSpinner getOwnDeathsSpinner() {
		logger.trace("getOwnDeathsSpinner() - Entering");
		if (ownDeathsTextField == null) {
			ownDeathsTextField = new JSpinner();
			ownDeathsTextField.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		}
		logger.trace("getOwnDeathsSpinner() - Returning");
		logger.debug("getOwnDeathsSpinner() - Returning: {}",
				ownDeathsTextField);
		return ownDeathsTextField;
	}

	private JLabel getOwnAssistsLabel() {
		logger.trace("getOwnAssistsLabel() - Entering");
		if (ownAssistsLabel == null) {
			ownAssistsLabel = new JLabel("Assists:");
		}
		logger.trace("getOwnAssistsLabel() - Returning");
		logger.debug("getOwnAssistsLabel() - Returning: {}", ownAssistsLabel);
		return ownAssistsLabel;
	}

	private JSpinner getOwnAssistsSpinner() {
		logger.trace("getOwnAssistsSpinner() - Entering");
		if (ownAssistsTextField == null) {
			ownAssistsTextField = new JSpinner();
			ownAssistsTextField.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		}
		logger.trace("getOwnAssistsSpinner() - Returning");
		logger.debug("getOwnAssistsSpinner() - Returning: {}",
				ownAssistsTextField);
		return ownAssistsTextField;
	}

	private JLabel getOwnCSLabel() {
		logger.trace("getOwnCSLabel() - Entering");
		if (ownCSLabel == null) {
			ownCSLabel = new JLabel("CS:");
		}
		logger.trace("getOwnCSLabel() - Returning");
		logger.debug("getOwnCSLabel() - Returning: {}", ownCSLabel);
		return ownCSLabel;
	}

	private JSpinner getOwnCSSpinner() {
		logger.trace("getOwnCSSpinner() - Entering");
		if (ownCSTextField == null) {
			ownCSTextField = new JSpinner();
			ownCSTextField.setModel(new SpinnerNumberModel(0, 0, 999, 1));
		}
		logger.trace("getOwnCSSpinner() - Returning");
		logger.debug("getOwnCSSpinner() - Returning: {}", ownCSTextField);
		return ownCSTextField;
	}

	private JTabbedPane getNotesPane() {
		logger.trace("getNotesPane() - Entering");
		if (notesPane == null) {
			notesPane = new JTabbedPane();
			notesPane.addTab(getGameNotesLabel().getText(),
					getGameNotesScrollPane());
			notesPane.addTab(getMatchupNotesLabel().getText(),
					getMatchupNotesScrollPane());
		}
		logger.trace("getNotesPane() - Returning");
		logger.debug("getNotesPane() - Returning: {}", notesPane);
		return notesPane;
	}

	private JLabel getGameNotesLabel() {
		logger.trace("getGameNotesLabel() - Entering");
		if (gameNotesLabel == null) {
			gameNotesLabel = new JLabel("Game notes:");
		}
		logger.trace("getGameNotesLabel() - Returning");
		logger.debug("getGameNotesLabel() - Returning: {}", gameNotesLabel);
		return gameNotesLabel;
	}

	private JScrollPane getGameNotesScrollPane() {
		logger.trace("getGameNotesScrollPane() - Entering");
		if (gameNotesScrollPane == null) {
			gameNotesScrollPane = new JScrollPane(getGameNotesTextArea());
			gameNotesScrollPane.setPreferredSize(new Dimension(0, 80));
		}
		logger.trace("getGameNotesScrollPane() - Returning");
		logger.debug("getGameNotesScrollPane() - Returning: {}",
				gameNotesScrollPane);
		return gameNotesScrollPane;
	}

	private JTextArea getGameNotesTextArea() {
		logger.trace("getGameNotesTextArea() - Entering");
		if (gameNotesTextArea == null) {
			gameNotesTextArea = new JTextArea();
			gameNotesTextArea.setLineWrap(true);
			gameNotesTextArea.setBorder(BorderFactory.createBevelBorder(1));
		}
		logger.trace("getGameNotesTextArea() - Returning");
		logger.debug("getGameNotesTextArea() - Returning: {}",
				gameNotesTextArea);
		return gameNotesTextArea;
	}

	private JButton getGoToMatchupButton() {
		logger.trace("getGoToMatchupButton() - Entering");
		if (goToMatchupButton == null) {
			goToMatchupButton = new JButton("Matchup ->");
			goToMatchupButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					boolean ok = true;
					String error = "";
					if (getDateChooser().getDate() == null) {
						ok = false;
						error += "- You need to enter a date for this game.\n";
					}
					if (getMyTeamPanel().checkDoubles()) {
						ok = false;
						error += "- There can't be the same champ twice in one team. Please check your team.\n";
					}
					if (getEnemyTeamPanel().checkDoubles()) {
						ok = false;
						error += "- There can't be the same champ twice in one team. Please check the enemy team.\n";
					}
					if (enemyTeamPanel.checkDoubles()) {
						ok = false;
						error += "- You need to set all five champions for both teams.\n";
					}
					if (!getGameResultLossBox().isSelected()
							&& !getGameResultWonBox().isSelected()) {
						ok = false;
						error += "- You need to set if you won or lost this game.\n";
					}
					if (ok) {
						myTeamChampions = new ArrayList<Champion>();
						myTeamChampions.addAll(getMyTeamPanel().getChampions());
						myChampionPanel.setPossibleChampions(myTeamChampions);
						enemyTeamChampions = new ArrayList<Champion>();
						enemyTeamChampions.addAll(getEnemyTeamPanel()
								.getChampions());
						enemyChampionPanel
								.setPossibleChampions(enemyTeamChampions);
						newEntryContentPane.setEnabledAt(1, true);
						newEntryContentPane.setEnabledAt(0, false);
						newEntryContentPane.setSelectedIndex(1);
						if (toEdit != null) {
							getMyChampionPanel().setChampion(
									toEdit.getMatchup().getMyChamp());
							if (!isImport) {
								getEnemyChampionPanel().setChampion(
										toEdit.getMatchup().getEnemyChamp());
							}
							getFinishButton().setText("Save");
						}
					} else {
						JOptionPane.showMessageDialog(MainView.getInstance(),
								error);
					}
				}
			});
		}
		logger.trace("getGoToMatchupButton() - Returning");
		logger.debug("getGoToMatchupButton() - Returning: {}",
				goToMatchupButton);
		return goToMatchupButton;
	}

	private JPanel getMatchupContentPanel() {
		logger.trace("getMatchupContentPanel() - Entering");
		if (matchupContentPanel == null) {
			matchupContentPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 2;
			constraints.weightx = 1;
			constraints.weighty = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.anchor = GridBagConstraints.NORTH;
			constraints.insets = new Insets(5, 10, 0, 10);
			matchupContentPanel.add(getChampionsPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.gridwidth = 2;
			constraints.anchor = GridBagConstraints.LINE_START;
			constraints.insets = new Insets(10, 10, 10, 0);
			matchupContentPanel
					.add(new JLabel(
							"<html>Warning: If you click the \"Game\" Button the selected enemy<br>may change all items and spells will still be set.</html>"),
							constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			constraints.anchor = GridBagConstraints.LINE_START;
			constraints.insets = new Insets(10, 10, 10, 0);
			matchupContentPanel.add(getGoToGameButton(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 3;
			constraints.anchor = GridBagConstraints.LINE_END;
			constraints.insets = new Insets(10, 0, 10, 10);
			matchupContentPanel.add(getFinishButton(), constraints);
		}
		logger.trace("getMatchupContentPanel() - Returning");
		logger.debug("getMatchupContentPanel() - Returning: {}",
				matchupContentPanel);
		return matchupContentPanel;
	}

	private JPanel getChampionsPanel() {
		logger.trace("getChampionsPanel() - Entering");
		if (championsPanel == null) {
			championsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			constraints.weighty = 1;
			constraints.gridwidth = 3;
			constraints.anchor = GridBagConstraints.FIRST_LINE_START;
			championsPanel.add(getChampionsPane(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.3;
			constraints.insets = new Insets(0, 0, 5, 0);
			championsPanel.add(getLanePanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.3;
			constraints.insets = new Insets(0, 5, 5, 0);
			championsPanel.add(getDifficultyPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.3;
			constraints.insets = new Insets(0, 5, 5, 0);
			championsPanel.add(getMatchupResultPanel(), constraints);
		}
		logger.trace("getChampionsPanel() - Returning");
		logger.debug("getChampionsPanel() - Returning: {}", championsPanel);
		return championsPanel;
	}

	private JTabbedPane getChampionsPane() {
		logger.trace("getChampionsPane() - Entering");
		if (championsPane == null) {
			championsPane = new JTabbedPane();
			championsPane.addTab("My champion", getMyChampionPanel());
			championsPane.addTab("Enemy champion", getEnemyChampionPanel());
		}
		logger.trace("getChampionsPane() - Returning");
		logger.debug("getChampionsPane() - Returning: {}", championsPane);
		return championsPane;
	}

	private NewEntryChampionPanel getMyChampionPanel() {
		logger.trace("getMyChampionPanel() - Entering");
		if (myChampionPanel == null) {
			myChampionPanel = new NewEntryChampionPanel();
		}
		logger.trace("getMyChampionPanel() - Returning");
		logger.debug("getMyChampionPanel() - Returning: {}", myChampionPanel);
		return myChampionPanel;
	}

	private NewEntryChampionPanel getEnemyChampionPanel() {
		logger.trace("getEnemyChampionPanel() - Entering");
		if (enemyChampionPanel == null) {
			enemyChampionPanel = new NewEntryChampionPanel();
		}
		logger.trace("getEnemyChampionPanel() - Returning");
		logger.debug("getEnemyChampionPanel() - Returning: {}",
				enemyChampionPanel);
		return enemyChampionPanel;
	}

	private JPanel getMatchupResultPanel() {
		logger.trace("getMatchupResultPanel() - Entering");
		if (matchupResultPanel == null) {
			matchupResultPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			matchupResultPanel.add(getMatchupResultLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			matchupResultPanel.add(getMatchupResultBox(), constraints);
		}
		logger.trace("getMatchupResultPanel() - Returning");
		logger.debug("getMatchupResultPanel() - Returning: {}",
				matchupResultPanel);
		return matchupResultPanel;
	}

	private JLabel getMatchupResultLabel() {
		logger.trace("getMatchupResultLabel() - Entering");
		if (matchupResultLabel == null) {
			matchupResultLabel = new JLabel("Lane outcome:");
		}
		logger.trace("getMatchupResultLabel() - Returning");
		logger.debug("getMatchupResultLabel() - Returning: {}",
				matchupResultLabel);
		return matchupResultLabel;
	}

	private JComboBox<MatchupResult> getMatchupResultBox() {
		logger.trace("getMatchupResultBox() - Entering");
		if (matchupResultBox == null) {
			matchupResultBox = new JComboBox<MatchupResult>(
					new DefaultComboBoxModel<MatchupResult>(
							MatchupResult.values()));
		}
		logger.trace("getMatchupResultBox() - Returning");
		logger.debug("getMatchupResultBox() - Returning: {}", matchupResultBox);
		return matchupResultBox;
	}

	private JPanel getDifficultyPanel() {
		logger.trace("getDifficultyPanel() - Entering");
		if (difficultyPanel == null) {
			difficultyPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			difficultyPanel.add(getDifficultyLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			difficultyPanel.add(getMatchupDifficultyBox(), constraints);

		}
		logger.trace("getDifficultyPanel() - Returning");
		logger.debug("getDifficultyPanel() - Returning: {}", difficultyPanel);
		return difficultyPanel;
	}

	private JLabel getDifficultyLabel() {
		logger.trace("getDifficultyLabel() - Entering");
		if (difficultyLabel == null) {
			difficultyLabel = new JLabel("Matchup diffculty:");
		}
		logger.trace("getDifficultyLabel() - Returning");
		logger.debug("getDifficultyLabel() - Returning: {}", difficultyLabel);
		return difficultyLabel;
	}

	private JComboBox<MatchupDifficulty> getMatchupDifficultyBox() {
		logger.trace("getMatchupDifficultyBox() - Entering");
		if (matchupDifficultyBox == null) {
			matchupDifficultyBox = new JComboBox<MatchupDifficulty>(
					MatchupDifficulty.values());
		}
		logger.trace("getMatchupDifficultyBox() - Returning");
		logger.debug("getMatchupDifficultyBox() - Returning: {}",
				matchupDifficultyBox);
		return matchupDifficultyBox;
	}

	private JPanel getLanePanel() {
		logger.trace("getLanePanel() - Entering");
		if (lanePanel == null) {
			lanePanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			lanePanel.add(getLaneLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			lanePanel.add(getLaneBox(), constraints);
		}
		logger.trace("getLanePanel() - Returning");
		logger.debug("getLanePanel() - Returning: {}", lanePanel);
		return lanePanel;
	}

	private JLabel getLaneLabel() {
		logger.trace("getLaneLabel() - Entering");
		if (laneLabel == null) {
			laneLabel = new JLabel("Lane:");
		}
		logger.trace("getLaneLabel() - Returning");
		logger.debug("getLaneLabel() - Returning: {}", laneLabel);
		return laneLabel;
	}

	private JComboBox<Role> getLaneBox() {
		logger.trace("getLaneBox() - Entering");
		if (laneBox == null) {
			laneBox = new JComboBox<Role>(Role.values());
		}
		logger.trace("getLaneBox() - Returning");
		logger.debug("getLaneBox() - Returning: {}", laneBox);
		return laneBox;
	}

	private JLabel getMatchupNotesLabel() {
		logger.trace("getMatchupNotesLabel() - Entering");
		if (matchupNotesLabel == null) {
			matchupNotesLabel = new JLabel("Matchup notes:");
		}
		logger.trace("getMatchupNotesLabel() - Returning");
		logger.debug("getMatchupNotesLabel() - Returning: {}",
				matchupNotesLabel);
		return matchupNotesLabel;
	}

	private JScrollPane getMatchupNotesScrollPane() {
		logger.trace("getMatchupNotesScrollPane() - Entering");
		if (matchupNotesScrollPane == null) {
			matchupNotesScrollPane = new JScrollPane(getMatchupNotesTextArea());
			matchupNotesScrollPane.setPreferredSize(new Dimension(0, 80));
		}
		logger.trace("getMatchupNotesScrollPane() - Returning");
		logger.debug("getMatchupNotesScrollPane() - Returning: {}",
				matchupNotesScrollPane);
		return matchupNotesScrollPane;
	}

	private JTextArea getMatchupNotesTextArea() {
		logger.trace("getMatchupNotesTextArea() - Entering");
		if (matchupNotesTextArea == null) {
			matchupNotesTextArea = new JTextArea();
			matchupNotesTextArea.setLineWrap(true);
			matchupNotesTextArea.setBorder(BorderFactory.createBevelBorder(1));
		}
		logger.trace("getMatchupNotesTextArea() - Returning");
		logger.debug("getMatchupNotesTextArea() - Returning: {}",
				matchupNotesTextArea);
		return matchupNotesTextArea;
	}

	private JButton getGoToGameButton() {
		logger.trace("getGoToGameButton() - Entering");
		if (goToGameButton == null) {
			goToGameButton = new JButton("<- Game");
			goToGameButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					newEntryContentPane.setEnabledAt(0, true);
					newEntryContentPane.setEnabledAt(1, false);
					newEntryContentPane.setSelectedIndex(0);
				}
			});
		}
		logger.trace("getGoToGameButton() - Returning");
		logger.debug("getGoToGameButton() - Returning: {}", goToGameButton);
		return goToGameButton;
	}

	private JButton getFinishButton() {
		logger.trace("getFinishButton() - Entering");
		if (finishButton == null) {
			finishButton = new JButton("Add to list");
			finishButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					boolean ok = true;
					String error = "";
					if (!ok) {
						JOptionPane.showMessageDialog(MainView.getInstance(),
								error);
					} else {
						Game game = new Game();
						if (toEdit != null) {
							game = toEdit;
						}
						game.setDate(getDateChooser().getDate());
						long length = ((int) getLengthMinutesSpinner()
								.getValue() * 60)
								+ ((int) getLengthSecondsSpinner().getValue());
						game.setLength(length);
						game.setEnemyTeam(enemyTeamChampions);
						game.setMyTeam(myTeamChampions);
						game.setNotes(getGameNotesTextArea().getText());
						if (getGameResultLossBox().isSelected()) {
							game.setResult(GameResult.LOSS);
						} else {
							game.setResult(GameResult.WIN);
						}
						game.setOwnKills((int) getOwnKillsSpinner().getValue());
						game.setOwnDeaths((int) getOwnDeathsSpinner()
								.getValue());
						game.setOwnAssists((int) getOwnAssistsSpinner()
								.getValue());
						game.setOwnCS((int) getOwnCSSpinner().getValue());
						Matchup matchup = new Matchup();
						if (toEdit != null) {
							matchup = toEdit.getMatchup();
						}
						matchup.setGame(game);
						matchup.setMyChamp(getMyChampionPanel().getChampion());
						matchup.setEnemyChamp(getEnemyChampionPanel()
								.getChampion());
						matchup.setNotes(getMatchupNotesTextArea().getText());
						matchup.setLane(getLaneBox().getItemAt(
								getLaneBox().getSelectedIndex()));
						matchup.setDifficulty(getMatchupDifficultyBox()
								.getItemAt(
										getMatchupDifficultyBox()
												.getSelectedIndex()));
						matchup.setResult(getMatchupResultBox().getItemAt(
								getMatchupResultBox().getSelectedIndex()));

						matchup.setMyStartItems(getMyChampionPanel()
								.getStartItems());
						matchup.setMyEndItems(getMyChampionPanel()
								.getEndItems());

						matchup.setEnemyStartItems(getEnemyChampionPanel()
								.getStartItems());
						matchup.setEnemyEndItems(getEnemyChampionPanel()
								.getEndItems());
						matchup.setMySpell1(getMyChampionPanel().getSpell1());
						matchup.setMySpell2(getMyChampionPanel().getSpell2());
						matchup.setEnemySpell1(getEnemyChampionPanel()
								.getSpell1());
						matchup.setEnemySpell2(getEnemyChampionPanel()
								.getSpell2());
						game.setMatchup(matchup);
						if (toEdit != null && !isImport) {
							MainController.getInstance().updated();
						} else {
							MainView.getInstance().getMatchupPanel()
									.addMatchup(matchup);
						}
						MatchupUtil.saveMatchup(game.getMatchup());
						GameUtil.saveGame(game);
						dispose();
					}
				}
			});
		}
		logger.trace("getFinishButton() - Returning");
		logger.debug("getFinishButton() - Returning: {}", finishButton);
		return finishButton;
	}

	public void setToEdit(Game toEdit, boolean isImport) {
		logger.trace("setToEdit() - Entering");
		logger.debug("setToEdit() - Parameter: {}, {}", toEdit, isImport);
		this.toEdit = toEdit;
		this.isImport = isImport;
		logger.trace("setToEdit() - Leaving");
	}

	@Override
	public void dispose() {
		clearFields();
		super.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		logger.trace("run() - Entering");
		if (this.toEdit != null) {
			fillFields();
		}
		getNewEntryContentPane().setEnabledAt(0, true);
		getNewEntryContentPane().setEnabledAt(1, false);
		setVisible(true);
		logger.trace("run() - Leaving");
	}

	private void createGUI() {
		logger.trace("createGUI() - Entering");
		this.setIconImage(ImageIconFactory.createImageIconFromResourePath(
				"img/icon_128.png").getImage());
		this.setTitle("New game");
		this.setModal(true);
		this.setResizable(false);
		this.setContentPane(getNewEntryContentPane());
		this.pack();
		this.setLocationRelativeTo(MainView.getInstance());
		logger.trace("createGUI() - Leaving");
	}

	private void clearFields() {
		this.setTitle("New game");
		toEdit = null;
		isImport = false;
		getTeamsPane().setSelectedIndex(0);
		getNotesPane().setSelectedIndex(0);
		getChampionsPane().setSelectedIndex(0);
		getNewEntryContentPane().setSelectedIndex(0);
		getDateChooser().setDate(null);
		getLengthMinutesSpinner().setValue(0);
		getLengthSecondsSpinner().setValue(0);
		myTeamPanel.clear();
		enemyTeamPanel.clear();
		getGameResultWonBox().setSelected(false);
		getGameResultLossBox().setSelected(false);
		getOwnKillsSpinner().setValue(0);
		getOwnDeathsSpinner().setValue(0);
		getOwnAssistsSpinner().setValue(0);
		getOwnCSSpinner().setValue(0);
		getGameNotesTextArea().setText("");
		getLaneBox().setSelectedIndex(0);
		getMatchupDifficultyBox().setSelectedIndex(0);
		getMatchupResultBox().setSelectedIndex(0);
		getMyChampionPanel().clear();
		getEnemyChampionPanel().clear();
		getMatchupNotesTextArea().setText("");
	}

	private void fillFields() {
		logger.trace("fillFields() - Entering");
		if (isImport) {
			this.setTitle("Import game");
		} else {
			this.setTitle("Edit game");
		}
		getDateChooser().setDate(toEdit.getDate());
		long lengthFull = toEdit.getLenght();
		int lengthMin = (int) lengthFull / 60;
		int lengthSec = (int) lengthFull - lengthMin * 60;
		getLengthMinutesSpinner().setValue(lengthMin);
		getLengthSecondsSpinner().setValue(lengthSec);
		myTeamPanel.setChampions(toEdit.getMyTeam());
		enemyTeamPanel.setChampions(toEdit.getEnemyTeam());
		if (toEdit.getResult() == GameResult.WIN) {
			getGameResultWonBox().setSelected(true);
		} else {
			getGameResultLossBox().setSelected(true);
		}
		getOwnKillsSpinner().setValue(toEdit.getOwnKills());
		getOwnDeathsSpinner().setValue(toEdit.getOwnDeaths());
		getOwnAssistsSpinner().setValue(toEdit.getOwnAssists());
		getOwnCSSpinner().setValue(toEdit.getOwnCS());
		getGameNotesTextArea().setText(toEdit.getNotes());

		Matchup m = toEdit.getMatchup();
		if (!isImport) {
			getLaneBox().setSelectedItem(m.getLane());
			getMatchupDifficultyBox().setSelectedItem(m.getDifficulty());
			getMatchupResultBox().setSelectedItem(m.getResult());
		}
		getMyChampionPanel().fillFields(m.getMyChamp(), m.getMyStartItems(),
				m.getMyEndItems(), m.getMySpell1(), m.getMySpell2());
		getEnemyChampionPanel().fillFields(m.getEnemyChamp(),
				m.getEnemyStartItems(), m.getEnemyEndItems(),
				m.getEnemySpell1(), m.getEnemySpell2());
		getMatchupNotesTextArea().setText(m.getNotes());
		logger.trace("fillFields() - Leaving");
	}

	public static synchronized NewEntryDialog getInstance() {
		logger.trace("getInstance() - Entering");
		logger.debug("getInstance() - Instance is {}", instance);
		if (instance == null) {
			logger.debug("Creating a new instance of NewEntryDialoge");
			instance = new NewEntryDialog();
		}
		logger.trace("getInstance() - Returning");
		logger.debug("getInstance() - Returning {}", instance);
		return instance;
	}
}