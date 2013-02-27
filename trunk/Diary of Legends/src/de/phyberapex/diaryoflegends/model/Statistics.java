package de.phyberapex.diaryoflegends.model;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Statistics {

	private StatisticTypes type;
	private HashMap<String, Object> stats = new HashMap<>();
	transient private static Logger logger = LogManager
			.getLogger(Statistics.class.getName());

	public StatisticTypes getType() {
		logger.trace("getType() - Entering");
		logger.trace("getType() - Returning");
		logger.debug("getType() - Returning: {}", type);
		return type;
	}

	public void setType(StatisticTypes type) {
		logger.trace("setType() - Entering");
		logger.debug("setType() - Parameter: {}", type);
		this.type = type;
		logger.trace("setType() - Leaving");
	}

	public HashMap<String, Object> getStats() {
		logger.trace("getStats() - Entering");
		logger.trace("getStats() - Returning");
		logger.debug("getStats() - Returning: {}", stats);
		return stats;
	}

	public void addStats(String key, Object value) {
		logger.trace("addStats() - Entering");
		logger.debug("addStats() - Parameter: {}, {}", key, value);
		stats.put(key, value);
		logger.trace("addStats() - Leaving");

	}
}
