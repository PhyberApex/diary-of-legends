package de.phyberapex.diaryoflegends.view.dialoge;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.border.BevelBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.extra.ImageIconFactory;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;
import de.phyberapex.diaryoflegends.view.MainView;

public class NewChampionDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(NewChampionDialog.class
			.getName());
	private JLabel championIdLabel = new JLabel("ID:");
	private JSpinner championIdSpinner;
	private JLabel nameLabel = new JLabel("Name:");
	private JTextField nameTextfield;
	private JEditorPane notesEditorPane;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public NewChampionDialog() {
		super();
		creatGUI();
	}

	private void creatGUI() {
		this.setIconImage(ImageIconFactory
				.createImageIconFromResourePath("img/icon_128.png").getImage());
		setLayout(new GridBagLayout());
		setTitle("Add a new champion");
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(10, 10, 5, 0);
		this.add(getChampionIdLabel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(10, 5, 5, 0);
		this.add(getChampionIdSpinner(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.insets = new Insets(10, 5, 5, 0);
		this.add(getNameLabel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 3;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		constraints.insets = new Insets(5, 5, 5, 10);
		this.add(getNameTextfield(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 4;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(0, 10, 5, 10);
		this.add(getNotesEditorPane(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.5;
		constraints.insets = new Insets(5, 10, 5, 0);
		this.add(getOkButton(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.5;
		constraints.insets = new Insets(5, 5, 5, 10);
		this.add(getCancelButton(), constraints);

		this.pack();
		this.setLocationRelativeTo(MainView.getInstance());
	}

	public JLabel getChampionIdLabel() {
		logger.trace("getChampionIdLabel() - Entering");
		logger.trace("getChampionIdLabel() - Returning");
		logger.debug("getChampionIdLabel() - Returning: {}", championIdLabel);
		return championIdLabel;
	}

	public JSpinner getChampionIdSpinner() {
		logger.trace("getChampionIdSpinner() - Entering");
		if (championIdSpinner == null) {
			championIdSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 999,
					1));
		}
		logger.trace("getChampionIdSpinner() - Returning");
		logger.debug("getChampionIdSpinner() - Returning: {}",
				championIdSpinner);
		return championIdSpinner;
	}

	public JLabel getNameLabel() {
		logger.trace("getNameLabel() - Entering");
		logger.trace("getNameLabel() - Returning");
		logger.debug("getNameLabel() - Returning: {}", nameLabel);
		return nameLabel;
	}

	public JTextField getNameTextfield() {
		logger.trace("getNameTextfield() - Entering");
		if (nameTextfield == null) {
			nameTextfield = new JTextField();
		}
		logger.trace("getNameTextfield() - Returning");
		logger.debug("getNameTextfield() - Returning: {}", nameTextfield);
		return nameTextfield;
	}

	public JEditorPane getNotesEditorPane() {
		logger.trace("getNotesEditorPane() - Entering");
		if (notesEditorPane == null) {
			notesEditorPane = new JEditorPane();
			notesEditorPane.setPreferredSize(new Dimension(200, 80));
			notesEditorPane.setBorder(BorderFactory
					.createBevelBorder(BevelBorder.LOWERED));
			try {
				notesEditorPane
						.setPage("https://diary-of-legends.googlecode.com/svn/wiki/ChampionIDNotes");
			} catch (IOException e) {
				notesEditorPane.setContentType("text/html");
				notesEditorPane
						.setText("<html>No information available</html>");
			}
		}
		logger.trace("getNotesEditorPane() - Returning");
		logger.debug("getNotesEditorPane() - Returning: {}", notesEditorPane);
		return notesEditorPane;
	}

	public JButton getOkButton() {
		logger.trace("getOkButton() - Entering");
		if (okButton == null) {
			okButton = new JButton("Save");
			okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Champion tmp = new Champion(Integer
							.parseInt(getChampionIdSpinner().getValue()
									.toString()), getNameTextfield().getText());
					try {
						tmp.setIcon(new URL(
								"http://img.lolking.net/shared/riot/images/champions/"
										+ tmp.getId() + "_104.png"));
					} catch (MalformedURLException e1) {
						logger.error("Unable to load the icon for {}",
								getNameTextfield().getText());
					}
					ChampionUtil.saveChampion(tmp);
					JOptionPane.showMessageDialog(
							MainView.getInstance(),
							"Champion added he/she is avaliable after a restart of the application.",
							"Success", JOptionPane.PLAIN_MESSAGE);
					dispose();
				}
			});
		}
		logger.trace("getOkButton() - Returning");
		logger.debug("getOkButton() - Returning: {}", okButton);
		return okButton;
	}

	public JButton getCancelButton() {
		logger.trace("getCancelButton() - Entering");
		if (cancelButton == null) {
			cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		logger.trace("getCancelButton() - Returning");
		logger.debug("getCancelButton() - Returning: {}", cancelButton);
		return cancelButton;
	}

}
