package de.phyberapex.diaryoflegends.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;
import de.phyberapex.diaryoflegends.model.util.ItemUtil;

public class NewEntryDialoge extends JDialog implements Runnable {

	private static final long serialVersionUID = 2162451784302955479L;
	private List<Champion> allChampions = ChampionUtil.getAllChampions();
	private List<Item> allItems;
	private List<Champion> myTeamChampions;
	private List<Champion> enemyTeamChampions;
	private JTabbedPane newEntryContentPane;

	private JPanel gameContentPanel;
	private JPanel datePanel;
	private JLabel dateLabel;
	private JDateChooser dateChooser;
	private JPanel teamsPanel;
	private JPanel myTeamPanel;
	private JLabel myTeamLabel;
	private JLabel team1Champ1Label;
	private JComboBox<Champion> team1Champ1Box;
	private JLabel team1Champ2Label;
	private JComboBox<Champion> team1Champ2Box;
	private JLabel team1Champ3Label;
	private JComboBox<Champion> team1Champ3Box;
	private JLabel team1Champ4Label;
	private JComboBox<Champion> team1Champ4Box;
	private JLabel team1Champ5Label;
	private JComboBox<Champion> team1Champ5Box;
	private JPanel enemyTeamPanel;
	private JLabel enemyTeamLabel;
	private JLabel team2Champ1Label;
	private JComboBox<Champion> team2Champ1Box;
	private JLabel team2Champ2Label;
	private JComboBox<Champion> team2Champ2Box;
	private JLabel team2Champ3Label;
	private JComboBox<Champion> team2Champ3Box;
	private JLabel team2Champ4Label;
	private JComboBox<Champion> team2Champ4Box;
	private JLabel team2Champ5Label;
	private JComboBox<Champion> team2Champ5Box;
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
	private JPanel gameNotesPanel;
	private JLabel gameNotesLabel;
	private JTextArea gameNotesTextArea;
	private JButton goToMatchupButton;

	private JPanel matchupContentPanel;
	private JPanel championsPanel;
	private JPanel myChampionPanel;
	private JLabel myChampionLabel;
	private JComboBox<Champion> myChampionBox;
	private JLabel myItem1Label;
	private JSpinner myItem1AmountSpinner;
	private JLabel myItem1xLabel;
	private JComboBox<Item> myItem1Box;
	private JLabel myItem2Label;
	private JSpinner myItem2AmountSpinner;
	private JLabel myItem2xLabel;
	private JComboBox<Item> myItem2Box;
	private JLabel myItem3Label;
	private JSpinner myItem3AmountSpinner;
	private JLabel myItem3xLabel;
	private JComboBox<Item> myItem3Box;
	private JLabel myItem4Label;
	private JSpinner myItem4AmountSpinner;
	private JLabel myItem4xLabel;
	private JComboBox<Item> myItem4Box;
	private JLabel myItem5Label;
	private JSpinner myItem5AmountSpinner;
	private JLabel myItem5xLabel;
	private JComboBox<Item> myItem5Box;
	private JLabel myItem6Label;
	private JSpinner myItem6AmountSpinner;
	private JLabel myItem6xLabel;
	private JComboBox<Item> myItem6Box;
	private JPanel matchupResultPanel;
	private JLabel matchupResultLabel;
	private ButtonGroup matchupResultGroup = new ButtonGroup();
	private JLabel matchupResultWonLabel;
	private JRadioButton matchupResultWonButton;
	private JLabel matchupResultDrawLabel;
	private JRadioButton matchupResultDrawButton;
	private JLabel matchupResultLossLabel;
	private JRadioButton matchupResultLossButton;
	private JPanel enemyChampionPanel;
	private JLabel enemyChampionLabel;
	private JComboBox<Champion> enemyChampionBox;
	private JLabel enemyItem1Label;
	private JSpinner enemyItem1AmountSpinner;
	private JLabel enemyItem1xLabel;
	private JComboBox<Item> enemyItem1Box;
	private JLabel enemyItem2Label;
	private JSpinner enemyItem2AmountSpinner;
	private JLabel enemyItem2xLabel;
	private JComboBox<Item> enemyItem2Box;
	private JLabel enemyItem3Label;
	private JSpinner enemyItem3AmountSpinner;
	private JLabel enemyItem3xLabel;
	private JComboBox<Item> enemyItem3Box;
	private JLabel enemyItem4Label;
	private JSpinner enemyItem4AmountSpinner;
	private JLabel enemyItem4xLabel;
	private JComboBox<Item> enemyItem4Box;
	private JLabel enemyItem5Label;
	private JSpinner enemyItem5AmountSpinner;
	private JLabel enemyItem5xLabel;
	private JComboBox<Item> enemyItem5Box;
	private JLabel enemyItem6Label;
	private JSpinner enemyItem6AmountSpinner;
	private JLabel enemyItem6xLabel;
	private JComboBox<Item> enemyItem6Box;
	private JPanel difficultyPanel;
	private JLabel difficultyLabel;
	private JComboBox<MatchupDifficulty> matchupDifficultyBox;
	private JPanel lanePanel;
	private JLabel laneLabel;
	private JComboBox<Role> laneBox;
	private JPanel matchupNotesPanel;
	private JLabel matchupNotesLabel;
	private JTextArea matchupNotesTextArea;

	private JButton goToGameButton;
	private JButton finishButton;

	private static Logger logger = LogManager.getLogger(NewEntryDialoge.class
			.getName());

	public NewEntryDialoge() {
		super(MainView.getInstance());
		logger.trace("NewEntryDialoge() - Entering");
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
			constraints.weightx = 1;
			gameContentPanel.add(getDatePanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.insets = new Insets(5, 10, 5, 10);
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			gameContentPanel.add(getTeamsPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			constraints.weighty = 1;
			gameContentPanel.add(getGameNotesPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
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

	public JPanel getTeamsPanel() {
		logger.trace("getTeamsPanel() - Entering");
		if (teamsPanel == null) {
			teamsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridheight = 2;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.5;
			teamsPanel.add(getMyTeamPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.insets = new Insets(25, 0, 0, 0);
			teamsPanel.add(getGameResultPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.anchor = GridBagConstraints.SOUTH;
			constraints.insets = new Insets(0, 10, 0, 10);
			teamsPanel.add(getGameStatsPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 0;
			constraints.gridheight = 2;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.5;
			teamsPanel.add(getEnemyTeamPanel(), constraints);
		}
		logger.trace("getTeamsPanel() - Returning");
		logger.debug("getTeamsPanel() - Returning: {}", teamsPanel);
		return teamsPanel;
	}

	public JPanel getMyTeamPanel() {
		logger.trace("getMyTeamPanel() - Entering");
		if (myTeamPanel == null) {
			myTeamPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 2;
			myTeamPanel.add(getMyTeamLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			myTeamPanel.add(getTeam1Champ1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myTeamPanel.add(getTeam1Champ1Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			myTeamPanel.add(getTeam1Champ2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myTeamPanel.add(getTeam1Champ2Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			myTeamPanel.add(getTeam1Champ3Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 3;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myTeamPanel.add(getTeam1Champ3Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 4;
			myTeamPanel.add(getTeam1Champ4Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 4;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myTeamPanel.add(getTeam1Champ4Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 5;
			myTeamPanel.add(getTeam1Champ5Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 5;
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

	public JComboBox<Champion> getTeam1Champ1Box() {
		logger.trace("getTeam1Champ1Box() - Entering");
		if (team1Champ1Box == null) {
			team1Champ1Box = new JComboBox<Champion>(
					new DefaultComboBoxModel<Champion>(
							allChampions.toArray(new Champion[] {})));
			team1Champ1Box.setRenderer(new ChampionComboBoxRenderer());
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

	public JComboBox<Champion> getTeam1Champ2Box() {
		logger.trace("getTeam1Champ2Box() - Entering");
		if (team1Champ2Box == null) {
			team1Champ2Box = new JComboBox<Champion>(
					new DefaultComboBoxModel<Champion>(
							allChampions.toArray(new Champion[] {})));
			team1Champ2Box.setRenderer(new ChampionComboBoxRenderer());
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

	public JComboBox<Champion> getTeam1Champ3Box() {
		logger.trace("getTeam1Champ3Box() - Entering");
		if (team1Champ3Box == null) {
			team1Champ3Box = new JComboBox<Champion>(
					new DefaultComboBoxModel<Champion>(
							allChampions.toArray(new Champion[] {})));
			team1Champ3Box.setRenderer(new ChampionComboBoxRenderer());
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

	public JComboBox<Champion> getTeam1Champ4Box() {
		logger.trace("getTeam1Champ4Box() - Entering");
		if (team1Champ4Box == null) {
			team1Champ4Box = new JComboBox<Champion>(
					new DefaultComboBoxModel<Champion>(
							allChampions.toArray(new Champion[] {})));
			team1Champ4Box.setRenderer(new ChampionComboBoxRenderer());
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

	public JComboBox<Champion> getTeam1Champ5Box() {
		logger.trace("getTeam1Champ5Box() - Entering");
		if (team1Champ5Box == null) {
			team1Champ5Box = new JComboBox<Champion>(
					new DefaultComboBoxModel<Champion>(
							allChampions.toArray(new Champion[] {})));
			team1Champ5Box.setRenderer(new ChampionComboBoxRenderer());
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
			constraints.gridwidth = 2;
			enemyTeamPanel.add(getEnemyTeamLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			enemyTeamPanel.add(getTeam2Champ1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyTeamPanel.add(getTeam2Champ1Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			enemyTeamPanel.add(getTeam2Champ2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyTeamPanel.add(getTeam2Champ2Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 3;
			enemyTeamPanel.add(getTeam2Champ3Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyTeamPanel.add(getTeam2Champ3Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 4;
			enemyTeamPanel.add(getTeam2Champ4Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 4;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyTeamPanel.add(getTeam2Champ4Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 5;
			enemyTeamPanel.add(getTeam2Champ5Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 5;
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

	public JComboBox<Champion> getTeam2Champ1Box() {
		logger.trace("getTeam2Champ1Box() - Entering");
		if (team2Champ1Box == null) {
			team2Champ1Box = new JComboBox<Champion>(
					new DefaultComboBoxModel<Champion>(
							allChampions.toArray(new Champion[] {})));
			team2Champ1Box.setRenderer(new ChampionComboBoxRenderer());
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

	public JComboBox<Champion> getTeam2Champ2Box() {
		logger.trace("getTeam2Champ2Box() - Entering");
		if (team2Champ2Box == null) {
			team2Champ2Box = new JComboBox<Champion>(
					new DefaultComboBoxModel<Champion>(
							allChampions.toArray(new Champion[] {})));
			team2Champ2Box.setRenderer(new ChampionComboBoxRenderer());
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

	public JComboBox<Champion> getTeam2Champ3Box() {
		logger.trace("getTeam2Champ3Box() - Entering");
		if (team2Champ3Box == null) {
			team2Champ3Box = new JComboBox<Champion>(
					new DefaultComboBoxModel<Champion>(
							allChampions.toArray(new Champion[] {})));
			team2Champ3Box.setRenderer(new ChampionComboBoxRenderer());
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

	public JComboBox<Champion> getTeam2Champ4Box() {
		logger.trace("getTeam2Champ4Box() - Entering");
		if (team2Champ4Box == null) {
			team2Champ4Box = new JComboBox<Champion>(
					new DefaultComboBoxModel<Champion>(
							allChampions.toArray(new Champion[] {})));
			team2Champ4Box.setRenderer(new ChampionComboBoxRenderer());
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

	public JComboBox<Champion> getTeam2Champ5Box() {
		logger.trace("getTeam2Champ5Box() - Entering");
		if (team2Champ5Box == null) {
			team2Champ5Box = new JComboBox<Champion>(
					new DefaultComboBoxModel<Champion>(
							allChampions.toArray(new Champion[] {})));
			team2Champ5Box.setRenderer(new ChampionComboBoxRenderer());
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
			gameNotesPanel.add(getGameNotesTextArea(), constraints);
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

	public JTextArea getGameNotesTextArea() {
		logger.trace("getGameNotesTextArea() - Entering");
		if (gameNotesTextArea == null) {
			gameNotesTextArea = new JTextArea();
			gameNotesTextArea.setBorder(BorderFactory.createBevelBorder(1));
			gameNotesTextArea.setPreferredSize(new Dimension(0, 60));
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
					if (!getGameResultLossBox().isSelected()
							&& !getGameResultWonBox().isSelected()) {
						ok = false;
						error += "- You need to set if you won or lost this game.\n";
					}
					if (getGameNotesTextArea().getText().contains("§")) {
						ok = false;
						error += "- Your notes can't contain the character '§'. This is due to exporting.\n";
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
			constraints.gridy = 0;
			constraints.insets = new Insets(15, 15, 10, 5);
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.5;
			matchupContentPanel.add(getLanePanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.insets = new Insets(15, 5, 10, 15);
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.5;
			matchupContentPanel.add(getDifficultyPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.gridwidth = 2;
			matchupContentPanel.add(getChampionsPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			constraints.weighty = 1;
			constraints.gridwidth = 2;
			matchupContentPanel.add(getMatchupNotesPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 4;
			matchupContentPanel.add(getGoToGameButton(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 4;
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
			constraints.gridy = 0;
			constraints.insets = new Insets(5, 15, 0, 10);
			championsPanel.add(getMyChampionPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.insets = new Insets(5, 0, 0, 5);
			championsPanel.add(getMatchupResultPanel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 0;
			constraints.insets = new Insets(5, 10, 0, 15);
			championsPanel.add(getEnemyChampionPanel(), constraints);
		}
		logger.trace("getChampionsPanel() - Returning");
		logger.debug("getChampionsPanel() - Returning: {}", championsPanel);
		return championsPanel;
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
			constraints.gridwidth = 4;
			myChampionPanel.add(getMyChampionLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.gridwidth = 4;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			myChampionPanel.add(getMyChampionBox(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			myChampionPanel.add(getMyItem1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			myChampionPanel.add(getMyItem1AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 2;
			myChampionPanel.add(getMyItem1xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 2;
			myChampionPanel.add(getMyItem1Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			myChampionPanel.add(getMyItem2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 3;
			myChampionPanel.add(getMyItem2AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 3;
			myChampionPanel.add(getMyItem2xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 3;
			myChampionPanel.add(getMyItem2Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 4;
			myChampionPanel.add(getMyItem3Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 4;
			myChampionPanel.add(getMyItem3AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 4;
			myChampionPanel.add(getMyItem3xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 4;
			myChampionPanel.add(getMyItem3Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 5;
			myChampionPanel.add(getMyItem4Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 5;
			myChampionPanel.add(getMyItem4AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 5;
			myChampionPanel.add(getMyItem4xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 5;
			myChampionPanel.add(getMyItem4Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 6;
			myChampionPanel.add(getMyItem5Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 6;
			myChampionPanel.add(getMyItem5AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 6;
			myChampionPanel.add(getMyItem5xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 6;
			myChampionPanel.add(getMyItem5Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 7;
			myChampionPanel.add(getMyItem6Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 7;
			myChampionPanel.add(getMyItem6AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 7;
			myChampionPanel.add(getMyItem6xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 7;
			myChampionPanel.add(getMyItem6Box(), constraints);
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

	public JComboBox<Champion> getMyChampionBox() {
		logger.trace("getMyChampionBox() - Entering");
		if (myChampionBox == null) {
			myChampionBox = new JComboBox<Champion>();
			myChampionBox.setRenderer(new ChampionComboBoxRenderer());
		}
		logger.trace("getMyChampionBox() - Returning");
		logger.debug("getMyChampionBox() - Returning: {}", myChampionBox);
		return myChampionBox;
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

	public JComboBox<Item> getMyItem1Box() {
		logger.trace("getMyItem1Box() - Entering");
		if (myItem1Box == null) {
			myItem1Box = new JComboBox<Item>(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
			myItem1Box.setRenderer(new ItemComboBoxRenderer());
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

	public JComboBox<Item> getMyItem2Box() {
		logger.trace("getMyItem2Box() - Entering");
		if (myItem2Box == null) {
			myItem2Box = new JComboBox<Item>(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
			myItem2Box.setRenderer(new ItemComboBoxRenderer());
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

	public JComboBox<Item> getMyItem3Box() {
		logger.trace("getMyItem3Box() - Entering");
		if (myItem3Box == null) {
			myItem3Box = new JComboBox<Item>(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
			myItem3Box.setRenderer(new ItemComboBoxRenderer());
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

	public JComboBox<Item> getMyItem4Box() {
		logger.trace("getMyItem4Box() - Entering");
		if (myItem4Box == null) {
			myItem4Box = new JComboBox<Item>(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
			myItem4Box.setRenderer(new ItemComboBoxRenderer());
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

	public JComboBox<Item> getMyItem5Box() {
		logger.trace("getMyItem5Box() - Entering");
		if (myItem5Box == null) {
			myItem5Box = new JComboBox<Item>(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
			myItem5Box.setRenderer(new ItemComboBoxRenderer());
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

	public JComboBox<Item> getMyItem6Box() {
		logger.trace("getMyItem6Box() - Entering");
		if (myItem6Box == null) {
			myItem6Box = new JComboBox<Item>(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
			myItem6Box.setRenderer(new ItemComboBoxRenderer());
		}
		logger.trace("getMyItem6Box() - Returning");
		logger.debug("getMyItem6Box() - Returning: {}", myItem6Box);
		return myItem6Box;
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
			constraints.gridwidth = 4;
			enemyChampionPanel.add(getEnemyChampionLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.gridwidth = 4;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			enemyChampionPanel.add(getEnemyChampionBox(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			enemyChampionPanel.add(getEnemyItem1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			enemyChampionPanel.add(getEnemyItem1AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 2;
			enemyChampionPanel.add(getEnemyItem1xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 2;
			enemyChampionPanel.add(getEnemyItem1Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			enemyChampionPanel.add(getEnemyItem2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 3;
			enemyChampionPanel.add(getEnemyItem2AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 3;
			enemyChampionPanel.add(getEnemyItem2xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 3;
			enemyChampionPanel.add(getEnemyItem2Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 4;
			enemyChampionPanel.add(getEnemyItem3Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 4;
			enemyChampionPanel.add(getEnemyItem3AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 4;
			enemyChampionPanel.add(getEnemyItem3xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 4;
			enemyChampionPanel.add(getEnemyItem3Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 5;
			enemyChampionPanel.add(getEnemyItem4Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 5;
			enemyChampionPanel.add(getEnemyItem4AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 5;
			enemyChampionPanel.add(getEnemyItem4xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 5;
			enemyChampionPanel.add(getEnemyItem4Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 6;
			enemyChampionPanel.add(getEnemyItem5Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 6;
			enemyChampionPanel.add(getEnemyItem5AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 6;
			enemyChampionPanel.add(getEnemyItem5xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 6;
			enemyChampionPanel.add(getEnemyItem5Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 7;
			enemyChampionPanel.add(getEnemyItem6Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 7;
			enemyChampionPanel.add(getEnemyItem6AmountSpinner(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 2;
			constraints.gridy = 7;
			enemyChampionPanel.add(getEnemyItem6xLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 3;
			constraints.gridy = 7;
			enemyChampionPanel.add(getEnemyItem6Box(), constraints);
		}
		logger.trace("getEnemyChampionPanel() - Returning");
		logger.debug("getEnemyChampionPanel() - Returning: {}",
				enemyChampionPanel);
		return enemyChampionPanel;
	}

	public JLabel getEnemyChampionLabel() {
		logger.trace("getEnemyChampionLabel() - Entering");
		if (enemyChampionLabel == null) {
			enemyChampionLabel = new JLabel("Lane opponent");
		}
		logger.trace("getEnemyChampionLabel() - Returning");
		logger.debug("getEnemyChampionLabel() - Returning: {}",
				enemyChampionLabel);
		return enemyChampionLabel;
	}

	public JComboBox<Champion> getEnemyChampionBox() {
		logger.trace("getEnemyChampionBox() - Entering");
		if (enemyChampionBox == null) {
			enemyChampionBox = new JComboBox<Champion>();
			enemyChampionBox.setRenderer(new ChampionComboBoxRenderer());
		}
		logger.trace("getEnemyChampionBox() - Returning");
		logger.debug("getEnemyChampionBox() - Returning: {}", enemyChampionBox);
		return enemyChampionBox;
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

	public JComboBox<Item> getEnemyItem1Box() {
		logger.trace("getEnemyItem1Box() - Entering");
		if (enemyItem1Box == null) {
			enemyItem1Box = new JComboBox<Item>(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
			enemyItem1Box.setRenderer(new ItemComboBoxRenderer());
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

	public JComboBox<Item> getEnemyItem2Box() {
		logger.trace("getEnemyItem2Box() - Entering");
		if (enemyItem2Box == null) {
			enemyItem2Box = new JComboBox<Item>(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
			enemyItem2Box.setRenderer(new ItemComboBoxRenderer());
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

	public JComboBox<Item> getEnemyItem3Box() {
		logger.trace("getEnemyItem3Box() - Entering");
		if (enemyItem3Box == null) {
			enemyItem3Box = new JComboBox<Item>(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
			enemyItem3Box.setRenderer(new ItemComboBoxRenderer());
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

	public JComboBox<Item> getEnemyItem4Box() {
		logger.trace("getEnemyItem4Box() - Entering");
		if (enemyItem4Box == null) {
			enemyItem4Box = new JComboBox<Item>(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
			enemyItem4Box.setRenderer(new ItemComboBoxRenderer());
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

	public JComboBox<Item> getEnemyItem5Box() {
		logger.trace("getEnemyItem5Box() - Entering");
		if (enemyItem5Box == null) {
			enemyItem5Box = new JComboBox<Item>(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
			enemyItem5Box.setRenderer(new ItemComboBoxRenderer());
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

	public JComboBox<Item> getEnemyItem6Box() {
		logger.trace("getEnemyItem6Box() - Entering");
		if (enemyItem6Box == null) {
			enemyItem6Box = new JComboBox<Item>(new DefaultComboBoxModel<Item>(
					allItems.toArray(new Item[] {})));
			enemyItem6Box.setRenderer(new ItemComboBoxRenderer());
		}
		logger.trace("getEnemyItem6Box() - Returning");
		logger.debug("getEnemyItem6Box() - Returning: {}", enemyItem6Box);
		return enemyItem6Box;
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
			matchupResultPanel.add(getMatchupResultWonLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			matchupResultPanel.add(getMatchupResultWonButton(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			matchupResultPanel.add(getMatchupResultDrawLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			matchupResultPanel.add(getMatchupResultDrawButton(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			matchupResultPanel.add(getMatchupResultLossLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 3;
			matchupResultPanel.add(getMatchupResultLossButton(), constraints);
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

	public JLabel getMatchupResultWonLabel() {
		logger.trace("getMatchupResultWonLabel() - Entering");
		if (matchupResultWonLabel == null) {
			matchupResultWonLabel = new JLabel(MatchupResult.WIN.toString());
		}
		logger.trace("getMatchupResultWonLabel() - Returning");
		logger.debug("getMatchupResultWonLabel() - Returning: {}",
				matchupResultWonLabel);
		return matchupResultWonLabel;
	}

	public JRadioButton getMatchupResultWonButton() {
		logger.trace("getMatchupResultWonButton() - Entering");
		if (matchupResultWonButton == null) {
			matchupResultWonButton = new JRadioButton();
			matchupResultGroup.add(matchupResultWonButton);
		}
		logger.trace("getMatchupResultWonButton() - Returning");
		logger.debug("getMatchupResultWonButton() - Returning: {}",
				matchupResultWonButton);
		return matchupResultWonButton;
	}

	public JLabel getMatchupResultDrawLabel() {
		logger.trace("getMatchupResultDrawLabel() - Entering");
		if (matchupResultDrawLabel == null) {
			matchupResultDrawLabel = new JLabel(MatchupResult.DRAW.toString());
		}
		logger.trace("getMatchupResultDrawLabel() - Returning");
		logger.debug("getMatchupResultDrawLabel() - Returning: {}",
				matchupResultDrawLabel);
		return matchupResultDrawLabel;
	}

	public JRadioButton getMatchupResultDrawButton() {
		logger.trace("getMatchupResultDrawButton() - Entering");
		if (matchupResultDrawButton == null) {
			matchupResultDrawButton = new JRadioButton();
			matchupResultGroup.add(matchupResultDrawButton);
		}
		logger.trace("getMatchupResultDrawButton() - Returning");
		logger.debug("getMatchupResultDrawButton() - Returning: {}",
				matchupResultDrawButton);
		return matchupResultDrawButton;
	}

	public JLabel getMatchupResultLossLabel() {
		logger.trace("getMatchupResultLossLabel() - Entering");
		if (matchupResultLossLabel == null) {
			matchupResultLossLabel = new JLabel(MatchupResult.LOSS.toString());
		}
		logger.trace("getMatchupResultLossLabel() - Returning");
		logger.debug("getMatchupResultLossLabel() - Returning: {}",
				matchupResultLossLabel);
		return matchupResultLossLabel;
	}

	public JRadioButton getMatchupResultLossButton() {
		logger.trace("getMatchupResultLossButton() - Entering");
		if (matchupResultLossButton == null) {
			matchupResultLossButton = new JRadioButton();
			matchupResultGroup.add(matchupResultLossButton);
		}
		logger.trace("getMatchupResultLossButton() - Returning");
		logger.debug("getMatchupResultLossButton() - Returning: {}",
				matchupResultLossButton);
		return matchupResultLossButton;
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
			constraints.gridy = 0;
			difficultyPanel.add(getDifficultyLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
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
			difficultyLabel = new JLabel("Diffculty of the matchup:");
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
			constraints.gridx = 1;
			constraints.gridy = 0;
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
			matchupNotesPanel.add(getMatchupNotesTextArea(), constraints);
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

	public JTextArea getMatchupNotesTextArea() {
		logger.trace("getMatchupNotesTextArea() - Entering");
		if (matchupNotesTextArea == null) {
			matchupNotesTextArea = new JTextArea();
			matchupNotesTextArea.setBorder(BorderFactory.createBevelBorder(1));
			matchupNotesTextArea.setPreferredSize(new Dimension(0, 60));
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
					if (!getMatchupResultDrawButton().isSelected()
							&& !getMatchupResultLossButton().isSelected()
							&& !getMatchupResultWonButton().isSelected()) {
						ok = false;
						error += "- Please select if you won the matchup.\n";
					}
					if (getMatchupNotesTextArea().getText().contains("§")) {
						ok = false;
						error += "- Your notes can't contain the character '§'. This is due to exporting.\n";
					}
					int monehz = 500;
					Item i = allItems.get(getMyItem1Box().getSelectedIndex());
					monehz -= (i.getPrice() * (int) getMyItem1AmountSpinner()
							.getValue());
					i = allItems.get(getMyItem2Box().getSelectedIndex());
					monehz -= (i.getPrice() * (int) getMyItem2AmountSpinner()
							.getValue());
					i = allItems.get(getMyItem3Box().getSelectedIndex());
					monehz -= (i.getPrice() * (int) getMyItem3AmountSpinner()
							.getValue());
					i = allItems.get(getMyItem4Box().getSelectedIndex());
					monehz -= (i.getPrice() * (int) getMyItem4AmountSpinner()
							.getValue());
					i = allItems.get(getMyItem5Box().getSelectedIndex());
					monehz -= (i.getPrice() * (int) getMyItem5AmountSpinner()
							.getValue());
					i = allItems.get(getMyItem6Box().getSelectedIndex());
					monehz -= (i.getPrice() * (int) getMyItem6AmountSpinner()
							.getValue());
					if (monehz < 0) {
						ok = false;
						error += "- Please dont exceed 500g with your items.\n";
					}
					monehz = 500;
					i = allItems.get(getEnemyItem1Box().getSelectedIndex());
					monehz -= (i.getPrice() * (int) getEnemyItem1AmountSpinner()
							.getValue());
					i = allItems.get(getEnemyItem2Box().getSelectedIndex());
					monehz -= (i.getPrice() * (int) getEnemyItem2AmountSpinner()
							.getValue());
					i = allItems.get(getEnemyItem3Box().getSelectedIndex());
					monehz -= (i.getPrice() * (int) getEnemyItem3AmountSpinner()
							.getValue());
					i = allItems.get(getEnemyItem4Box().getSelectedIndex());
					monehz -= (i.getPrice() * (int) getEnemyItem4AmountSpinner()
							.getValue());
					i = allItems.get(getEnemyItem5Box().getSelectedIndex());
					monehz -= (i.getPrice() * (int) getEnemyItem5AmountSpinner()
							.getValue());
					i = allItems.get(getEnemyItem6Box().getSelectedIndex());
					monehz -= (i.getPrice() * (int) getEnemyItem6AmountSpinner()
							.getValue());
					if (monehz < 0) {
						ok = false;
						error += "- Please dont exceed 500g with your opponents items.\n";
					}
					if (!ok) {
						JOptionPane.showMessageDialog(MainView.getInstance(),
								error);
					} else {
						Game game = new Game();
						game.setDate(getDateChooser().getDate());
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
						Matchup matchup = new Matchup();
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
						if (getMatchupResultLossButton().isSelected()) {
							matchup.setResult(MatchupResult.LOSS);
						} else if (getMatchupResultDrawButton().isSelected()) {
							matchup.setResult(MatchupResult.DRAW);
						} else {
							matchup.setResult(MatchupResult.WIN);
						}
						ArrayList<MatchupItem> mis = new ArrayList<MatchupItem>();
						i = getMyItem1Box().getItemAt(
								getMyItem1Box().getSelectedIndex());
						if (!i.getName().equals("no item")) {
							mis.add(new MatchupItem(i,
									(int) getMyItem1AmountSpinner().getValue()));
						}
						i = getMyItem2Box().getItemAt(
								getMyItem2Box().getSelectedIndex());
						if (!i.getName().equals("no item")) {
							mis.add(new MatchupItem(i,
									(int) getMyItem2AmountSpinner().getValue()));
						}
						i = getMyItem3Box().getItemAt(
								getMyItem3Box().getSelectedIndex());
						if (!i.getName().equals("no item")) {
							mis.add(new MatchupItem(i,
									(int) getMyItem3AmountSpinner().getValue()));
						}
						i = getMyItem4Box().getItemAt(
								getMyItem4Box().getSelectedIndex());
						if (!i.getName().equals("no item")) {
							mis.add(new MatchupItem(i,
									(int) getMyItem4AmountSpinner().getValue()));
						}
						i = getMyItem5Box().getItemAt(
								getMyItem5Box().getSelectedIndex());
						if (!i.getName().equals("no item")) {
							mis.add(new MatchupItem(i,
									(int) getMyItem5AmountSpinner().getValue()));
						}
						i = getMyItem6Box().getItemAt(
								getMyItem6Box().getSelectedIndex());
						if (!i.getName().equals("no item")) {
							mis.add(new MatchupItem(i,
									(int) getMyItem6AmountSpinner().getValue()));
						}
						matchup.setMyStartItems(mis);

						mis = new ArrayList<MatchupItem>();
						i = getEnemyItem1Box().getItemAt(
								getEnemyItem1Box().getSelectedIndex());
						if (!i.getName().equals("no item")) {
							mis.add(new MatchupItem(i,
									(int) getEnemyItem1AmountSpinner()
											.getValue()));
						}
						i = getEnemyItem2Box().getItemAt(
								getEnemyItem2Box().getSelectedIndex());
						if (!i.getName().equals("no item")) {
							mis.add(new MatchupItem(i,
									(int) getEnemyItem2AmountSpinner()
											.getValue()));
						}
						i = getEnemyItem3Box().getItemAt(
								getEnemyItem3Box().getSelectedIndex());
						if (!i.getName().equals("no item")) {
							mis.add(new MatchupItem(i,
									(int) getEnemyItem3AmountSpinner()
											.getValue()));
						}
						i = getEnemyItem4Box().getItemAt(
								getEnemyItem4Box().getSelectedIndex());
						if (!i.getName().equals("no item")) {
							mis.add(new MatchupItem(i,
									(int) getEnemyItem4AmountSpinner()
											.getValue()));
						}
						i = getEnemyItem5Box().getItemAt(
								getEnemyItem5Box().getSelectedIndex());
						if (!i.getName().equals("no item")) {
							mis.add(new MatchupItem(i,
									(int) getEnemyItem5AmountSpinner()
											.getValue()));
						}
						i = getEnemyItem6Box().getItemAt(
								getEnemyItem6Box().getSelectedIndex());
						if (!i.getName().equals("no item")) {
							mis.add(new MatchupItem(i,
									(int) getEnemyItem6AmountSpinner()
											.getValue()));
						}
						matchup.setEnemyStartItems(mis);
						game.setMatchup(matchup);
						MainController.getInstance().saveNewGame(game);
						dispose();
					}
				}
			});
		}
		logger.trace("getFinishButton() - Returning");
		logger.debug("getFinishButton() - Returning: {}", finishButton);
		return finishButton;
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		createGUI();
	}

	private void createGUI() {
		allItems = new ArrayList<Item>();
		Item i = new Item("no item", 0, null);
		allItems.add(i);
		allItems.addAll(ItemUtil.getAllItems());
		this.setTitle("New Game");
		this.setModal(true);
		this.setResizable(false);
		this.setContentPane(getNewEntryContentPane());
		this.pack();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((d.width - this.getSize().width) / 2,
				(d.height - this.getSize().height) / 2);
		setVisible(true);
	}
}