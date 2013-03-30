package de.phyberapex.diaryoflegends.view.panel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.model.GameElophantImport;

public class ElophantImportGamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GameElophantImport game;
	private JCheckBox toImportBox;
	private JLabel dateLabel;
	private JLabel myChamp1Label, myChamp2Label, myChamp3Label, myChamp4Label,
			myChamp5Label;
	private JLabel enemyChamp1Label, enemyChamp2Label, enemyChamp3Label,
			enemyChamp4Label, enemyChamp5Label;
	private JLabel vsLabel;
	private JCheckBox isRankedBox;
	transient private static Logger logger = LogManager
			.getLogger(ElophantImportGamePanel.class.getName());

	/**
	 * @param imp
	 */
	public ElophantImportGamePanel(GameElophantImport imp) {
		super(new GridBagLayout());
		this.game = imp;
		createGUI();
	}

	private void createGUI() {
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 6;
		this.add(getDateLabel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.2;
		this.add(getMyChamp1Label(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.2;
		this.add(getMyChamp2Label(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 3;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.2;
		this.add(getMyChamp3Label(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 4;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.2;
		this.add(getMyChamp4Label(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 5;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.2;
		this.add(getMyChamp5Label(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 5;
		this.add(getVsLabel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.2;
		this.add(getEnemyChamp1Label(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.2;
		this.add(getEnemyChamp2Label(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 3;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.2;
		this.add(getEnemyChamp3Label(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 4;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.2;
		this.add(getEnemyChamp4Label(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 5;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.2;
		this.add(getEnemyChamp5Label(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(0, 5, 0, 5);
		this.add(getToImportBox(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.insets = new Insets(0, 5, 0, 5);
		this.add(getIsRankedBox(), constraints);

	}

	public JCheckBox getToImportBox() {
		logger.trace("getToImportBox() - Entering");
		if (toImportBox == null) {
			toImportBox = new JCheckBox("Import");
		}
		logger.trace("getToImportBox() - Returning");
		logger.debug("getToImportBox() - Returning: {}", toImportBox);
		return toImportBox;
	}

	public JLabel getDateLabel() {
		logger.trace("getDateLabel() - Entering");
		if (dateLabel == null) {
			dateLabel = new JLabel("Date: " + game);
		}
		logger.trace("getDateLabel() - Returning");
		logger.debug("getDateLabel() - Returning: {}", dateLabel);
		return dateLabel;
	}

	public JLabel getMyChamp1Label() {
		logger.trace("getMyChamp1Label() - Entering");
		if (myChamp1Label == null) {
			myChamp1Label = new ChampionLabel(game.getMyTeam().get(0));
			if (game.getMyTeam().get(0).getId() == game.getMyChampion().getId()) {
				myChamp1Label.setBorder(BorderFactory.createLineBorder(
						Color.BLACK, 2, false));
			}
		}
		logger.trace("getMyChamp1Label() - Returning");
		logger.debug("getMyChamp1Label() - Returning: {}", myChamp1Label);
		return myChamp1Label;
	}

	public JLabel getMyChamp2Label() {
		logger.trace("getMyChamp2Label() - Entering");
		if (myChamp2Label == null) {
			myChamp2Label = new ChampionLabel(game.getMyTeam().get(1));
		}
		logger.trace("getMyChamp2Label() - Returning");
		logger.debug("getMyChamp2Label() - Returning: {}", myChamp2Label);
		return myChamp2Label;
	}

	public JLabel getMyChamp3Label() {
		logger.trace("getMyChamp3Label() - Entering");
		if (myChamp3Label == null) {
			myChamp3Label = new ChampionLabel(game.getMyTeam().get(2));
		}
		logger.trace("getMyChamp3Label() - Returning");
		logger.debug("getMyChamp3Label() - Returning: {}", myChamp3Label);
		return myChamp3Label;
	}

	public JLabel getMyChamp4Label() {
		logger.trace("getMyChamp4Label() - Entering");
		if (myChamp4Label == null) {
			myChamp4Label = new ChampionLabel(game.getMyTeam().get(3));
		}
		logger.trace("getMyChamp4Label() - Returning");
		logger.debug("getMyChamp4Label() - Returning: {}", myChamp4Label);
		return myChamp4Label;
	}

	public JLabel getMyChamp5Label() {
		logger.trace("getMyChamp5Label() - Entering");
		if (myChamp5Label == null) {
			myChamp5Label = new ChampionLabel(game.getMyTeam().get(4));
		}
		logger.trace("getMyChamp5Label() - Returning");
		logger.debug("getMyChamp5Label() - Returning: {}", myChamp5Label);
		return myChamp5Label;
	}

	public JLabel getEnemyChamp1Label() {
		logger.trace("getEnemyChamp1Label() - Entering");
		if (enemyChamp1Label == null) {
			enemyChamp1Label = new ChampionLabel(game.getEnemyTeam().get(0));
		}
		logger.trace("getEnemyChamp1Label() - Returning");
		logger.debug("getEnemyChamp1Label() - Returning: {}", enemyChamp1Label);
		return enemyChamp1Label;
	}

	public JLabel getEnemyChamp2Label() {
		logger.trace("getEnemyChamp2Label() - Entering");
		if (enemyChamp2Label == null) {
			enemyChamp2Label = new ChampionLabel(game.getEnemyTeam().get(1));
		}
		logger.trace("getEnemyChamp2Label() - Returning");
		logger.debug("getEnemyChamp2Label() - Returning: {}", enemyChamp2Label);
		return enemyChamp2Label;
	}

	public JLabel getEnemyChamp3Label() {
		logger.trace("getEnemyChamp3Label() - Entering");
		if (enemyChamp3Label == null) {
			enemyChamp3Label = new ChampionLabel(game.getEnemyTeam().get(2));
		}
		logger.trace("getEnemyChamp3Label() - Returning");
		logger.debug("getEnemyChamp3Label() - Returning: {}", enemyChamp3Label);
		return enemyChamp3Label;
	}

	public JLabel getEnemyChamp4Label() {
		logger.trace("getEnemyChamp4Label() - Entering");
		if (enemyChamp4Label == null) {
			enemyChamp4Label = new ChampionLabel(game.getEnemyTeam().get(3));
		}
		logger.trace("getEnemyChamp4Label() - Returning");
		logger.debug("getEnemyChamp4Label() - Returning: {}", enemyChamp4Label);
		return enemyChamp4Label;
	}

	public JLabel getEnemyChamp5Label() {
		logger.trace("getEnemyChamp5Label() - Entering");
		if (enemyChamp5Label == null) {
			enemyChamp5Label = new ChampionLabel(game.getEnemyTeam().get(4));
		}
		logger.trace("getEnemyChamp5Label() - Returning");
		logger.debug("getEnemyChamp5Label() - Returning: {}", enemyChamp5Label);
		return enemyChamp5Label;
	}

	public JLabel getVsLabel() {
		logger.trace("getVsLabel() - Entering");
		if (vsLabel == null) {
			vsLabel = new JLabel(" - VS - ");
		}
		logger.trace("getVsLabel() - Returning");
		logger.debug("getVsLabel() - Returning: {}", vsLabel);
		return vsLabel;
	}

	public boolean isSelected() {
		return getToImportBox().isSelected();
	}

	/**
	 * 
	 */
	public void setSelection() {
		game.setSelected(isSelected());
	}

	public JCheckBox getIsRankedBox() {
		logger.trace("getIsRankedBox() - Entering");
		if (isRankedBox == null) {
			isRankedBox = new JCheckBox("Ranked");
			isRankedBox.setSelected(game.isRanked());
			isRankedBox.setEnabled(false);
		}
		logger.trace("getIsRankedBox() - Returning");
		logger.debug("getIsRankedBox() - Returning: {}", isRankedBox);
		return isRankedBox;
	}
}
