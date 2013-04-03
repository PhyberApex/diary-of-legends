package de.phyberapex.diaryoflegends.view.dialoge;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import de.phyberapex.diaryoflegends.base.Constants;
import de.phyberapex.diaryoflegends.extra.ImageIconFactory;
import de.phyberapex.diaryoflegends.view.MainView;
import java.awt.GridLayout;

public class AboutDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	public static AboutDialog instance;

	/**
	 * Create the dialog.
	 */
	private AboutDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("About");
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Application:",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			panel.setLayout(gbl_panel);
			{
				JLabel lblNewLabel = new JLabel("");
				lblNewLabel.setIcon(ImageIconFactory.resizeImageIcon(ImageIconFactory.createImageIconFromResourePath("img/icon_128.png"), 60, 60));
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.gridheight = 5;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 0;
				panel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				JLabel lblName = new JLabel("Name:");
				GridBagConstraints gbc_lblName = new GridBagConstraints();
				gbc_lblName.insets = new Insets(0, 0, 5, 5);
				gbc_lblName.anchor = GridBagConstraints.WEST;
				gbc_lblName.gridx = 1;
				gbc_lblName.gridy = 0;
				panel.add(lblName, gbc_lblName);
			}
			{
				JLabel lblAppname = new JLabel(Constants.getAppName());
				GridBagConstraints gbc_lblAppname = new GridBagConstraints();
				gbc_lblAppname.anchor = GridBagConstraints.WEST;
				gbc_lblAppname.insets = new Insets(0, 0, 5, 0);
				gbc_lblAppname.gridx = 2;
				gbc_lblAppname.gridy = 0;
				panel.add(lblAppname, gbc_lblAppname);
			}
			{
				JLabel lblVersion = new JLabel("Version:");
				GridBagConstraints gbc_lblVersion = new GridBagConstraints();
				gbc_lblVersion.anchor = GridBagConstraints.WEST;
				gbc_lblVersion.insets = new Insets(0, 0, 5, 5);
				gbc_lblVersion.gridx = 1;
				gbc_lblVersion.gridy = 1;
				panel.add(lblVersion, gbc_lblVersion);
			}
			{
				JLabel lblAppversion = new JLabel(Constants.getAppVersion());
				GridBagConstraints gbc_lblAppversion = new GridBagConstraints();
				gbc_lblAppversion.insets = new Insets(0, 0, 5, 0);
				gbc_lblAppversion.anchor = GridBagConstraints.WEST;
				gbc_lblAppversion.gridx = 2;
				gbc_lblAppversion.gridy = 1;
				panel.add(lblAppversion, gbc_lblAppversion);
			}
			{
				JLabel lblAuthor = new JLabel("Author:");
				GridBagConstraints gbc_lblAuthor = new GridBagConstraints();
				gbc_lblAuthor.anchor = GridBagConstraints.WEST;
				gbc_lblAuthor.insets = new Insets(0, 0, 5, 5);
				gbc_lblAuthor.gridx = 1;
				gbc_lblAuthor.gridy = 2;
				panel.add(lblAuthor, gbc_lblAuthor);
			}
			{
				JLabel lblAppauthor = new JLabel(Constants.getAppAuthor());
				GridBagConstraints gbc_lblAppauthor = new GridBagConstraints();
				gbc_lblAppauthor.insets = new Insets(0, 0, 5, 0);
				gbc_lblAppauthor.anchor = GridBagConstraints.WEST;
				gbc_lblAppauthor.gridx = 2;
				gbc_lblAppauthor.gridy = 2;
				panel.add(lblAppauthor, gbc_lblAppauthor);
			}
			{
				JLabel lblProjectHome = new JLabel("Project home:");
				GridBagConstraints gbc_lblProjectHome = new GridBagConstraints();
				gbc_lblProjectHome.anchor = GridBagConstraints.WEST;
				gbc_lblProjectHome.insets = new Insets(0, 0, 5, 5);
				gbc_lblProjectHome.gridx = 1;
				gbc_lblProjectHome.gridy = 3;
				panel.add(lblProjectHome, gbc_lblProjectHome);
			}
			{
				JLabel lblProjhome = new JLabel(
						"https://code.google.com/p/diary-of-legends");
				GridBagConstraints gbc_lblProjhome = new GridBagConstraints();
				gbc_lblProjhome.insets = new Insets(0, 0, 5, 0);
				gbc_lblProjhome.anchor = GridBagConstraints.WEST;
				gbc_lblProjhome.gridx = 2;
				gbc_lblProjhome.gridy = 3;
				panel.add(lblProjhome, gbc_lblProjhome);
			}
			{
				JLabel lblLicense = new JLabel("License:");
				GridBagConstraints gbc_lblLicense = new GridBagConstraints();
				gbc_lblLicense.anchor = GridBagConstraints.WEST;
				gbc_lblLicense.insets = new Insets(0, 0, 0, 5);
				gbc_lblLicense.gridx = 1;
				gbc_lblLicense.gridy = 4;
				panel.add(lblLicense, gbc_lblLicense);
			}
			{
				JLabel lblHttpwwwgnudedocumentsgpldehtml = new JLabel(
						"GPL 3.0 - http://www.gnu.de/documents/gpl.de.html");
				GridBagConstraints gbc_lblHttpwwwgnudedocumentsgpldehtml = new GridBagConstraints();
				gbc_lblHttpwwwgnudedocumentsgpldehtml.anchor = GridBagConstraints.WEST;
				gbc_lblHttpwwwgnudedocumentsgpldehtml.gridx = 2;
				gbc_lblHttpwwwgnudedocumentsgpldehtml.gridy = 4;
				panel.add(lblHttpwwwgnudedocumentsgpldehtml,
						gbc_lblHttpwwwgnudedocumentsgpldehtml);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Credits:",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel.insets = new Insets(0, 0, 0, 0);
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 1;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			panel.setLayout(gbl_panel);
			{
				JLabel lblLoadingIcons = new JLabel("Loading icons:");
				lblLoadingIcons.setHorizontalAlignment(SwingConstants.LEFT);
				GridBagConstraints gbc_lblLoadingIcons = new GridBagConstraints();
				gbc_lblLoadingIcons.anchor = GridBagConstraints.WEST;
				gbc_lblLoadingIcons.insets = new Insets(0, 0, 5, 5);
				gbc_lblLoadingIcons.gridx = 0;
				gbc_lblLoadingIcons.gridy = 0;
				panel.add(lblLoadingIcons, gbc_lblLoadingIcons);
			}
			{
				JLabel lblHttpfaziedeviantartcom = new JLabel(
						"fazie69 - http://fazie69.deviantart.com/");
				GridBagConstraints gbc_lblHttpfaziedeviantartcom = new GridBagConstraints();
				gbc_lblHttpfaziedeviantartcom.anchor = GridBagConstraints.WEST;
				gbc_lblHttpfaziedeviantartcom.insets = new Insets(0, 0, 5, 0);
				gbc_lblHttpfaziedeviantartcom.gridx = 1;
				gbc_lblHttpfaziedeviantartcom.gridy = 0;
				panel.add(lblHttpfaziedeviantartcom,
						gbc_lblHttpfaziedeviantartcom);
			}
			{
				JLabel lblChampionItemAnd = new JLabel(
						"Champion, item and match API:");
				GridBagConstraints gbc_lblChampionItemAnd = new GridBagConstraints();
				gbc_lblChampionItemAnd.insets = new Insets(0, 0, 5, 5);
				gbc_lblChampionItemAnd.gridx = 0;
				gbc_lblChampionItemAnd.gridy = 1;
				panel.add(lblChampionItemAnd, gbc_lblChampionItemAnd);
			}
			{
				JLabel lblHttpelophantcom = new JLabel(
						"Elophant - http://elophant.com");
				lblHttpelophantcom.setHorizontalAlignment(SwingConstants.LEFT);
				GridBagConstraints gbc_lblHttpelophantcom = new GridBagConstraints();
				gbc_lblHttpelophantcom.insets = new Insets(0, 0, 5, 0);
				gbc_lblHttpelophantcom.anchor = GridBagConstraints.WEST;
				gbc_lblHttpelophantcom.gridx = 1;
				gbc_lblHttpelophantcom.gridy = 1;
				panel.add(lblHttpelophantcom, gbc_lblHttpelophantcom);
			}
			{
				JLabel lblDatabase = new JLabel("Database:");
				GridBagConstraints gbc_lblDatabase = new GridBagConstraints();
				gbc_lblDatabase.anchor = GridBagConstraints.WEST;
				gbc_lblDatabase.insets = new Insets(0, 0, 5, 5);
				gbc_lblDatabase.gridx = 0;
				gbc_lblDatabase.gridy = 2;
				panel.add(lblDatabase, gbc_lblDatabase);
			}
			{
				JLabel lblHttpwwwdbocom = new JLabel(
						"DB4O - http://www.db4o.com/");
				GridBagConstraints gbc_lblHttpwwwdbocom = new GridBagConstraints();
				gbc_lblHttpwwwdbocom.insets = new Insets(0, 0, 5, 0);
				gbc_lblHttpwwwdbocom.anchor = GridBagConstraints.WEST;
				gbc_lblHttpwwwdbocom.gridx = 1;
				gbc_lblHttpwwwdbocom.gridy = 2;
				panel.add(lblHttpwwwdbocom, gbc_lblHttpwwwdbocom);
			}
			{
				JLabel lblImportExport = new JLabel("Import / Export:");
				lblImportExport.setHorizontalAlignment(SwingConstants.LEFT);
				GridBagConstraints gbc_lblImportExport = new GridBagConstraints();
				gbc_lblImportExport.anchor = GridBagConstraints.WEST;
				gbc_lblImportExport.insets = new Insets(0, 0, 5, 5);
				gbc_lblImportExport.gridx = 0;
				gbc_lblImportExport.gridy = 3;
				panel.add(lblImportExport, gbc_lblImportExport);
			}
			{
				JLabel lblHttpjsonorg = new JLabel("JSON - http://json.org/");
				GridBagConstraints gbc_lblHttpjsonorg = new GridBagConstraints();
				gbc_lblHttpjsonorg.insets = new Insets(0, 0, 5, 0);
				gbc_lblHttpjsonorg.anchor = GridBagConstraints.WEST;
				gbc_lblHttpjsonorg.gridx = 1;
				gbc_lblHttpjsonorg.gridy = 3;
				panel.add(lblHttpjsonorg, gbc_lblHttpjsonorg);
			}
			{
				JLabel lblLogging = new JLabel("Logging:");
				GridBagConstraints gbc_lblLogging = new GridBagConstraints();
				gbc_lblLogging.anchor = GridBagConstraints.WEST;
				gbc_lblLogging.insets = new Insets(0, 0, 5, 5);
				gbc_lblLogging.gridx = 0;
				gbc_lblLogging.gridy = 4;
				panel.add(lblLogging, gbc_lblLogging);
			}
			{
				JLabel lblHttploggingapacheorglogjx = new JLabel(
						"Log4j2 - http://logging.apache.org/log4j/2.x/");
				GridBagConstraints gbc_lblHttploggingapacheorglogjx = new GridBagConstraints();
				gbc_lblHttploggingapacheorglogjx.insets = new Insets(0, 0, 5, 0);
				gbc_lblHttploggingapacheorglogjx.anchor = GridBagConstraints.WEST;
				gbc_lblHttploggingapacheorglogjx.gridx = 1;
				gbc_lblHttploggingapacheorglogjx.gridy = 4;
				panel.add(lblHttploggingapacheorglogjx,
						gbc_lblHttploggingapacheorglogjx);
			}
			{
				JLabel lblChampionItem = new JLabel("Champion / item icons:");
				GridBagConstraints gbc_lblChampionItem = new GridBagConstraints();
				gbc_lblChampionItem.anchor = GridBagConstraints.WEST;
				gbc_lblChampionItem.insets = new Insets(0, 0, 0, 5);
				gbc_lblChampionItem.gridx = 0;
				gbc_lblChampionItem.gridy = 5;
				panel.add(lblChampionItem, gbc_lblChampionItem);
			}
			{
				JLabel lblAllIconsOf = new JLabel("All icons of champions and items belong to riot");
				GridBagConstraints gbc_lblAllIconsOf = new GridBagConstraints();
				gbc_lblAllIconsOf.anchor = GridBagConstraints.WEST;
				gbc_lblAllIconsOf.gridx = 1;
				gbc_lblAllIconsOf.gridy = 5;
				panel.add(lblAllIconsOf, gbc_lblAllIconsOf);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			JButton okButton = new JButton("Close");
			okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			buttonPane.setLayout(new GridLayout(0, 1, 0, 0));
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		setResizable(false);
		pack();
		setLocationRelativeTo(MainView.getInstance());
	}

	public static synchronized AboutDialog getInstance() {
		if (instance == null) {
			instance = new AboutDialog();
		}
		return instance;
	}

}
