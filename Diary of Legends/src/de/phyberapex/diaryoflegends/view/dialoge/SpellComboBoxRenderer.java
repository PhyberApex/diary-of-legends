package de.phyberapex.diaryoflegends.view.dialoge;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import de.phyberapex.diaryoflegends.extra.ImageIconFactory;
import de.phyberapex.diaryoflegends.model.SummonerSpell;

public class SpellComboBoxRenderer extends JPanel implements
		ListCellRenderer<SummonerSpell> {

	private static final long serialVersionUID = -2728171812399421250L;
	private JPanel panel;

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
	public Component getListCellRendererComponent(JList<? extends SummonerSpell> list,
			SummonerSpell value, int index, boolean isSelected, boolean cellHasFocus) {
		panel = new JPanel(new GridBagLayout());
		if (isSelected) {
			panel.setBackground(list.getSelectionBackground());
			panel.setForeground(list.getSelectionForeground());
		} else {
			panel.setBackground(list.getBackground());
			panel.setForeground(list.getForeground());
		}
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		ImageIcon icon = value.getIcon();
		if (icon == null) {
			icon = ImageIconFactory
					.createImageIconFromResourePath("img/empty_60x60.png");
		}
		panel.add(new JLabel(ImageIconFactory.resizeImageIcon(icon, 50, 50)),
				constraints);
		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		constraints.anchor = GridBagConstraints.WEST;
		panel.add(new JLabel(value.getName()), constraints);
		return panel;
	}

}
