package de.phyberapex.diaryoflegends.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MatchupItem {

	private Item item;
	private int amount;
	private static Logger logger = LogManager.getLogger(MatchupItem.class
			.getName());

	public MatchupItem() {
	}

	public MatchupItem(Item item, int amount) {
		this.item = item;
		this.amount = amount;
	}

	public Item getItem() {
		logger.trace("getItem() - Entering");
		logger.trace("getItem() - Returning");
		logger.debug("getItem() - Returning: {}", item);
		return item;
	}

	public void setItem(Item item) {
		logger.trace("setItem() - Entering");
		logger.debug("setItem() - Parameter: {}", item);
		this.item = item;
		logger.trace("setItem() - Leaving");
	}

	public int getAmount() {
		logger.trace("getAmount() - Entering");
		logger.trace("getAmount() - Returning");
		logger.debug("getAmount() - Returning: {}", amount);
		return amount;
	}

	public void setAmount(int amount) {
		logger.trace("setAmount() - Entering");
		logger.debug("setAmount() - Parameter: {}", amount);
		this.amount = amount;
		logger.trace("setAmount() - Leaving");
	}
}
