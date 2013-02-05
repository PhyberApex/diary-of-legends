package de.phyberapex.diaryoflegends.view;

import java.util.Observable;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.ExitAction;

public class MenuBarView extends JMenuBar implements View{

	private static final long serialVersionUID = 4015087253364502577L;
	private JMenu fileMenu;
	private JMenuItem newEntryItem;
	private JMenuItem newChampItem;
	private JMenuItem newItemItem;
	private JMenuItem exitItem;
	private JMenu editMenu;
	private JMenuItem editEntryItem;
	private JMenuItem editChampItem;
	private JMenuItem editItemItem;
	private static Logger logger = LogManager.getLogger(MenuBarView.class.getName());

	public MenuBarView(){
		logger.trace("MenuBarView() - Entering");
		this.add(getFileMenu());
		this.add(getEditMenu());
		logger.trace("MenuBarView() - Leaving");
	}
	
	private JMenu getFileMenu() {
		logger.trace("getFileMenu() - Entering");
		logger.debug("fileMenu currently {}", fileMenu);
		if(this.fileMenu == null){
			logger.debug("Creating a new JMenu object");
			this.fileMenu = new JMenu("File");
			this.fileMenu.add(getNewEntryItem());
			this.fileMenu.add(getNewChampItem());
			this.fileMenu.add(getNewItemItem());
			this.fileMenu.add(getExitItem());
		}
		logger.trace("getFileMenu() - Returning");
		logger.debug("Returned {}", fileMenu);
		return fileMenu;
	}
	
	private JMenuItem getNewEntryItem() {
		logger.trace("getNewEntryItem() - Entering");
		logger.debug("newEntryItem currently {}", newEntryItem);
		if(this.newEntryItem == null){
			logger.debug("Creating a new JMenuItem object");
			this.newEntryItem = new JMenuItem("Add a new entry");
		}
		logger.trace("getNewEntryItem() - Returning");
		logger.debug("Returned {}", newEntryItem);
		return newEntryItem;
	}
	
	private JMenuItem getNewChampItem() {
		logger.trace("getNewChampItem() - Entering");
		logger.debug("newChampItem currently {}", newChampItem);
		if(this.newChampItem == null){
			logger.debug("Creating a new JMenuItem object");
			this.newChampItem = new JMenuItem("Add a new champion");
		}
		logger.trace("getNewChampItem() - Returning");
		logger.debug("Returned {}", newChampItem);
		return newChampItem;
	}
	
	private JMenuItem getNewItemItem() {
		logger.trace("getNewItemItem() - Entering");
		logger.debug("newItemItem currently {}", newItemItem);
		if(this.newItemItem == null){
			logger.debug("Creating a new JMenuItem object");
			this.newItemItem = new JMenuItem("Add a new item");
		}
		logger.trace("getNewItemItem() - Returning");
		logger.debug("Returned {}", newItemItem);
		return newItemItem;
	}
	
	private JMenuItem getExitItem() {
		logger.trace("getExitItem() - Entering");
		logger.debug("exitItem currently {}", exitItem);
		if(this.exitItem == null){
			logger.debug("Creating a new JMenuItem object");
			this.exitItem = new JMenuItem("Exit");
			this.exitItem.addActionListener(ExitAction.getInstance());
		}
		logger.trace("getExitItem() - Returning");
		logger.debug("Returned {}", exitItem);
		return exitItem;
	}
	
	private JMenu getEditMenu() {
		logger.trace("getEditMenu() - Entering");
		logger.debug("editMenu currently {}", editMenu);
		if(this.editMenu == null){
			logger.debug("Creating a new JMenu object");
			this.editMenu = new JMenu("Edit");
			this.editMenu.add(getEditEntryItem());
			this.editMenu.add(getEditChampItem());
			this.editMenu.add(getEditItemItem());
		}
		logger.trace("getEditMenu() - Returning");
		logger.debug("Returned {}", editMenu);
		return editMenu;
	}

	private JMenuItem getEditEntryItem() {
		logger.trace("getEditEntryItem() - Entering");
		logger.debug("editEntryItem currently {}", editEntryItem);
		if(this.editEntryItem == null){
			logger.debug("Creating a new JMenuItem object");
			this.editEntryItem = new JMenuItem("Edit a entry");
		}
		logger.trace("getEditEntryItem() - Returning");
		logger.debug("Returned {}", editEntryItem);
		return editEntryItem;
	}
	
	private JMenuItem getEditChampItem() {
		logger.trace("getEditChampItem() - Entering");
		logger.debug("editChampItem currently {}", editChampItem);
		if(this.editChampItem == null){
			logger.debug("Creating a new JMenuItem object");
			this.editChampItem = new JMenuItem("Edit a champion");
		}
		logger.trace("getEditChampItem() - Returning");
		logger.debug("Returned {}", editChampItem);
		return editChampItem;
	}
	
	private JMenuItem getEditItemItem() {
		logger.trace("getEditItemItem() - Entering");
		logger.debug("editItemItem currently {}", editItemItem);
		if(this.editItemItem == null){
			logger.debug("Creating a new JMenuItem object");
			this.editItemItem = new JMenuItem("Edit a item");
		}
		logger.trace("getEditItemItem() - Returning");
		logger.debug("Returned {}", editItemItem);
		return editItemItem;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
