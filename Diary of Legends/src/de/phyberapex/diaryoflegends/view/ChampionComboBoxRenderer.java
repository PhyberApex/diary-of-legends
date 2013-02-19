package de.phyberapex.diaryoflegends.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import de.phyberapex.diaryoflegends.extra.ImageIconFactory;
import de.phyberapex.diaryoflegends.model.Champion;

public class ChampionComboBoxRenderer extends JPanel implements
		ListCellRenderer<Champion> {

	private static final long serialVersionUID = -2728171812399421250L;
	private JPanel panel;

	public ChampionComboBoxRenderer() {
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
		panel.add(
				new JLabel(ImageIconFactory.resizeImageIcon(value.getIcon(),
						30, 30)), constraints);
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
