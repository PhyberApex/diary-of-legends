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
import de.phyberapex.diaryoflegends.model.Item;
import de.phyberapex.diaryoflegends.model.Matchup;
import de.phyberapex.diaryoflegends.model.MatchupDifficulty;
import de.phyberapex.diaryoflegends.model.MatchupItem;
import de.phyberapex.diaryoflegends.model.MatchupResult;
import de.phyberapex.diaryoflegends.model.Role;
import de.phyberapex.diaryoflegends.model.SummonerSpell;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;
import de.phyberapex.diaryoflegends.model.util.GameUtil;
import de.phyberapex.diaryoflegends.model.util.ItemUtil;
import de.phyberapex.diaryoflegends.model.util.MatchupUtil;
import de.phyberapex.diaryoflegends.model.util.SummonerSpellUtil;
import de.phyberapex.diaryoflegends.view.MainView;
import de.phyberapex.diaryoflegends.view.dialoge.comboBox.ChampionComboBox;
import de.phyberapex.diaryoflegends.view.dialoge.comboBox.ItemComboBox;
import de.phyberapex.diaryoflegends.view.dialoge.comboBox.SummonerSpellComboBox;

public class NewEntryDialoge extends JDialog implements Runnable {

	private static final long serialVersionUID = 2162451784302955479L;
	private static NewEntryDialoge instance;
	private List<Champion> allChampions;
	private Champion[] allChampionsArray;
	private List<Item> allItems;
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
	private JPanel myTeamPanel;
	private JLabel myTeamLabel;
	private JLabel team1Champ1Label;
	private ChampionComboBox team1Champ1Box;
	private JLabel team1Champ2Label;
	private ChampionComboBox team1Champ2Box;
	private JLabel team1Champ3Label;
	private ChampionComboBox team1Champ3Box;
	private JLabel team1Champ4Label;
	private ChampionComboBox team1Champ4Box;
	private JLabel team1Champ5Label;
	private ChampionComboBox team1Champ5Box;
	private JPanel enemyTeamPanel;
	private JLabel enemyTeamLabel;
	private JLabel team2Champ1Label;
	private ChampionComboBox team2Champ1Box;
	private JLabel team2Champ2Label;
	private ChampionComboBox team2Champ2Box;
	private JLabel team2Champ3Label;
	private ChampionComboBox team2Champ3Box;
	private JLabel team2Champ4Label;
	private ChampionComboBox team2Champ4Box;
	private JLabel team2Champ5Label;
	private ChampionComboBox team2Champ5Box;
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
	private JPanel gameNotesPanel;
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
	private JLabel myItem1Label;
	private JSpinner myItem1AmountSpinner;
	private JLabel myItem1xLabel;
	private ItemComboBox myItem1Box;
	private JLabel myItem2Label;
	private JSpinner myItem2AmountSpinner;
	private JLabel myItem2xLabel;
	private ItemComboBox myItem2Box;
	private JLabel myItem3Label;
	private JSpinner myItem3AmountSpinner;
	private JLabel myItem3xLabel;
	private ItemComboBox myItem3Box;
	private JLabel myItem4Label;
	private JSpinner myItem4AmountSpinner;
	private JLabel myItem4xLabel;
	private ItemComboBox myItem4Box;
	private JLabel myItem5Label;
	private JSpinner myItem5AmountSpinner;
	private JLabel myItem5xLabel;
	private ItemComboBox myItem5Box;
	private JLabel myItem6Label;
	private JSpinner myItem6AmountSpinner;
	private JLabel myItem6xLabel;
	private ItemComboBox myItem6Box;
	private JPanel myEndItemsPanel;
	private JLabel myEndItem1Label;
	private JSpinner myEndItem1AmountSpinner;
	private JLabel myEndItem1xLabel;
	private ItemComboBox myEndItem1Box;
	private JLabel myEndItem2Label;
	private JSpinner myEndItem2AmountSpinner;
	private JLabel myEndItem2xLabel;
	private ItemComboBox myEndItem2Box;
	private JLabel myEndItem3Label;
	private JSpinner myEndItem3AmountSpinner;
	private JLabel myEndItem3xLabel;
	private ItemComboBox myEndItem3Box;
	private JLabel myEndItem4Label;
	private JSpinner myEndItem4AmountSpinner;
	private JLabel myEndItem4xLabel;
	private ItemComboBox myEndItem4Box;
	private JLabel myEndItem5Label;
	private JSpinner myEndItem5AmountSpinner;
	private JLabel myEndItem5xLabel;
	private ItemComboBox myEndItem5Box;
	private JLabel myEndItem6Label;
	private JSpinner myEndItem6AmountSpinner;
	private JLabel myEndItem6xLabel;
	private ItemComboBox myEndItem6Box;
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
	private JLabel enemyItem1Label;
	private JSpinner enemyItem1AmountSpinner;
	private JLabel enemyItem1xLabel;
	private ItemComboBox enemyItem1Box;
	private JLabel enemyItem2Label;
	private JSpinner enemyItem2AmountSpinner;
	private JLabel enemyItem2xLabel;
	private ItemComboBox enemyItem2Box;
	private JLabel enemyItem3Label;
	private JSpinner enemyItem3AmountSpinner;
	private JLabel enemyItem3xLabel;
	private ItemComboBox enemyItem3Box;
	private JLabel enemyItem4Label;
	private JSpinner enemyItem4AmountSpinner;
	private JLabel enemyItem4xLabel;
	private ItemComboBox enemyItem4Box;
	private JLabel enemyItem5Label;
	private JSpinner enemyItem5AmountSpinner;
	private JLabel enemyItem5xLabel;
	private ItemComboBox enemyItem5Box;
	private JLabel enemyItem6Label;
	private JSpinner enemyItem6AmountSpinner;
	private JLabel enemyItem6xLabel;
	private ItemComboBox enemyItem6Box;
	private JPanel enemyEndItemsPanel;
	private JLabel enemyEndItem1Label;
	private JSpinner enemyEndItem1AmountSpinner;
	private JLabel enemyEndItem1xLabel;
	private ItemComboBox enemyEndItem1Box;
	private JLabel enemyEndItem2Label;
	private JSpinner enemyEndItem2AmountSpinner;
	private JLabel enemyEndItem2xLabel;
	private ItemComboBox enemyEndItem2Box;
	private JLabel enemyEndItem3Label;
	private JSpinner enemyEndItem3AmountSpinner;
	private JLabel enemyEndItem3xLabel;
	private ItemComboBox enemyEndItem3Box;
	private JLabel enemyEndItem4Label;
	private JSpinner enemyEndItem4AmountSpinner;
	private JLabel enemyEndItem4xLabel;
	private ItemComboBox enemyEndItem4Box;
	private JLabel enemyEndItem5Label;
	private JSpinner enemyEndItem5AmountSpinner;
	private JLabel enemyEndItem5xLabel;
	private ItemComboBox enemyEndItem5Box;
	private JLabel enemyEndItem6Label;
	private JSpinner enemyEndItem6AmountSpinner;
	private JLabel enemyEndItem6xLabel;
	private ItemComboBox enemyEndItem6Box;
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
	private JPanel matchupNotesPanel;
	private JLabel matchupNotesLabel;
	private JScrollPane matchupNotesScrollPane;
	private JTextArea matchupNotesTextArea;

	private JButton goToGameButton;
	private JButton finishButton;

	private static Logger logger = LogManager.getLogger(NewEntryDialoge.class
			.getName());

	private NewEntryDialoge() {
		super(MainView.getInstance());
		logger.trace("NewEntryDialoge() - Entering");
		createGUI();
		logger.trace("NewEntryDialoge() - Leaving");
	}

	public JTabbedPane getNewEntryContentPane() {
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

	public JPanel getGameContentPanel() {
		logger.trace("getGameContentPanel() - Entering");
		if (gameContentPanel == null) {
			gameContentPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.5;
			gameContentPanel.add(getDatePanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.5;
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
			gameContentPanel.add(getNotesPane(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 4;
			gameContentPanel.add(getGoToMatchupButton(), constraints);
		}
		logger.trace("getGameContentPanel() - Returning");
		logger.debug("getGameContentPanel() - Returning: {}", gameContentPanel);
		return gameContentPanel;
	}

	public JPanel getDatePanel() {
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

	public JLabel getDateLabel() {
		logger.trace("getDateLabel() - Entering");
		if (dateLabel == null) {
			dateLabel = new JLabel("Date of game:");
		}
		logger.trace("getDateLabel() - Returning");
		logger.debug("getDateLabel() - Returning: {}", dateLabel);
		return dateLabel;
	}

	public JDateChooser getDateChooser() {
		logger.trace("getDateChooser() - Entering");
		if (dateChooser == null) {
			dateChooser = new JDateChooser();
		}
		logger.trace("getDateChooser() - Returning");
		logger.debug("getDateChooser() - Returning: {}", dateChooser);
		return dateChooser;
	}

	public JPanel getLengthPanel() {
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

	public JLabel getLengthLabel() {
		logger.trace("getLengthLabel() - Entering");
		if (lengthLabel == null) {
			lengthLabel = new JLabel("Length:");
		}
		logger.trace("getLengthLabel() - Returning");
		logger.debug("getLengthLabel() - Returning: {}", lengthLabel);
		return lengthLabel;
	}

	public JLabel getLengthMinutesLabel() {
		logger.trace("getLengthMinutesLabel() - Entering");
		if (lengthMinutesLabel == null) {
			lengthMinutesLabel = new JLabel("minutes");
		}
		logger.trace("getLengthMinutesLabel() - Returning");
		logger.debug("getLengthMinutesLabel() - Returning: {}",
				lengthMinutesLabel);
		return lengthMinutesLabel;
	}

	public JSpinner getLengthMinutesSpinner() {
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

	public JLabel getLengthSecondsLabel() {
		logger.trace("getLengthSecondsLabel() - Entering");
		if (lengthSecondsLabel == null) {
			lengthSecondsLabel = new JLabel("seconds");
		}
		logger.trace("getLengthSecondsLabel() - Returning");
		logger.debug("getLengthSecondsLabel() - Returning: {}",
				lengthSecondsLabel);
		return lengthSecondsLabel;
	}

	public JSpinner getLengthSecondsSpinner() {
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

	public JPanel getTeamsPanel() {
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

	public JPanel getMyTeamPanel() {
		logger.trace("getMyTeamPanel() - Entering");
		if (myTeamPanel == null) {
			myTeamPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			myTeamPanel.add(getTeam1Champ1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myTeamPanel.add(getTeam1Champ1Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			myTeamPanel.add(getTeam1Champ2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myTeamPanel.add(getTeam1Champ2Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			myTeamPanel.add(getTeam1Champ3Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myTeamPanel.add(getTeam1Champ3Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			myTeamPanel.add(getTeam1Champ4Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 3;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myTeamPanel.add(getTeam1Champ4Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 4;
			myTeamPanel.add(getTeam1Champ5Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 4;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myTeamPanel.add(getTeam1Champ5Box(), constraints);
		}
		logger.trace("getMyTeamPanel() - Returning");
		logger.debug("getMyTeamPanel() - Returning: {}", myTeamPanel);
		return myTeamPanel;
	}

	public JLabel getMyTeamLabel() {
		logger.trace("getMyTeamLabel() - Entering");
		if (myTeamLabel == null) {
			myTeamLabel = new JLabel("My Team");
		}
		logger.trace("getMyTeamLabel() - Returning");
		logger.debug("getMyTeamLabel() - Returning: {}", myTeamLabel);
		return myTeamLabel;
	}

	public JLabel getTeam1Champ1Label() {
		logger.trace("getTeam1Champ1Label() - Entering");
		if (team1Champ1Label == null) {
			team1Champ1Label = new JLabel("Top");
		}
		logger.trace("getTeam1Champ1Label() - Returning");
		logger.debug("getTeam1Champ1Label() - Returning: {}", team1Champ1Label);
		return team1Champ1Label;
	}

	public ChampionComboBox getTeam1Champ1Box() {
		logger.trace("getTeam1Champ1Box() - Entering");
		if (team1Champ1Box == null) {
			team1Champ1Box = new ChampionComboBox(
					new DefaultComboBoxModel<Champion>(allChampionsArray));
		}
		logger.trace("getTeam1Champ1Box() - Returning");
		logger.debug("getTeam1Champ1Box() - Returning: {}", team1Champ1Box);
		return team1Champ1Box;
	}

	public JLabel getTeam1Champ2Label() {
		logger.trace("getTeam1Champ2Label() - Entering");
		if (team1Champ2Label == null) {
			team1Champ2Label = new JLabel("Mid");
		}
		logger.trace("getTeam1Champ2Label() - Returning");
		logger.debug("getTeam1Champ2Label() - Returning: {}", team1Champ2Label);
		return team1Champ2Label;
	}

	public ChampionComboBox getTeam1Champ2Box() {
		logger.trace("getTeam1Champ2Box() - Entering");
		if (team1Champ2Box == null) {
			team1Champ2Box = new ChampionComboBox(
					new DefaultComboBoxModel<Champion>(allChampionsArray));
		}
		logger.trace("getTeam1Champ2Box() - Returning");
		logger.debug("getTeam1Champ2Box() - Returning: {}", team1Champ2Box);
		return team1Champ2Box;
	}

	public JLabel getTeam1Champ3Label() {
		logger.trace("getTeam1Champ3Label() - Entering");
		if (team1Champ3Label == null) {
			team1Champ3Label = new JLabel("Jungle");
		}
		logger.trace("getTeam1Champ3Label() - Returning");
		logger.debug("getTeam1Champ3Label() - Returning: {}", team1Champ3Label);
		return team1Champ3Label;
	}

	public ChampionComboBox getTeam1Champ3Box() {
		logger.trace("getTeam1Champ3Box() - Entering");
		if (team1Champ3Box == null) {
			team1Champ3Box = new ChampionComboBox(
					new DefaultComboBoxModel<Champion>(allChampionsArray));
		}
		logger.trace("getTeam1Champ3Box() - Returning");
		logger.debug("getTeam1Champ3Box() - Returning: {}", team1Champ3Box);
		return team1Champ3Box;
	}

	public JLabel getTeam1Champ4Label() {
		logger.trace("getTeam1Champ4Label() - Entering");
		if (team1Champ4Label == null) {
			team1Champ4Label = new JLabel("AD-Carry");
		}
		logger.trace("getTeam1Champ4Label() - Returning");
		logger.debug("getTeam1Champ4Label() - Returning: {}", team1Champ4Label);
		return team1Champ4Label;
	}

	public ChampionComboBox getTeam1Champ4Box() {
		logger.trace("getTeam1Champ4Box() - Entering");
		if (team1Champ4Box == null) {
			team1Champ4Box = new ChampionComboBox(
					new DefaultComboBoxModel<Champion>(allChampionsArray));
		}
		logger.trace("getTeam1Champ4Box() - Returning");
		logger.debug("getTeam1Champ4Box() - Returning: {}", team1Champ4Box);
		return team1Champ4Box;
	}

	public JLabel getTeam1Champ5Label() {
		logger.trace("getTeam1Champ5Label() - Entering");
		if (team1Champ5Label == null) {
			team1Champ5Label = new JLabel("Support");
		}
		logger.trace("getTeam1Champ5Label() - Returning");
		logger.debug("getTeam1Champ5Label() - Returning: {}", team1Champ5Label);
		return team1Champ5Label;
	}

	public ChampionComboBox getTeam1Champ5Box() {
		logger.trace("getTeam1Champ5Box() - Entering");
		if (team1Champ5Box == null) {
			team1Champ5Box = new ChampionComboBox(
					new DefaultComboBoxModel<Champion>(allChampionsArray));
		}
		logger.trace("getTeam1Champ5Box() - Returning");
		logger.debug("getTeam1Champ5Box() - Returning: {}", team1Champ5Box);
		return team1Champ5Box;
	}

	public JPanel getEnemyTeamPanel() {
		logger.trace("getEnemyTeamPanel() - Entering");
		if (enemyTeamPanel == null) {
			enemyTeamPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			enemyTeamPanel.add(getTeam2Champ1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyTeamPanel.add(getTeam2Champ1Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			enemyTeamPanel.add(getTeam2Champ2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyTeamPanel.add(getTeam2Champ2Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			enemyTeamPanel.add(getTeam2Champ3Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyTeamPanel.add(getTeam2Champ3Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			enemyTeamPanel.add(getTeam2Champ4Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 3;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyTeamPanel.add(getTeam2Champ4Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 4;
			enemyTeamPanel.add(getTeam2Champ5Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 4;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyTeamPanel.add(getTeam2Champ5Box(), constraints);
		}
		logger.trace("getEnemyTeamPanel() - Returning");
		logger.debug("getEnemyTeamPanel() - Returning: {}", enemyTeamPanel);
		return enemyTeamPanel;
	}

	public JLabel getEnemyTeamLabel() {
		logger.trace("getEnemyTeamLabel() - Entering");
		if (enemyTeamLabel == null) {
			enemyTeamLabel = new JLabel("Enemy Team");
		}
		logger.trace("getEnemyTeamLabel() - Returning");
		logger.debug("getEnemyTeamLabel() - Returning: {}", enemyTeamLabel);
		return enemyTeamLabel;
	}

	public JLabel getTeam2Champ1Label() {
		logger.trace("getTeam2Champ1Label() - Entering");
		if (team2Champ1Label == null) {
			team2Champ1Label = new JLabel("Top");
		}
		logger.trace("getTeam2Champ1Label() - Returning");
		logger.debug("getTeam2Champ1Label() - Returning: {}", team2Champ1Label);
		return team2Champ1Label;
	}

	public ChampionComboBox getTeam2Champ1Box() {
		logger.trace("getTeam2Champ1Box() - Entering");
		if (team2Champ1Box == null) {
			team2Champ1Box = new ChampionComboBox(
					new DefaultComboBoxModel<Champion>(allChampionsArray));
		}
		logger.trace("getTeam2Champ1Box() - Returning");
		logger.debug("getTeam2Champ1Box() - Returning: {}", team2Champ1Box);
		return team2Champ1Box;
	}

	public JLabel getTeam2Champ2Label() {
		logger.trace("getTeam2Champ2Label() - Entering");
		if (team2Champ2Label == null) {
			team2Champ2Label = new JLabel("Mid");
		}
		logger.trace("getTeam2Champ2Label() - Returning");
		logger.debug("getTeam2Champ2Label() - Returning: {}", team2Champ2Label);
		return team2Champ2Label;
	}

	public ChampionComboBox getTeam2Champ2Box() {
		logger.trace("getTeam2Champ2Box() - Entering");
		if (team2Champ2Box == null) {
			team2Champ2Box = new ChampionComboBox(
					new DefaultComboBoxModel<Champion>(allChampionsArray));
		}
		logger.trace("getTeam2Champ2Box() - Returning");
		logger.debug("getTeam2Champ2Box() - Returning: {}", team2Champ2Box);
		return team2Champ2Box;
	}

	public JLabel getTeam2Champ3Label() {
		logger.trace("getTeam2Champ3Label() - Entering");
		if (team2Champ3Label == null) {
			team2Champ3Label = new JLabel("Jungle");
		}
		logger.trace("getTeam2Champ3Label() - Returning");
		logger.debug("getTeam2Champ3Label() - Returning: {}", team2Champ3Label);
		return team2Champ3Label;
	}

	public ChampionComboBox getTeam2Champ3Box() {
		logger.trace("getTeam2Champ3Box() - Entering");
		if (team2Champ3Box == null) {
			team2Champ3Box = new ChampionComboBox(
					new DefaultComboBoxModel<Champion>(allChampionsArray));
		}
		logger.trace("getTeam2Champ3Box() - Returning");
		logger.debug("getTeam2Champ3Box() - Returning: {}", team2Champ3Box);
		return team2Champ3Box;
	}

	public JLabel getTeam2Champ4Label() {
		logger.trace("getTeam2Champ4Label() - Entering");
		if (team2Champ4Label == null) {
			team2Champ4Label = new JLabel("AD-Carry");
		}
		logger.trace("getTeam2Champ4Label() - Returning");
		logger.debug("getTeam2Champ4Label() - Returning: {}", team2Champ4Label);
		return team2Champ4Label;
	}

	public ChampionComboBox getTeam2Champ4Box() {
		logger.trace("getTeam2Champ4Box() - Entering");
		if (team2Champ4Box == null) {
			team2Champ4Box = new ChampionComboBox(
					new DefaultComboBoxModel<Champion>(allChampionsArray));
		}
		logger.trace("getTeam2Champ4Box() - Returning");
		logger.debug("getTeam2Champ4Box() - Returning: {}", team2Champ4Box);
		return team2Champ4Box;
	}

	public JLabel getTeam2Champ5Label() {
		logger.trace("getTeam2Champ5Label() - Entering");
		if (team2Champ5Label == null) {
			team2Champ5Label = new JLabel("Support");
		}
		logger.trace("getTeam2Champ5Label() - Returning");
		logger.debug("getTeam2Champ5Label() - Returning: {}", team2Champ5Label);
		return team2Champ5Label;
	}

	public ChampionComboBox getTeam2Champ5Box() {
		logger.trace("getTeam2Champ5Box() - Entering");
		if (team2Champ5Box == null) {
			team2Champ5Box = new ChampionComboBox(
					new DefaultComboBoxModel<Champion>(allChampionsArray));
		}
		logger.trace("getTeam2Champ5Box() - Returning");
		logger.debug("getTeam2Champ5Box() - Returning: {}", team2Champ5Box);
		return team2Champ5Box;
	}

	public JPanel getGameResultPanel() {
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

	public JLabel getGameResultWonLabel() {
		logger.trace("getGameResultWonLabel() - Entering");
		if (gameResultWonLabel == null) {
			gameResultWonLabel = new JLabel("Win");
		}
		logger.trace("getGameResultWonLabel() - Returning");
		logger.debug("getGameResultWonLabel() - Returning: {}",
				gameResultWonLabel);
		return gameResultWonLabel;
	}

	public JRadioButton getGameResultWonBox() {
		logger.trace("getGameResultWonBox() - Entering");
		if (gameResultWonBox == null) {
			gameResultWonBox = new JRadioButton();
			winLossGroup.add(gameResultWonBox);
		}
		logger.trace("getGameResultWonBox() - Returning");
		logger.debug("getGameResultWonBox() - Returning: {}", gameResultWonBox);
		return gameResultWonBox;
	}

	public JLabel getGameResultLossLabel() {
		logger.trace("getGameResultLossLabel() - Entering");
		if (gameResultLossLabel == null) {
			gameResultLossLabel = new JLabel("Loss");
		}
		logger.trace("getGameResultLossLabel() - Returning");
		logger.debug("getGameResultLossLabel() - Returning: {}",
				gameResultLossLabel);
		return gameResultLossLabel;
	}

	public JRadioButton getGameResultLossBox() {
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

	public JPanel getGameStatsPanel() {
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

	public JLabel getOwnKillsLabel() {
		logger.trace("getOwnKillsLabel() - Entering");
		if (ownKillsLabel == null) {
			ownKillsLabel = new JLabel("Kills:");
		}
		logger.trace("getOwnKillsLabel() - Returning");
		logger.debug("getOwnKillsLabel() - Returning: {}", ownKillsLabel);
		return ownKillsLabel;
	}

	public JSpinner getOwnKillsSpinner() {
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

	public JLabel getOwnDeathsLabel() {
		logger.trace("getOwnDeathsLabel() - Entering");
		if (ownDeathsLabel == null) {
			ownDeathsLabel = new JLabel("Deaths:");
		}
		logger.trace("getOwnDeathsLabel() - Returning");
		logger.debug("getOwnDeathsLabel() - Returning: {}", ownDeathsLabel);
		return ownDeathsLabel;
	}

	public JSpinner getOwnDeathsSpinner() {
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

	public JLabel getOwnAssistsLabel() {
		logger.trace("getOwnAssistsLabel() - Entering");
		if (ownAssistsLabel == null) {
			ownAssistsLabel = new JLabel("Assists:");
		}
		logger.trace("getOwnAssistsLabel() - Returning");
		logger.debug("getOwnAssistsLabel() - Returning: {}", ownAssistsLabel);
		return ownAssistsLabel;
	}

	public JSpinner getOwnAssistsSpinner() {
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

	public JLabel getOwnCSLabel() {
		logger.trace("getOwnCSLabel() - Entering");
		if (ownCSLabel == null) {
			ownCSLabel = new JLabel("CS:");
		}
		logger.trace("getOwnCSLabel() - Returning");
		logger.debug("getOwnCSLabel() - Returning: {}", ownCSLabel);
		return ownCSLabel;
	}

	public JSpinner getOwnCSSpinner() {
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

	public JPanel getGameNotesPanel() {
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
			constraints.insets = new Insets(0, 10, 5, 10);
			gameNotesPanel.add(getGameNotesScrollPane(), constraints);
		}
		logger.trace("getGameNotesPanel() - Returning");
		logger.debug("getGameNotesPanel() - Returning: {}", gameNotesPanel);
		return gameNotesPanel;
	}

	public JLabel getGameNotesLabel() {
		logger.trace("getGameNotesLabel() - Entering");
		if (gameNotesLabel == null) {
			gameNotesLabel = new JLabel("Game notes:");
		}
		logger.trace("getGameNotesLabel() - Returning");
		logger.debug("getGameNotesLabel() - Returning: {}", gameNotesLabel);
		return gameNotesLabel;
	}

	public JScrollPane getGameNotesScrollPane() {
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

	public JTextArea getGameNotesTextArea() {
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

	public JButton getGoToMatchupButton() {
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
					if ((int) getLengthMinutesSpinner().getValue() == 0
							&& (int) getLengthSecondsSpinner().getValue() == 0) {
						ok = false;
						error += "- You need to enter the length for this game.\n";
					}
					int selected1 = getTeam1Champ1Box().getSelectedIndex();
					int selected2 = getTeam1Champ2Box().getSelectedIndex();
					int selected3 = getTeam1Champ3Box().getSelectedIndex();
					int selected4 = getTeam1Champ4Box().getSelectedIndex();
					int selected5 = getTeam1Champ5Box().getSelectedIndex();

					int selected6 = getTeam2Champ1Box().getSelectedIndex();
					int selected7 = getTeam2Champ2Box().getSelectedIndex();
					int selected8 = getTeam2Champ3Box().getSelectedIndex();
					int selected9 = getTeam2Champ4Box().getSelectedIndex();
					int selected10 = getTeam2Champ5Box().getSelectedIndex();
					if (selected1 == selected2 || selected1 == selected3
							|| selected1 == selected4 || selected1 == selected4
							|| selected1 == selected5 || selected2 == selected3
							|| selected2 == selected4 || selected2 == selected5
							|| selected3 == selected4 || selected3 == selected5
							|| selected4 == selected5) {
						ok = false;
						error += "- There can't be the same champ twice in one team. Please check your team.\n";
					}
					if (selected6 == selected7 || selected6 == selected7
							|| selected6 == selected8 || selected6 == selected9
							|| selected6 == selected10
							|| selected7 == selected8 || selected7 == selected9
							|| selected7 == selected10
							|| selected8 == selected9
							|| selected8 == selected10
							|| selected9 == selected10) {
						ok = false;
						error += "- There can't be the same champ twice in one team. Please check the enemy team.\n";
					}
					if (selected1 == 0 || selected2 == 0 || selected3 == 0
							|| selected4 == 0 || selected5 == 0
							|| selected6 == 0 || selected7 == 0
							|| selected8 == 0 || selected9 == 0
							|| selected10 == 0) {
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
						myTeamChampions.add(allChampions.get(selected1));
						myTeamChampions.add(allChampions.get(selected2));
						myTeamChampions.add(allChampions.get(selected3));
						myTeamChampions.add(allChampions.get(selected4));
						myTeamChampions.add(allChampions.get(selected5));
						getMyChampionBox().setModel(
								new DefaultComboBoxModel<Champion>(
										myTeamChampions
												.toArray(new Champion[] {})));
						enemyTeamChampions = new ArrayList<Champion>();
						enemyTeamChampions.add(allChampions.get(selected6));
						enemyTeamChampions.add(allChampions.get(selected7));
						enemyTeamChampions.add(allChampions.get(selected8));
						enemyTeamChampions.add(allChampions.get(selected9));
						enemyTeamChampions.add(allChampions.get(selected10));
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
							getEnemyChampionBox().setSelectedItem(
									toEdit.getMatchup().getEnemyChamp());
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

	public JPanel getMatchupContentPanel() {
		logger.trace("getMatchupContentPanel() - Entering");
		if (matchupContentPanel == null) {
			matchupContentPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.gridwidth = 2;
			constraints.weightx = 1;
			constraints.weighty = 1;
			constraints.fill = GridBagConstraints.BOTH;
			matchupContentPanel.add(getChampionsPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			matchupContentPanel.add(getGoToGameButton(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			matchupContentPanel.add(getFinishButton(), constraints);
		}
		logger.trace("getMatchupContentPanel() - Returning");
		logger.debug("getMatchupContentPanel() - Returning: {}",
				matchupContentPanel);
		return matchupContentPanel;
	}

	public JPanel getChampionsPanel() {
		logger.trace("getChampionsPanel() - Entering");
		if (championsPanel == null) {
			championsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			constraints.gridwidth = 3;
			championsPanel.add(getChampionsPane(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.3;
			championsPanel.add(getLanePanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.3;
			championsPanel.add(getDifficultyPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.3;
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

	public JPanel getMyChampionPanel() {
		logger.trace("getMyChampionPanel() - Entering");
		if (myChampionPanel == null) {
			myChampionPanel = new JPanel(new GridBagLayout());
			myChampionPanel.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.LOWERED));
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
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

	public JLabel getMyChampionLabel() {
		logger.trace("getMyChampionLabel() - Entering");
		if (myChampionLabel == null) {
			myChampionLabel = new JLabel("My champion");
		}
		logger.trace("getMyChampionLabel() - Returning");
		logger.debug("getMyChampionLabel() - Returning: {}", myChampionLabel);
		return myChampionLabel;
	}

	public ChampionComboBox getMyChampionBox() {
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

	public JPanel getMyStartItemsPanel() {
		logger.trace("getMyStartItemsPanel() - Entering");
		if (myStartItemsPanel == null) {
			myStartItemsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			myStartItemsPanel.add(getMyItem1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			myStartItemsPanel.add(getMyItem1AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 0;
			myStartItemsPanel.add(getMyItem1xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myStartItemsPanel.add(getMyItem1Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			myStartItemsPanel.add(getMyItem2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			myStartItemsPanel.add(getMyItem2AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 1;
			myStartItemsPanel.add(getMyItem2xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myStartItemsPanel.add(getMyItem2Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			myStartItemsPanel.add(getMyItem3Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			myStartItemsPanel.add(getMyItem3AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 2;
			myStartItemsPanel.add(getMyItem3xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 2;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myStartItemsPanel.add(getMyItem3Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			myStartItemsPanel.add(getMyItem4Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 3;
			myStartItemsPanel.add(getMyItem4AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 3;
			myStartItemsPanel.add(getMyItem4xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 3;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myStartItemsPanel.add(getMyItem4Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 4;
			myStartItemsPanel.add(getMyItem5Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 4;
			myStartItemsPanel.add(getMyItem5AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 4;
			myStartItemsPanel.add(getMyItem5xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 4;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myStartItemsPanel.add(getMyItem5Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 5;
			myStartItemsPanel.add(getMyItem6Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 5;
			myStartItemsPanel.add(getMyItem6AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 5;
			myStartItemsPanel.add(getMyItem6xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 5;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myStartItemsPanel.add(getMyItem6Box(), constraints);

		}
		logger.trace("getMyStartItemsPanel() - Returning");
		logger.debug("getMyStartItemsPanel() - Returning: {}",
				myStartItemsPanel);
		return myStartItemsPanel;
	}

	public JLabel getMyItem1Label() {
		logger.trace("getMyItem1Label() - Entering");
		if (myItem1Label == null) {
			myItem1Label = new JLabel("Item slot 1");
		}
		logger.trace("getMyItem1Label() - Returning");
		logger.debug("getMyItem1Label() - Returning: {}", myItem1Label);
		return myItem1Label;
	}

	public JSpinner getMyItem1AmountSpinner() {
		logger.trace("getMyItem1AmountSpinner() - Entering");
		if (myItem1AmountSpinner == null) {
			myItem1AmountSpinner = new JSpinner();
			myItem1AmountSpinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		}
		logger.trace("getMyItem1AmountSpinner() - Returning");
		logger.debug("getMyItem1AmountSpinner() - Returning: {}",
				myItem1AmountSpinner);
		return myItem1AmountSpinner;
	}

	public JLabel getMyItem1xLabel() {
		logger.trace("getMyItem1xLabel() - Entering");
		if (myItem1xLabel == null) {
			myItem1xLabel = new JLabel("x");
		}
		logger.trace("getMyItem1xLabel() - Returning");
		logger.debug("getMyItem1xLabel() - Returning: {}", myItem1xLabel);
		return myItem1xLabel;
	}

	public ItemComboBox getMyItem1Box() {
		logger.trace("getMyItem1Box() - Entering");
		if (myItem1Box == null) {
			myItem1Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getMyItem1Box() - Returning");
		logger.debug("getMyItem1Box() - Returning: {}", myItem1Box);
		return myItem1Box;
	}

	public JLabel getMyItem2Label() {
		logger.trace("getMyItem2Label() - Entering");
		if (myItem2Label == null) {
			myItem2Label = new JLabel("Item slot 2");
		}
		logger.trace("getMyItem2Label() - Returning");
		logger.debug("getMyItem2Label() - Returning: {}", myItem2Label);
		return myItem2Label;
	}

	public JSpinner getMyItem2AmountSpinner() {
		logger.trace("getMyItem2AmountSpinner() - Entering");
		if (myItem2AmountSpinner == null) {
			myItem2AmountSpinner = new JSpinner();
			myItem2AmountSpinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		}
		logger.trace("getMyItem2AmountSpinner() - Returning");
		logger.debug("getMyItem2AmountSpinner() - Returning: {}",
				myItem2AmountSpinner);
		return myItem2AmountSpinner;
	}

	public JLabel getMyItem2xLabel() {
		logger.trace("getMyItem2xLabel() - Entering");
		if (myItem2xLabel == null) {
			myItem2xLabel = new JLabel("x");
		}
		logger.trace("getMyItem2xLabel() - Returning");
		logger.debug("getMyItem2xLabel() - Returning: {}", myItem2xLabel);
		return myItem2xLabel;
	}

	public ItemComboBox getMyItem2Box() {
		logger.trace("getMyItem2Box() - Entering");
		if (myItem2Box == null) {
			myItem2Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getMyItem2Box() - Returning");
		logger.debug("getMyItem2Box() - Returning: {}", myItem2Box);
		return myItem2Box;
	}

	public JLabel getMyItem3Label() {
		logger.trace("getMyItem3Label() - Entering");
		if (myItem3Label == null) {
			myItem3Label = new JLabel("Item slot 3");
		}
		logger.trace("getMyItem3Label() - Returning");
		logger.debug("getMyItem3Label() - Returning: {}", myItem3Label);
		return myItem3Label;
	}

	public JSpinner getMyItem3AmountSpinner() {
		logger.trace("getMyItem3AmountSpinner() - Entering");
		if (myItem3AmountSpinner == null) {
			myItem3AmountSpinner = new JSpinner();
			myItem3AmountSpinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		}
		logger.trace("getMyItem3AmountSpinner() - Returning");
		logger.debug("getMyItem3AmountSpinner() - Returning: {}",
				myItem3AmountSpinner);
		return myItem3AmountSpinner;
	}

	public JLabel getMyItem3xLabel() {
		logger.trace("getMyItem3xLabel() - Entering");
		if (myItem3xLabel == null) {
			myItem3xLabel = new JLabel("x");
		}
		logger.trace("getMyItem3xLabel() - Returning");
		logger.debug("getMyItem3xLabel() - Returning: {}", myItem3xLabel);
		return myItem3xLabel;
	}

	public ItemComboBox getMyItem3Box() {
		logger.trace("getMyItem3Box() - Entering");
		if (myItem3Box == null) {
			myItem3Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getMyItem3Box() - Returning");
		logger.debug("getMyItem3Box() - Returning: {}", myItem3Box);
		return myItem3Box;
	}

	public JLabel getMyItem4Label() {
		logger.trace("getMyItem4Label() - Entering");
		if (myItem4Label == null) {
			myItem4Label = new JLabel("Item slot 4");
		}
		logger.trace("getMyItem4Label() - Returning");
		logger.debug("getMyItem4Label() - Returning: {}", myItem4Label);
		return myItem4Label;
	}

	public JSpinner getMyItem4AmountSpinner() {
		logger.trace("getMyItem4AmountSpinner() - Entering");
		if (myItem4AmountSpinner == null) {
			myItem4AmountSpinner = new JSpinner();
			myItem4AmountSpinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		}
		logger.trace("getMyItem4AmountSpinner() - Returning");
		logger.debug("getMyItem4AmountSpinner() - Returning: {}",
				myItem4AmountSpinner);
		return myItem4AmountSpinner;
	}

	public JLabel getMyItem4xLabel() {
		logger.trace("getMyItem4xLabel() - Entering");
		if (myItem4xLabel == null) {
			myItem4xLabel = new JLabel("x");
		}
		logger.trace("getMyItem4xLabel() - Returning");
		logger.debug("getMyItem4xLabel() - Returning: {}", myItem4xLabel);
		return myItem4xLabel;
	}

	public ItemComboBox getMyItem4Box() {
		logger.trace("getMyItem4Box() - Entering");
		if (myItem4Box == null) {
			myItem4Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getMyItem4Box() - Returning");
		logger.debug("getMyItem4Box() - Returning: {}", myItem4Box);
		return myItem4Box;
	}

	public JLabel getMyItem5Label() {
		logger.trace("getMyItem5Label() - Entering");
		if (myItem5Label == null) {
			myItem5Label = new JLabel("Item slot 5");
		}
		logger.trace("getMyItem5Label() - Returning");
		logger.debug("getMyItem5Label() - Returning: {}", myItem5Label);
		return myItem5Label;
	}

	public JSpinner getMyItem5AmountSpinner() {
		logger.trace("getMyItem5AmountSpinner() - Entering");
		if (myItem5AmountSpinner == null) {
			myItem5AmountSpinner = new JSpinner();
			myItem5AmountSpinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		}
		logger.trace("getMyItem5AmountSpinner() - Returning");
		logger.debug("getMyItem5AmountSpinner() - Returning: {}",
				myItem5AmountSpinner);
		return myItem5AmountSpinner;
	}

	public JLabel getMyItem5xLabel() {
		logger.trace("getMyItem5xLabel() - Entering");
		if (myItem5xLabel == null) {
			myItem5xLabel = new JLabel("x");
		}
		logger.trace("getMyItem5xLabel() - Returning");
		logger.debug("getMyItem5xLabel() - Returning: {}", myItem5xLabel);
		return myItem5xLabel;
	}

	public ItemComboBox getMyItem5Box() {
		logger.trace("getMyItem5Box() - Entering");
		if (myItem5Box == null) {
			myItem5Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getMyItem5Box() - Returning");
		logger.debug("getMyItem5Box() - Returning: {}", myItem5Box);
		return myItem5Box;
	}

	public JLabel getMyItem6Label() {
		logger.trace("getMyItem6Label() - Entering");
		if (myItem6Label == null) {
			myItem6Label = new JLabel("Item slot 6");
		}
		logger.trace("getMyItem6Label() - Returning");
		logger.debug("getMyItem6Label() - Returning: {}", myItem6Label);
		return myItem6Label;
	}

	public JSpinner getMyItem6AmountSpinner() {
		logger.trace("getMyItem6AmountSpinner() - Entering");
		if (myItem6AmountSpinner == null) {
			myItem6AmountSpinner = new JSpinner();
			myItem6AmountSpinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		}
		logger.trace("getMyItem6AmountSpinner() - Returning");
		logger.debug("getMyItem6AmountSpinner() - Returning: {}",
				myItem6AmountSpinner);
		return myItem6AmountSpinner;
	}

	public JLabel getMyItem6xLabel() {
		logger.trace("getMyItem1xLabel() - Entering");
		if (myItem6xLabel == null) {
			myItem6xLabel = new JLabel("x");
		}
		logger.trace("getMyItem6xLabel() - Returning");
		logger.debug("getMyItem6xLabel() - Returning: {}", myItem6xLabel);
		return myItem6xLabel;
	}

	public ItemComboBox getMyItem6Box() {
		logger.trace("getMyItem6Box() - Entering");
		if (myItem6Box == null) {
			myItem6Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getMyItem6Box() - Returning");
		logger.debug("getMyItem6Box() - Returning: {}", myItem6Box);
		return myItem6Box;
	}

	public JPanel getMyEndItemsPanel() {
		logger.trace("getMyEndItemsPanel() - Entering");
		if (myEndItemsPanel == null) {
			myEndItemsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			myEndItemsPanel.add(getMyEndItem1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			myEndItemsPanel.add(getMyEndItem1AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 0;
			myEndItemsPanel.add(getMyEndItem1xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myEndItemsPanel.add(getMyEndItem1Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			myEndItemsPanel.add(getMyEndItem2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			myEndItemsPanel.add(getMyEndItem2AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 1;
			myEndItemsPanel.add(getMyEndItem2xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myEndItemsPanel.add(getMyEndItem2Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			myEndItemsPanel.add(getMyEndItem3Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			myEndItemsPanel.add(getMyEndItem3AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 2;
			myEndItemsPanel.add(getMyEndItem3xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 2;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myEndItemsPanel.add(getMyEndItem3Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			myEndItemsPanel.add(getMyEndItem4Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 3;
			myEndItemsPanel.add(getMyEndItem4AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 3;
			myEndItemsPanel.add(getMyEndItem4xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 3;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myEndItemsPanel.add(getMyEndItem4Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 4;
			myEndItemsPanel.add(getMyEndItem5Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 4;
			myEndItemsPanel.add(getMyEndItem5AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 4;
			myEndItemsPanel.add(getMyEndItem5xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 4;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myEndItemsPanel.add(getMyEndItem5Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 5;
			myEndItemsPanel.add(getMyEndItem6Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 5;
			myEndItemsPanel.add(getMyEndItem6AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 5;
			myEndItemsPanel.add(getMyEndItem6xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 5;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myEndItemsPanel.add(getMyEndItem6Box(), constraints);

		}
		logger.trace("getMyEndItemsPanel() - Returning");
		logger.debug("getMyEndItemsPanel() - Returning: {}", myEndItemsPanel);
		return myEndItemsPanel;
	}

	public JLabel getMyEndItem1Label() {
		logger.trace("getMyEndItem1Label() - Entering");
		if (myEndItem1Label == null) {
			myEndItem1Label = new JLabel("Item slot 1");
		}
		logger.trace("getMyEndItem1Label() - Returning");
		logger.debug("getMyEndItem1Label() - Returning: {}", myEndItem1Label);
		return myEndItem1Label;
	}

	public JSpinner getMyEndItem1AmountSpinner() {
		logger.trace("getMyEndItem1AmountSpinner() - Entering");
		if (myEndItem1AmountSpinner == null) {
			myEndItem1AmountSpinner = new JSpinner();
			myEndItem1AmountSpinner
					.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		}
		logger.trace("getMyEndItem1AmountSpinner() - Returning");
		logger.debug("getMyEndItem1AmountSpinner() - Returning: {}",
				myEndItem1AmountSpinner);
		return myEndItem1AmountSpinner;
	}

	public JLabel getMyEndItem1xLabel() {
		logger.trace("getMyEndItem1xLabel() - Entering");
		if (myEndItem1xLabel == null) {
			myEndItem1xLabel = new JLabel("x");
		}
		logger.trace("getMyEndItem1xLabel() - Returning");
		logger.debug("getMyEndItem1xLabel() - Returning: {}", myEndItem1xLabel);
		return myEndItem1xLabel;
	}

	public ItemComboBox getMyEndItem1Box() {
		logger.trace("getMyEndItem1Box() - Entering");
		if (myEndItem1Box == null) {
			myEndItem1Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getMyEndItem1Box() - Returning");
		logger.debug("getMyEndItem1Box() - Returning: {}", myEndItem1Box);
		return myEndItem1Box;
	}

	public JLabel getMyEndItem2Label() {
		logger.trace("getMyEndItem2Label() - Entering");
		if (myEndItem2Label == null) {
			myEndItem2Label = new JLabel("Item slot 2");
		}
		logger.trace("getMyEndItem2Label() - Returning");
		logger.debug("getMyEndItem2Label() - Returning: {}", myEndItem2Label);
		return myEndItem2Label;
	}

	public JSpinner getMyEndItem2AmountSpinner() {
		logger.trace("getMyEndItem2AmountSpinner() - Entering");
		if (myEndItem2AmountSpinner == null) {
			myEndItem2AmountSpinner = new JSpinner();
			myEndItem2AmountSpinner
					.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		}
		logger.trace("getMyEndItem2AmountSpinner() - Returning");
		logger.debug("getMyEndItem2AmountSpinner() - Returning: {}",
				myEndItem2AmountSpinner);
		return myEndItem2AmountSpinner;
	}

	public JLabel getMyEndItem2xLabel() {
		logger.trace("getMyEndItem2xLabel() - Entering");
		if (myEndItem2xLabel == null) {
			myEndItem2xLabel = new JLabel("x");
		}
		logger.trace("getMyEndItem2xLabel() - Returning");
		logger.debug("getMyEndItem2xLabel() - Returning: {}", myEndItem2xLabel);
		return myEndItem2xLabel;
	}

	public ItemComboBox getMyEndItem2Box() {
		logger.trace("getMyEndItem2Box() - Entering");
		if (myEndItem2Box == null) {
			myEndItem2Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getMyEndItem2Box() - Returning");
		logger.debug("getMyEndItem2Box() - Returning: {}", myEndItem2Box);
		return myEndItem2Box;
	}

	public JLabel getMyEndItem3Label() {
		logger.trace("getMyEndItem3Label() - Entering");
		if (myEndItem3Label == null) {
			myEndItem3Label = new JLabel("Item slot 3");
		}
		logger.trace("getMyEndItem3Label() - Returning");
		logger.debug("getMyEndItem3Label() - Returning: {}", myEndItem3Label);
		return myEndItem3Label;
	}

	public JSpinner getMyEndItem3AmountSpinner() {
		logger.trace("getMyEndItem3AmountSpinner() - Entering");
		if (myEndItem3AmountSpinner == null) {
			myEndItem3AmountSpinner = new JSpinner();
			myEndItem3AmountSpinner
					.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		}
		logger.trace("getMyEndItem3AmountSpinner() - Returning");
		logger.debug("getMyEndItem3AmountSpinner() - Returning: {}",
				myEndItem3AmountSpinner);
		return myEndItem3AmountSpinner;
	}

	public JLabel getMyEndItem3xLabel() {
		logger.trace("getMyEndItem3xLabel() - Entering");
		if (myEndItem3xLabel == null) {
			myEndItem3xLabel = new JLabel("x");
		}
		logger.trace("getMyEndItem3xLabel() - Returning");
		logger.debug("getMyEndItem3xLabel() - Returning: {}", myEndItem3xLabel);
		return myEndItem3xLabel;
	}

	public ItemComboBox getMyEndItem3Box() {
		logger.trace("getMyEndItem3Box() - Entering");
		if (myEndItem3Box == null) {
			myEndItem3Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getMyEndItem3Box() - Returning");
		logger.debug("getMyEndItem3Box() - Returning: {}", myEndItem3Box);
		return myEndItem3Box;
	}

	public JLabel getMyEndItem4Label() {
		logger.trace("getMyEndItem4Label() - Entering");
		if (myEndItem4Label == null) {
			myEndItem4Label = new JLabel("Item slot 4");
		}
		logger.trace("getMyEndItem4Label() - Returning");
		logger.debug("getMyEndItem4Label() - Returning: {}", myEndItem4Label);
		return myEndItem4Label;
	}

	public JSpinner getMyEndItem4AmountSpinner() {
		logger.trace("getMyEndItem4AmountSpinner() - Entering");
		if (myEndItem4AmountSpinner == null) {
			myEndItem4AmountSpinner = new JSpinner();
			myEndItem4AmountSpinner
					.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		}
		logger.trace("getMyEndItem4AmountSpinner() - Returning");
		logger.debug("getMyEndItem4AmountSpinner() - Returning: {}",
				myEndItem4AmountSpinner);
		return myEndItem4AmountSpinner;
	}

	public JLabel getMyEndItem4xLabel() {
		logger.trace("getMyEndItem4xLabel() - Entering");
		if (myEndItem4xLabel == null) {
			myEndItem4xLabel = new JLabel("x");
		}
		logger.trace("getMyEndItem4xLabel() - Returning");
		logger.debug("getMyEndItem4xLabel() - Returning: {}", myEndItem4xLabel);
		return myEndItem4xLabel;
	}

	public ItemComboBox getMyEndItem4Box() {
		logger.trace("getMyEndItem4Box() - Entering");
		if (myEndItem4Box == null) {
			myEndItem4Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getMyEndItem4Box() - Returning");
		logger.debug("getMyEndItem4Box() - Returning: {}", myEndItem4Box);
		return myEndItem4Box;
	}

	public JLabel getMyEndItem5Label() {
		logger.trace("getMyEndItem5Label() - Entering");
		if (myEndItem5Label == null) {
			myEndItem5Label = new JLabel("Item slot 5");
		}
		logger.trace("getMyEndItem5Label() - Returning");
		logger.debug("getMyEndItem5Label() - Returning: {}", myEndItem5Label);
		return myEndItem5Label;
	}

	public JSpinner getMyEndItem5AmountSpinner() {
		logger.trace("getMyEndItem5AmountSpinner() - Entering");
		if (myEndItem5AmountSpinner == null) {
			myEndItem5AmountSpinner = new JSpinner();
			myEndItem5AmountSpinner
					.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		}
		logger.trace("getMyEndItem5AmountSpinner() - Returning");
		logger.debug("getMyEndItem5AmountSpinner() - Returning: {}",
				myEndItem5AmountSpinner);
		return myEndItem5AmountSpinner;
	}

	public JLabel getMyEndItem5xLabel() {
		logger.trace("getMyEndItem5xLabel() - Entering");
		if (myEndItem5xLabel == null) {
			myEndItem5xLabel = new JLabel("x");
		}
		logger.trace("getMyEndItem5xLabel() - Returning");
		logger.debug("getMyEndItem5xLabel() - Returning: {}", myEndItem5xLabel);
		return myEndItem5xLabel;
	}

	public ItemComboBox getMyEndItem5Box() {
		logger.trace("getMyEndItem5Box() - Entering");
		if (myEndItem5Box == null) {
			myEndItem5Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getMyEndItem5Box() - Returning");
		logger.debug("getMyEndItem5Box() - Returning: {}", myEndItem5Box);
		return myEndItem5Box;
	}

	public JLabel getMyEndItem6Label() {
		logger.trace("getMyEndItem6Label() - Entering");
		if (myEndItem6Label == null) {
			myEndItem6Label = new JLabel("Item slot 6");
		}
		logger.trace("getMyEndItem6Label() - Returning");
		logger.debug("getMyEndItem6Label() - Returning: {}", myEndItem6Label);
		return myEndItem6Label;
	}

	public JSpinner getMyEndItem6AmountSpinner() {
		logger.trace("getMyEndItem6AmountSpinner() - Entering");
		if (myEndItem6AmountSpinner == null) {
			myEndItem6AmountSpinner = new JSpinner();
			myEndItem6AmountSpinner
					.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		}
		logger.trace("getMyEndItem6AmountSpinner() - Returning");
		logger.debug("getMyEndItem6AmountSpinner() - Returning: {}",
				myEndItem6AmountSpinner);
		return myEndItem6AmountSpinner;
	}

	public JLabel getMyEndItem6xLabel() {
		logger.trace("getMyEndItem6xLabel() - Entering");
		if (myEndItem6xLabel == null) {
			myEndItem6xLabel = new JLabel("x");
		}
		logger.trace("getMyEndItem6xLabel() - Returning");
		logger.debug("getMyEndItem6xLabel() - Returning: {}", myEndItem6xLabel);
		return myEndItem6xLabel;
	}

	public ItemComboBox getMyEndItem6Box() {
		logger.trace("getMyEndItem6Box() - Entering");
		if (myEndItem6Box == null) {
			myEndItem6Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getMyEndItem6Box() - Returning");
		logger.debug("getMyEndItem6Box() - Returning: {}", myEndItem6Box);
		return myEndItem6Box;
	}

	public JPanel getMySpellsPanel() {
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

	public JLabel getMySpell1Label() {
		logger.trace("getMySpell1Label() - Entering");
		if (mySpell1Label == null) {
			mySpell1Label = new JLabel("Summoner Spell 1");
		}
		logger.trace("getMySpell1Label() - Returning");
		logger.debug("getMySpell1Label() - Returning: {}", mySpell1Label);
		return mySpell1Label;
	}

	public SummonerSpellComboBox getMySpell1Box() {
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

	public JLabel getMySpell2Label() {
		logger.trace("getMySpell2Label() - Entering");
		if (mySpell2Label == null) {
			mySpell2Label = new JLabel("Summoner Spell 2");
		}
		logger.trace("getMySpell2Label() - Returning");
		logger.debug("getMySpell2Label() - Returning: {}", mySpell2Label);
		return mySpell2Label;
	}

	public SummonerSpellComboBox getMySpell2Box() {
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

	public JPanel getEnemyChampionPanel() {
		logger.trace("getEnemyChampionPanel() - Entering");
		if (enemyChampionPanel == null) {
			enemyChampionPanel = new JPanel(new GridBagLayout());
			enemyChampionPanel.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.LOWERED));
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
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

	public JLabel getEnemyChampionLabel() {
		logger.trace("getEnemyChampionLabel() - Entering");
		if (enemyChampionLabel == null) {
			enemyChampionLabel = new JLabel("Enemy champion");
		}
		logger.trace("getEnemyChampionLabel() - Returning");
		logger.debug("getEnemyChampionLabel() - Returning: {}",
				enemyChampionLabel);
		return enemyChampionLabel;
	}

	public ChampionComboBox getEnemyChampionBox() {
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

	public JPanel getEnemyStartItemsPanel() {
		logger.trace("getEnemyStartItemsPanel() - Entering");
		if (enemyStartItemsPanel == null) {
			enemyStartItemsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			enemyStartItemsPanel.add(getEnemyItem1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			enemyStartItemsPanel.add(getEnemyItem1AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 0;
			enemyStartItemsPanel.add(getEnemyItem1xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyStartItemsPanel.add(getEnemyItem1Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			enemyStartItemsPanel.add(getEnemyItem2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			enemyStartItemsPanel.add(getEnemyItem2AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 1;
			enemyStartItemsPanel.add(getEnemyItem2xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyStartItemsPanel.add(getEnemyItem2Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			enemyStartItemsPanel.add(getEnemyItem3Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			enemyStartItemsPanel.add(getEnemyItem3AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 2;
			enemyStartItemsPanel.add(getEnemyItem3xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 2;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyStartItemsPanel.add(getEnemyItem3Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			enemyStartItemsPanel.add(getEnemyItem4Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 3;
			enemyStartItemsPanel.add(getEnemyItem4AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 3;
			enemyStartItemsPanel.add(getEnemyItem4xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 3;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyStartItemsPanel.add(getEnemyItem4Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 4;
			enemyStartItemsPanel.add(getEnemyItem5Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 4;
			enemyStartItemsPanel.add(getEnemyItem5AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 4;
			enemyStartItemsPanel.add(getEnemyItem5xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 4;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyStartItemsPanel.add(getEnemyItem5Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 5;
			enemyStartItemsPanel.add(getEnemyItem6Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 5;
			enemyStartItemsPanel.add(getEnemyItem6AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 5;
			enemyStartItemsPanel.add(getEnemyItem6xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 5;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyStartItemsPanel.add(getEnemyItem6Box(), constraints);

		}
		logger.trace("getEnemyStartItemsPanel() - Returning");
		logger.debug("getEnemyStartItemsPanel() - Returning: {}",
				enemyStartItemsPanel);
		return enemyStartItemsPanel;
	}

	public JLabel getEnemyItem1Label() {
		logger.trace("getEnemyItem1Label() - Entering");
		if (enemyItem1Label == null) {
			enemyItem1Label = new JLabel("Item slot 1");
		}
		logger.trace("getEnemyItem1Label() - Returning");
		logger.debug("getEnemyItem1Label() - Returning: {}", enemyItem1Label);
		return enemyItem1Label;
	}

	public JSpinner getEnemyItem1AmountSpinner() {
		logger.trace("getEnemyItem1AmountSpinner() - Entering");
		if (enemyItem1AmountSpinner == null) {
			enemyItem1AmountSpinner = new JSpinner();
			enemyItem1AmountSpinner
					.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		}
		logger.trace("getEnemyItem1AmountSpinner() - Returning");
		logger.debug("getEnemyItem1AmountSpinner() - Returning: {}",
				enemyItem1AmountSpinner);
		return enemyItem1AmountSpinner;
	}

	public JLabel getEnemyItem1xLabel() {
		logger.trace("getEnemyItem1xLabel() - Entering");
		if (enemyItem1xLabel == null) {
			enemyItem1xLabel = new JLabel("x");
		}
		logger.trace("getEnemyItem1xLabel() - Returning");
		logger.debug("getEnemyItem1xLabel() - Returning: {}", enemyItem1xLabel);
		return enemyItem1xLabel;
	}

	public ItemComboBox getEnemyItem1Box() {
		logger.trace("getEnemyItem1Box() - Entering");
		if (enemyItem1Box == null) {
			enemyItem1Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getEnemyItem1Box() - Returning");
		logger.debug("getEnemyItem1Box() - Returning: {}", enemyItem1Box);
		return enemyItem1Box;
	}

	public JLabel getEnemyItem2Label() {
		logger.trace("getEnemyItem2Label() - Entering");
		if (enemyItem2Label == null) {
			enemyItem2Label = new JLabel("Item slot 2");
		}
		logger.trace("getEnemyItem2Label() - Returning");
		logger.debug("getEnemyItem2Label() - Returning: {}", enemyItem2Label);
		return enemyItem2Label;
	}

	public JSpinner getEnemyItem2AmountSpinner() {
		logger.trace("getEnemyItem2AmountSpinner() - Entering");
		if (enemyItem2AmountSpinner == null) {
			enemyItem2AmountSpinner = new JSpinner();
			enemyItem2AmountSpinner
					.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		}
		logger.trace("getEnemyItem2AmountSpinner() - Returning");
		logger.debug("getEnemyItem2AmountSpinner() - Returning: {}",
				enemyItem2AmountSpinner);
		return enemyItem2AmountSpinner;
	}

	public JLabel getEnemyItem2xLabel() {
		logger.trace("getEnemyItem2xLabel() - Entering");
		if (enemyItem2xLabel == null) {
			enemyItem2xLabel = new JLabel("x");
		}
		logger.trace("getEnemyItem2xLabel() - Returning");
		logger.debug("getEnemyItem2xLabel() - Returning: {}", enemyItem2xLabel);
		return enemyItem2xLabel;
	}

	public ItemComboBox getEnemyItem2Box() {
		logger.trace("getEnemyItem2Box() - Entering");
		if (enemyItem2Box == null) {
			enemyItem2Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getEnemyItem2Box() - Returning");
		logger.debug("getEnemyItem2Box() - Returning: {}", enemyItem2Box);
		return enemyItem2Box;
	}

	public JLabel getEnemyItem3Label() {
		logger.trace("getEnemyItem3Label() - Entering");
		if (enemyItem3Label == null) {
			enemyItem3Label = new JLabel("Item slot 3");
		}
		logger.trace("getEnemyItem3Label() - Returning");
		logger.debug("getEnemyItem3Label() - Returning: {}", enemyItem3Label);
		return enemyItem3Label;
	}

	public JSpinner getEnemyItem3AmountSpinner() {
		logger.trace("getEnemyItem3AmountSpinner() - Entering");
		if (enemyItem3AmountSpinner == null) {
			enemyItem3AmountSpinner = new JSpinner();
			enemyItem3AmountSpinner
					.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		}
		logger.trace("getEnemyItem3AmountSpinner() - Returning");
		logger.debug("getEnemyItem3AmountSpinner() - Returning: {}",
				enemyItem3AmountSpinner);
		return enemyItem3AmountSpinner;
	}

	public JLabel getEnemyItem3xLabel() {
		logger.trace("getEnemyItem3xLabel() - Entering");
		if (enemyItem3xLabel == null) {
			enemyItem3xLabel = new JLabel("x");
		}
		logger.trace("getEnemyItem3xLabel() - Returning");
		logger.debug("getEnemyItem3xLabel() - Returning: {}", enemyItem3xLabel);
		return enemyItem3xLabel;
	}

	public ItemComboBox getEnemyItem3Box() {
		logger.trace("getEnemyItem3Box() - Entering");
		if (enemyItem3Box == null) {
			enemyItem3Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getEnemyItem3Box() - Returning");
		logger.debug("getEnemyItem3Box() - Returning: {}", enemyItem3Box);
		return enemyItem3Box;
	}

	public JLabel getEnemyItem4Label() {
		logger.trace("getEnemyItem4Label() - Entering");
		if (enemyItem4Label == null) {
			enemyItem4Label = new JLabel("Item slot 4");
		}
		logger.trace("getEnemyItem4Label() - Returning");
		logger.debug("getEnemyItem4Label() - Returning: {}", enemyItem4Label);
		return enemyItem4Label;
	}

	public JSpinner getEnemyItem4AmountSpinner() {
		logger.trace("getEnemyItem4AmountSpinner() - Entering");
		if (enemyItem4AmountSpinner == null) {
			enemyItem4AmountSpinner = new JSpinner();
			enemyItem4AmountSpinner
					.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		}
		logger.trace("getEnemyItem4AmountSpinner() - Returning");
		logger.debug("getEnemyItem4AmountSpinner() - Returning: {}",
				enemyItem4AmountSpinner);
		return enemyItem4AmountSpinner;
	}

	public JLabel getEnemyItem4xLabel() {
		logger.trace("getEnemyItem4xLabel() - Entering");
		if (enemyItem4xLabel == null) {
			enemyItem4xLabel = new JLabel("x");
		}
		logger.trace("getEnemyItem4xLabel() - Returning");
		logger.debug("getEnemyItem4xLabel() - Returning: {}", enemyItem4xLabel);
		return enemyItem4xLabel;
	}

	public ItemComboBox getEnemyItem4Box() {
		logger.trace("getEnemyItem4Box() - Entering");
		if (enemyItem4Box == null) {
			enemyItem4Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getEnemyItem4Box() - Returning");
		logger.debug("getEnemyItem4Box() - Returning: {}", enemyItem4Box);
		return enemyItem4Box;
	}

	public JLabel getEnemyItem5Label() {
		logger.trace("getEnemyItem5Label() - Entering");
		if (enemyItem5Label == null) {
			enemyItem5Label = new JLabel("Item slot 5");
		}
		logger.trace("getEnemyItem5Label() - Returning");
		logger.debug("getEnemyItem5Label() - Returning: {}", enemyItem5Label);
		return enemyItem5Label;
	}

	public JSpinner getEnemyItem5AmountSpinner() {
		logger.trace("getEnemyItem5AmountSpinner() - Entering");
		if (enemyItem5AmountSpinner == null) {
			enemyItem5AmountSpinner = new JSpinner();
			enemyItem5AmountSpinner
					.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		}
		logger.trace("getEnemyItem5AmountSpinner() - Returning");
		logger.debug("getEnemyItem5AmountSpinner() - Returning: {}",
				enemyItem5AmountSpinner);
		return enemyItem5AmountSpinner;
	}

	public JLabel getEnemyItem5xLabel() {
		logger.trace("getEnemyItem5xLabel() - Entering");
		if (enemyItem5xLabel == null) {
			enemyItem5xLabel = new JLabel("x");
		}
		logger.trace("getEnemyItem5xLabel() - Returning");
		logger.debug("getEnemyItem5xLabel() - Returning: {}", enemyItem5xLabel);
		return enemyItem5xLabel;
	}

	public ItemComboBox getEnemyItem5Box() {
		logger.trace("getEnemyItem5Box() - Entering");
		if (enemyItem5Box == null) {
			enemyItem5Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getEnemyItem5Box() - Returning");
		logger.debug("getEnemyItem5Box() - Returning: {}", enemyItem5Box);
		return enemyItem5Box;
	}

	public JLabel getEnemyItem6Label() {
		logger.trace("getEnemyItem6Label() - Entering");
		if (enemyItem6Label == null) {
			enemyItem6Label = new JLabel("Item slot 6");
		}
		logger.trace("getEnemyItem6Label() - Returning");
		logger.debug("getEnemyItem6Label() - Returning: {}", enemyItem6Label);
		return enemyItem6Label;
	}

	public JSpinner getEnemyItem6AmountSpinner() {
		logger.trace("getEnemyItem6AmountSpinner() - Entering");
		if (enemyItem6AmountSpinner == null) {
			enemyItem6AmountSpinner = new JSpinner();
			enemyItem6AmountSpinner
					.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		}
		logger.trace("getEnemyItem6AmountSpinner() - Returning");
		logger.debug("getEnemyItem6AmountSpinner() - Returning: {}",
				enemyItem6AmountSpinner);
		return enemyItem6AmountSpinner;
	}

	public JLabel getEnemyItem6xLabel() {
		logger.trace("getEnemyItem1xLabel() - Entering");
		if (enemyItem6xLabel == null) {
			enemyItem6xLabel = new JLabel("x");
		}
		logger.trace("getEnemyItem6xLabel() - Returning");
		logger.debug("getEnemyItem6xLabel() - Returning: {}", enemyItem6xLabel);
		return enemyItem6xLabel;
	}

	public ItemComboBox getEnemyItem6Box() {
		logger.trace("getEnemyItem6Box() - Entering");
		if (enemyItem6Box == null) {
			enemyItem6Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getEnemyItem6Box() - Returning");
		logger.debug("getEnemyItem6Box() - Returning: {}", enemyItem6Box);
		return enemyItem6Box;
	}

	public JPanel getEnemyEndItemsPanel() {
		logger.trace("getEnemyEndItemsPanel() - Entering");
		if (enemyEndItemsPanel == null) {
			enemyEndItemsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			enemyEndItemsPanel.add(getEnemyEndItem1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			enemyEndItemsPanel
					.add(getEnemyEndItem1AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 0;
			enemyEndItemsPanel.add(getEnemyEndItem1xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyEndItemsPanel.add(getEnemyEndItem1Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			enemyEndItemsPanel.add(getEnemyEndItem2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			enemyEndItemsPanel
					.add(getEnemyEndItem2AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 1;
			enemyEndItemsPanel.add(getEnemyEndItem2xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyEndItemsPanel.add(getEnemyEndItem2Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			enemyEndItemsPanel.add(getEnemyEndItem3Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			enemyEndItemsPanel
					.add(getEnemyEndItem3AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 2;
			enemyEndItemsPanel.add(getEnemyEndItem3xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 2;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyEndItemsPanel.add(getEnemyEndItem3Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			enemyEndItemsPanel.add(getEnemyEndItem4Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 3;
			enemyEndItemsPanel
					.add(getEnemyEndItem4AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 3;
			enemyEndItemsPanel.add(getEnemyEndItem4xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 3;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyEndItemsPanel.add(getEnemyEndItem4Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 4;
			enemyEndItemsPanel.add(getEnemyEndItem5Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 4;
			enemyEndItemsPanel
					.add(getEnemyEndItem5AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 4;
			enemyEndItemsPanel.add(getEnemyEndItem5xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 4;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyEndItemsPanel.add(getEnemyEndItem5Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 5;
			enemyEndItemsPanel.add(getEnemyEndItem6Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 5;
			enemyEndItemsPanel
					.add(getEnemyEndItem6AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 5;
			enemyEndItemsPanel.add(getEnemyEndItem6xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 5;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyEndItemsPanel.add(getEnemyEndItem6Box(), constraints);

		}
		logger.trace("getEnemyEndItemsPanel() - Returning");
		logger.debug("getEnemyEndItemsPanel() - Returning: {}",
				enemyEndItemsPanel);
		return enemyEndItemsPanel;
	}

	public JLabel getEnemyEndItem1Label() {
		logger.trace("getEnemyEndItem1Label() - Entering");
		if (enemyEndItem1Label == null) {
			enemyEndItem1Label = new JLabel("Item slot 1");
		}
		logger.trace("getEnemyEndItem1Label() - Returning");
		logger.debug("getEnemyEndItem1Label() - Returning: {}",
				enemyEndItem1Label);
		return enemyEndItem1Label;
	}

	public JSpinner getEnemyEndItem1AmountSpinner() {
		logger.trace("getEnemyEndItem1AmountSpinner() - Entering");
		if (enemyEndItem1AmountSpinner == null) {
			enemyEndItem1AmountSpinner = new JSpinner();
			enemyEndItem1AmountSpinner.setModel(new SpinnerNumberModel(0, 0, 9,
					1));
		}
		logger.trace("getEnemyEndItem1AmountSpinner() - Returning");
		logger.debug("getEnemyEndItem1AmountSpinner() - Returning: {}",
				enemyEndItem1AmountSpinner);
		return enemyEndItem1AmountSpinner;
	}

	public JLabel getEnemyEndItem1xLabel() {
		logger.trace("getEnemyEndItem1xLabel() - Entering");
		if (enemyEndItem1xLabel == null) {
			enemyEndItem1xLabel = new JLabel("x");
		}
		logger.trace("getEnemyEndItem1xLabel() - Returning");
		logger.debug("getEnemyEndItem1xLabel() - Returning: {}",
				enemyEndItem1xLabel);
		return enemyEndItem1xLabel;
	}

	public ItemComboBox getEnemyEndItem1Box() {
		logger.trace("getEnemyEndItem1Box() - Entering");
		if (enemyEndItem1Box == null) {
			enemyEndItem1Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getEnemyEndItem1Box() - Returning");
		logger.debug("getEnemyEndItem1Box() - Returning: {}", enemyEndItem1Box);
		return enemyEndItem1Box;
	}

	public JLabel getEnemyEndItem2Label() {
		logger.trace("getEnemyEndItem2Label() - Entering");
		if (enemyEndItem2Label == null) {
			enemyEndItem2Label = new JLabel("Item slot 2");
		}
		logger.trace("getEnemyEndItem2Label() - Returning");
		logger.debug("getEnemyEndItem2Label() - Returning: {}",
				enemyEndItem2Label);
		return enemyEndItem2Label;
	}

	public JSpinner getEnemyEndItem2AmountSpinner() {
		logger.trace("getEnemyEndItem2AmountSpinner() - Entering");
		if (enemyEndItem2AmountSpinner == null) {
			enemyEndItem2AmountSpinner = new JSpinner();
			enemyEndItem2AmountSpinner.setModel(new SpinnerNumberModel(0, 0, 9,
					1));
		}
		logger.trace("getEnemyEndItem2AmountSpinner() - Returning");
		logger.debug("getEnemyEndItem2AmountSpinner() - Returning: {}",
				enemyEndItem2AmountSpinner);
		return enemyEndItem2AmountSpinner;
	}

	public JLabel getEnemyEndItem2xLabel() {
		logger.trace("getEnemyEndItem2xLabel() - Entering");
		if (enemyEndItem2xLabel == null) {
			enemyEndItem2xLabel = new JLabel("x");
		}
		logger.trace("getEnemyEndItem2xLabel() - Returning");
		logger.debug("getEnemyEndItem2xLabel() - Returning: {}",
				enemyEndItem2xLabel);
		return enemyEndItem2xLabel;
	}

	public ItemComboBox getEnemyEndItem2Box() {
		logger.trace("getEnemyEndItem2Box() - Entering");
		if (enemyEndItem2Box == null) {
			enemyEndItem2Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getEnemyEndItem2Box() - Returning");
		logger.debug("getEnemyEndItem2Box() - Returning: {}", enemyEndItem2Box);
		return enemyEndItem2Box;
	}

	public JLabel getEnemyEndItem3Label() {
		logger.trace("getEnemyEndItem3Label() - Entering");
		if (enemyEndItem3Label == null) {
			enemyEndItem3Label = new JLabel("Item slot 3");
		}
		logger.trace("getEnemyEndItem3Label() - Returning");
		logger.debug("getEnemyEndItem3Label() - Returning: {}",
				enemyEndItem3Label);
		return enemyEndItem3Label;
	}

	public JSpinner getEnemyEndItem3AmountSpinner() {
		logger.trace("getEnemyEndItem3AmountSpinner() - Entering");
		if (enemyEndItem3AmountSpinner == null) {
			enemyEndItem3AmountSpinner = new JSpinner();
			enemyEndItem3AmountSpinner.setModel(new SpinnerNumberModel(0, 0, 9,
					1));
		}
		logger.trace("getEnemyEndItem3AmountSpinner() - Returning");
		logger.debug("getEnemyEndItem3AmountSpinner() - Returning: {}",
				enemyEndItem3AmountSpinner);
		return enemyEndItem3AmountSpinner;
	}

	public JLabel getEnemyEndItem3xLabel() {
		logger.trace("getEnemyEndItem3xLabel() - Entering");
		if (enemyEndItem3xLabel == null) {
			enemyEndItem3xLabel = new JLabel("x");
		}
		logger.trace("getEnemyEndItem3xLabel() - Returning");
		logger.debug("getEnemyEndItem3xLabel() - Returning: {}",
				enemyEndItem3xLabel);
		return enemyEndItem3xLabel;
	}

	public ItemComboBox getEnemyEndItem3Box() {
		logger.trace("getEnemyEndItem3Box() - Entering");
		if (enemyEndItem3Box == null) {
			enemyEndItem3Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getEnemyEndItem3Box() - Returning");
		logger.debug("getEnemyEndItem3Box() - Returning: {}", enemyEndItem3Box);
		return enemyEndItem3Box;
	}

	public JLabel getEnemyEndItem4Label() {
		logger.trace("getEnemyEndItem4Label() - Entering");
		if (enemyEndItem4Label == null) {
			enemyEndItem4Label = new JLabel("Item slot 4");
		}
		logger.trace("getEnemyEndItem4Label() - Returning");
		logger.debug("getEnemyEndItem4Label() - Returning: {}",
				enemyEndItem4Label);
		return enemyEndItem4Label;
	}

	public JSpinner getEnemyEndItem4AmountSpinner() {
		logger.trace("getEnemyEndItem4AmountSpinner() - Entering");
		if (enemyEndItem4AmountSpinner == null) {
			enemyEndItem4AmountSpinner = new JSpinner();
			enemyEndItem4AmountSpinner.setModel(new SpinnerNumberModel(0, 0, 9,
					1));
		}
		logger.trace("getEnemyEndItem4AmountSpinner() - Returning");
		logger.debug("getEnemyEndItem4AmountSpinner() - Returning: {}",
				enemyEndItem4AmountSpinner);
		return enemyEndItem4AmountSpinner;
	}

	public JLabel getEnemyEndItem4xLabel() {
		logger.trace("getEnemyEndItem4xLabel() - Entering");
		if (enemyEndItem4xLabel == null) {
			enemyEndItem4xLabel = new JLabel("x");
		}
		logger.trace("getEnemyEndItem4xLabel() - Returning");
		logger.debug("getEnemyEndItem4xLabel() - Returning: {}",
				enemyEndItem4xLabel);
		return enemyEndItem4xLabel;
	}

	public ItemComboBox getEnemyEndItem4Box() {
		logger.trace("getEnemyEndItem4Box() - Entering");
		if (enemyEndItem4Box == null) {
			enemyEndItem4Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getEnemyEndItem4Box() - Returning");
		logger.debug("getEnemyEndItem4Box() - Returning: {}", enemyEndItem4Box);
		return enemyEndItem4Box;
	}

	public JLabel getEnemyEndItem5Label() {
		logger.trace("getEnemyEndItem5Label() - Entering");
		if (enemyEndItem5Label == null) {
			enemyEndItem5Label = new JLabel("Item slot 5");
		}
		logger.trace("getEnemyEndItem5Label() - Returning");
		logger.debug("getEnemyEndItem5Label() - Returning: {}",
				enemyEndItem5Label);
		return enemyEndItem5Label;
	}

	public JSpinner getEnemyEndItem5AmountSpinner() {
		logger.trace("getEnemyEndItem5AmountSpinner() - Entering");
		if (enemyEndItem5AmountSpinner == null) {
			enemyEndItem5AmountSpinner = new JSpinner();
			enemyEndItem5AmountSpinner.setModel(new SpinnerNumberModel(0, 0, 9,
					1));
		}
		logger.trace("getEnemyEndItem5AmountSpinner() - Returning");
		logger.debug("getEnemyEndItem5AmountSpinner() - Returning: {}",
				enemyEndItem5AmountSpinner);
		return enemyEndItem5AmountSpinner;
	}

	public JLabel getEnemyEndItem5xLabel() {
		logger.trace("getEnemyEndItem5xLabel() - Entering");
		if (enemyEndItem5xLabel == null) {
			enemyEndItem5xLabel = new JLabel("x");
		}
		logger.trace("getEnemyEndItem5xLabel() - Returning");
		logger.debug("getEnemyEndItem5xLabel() - Returning: {}",
				enemyEndItem5xLabel);
		return enemyEndItem5xLabel;
	}

	public ItemComboBox getEnemyEndItem5Box() {
		logger.trace("getEnemyEndItem5Box() - Entering");
		if (enemyEndItem5Box == null) {
			enemyEndItem5Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getEnemyEndItem5Box() - Returning");
		logger.debug("getEnemyEndItem5Box() - Returning: {}", enemyEndItem5Box);
		return enemyEndItem5Box;
	}

	public JLabel getEnemyEndItem6Label() {
		logger.trace("getEnemyEndItem6Label() - Entering");
		if (enemyEndItem6Label == null) {
			enemyEndItem6Label = new JLabel("Item slot 6");
		}
		logger.trace("getEnemyEndItem6Label() - Returning");
		logger.debug("getEnemyEndItem6Label() - Returning: {}",
				enemyEndItem6Label);
		return enemyEndItem6Label;
	}

	public JSpinner getEnemyEndItem6AmountSpinner() {
		logger.trace("getEnemyEndItem6AmountSpinner() - Entering");
		if (enemyEndItem6AmountSpinner == null) {
			enemyEndItem6AmountSpinner = new JSpinner();
			enemyEndItem6AmountSpinner.setModel(new SpinnerNumberModel(0, 0, 9,
					1));
		}
		logger.trace("getEnemyEndItem6AmountSpinner() - Returning");
		logger.debug("getEnemyEndItem6AmountSpinner() - Returning: {}",
				enemyEndItem6AmountSpinner);
		return enemyEndItem6AmountSpinner;
	}

	public JLabel getEnemyEndItem6xLabel() {
		logger.trace("getEnemyEndItem6xLabel() - Entering");
		if (enemyEndItem6xLabel == null) {
			enemyEndItem6xLabel = new JLabel("x");
		}
		logger.trace("getEnemyEndItem6xLabel() - Returning");
		logger.debug("getEnemyEndItem6xLabel() - Returning: {}",
				enemyEndItem6xLabel);
		return enemyEndItem6xLabel;
	}

	public ItemComboBox getEnemyEndItem6Box() {
		logger.trace("getEnemyEndItem6Box() - Entering");
		if (enemyEndItem6Box == null) {
			enemyEndItem6Box = new ItemComboBox(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
		}
		logger.trace("getEnemyEndItem6Box() - Returning");
		logger.debug("getEnemyEndItem6Box() - Returning: {}", enemyEndItem6Box);
		return enemyEndItem6Box;
	}

	public JPanel getEnemySpellsPanel() {
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

	public JLabel getEnemySpell1Label() {
		logger.trace("getEnemySpell1Label() - Entering");
		if (enemySpell1Label == null) {
			enemySpell1Label = new JLabel("Summoner Spell 1");
		}
		logger.trace("getEnemySpell1Label() - Returning");
		logger.debug("getEnemySpell1Label() - Returning: {}", enemySpell1Label);
		return enemySpell1Label;
	}

	public SummonerSpellComboBox getEnemySpell1Box() {
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

	public JLabel getEnemySpell2Label() {
		logger.trace("getEnemySpell2Label() - Entering");
		if (enemySpell2Label == null) {
			enemySpell2Label = new JLabel("Summoner Spell 2");
		}
		logger.trace("getEnemySpell2Label() - Returning");
		logger.debug("getEnemySpell2Label() - Returning: {}", enemySpell2Label);
		return enemySpell2Label;
	}

	public SummonerSpellComboBox getEnemySpell2Box() {
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

	public JPanel getMatchupResultPanel() {
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

	public JLabel getMatchupResultLabel() {
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

	public JPanel getDifficultyPanel() {
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

	public JLabel getDifficultyLabel() {
		logger.trace("getDifficultyLabel() - Entering");
		if (difficultyLabel == null) {
			difficultyLabel = new JLabel("Matchup diffculty:");
		}
		logger.trace("getDifficultyLabel() - Returning");
		logger.debug("getDifficultyLabel() - Returning: {}", difficultyLabel);
		return difficultyLabel;
	}

	public JComboBox<MatchupDifficulty> getMatchupDifficultyBox() {
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

	public JPanel getLanePanel() {
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

	public JLabel getLaneLabel() {
		logger.trace("getLaneLabel() - Entering");
		if (laneLabel == null) {
			laneLabel = new JLabel("Lane:");
		}
		logger.trace("getLaneLabel() - Returning");
		logger.debug("getLaneLabel() - Returning: {}", laneLabel);
		return laneLabel;
	}

	public JComboBox<Role> getLaneBox() {
		logger.trace("getLaneBox() - Entering");
		if (laneBox == null) {
			laneBox = new JComboBox<Role>(Role.values());
		}
		logger.trace("getLaneBox() - Returning");
		logger.debug("getLaneBox() - Returning: {}", laneBox);
		return laneBox;
	}

	public JPanel getMatchupNotesPanel() {
		logger.trace("getMatchupNotesPanel() - Entering");
		if (matchupNotesPanel == null) {
			matchupNotesPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			matchupNotesPanel.add(getMatchupNotesLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			constraints.weighty = 1;
			constraints.insets = new Insets(0, 10, 5, 10);
			matchupNotesPanel.add(getMatchupNotesScrollPane(), constraints);
		}
		logger.trace("getMatchupNotesPanel() - Returning");
		logger.debug("getMatchupNotesPanel() - Returning: {}",
				matchupNotesPanel);
		return matchupNotesPanel;
	}

	public JLabel getMatchupNotesLabel() {
		logger.trace("getMatchupNotesLabel() - Entering");
		if (matchupNotesLabel == null) {
			matchupNotesLabel = new JLabel("Matchup notes:");
		}
		logger.trace("getMatchupNotesLabel() - Returning");
		logger.debug("getMatchupNotesLabel() - Returning: {}",
				matchupNotesLabel);
		return matchupNotesLabel;
	}

	public JScrollPane getMatchupNotesScrollPane() {
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

	public JTextArea getMatchupNotesTextArea() {
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

	public JButton getGoToGameButton() {
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

	public JButton getFinishButton() {
		logger.trace("getFinishButton() - Entering");
		if (finishButton == null) {
			finishButton = new JButton("Add to list");
			finishButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					boolean ok = true;
					String error = "";
					// if (getMySpell1Box().getItemAt(
					// getMySpell1Box().getSelectedIndex()).getId() == 0) {
					// ok = false;
					// error += "- Please select your first summoner spell\n";
					// }
					// if (getMySpell2Box().getItemAt(
					// getMySpell2Box().getSelectedIndex()).getId() == 0) {
					// ok = false;
					// error += "- Please select your second summoner spell\n";
					// }
					// if (getEnemySpell1Box().getItemAt(
					// getEnemySpell1Box().getSelectedIndex()).getId() == 0) {
					// ok = false;
					// error +=
					// "- Please select your opponents first summoner spell\n";
					// }
					// if (getEnemySpell2Box().getItemAt(
					// getEnemySpell2Box().getSelectedIndex()).getId() == 0) {
					// ok = false;
					// error +=
					// "- Please select your opponents second summoner spell\n";
					// }
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
						Item i = getMyItem1Box().getItemAt(
								getMyItem1Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getMyItem1AmountSpinner().getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getMyItem1AmountSpinner().getValue()));
						}
						i = getMyItem2Box().getItemAt(
								getMyItem2Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getMyItem2AmountSpinner().getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getMyItem2AmountSpinner().getValue()));
						}
						i = getMyItem3Box().getItemAt(
								getMyItem3Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getMyItem3AmountSpinner().getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getMyItem3AmountSpinner().getValue()));
						}
						i = getMyItem4Box().getItemAt(
								getMyItem4Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getMyItem4AmountSpinner().getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getMyItem4AmountSpinner().getValue()));
						}
						i = getMyItem5Box().getItemAt(
								getMyItem5Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getMyItem5AmountSpinner().getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getMyItem5AmountSpinner().getValue()));
						}
						i = getMyItem6Box().getItemAt(
								getMyItem6Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getMyItem6AmountSpinner().getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getMyItem6AmountSpinner().getValue()));
						}
						matchup.setMyStartItems(mis);

						mis = new ArrayList<MatchupItem>();
						i = getMyEndItem1Box().getItemAt(
								getMyEndItem1Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getMyEndItem1AmountSpinner()
										.getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getMyEndItem1AmountSpinner()
											.getValue()));
						}
						i = getMyEndItem2Box().getItemAt(
								getMyEndItem2Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getMyEndItem2AmountSpinner()
										.getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getMyEndItem2AmountSpinner()
											.getValue()));
						}
						i = getMyEndItem3Box().getItemAt(
								getMyEndItem3Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getMyEndItem3AmountSpinner()
										.getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getMyEndItem3AmountSpinner()
											.getValue()));
						}
						i = getMyEndItem4Box().getItemAt(
								getMyEndItem4Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getMyEndItem4AmountSpinner()
										.getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getMyEndItem4AmountSpinner()
											.getValue()));
						}
						i = getMyEndItem5Box().getItemAt(
								getMyEndItem5Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getMyEndItem5AmountSpinner()
										.getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getMyEndItem5AmountSpinner()
											.getValue()));
						}
						i = getMyEndItem6Box().getItemAt(
								getMyEndItem6Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getMyEndItem6AmountSpinner()
										.getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getMyEndItem6AmountSpinner()
											.getValue()));
						}
						matchup.setMyEndItems(mis);

						mis = new ArrayList<MatchupItem>();
						i = getEnemyItem1Box().getItemAt(
								getEnemyItem1Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getEnemyItem1AmountSpinner()
										.getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getEnemyItem1AmountSpinner()
											.getValue()));
						}
						i = getEnemyItem2Box().getItemAt(
								getEnemyItem2Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getEnemyItem2AmountSpinner()
										.getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getEnemyItem2AmountSpinner()
											.getValue()));
						}
						i = getEnemyItem3Box().getItemAt(
								getEnemyItem3Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getEnemyItem3AmountSpinner()
										.getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getEnemyItem3AmountSpinner()
											.getValue()));
						}
						i = getEnemyItem4Box().getItemAt(
								getEnemyItem4Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getEnemyItem4AmountSpinner()
										.getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getEnemyItem4AmountSpinner()
											.getValue()));
						}
						i = getEnemyItem5Box().getItemAt(
								getEnemyItem5Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getEnemyItem5AmountSpinner()
										.getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getEnemyItem5AmountSpinner()
											.getValue()));
						}
						i = getEnemyItem6Box().getItemAt(
								getEnemyItem6Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getEnemyItem6AmountSpinner()
										.getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getEnemyItem6AmountSpinner()
											.getValue()));
						}
						matchup.setEnemyStartItems(mis);

						mis = new ArrayList<MatchupItem>();
						i = getEnemyEndItem1Box().getItemAt(
								getEnemyEndItem1Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getEnemyEndItem1AmountSpinner()
										.getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getEnemyEndItem1AmountSpinner()
											.getValue()));
						}
						i = getEnemyEndItem2Box().getItemAt(
								getEnemyEndItem2Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getEnemyEndItem2AmountSpinner()
										.getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getEnemyEndItem2AmountSpinner()
											.getValue()));
						}
						i = getEnemyEndItem3Box().getItemAt(
								getEnemyEndItem3Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getEnemyEndItem3AmountSpinner()
										.getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getEnemyEndItem3AmountSpinner()
											.getValue()));
						}
						i = getEnemyEndItem4Box().getItemAt(
								getEnemyEndItem4Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getEnemyEndItem4AmountSpinner()
										.getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getEnemyEndItem4AmountSpinner()
											.getValue()));
						}
						i = getEnemyEndItem5Box().getItemAt(
								getEnemyEndItem5Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getEnemyEndItem5AmountSpinner()
										.getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getEnemyEndItem5AmountSpinner()
											.getValue()));
						}
						i = getEnemyEndItem6Box().getItemAt(
								getEnemyEndItem6Box().getSelectedIndex());
						if (i.getId() != 0
								&& (int) getEnemyEndItem6AmountSpinner()
										.getValue() != 0) {
							mis.add(new MatchupItem(i,
									(int) getEnemyEndItem6AmountSpinner()
											.getValue()));
						}
						matchup.setEnemyEndItems(mis);
						matchup.setMySpell1(getMySpell1Box().getItemAt(
								getMySpell1Box().getSelectedIndex()));
						matchup.setMySpell2(getMySpell2Box().getItemAt(
								getMySpell2Box().getSelectedIndex()));
						matchup.setEnemySpell1(getEnemySpell1Box().getItemAt(
								getEnemySpell1Box().getSelectedIndex()));
						matchup.setEnemySpell2(getEnemySpell2Box().getItemAt(
								getEnemySpell2Box().getSelectedIndex()));
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
		allChampions = new ArrayList<Champion>();
		Champion c = new Champion(0, "no champion", null);
		allChampions.add(c);
		for (Champion champ : ChampionUtil.getAllChampions()) {
			allChampions.add(champ);
		}
		allChampionsArray = allChampions.toArray(new Champion[] {});
		allItems = new ArrayList<Item>();
		Item i = new Item(0, "no item", null);
		allItems.add(i);
		for (Item item : ItemUtil.getAllItems()) {
			allItems.add(item);
		}
		allSpells = new ArrayList<SummonerSpell>();
		SummonerSpell s = new SummonerSpell(0, "no spell", null);
		allSpells.add(s);
		allSpells.addAll(SummonerSpellUtil.getAllSpells());
		this.setTitle("New Game");
		this.setModal(true);
		this.setResizable(false);
		this.setContentPane(getNewEntryContentPane());
		this.pack();
		this.setLocationRelativeTo(MainView.getInstance());
		logger.trace("createGUI() - Leaving");
	}

	private void clearFields() {
		toEdit = null;
		isImport = false;
		getNewEntryContentPane().setSelectedIndex(0);
		getDateChooser().setDate(null);
		getLengthMinutesSpinner().setValue(0);
		getLengthSecondsSpinner().setValue(0);
		getTeam1Champ1Box().setSelectedIndex(0);
		getTeam1Champ2Box().setSelectedIndex(0);
		getTeam1Champ3Box().setSelectedIndex(0);
		getTeam1Champ4Box().setSelectedIndex(0);
		getTeam1Champ5Box().setSelectedIndex(0);
		getTeam2Champ1Box().setSelectedIndex(0);
		getTeam2Champ2Box().setSelectedIndex(0);
		getTeam2Champ3Box().setSelectedIndex(0);
		getTeam2Champ4Box().setSelectedIndex(0);
		getTeam2Champ5Box().setSelectedIndex(0);
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
		getMyItem1AmountSpinner().setValue(0);
		getMyItem1Box().setSelectedIndex(0);
		getMyItem2AmountSpinner().setValue(0);
		getMyItem2Box().setSelectedIndex(0);
		getMyItem3AmountSpinner().setValue(0);
		getMyItem3Box().setSelectedIndex(0);
		getMyItem4AmountSpinner().setValue(0);
		getMyItem4Box().setSelectedIndex(0);
		getMyItem5AmountSpinner().setValue(0);
		getMyItem5Box().setSelectedIndex(0);
		getMyItem6AmountSpinner().setValue(0);
		getMyItem6Box().setSelectedIndex(0);
		getMyItem1AmountSpinner().setValue(0);
		getMyEndItem1Box().setSelectedIndex(0);
		getMyEndItem2AmountSpinner().setValue(0);
		getMyEndItem2Box().setSelectedIndex(0);
		getMyEndItem3AmountSpinner().setValue(0);
		getMyEndItem3Box().setSelectedIndex(0);
		getMyEndItem4AmountSpinner().setValue(0);
		getMyEndItem4Box().setSelectedIndex(0);
		getMyEndItem5AmountSpinner().setValue(0);
		getMyEndItem5Box().setSelectedIndex(0);
		getMyEndItem6AmountSpinner().setValue(0);
		getMyEndItem6Box().setSelectedIndex(0);
		getEnemyItem1AmountSpinner().setValue(0);
		getEnemyItem1Box().setSelectedIndex(0);
		getEnemyItem2AmountSpinner().setValue(0);
		getEnemyItem2Box().setSelectedIndex(0);
		getEnemyItem3AmountSpinner().setValue(0);
		getEnemyItem3Box().setSelectedIndex(0);
		getEnemyItem4AmountSpinner().setValue(0);
		getEnemyItem4Box().setSelectedIndex(0);
		getEnemyItem5AmountSpinner().setValue(0);
		getEnemyItem5Box().setSelectedIndex(0);
		getEnemyItem6AmountSpinner().setValue(0);
		getEnemyItem6Box().setSelectedIndex(0);
		getEnemyItem1AmountSpinner().setValue(0);
		getEnemyEndItem1Box().setSelectedIndex(0);
		getEnemyEndItem2AmountSpinner().setValue(0);
		getEnemyEndItem2Box().setSelectedIndex(0);
		getEnemyEndItem3AmountSpinner().setValue(0);
		getEnemyEndItem3Box().setSelectedIndex(0);
		getEnemyEndItem4AmountSpinner().setValue(0);
		getEnemyEndItem4Box().setSelectedIndex(0);
		getEnemyEndItem5AmountSpinner().setValue(0);
		getEnemyEndItem5Box().setSelectedIndex(0);
		getEnemyEndItem6AmountSpinner().setValue(0);
		getEnemyEndItem6Box().setSelectedIndex(0);
		getMySpell1Box().setSelectedIndex(0);
		getMySpell2Box().setSelectedIndex(0);
		getEnemySpell1Box().setSelectedIndex(0);
		getEnemySpell2Box().setSelectedIndex(0);
		getMatchupNotesTextArea().setText("");
	}

	private void fillFields() {
		logger.trace("fillFields() - Entering");
		getDateChooser().setDate(toEdit.getDate());
		long lengthFull = toEdit.getLenght();
		int lengthMin = (int) lengthFull / 60;
		int lengthSec = (int) lengthFull - lengthMin * 60;
		getLengthMinutesSpinner().setValue(lengthMin);
		getLengthSecondsSpinner().setValue(lengthSec);
		getTeam1Champ1Box().setSelectedItem(toEdit.getMyTeam().get(0));
		getTeam1Champ2Box().setSelectedItem(toEdit.getMyTeam().get(1));
		getTeam1Champ3Box().setSelectedItem(toEdit.getMyTeam().get(2));
		getTeam1Champ4Box().setSelectedItem(toEdit.getMyTeam().get(3));
		getTeam1Champ5Box().setSelectedItem(toEdit.getMyTeam().get(4));
		getTeam2Champ1Box().setSelectedItem(toEdit.getEnemyTeam().get(0));
		getTeam2Champ2Box().setSelectedItem(toEdit.getEnemyTeam().get(1));
		getTeam2Champ3Box().setSelectedItem(toEdit.getEnemyTeam().get(2));
		getTeam2Champ4Box().setSelectedItem(toEdit.getEnemyTeam().get(3));
		getTeam2Champ5Box().setSelectedItem(toEdit.getEnemyTeam().get(4));
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
		if (m.getMyStartItems().size() >= 1) {
			getMyItem1AmountSpinner().setValue(
					m.getMyStartItems().get(0).getAmount());
			getMyItem1Box().setSelectedItem(
					m.getMyStartItems().get(0).getItem());
			if (m.getMyStartItems().size() >= 2) {
				getMyItem2AmountSpinner().setValue(
						m.getMyStartItems().get(1).getAmount());
				getMyItem2Box().setSelectedItem(
						m.getMyStartItems().get(1).getItem());
				if (m.getMyStartItems().size() >= 3) {
					getMyItem3AmountSpinner().setValue(
							m.getMyStartItems().get(2).getAmount());
					getMyItem3Box().setSelectedItem(
							m.getMyStartItems().get(2).getItem());
					if (m.getMyStartItems().size() >= 4) {
						getMyItem4AmountSpinner().setValue(
								m.getMyStartItems().get(3).getAmount());
						getMyItem4Box().setSelectedItem(
								m.getMyStartItems().get(3).getItem());
						if (m.getMyStartItems().size() >= 5) {
							getMyItem5AmountSpinner().setValue(
									m.getMyStartItems().get(4).getAmount());
							getMyItem5Box().setSelectedItem(
									m.getMyStartItems().get(4).getItem());
							if (m.getMyStartItems().size() >= 5) {
								getMyItem6AmountSpinner().setValue(
										m.getMyStartItems().get(5).getAmount());
								getMyItem6Box().setSelectedItem(
										m.getMyStartItems().get(5).getItem());
							}
						}
					}
				}
			}
		}
		if (m.getMyEndItems().size() >= 1) {
			getMyEndItem1AmountSpinner().setValue(
					m.getMyEndItems().get(0).getAmount());
			getMyEndItem1Box().setSelectedItem(
					m.getMyEndItems().get(0).getItem());
			if (m.getMyEndItems().size() >= 2) {
				getMyEndItem2AmountSpinner().setValue(
						m.getMyEndItems().get(1).getAmount());
				getMyEndItem2Box().setSelectedItem(
						m.getMyEndItems().get(1).getItem());
				if (m.getMyEndItems().size() >= 3) {
					getMyEndItem3AmountSpinner().setValue(
							m.getMyEndItems().get(2).getAmount());
					getMyEndItem3Box().setSelectedItem(
							m.getMyEndItems().get(2).getItem());
					if (m.getMyEndItems().size() >= 4) {
						getMyEndItem4AmountSpinner().setValue(
								m.getMyEndItems().get(3).getAmount());
						getMyEndItem4Box().setSelectedItem(
								m.getMyEndItems().get(3).getItem());
						if (m.getMyEndItems().size() >= 5) {
							getMyEndItem5AmountSpinner().setValue(
									m.getMyEndItems().get(4).getAmount());
							getMyEndItem5Box().setSelectedItem(
									m.getMyEndItems().get(4).getItem());
							if (m.getMyEndItems().size() >= 5) {
								getMyEndItem6AmountSpinner().setValue(
										m.getMyEndItems().get(5).getAmount());
								getMyEndItem6Box().setSelectedItem(
										m.getMyEndItems().get(5).getItem());
							}
						}
					}
				}
			}
		}
		if (m.getEnemyStartItems().size() >= 1) {
			getEnemyItem1AmountSpinner().setValue(
					m.getEnemyStartItems().get(0).getAmount());
			getEnemyItem1Box().setSelectedItem(
					m.getEnemyStartItems().get(0).getItem());
			if (m.getEnemyStartItems().size() >= 2) {
				getEnemyItem2AmountSpinner().setValue(
						m.getEnemyStartItems().get(1).getAmount());
				getEnemyItem2Box().setSelectedItem(
						m.getEnemyStartItems().get(1).getItem());
				if (m.getEnemyStartItems().size() >= 3) {
					getEnemyItem3AmountSpinner().setValue(
							m.getEnemyStartItems().get(2).getAmount());
					getEnemyItem3Box().setSelectedItem(
							m.getEnemyStartItems().get(2).getItem());
					if (m.getEnemyStartItems().size() >= 4) {
						getEnemyItem4AmountSpinner().setValue(
								m.getEnemyStartItems().get(3).getAmount());
						getEnemyItem4Box().setSelectedItem(
								m.getEnemyStartItems().get(3).getItem());
						if (m.getEnemyStartItems().size() >= 5) {
							getEnemyItem5AmountSpinner().setValue(
									m.getEnemyStartItems().get(4).getAmount());
							getEnemyItem5Box().setSelectedItem(
									m.getEnemyStartItems().get(4).getItem());
							if (m.getEnemyStartItems().size() >= 6) {
								getEnemyItem6AmountSpinner().setValue(
										m.getEnemyStartItems().get(5)
												.getAmount());
								getEnemyItem6Box()
										.setSelectedItem(
												m.getEnemyStartItems().get(5)
														.getItem());
							}
						}
					}
				}
			}
		}
		if (m.getEnemyEndItems().size() >= 1) {
			getEnemyEndItem1AmountSpinner().setValue(
					m.getEnemyEndItems().get(0).getAmount());
			getEnemyEndItem1Box().setSelectedItem(
					m.getEnemyEndItems().get(0).getItem());
			if (m.getEnemyEndItems().size() >= 2) {
				getEnemyEndItem2AmountSpinner().setValue(
						m.getEnemyEndItems().get(1).getAmount());
				getEnemyEndItem2Box().setSelectedItem(
						m.getEnemyEndItems().get(1).getItem());
				if (m.getEnemyEndItems().size() >= 3) {
					getEnemyEndItem3AmountSpinner().setValue(
							m.getEnemyEndItems().get(2).getAmount());
					getEnemyEndItem3Box().setSelectedItem(
							m.getEnemyEndItems().get(2).getItem());
					if (m.getEnemyEndItems().size() >= 4) {
						getEnemyEndItem4AmountSpinner().setValue(
								m.getEnemyEndItems().get(3).getAmount());
						getEnemyEndItem4Box().setSelectedItem(
								m.getEnemyEndItems().get(3).getItem());
						if (m.getEnemyEndItems().size() >= 5) {
							getEnemyEndItem5AmountSpinner().setValue(
									m.getEnemyEndItems().get(4).getAmount());
							getEnemyEndItem5Box().setSelectedItem(
									m.getEnemyEndItems().get(4).getItem());
							if (m.getEnemyEndItems().size() >= 6) {
								getEnemyEndItem6AmountSpinner()
										.setValue(
												m.getEnemyEndItems().get(5)
														.getAmount());
								getEnemyEndItem6Box().setSelectedItem(
										m.getEnemyEndItems().get(5).getItem());
							}
						}
					}
				}
			}
		}
		getMySpell1Box().setSelectedItem(m.getMySpell1());
		getMySpell2Box().setSelectedItem(m.getMySpell2());
		getEnemySpell1Box().setSelectedItem(m.getEnemySpell1());
		getEnemySpell2Box().setSelectedItem(m.getEnemySpell2());
		getMatchupNotesTextArea().setText(m.getNotes());
		logger.trace("fillFields() - Leaving");
	}

	public static synchronized NewEntryDialoge getInstance() {
		logger.trace("getInstance() - Entering");
		logger.debug("getInstance() - Instance is {}", instance);
		if (instance == null) {
			logger.debug("Creating a new instance of NewEntryDialoge");
			instance = new NewEntryDialoge();
		}
		logger.trace("getInstance() - Returning");
		logger.debug("getInstance() - Returning {}", instance);
		return instance;
	}
}