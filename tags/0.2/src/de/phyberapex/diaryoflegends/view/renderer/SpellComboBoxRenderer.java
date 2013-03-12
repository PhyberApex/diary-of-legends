package de.phyberapex.diaryoflegends.view.renderer;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import de.phyberapex.diaryoflegends.model.SummonerSpell;

public class SpellComboBoxRenderer extends JLabel implements
		ListCellRenderer<SummonerSpell> {

	private static final long serialVersionUID = -2728171812399421250L;

	public SpellComboBoxRenderer() {
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
			JList<? extends SummonerSpell> list, SummonerSpell value,
			int index, boolean isSelected, boolean cellHasFocus) {
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		setOpaque(true);
		if (value == null) {
			value = new SummonerSpell(0, "no spell");
		}
		setIcon(value.getResizeIcon(40));
		setText(value.getName());
		return this;
	}
}
