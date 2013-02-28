package de.phyberapex.diaryoflegends.view.panel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.extra.ImageIconFactory;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.GameStatistic;
import de.phyberapex.diaryoflegends.model.Statistics;

public class DefaultStatsPanel extends JPanel {

	private JPanel mostKillsOneGamePanel;
	private JLabel mostKillsOneGameLabel = new JLabel("Most kills:");
	private JLabel mostKillsOneGameValueLabel;

	private JPanel mostKillsAvgPanel;
	private JLabel mostKillsAvgLabel = new JLabel("Highest kill average:");
	private JLabel mostKillsAvgValueLabel;

	private JPanel mostDeathsOneGamePanel;
	private JLabel mostDeathsOneGameLabel = new JLabel("Most deaths:");
	private JLabel mostDeathsOneGameValueLabel;

	private JPanel mostDeathsAvgPanel;
	private JLabel mostDeathsAvgLabel = new JLabel("Highest death average:");
	private JLabel mostDeathsAvgValueLabel;

	private JPanel mostAssistsOneGamePanel;
	private JLabel mostAssistsOneGameLabel = new JLabel("Most assists:");
	private JLabel mostAssistsOneGameValueLabel;

	private JPanel mostAssistsAvgPanel;
	private JLabel mostAssistsAvgLabel = new JLabel("Highest assist average:");
	private JLabel mostAssistsAvgValueLabel;

	private JPanel mostCSOneGamePanel;
	private JLabel mostCSOneGameLabel = new JLabel("Most creep kills:");
	private JLabel mostCSOneGameValueLabel;

	private JPanel mostCSAvgPanel;
	private JLabel mostCSAvgLabel = new JLabel("Highest creep kill average:");
	private JLabel mostCSAvgValueLabel;

	private JPanel mostGamesPanel;
	private JLabel mostGamesLabel = new JLabel("Most games:");
	private JLabel mostGamesValueLabel;

	private JPanel champsPanel;
	private JLabel champsLabel = new JLabel("Champions found:");
	private JScrollPane champsValuePane;
	private JList<Champion> champsValueBox;

	private JPanel totalStatsPanel;
	private JLabel totalStatsLabel = new JLabel("Total stats:");
	private JLabel gameTotalLabel = new JLabel("Total games:");
	private JLabel gameTotalValueLabel;
	private JLabel killsTotalLabel = new JLabel("Total kills:");
	private JLabel killsTotalValueLabel;
	private JLabel deathsTotalLabel = new JLabel("Total deaths:");
	private JLabel deathsTotalValueLabel;
	private JLabel assistsTotalLabel = new JLabel("Total assists:");
	private JLabel assistsTotalValueLabel;
	private JLabel csTotalLabel = new JLabel("Total minions slain:");
	private JLabel csTotalValueLabel;

	private Statistics stats;
	private static Logger logger = LogManager.getLogger(DefaultStatsPanel.class
			.getName());
	private static final long serialVersionUID = 1L;

	public DefaultStatsPanel(Statistics stats) {
		super(new GridBagLayout());
		this.stats = stats;
		createGUI();
		fillStats();
	}

	private void createGUI() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.3;
		this.add(getMostKillsOneGamePanel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.3;
		this.add(getMostKillsAvgPanel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.3;
		this.add(getMostDeathsOneGamePanel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.3;
		this.add(getMostDeathsAvgPanel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.3;
		this.add(getMostAssistsOneGamePanel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.3;
		this.add(getMostAssistsAvgPanel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.3;
		this.add(getMostCSOneGamePanel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.3;
		this.add(getMostCSAvgPanel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = 3;
		constraints.weightx = 1;
		constraints.weighty = 1;
		this.add(getMostGamesPanel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0.3;
		constraints.weighty = 0;
		constraints.gridheight = 2;
		this.add(getChampsPanel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0.3;
		constraints.weighty = 0;
		constraints.gridheight = 2;
		this.add(getTotalStatsPanel(), constraints);

	}

	public JPanel getMostKillsOneGamePanel() {
		logger.trace("getMostKillsOneGamePanel() - Entering");
		if (mostKillsOneGamePanel == null) {
			mostKillsOneGamePanel = new JPanel(new GridBagLayout());
			mostKillsOneGamePanel.setBorder(BorderFactory
					.createTitledBorder(getMostKillsOneGameLabel().getText()));

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			mostKillsOneGamePanel.add(getMostKillsOneGameValueLabel(),
					constraints);
		}
		logger.trace("getMostKillsOneGamePanel() - Returning");
		logger.debug("getMostKillsOneGamePanel() - Returning: {}",
				mostKillsOneGamePanel);
		return mostKillsOneGamePanel;
	}

	public JLabel getMostKillsOneGameLabel() {
		logger.trace("getMostKillsOneGameLabel() - Entering");
		logger.trace("getMostKillsOneGameLabel() - Returning");
		logger.debug("getMostKillsOneGameLabel() - Returning: {}",
				mostKillsOneGameLabel);
		return mostKillsOneGameLabel;
	}

	public JLabel getMostKillsOneGameValueLabel() {
		logger.trace("getMostKillsOneGameValueLabel() - Entering");
		if (mostKillsOneGameValueLabel == null) {
			mostKillsOneGameValueLabel = new JLabel();
		}
		logger.trace("getMostKillsOneGameValueLabel() - Returning");
		logger.debug("getMostKillsOneGameValueLabel() - Returning: {}",
				mostKillsOneGameValueLabel);
		return mostKillsOneGameValueLabel;
	}

	public JPanel getMostKillsAvgPanel() {
		logger.trace("getMostKillsAvgPanel() - Entering");
		if (mostKillsAvgPanel == null) {
			mostKillsAvgPanel = new JPanel(new GridBagLayout());
			mostKillsAvgPanel.setBorder(BorderFactory
					.createTitledBorder(getMostKillsAvgLabel().getText()));

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			mostKillsAvgPanel.add(getMostKillsAvgValueLabel(), constraints);

		}
		logger.trace("getMostKillsAvgPanel() - Returning");
		logger.debug("getMostKillsAvgPanel() - Returning: {}",
				mostKillsAvgPanel);
		return mostKillsAvgPanel;
	}

	public JLabel getMostKillsAvgLabel() {
		logger.trace("getMostKillsAvgLabel() - Entering");
		logger.trace("getMostKillsAvgLabel() - Returning");
		logger.debug("getMostKillsAvgLabel() - Returning: {}",
				mostKillsAvgLabel);
		return mostKillsAvgLabel;
	}

	public JLabel getMostKillsAvgValueLabel() {
		logger.trace("getMostKillsAvgValueLabel() - Entering");
		if (mostKillsAvgValueLabel == null) {
			mostKillsAvgValueLabel = new JLabel();
		}
		logger.trace("getMostKillsAvgValueLabel() - Returning");
		logger.debug("getMostKillsAvgValueLabel() - Returning: {}",
				mostKillsAvgValueLabel);
		return mostKillsAvgValueLabel;
	}

	public JPanel getMostDeathsOneGamePanel() {
		logger.trace("getMostDeathsOneGamePanel() - Entering");
		if (mostDeathsOneGamePanel == null) {
			mostDeathsOneGamePanel = new JPanel(new GridBagLayout());
			mostDeathsOneGamePanel.setBorder(BorderFactory
					.createTitledBorder(getMostDeathsOneGameLabel().getText()));

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			mostDeathsOneGamePanel.add(getMostDeathsOneGameValueLabel(),
					constraints);
		}
		logger.trace("getMostDeathsOneGamePanel() - Returning");
		logger.debug("getMostDeathsOneGamePanel() - Returning: {}",
				mostDeathsOneGamePanel);
		return mostDeathsOneGamePanel;
	}

	public JLabel getMostDeathsOneGameLabel() {
		logger.trace("getMostDeathsOneGameLabel() - Entering");
		logger.trace("getMostDeathsOneGameLabel() - Returning");
		logger.debug("getMostDeathsOneGameLabel() - Returning: {}",
				mostDeathsOneGameLabel);
		return mostDeathsOneGameLabel;
	}

	public JLabel getMostDeathsOneGameValueLabel() {
		logger.trace("getMostDeathsOneGameValueLabel() - Entering");
		if (mostDeathsOneGameValueLabel == null) {
			mostDeathsOneGameValueLabel = new JLabel();
		}
		logger.trace("getMostDeathsOneGameValueLabel() - Returning");
		logger.debug("getMostDeathsOneGameValueLabel() - Returning: {}",
				mostDeathsOneGameValueLabel);
		return mostDeathsOneGameValueLabel;
	}

	public JPanel getMostDeathsAvgPanel() {
		logger.trace("getMostDeathsAvgPanel() - Entering");
		if (mostDeathsAvgPanel == null) {
			mostDeathsAvgPanel = new JPanel(new GridBagLayout());
			mostDeathsAvgPanel.setBorder(BorderFactory
					.createTitledBorder(getMostDeathsAvgLabel().getText()));

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			mostDeathsAvgPanel.add(getMostDeathsAvgValueLabel(), constraints);
		}
		logger.trace("getMostDeathsAvgPanel() - Returning");
		logger.debug("getMostDeathsAvgPanel() - Returning: {}",
				mostDeathsAvgPanel);
		return mostDeathsAvgPanel;
	}

	public JLabel getMostDeathsAvgLabel() {
		logger.trace("getMostDeathsAvgLabel() - Entering");
		logger.trace("getMostDeathsAvgLabel() - Returning");
		logger.debug("getMostDeathsAvgLabel() - Returning: {}",
				mostDeathsAvgLabel);
		return mostDeathsAvgLabel;
	}

	public JLabel getMostDeathsAvgValueLabel() {
		logger.trace("getMostDeathsAvgValueLabel() - Entering");
		if (mostDeathsAvgValueLabel == null) {
			mostDeathsAvgValueLabel = new JLabel();
		}
		logger.trace("getMostDeathsAvgValueLabel() - Returning");
		logger.debug("getMostDeathsAvgValueLabel() - Returning: {}",
				mostDeathsAvgValueLabel);
		return mostDeathsAvgValueLabel;
	}

	public JPanel getMostAssistsOneGamePanel() {
		logger.trace("getMostAssistsOneGamePanel() - Entering");
		if (mostAssistsOneGamePanel == null) {
			mostAssistsOneGamePanel = new JPanel(new GridBagLayout());
			mostAssistsOneGamePanel
					.setBorder(BorderFactory
							.createTitledBorder(getMostAssistsOneGameLabel()
									.getText()));

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			mostAssistsOneGamePanel.add(getMostAssistsOneGameValueLabel(),
					constraints);
		}
		logger.trace("getMostAssistsOneGamePanel() - Returning");
		logger.debug("getMostAssistsOneGamePanel() - Returning: {}",
				mostAssistsOneGamePanel);
		return mostAssistsOneGamePanel;
	}

	public JLabel getMostAssistsOneGameLabel() {
		logger.trace("getMostAssistsOneGameLabel() - Entering");
		logger.trace("getMostAssistsOneGameLabel() - Returning");
		logger.debug("getMostAssistsOneGameLabel() - Returning: {}",
				mostAssistsOneGameLabel);
		return mostAssistsOneGameLabel;
	}

	public JLabel getMostAssistsOneGameValueLabel() {
		logger.trace("getMostAssistsOneGameValueLabel() - Entering");
		if (mostAssistsOneGameValueLabel == null) {
			mostAssistsOneGameValueLabel = new JLabel();
		}
		logger.trace("getMostAssistsOneGameValueLabel() - Returning");
		logger.debug("getMostAssistsOneGameValueLabel() - Returning: {}",
				mostAssistsOneGameValueLabel);
		return mostAssistsOneGameValueLabel;
	}

	public JPanel getMostAssistsAvgPanel() {
		logger.trace("getMostAssistsAvgPanel() - Entering");
		if (mostAssistsAvgPanel == null) {
			mostAssistsAvgPanel = new JPanel(new GridBagLayout());
			mostAssistsAvgPanel.setBorder(BorderFactory
					.createTitledBorder(getMostAssistsAvgLabel().getText()));

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			mostAssistsAvgPanel.add(getMostAssistsAvgValueLabel(), constraints);
		}
		logger.trace("getMostAssistsAvgPanel() - Returning");
		logger.debug("getMostAssistsAvgPanel() - Returning: {}",
				mostAssistsAvgPanel);
		return mostAssistsAvgPanel;
	}

	public JLabel getMostAssistsAvgLabel() {
		logger.trace("getMostAssistsAvgLabel() - Entering");
		logger.trace("getMostAssistsAvgLabel() - Returning");
		logger.debug("getMostAssistsAvgLabel() - Returning: {}",
				mostAssistsAvgLabel);
		return mostAssistsAvgLabel;
	}

	public JLabel getMostAssistsAvgValueLabel() {
		logger.trace("getMostAssistsAvgValueLabel() - Entering");
		if (mostAssistsAvgValueLabel == null) {
			mostAssistsAvgValueLabel = new JLabel();
		}
		logger.trace("getMostAssistsAvgValueLabel() - Returning");
		logger.debug("getMostAssistsAvgValueLabel() - Returning: {}",
				mostAssistsAvgValueLabel);
		return mostAssistsAvgValueLabel;
	}

	public JPanel getMostCSOneGamePanel() {
		logger.trace("getMostCSOneGamePanel() - Entering");
		if (mostCSOneGamePanel == null) {
			mostCSOneGamePanel = new JPanel(new GridBagLayout());
			mostCSOneGamePanel.setBorder(BorderFactory
					.createTitledBorder(getMostCSOneGameLabel().getText()));

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			mostCSOneGamePanel.add(getMostCSOneGameValueLabel(), constraints);
		}
		logger.trace("getMostCSOneGamePanel() - Returning");
		logger.debug("getMostCSOneGamePanel() - Returning: {}",
				mostCSOneGamePanel);
		return mostCSOneGamePanel;
	}

	public JLabel getMostCSOneGameLabel() {
		logger.trace("getMostCSOneGameLabel() - Entering");
		logger.trace("getMostCSOneGameLabel() - Returning");
		logger.debug("getMostCSOneGameLabel() - Returning: {}",
				mostCSOneGameLabel);
		return mostCSOneGameLabel;
	}

	public JLabel getMostCSOneGameValueLabel() {
		logger.trace("getMostCSOneGameValueLabel() - Entering");
		if (mostCSOneGameValueLabel == null) {
			mostCSOneGameValueLabel = new JLabel();
		}
		logger.trace("getMostCSOneGameValueLabel() - Returning");
		logger.debug("getMostCSOneGameValueLabel() - Returning: {}",
				mostCSOneGameValueLabel);
		return mostCSOneGameValueLabel;
	}

	public JPanel getMostCSAvgPanel() {
		logger.trace("getMostCSAvgPanel() - Entering");
		if (mostCSAvgPanel == null) {
			mostCSAvgPanel = new JPanel(new GridBagLayout());
			mostCSAvgPanel.setBorder(BorderFactory
					.createTitledBorder(getMostCSAvgLabel().getText()));

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			mostCSAvgPanel.add(getMostCSAvgValueLabel(), constraints);
		}
		logger.trace("getMostCSAvgPanel() - Returning");
		logger.debug("getMostCSAvgPanel() - Returning: {}", mostCSAvgPanel);
		return mostCSAvgPanel;
	}

	public JLabel getMostCSAvgLabel() {
		logger.trace("getMostCSAvgLabel() - Entering");
		logger.trace("getMostCSAvgLabel() - Returning");
		logger.debug("getMostCSAvgLabel() - Returning: {}", mostCSAvgLabel);
		return mostCSAvgLabel;
	}

	public JLabel getMostCSAvgValueLabel() {
		logger.trace("getMostCSAvgValueLabel() - Entering");
		if (mostCSAvgValueLabel == null) {
			mostCSAvgValueLabel = new JLabel();
		}
		logger.trace("getMostCSAvgValueLabel() - Returning");
		logger.debug("getMostCSAvgValueLabel() - Returning: {}",
				mostCSAvgValueLabel);
		return mostCSAvgValueLabel;
	}

	public JPanel getMostGamesPanel() {
		logger.trace("getMostGamesPanel() - Entering");
		if (mostGamesPanel == null) {
			mostGamesPanel = new JPanel(new GridBagLayout());
			mostGamesPanel.setBorder(BorderFactory
					.createTitledBorder(getMostGamesLabel().getText()));

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			mostGamesPanel.add(getMostGamesValueLabel(), constraints);
		}
		logger.trace("getMostGamesPanel() - Returning");
		logger.debug("getMostGamesPanel() - Returning: {}", mostGamesPanel);
		return mostGamesPanel;
	}

	public JLabel getMostGamesLabel() {
		logger.trace("getMostGamesLabel() - Entering");
		logger.trace("getMostGamesLabel() - Returning");
		logger.debug("getMostGamesLabel() - Returning: {}", mostGamesLabel);
		return mostGamesLabel;
	}

	public JLabel getMostGamesValueLabel() {
		logger.trace("getMostGamesValueLabel() - Entering");
		if (mostGamesValueLabel == null) {
			mostGamesValueLabel = new JLabel();
			mostGamesValueLabel.setFont(mostGamesValueLabel.getFont()
					.deriveFont(Font.PLAIN, 18));
		}
		logger.trace("getMostGamesValueLabel() - Returning");
		logger.debug("getMostGamesValueLabel() - Returning: {}",
				mostGamesValueLabel);
		return mostGamesValueLabel;
	}

	public JPanel getChampsPanel() {
		logger.trace("getChampsPanel() - Entering");
		if (champsPanel == null) {
			champsPanel = new JPanel(new GridBagLayout());
			champsPanel.setBorder(BorderFactory
					.createTitledBorder(getChampsLabel().getText()));

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			constraints.weighty = 1;
			champsPanel.add(getChampsValuePane(), constraints);
		}
		logger.trace("getChampsPanel() - Returning");
		logger.debug("getChampsPanel() - Returning: {}", champsPanel);
		return champsPanel;
	}

	public JLabel getChampsLabel() {
		logger.trace("getChampsLabel() - Entering");
		logger.trace("getChampsLabel() - Returning");
		logger.debug("getChampsLabel() - Returning: {}", champsLabel);
		return champsLabel;
	}

	public JScrollPane getChampsValuePane() {
		logger.trace("getChampsValuePane() - Entering");
		if (champsValuePane == null) {
			champsValuePane = new JScrollPane(getChampsValueBox());
		}
		logger.trace("getChampsValuePane() - Returning");
		logger.debug("getChampsValuePane() - Returning: {}", champsValuePane);
		return champsValuePane;
	}

	public JList<Champion> getChampsValueBox() {
		logger.trace("getChampsValueBox() - Entering");
		if (champsValueBox == null) {
			champsValueBox = new JList<Champion>();
			champsValueBox.setLayoutOrientation(JList.VERTICAL);
			champsValueBox.setVisibleRowCount(4);
		}
		logger.trace("getChampsValueBox() - Returning");
		logger.debug("getChampsValueBox() - Returning: {}", champsValueBox);
		return champsValueBox;
	}

	public JPanel getTotalStatsPanel() {
		logger.trace("getTotalStatsPanel() - Entering");
		if (totalStatsPanel == null) {
			totalStatsPanel = new JPanel(new GridBagLayout());
			totalStatsPanel.setBorder(BorderFactory
					.createTitledBorder(getTotalStatsLabel().getText()));

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			totalStatsPanel.add(getGameTotalLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			totalStatsPanel.add(getGameTotalValueLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			totalStatsPanel.add(getKillsTotalLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			totalStatsPanel.add(getKillsTotalValueLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			totalStatsPanel.add(getDeathsTotalLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			totalStatsPanel.add(getDeathsTotalValueLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			totalStatsPanel.add(getAssistsTotalLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 3;
			totalStatsPanel.add(getAssistsTotalValueLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 4;
			totalStatsPanel.add(getCsTotalLabel(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 4;
			totalStatsPanel.add(getCsTotalValueLabel(), constraints);
		}
		logger.trace("getTotalStatsPanel() - Returning");
		logger.debug("getTotalStatsPanel() - Returning: {}", totalStatsPanel);
		return totalStatsPanel;
	}

	public JLabel getTotalStatsLabel() {
		logger.trace("getTotalStatsLabel() - Entering");
		logger.trace("getTotalStatsLabel() - Returning");
		logger.debug("getTotalStatsLabel() - Returning: {}", totalStatsLabel);
		return totalStatsLabel;
	}

	public JLabel getGameTotalLabel() {
		logger.trace("getGameTotalLabel() - Entering");
		logger.trace("getGameTotalLabel() - Returning");
		logger.debug("getGameTotalLabel() - Returning: {}", gameTotalLabel);
		return gameTotalLabel;
	}

	public JLabel getGameTotalValueLabel() {
		logger.trace("getGameTotalValueLabel() - Entering");
		if (gameTotalValueLabel == null) {
			gameTotalValueLabel = new JLabel();
		}
		logger.trace("getGameTotalValueLabel() - Returning");
		logger.debug("getGameTotalValueLabel() - Returning: {}",
				gameTotalValueLabel);
		return gameTotalValueLabel;
	}

	public JLabel getKillsTotalLabel() {
		logger.trace("getKillsTotalLabel() - Entering");
		logger.trace("getKillsTotalLabel() - Returning");
		logger.debug("getKillsTotalLabel() - Returning: {}", killsTotalLabel);
		return killsTotalLabel;
	}

	public JLabel getKillsTotalValueLabel() {
		logger.trace("getKillsTotalValueLabel() - Entering");
		if (killsTotalValueLabel == null) {
			killsTotalValueLabel = new JLabel();
		}
		logger.trace("getKillsTotalValueLabel() - Returning");
		logger.debug("getKillsTotalValueLabel() - Returning: {}",
				killsTotalValueLabel);
		return killsTotalValueLabel;
	}

	public JLabel getDeathsTotalLabel() {
		logger.trace("getDeathsTotalLabel() - Entering");
		logger.trace("getDeathsTotalLabel() - Returning");
		logger.debug("getDeathsTotalLabel() - Returning: {}", deathsTotalLabel);
		return deathsTotalLabel;
	}

	public JLabel getDeathsTotalValueLabel() {
		logger.trace("getDeathsTotalValueLabel() - Entering");
		if (deathsTotalValueLabel == null) {
			deathsTotalValueLabel = new JLabel();
		}
		logger.trace("getDeathsTotalValueLabel() - Returning");
		logger.debug("getDeathsTotalValueLabel() - Returning: {}",
				deathsTotalValueLabel);
		return deathsTotalValueLabel;
	}

	public JLabel getAssistsTotalLabel() {
		logger.trace("getAssistsTotalLabel() - Entering");
		logger.trace("getAssistsTotalLabel() - Returning");
		logger.debug("getAssistsTotalLabel() - Returning: {}",
				assistsTotalLabel);
		return assistsTotalLabel;
	}

	public JLabel getAssistsTotalValueLabel() {
		logger.trace("getAssistsTotalValueLabel() - Entering");
		if (assistsTotalValueLabel == null) {
			assistsTotalValueLabel = new JLabel();
		}
		logger.trace("getAssistsTotalValueLabel() - Returning");
		logger.debug("getAssistsTotalValueLabel() - Returning: {}",
				assistsTotalValueLabel);
		return assistsTotalValueLabel;
	}

	public JLabel getCsTotalLabel() {
		logger.trace("getCsTotalLabel() - Entering");
		logger.trace("getCsTotalLabel() - Returning");
		logger.debug("getCsTotalLabel() - Returning: {}", csTotalLabel);
		return csTotalLabel;
	}

	public JLabel getCsTotalValueLabel() {
		logger.trace("getCsTotalValueLabel() - Entering");
		if (csTotalValueLabel == null) {
			csTotalValueLabel = new JLabel();
		}
		logger.trace("getCsTotalValueLabel() - Returning");
		logger.debug("getCsTotalValueLabel() - Returning: {}",
				csTotalValueLabel);
		return csTotalValueLabel;
	}

	private void fillStats() {
		@SuppressWarnings("unchecked")
		HashMap<String, GameStatistic> mostStats = (HashMap<String, GameStatistic>) stats
				.getStats().get("mostXXX");

		GameStatistic mostKills = mostStats.get("mostKills");
		if (mostKills.getChampion().getId() != 0) {
			getMostKillsOneGameValueLabel().setIcon(
					ImageIconFactory.resizeImageIcon(mostKills.getChampion()
							.getIcon(), 55, 55));
			getMostKillsOneGameValueLabel().setToolTipText(
					mostKills.getChampion().getName());
			getMostKillsOneGameValueLabel().setText(
					String.valueOf(mostKills.getValue()) + " kill(s)");
		}

		GameStatistic mostAvgKills = mostStats.get("mostAvgKills");
		if (mostAvgKills.getChampion().getId() != 0) {
			getMostKillsAvgValueLabel().setIcon(
					ImageIconFactory.resizeImageIcon(mostAvgKills.getChampion()
							.getIcon(), 55, 55));
			getMostKillsAvgValueLabel().setToolTipText(
					mostAvgKills.getChampion().getName());
			getMostKillsAvgValueLabel().setText(
					String.valueOf(mostAvgKills.getValue()) + " kill(s)");
		}

		GameStatistic mostDeaths = mostStats.get("mostDeaths");
		if (mostDeaths.getChampion().getId() != 0) {
			getMostDeathsOneGameValueLabel().setIcon(
					ImageIconFactory.resizeImageIcon(mostDeaths.getChampion()
							.getIcon(), 55, 55));
			getMostDeathsOneGameValueLabel().setToolTipText(
					mostDeaths.getChampion().getName());
			getMostDeathsOneGameValueLabel().setText(
					String.valueOf(mostDeaths.getValue()) + " death(s)");
		}

		GameStatistic mostAvgDeaths = mostStats.get("mostAvgDeaths");
		if (mostAvgDeaths.getChampion().getId() != 0) {
			getMostDeathsAvgValueLabel().setIcon(
					ImageIconFactory.resizeImageIcon(mostAvgDeaths
							.getChampion().getIcon(), 55, 55));
			getMostDeathsAvgValueLabel().setToolTipText(
					mostAvgDeaths.getChampion().getName());
			getMostDeathsAvgValueLabel().setText(
					String.valueOf(mostAvgDeaths.getValue()) + " deaths(s)");
		}

		GameStatistic mostAssists = mostStats.get("mostAssists");
		if (mostAssists.getChampion().getId() != 0) {
			getMostAssistsOneGameValueLabel().setIcon(
					ImageIconFactory.resizeImageIcon(mostAssists.getChampion()
							.getIcon(), 55, 55));
			getMostAssistsOneGameValueLabel().setToolTipText(
					mostAssists.getChampion().getName());
			getMostAssistsOneGameValueLabel().setText(
					String.valueOf(mostAssists.getValue()) + " assist(s)");
		}

		GameStatistic mostAvgAssists = mostStats.get("mostAvgAssists");
		if (mostAvgAssists.getChampion().getId() != 0) {
			getMostAssistsAvgValueLabel().setIcon(
					ImageIconFactory.resizeImageIcon(mostAvgAssists
							.getChampion().getIcon(), 55, 55));
			getMostAssistsAvgValueLabel().setToolTipText(
					mostAvgAssists.getChampion().getName());
			getMostAssistsAvgValueLabel().setText(
					String.valueOf(mostAvgAssists.getValue()) + " assist(s)");
		}

		GameStatistic mostCS = mostStats.get("mostCS");
		if (mostCS.getChampion().getId() != 0) {
			getMostCSOneGameValueLabel().setIcon(
					ImageIconFactory.resizeImageIcon(mostCS.getChampion()
							.getIcon(), 55, 55));
			getMostCSOneGameValueLabel().setToolTipText(
					mostCS.getChampion().getName());
			getMostCSOneGameValueLabel().setText(
					String.valueOf(mostCS.getValue()) + " creep kills");
		}

		GameStatistic mostAvgCS = mostStats.get("mostAvgCS");
		if (mostAvgCS.getChampion().getId() != 0) {
			getMostCSAvgValueLabel().setIcon(
					ImageIconFactory.resizeImageIcon(mostAvgCS.getChampion()
							.getIcon(), 55, 55));
			getMostCSAvgValueLabel().setToolTipText(
					mostAvgCS.getChampion().getName());
			getMostCSAvgValueLabel().setText(
					String.valueOf(mostAvgCS.getValue()) + " creep kills");
		}

		GameStatistic mostGames = mostStats.get("mostGames");
		if (mostGames.getChampion().getId() != 0) {
			getMostGamesValueLabel().setIcon(
					ImageIconFactory.resizeImageIcon(mostGames.getChampion()
							.getIcon(), 65, 65));
			getMostGamesValueLabel().setToolTipText(
					mostGames.getChampion().getName());
			getMostGamesValueLabel().setText(
					String.valueOf(mostGames.getValue()) + " game(s) played");
		}
		GameStatistic foundChamps = mostStats.get("champsFound");
		getChampsValueBox().setModel(
				new DefaultComboBoxModel<Champion>(foundChamps.getFoundChamps()
						.toArray(new Champion[] {})));
		getGameTotalValueLabel().setText(
				String.valueOf(mostStats.get("gamesTotal").getValue()));
		getKillsTotalValueLabel().setText(
				String.valueOf(mostStats.get("killsTotal").getValue()));
		getDeathsTotalValueLabel().setText(
				String.valueOf(mostStats.get("deathsTotal").getValue()));
		getAssistsTotalValueLabel().setText(
				String.valueOf(mostStats.get("assistsTotal").getValue()));
		getCsTotalValueLabel().setText(
				String.valueOf(mostStats.get("minionsSlain").getValue()));

	}
}