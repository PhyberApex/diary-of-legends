package de.phyberapex.diaryoflegends.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.model.Matchup;

public class MatchupDetailView extends JDialog implements View {

	private static final long serialVersionUID = -7485492927311999351L;
	private static MatchupDetailView instance;
	private JPanel matchupContentPanel;
	private JLabel headlineLabel;
	private JPanel myChampPanel;
	private JLabel myChampIconLabel;
	private JLabel myChampNameLabel;
	private JPanel myChampItemsPanel;
	private JLabel myChampItemsLabel;
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
	private JLabel enemyChampItemsLabel;
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
	private JButton closeButton;

	private static Logger logger = LogManager.getLogger(MatchupDetailView.class
			.getName());

	private MatchupDetailView() {
		super();
		createGUI();
	}

	private void createGUI() {
		this.setContentPane(getMatchupContentPanel());
	}

	public JPanel getMatchupContentPanel() {
	logger.trace("getMatchupContentPanel() - Entering");
	if(matchupContentPanel == null){
		matchupContentPanel = new JPanel();
	}
	logger.trace("getMatchupContentPanel() - Returning");
	logger.debug("getMatchupContentPanel() - Returning: {}", matchupContentPanel);
	return matchupContentPanel;}
	

	public JLabel getHeadlineLabel() {
	logger.trace("getHeadlineLabel() - Entering");
	if(headlineLabel == null){
		headlineLabel = new JLabel();
	}
	logger.trace("getHeadlineLabel() - Returning");
	logger.debug("getHeadlineLabel() - Returning: {}", headlineLabel);
	return headlineLabel;}
	

	public JPanel getMyChampPanel() {
	logger.trace("getMyChampPanel() - Entering");
	if(myChampPanel == null){
		myChampPanel = new JPanel();
	}
	logger.trace("getMyChampPanel() - Returning");
	logger.debug("getMyChampPanel() - Returning: {}", myChampPanel);
	return myChampPanel;}
	

	public JLabel getMyChampIconLabel() {
	logger.trace("getMyChampIconLabel() - Entering");
	if(myChampIconLabel == null){
		myChampIconLabel = new JLabel();
	}
	logger.trace("getMyChampIconLabel() - Returning");
	logger.debug("getMyChampIconLabel() - Returning: {}", myChampIconLabel);
	return myChampIconLabel;}
	

	public JLabel getMyChampNameLabel() {
	logger.trace("getMyChampNameLabel() - Entering");
	if(myChampNameLabel == null){
		myChampNameLabel = new JLabel();
	}
	logger.trace("getMyChampNameLabel() - Returning");
	logger.debug("getMyChampNameLabel() - Returning: {}", myChampNameLabel);
	return myChampNameLabel;}
	

	public JPanel getMyChampItemsPanel() {
	logger.trace("getMyChampItemsPanel() - Entering");
	if(myChampItemsPanel == null){
		myChampItemsPanel = new JPanel();
	}
	logger.trace("getMyChampItemsPanel() - Returning");
	logger.debug("getMyChampItemsPanel() - Returning: {}", myChampItemsPanel);
	return myChampItemsPanel;}
	

	public JLabel getMyChampItemsLabel() {
	logger.trace("getMyChampItemsLabel() - Entering");
	if(myChampItemsLabel == null){
		myChampItemsLabel = new JLabel();
	}
	logger.trace("getMyChampItemsLabel() - Returning");
	logger.debug("getMyChampItemsLabel() - Returning: {}", myChampItemsLabel);
	return myChampItemsLabel;}
	

	public JLabel getMyChampItem1Label() {
	logger.trace("getMyChampItem1Label() - Entering");
	if(myChampItem1Label == null){
		myChampItem1Label = new JLabel();
	}
	logger.trace("getMyChampItem1Label() - Returning");
	logger.debug("getMyChampItem1Label() - Returning: {}", myChampItem1Label);
	return myChampItem1Label;}
	

	public JLabel getMyChampItem2Label() {
	logger.trace("getMyChampItem2Label() - Entering");
	if(myChampItem2Label == null){
		myChampItem2Label = new JLabel();
	}
	logger.trace("getMyChampItem2Label() - Returning");
	logger.debug("getMyChampItem2Label() - Returning: {}", myChampItem2Label);
	return myChampItem2Label;}
	

	public JLabel getMyChampItem3Label() {
	logger.trace("getMyChampItem3Label() - Entering");
	if(myChampItem3Label == null){
		myChampItem3Label = new JLabel();
	}
	logger.trace("getMyChampItem3Label() - Returning");
	logger.debug("getMyChampItem3Label() - Returning: {}", myChampItem3Label);
	return myChampItem3Label;}
	

	public JLabel getMyChampItem4Label() {
	logger.trace("getMyChampItem4Label() - Entering");
	if(myChampItem4Label == null){
		myChampItem4Label = new JLabel();
	}
	logger.trace("getMyChampItem4Label() - Returning");
	logger.debug("getMyChampItem4Label() - Returning: {}", myChampItem4Label);
	return myChampItem4Label;}
	

	public JLabel getMyChampItem5Label() {
	logger.trace("getMyChampItem5Label() - Entering");
	if(myChampItem5Label == null){
		myChampItem5Label = new JLabel();
	}
	logger.trace("getMyChampItem5Label() - Returning");
	logger.debug("getMyChampItem5Label() - Returning: {}", myChampItem5Label);
	return myChampItem5Label;}
	

	public JLabel getMyChampItem6Label() {
	logger.trace("getMyChampItem6Label() - Entering");
	if(myChampItem6Label == null){
		myChampItem6Label = new JLabel();
	}
	logger.trace("getMyChampItem6Label() - Returning");
	logger.debug("getMyChampItem6Label() - Returning: {}", myChampItem6Label);
	return myChampItem6Label;}
	

	public JLabel getMyChampResultLabel() {
	logger.trace("getMyChampResultLabel() - Entering");
	if(myChampResultLabel == null){
		myChampResultLabel = new JLabel();
	}
	logger.trace("getMyChampResultLabel() - Returning");
	logger.debug("getMyChampResultLabel() - Returning: {}", myChampResultLabel);
	return myChampResultLabel;}
	

	public JLabel getVersusLabel() {
	logger.trace("getVersusLabel() - Entering");
	if(versusLabel == null){
		versusLabel = new JLabel();
	}
	logger.trace("getVersusLabel() - Returning");
	logger.debug("getVersusLabel() - Returning: {}", versusLabel);
	return versusLabel;}
	

	public JPanel getEnemyChampPanel() {
	logger.trace("getEnemyChampPanel() - Entering");
	if(enemyChampPanel == null){
		enemyChampPanel = new JPanel();
	}
	logger.trace("getEnemyChampPanel() - Returning");
	logger.debug("getEnemyChampPanel() - Returning: {}", enemyChampPanel);
	return enemyChampPanel;}
	

	public JLabel getEnemyChampIconLabel() {
	logger.trace("getEnemyChampIconLabel() - Entering");
	if(enemyChampIconLabel == null){
		enemyChampIconLabel = new JLabel();
	}
	logger.trace("getEnemyChampIconLabel() - Returning");
	logger.debug("getEnemyChampIconLabel() - Returning: {}", enemyChampIconLabel);
	return enemyChampIconLabel;}
	

	public JLabel getEnemyChampNameLabel() {
	logger.trace("getEnemyChampNameLabel() - Entering");
	if(enemyChampNameLabel == null){
		enemyChampNameLabel = new JLabel();
	}
	logger.trace("getEnemyChampNameLabel() - Returning");
	logger.debug("getEnemyChampNameLabel() - Returning: {}", enemyChampNameLabel);
	return enemyChampNameLabel;}
	

	public JPanel getEnemyChampItemsPanel() {
	logger.trace("getEnemyChampItemsPanel() - Entering");
	if(enemyChampItemsPanel == null){
		enemyChampItemsPanel = new JPanel();
	}
	logger.trace("getEnemyChampItemsPanel() - Returning");
	logger.debug("getEnemyChampItemsPanel() - Returning: {}", enemyChampItemsPanel);
	return enemyChampItemsPanel;}
	

	public JLabel getEnemyChampItemsLabel() {
	logger.trace("getEnemyChampItemsLabel() - Entering");
	if(enemyChampItemsLabel == null){
		enemyChampItemsLabel = new JLabel();
	}
	logger.trace("getEnemyChampItemsLabel() - Returning");
	logger.debug("getEnemyChampItemsLabel() - Returning: {}", enemyChampItemsLabel);
	return enemyChampItemsLabel;}
	

	public JLabel getEnemyChampItem1Label() {
	logger.trace("getEnemyChampItem1Label() - Entering");
	if(enemyChampItem1Label == null){
		enemyChampItem1Label = new JLabel();
	}
	logger.trace("getEnemyChampItem1Label() - Returning");
	logger.debug("getEnemyChampItem1Label() - Returning: {}", enemyChampItem1Label);
	return enemyChampItem1Label;}
	

	public JLabel getEnemyChampItem2Label() {
	logger.trace("getEnemyChampItem2Label() - Entering");
	if(enemyChampItem2Label == null){
		enemyChampItem2Label = new JLabel();
	}
	logger.trace("getEnemyChampItem2Label() - Returning");
	logger.debug("getEnemyChampItem2Label() - Returning: {}", enemyChampItem2Label);
	return enemyChampItem2Label;}
	

	public JLabel getEnemyChampItem3Label() {
	logger.trace("getEnemyChampItem3Label() - Entering");
	if(enemyChampItem3Label == null){
		enemyChampItem3Label = new JLabel();
	}
	logger.trace("getEnemyChampItem3Label() - Returning");
	logger.debug("getEnemyChampItem3Label() - Returning: {}", enemyChampItem3Label);
	return enemyChampItem3Label;}
	

	public JLabel getEnemyChampItem4Label() {
	logger.trace("getEnemyChampItem4Label() - Entering");
	if(enemyChampItem4Label == null){
		enemyChampItem4Label = new JLabel();
	}
	logger.trace("getEnemyChampItem4Label() - Returning");
	logger.debug("getEnemyChampItem4Label() - Returning: {}", enemyChampItem4Label);
	return enemyChampItem4Label;}
	

	public JLabel getEnemyChampItem5Label() {
	logger.trace("getEnemyChampItem5Label() - Entering");
	if(enemyChampItem5Label == null){
		enemyChampItem5Label = new JLabel();
	}
	logger.trace("getEnemyChampItem5Label() - Returning");
	logger.debug("getEnemyChampItem5Label() - Returning: {}", enemyChampItem5Label);
	return enemyChampItem5Label;}
	

	public JLabel getEnemyChampItem6Label() {
	logger.trace("getEnemyChampItem6Label() - Entering");
	if(enemyChampItem6Label == null){
		enemyChampItem6Label = new JLabel();
	}
	logger.trace("getEnemyChampItem6Label() - Returning");
	logger.debug("getEnemyChampItem6Label() - Returning: {}", enemyChampItem6Label);
	return enemyChampItem6Label;}
	

	public JLabel getEnemyChampResultLabel() {
	logger.trace("getEnemyChampResultLabel() - Entering");
	if(enemyChampResultLabel == null){
		enemyChampResultLabel = new JLabel();
	}
	logger.trace("getEnemyChampResultLabel() - Returning");
	logger.debug("getEnemyChampResultLabel() - Returning: {}", enemyChampResultLabel);
	return enemyChampResultLabel;}
	

	public JPanel getNotesPanel() {
	logger.trace("getNotesPanel() - Entering");
	if(notesPanel == null){
		notesPanel = new JPanel();
	}
	logger.trace("getNotesPanel() - Returning");
	logger.debug("getNotesPanel() - Returning: {}", notesPanel);
	return notesPanel;}
	

	public JLabel getNotesLabel() {
	logger.trace("getNotesLabel() - Entering");
	if(notesLabel == null){
		notesLabel = new JLabel();
	}
	logger.trace("getNotesLabel() - Returning");
	logger.debug("getNotesLabel() - Returning: {}", notesLabel);
	return notesLabel;}
	

	public JTextArea getNotesTextarea() {
	logger.trace("getNotesTextarea() - Entering");
	if(notesTextarea == null){
		notesTextarea = new JTextArea();
	}
	logger.trace("getNotesTextarea() - Returning");
	logger.debug("getNotesTextarea() - Returning: {}", notesTextarea);
	return notesTextarea;}
	

	public JButton getSaveNotesButton() {
	logger.trace("getSaveNotesButton() - Entering");
	if(saveNotesButton == null){
		saveNotesButton = new JButton();
	}
	logger.trace("getSaveNotesButton() - Returning");
	logger.debug("getSaveNotesButton() - Returning: {}", saveNotesButton);
	return saveNotesButton;}
	

	public JPanel getGamePanel() {
	logger.trace("getGamePanel() - Entering");
	if(gamePanel == null){
		gamePanel = new JPanel();
	}
	logger.trace("getGamePanel() - Returning");
	logger.debug("getGamePanel() - Returning: {}", gamePanel);
	return gamePanel;}
	

	public JLabel getGameStatsLabel() {
	logger.trace("getGameStatsLabel() - Entering");
	if(gameStatsLabel == null){
		gameStatsLabel = new JLabel();
	}
	logger.trace("getGameStatsLabel() - Returning");
	logger.debug("getGameStatsLabel() - Returning: {}", gameStatsLabel);
	return gameStatsLabel;}
	

	public JLabel getKillsLabel() {
	logger.trace("getKillsLabel() - Entering");
	if(killsLabel == null){
		killsLabel = new JLabel();
	}
	logger.trace("getKillsLabel() - Returning");
	logger.debug("getKillsLabel() - Returning: {}", killsLabel);
	return killsLabel;}
	

	public JLabel getKillsValueLabel() {
	logger.trace("getKillsValueLabel() - Entering");
	if(killsValueLabel == null){
		killsValueLabel = new JLabel();
	}
	logger.trace("getKillsValueLabel() - Returning");
	logger.debug("getKillsValueLabel() - Returning: {}", killsValueLabel);
	return killsValueLabel;}
	

	public JLabel getDeathsLabel() {
	logger.trace("getDeathsLabel() - Entering");
	if(deathsLabel == null){
		deathsLabel = new JLabel();
	}
	logger.trace("getDeathsLabel() - Returning");
	logger.debug("getDeathsLabel() - Returning: {}", deathsLabel);
	return deathsLabel;}
	

	public JLabel getDeathsValueLabel() {
	logger.trace("getDeathsValueLabel() - Entering");
	if(deathsValueLabel == null){
		deathsValueLabel = new JLabel();
	}
	logger.trace("getDeathsValueLabel() - Returning");
	logger.debug("getDeathsValueLabel() - Returning: {}", deathsValueLabel);
	return deathsValueLabel;}
	

	public JLabel getAssistsLabel() {
	logger.trace("getAssistsLabel() - Entering");
	if(assistsLabel == null){
		assistsLabel = new JLabel();
	}
	logger.trace("getAssistsLabel() - Returning");
	logger.debug("getAssistsLabel() - Returning: {}", assistsLabel);
	return assistsLabel;}
	

	public JLabel getAssistsValueLabel() {
	logger.trace("getAssistsValueLabel() - Entering");
	if(assistsValueLabel == null){
		assistsValueLabel = new JLabel();
	}
	logger.trace("getAssistsValueLabel() - Returning");
	logger.debug("getAssistsValueLabel() - Returning: {}", assistsValueLabel);
	return assistsValueLabel;}
	

	public JButton getCloseButton() {
	logger.trace("getCloseButton() - Entering");
	if(closeButton == null){
		closeButton = new JButton();
	}
	logger.trace("getCloseButton() - Returning");
	logger.debug("getCloseButton() - Returning: {}", closeButton);
	return closeButton;}
	

	public void showDetails(Matchup matchup) {
		// TODO
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
