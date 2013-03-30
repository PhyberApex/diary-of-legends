package de.phyberapex.diaryoflegends.view.dialoge.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;
import de.phyberapex.diaryoflegends.view.dialoge.comboBox.ChampionComboBox;

public class NewEntryTeamListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ChampionComboBox champ1Box;
	private ChampionComboBox champ2Box;
	private ChampionComboBox champ3Box;
	private ChampionComboBox champ4Box;
	private ChampionComboBox champ5Box;
	private static List<Champion> allChampions = new ArrayList<>();;
	private Logger logger = LogManager.getLogger(NewEntryItemPanel.class
			.getName());

	static {
		Champion chmp = new Champion(0, "no champion");
		allChampions.add(chmp);
		allChampions.addAll(ChampionUtil.getAllChampions());
	}

	public NewEntryTeamListPanel() {
		super(new GridBagLayout());
		createGUI();
	}

	private void createGUI() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		this.add(getChamp1Box(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		this.add(getChamp2Box(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		this.add(getChamp3Box(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		this.add(getChamp4Box(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		this.add(getChamp5Box(), constraints);
	}

	private ChampionComboBox getChamp1Box() {
		logger.trace("getChamp1Box() - Entering");
		if (champ1Box == null) {
			champ1Box = new ChampionComboBox(
					new DefaultComboBoxModel<Champion>(
							allChampions.toArray(new Champion[] {})));
		}
		logger.trace("getChamp1Box() - Returning");
		logger.debug("getChamp1Box() - Returning: {}", champ1Box);
		return champ1Box;
	}

	private ChampionComboBox getChamp2Box() {
		logger.trace("getChamp2Box() - Entering");
		if (champ2Box == null) {
			champ2Box = new ChampionComboBox(
					new DefaultComboBoxModel<Champion>(
							allChampions.toArray(new Champion[] {})));
		}
		logger.trace("getChamp2Box() - Returning");
		logger.debug("getChamp2Box() - Returning: {}", champ2Box);
		return champ2Box;
	}

	private ChampionComboBox getChamp3Box() {
		logger.trace("getChamp3Box() - Entering");
		if (champ3Box == null) {
			champ3Box = new ChampionComboBox(
					new DefaultComboBoxModel<Champion>(
							allChampions.toArray(new Champion[] {})));
		}
		logger.trace("getChamp3Box() - Returning");
		logger.debug("getChamp3Box() - Returning: {}", champ3Box);
		return champ3Box;
	}

	private ChampionComboBox getChamp4Box() {
		logger.trace("getChamp4Box() - Entering");
		if (champ4Box == null) {
			champ4Box = new ChampionComboBox(
					new DefaultComboBoxModel<Champion>(
							allChampions.toArray(new Champion[] {})));
		}
		logger.trace("getChamp4Box() - Returning");
		logger.debug("getChamp4Box() - Returning: {}", champ4Box);
		return champ4Box;
	}

	private ChampionComboBox getChamp5Box() {
		logger.trace("getChamp5Box() - Entering");
		if (champ5Box == null) {
			champ5Box = new ChampionComboBox(
					new DefaultComboBoxModel<Champion>(
							allChampions.toArray(new Champion[] {})));
		}
		logger.trace("getChamp5Box() - Returning");
		logger.debug("getChamp5Box() - Returning: {}", champ5Box);
		return champ5Box;
	}

	public List<Champion> getChampions() {
		logger.trace("getChampions() - Entering");
		List<Champion> returnValue = new ArrayList<>();
		returnValue.add((Champion) champ1Box.getSelectedItem());
		returnValue.add((Champion) champ2Box.getSelectedItem());
		returnValue.add((Champion) champ3Box.getSelectedItem());
		returnValue.add((Champion) champ4Box.getSelectedItem());
		returnValue.add((Champion) champ5Box.getSelectedItem());
		logger.trace("getChampions() - Returning");
		logger.debug("getChampions() - Returning: {}", returnValue);
		return returnValue;
	}

	public void setChampions(List<Champion> champions) {
		logger.trace("setChampions() - Entering");
		logger.debug("setChampions() - Parameter: {}", champions);
		if (champions.size() == 5) {
			getChamp1Box().setSelectedItem(champions.get(0));
			getChamp2Box().setSelectedItem(champions.get(1));
			getChamp3Box().setSelectedItem(champions.get(2));
			getChamp4Box().setSelectedItem(champions.get(3));
			getChamp5Box().setSelectedItem(champions.get(4));
		}
		logger.trace("setChampions() - Leaving");
	}

	public boolean checkDoubles() {
		logger.trace("checkDoubles() - Entering");
		boolean returnValue = false;
		if (getChamp1Box().getSelectedIndex() == getChamp2Box()
				.getSelectedIndex()
				|| getChamp1Box().getSelectedIndex() == getChamp3Box()
						.getSelectedIndex()
				|| getChamp1Box().getSelectedIndex() == getChamp4Box()
						.getSelectedIndex()
				|| getChamp1Box().getSelectedIndex() == getChamp5Box()
						.getSelectedIndex()
				|| getChamp2Box().getSelectedIndex() == getChamp3Box()
						.getSelectedIndex()
				|| getChamp2Box().getSelectedIndex() == getChamp4Box()
						.getSelectedIndex()
				|| getChamp2Box().getSelectedIndex() == getChamp5Box()
						.getSelectedIndex()
				|| getChamp3Box().getSelectedIndex() == getChamp4Box()
						.getSelectedIndex()
				|| getChamp3Box().getSelectedIndex() == getChamp4Box()
						.getSelectedIndex()
				|| getChamp4Box().getSelectedIndex() == getChamp5Box()
						.getSelectedIndex()) {
			returnValue = true;
		}
		logger.trace("checkDoubles() - Returning");
		logger.debug("checkDoubles() - Returning: {}", returnValue);
		return returnValue;
	}

	public void clear() {
		getChamp1Box().setSelectedIndex(0);
		getChamp2Box().setSelectedIndex(0);
		getChamp3Box().setSelectedIndex(0);
		getChamp4Box().setSelectedIndex(0);
		getChamp5Box().setSelectedIndex(0);
	}
}
