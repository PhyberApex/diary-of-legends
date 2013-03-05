package de.phyberapex.diaryoflegends.view.panel.stats;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.model.Top3EnemyStatistics;

public class Top3EnemiesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Top3EnemyStatistics stats;

	private List<JPanel> champsPanels = new ArrayList<>();
	private List<JLabel> myChampLabels = new ArrayList<>();
	private List<JLabel> enemyChamp1Labels = new ArrayList<>();
	private List<JLabel> enemyChamp2Labels = new ArrayList<>();
	private List<JLabel> enemyChamp3Labels = new ArrayList<>();

	transient private static Logger logger = LogManager
			.getLogger(Top3EnemiesPanel.class.getName());

	public Top3EnemiesPanel(Top3EnemyStatistics stats) {
		super();
		this.stats = stats;
		fillStats();
		createGUI();
	}

	private void createGUI() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		// this.add(champsPanel, constraints);
	}

	private void fillStats() {
	}
}
