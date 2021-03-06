package de.phyberapex.diaryoflegends.view.dialoge.comboBox;

import javax.accessibility.Accessible;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.plaf.basic.ComboPopup;

import de.phyberapex.diaryoflegends.model.SummonerSpell;
import de.phyberapex.diaryoflegends.view.renderer.SpellComboBoxRenderer;

public class SummonerSpellComboBox extends JComboBox<SummonerSpell> {

	private static final long serialVersionUID = 1L;

	public SummonerSpellComboBox(DefaultComboBoxModel<SummonerSpell> model) {
		super(model);
		this.setRenderer(new SpellComboBoxRenderer());
		setPrototype();
	}

	private void setPrototype() {
		setPrototypeDisplayValue(new SummonerSpell(0,
				"just some puffer textxxxxx"));
		Accessible a = getUI().getAccessibleChild(this, 0);
		if (a instanceof javax.swing.plaf.basic.ComboPopup) {
			@SuppressWarnings("unchecked")
			JList<SummonerSpell> popupList = ((ComboPopup) a).getList();
			popupList.setPrototypeCellValue(getPrototypeDisplayValue());
		}
	}

}
