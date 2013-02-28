package de.phyberapex.diaryoflegends.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.TableRowSorter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.controller.MatchupController;
import de.phyberapex.diaryoflegends.model.GameResult;
import de.phyberapex.diaryoflegends.model.Matchup;
import de.phyberapex.diaryoflegends.model.MatchupResult;
import de.phyberapex.diaryoflegends.view.dialoge.MatchupDetailDialoge;
import de.phyberapex.diaryoflegends.view.dialoge.NewEntryDialoge;
import de.phyberapex.diaryoflegends.view.model.MatchupTableModel;
import de.phyberapex.diaryoflegends.view.renderer.MatchupTableRenderer;

public class MatchupView extends JPanel implements View {
	private static final long serialVersionUID = -8456209180736169342L;
	private MatchupController controller;
	private JScrollPane matchupTablePane;
	private JTable matchupTable;
	private JPanel legendPanel;
	private TableRowSorter<MatchupTableModel> sorter = new TableRowSorter<>();
	private static Logger logger = LogManager.getLogger(MatchupView.class
			.getName());

	public MatchupView(MatchupController controller) {
		logger.trace("MatchupView() - Entering");
		logger.debug("MatchupView() - Parameter: {}", controller);
		this.controller = controller;
		createGUI();
		logger.trace("MatchupView() - Leaving");
	}

	/**
	 * Creates the GUI
	 */
	private void createGUI() {
		logger.trace("createGUI() - Entering");
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.add(getMatchupTablePane(), constraints);

		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(0, 50, 0, 50);
		this.add(getLegendPanel(), constraints);
		logger.trace("createGUI() - Leaving");
	}

	/**
	 * Returns the matchupTablePane
	 * 
	 * @return {@link JScrollPane} The champTablePane
	 */
	private JScrollPane getMatchupTablePane() {
		logger.trace("getMatchupTablePane() - Entering");
		if (matchupTablePane == null) {
			matchupTablePane = new JScrollPane(getMatchupTable());
		}
		logger.trace("getMatchupTablePane() - Returning");
		logger.debug("getMatchupTablePane() - Returning: {}", matchupTablePane);
		return matchupTablePane;
	}

	/**
	 * Returns the matchupTable
	 * 
	 * @return {@link JTable} The championTable
	 */
	private JTable getMatchupTable() {
		logger.trace("getMatchupTable() - Entering");
		if (matchupTable == null) {
			matchupTable = new JTable();
			MatchupTableModel m = new MatchupTableModel(
					controller.getMatchups());
			matchupTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			matchupTable.getTableHeader().setReorderingAllowed(false);
			matchupTable.getTableHeader().setResizingAllowed(false);
			matchupTable.setModel(m);
			matchupTable.setDefaultRenderer(Object.class,
					new MatchupTableRenderer());
			sorter = new TableRowSorter<MatchupTableModel>();
			matchupTable.setRowSorter(sorter);
			sorter.setModel(m);
			matchupTable.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					int column = matchupTable.columnAtPoint(e.getPoint());
					final Matchup matchup = (Matchup) matchupTable.getValueAt(
							matchupTable.rowAtPoint(e.getPoint()), 0);
					if (e.getButton() == MouseEvent.BUTTON3) {
						int row = matchupTable.rowAtPoint(e.getPoint());
						matchupTable.setRowSelectionInterval(row, row);
						JPopupMenu menu = new JPopupMenu();
						JMenuItem newEntry = new JMenuItem("New...");
						newEntry.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								SwingUtilities.invokeLater(NewEntryDialoge
										.getInstance());
							}
						});
						menu.add(newEntry);
						menu.add(new JSeparator());
						JMenuItem edit = new JMenuItem("Edit");
						edit.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								NewEntryDialoge nd = NewEntryDialoge
										.getInstance();
								nd.setToEdit(matchup.getGame(), false);
								SwingUtilities.invokeLater(nd);
							}
						});
						menu.add(edit);
						JMenuItem view = new JMenuItem("View");
						view.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								MatchupDetailDialoge dd = MatchupDetailDialoge
										.getInstance();
								dd.setMatchup(matchup);
								SwingUtilities.invokeLater(dd);
							}
						});
						menu.add(view);
						JMenuItem delete = new JMenuItem("Delete");
						delete.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								String message = "Are you sure you want to delete the matchup from "
										+ matchup
										+ "\n\n"
										+ matchup.getMyChamp()
										+ " vs. "
										+ matchup.getEnemyChamp()
										+ "\n\nThis can not be reversed?";
								String title = "Information";
								int ok = JOptionPane.showConfirmDialog(null,
										message, title,
										JOptionPane.YES_NO_OPTION);
								if (ok == JOptionPane.OK_OPTION) {
									controller.deleteMatchup(matchup);
									((MatchupTableModel) matchupTable
											.getModel()).removeMatchup(matchup);
									MainView.getInstance().setStatusText(
											"Matchup and game " + matchup
													+ " removed");
								}
							}
						});
						menu.add(delete);
						menu.add(new JSeparator());
						switch (column) {
						case 0:
							JMenuItem moreDate = new JMenuItem(
									"All matchups from "
											+ matchup.toString().trim());
							moreDate.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									RowFilter<MatchupTableModel, Object> rf = RowFilter
											.regexFilter(matchup.toString());
									sorter.setRowFilter(rf);
								}
							});
							menu.add(moreDate);
							break;
						case 1:
							JMenuItem moreLane = new JMenuItem(
									"All matchups on the " + matchup.getLane());
							moreLane.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									RowFilter<MatchupTableModel, Object> rf = RowFilter
											.regexFilter(matchup.getLane()
													.toString());
									sorter.setRowFilter(rf);
								}
							});
							menu.add(moreLane);
							break;
						case 2:
						case 3:
							JMenuItem moreMyChamp = new JMenuItem(
									"All matchups with " + matchup.getMyChamp());
							moreMyChamp.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									RowFilter<MatchupTableModel, Object> rf = RowFilter
											.regexFilter(matchup.getMyChamp()
													.toString());
									sorter.setRowFilter(rf);
								}
							});
							menu.add(moreMyChamp);
							JMenuItem moreEnemyChamp = new JMenuItem(
									"All matchups with "
											+ matchup.getEnemyChamp());
							moreEnemyChamp
									.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(
												ActionEvent e) {
											RowFilter<MatchupTableModel, Object> rf = RowFilter
													.regexFilter(matchup
															.getEnemyChamp()
															.toString());
											sorter.setRowFilter(rf);
										}
									});
							menu.add(moreEnemyChamp);
							break;
						case 4:
							JMenuItem moreGameResult = new JMenuItem();
							String str = "All ";
							if (matchup.getGame().getResult() == GameResult.WIN) {
								str += "won";
							} else {
								str += "lost";
							}
							str += " games";
							moreGameResult.setText(str);
							moreGameResult
									.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(
												ActionEvent e) {
											RowFilter<MatchupTableModel, Object> rf = RowFilter
													.regexFilter(matchup
															.getGame()
															.getResult()
															.toString());
											sorter.setRowFilter(rf);
										}
									});
							menu.add(moreGameResult);
							break;
						}
						JMenuItem resetFilter = new JMenuItem("Reset Filter");
						resetFilter.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								sorter.setRowFilter(null);
							}
						});
						menu.add(new JSeparator());
						menu.add(resetFilter);
						menu.show(matchupTable, e.getX(), e.getY());
					} else if (e.getClickCount() == 2) {
						MatchupDetailDialoge dd = MatchupDetailDialoge
								.getInstance();
						dd.setMatchup(matchup);
						SwingUtilities.invokeLater(dd);
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseClicked(MouseEvent arg0) {

				}
			});
		}
		logger.trace("getMatchupTable() - Returning");
		logger.debug("getMatchupTable() - Returning: {}", matchupTable);
		return matchupTable;
	}

	public JPanel getLegendPanel() {
		logger.trace("getLegendPanel() - Entering");
		if (legendPanel == null) {
			legendPanel = new JPanel(new GridBagLayout());
			int x = 0;
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.insets = new Insets(0, 20, 0, 0);
			for (MatchupResult res : MatchupResult.values()) {
				constraints.gridx = x;
				x++;
				constraints.gridy = 0;
				constraints.fill = GridBagConstraints.HORIZONTAL;
				constraints.weightx = 1D / MatchupResult.values().length;
				JLabel color = new JLabel(" ");
				color.setOpaque(true);
				color.setBackground(res.getColor());
				legendPanel.add(color, constraints);

				constraints = new GridBagConstraints();
				constraints.gridx = x;
				x++;
				constraints.gridy = 0;
				constraints.insets = new Insets(0, 0, 0, 20);
				JLabel dsc = new JLabel(" = " + res.getDescription());
				legendPanel.add(dsc, constraints);

				constraints = new GridBagConstraints();
				constraints.gridx = x;
				x++;
				constraints.gridy = 0;
				legendPanel.add(new JSeparator(JSeparator.VERTICAL),
						constraints);
			}
		}
		logger.trace("getLegendPanel() - Returning");
		logger.debug("getLegendPanel() - Returning: {}", legendPanel);
		return legendPanel;
	}

	/**
	 * Adds a new matchup
	 * 
	 * @param matchup
	 *            The {@link Matchup matchup} to add
	 */
	public void addMatchup(Matchup matchup) {
		logger.trace("addMatchup() - Entering");
		logger.debug("addMatchup() - Parameter: {}", matchup);
		((MatchupTableModel) matchupTable.getModel()).addMatchup(matchup);
		logger.trace("addMatchup() - Leaving");
	}

	/**
	 * Removes a matchup
	 * 
	 * @param matchup
	 *            The {@link Matchup matchup} to remove
	 */
	public void removeMatchup(Matchup matchup) {
		logger.trace("removeMatchup() - Entering");
		logger.debug("removeMatchup() - Parameter: {}", matchup);
		((MatchupTableModel) matchupTable.getModel()).removeMatchup(matchup);
		logger.trace("removeMatchup() - Leaving");
	}

	@Override
	public void refresh() {
		((MatchupTableModel) matchupTable.getModel()).fireTableDataChanged();
	}
}