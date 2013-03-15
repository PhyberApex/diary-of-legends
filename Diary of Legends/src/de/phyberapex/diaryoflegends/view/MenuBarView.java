package de.phyberapex.diaryoflegends.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.action.ExitAction;
import de.phyberapex.diaryoflegends.action.ExportDolexAction;
import de.phyberapex.diaryoflegends.action.ImportDolexAction;
import de.phyberapex.diaryoflegends.action.ImportLastMatchAction;
import de.phyberapex.diaryoflegends.action.ImportRoflAction;
import de.phyberapex.diaryoflegends.action.UpdateAction;
import de.phyberapex.diaryoflegends.base.Config;
import de.phyberapex.diaryoflegends.view.dialoge.AboutDialog;
import de.phyberapex.diaryoflegends.view.dialoge.NewChampionDialog;
import de.phyberapex.diaryoflegends.view.dialoge.NewEntryDialog;
import de.phyberapex.diaryoflegends.view.panel.APIKeyViewPanel;
import de.phyberapex.diaryoflegends.view.panel.SummonerNamePanel;

public class MenuBarView extends JMenuBar implements View {

	private static final long serialVersionUID = 4015087253364502577L;
	private JMenu fileMenu;
	private JMenuItem newEntryItem;
	private JMenuItem changeNameItem;
	private JMenuItem changeAPIKeyItem;
	private JMenuItem importDolexItem;
	private JMenuItem importRoflItem;
	private JMenuItem importLastMatchItem;
	private JMenuItem exportItem;
	private JMenuItem exitItem;
	private JMenu aboutMenu;
	private JMenuItem helpItem;
	private JMenuItem updateItem;
	private JCheckBoxMenuItem autoUpdateItem;
	private JMenuItem addChampionItem;
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
			this.fileMenu.add(getChangeNameItem());
			this.fileMenu.add(getChangeAPIKeyItem());
			this.fileMenu.add(new JSeparator());
			this.fileMenu.add(getImportDolexItem());
			this.fileMenu.add(getImportRoflItem());
			this.fileMenu.add(getImportLastMatchItem());
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
					SwingUtilities.invokeLater(NewEntryDialog.getInstance());
				}
			});
		}
		logger.trace("getNewEntryItem() - Returning");
		logger.debug("Returned {}", newEntryItem);
		return newEntryItem;
	}

	private JMenuItem getChangeNameItem() {
		logger.trace("getChangeNameItem() - Entering");
		if (this.changeNameItem == null) {
			this.changeNameItem = new JMenuItem("Change summoner name");
			this.changeNameItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					SummonerNamePanel currSumView = SummonerNamePanel
							.getInstance();
					Object[] options = { "OK" };
					int ok = JOptionPane.showOptionDialog(null, currSumView,
							"Enter your Summoner name",
							JOptionPane.WARNING_MESSAGE,
							JOptionPane.INFORMATION_MESSAGE, null, options,
							"OK");
					logger.debug("Entered text was '{}'",
							currSumView.getSummonerName());
					logger.debug(
							"Button pressed was '{}' ({} = OK, {} = CLOSED)",
							ok, JOptionPane.OK_OPTION,
							JOptionPane.CLOSED_OPTION);
					Config.getInstance().setProperty("SUMMONER_NAME",
							currSumView.getSummonerName());
					Config.getInstance().saveChanges();
					MainView.getInstance().setStatusText(
							"Summoner name changed in "
									+ currSumView.getSummonerName());
					Config.getInstance().setProperty("SUMMONER_ID", "0");
					Config.getInstance().setProperty("ACCOUNT_ID", "0");
					Config.getInstance().saveChanges();
					MainView.getInstance().setSummonerInfo(
							Config.getInstance().getCurrentSummoner()
									.getSummonerInfoString());
					refresh();
				}
			});
		}
		logger.trace("getChangeNameItem() - Returning");
		logger.debug("getChangeNameItem() - Returning: {}", changeNameItem);
		return changeNameItem;
	}

	private JMenuItem getChangeAPIKeyItem() {
		logger.trace("getChangeAPIKeyItem() - Entering");
		if (this.changeAPIKeyItem == null) {
			this.changeAPIKeyItem = new JMenuItem("Change API-Key");
			this.changeAPIKeyItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					APIKeyViewPanel apiKeyView = APIKeyViewPanel.getInstance();
					Object[] options = { "OK" };
					int ok = JOptionPane.showOptionDialog(null, apiKeyView,
							"Enter your Elophant API-Key",
							JOptionPane.WARNING_MESSAGE,
							JOptionPane.INFORMATION_MESSAGE, null, options,
							"OK");
					logger.debug("Entered text was '{}'",
							apiKeyView.getAPIKey());
					logger.debug(
							"Button pressed was '{}' ({} = OK, {} = CLOSED)",
							ok, JOptionPane.OK_OPTION,
							JOptionPane.CLOSED_OPTION);
					if (ok == JOptionPane.OK_OPTION) {
						Config.getInstance().setProperty("API_KEY",
								apiKeyView.getAPIKey());
					}
					Config.getInstance().saveChanges();
				}
			});
		}
		logger.trace("getChangeAPIKeyItem() - Returning");
		logger.debug("getChangeAPIKeyItem() - Returning: {}", changeAPIKeyItem);
		return changeAPIKeyItem;
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
					fc.setAcceptAllFileFilterUsed(false);
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
			this.importRoflItem
					.setToolTipText("Only available if you entered your summonername");
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

	private JMenuItem getImportLastMatchItem() {
		logger.trace("getImportLastMatchItem() - Entering");
		if (this.importLastMatchItem == null) {
			this.importLastMatchItem = new JMenuItem(
					"Import last 10 matches...");
			this.importLastMatchItem
					.setToolTipText("Only available if you entered your summonername and your summoner id and account id have been fetched(online)");
			this.importLastMatchItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					ImportLastMatchAction imp = new ImportLastMatchAction();
					Thread tr = new Thread(imp);
					tr.start();
				}
			});
		}
		logger.trace("getImportLastMatchItem() - Returning");
		logger.debug("getImportLastMatchItem() - Returning {}",
				importLastMatchItem);
		return importLastMatchItem;
	}

	private JMenuItem getExportItem() {
		logger.trace("getExportItem() - Entering");
		if (this.exportItem == null) {
			this.exportItem = new JMenuItem("Export...");
			this.exportItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser fc = new JFileChooser(System
							.getProperty("user.dir"));
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int ok = fc.showOpenDialog(MainView.getInstance());
					if (ok == JFileChooser.APPROVE_OPTION) {
						Thread tr = new Thread(new ExportDolexAction(fc
								.getSelectedFile()));
						tr.start();
					}
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
			this.aboutMenu.add(getAutoUpdateItem());
			this.aboutMenu.add(new JSeparator());
			this.aboutMenu.add(getAddChampion());
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
			this.helpItem = new JMenuItem("About");
			this.helpItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					AboutDialog.getInstance().setVisible(true);
				}
			});
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
			this.updateItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Thread tr = new Thread(new UpdateAction());
					tr.start();
				}
			});
		}
		logger.trace("getUpdateItem() - Returning");
		logger.debug("Returned {}", updateItem);
		return updateItem;
	}

	public JCheckBoxMenuItem getAutoUpdateItem() {
		logger.trace("getAutoUpdateItem() - Entering");
		if (autoUpdateItem == null) {
			autoUpdateItem = new JCheckBoxMenuItem(
					"Update champions/items on startup");
			autoUpdateItem.setSelected(Config.getInstance()
					.getProperty("AUTO_UPDATE").equals("1"));
			autoUpdateItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Config conf = Config.getInstance();
					if (!autoUpdateItem.isSelected()) {
						conf.setProperty("AUTO_UPDATE", "0");
					} else {
						conf.setProperty("AUTO_UPDATE", "1");
					}
					conf.saveChanges();
				}
			});
		}
		logger.trace("getAutoUpdateItem() - Returning");
		logger.debug("getAutoUpdateItem() - Returning: {}", autoUpdateItem);
		return autoUpdateItem;
	}

	private JMenuItem getAddChampion() {
		logger.trace("getAddChampion() - Entering");
		if (this.addChampionItem == null) {
			this.addChampionItem = new JMenuItem("Add a new champion...");
			this.addChampionItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new NewChampionDialog().setVisible(true);
				}
			});
		}
		logger.trace("getAddChampion() - Returning");
		logger.debug("getAddChampion() - Returning: {}", addChampionItem);
		return addChampionItem;
	}

	@Override
	public void refresh() {
		boolean summNameSet = Config.getInstance().getProperty("SUMMONER_NAME") != null;
		boolean regionSet = Config.getInstance().getProperty("REGION") != null;
		boolean summIdSet = Config.getInstance().getProperty("SUMMONER_ID") != null;
		boolean accountIdSet = Config.getInstance().getProperty("ACCOUNT_ID") != null;
		this.importRoflItem.setEnabled(summNameSet);
		this.importLastMatchItem.setEnabled(summNameSet && regionSet
				&& summIdSet && accountIdSet);
	}
}
