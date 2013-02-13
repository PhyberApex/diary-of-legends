package de.phyberapex.diaryoflegends.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.model.Item;
import de.phyberapex.diaryoflegends.model.util.ItemUtil;
import de.phyberapex.diaryoflegends.view.ChampionView;
import de.phyberapex.diaryoflegends.view.ItemView;

public class ItemController extends Controller {

	private List<Item> items = new ArrayList<Item>();
	transient private static Logger logger = LogManager
			.getLogger(ItemController.class.getName());

	public ItemController(MainController mainController) {
		super(mainController);
		logger.trace("ItemController() - Entering");
		logger.debug("ItemController() - Parameter: {}", mainController);
		items = ItemUtil.getAllItems();
		this.models.addAll(items);
		logger.trace("ItemController() - Leaving");
	}

	@Override
	public void loadGUI() {
		logger.trace("loadGUI() - Entering");
		this.view = new ItemView(this);
		logger.trace("loadGUI() - Leaving");
	}

	@Override
	public void loadData() {
		logger.trace("loadData() - Entering");
		logger.trace("loadData() - Leaving");
	}

	/**
	 * Returns all items
	 * 
	 * @return {@link List<Item>} A list with all items
	 */
	public List<Item> getItems() {
		logger.trace("getItems() - Entering");
		logger.trace("getItems() - Returning");
		logger.debug("getItems() - Returning: {}", items);
		return this.items;
	}

	/**
	 * Returns all items which have the searchString in their name
	 * 
	 * @param text
	 *            {@link String} searchString
	 * @return {@link List<Item>} List of items which have the searchString in
	 *         their name
	 */
	public List<Item> searchItems(String text) {
		logger.trace("searchItems() - Entering");
		logger.debug("searchItems() - Parameter: {}", text);
		List<Item> returnValue = ItemUtil.searchItemByName(text);
		logger.trace("searchItems() - Returning");
		logger.debug("searchItems() - Returning: {}", returnValue);
		return returnValue;
	}
}
