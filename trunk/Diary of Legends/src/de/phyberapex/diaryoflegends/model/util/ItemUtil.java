package de.phyberapex.diaryoflegends.model.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import de.phyberapex.diaryoflegends.base.Config;
import de.phyberapex.diaryoflegends.model.Item;

/**
 * This class is used to save and retrive items
 * 
 * @author Janis Walliser <walliser.janis@gmx.de>
 * 
 */
public class ItemUtil {

	private static ObjectContainer dbHandle = Config.getInstance()
			.getDBHandle();
	transient private static Logger logger = LogManager
			.getLogger(ItemUtil.class.getName());

	/**
	 * Return all items currently stored in the database
	 * 
	 * @return {@link List<Item>} A list containing all items in the database
	 */
	public static List<Item> getAllItems() {
		logger.trace("getAllItems() - Entering");
		List<Item> returnValue = new ArrayList<Item>();
		ObjectSet<Item> set = dbHandle.query(new Predicate<Item>() {

			private static final long serialVersionUID = -6535736734146443615L;

			@Override
			public boolean match(Item arg0) {
				return true;
			}
		});
		Iterator<Item> i = set.iterator();
		while (i.hasNext()) {
			returnValue.add(i.next());
		}
		logger.trace("getAllItems() - Returning");
		logger.debug("getAllItems() - Returning: {}", returnValue);
		return returnValue;
	}

	/**
	 * Saves the item given in the database
	 * 
	 * @param {@link Item item}
	 */
	public static void saveItem(Item item) {
		logger.trace("saveItem() - Entering");
		logger.debug("saveItem() - Parameter {}", item);
		dbHandle.store(item);
		logger.trace("saveItem() - Leaving");
	}

	/**
	 * Saves the item given in the database
	 * 
	 * @param {@link Item} item
	 */
	public static void deleteItem(Item item) {
		logger.trace("deleteItem() - Entering");
		logger.debug("deleteItem() - Parameter {}", item);
		dbHandle.delete(item);
		logger.trace("deleteItem() - Leaving");
	}

	/**
	 * Returns a {@link List<Item>} with item that have the given string in
	 * their name
	 * 
	 * @param text
	 *            {@link String}
	 * @return {@link List<Item>}
	 */
	public static List<Item> searchItemByName(final String text) {
		logger.trace("searchItemByName() - Entering");
		logger.debug("searchItemByName() - Parameter: {}", text);
		List<Item> returnValue = new ArrayList<Item>();
		ObjectSet<Item> set = dbHandle.query(new Predicate<Item>() {

			private static final long serialVersionUID = -6535736734146443615L;

			@Override
			public boolean match(Item arg0) {
				if (arg0.getName().toLowerCase().contains(text.toLowerCase())) {
					return true;
				} else {
					return false;
				}
			}
		});
		Iterator<Item> i = set.iterator();
		while (i.hasNext()) {
			returnValue.add(i.next());
		}
		logger.trace("searchItemByName() - Returning");
		logger.debug("searchItemByName() - Returning {}", returnValue);
		return returnValue;
	}

	/**
	 * Returns the {@link Item} with the given id or if not found<br>
	 * null
	 * 
	 * @param id
	 *            {@link int}
	 * @return {@link Item} or null
	 */
	public static Item getItemById(final int id) {
		logger.trace("getItemById() - Entering");
		logger.debug("getItemById() - Parameter: {}", id);
		ObjectSet<Item> set = dbHandle.query(new Predicate<Item>() {

			private static final long serialVersionUID = -6535736734146443615L;

			@Override
			public boolean match(Item arg0) {
				if (arg0.getId() == id) {
					return true;
				} else {
					return false;
				}
			}
		});
		Item returnValue = set.next();
		logger.trace("getItemById() - Returning");
		logger.debug("getItemById() - Returning {}", returnValue);
		return returnValue;
	}

	/**
	 * Returns all items for which the price is under 500
	 * 
	 * @return {@link List<Item>}
	 */
	public static List<Item> getAllStartingItems() {
		logger.trace("getAllStartingItems() - Entering");
		List<Item> returnValue = new ArrayList<Item>();
		ObjectSet<Item> set = dbHandle.query(new Predicate<Item>() {

			private static final long serialVersionUID = -6535736734146443615L;

			@Override
			public boolean match(Item arg0) {
				if (arg0.getPrice() <= 500) {
					return true;
				} else {
					return false;
				}
			}
		});
		Iterator<Item> i = set.iterator();
		while (i.hasNext()) {
			returnValue.add(i.next());
		}
		logger.trace("getAllStartingItems() - Returning");
		logger.debug("getAllStartingItems() - Returning {}", returnValue);
		return returnValue;
	}
}
