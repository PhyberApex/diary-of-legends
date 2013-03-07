package de.phyberapex.diaryoflegends.controller;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.phyberapex.diaryoflegends.model.stats.Top3EnemyStatistics;

public class Top3EnemiesPerChampPanel extends JPanel {

	private List<JPanel> panels = new ArrayList<>();
	private List<JLabel> myChampLabels = new ArrayList<>();
	private List<JLabel> enemy1Labels = new ArrayList<>();
	private List<JLabel> enemy2Labels = new ArrayList<>();
	private List<JLabel> enemy3Labels = new ArrayList<>();
	private List<Top3EnemyStatistics> stats;

	/**
	 * @param statsTop3Champ
	 */
	public Top3EnemiesPerChampPanel(List<Top3EnemyStatistics> statsTop3Champ) {
		super(new GridBagLayout());
		this.stats = statsTop3Champ;
		createGUI();
		fillFields();
	}

	private void fillFields() {
		int x = 0;
		for (Top3EnemyStatistics stat : stats) {
			panels.get(x).setBorder(
					BorderFactory.createTitledBorder(stat.getMyChamp().get(0)
							.getName()));
			myChampLabels.get(x).setIcon(stat.getMyChamp().get(0).getIcon());
			enemy1Labels.get(x)
					.setIcon(stat.getEnemy1Champ().getResizeIcon(35));
			enemy1Labels.get(x).setToolTipText(stat.getEnemy1Champ().getName());
			enemy1Labels.get(x).setText(
					"Top enemy 1 with a difficulty of " + stat.getEnemy1diff());
			enemy2Labels.get(x)
					.setIcon(stat.getEnemy2Champ().getResizeIcon(35));
			enemy2Labels.get(x).setToolTipText(stat.getEnemy2Champ().getName());
			enemy2Labels.get(x).setText(
					"Top enemy 2 with a difficulty of " + stat.getEnemy2diff());
			enemy3Labels.get(x)
					.setIcon(stat.getEnemy3Champ().getResizeIcon(35));
			enemy3Labels.get(x).setToolTipText(stat.getEnemy3Champ().getName());
			enemy3Labels.get(x).setText(
					"Top enemy 3 with a difficulty of " + stat.getEnemy3diff());
			x++;
		}
	}

	private void createGUI() {
		for (int i = 0; i < stats.size(); i++) {
			JPanel panel = new JPanel(new GridBagLayout());
			panels.add(panel);
			JLabel myChampLabel = new JLabel();
			myChampLabels.add(myChampLabel);
			JLabel enemy1Label = new JLabel();
			enemy1Labels.add(enemy1Label);
			JLabel enemy2Label = new JLabel();
			enemy2Labels.add(enemy2Label);
			JLabel enemy3Label = new JLabel();
			enemy3Labels.add(enemy3Label);

			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridheight = 3;
			panel.add(myChampLabel, constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 0;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			constraints.weighty = 0.33;
			panel.add(enemy1Label, constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			constraints.weighty = 0.33;
			panel.add(enemy2Label, constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 1;
			constraints.gridy = 2;
			constraints.fill = GridBagConstraints.BOTH;
			constraints.weightx = 1;
			constraints.weighty = 0.33;
			panel.add(enemy3Label, constraints);
		}
		for (int i = 0; i < panels.size(); i++) {
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = i;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			constraints.anchor = GridBagConstraints.NORTH;
			this.add(panels.get(i), constraints);
		}
	}

	private static final long serialVersionUID = 1L;

}