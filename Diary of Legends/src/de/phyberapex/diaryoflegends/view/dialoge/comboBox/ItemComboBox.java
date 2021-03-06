package de.phyberapex.diaryoflegends.view.dialoge.comboBox;

import javax.accessibility.Accessible;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.plaf.basic.ComboPopup;

import de.phyberapex.diaryoflegends.model.Item;
import de.phyberapex.diaryoflegends.view.renderer.ItemComboBoxRenderer;

public class ItemComboBox extends JComboBox<Item> {

	private static final long serialVersionUID = 1L;

	public ItemComboBox(DefaultComboBoxModel<Item> model) {
		super(model);
		this.setRenderer(new ItemComboBoxRenderer());
		setPrototype();
	}

	private void setPrototype() {
		setPrototypeDisplayValue(new Item(0, "just some puffer text"));
		Accessible a = getUI().getAccessibleChild(this, 0);
		if (a instanceof javax.swing.plaf.basic.ComboPopup) {
			@SuppressWarnings("unchecked")
			JList<Item> popupList = ((ComboPopup) a).getList();
			popupList.setPrototypeCellValue(getPrototypeDisplayValue());
		}
	}

}
