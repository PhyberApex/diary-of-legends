package de.phyberapex.diaryoflegends.view.panel;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.base.Initializer;
import de.phyberapex.diaryoflegends.view.View;

public class APIKeyViewPanel extends JPanel implements View {

	private static final long serialVersionUID = -3649794292915604043L;
	private static APIKeyViewPanel instance;
	private static Logger logger = LogManager.getLogger(APIKeyViewPanel.class
			.getName());
	private JLabel keyLabel;
	private JTextField keyTextfield;
	private JLabel desc;

	private APIKeyViewPanel() {
		super();
		logger.trace("CurrentSummonerNameView() - Entering");
		createGUI();
		logger.trace("CurrentSummonerNameView() - Leaving");
	}

	private void createGUI() {
		logger.trace("createGUI() - Entering");
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.add(getKeyLabel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		constraints.insets = new Insets(0, 0, 10, 0);
		this.add(getKeyTextfield(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		this.add(getDescLabel(), constraints);
		logger.trace("createGUI() - Leaving");
	}

	private JTextField getKeyTextfield() {
		logger.trace("getKeyTextfield() - Entering");
		if (keyTextfield == null) {
			keyTextfield = new JTextField("");
			keyTextfield.setPreferredSize(new Dimension(150, 30));
			keyTextfield
					.setToolTipText("This is only needed for import reasons.");
		}
		logger.trace("getKeyTextfield() - Returning");
		logger.debug("getKeyTextfield() - Returning: {}", keyTextfield);
		return keyTextfield;
	}

	private JLabel getKeyLabel() {
		logger.trace("getKeyLabel() - Entering");
		if (keyLabel == null) {
			keyLabel = new JLabel("Elophant API-Key:");
		}
		logger.trace("getKeyLabel() - Returning");
		logger.debug("getKeyLabel() - Returning: {}", keyLabel);
		return keyLabel;
	}

	private JLabel getDescLabel() {
		logger.trace("getDescLabel() - Entering");
		if (desc == null) {
			desc = new JLabel(
					"<html>You need to enter this information to be able to use this application. It is used to update the<br>champion and item pool as well as fetch you most recent game. You can fetch one by simply<br>entering your email <a href=\"\">here<a/>.</html>");
			this.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						Desktop desktop = Desktop.isDesktopSupported() ? Desktop
								.getDesktop() : null;
						if (desktop != null
								&& desktop.isSupported(Desktop.Action.BROWSE)) {
							try {
								desktop.browse(new URL(
										"http://elophant.com/developers/new")
										.toURI());
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
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
				public void mouseClicked(MouseEvent e) {
				}
			});
		}
		logger.trace("getDescLabel() - Returning");
		logger.debug("getDescLabel() - Returning: {}", desc);
		return desc;
	}

	/**
	 * Returns an instance of this class if the static attribute instance is
	 * null it will be created.
	 * 
	 * @return {@link Initializer} an instance of this class
	 */
	public static synchronized APIKeyViewPanel getInstance() {
		logger.trace("getInstance() - Entering");
		if (instance == null) {
			instance = new APIKeyViewPanel();
		}
		logger.trace("getInstance() - Returning");
		logger.debug("getInstance() - Returning: {}", instance);
		return instance;
	}

	/**
	 * Returns the api key
	 * 
	 * @return {@link String}
	 */
	public String getAPIKey() {
		logger.trace("getAPIKey() - Entering");
		logger.trace("getAPIKey() - Returning");
		logger.debug("getAPIKey() - Returning: {}", keyTextfield.getText());
		return keyTextfield.getText();
	}

	@Override
	public void refresh() {
	}

}