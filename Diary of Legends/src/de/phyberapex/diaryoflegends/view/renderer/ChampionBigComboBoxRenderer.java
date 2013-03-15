package de.phyberapex.diaryoflegends.view.renderer;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import de.phyberapex.diaryoflegends.model.Champion;

public class ChampionBigComboBoxRenderer extends JLabel implements
		ListCellRenderer<Champion> {

	private static final long serialVersionUID = -2728171812399421250L;

	public ChampionBigComboBoxRenderer() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing
	 * .JList, java.lang.Object, int, boolean, boolean)
	 */
	@Override
	public Component getListCellRendererComponent(
			JList<? extends Champion> list, Champion value, int index,
			boolean isSelected, boolean cellHasFocus) {
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		setOpaque(true);
		setIcon(value.getResizeIcon(60));
		setText(value.getName());
		return this;
	}

}
