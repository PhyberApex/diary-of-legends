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
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.Game;
import de.phyberapex.diaryoflegends.model.GameResult;
import de.phyberapex.diaryoflegends.model.Matchup;
import de.phyberapex.diaryoflegends.model.MatchupDifficulty;
import de.phyberapex.diaryoflegends.model.MatchupItem;
import de.phyberapex.diaryoflegends.model.MatchupResult;
import de.phyberapex.diaryoflegends.model.Role;
import de.phyberapex.diaryoflegends.model.SummonerSpell;
import de.phyberapex.diaryoflegends.model.util.GameUtil;
import de.phyberapex.diaryoflegends.model.util.MatchupUtil;
import de.phyberapex.diaryoflegends.model.util.SummonerSpellUtil;
import de.phyberapex.diaryoflegends.view.MainView;
import de.phyberapex.diaryoflegends.view.dialoge.comboBox.ChampionComboBox;
import de.phyberapex.diaryoflegends.view.dialoge.comboBox.SummonerSpellComboBox;
import de.phyberapex.diaryoflegends.view.dialoge.panel.NewEntryItemPanel;
import de.phyberapex.diaryoflegends.view.dialoge.panel.NewEntryTeamListPanel;

public class NewEntryDialog extends JDialog implements Runnable {

	private static final long serialVersionUID = 2162451784302955479L;
	private static NewEntryDialog instance;
	private List<SummonerSpell> allSpells;
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
	private JPanel myChampionPanel;
	private JLabel myChampionLabel;
	private ChampionComboBox myChampionBox;
	private JTabbedPane myItemsPane;
	private JPanel myStartItemsPanel;
	private List<NewEntryItemPanel> myItemPanelList;
	private JPanel myEndItemsPanel;
	private List<NewEntryItemPanel> myEndItemPanelList;
	private JPanel mySpellsPanel;
	private JLabel mySpell1Label;
	private SummonerSpellComboBox mySpell1Box;
	private JLabel mySpell2Label;
	private SummonerSpellComboBox mySpell2Box;
	private JPanel matchupResultPanel;
	private JLabel matchupResultLabel;
	private JComboBox<MatchupResult> matchupResultBox;
	private JPanel enemyChampionPanel;
	private JLabel enemyChampionLabel;
	private ChampionComboBox enemyChampionBox;
	private JTabbedPane enemyItemsPane;
	private JPanel enemyStartItemsPanel;
	private List<NewEntryItemPanel> enemyItemPanelList;
	private JPanel enemyEndItemsPanel;
	private List<NewEntryItemPanel> enemyEndItemPanelList;
	private JPanel enemySpellsPanel;
	private JLabel enemySpell1Label;
	private SummonerSpellComboBox enemySpell1Box;
	private JLabel enemySpell2Label;
	private SummonerSpellComboBox enemySpell2Box;
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
			constraints.weightx = 0.5;
			constraints.insets = new Insets(5, 10, 0, 0);
			gameContentPanel.add(getDatePanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.5;
			constraints.insets = new Insets(5, 5, 0, 10);
			gameContentPanel.add(getLengthPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.insets = new Insets(5, 10, 5, 10);
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			constraints.gridwidth = 2;
			gameContentPanel.add(getTeamsPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			constraints.gridwidth = 2;
			constraints.insets = new Insets(0, 10, 0, 10);
			gameContentPanel.add(getNotesPane(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 4;
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
			constraints.gridheight = 2;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			teamsPanel.add(getTeamsPane(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.insets = new Insets(25, 0, 0, 0);
			teamsPanel.add(getGameResultPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.insets = new Insets(20, 10, 0, 10);
			teamsPanel.add(getGameStatsPanel(), constraints);
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
			constraints.gridx = 0;
			constraints.gridy = 1;
			gameResultPanel.add(getGameResultLossLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
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
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			gameStatsPanel.add(getOwnKillsSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			gameStatsPanel.add(getOwnDeathsLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			gameStatsPanel.add(getOwnDeathsSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			gameStatsPanel.add(getOwnAssistsLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			gameStatsPanel.add(getOwnAssistsSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			gameStatsPanel.add(getOwnCSLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 3;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
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
						getMyChampionBox().setModel(
								new DefaultComboBoxModel<Champion>(
										myTeamChampions
												.toArray(new Champion[] {})));
						enemyTeamChampions = new ArrayList<Champion>();
						enemyTeamChampions.addAll(getEnemyTeamPanel()
								.getChampions());
						getEnemyChampionBox().setModel(
								new DefaultComboBoxModel<Champion>(
										enemyTeamChampions
												.toArray(new Champion[] {})));
						newEntryContentPane.setEnabledAt(1, true);
						newEntryContentPane.setEnabledAt(0, false);
						newEntryContentPane.setSelectedIndex(1);
						if (toEdit != null) {
							getMyChampionBox().setSelectedItem(
									toEdit.getMatchup().getMyChamp());
							if (!isImport) {
								getEnemyChampionBox().setSelectedItem(
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
			constraints.fill = GridBagConstraints.BOTH;
			constraints.insets = new Insets(5, 10, 0, 10);
			matchupContentPanel.add(getChampionsPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.anchor = GridBagConstraints.LINE_START;
			constraints.insets = new Insets(10, 10, 10, 0);
			matchupContentPanel.add(getGoToGameButton(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
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
			championsPane.addTab(getMyChampionLabel().getText(),
					getMyChampionPanel());
			championsPane.addTab(getEnemyChampionLabel().getText(),
					getEnemyChampionPanel());
		}
		logger.trace("getChampionsPane() - Returning");
		logger.debug("getChampionsPane() - Returning: {}", championsPane);
		return championsPane;
	}

	private JPanel getMyChampionPanel() {
		logger.trace("getMyChampionPanel() - Entering");
		if (myChampionPanel == null) {
			myChampionPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			constraints.insets = new Insets(0, 0, 5, 0);
			myChampionPanel.add(getMyChampionBox(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myChampionPanel.add(getMyItemsPane(), constraints);
		}
		logger.trace("getMyChampionPanel() - Returning");
		logger.debug("getMyChampionPanel() - Returning: {}", myChampionPanel);
		return myChampionPanel;
	}

	private JLabel getMyChampionLabel() {
		logger.trace("getMyChampionLabel() - Entering");
		if (myChampionLabel == null) {
			myChampionLabel = new JLabel("My champion");
		}
		logger.trace("getMyChampionLabel() - Returning");
		logger.debug("getMyChampionLabel() - Returning: {}", myChampionLabel);
		return myChampionLabel;
	}

	private ChampionComboBox getMyChampionBox() {
		logger.trace("getMyChampionBox() - Entering");
		if (myChampionBox == null) {
			myChampionBox = new ChampionComboBox();
		}
		logger.trace("getMyChampionBox() - Returning");
		logger.debug("getMyChampionBox() - Returning: {}", myChampionBox);
		return myChampionBox;
	}

	private JTabbedPane getMyItemsPane() {
		logger.trace("getMyItemsPane() - Entering");
		if (myItemsPane == null) {
			myItemsPane = new JTabbedPane();
			myItemsPane.setTabPlacement(JTabbedPane.BOTTOM);
			myItemsPane.addTab("Start items", getMyStartItemsPanel());
			myItemsPane.addTab("End items", getMyEndItemsPanel());
			myItemsPane.addTab("Summoner Spells", getMySpellsPanel());
		}
		logger.trace("getMyItemsPane() - Returning");
		logger.debug("getMyItemsPane() - Returning: {}", myItemsPane);
		return myItemsPane;
	}

	private JPanel getMyStartItemsPanel() {
		logger.trace("getMyStartItemsPanel() - Entering");
		if (myStartItemsPanel == null) {
			myStartItemsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints;
			int x = 0;
			for (NewEntryItemPanel panel : getMyItemPanelList()) {
				constraints = new GridBagConstraints();
				constraints.gridx = 0;
				constraints.gridy = x;
				constraints.fill = GridBagConstraints.HORIZONTAL;
				constraints.weightx = 1;
				myStartItemsPanel.add(panel, constraints);
				x++;
			}
		}
		logger.trace("getMyStartItemsPanel() - Returning");
		logger.debug("getMyStartItemsPanel() - Returning: {}",
				myStartItemsPanel);
		return myStartItemsPanel;
	}

	private List<NewEntryItemPanel> getMyItemPanelList() {
		logger.trace("getMyItem1Label() - Entering");
		if (myItemPanelList == null) {
			myItemPanelList = new ArrayList<>();
			for (int i = 0; i < 6; i++) {
				myItemPanelList.add(new NewEntryItemPanel(i + 1));
			}
		}
		logger.trace("getMyItem1Label() - Returning");
		logger.debug("getMyItem1Label() - Returning: {}", myItemPanelList);
		return myItemPanelList;
	}

	private JPanel getMyEndItemsPanel() {
		logger.trace("getMyEndItemsPanel() - Entering");
		if (myEndItemsPanel == null) {
			myEndItemsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints;
			int x = 0;
			for (NewEntryItemPanel panel : getMyEndItemPanelList()) {
				constraints = new GridBagConstraints();
				constraints.gridx = 0;
				constraints.gridy = x;
				constraints.fill = GridBagConstraints.HORIZONTAL;
				constraints.weightx = 1;
				myEndItemsPanel.add(panel, constraints);
				x++;
			}
		}
		logger.trace("getMyEndItemsPanel() - Returning");
		logger.debug("getMyEndItemsPanel() - Returning: {}", myEndItemsPanel);
		return myEndItemsPanel;
	}

	private List<NewEntryItemPanel> getMyEndItemPanelList() {
		logger.trace("getMyEndItemPanelList() - Entering");
		if (myEndItemPanelList == null) {
			myEndItemPanelList = new ArrayList<>();
			for (int i = 0; i < 6; i++) {
				myEndItemPanelList.add(new NewEntryItemPanel(i + 1));
			}
		}
		logger.trace("getMyEndItemPanelList() - Returning");
		logger.debug("getMyEndItemPanelList() - Returning: {}",
				myEndItemPanelList);
		return myEndItemPanelList;
	}

	private JPanel getMySpellsPanel() {
		logger.trace("getMySpellsPanel() - Entering");
		if (mySpellsPanel == null) {
			mySpellsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			mySpellsPanel.add(getMySpell1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			mySpellsPanel.add(getMySpell1Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			mySpellsPanel.add(getMySpell2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			mySpellsPanel.add(getMySpell2Box(), constraints);
		}
		logger.trace("getMySpellsPanel() - Returning");
		logger.debug("getMySpellsPanel() - Returning: {}", mySpellsPanel);
		return mySpellsPanel;
	}

	private JLabel getMySpell1Label() {
		logger.trace("getMySpell1Label() - Entering");
		if (mySpell1Label == null) {
			mySpell1Label = new JLabel("Summoner Spell 1");
		}
		logger.trace("getMySpell1Label() - Returning");
		logger.debug("getMySpell1Label() - Returning: {}", mySpell1Label);
		return mySpell1Label;
	}

	private SummonerSpellComboBox getMySpell1Box() {
		logger.trace("getMySpell1Box() - Entering");
		if (mySpell1Box == null) {
			mySpell1Box = new SummonerSpellComboBox(
					new DefaultComboBoxModel<SummonerSpell>(
							allSpells.toArray(new SummonerSpell[] {})));
		}
		logger.trace("getMySpell1Box() - Returning");
		logger.debug("getMySpell1Box() - Returning: {}", mySpell1Box);
		return mySpell1Box;
	}

	private JLabel getMySpell2Label() {
		logger.trace("getMySpell2Label() - Entering");
		if (mySpell2Label == null) {
			mySpell2Label = new JLabel("Summoner Spell 2");
		}
		logger.trace("getMySpell2Label() - Returning");
		logger.debug("getMySpell2Label() - Returning: {}", mySpell2Label);
		return mySpell2Label;
	}

	private SummonerSpellComboBox getMySpell2Box() {
		logger.trace("getMySpell2Box() - Entering");
		if (mySpell2Box == null) {
			mySpell2Box = new SummonerSpellComboBox(
					new DefaultComboBoxModel<SummonerSpell>(
							allSpells.toArray(new SummonerSpell[] {})));
		}
		logger.trace("getMySpell2Box() - Returning");
		logger.debug("getMySpell2Box() - Returning: {}", mySpell2Box);
		return mySpell2Box;
	}

	private JPanel getEnemyChampionPanel() {
		logger.trace("getEnemyChampionPanel() - Entering");
		if (enemyChampionPanel == null) {
			enemyChampionPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			constraints.insets = new Insets(0, 0, 5, 0);
			enemyChampionPanel.add(getEnemyChampionBox(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyChampionPanel.add(getEnemyItemsPane(), constraints);
		}
		logger.trace("getEnemyChampionPanel() - Returning");
		logger.debug("getEnemyChampionPanel() - Returning: {}",
				enemyChampionPanel);
		return enemyChampionPanel;
	}

	private JLabel getEnemyChampionLabel() {
		logger.trace("getEnemyChampionLabel() - Entering");
		if (enemyChampionLabel == null) {
			enemyChampionLabel = new JLabel("Enemy champion");
		}
		logger.trace("getEnemyChampionLabel() - Returning");
		logger.debug("getEnemyChampionLabel() - Returning: {}",
				enemyChampionLabel);
		return enemyChampionLabel;
	}

	private ChampionComboBox getEnemyChampionBox() {
		logger.trace("getEnemyChampionBox() - Entering");
		if (enemyChampionBox == null) {
			enemyChampionBox = new ChampionComboBox();
		}
		logger.trace("getEnemyChampionBox() - Returning");
		logger.debug("getEnemyChampionBox() - Returning: {}", enemyChampionBox);
		return enemyChampionBox;
	}

	private JTabbedPane getEnemyItemsPane() {
		logger.trace("getEnemyItemsPane() - Entering");
		if (enemyItemsPane == null) {
			enemyItemsPane = new JTabbedPane();
			enemyItemsPane.setTabPlacement(JTabbedPane.BOTTOM);
			enemyItemsPane.addTab("Start items", getEnemyStartItemsPanel());
			enemyItemsPane.addTab("End items", getEnemyEndItemsPanel());
			enemyItemsPane.addTab("Summoner Spells", getEnemySpellsPanel());
		}
		logger.trace("getEnemyItemsPane() - Returning");
		logger.debug("getEnemyItemsPane() - Returning: {}", enemyItemsPane);
		return enemyItemsPane;
	}

	private JPanel getEnemyStartItemsPanel() {
		logger.trace("getEnemyStartItemsPanel() - Entering");
		if (enemyStartItemsPanel == null) {
			enemyStartItemsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints;
			int x = 0;
			for (NewEntryItemPanel panel : getEnemyItemPanelList()) {
				constraints = new GridBagConstraints();
				constraints.gridx = 0;
				constraints.gridy = x;
				constraints.fill = GridBagConstraints.HORIZONTAL;
				constraints.weightx = 1;
				enemyStartItemsPanel.add(panel, constraints);
				x++;
			}
		}
		logger.trace("getEnemyStartItemsPanel() - Returning");
		logger.debug("getEnemyStartItemsPanel() - Returning: {}",
				enemyStartItemsPanel);
		return enemyStartItemsPanel;
	}

	private List<NewEntryItemPanel> getEnemyItemPanelList() {
		logger.trace("getEnemyItemPanelList() - Entering");
		if (enemyItemPanelList == null) {
			enemyItemPanelList = new ArrayList<>();
			for (int i = 0; i < 6; i++) {
				enemyItemPanelList.add(new NewEntryItemPanel(i + 1));
			}
		}
		logger.trace("getEnemyItemPanelList() - Returning");
		logger.debug("getEnemyItemPanelList() - Returning: {}",
				enemyItemPanelList);
		return enemyItemPanelList;
	}

	private JPanel getEnemyEndItemsPanel() {
		logger.trace("getEnemyEndItemsPanel() - Entering");
		if (enemyEndItemsPanel == null) {
			enemyEndItemsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints;
			int x = 0;
			for (NewEntryItemPanel panel : getEnemyEndItemPanelList()) {
				constraints = new GridBagConstraints();
				constraints.gridx = 0;
				constraints.gridy = x;
				constraints.fill = GridBagConstraints.HORIZONTAL;
				constraints.weightx = 1;
				enemyEndItemsPanel.add(panel, constraints);
				x++;
			}
		}
		logger.trace("getEnemyEndItemsPanel() - Returning");
		logger.debug("getEnemyEndItemsPanel() - Returning: {}",
				enemyEndItemsPanel);
		return enemyEndItemsPanel;
	}

	private List<NewEntryItemPanel> getEnemyEndItemPanelList() {
		logger.trace("getEnemyEndItemPanelList() - Entering");
		if (enemyEndItemPanelList == null) {
			enemyEndItemPanelList = new ArrayList<>();
			for (int i = 0; i < 6; i++) {
				enemyEndItemPanelList.add(new NewEntryItemPanel(i + 1));
			}
		}
		logger.trace("getEnemyEndItemPanelList() - Returning");
		logger.debug("getEnemyEndItemPanelList() - Returning: {}",
				enemyEndItemPanelList);
		return enemyEndItemPanelList;
	}

	private JPanel getEnemySpellsPanel() {
		logger.trace("getEnemySpellsPanel() - Entering");
		if (enemySpellsPanel == null) {
			enemySpellsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			enemySpellsPanel.add(getEnemySpell1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemySpellsPanel.add(getEnemySpell1Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			enemySpellsPanel.add(getEnemySpell2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemySpellsPanel.add(getEnemySpell2Box(), constraints);
		}
		logger.trace("getEnemySpellsPanel() - Returning");
		logger.debug("getEnemySpellsPanel() - Returning: {}", enemySpellsPanel);
		return enemySpellsPanel;
	}

	private JLabel getEnemySpell1Label() {
		logger.trace("getEnemySpell1Label() - Entering");
		if (enemySpell1Label == null) {
			enemySpell1Label = new JLabel("Summoner Spell 1");
		}
		logger.trace("getEnemySpell1Label() - Returning");
		logger.debug("getEnemySpell1Label() - Returning: {}", enemySpell1Label);
		return enemySpell1Label;
	}

	private SummonerSpellComboBox getEnemySpell1Box() {
		logger.trace("getEnemySpell1Box() - Entering");
		if (enemySpell1Box == null) {
			enemySpell1Box = new SummonerSpellComboBox(
					new DefaultComboBoxModel<SummonerSpell>(
							allSpells.toArray(new SummonerSpell[] {})));
		}
		logger.trace("getEnemySpell1Box() - Returning");
		logger.debug("getEnemySpell1Box() - Returning: {}", enemySpell1Box);
		return enemySpell1Box;
	}

	private JLabel getEnemySpell2Label() {
		logger.trace("getEnemySpell2Label() - Entering");
		if (enemySpell2Label == null) {
			enemySpell2Label = new JLabel("Summoner Spell 2");
		}
		logger.trace("getEnemySpell2Label() - Returning");
		logger.debug("getEnemySpell2Label() - Returning: {}", enemySpell2Label);
		return enemySpell2Label;
	}

	private SummonerSpellComboBox getEnemySpell2Box() {
		logger.trace("getEnemySpell2Box() - Entering");
		if (enemySpell2Box == null) {
			enemySpell2Box = new SummonerSpellComboBox(
					new DefaultComboBoxModel<SummonerSpell>(
							allSpells.toArray(new SummonerSpell[] {})));
		}
		logger.trace("getEnemySpell2Box() - Returning");
		logger.debug("getEnemySpell2Box() - Returning: {}", enemySpell2Box);
		return enemySpell2Box;
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
						matchup.setMyChamp(myTeamChampions
								.get(getMyChampionBox().getSelectedIndex()));
						matchup.setEnemyChamp(enemyTeamChampions
								.get(getEnemyChampionBox().getSelectedIndex()));
						matchup.setNotes(getMatchupNotesTextArea().getText());
						matchup.setLane(getLaneBox().getItemAt(
								getLaneBox().getSelectedIndex()));
						matchup.setDifficulty(getMatchupDifficultyBox()
								.getItemAt(
										getMatchupDifficultyBox()
												.getSelectedIndex()));
						matchup.setResult(getMatchupResultBox().getItemAt(
								getMatchupResultBox().getSelectedIndex()));
						ArrayList<MatchupItem> mis = new ArrayList<MatchupItem>();
						for (NewEntryItemPanel panel : myItemPanelList) {
							if (panel.getMatchupItem() != null) {
								mis.add(panel.getMatchupItem());
							}
						}
						matchup.setMyStartItems(mis);

						mis = new ArrayList<MatchupItem>();
						for (NewEntryItemPanel panel : myEndItemPanelList) {
							if (panel.getMatchupItem() != null) {
								mis.add(panel.getMatchupItem());
							}
						}
						matchup.setMyEndItems(mis);

						mis = new ArrayList<MatchupItem>();
						for (NewEntryItemPanel panel : enemyItemPanelList) {
							if (panel.getMatchupItem() != null) {
								mis.add(panel.getMatchupItem());
							}
						}
						matchup.setEnemyStartItems(mis);

						mis = new ArrayList<MatchupItem>();
						for (NewEntryItemPanel panel : enemyEndItemPanelList) {
							if (panel.getMatchupItem() != null) {
								mis.add(panel.getMatchupItem());
							}
						}
						matchup.setEnemyEndItems(mis);
						if (getMySpell1Box().getSelectedIndex() != 0) {
							matchup.setMySpell1(getMySpell1Box().getItemAt(
									getMySpell1Box().getSelectedIndex()));
						}
						if (getMySpell2Box().getSelectedIndex() != 0) {
							matchup.setMySpell2(getMySpell2Box().getItemAt(
									getMySpell2Box().getSelectedIndex()));
						}
						if (getEnemySpell1Box().getSelectedIndex() != 0) {
							matchup.setEnemySpell1(getEnemySpell1Box()
									.getItemAt(
											getEnemySpell1Box()
													.getSelectedIndex()));
						}
						if (getEnemySpell2Box().getSelectedIndex() != 0) {
							matchup.setEnemySpell2(getEnemySpell2Box()
									.getItemAt(
											getEnemySpell2Box()
													.getSelectedIndex()));
						}
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
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		allSpells = new ArrayList<SummonerSpell>();
		SummonerSpell s = new SummonerSpell(0, "no spell");
		allSpells.add(s);
		allSpells.addAll(SummonerSpellUtil.getAllSpells());
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
		getMyItemsPane().setSelectedIndex(0);
		getEnemyItemsPane().setSelectedIndex(0);
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
		for (NewEntryItemPanel panel : getMyItemPanelList()) {
			panel.clear();
		}
		for (NewEntryItemPanel panel : getMyEndItemPanelList()) {
			panel.clear();
		}
		for (NewEntryItemPanel panel : getEnemyItemPanelList()) {
			panel.clear();
		}
		for (NewEntryItemPanel panel : getEnemyEndItemPanelList()) {
			panel.clear();
		}
		getMySpell1Box().setSelectedIndex(0);
		getMySpell2Box().setSelectedIndex(0);
		getEnemySpell1Box().setSelectedIndex(0);
		getEnemySpell2Box().setSelectedIndex(0);
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
		int currItem = 0;
		for (MatchupItem mi : m.getMyStartItems()) {
			myItemPanelList.get(currItem).setMatchupItem(mi);
			currItem++;
		}
		currItem = 0;
		for (MatchupItem mi : m.getMyEndItems()) {
			myEndItemPanelList.get(currItem).setMatchupItem(mi);
			currItem++;
		}
		currItem = 0;
		for (MatchupItem mi : m.getEnemyStartItems()) {
			enemyItemPanelList.get(currItem).setMatchupItem(mi);
			currItem++;
		}
		currItem = 0;
		for (MatchupItem mi : m.getEnemyEndItems()) {
			enemyEndItemPanelList.get(currItem).setMatchupItem(mi);
			currItem++;
		}
		getMySpell1Box().setSelectedItem(m.getMySpell1());
		getMySpell2Box().setSelectedItem(m.getMySpell2());
		getEnemySpell1Box().setSelectedItem(m.getEnemySpell1());
		getEnemySpell2Box().setSelectedItem(m.getEnemySpell2());
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