package de.phyberapex.diaryoflegends.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.ExitAction;
import de.phyberapex.diaryoflegends.export.ExportDolexAction;
import de.phyberapex.diaryoflegends.export.ImportDolexAction;
import de.phyberapex.diaryoflegends.export.ImportRoflAction;
import de.phyberapex.diaryoflegends.view.dialoge.NewEntryDialoge;

public class MenuBarView extends JMenuBar implements View {

	private static final long serialVersionUID = 4015087253364502577L;
	private JMenu fileMenu;
	private JMenuItem newEntryItem;
	private JMenuItem importDolexItem;
	private JMenuItem importRoflItem;
	private JMenuItem exportItem;
	private JMenuItem exitItem;
	private JMenu aboutMenu;
	private JMenuItem helpItem;
	private JMenuItem updateItem;
	private static Logger logger = LogManager.getLogger(MenuBarView.class
			.getName());

	public MenuBarView() {
		logger.trace("MenuBarView() - Entering");
		this.add(getFileMenu());
		this.add(getAboutMenu());
		logger.trace("MenuBarView() - Leaving");
	}

	private JMenu getFileMenu() {
		logger.trace("getFileMenu() - Entering");
		logger.debug("fileMenu currently {}", fileMenu);
		if (this.fileMenu == null) {
			logger.debug("Creating a new JMenu object");
			this.fileMenu = new JMenu("File");
			this.fileMenu.add(getNewEntryItem());
			this.fileMenu.add(new JSeparator());
			this.fileMenu.add(getImportDolexItem());
			this.fileMenu.add(getImportRoflItem());
			this.fileMenu.add(getExportItem());
			this.fileMenu.add(new JSeparator());
			this.fileMenu.add(getExitItem());
		}
		logger.trace("getFileMenu() - Returning");
		logger.debug("Returned {}", fileMenu);
		return fileMenu;
	}

	private JMenuItem getNewEntryItem() {
		logger.trace("getNewEntryItem() - Entering");
		logger.debug("newEntryItem currently {}", newEntryItem);
		if (this.newEntryItem == null) {
			logger.debug("Creating a new JMenuItem object");
			this.newEntryItem = new JMenuItem("Add a new entry");
			this.newEntryItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(NewEntryDialoge.getInstance());
				}
			});
		}
		logger.trace("getNewEntryItem() - Returning");
		logger.debug("Returned {}", newEntryItem);
		return newEntryItem;
	}

	private JMenuItem getImportDolexItem() {
		logger.trace("getImportDolexItem() - Entering");
		if (this.importDolexItem == null) {
			this.importDolexItem = new JMenuItem("Import *.dolex...");
			this.importDolexItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JFileChooser fc = new JFileChooser(System
							.getProperty("user.dir"));
					fc.setFileFilter(new FileFilter() {

						@Override
						public String getDescription() {
							return "DOLEX files";
						}

						@Override
						public boolean accept(File arg0) {
							String extension = "";

							int i = arg0.getName().lastIndexOf('.');
							if (i > 0) {
								extension = arg0.getName().substring(i + 1);
							}
							if (extension.equals("dolex") || arg0.isDirectory()) {
								return true;
							} else {
								return false;
							}
						}
					});
					int ok = fc.showOpenDialog(MainView.getInstance());
					if (ok == JFileChooser.APPROVE_OPTION) {
						ImportDolexAction imp = new ImportDolexAction(fc
								.getSelectedFile());
						Thread tr = new Thread(imp);
						tr.start();
					}
				}
			});
		}
		logger.trace("getImportDolexItem() - Returning");
		logger.debug("getImportDolexItem() - Returning {}", importDolexItem);
		return importDolexItem;
	}

	private JMenuItem getImportRoflItem() {
		logger.trace("getImportRoflItem() - Entering");
		if (this.importRoflItem == null) {
			this.importRoflItem = new JMenuItem("Import *.rofl...");
			this.importRoflItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JFileChooser fc = new JFileChooser();
					fc.setFileFilter(new FileFilter() {

						@Override
						public String getDescription() {
							return "rofl files";
						}

						@Override
						public boolean accept(File arg0) {
							String extension = "";

							int i = arg0.getName().lastIndexOf('.');
							if (i > 0) {
								extension = arg0.getName().substring(i + 1);
							}
							if (extension.equals("rofl") || arg0.isDirectory()) {
								return true;
							} else {
								return false;
							}
						}
					});
					int ok = fc.showOpenDialog(MainView.getInstance());
					if (ok == JFileChooser.APPROVE_OPTION) {
						ImportRoflAction imp = new ImportRoflAction(fc
								.getSelectedFile());
						Thread tr = new Thread(imp);
						tr.start();
					}
				}
			});
		}
		logger.trace("getImportRoflItem() - Returning");
		logger.debug("getImportRoflItem() - Returning {}", importRoflItem);
		return importRoflItem;
	}

	private JMenuItem getExportItem() {
		logger.trace("getExportItem() - Entering");
		if (this.exportItem == null) {
			this.exportItem = new JMenuItem("Export");
			this.exportItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					Thread tr = new Thread(new ExportDolexAction());
					tr.start();
				}
			});
		}
		logger.trace("getExportItem() - Returning");
		logger.debug("getExportItem() - Returning: {}", exportItem);
		return exportItem;
	}

	private JMenuItem getExitItem() {
		logger.trace("getExitItem() - Entering");
		logger.debug("exitItem currently {}", exitItem);
		if (this.exitItem == null) {
			logger.debug("Creating a new JMenuItem object");
			this.exitItem = new JMenuItem("Exit");
			this.exitItem.addActionListener(ExitAction.getInstance());
		}
		logger.trace("getExitItem() - Returning");
		logger.debug("Returned {}", exitItem);
		return exitItem;
	}

	private JMenu getAboutMenu() {
		logger.trace("getAboutMenu() - Entering");
		logger.debug("aboutMenu currently {}", aboutMenu);
		if (this.aboutMenu == null) {
			logger.debug("Creating a new JMenu object");
			this.aboutMenu = new JMenu("?");
			this.aboutMenu.add(getHelpItem());
			this.aboutMenu.add(getUpdateItem());
		}
		logger.trace("getAboutMenu() - Returning");
		logger.debug("Returned {}", aboutMenu);
		return aboutMenu;
	}

	private JMenuItem getHelpItem() {
		logger.trace("getHelpItem() - Entering");
		logger.debug("helpItem currently {}", helpItem);
		if (this.helpItem == null) {
			logger.debug("Creating a new JMenuItem object");
			this.helpItem = new JMenuItem("Help");
		}
		logger.trace("getHelpItem() - Returning");
		logger.debug("Returned {}", helpItem);
		return helpItem;
	}

	private JMenuItem getUpdateItem() {
		logger.trace("getUpdateItem() - Entering");
		logger.debug("updateItem currently {}", updateItem);
		if (this.updateItem == null) {
			logger.debug("Creating a new JMenuItem object");
			this.updateItem = new JMenuItem("Check for Updates");
			this.updateItem.setEnabled(false);
		}
		logger.trace("getUpdateItem() - Returning");
		logger.debug("Returned {}", updateItem);
		return updateItem;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}
}
