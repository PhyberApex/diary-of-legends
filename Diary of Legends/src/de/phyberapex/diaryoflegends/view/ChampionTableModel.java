package de.phyberapex.diaryoflegends.view;

import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.Model;

public class ChampionTableModel extends AbstractTableModel {

	public List<Champion> champs;

	public ChampionTableModel(List<Champion> models) {
		this.champs = models;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return champs.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return champs.get(rowIndex).getIcon();
		case 1:
			return champs.get(rowIndex).getName();
		default:
			return null;
		}
	}

}
