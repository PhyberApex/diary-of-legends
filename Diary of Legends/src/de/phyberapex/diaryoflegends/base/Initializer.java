package de.phyberapex.diaryoflegends.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.query.Predicate;
import de.phyberapex.diaryoflegends.controller.MainController;
import de.phyberapex.diaryoflegends.exception.InitializeException;
import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.Item;
import de.phyberapex.diaryoflegends.model.Summoner;
import de.phyberapex.diaryoflegends.model.SummonerSpell;

/**
 * Class to initialize the application on the start
 * 
 * @author Janis Walliser <walliser.janis@gmx.de>
 */
public class Initializer {

	private static Initializer instance;
	private File updateFolder;
	transient private static Logger logger = LogManager
			.getLogger(MainController.class.getName());

	/**
	 * Consturctor of this class
	 */
	private Initializer() {
	}

	/**
	 * Returns an instance of this class if the static attribute instance is
	 * null it will be created.
	 * 
	 * @return {@link Initializer} an instance of this class
	 */
	public static synchronized Initializer getInstance() {
		logger.trace("getInstance() - Entering");
		logger.debug("Instance is {}", instance);
		if (instance == null) {
			logger.debug("Creating a new instance of Initializer");
			instance = new Initializer();
		}
		logger.trace("getInstance() - Returning");
		logger.debug("getInstance() - Returning: {}", instance);
		return instance;
	}

	/**
	 * Initilizes the application
	 * 
	 * @return {@link boolean} True if initilizing was successfull false if not
	 */
	public InitializeAction initializeApp() throws InitializeException {
		logger.trace("initializeApp() - Entering");
		InitializeAction returnValue = InitializeAction.NONE;
		changeLaF();
		update();
		Config conf = Config.getInstance();
		if (conf.getProperty("SUMMONER_NAME") == null) {
			returnValue = InitializeAction.CREATE_SUMMONER;
		} else {
			conf.setCurrentSummoner(new Summoner(conf
					.getProperty("SUMMONER_NAME"), null));
		}
		try {
			conf.getDBHandle();
		} catch (DatabaseFileLockedException e) {
			logger.fatal("Databasefile is locked");
			throw new InitializeException(
					"Can't open database. Databasefile is locked.");
		}
		logger.trace("initializeApp() - Returning");
		logger.debug("initializeApp() - Returning {}", returnValue);
		return returnValue;
	}

	private void update() {
		logger.trace("update() - Entering");
		updateFolder = new File(System.getProperty("user.dir") + "\\update");
		if (updateFolder.isDirectory()) {
			logger.debug("Update folder found. Searching for update file");
			File update = new File(updateFolder.getAbsolutePath()
					+ "\\update.dolup");
			if (update != null) {
				logger.debug("Update file found. Reading update file");
				try {
					// Open the file that is the first
					// command line parameter
					FileInputStream fstream = new FileInputStream(update);
					// Get the object of DataInputStream
					BufferedReader br = new BufferedReader(
							new InputStreamReader(fstream));
					String strLine;
					// Read File Line By Line
					while ((strLine = br.readLine()) != null) {
						doUpdate(strLine);
						// Print the content on the console
					}
					// Close the input stream
					br.close();
					fstream.close();
					try {
						deleteDir(updateFolder);
					} catch (SecurityException e) {
						e.printStackTrace();
					}
				} catch (Exception e) {// Catch exception if any
					logger.error(e.getMessage());
				}
			}
		}
		logger.trace("update() - Leaving");
	}

	private void doUpdate(String strLine) {
		logger.trace("doUpdate() - Entering");
		logger.debug("doUpdate() - Parameter: {}", strLine);
		int first = strLine.indexOf("[");
		int second = strLine.indexOf("]");
		String work = strLine.substring(first + 1, second);
		logger.debug("doUpdate() - work = {}", work);
		first = strLine.indexOf("[", second);
		second = strLine.indexOf("]", first);
		String entity = strLine.substring(first + 1, second);
		logger.debug("doUpdate() - entity = {}", entity);
		first = strLine.indexOf("[", second);
		second = strLine.indexOf("]", first);
		String[] attributes = strLine.substring(first + 1, second).split(",");
		logger.debug("doUpdate() - attributes = {}", (Object[]) attributes);
		switch (work) {
		case "NEW":
			updateNew(entity, attributes);
			break;
		case "EDIT":
			updateEdit(entity, attributes);
			break;
		}
		logger.trace("doUpdate() - Leaving");
	}

	/**
	 * @param entity
	 * @param attributes
	 */
	private void updateEdit(String entity, String[] attributes) {
		switch (entity) {
		case "CHAMPION":
			editChamp(attributes);
			break;
		case "ITEM":
			editItem(attributes);
			break;
		case "SPELL":
			editSpell(attributes);
			break;
		}
	}

	/**
	 * @param attributes
	 */
	private void editItem(final String[] attributes) {
		ObjectSet<Item> osItem = Config.getInstance().getDBHandle()
				.query(new Predicate<Item>() {

					private static final long serialVersionUID = 1L;

					@Override
					public boolean match(Item arg0) {
						if (arg0.getName().equals(attributes[0])) {
							return true;
						} else {
							return false;
						}
					}
				});
		Item item = osItem.next();
		item.setName(attributes[1]);
		item.setPrice(Integer.valueOf(attributes[2]));
		item.setIcon(new File(updateFolder.getAbsolutePath() + "\\"
				+ attributes[3]));
		Config.getInstance().getDBHandle().store(item);
	}

	/**
	 * @param attributes
	 */
	private void editChamp(final String[] attributes) {
		ObjectSet<Champion> osChamp = Config.getInstance().getDBHandle()
				.query(new Predicate<Champion>() {

					private static final long serialVersionUID = 1L;

					@Override
					public boolean match(Champion arg0) {
						if (arg0.getId() == Integer.parseInt(attributes[0])) {
							return true;
						} else {
							return false;
						}
					}
				});
		Champion champ = osChamp.next();
		champ.setName(attributes[1]);
		champ.setIcon(new File(updateFolder.getAbsolutePath() + "\\"
				+ attributes[2]));
		Config.getInstance().getDBHandle().store(champ);
	}

	/**
	 * @param attributes
	 */
	private void editSpell(final String[] attributes) {
		ObjectSet<SummonerSpell> osSpell = Config.getInstance().getDBHandle()
				.query(new Predicate<SummonerSpell>() {

					private static final long serialVersionUID = 1L;

					@Override
					public boolean match(SummonerSpell arg0) {
						if (arg0.getId() == Integer.parseInt(attributes[0])) {
							return true;
						} else {
							return false;
						}
					}
				});
		SummonerSpell spell = osSpell.next();
		spell.setName(attributes[1]);
		spell.setIcon(new File(updateFolder.getAbsolutePath() + "\\"
				+ attributes[2]));
		Config.getInstance().getDBHandle().store(spell);
	}

	/**
	 * @param entity
	 * @param attributes
	 */
	private void updateNew(String entity, String[] attributes) {
		switch (entity) {
		case "CHAMPION":
			Champion champ = new Champion(Integer.valueOf(attributes[0]),
					attributes[1], new File(updateFolder.getAbsolutePath()
							+ "\\" + attributes[2]));
			Config.getInstance().getDBHandle().store(champ);
			break;
		case "ITEM":
			Item item = new Item(Integer.valueOf(attributes[0]), attributes[1],
					Integer.valueOf(attributes[2]), new File(
							updateFolder.getAbsolutePath() + "\\"
									+ attributes[3]));
			Config.getInstance().getDBHandle().store(item);
			break;
		case "SPELL":
			SummonerSpell spell = new SummonerSpell(
					Integer.valueOf(attributes[0]), attributes[1], new File(
							updateFolder.getAbsolutePath() + "\\"
									+ attributes[2]));
			Config.getInstance().getDBHandle().store(spell);
			break;
		}
	}

	private void deleteDir(File dir) {
		// If it is a directory get the child
		if (dir.isDirectory()) {
			// List all the contents of the directory
			File fileList[] = dir.listFiles();

			// Loop through the list of files/directories
			for (int index = 0; index < fileList.length; index++) {
				// Get the current file object.
				File file = fileList[index];

				// Call deleteDir function once again for deleting all the
				// directory contents or
				// sub directories if any present.
				deleteDir(file);
			}
		}

		// Delete the current directory or file.
		dir.delete();
	}

	private void changeLaF() {
		logger.trace("changeLaF() - Entering");
		try {
			logger.debug("Iterating through LaFs");
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				logger.debug("Current LaF: {}", info.getName());
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					logger.debug("LaF {} set", info.getName());
					break;
				}
			}
		} catch (Exception e) {
			logger.info("Unable to find look and feel nimbus. Using default.");
		} finally {
			logger.trace("changeLaF() - Leaving");
		}
	}

}