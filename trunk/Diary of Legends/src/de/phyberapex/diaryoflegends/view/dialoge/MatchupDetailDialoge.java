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

	public JLabel getDifficultyValueLabel() {
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

	public JPanel getMyChampPanel() {
		logger.trace("getMyChampPanel() - Entering");
		if (myChampPanel == null) {
			myChampPanel = new MatchupDetailChampionPanel(false);
		}
		logger.trace("getMyChampPanel() - Returning");
		logger.debug("getMyChampPanel() - Returning: {}", myChampPanel);
		return myChampPanel;
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
			enemyChampPanel = new MatchupDetailChampionPanel(true);
		}
		logger.trace("getEnemyChampPanel() - Returning");
		logger.debug("getEnemyChampPanel() - Returning: {}", enemyChampPanel);
		return enemyChampPanel;
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
				l.setIcon(ImageIconFactory.resizeImageIcon(champ.getIcon(), 25,
						25));
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
				l.setIcon(ImageIconFactory.resizeImageIcon(champ.getIcon(), 25,
						25));
				l.setToolTipText(champ.getName());
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
		this.setResizable(false);
		this.setModal(true);
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