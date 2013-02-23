package de.phyberapex.diaryoflegends.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.Item;
import de.phyberapex.diaryoflegends.model.SummonerSpell;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;
import de.phyberapex.diaryoflegends.model.util.ItemUtil;
import de.phyberapex.diaryoflegends.model.util.SummonerSpellUtil;

public class Update {

	private static Logger logger = LogManager.getLogger(Update.class.getName());
	private static File updateFolder;

	public static void update() {
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

	private static void doUpdate(String strLine) {
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
	private static void updateEdit(String entity, String[] attributes) {
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
	private static void editItem(final String[] attributes) {
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
	private static void editChamp(final String[] attributes) {
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
	private static void editSpell(final String[] attributes) {
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
	private static void updateNew(String entity, String[] attributes) {
		switch (entity) {
		case "CHAMPION":
			Champion champ = ChampionUtil.getChampionById(Integer
					.parseInt(attributes[0]));
			if (champ == null) {
				champ = new Champion(Integer.valueOf(attributes[0]),
						attributes[1], new File(updateFolder.getAbsolutePath()
								+ "\\" + attributes[2]));
				Config.getInstance().getDBHandle().store(champ);
			}
			break;
		case "ITEM":
			Item item = ItemUtil.getItemById(Integer.parseInt(attributes[0]));
			if (item == null) {
				item = new Item(Integer.valueOf(attributes[0]), attributes[1],
						Integer.valueOf(attributes[2]), new File(
								updateFolder.getAbsolutePath() + "\\"
										+ attributes[3]));
				Config.getInstance().getDBHandle().store(item);
			}
			break;
		case "SPELL":
			SummonerSpell spell = SummonerSpellUtil.getSpellById(Integer
					.valueOf(attributes[0]));
			if (spell == null) {
				spell = new SummonerSpell(Integer.valueOf(attributes[0]),
						attributes[1], new File(updateFolder.getAbsolutePath()
								+ "\\" + attributes[2]));
				Config.getInstance().getDBHandle().store(spell);
			}
			break;
		}
	}

	private static void deleteDir(File dir) {
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
}
