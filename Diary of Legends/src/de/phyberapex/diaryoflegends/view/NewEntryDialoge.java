package de.phyberapex.diaryoflegends.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.model.Champion;

public class NewEntryDialoge extends JDialog {

	private static final long serialVersionUID = 2162451784302955479L;
	private JTabbedPane newEntryContentPane;
	
	private JPanel gameContentPanel = new JPanel();
	//TODO Datum
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
	private JCheckBox gameResultWonBox;
	private JLabel gameResultLossLabel;
	private JCheckBox gameResultLossBox;
	private JPanel gameStatsPanel;
	private JLabel ownKillsLabel;
	private JTextField ownKillsTextField;
	private JLabel ownDeathsLabel;
	private JTextField ownDeathsTextField;
	private JLabel ownAssistsLabel;
	private JTextField ownAssistsTextField;
	private JPanel gameNotesPanel;
	private JLabel gameNotesLabel;
	private JTextArea gameNotesTextArea;
	
	private JPanel matchupContentPanel = new JPanel();
	private JPanel championsPanel;
	private JPanel myChampionPanel;

	private static Logger logger = LogManager.getLogger(NewEntryDialoge.class
			.getName());

	public NewEntryDialoge() {
		super(MainView.getInstance());
		logger.trace("NewEntryDialoge() - Entering");
		logger.debug("NewEntryDialoge() - Parameter: {}", "");
		this.setTitle("New Game");
		this.setModal(true);
		//this.setContentPane();
		this.pack();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((d.width - this.getSize().width) / 2,
				(d.height - this.getSize().height) / 2);
		logger.trace("NewEntryDialoge() - Leaving");
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}