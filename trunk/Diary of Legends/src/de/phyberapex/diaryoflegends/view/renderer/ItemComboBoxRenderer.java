package de.phyberapex.diaryoflegends.view.renderer;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import de.phyberapex.diaryoflegends.extra.ImageIconFactory;
import de.phyberapex.diaryoflegends.model.Item;

public class ItemComboBoxRenderer extends JLabel implements
		ListCellRenderer<Item> {

	private static final long serialVersionUID = -2728171812399421250L;
	private static ImageIcon defaultIcon = ImageIconFactory.resizeImageIcon(
			ImageIconFactory
					.createImageIconFromResourePath("img/empty_60x60.png"), 30,
			30);

	public ItemComboBoxRenderer() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing
	 * .JList, java.lang.Object, int, boolean, boolean)
	 */
	@Override
	public Component getListCellRendererComponent(JList<? extends Item> list,
			Item value, int index, boolean isSelected, boolean cellHasFocus) {
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		setOpaque(true);
		if (value.getIcon() == null) {
			setIcon(defaultIcon);
		} else {
			setIcon(ImageIconFactory.resizeImageIcon(value.getIcon(), 30, 30));
		}
		setText(value.getName());
		return this;
	}

}
