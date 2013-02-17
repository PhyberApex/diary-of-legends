package de.phyberapex.diaryoflegends.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.phyberapex.diaryoflegends.extra.ImageIconFactory;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;

public class NewChampionDialoge extends JDialog {

	private static final long serialVersionUID = -1135250312216796819L;
	private Champion champ = new Champion();
	private boolean iconOk = false;
	private JLabel nameLabel;
	private JLabel iconLabel;
	private JTextField nameTextField;
	private JButton iconFileButton;
	private JFileChooser iconFileChooser;
	private JButton okayButton;
	private JButton cancelButton;
	private JLabel iconPreviewLabel;
	private GridBagConstraints constraints;
	private ChampionView champview;
	private JPanel contentPanel = new JPanel();
	private static Logger logger = LogManager
			.getLogger(NewChampionDialoge.class.getName());

	public NewChampionDialoge(ChampionView champview) {
		super(MainView.getInstance());
		this.setTitle("New Champion");
		logger.trace("NewChampionDialoge() - Entering");
		logger.debug("NewChampionDialoge() - Parameter: {}", champview);
		this.champview = champview;
		this.setModal(true);

		contentPanel.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		contentPanel.add(getNameLabel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		contentPanel.add(getNameTextField(), constraints);

		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 0;
		constraints.gridy = 1;
		contentPanel.add(getIconLabel(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 1;
		contentPanel.add(getIconFileButton(), constraints);

		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		contentPanel.add(getIconPreviewLabel(), constraints);

		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 3;
		contentPanel.add(getOkayButton(), constraints);

		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 3;
		contentPanel.add(getCancelButton(), constraints);

		this.add(contentPanel);
		this.pack();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((d.width - this.getSize().width) / 2,
				(d.height - this.getSize().height) / 2);
		logger.trace("NewChampionDialoge() - Leaving");
	}

	public JLabel getNameLabel() {
		logger.trace("getNameLabel() - Entering");
		if (nameLabel == null) {
			nameLabel = new JLabel("Champion name:");
		}
		logger.trace("getNameLabel() - Returning");
		logger.debug("getNameLabel() - Returning: {}", nameLabel);
		return nameLabel;
	}

	public JLabel getIconLabel() {
		logger.trace("getIconLabel() - Entering");
		if (iconLabel == null) {
			iconLabel = new JLabel("Icon:");
		}
		logger.trace("getIconLabel() - Returning");
		logger.debug("getIconLabel() - Returning: {}", iconLabel);
		return iconLabel;
	}

	public JTextField getNameTextField() {
		logger.trace("getNameTextField() - Entering");
		if (nameTextField == null) {
			nameTextField = new JTextField();
		}
		logger.trace("getNameTextField() - Returning");
		logger.debug("getNameTextField() - Returning: {}", nameTextField);
		return nameTextField;
	}

	public JButton getIconFileButton() {
		logger.trace("getIconFileButton() - Entering");
		if (iconFileButton == null) {
			iconFileButton = new JButton("Choose...");
			iconFileButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					int retValue = getIconFileChooser().showOpenDialog(
							MainView.getInstance());
					if (retValue == JFileChooser.APPROVE_OPTION) {
						ImageIcon icon = ImageIconFactory
								.createImageIcon(getIconFileChooser()
										.getSelectedFile().getAbsolutePath());
						if (icon.getIconHeight() <= 60
								&& icon.getIconWidth() <= 60) {
							getIconPreviewLabel().setIcon(icon);
							champ.setIcon(getIconFileChooser()
									.getSelectedFile());
							iconOk = true;
						} else {
							// TODO SCHÖNER"!
							JOptionPane.showMessageDialog(
									MainView.getInstance(),
									"Please choose a image with max 60x60 pixel.");
						}
					}
				}
			});
		}
		logger.trace("getIconFileButton() - Returning");
		logger.debug("getIconFileButton() - Returning: {}", iconFileButton);
		return iconFileButton;
	}

	public JFileChooser getIconFileChooser() {
		logger.trace("getIconFileChooser() - Entering");
		if (iconFileChooser == null) {
			iconFileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"JPG & PNG Images", "jpg", "png");
			iconFileChooser.setFileFilter(filter);
		}
		logger.trace("getIconFileChooser() - Returning");
		logger.debug("getIconFileChooser() - Returning: {}", iconFileChooser);
		return iconFileChooser;
	}

	public JButton getOkayButton() {
		logger.trace("getOkayButton() - Entering");
		if (okayButton == null) {
			okayButton = new JButton("Save");
			okayButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					logger.debug("okayButton clicked.");
					logger.debug("File choosen: {} Textfield content. {}",
							getIconFileChooser().getSelectedFile(),
							getNameTextField().getText());
					if (iconOk) {
						if (!getNameTextField().getText().equals("")) {
							champ.setName(getNameTextField().getText());
							ChampionUtil.saveChampion(champ);
							champview.addChamp(champ);
							MainView.getInstance().setStatusText(
									"Champion " + champ + " saved");
							dispose();
						} else {
							// TODO schöner!
							JOptionPane.showMessageDialog(
									MainView.getInstance(),
									"Please enter a name for the champion");
						}
					} else {
						// TODO schöner!
						JOptionPane.showMessageDialog(MainView.getInstance(),
								"Please choose a icon for the champion");
					}
				}
			});
		}
		logger.trace("getOkayButton() - Returning");
		logger.debug("getOkayButton() - Returning: {}", okayButton);
		return okayButton;
	}

	public JButton getCancelButton() {
		logger.trace("getCancelButton() - Entering");
		if (cancelButton == null) {
			cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
		}
		logger.trace("getCancelButton() - Returning");
		logger.debug("getCancelButton() - Returning: {}", cancelButton);
		return cancelButton;
	}

	public JLabel getIconPreviewLabel() {
		logger.trace("getIconPreviewLabel() - Entering");
		if (iconPreviewLabel == null) {
			iconPreviewLabel = new JLabel();
			iconPreviewLabel
					.setIcon(ImageIconFactory
							.createImageIconFromResourePath("img/empty_60x60.png"));
		}
		logger.trace("getIconPreviewLabel() - Returning");
		logger.debug("getIconPreviewLabel() - Returning: {}", iconPreviewLabel);
		return iconPreviewLabel;
	}

	@Override
	public void dispose() {
		getNameTextField().setText("");
		getIconPreviewLabel()
				.setIcon(
						ImageIconFactory
								.createImageIconFromResourePath("img/emptpy_60x60.png"));
		iconOk = false;
		super.dispose();
	}
}
