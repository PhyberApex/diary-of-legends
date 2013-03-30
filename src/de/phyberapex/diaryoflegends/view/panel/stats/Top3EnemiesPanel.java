package de.phyberapex.diaryoflegends.view.panel.stats;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.stats.Top3EnemyStatistics;

public class Top3EnemiesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Top3EnemyStatistics stats;

	private JScrollPane foundChampsPane;
	private JList<Champion> foundChamps;
	private JPanel champsPanels;
	private JLabel enemyChamp1Labels;
	private JLabel enemyChamp2Labels;
	private JLabel enemyChamp3Labels;

	transient private static Logger logger = LogManager
			.getLogger(Top3EnemiesPanel.class.getName());

	public Top3EnemiesPanel(Top3EnemyStatistics stats) {
		super();
		this.stats = stats;
		createGUI();
		fillStats();
	}

	private void createGUI() {
		this.setLayout(new GridBagLayout());
		if (stats == null) {
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			this.add(
					new JLabel("Not enough Champions to create this statistic"),
					constraints);
		} else {
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.gridheight = 3;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 0.4;
			constraints.weighty = 1;
			this.add(getFoundChampsPane(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 0.6;
			constraints.weighty = 0.33;
			this.add(getEnemyChamp1Labels(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 0.6;
			constraints.weighty = 0.33;
			this.add(getEnemyChamp2Labels(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 0.6;
			constraints.weighty = 0.33;
			this.add(getEnemyChamp3Labels(), constraints);
		}
	}

	private void fillStats() {
		if (stats != null) {
			getFoundChamps().setModel(
					new DefaultComboBoxModel<Champion>(stats.getMyChamp()
							.toArray(new Champion[] {})));
			getEnemyChamp1Labels().setIcon(stats.getEnemy1Champ().getIcon());
			getEnemyChamp1Labels().setText(
					stats.getEnemy1Champ().getName()
							+ " with a average difficulty of "
							+ stats.getEnemy1diff());
			getEnemyChamp2Labels().setIcon(stats.getEnemy2Champ().getIcon());
			getEnemyChamp2Labels().setText(
					stats.getEnemy2Champ().getName()
							+ " with a average difficulty of "
							+ stats.getEnemy2diff());
			getEnemyChamp3Labels().setIcon(stats.getEnemy3Champ().getIcon());
			getEnemyChamp3Labels().setText(
					stats.getEnemy3Champ().getName()
							+ " with a average difficulty of "
							+ stats.getEnemy3diff());
		}
	}

	public JPanel getChampsPanels() {
		logger.trace("getChampsPanels() - Entering");
		if (champsPanels == null) {
			champsPanels = new JPanel();
		}
		logger.trace("getChampsPanels() - Returning");
		logger.debug("getChampsPanels() - Returning: {}", champsPanels);
		return champsPanels;
	}

	public JLabel getEnemyChamp1Labels() {
		logger.trace("getEnemyChamp1Labels() - Entering");
		if (enemyChamp1Labels == null) {
			enemyChamp1Labels = new JLabel();
			enemyChamp1Labels.setBorder(BorderFactory
					.createTitledBorder("Number 1 enemy:"));
		}
		logger.trace("getEnemyChamp1Labels() - Returning");
		logger.debug("getEnemyChamp1Labels() - Returning: {}",
				enemyChamp1Labels);
		return enemyChamp1Labels;
	}

	public JLabel getEnemyChamp2Labels() {
		logger.trace("getEnemyChamp2Labels() - Entering");
		if (enemyChamp2Labels == null) {
			enemyChamp2Labels = new JLabel();
			enemyChamp2Labels.setBorder(BorderFactory
					.createTitledBorder("Number 2 enemy:"));
		}
		logger.trace("getEnemyChamp2Labels() - Returning");
		logger.debug("getEnemyChamp2Labels() - Returning: {}",
				enemyChamp2Labels);
		return enemyChamp2Labels;
	}

	public JLabel getEnemyChamp3Labels() {
		logger.trace("getEnemyChamp3Labels() - Entering");
		if (enemyChamp3Labels == null) {
			enemyChamp3Labels = new JLabel();
			enemyChamp3Labels.setBorder(BorderFactory
					.createTitledBorder("Number 3 enemy:"));
		}
		logger.trace("getEnemyChamp3Labels() - Returning");
		logger.debug("getEnemyChamp3Labels() - Returning: {}",
				enemyChamp3Labels);
		return enemyChamp3Labels;
	}

	public JScrollPane getFoundChampsPane() {
		logger.trace("getFoundChampsPane() - Entering");
		if (foundChampsPane == null) {
			foundChampsPane = new JScrollPane();
			foundChampsPane.setBorder(BorderFactory
					.createTitledBorder("Champions found:"));
			foundChampsPane.setViewportView(getFoundChamps());
		}
		logger.trace("getFoundChampsPane() - Returning");
		logger.debug("getFoundChampsPane() - Returning: {}", foundChampsPane);
		return foundChampsPane;
	}

	public JList<Champion> getFoundChamps() {
		logger.trace("getFoundChamps() - Entering");
		if (foundChamps == null) {
			foundChamps = new JList<Champion>();
			foundChamps.setEnabled(false);
		}
		logger.trace("getFoundChamps() - Returning");
		logger.debug("getFoundChamps() - Returning: {}", foundChamps);
		return foundChamps;
	}
}
