package de.phyberapex.diaryoflegends.view.dialoge.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.MatchupItem;
import de.phyberapex.diaryoflegends.model.SummonerSpell;
import de.phyberapex.diaryoflegends.model.util.SummonerSpellUtil;
import de.phyberapex.diaryoflegends.view.dialoge.comboBox.ChampionComboBox;
import de.phyberapex.diaryoflegends.view.dialoge.comboBox.SummonerSpellComboBox;
import de.phyberapex.diaryoflegends.view.renderer.ChampionBigComboBoxRenderer;

public class NewEntryChampionPanel extends JPanel {

	private static List<SummonerSpell> allSpells;
	private ChampionComboBox championBox;
	private JTabbedPane itemsPane;
	private NewEntryItemBuildPanel startItemsPanel;
	private NewEntryItemBuildPanel endItemsPanel;
	private JPanel spellsPanel;
	private JLabel spell1Label;
	private SummonerSpellComboBox spell1Box;
	private JLabel spell2Label;
	private SummonerSpellComboBox spell2Box;
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager
			.getLogger(NewEntryChampionPanel.class.getName());

	static {
		allSpells = new ArrayList<SummonerSpell>();
		SummonerSpell s = new SummonerSpell(0, "no spell");
		allSpells.add(s);
		allSpells.addAll(SummonerSpellUtil.getAllSpells());
	}

	public NewEntryChampionPanel() {
		super(new GridBagLayout());
		createGUI();
	}

	private void createGUI() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		constraints.insets = new Insets(0, 0, 5, 0);
		add(getChampionBox(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		add(getItemsPane(), constraints);
	}

	private ChampionComboBox getChampionBox() {
		logger.trace("getChampionBox() - Entering");
		if (championBox == null) {
			championBox = new ChampionComboBox();
			championBox.setRenderer(new ChampionBigComboBoxRenderer());
		}
		logger.trace("getChampionBox() - Returning");
		logger.debug("getChampionBox() - Returning: {}", championBox);
		return championBox;
	}

	private JTabbedPane getItemsPane() {
		logger.trace("getItemsPane() - Entering");
		if (itemsPane == null) {
			itemsPane = new JTabbedPane();
			itemsPane.setTabPlacement(JTabbedPane.BOTTOM);
			itemsPane.addTab("Start items", getStartItemsPanel());
			itemsPane.addTab("End items", getEndItemsPanel());
			itemsPane.addTab("Summoner Spells", getSpellsPanel());
		}
		logger.trace("getItemsPane() - Returning");
		logger.debug("getItemsPane() - Returning: {}", itemsPane);
		return itemsPane;
	}

	private NewEntryItemBuildPanel getStartItemsPanel() {
		logger.trace("getStartItemsPanel() - Entering");
		if (startItemsPanel == null) {
			startItemsPanel = new NewEntryItemBuildPanel();
		}
		logger.trace("getStartItemsPanel() - Returning");
		logger.debug("getStartItemsPanel() - Returning: {}", startItemsPanel);
		return startItemsPanel;
	}

	private NewEntryItemBuildPanel getEndItemsPanel() {
		logger.trace("getEndItemsPanel() - Entering");
		if (endItemsPanel == null) {
			endItemsPanel = new NewEntryItemBuildPanel();
		}
		logger.trace("getEndItemsPanel() - Returning");
		logger.debug("getEndItemsPanel() - Returning: {}", endItemsPanel);
		return endItemsPanel;
	}

	private JPanel getSpellsPanel() {
		logger.trace("getSpellsPanel() - Entering");
		if (spellsPanel == null) {
			spellsPanel = new JPanel(new GridBagLayout());
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 0;
			spellsPanel.add(getSpell1Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			spellsPanel.add(getSpell1Box(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 2;
			spellsPanel.add(getSpell2Label(), constraints);

			constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 3;
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 1;
			spellsPanel.add(getSpell2Box(), constraints);
		}
		logger.trace("getSpellsPanel() - Returning");
		logger.debug("getSpellsPanel() - Returning: {}", spellsPanel);
		return spellsPanel;
	}

	private JLabel getSpell1Label() {
		logger.trace("getSpell1Label() - Entering");
		if (spell1Label == null) {
			spell1Label = new JLabel("Summoner Spell 1");
		}
		logger.trace("getSpell1Label() - Returning");
		logger.debug("getSpell1Label() - Returning: {}", spell1Label);
		return spell1Label;
	}

	private SummonerSpellComboBox getSpell1Box() {
		logger.trace("getSpell1Box() - Entering");
		if (spell1Box == null) {
			spell1Box = new SummonerSpellComboBox(
					new DefaultComboBoxModel<SummonerSpell>(
							allSpells.toArray(new SummonerSpell[] {})));
		}
		logger.trace("getSpell1Box() - Returning");
		logger.debug("getSpell1Box() - Returning: {}", spell1Box);
		return spell1Box;
	}

	private JLabel getSpell2Label() {
		logger.trace("getSpell2Label() - Entering");
		if (spell2Label == null) {
			spell2Label = new JLabel("Summoner Spell 2");
		}
		logger.trace("getSpell2Label() - Returning");
		logger.debug("getSpell2Label() - Returning: {}", spell2Label);
		return spell2Label;
	}

	private SummonerSpellComboBox getSpell2Box() {
		logger.trace("getSpell2Box() - Entering");
		if (spell2Box == null) {
			spell2Box = new SummonerSpellComboBox(
					new DefaultComboBoxModel<SummonerSpell>(
							allSpells.toArray(new SummonerSpell[] {})));
		}
		logger.trace("getSpell2Box() - Returning");
		logger.debug("getSpell2Box() - Returning: {}", spell2Box);
		return spell2Box;
	}

	public void setPossibleChampions(List<Champion> champs) {
		logger.trace("setPossibleChampions() - Entering");
		logger.debug("setPossibleChampions() - Parameter: {}", champs);
		getChampionBox().setModel(
				new DefaultComboBoxModel<Champion>(champs
						.toArray(new Champion[] {})));
		logger.trace("setPossibleChampions() - Leaving");
	}

	public void setChampion(Champion champ) {
		logger.trace("setChampion() - Entering");
		logger.debug("setChampion() - Parameter: {}", champ);
		getChampionBox().setSelectedItem(champ);
		logger.trace("setChampion() - Leaving");
	}

	public Champion getChampion() {
		logger.trace("getChampion() - Entering");
		Champion returnValue = getChampionBox().getItemAt(
				getChampionBox().getSelectedIndex());
		logger.trace("getChampion() - Returning");
		logger.debug("getChampion() - Returning: {}", returnValue);
		return returnValue;
	}

	public List<MatchupItem> getStartItems() {
		logger.trace("getStartItems() - Entering");
		List<MatchupItem> returnValue = getStartItemsPanel().getItems();
		logger.trace("getStartItems() - Returning");
		logger.debug("getStartItems() - Returning: {}", returnValue);
		return returnValue;
	}

	public List<MatchupItem> getEndItems() {
		logger.trace("getEndItems() - Entering");
		List<MatchupItem> returnValue = getEndItemsPanel().getItems();
		logger.trace("getEndItems() - Returning");
		logger.debug("getEndItems() - Returning: {}", returnValue);
		return returnValue;
	}

	public void clear() {
		logger.trace("clear() - Entering");
		getStartItemsPanel().clear();
		getEndItemsPanel().clear();
		getSpell1Box().setSelectedItem(0);
		getSpell2Box().setSelectedItem(0);
		getItemsPane().setSelectedIndex(0);
		logger.trace("clear() - Leaving");
	}

	public void fillFields(Champion champ, List<MatchupItem> startItems,
			List<MatchupItem> endItems, SummonerSpell spell1,
			SummonerSpell spell2) {
		logger.trace("fillFields() - Entering");
		logger.debug("fillFields() - Parameter: {}, {}, {}, {}, {}", champ,
				startItems, endItems, spell1, spell2);
		getStartItemsPanel().setItems(startItems);
		getEndItemsPanel().setItems(endItems);
		getSpell1Box().setSelectedItem(spell1);
		getSpell2Box().setSelectedItem(spell2);
		logger.trace("fillFields() - Leaving");
	}

	public SummonerSpell getSpell1() {
		logger.trace("getSpell1() - Entering");
		SummonerSpell returnValue = getSpell1Box().getItemAt(
				getSpell1Box().getSelectedIndex());
		logger.trace("getSpell1() - Returning");
		logger.debug("getSpell1() - Returning: {}", returnValue);
		return returnValue;
	}

	public SummonerSpell getSpell2() {
		logger.trace("getSpell2() - Entering");
		SummonerSpell returnValue = getSpell2Box().getItemAt(
				getSpell2Box().getSelectedIndex());
		logger.trace("getSpell2() - Returning");
		logger.debug("getSpell2() - Returning: {}", returnValue);
		return returnValue;
	}
}
