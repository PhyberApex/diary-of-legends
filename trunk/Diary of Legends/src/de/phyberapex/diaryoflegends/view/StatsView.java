package de.phyberapex.diaryoflegends.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.action.CreateStatsAction;
import de.phyberapex.diaryoflegends.controller.StatsController;
import de.phyberapex.diaryoflegends.model.StatisticTypes;

public class StatsView extends JPanel implements View {

	private static final long serialVersionUID = 1L;
	private StatsController controller;
	private JComboBox<StatisticTypes> statTypesBox;
	private JTextField parameterTextfield;
	private JButton createButton;
	private JScrollPane resultPane;
	private static Logger logger = LogManager.getLogger(StatsView.class
			.getName());

	/**
	 * @param statsController
	 */
	public StatsView(StatsController statsController) {
		super(new GridBagLayout());
		this.controller = statsController;
		createGUI();
	}

	private void createGUI() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.add(getStatTypesBox(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		this.add(getParameterTextfield(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 0;
		this.add(getCreateButton(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 3;
		this.add(getResultPane(), constraints);
	}

	private JComboBox<StatisticTypes> getStatTypesBox() {
		logger.trace("getStatTypesBox() - Entering");
		if (statTypesBox == null) {
			statTypesBox = new JComboBox<StatisticTypes>(
					new DefaultComboBoxModel<StatisticTypes>(
							StatisticTypes.values()));
		}
		logger.trace("getStatTypesBox() - Returning");
		logger.debug("getStatTypesBox() - Returning: {}", statTypesBox);
		return statTypesBox;
	}

	private JTextField getParameterTextfield() {
		logger.trace("getParameterTextfield() - Entering");
		if (parameterTextfield == null) {
			parameterTextfield = new JTextField();
			parameterTextfield
					.setToolTipText("Enter one or more parameter e.g. champion names seperated by \";\"");
			parameterTextfield.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						getCreateButton().doClick();
					}
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}
			});
		}
		logger.trace("getParameterTextfield() - Returning");
		logger.debug("getParameterTextfield() - Returning: {}",
				parameterTextfield);
		return parameterTextfield;
	}

	private JButton getCreateButton() {
		logger.trace("getCreateButton() - Entering");
		if (createButton == null) {
			createButton = new JButton("create");
			createButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Thread tr = new Thread(new CreateStatsAction(controller,
							getStatTypesBox().getItemAt(
									getStatTypesBox().getSelectedIndex()),
							getParameterTextfield().getText()));
					tr.start();
				}
			});
		}
		logger.trace("getCreateButton() - Returning");
		logger.debug("getCreateButton() - Returning: {}", createButton);
		return createButton;
	}

	private JScrollPane getResultPane() {
		logger.trace("getResultPane() - Entering");
		if (resultPane == null) {
			resultPane = new JScrollPane();
		}
		logger.trace("getResultPane() - Returning");
		logger.debug("getResultPane() - Returning: {}", resultPane);
		return resultPane;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.phyberapex.diaryoflegends.view.View#refresh()
	 */
	@Override
	public void refresh() {
	}

	/**
	 * @param jPanel
	 */
	public void setStatsPanel(JPanel jPanel) {
		getResultPane().setViewportView(jPanel);
	}

}
