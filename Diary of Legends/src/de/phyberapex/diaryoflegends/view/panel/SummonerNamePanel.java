package de.phyberapex.diaryoflegends.view.panel;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.base.Initializer;
import de.phyberapex.diaryoflegends.model.Region;
import de.phyberapex.diaryoflegends.view.View;

public class SummonerNamePanel extends JPanel implements View {

	private static final long serialVersionUID = -3649794292915604043L;
	private static SummonerNamePanel instance;
	private static Logger logger = LogManager
			.getLogger(SummonerNamePanel.class.getName());
	private JLabel nameLabel;
	private JTextField nameTextfield;
	private JLabel regionLabel;
	private JComboBox<Region> regionBox;

	private SummonerNamePanel() {
		super();
		logger.trace("CurrentSummonerNameView() - Entering");
		createGUI();
		logger.trace("CurrentSummonerNameView() - Leaving");
	}

	private void createGUI() {
		logger.trace("createGUI() - Entering");
		this.setLayout(new FlowLayout());
		this.add(getNameLabel());
		this.add(getNameTextfield());

		this.add(getRegionLabel());
		this.add(getRegionBox());
		logger.trace("createGUI() - Leaving");
	}

	private JTextField getNameTextfield() {
		logger.trace("getNameTextfield() - Entering");
		if (nameTextfield == null) {
			nameTextfield = new JTextField();
			nameTextfield.setPreferredSize(new Dimension(150, 30));
			nameTextfield
					.setToolTipText("This is only needed for import reasons.");
		}
		logger.trace("getNameTextfield() - Returning");
		logger.debug("getNameTextfield() - Returning: {}", nameTextfield);
		return nameTextfield;
	}

	private JLabel getNameLabel() {
		logger.trace("getNameLabel() - Entering");
		if (nameLabel == null) {
			nameLabel = new JLabel("Summoner Name:");
		}
		logger.trace("getNameLabel() - Returning");
		logger.debug("getNameLabel() - Returning: {}", nameLabel);
		return nameLabel;
	}

	private JLabel getRegionLabel() {
		logger.trace("getRegionLabel() - Entering");
		if (regionLabel == null) {
			regionLabel = new JLabel("Region:");
		}
		logger.trace("getRegionLabel() - Returning");
		logger.debug("getRegionLabel() - Returning: {}", regionLabel);
		return regionLabel;
	}

	private JComboBox<Region> getRegionBox() {
		logger.trace("getRegionBox() - Entering");
		if (regionBox == null) {
			regionBox = new JComboBox<Region>(new DefaultComboBoxModel<Region>(
					Region.values()));
		}
		logger.trace("getRegionBox() - Returning");
		logger.debug("getRegionBox() - Returning: {}", regionBox);
		return regionBox;
	}

	/**
	 * Returns an instance of this class if the static attribute instance is
	 * null it will be created.
	 * 
	 * @return {@link Initializer} an instance of this class
	 */
	public static synchronized SummonerNamePanel getInstance() {
		logger.trace("getInstance() - Entering");
		if (instance == null) {
			instance = new SummonerNamePanel();
		}
		logger.trace("getInstance() - Returning");
		logger.debug("getInstance() - Returning: {}", instance);
		return instance;
	}

	/**
	 * Sets the current summoners name
	 * 
	 * @param name
	 *            {@link String} the new name
	 */

	public void setSummonerName(String name) {
		logger.trace("setSummonerName() - Entering");
		logger.debug("setSummonerName() - Parameter: {}", name);
		nameTextfield.setText(name);
		logger.trace("setSummonerName() - Leaving");
	}

	/**
	 * Returns the current summoners name
	 * 
	 * @return {@link String}
	 */
	public String getSummonerName() {
		logger.trace("getSummonerName() - Entering");
		logger.trace("getSummonerName() - Returning");
		logger.debug("getSummonerName() - Returning: {}",
				nameTextfield.getText());
		return nameTextfield.getText();
	}

	public Region getRegion() {
		logger.trace("getRegion() - Entering");
		Region returnValue = regionBox.getItemAt(regionBox.getSelectedIndex());
		logger.trace("getRegion() - Returning");
		logger.debug("getRegion() - Returning: {}", returnValue);
		return returnValue;
	}

	public void setRegion(Region region) {
		logger.trace("setRegion() - Entering");
		logger.debug("setRegion() - Parameter: {}", region);
		regionBox.setSelectedItem(region);
		logger.trace("setRegion() - Returning");

	}

	@Override
	public void refresh() {
	}

}