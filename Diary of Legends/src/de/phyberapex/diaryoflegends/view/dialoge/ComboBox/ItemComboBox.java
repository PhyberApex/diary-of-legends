package de.phyberapex.diaryoflegends.view.dialoge.ComboBox;

import javax.accessibility.Accessible;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
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
		setPrototypeDisplayValue(new Item(0, "just some puffer text", 0, null));
		Accessible a = getUI().getAccessibleChild(this, 0);
		if (a instanceof javax.swing.plaf.basic.ComboPopup) {
			JList<Item> popupList = ((javax.swing.plaf.basic.ComboPopup) a)
					.getList();
			popupList.setPrototypeCellValue(getPrototypeDisplayValue());
		}
	}

}
