package de.phyberapex.diaryoflegends.view;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;

public class ChampionTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 6832182630678830128L;
	private List<Champion> champs;
	private String[] columnNames = new String[] {"Picture", "Name" };

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

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Champion c = champs.get(rowIndex);
		switch (columnIndex) {
		case 1:
			c.setName((String) aValue);
			break;
		}
		ChampionUtil.saveChampion(c);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return false;
		}
		return true;
	}
	
	@Override
	public String getColumnName(int column) {
	    return columnNames[column];
	}
}
