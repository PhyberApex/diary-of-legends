package de.phyberapex.diaryoflegends.view.dialoge.comboBox;

import javax.accessibility.Accessible;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.plaf.basic.ComboPopup;

import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.view.renderer.ChampionComboBoxRenderer;

public class ChampionComboBox extends JComboBox<Champion> {

	private static final long serialVersionUID = 1L;

	public ChampionComboBox() {
		super();
		this.setRenderer(new ChampionComboBoxRenderer());
		setPrototype();
	}

	public ChampionComboBox(DefaultComboBoxModel<Champion> model) {
		this();
		setModel(model);
	}

	private void setPrototype() {
		setPrototypeDisplayValue(new Champion(0, "Just some puffer text", null));
		Accessible a = getUI().getAccessibleChild(this, 0);
		if (a instanceof javax.swing.plaf.basic.ComboPopup) {
			@SuppressWarnings("unchecked")
			JList<Champion> popupList = ((ComboPopup) a).getList();
			popupList.setPrototypeCellValue(getPrototypeDisplayValue());
		}
	}

}
