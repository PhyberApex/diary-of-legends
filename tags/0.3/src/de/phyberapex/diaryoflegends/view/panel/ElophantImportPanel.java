package de.phyberapex.diaryoflegends.view.panel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.phyberapex.diaryoflegends.model.GameElophantImport;

public class ElophantImportPanel extends JScrollPane {

	private static final long serialVersionUID = 1L;
	List<ElophantImportGamePanel> panels = new ArrayList<>();

	/**
	 * @param games
	 */
	public ElophantImportPanel(List<GameElophantImport> games) {
		super();
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints;
		int y = 0;
		for (int i = games.size()-1; i >= 0; i--) {
			GameElophantImport imp = games.get(i);
			ElophantImportGamePanel tmp = new ElophantImportGamePanel(imp);
			panels.add(tmp);
			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = y;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridwidth = 1;
			y++;
			panel.add(tmp, constraints);
		}
		this.setPreferredSize(new Dimension(500, 400));
		this.setViewportView(panel);
	}

	/**
	 * 
	 */
	public void setSelection() {
		for (ElophantImportGamePanel panel : panels) {
			panel.setSelection();
		}
	}
}
