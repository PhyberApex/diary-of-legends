package de.phyberapex.diaryoflegends.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.phyberapex.diaryoflegends.model.Item;
import de.phyberapex.diaryoflegends.model.util.ItemUtil;
import de.phyberapex.diaryoflegends.view.ItemView;

public class ItemController extends Controller {

	private List<Item> items;
	transient private static Logger logger = LogManager
			.getLogger(ItemController.class.getName());

	public ItemController(MainController mainController) {
		super(mainController);
		logger.trace("ItemController() - Entering");
		logger.debug("ItemController() - Parameter: {}", mainController);
		this.view = new ItemView(this);

		items = ItemUtil.getAllItems();
		this.models.addAll(items);
		logger.trace("ItemController() - Leaving");
	}

	@Override
	public void loadGUI() {

	}

	@Override
	public void loadData() {
		// TODO Auto-generated method stub

	}
}
