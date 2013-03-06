package de.phyberapex.diaryoflegends.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import de.phyberapex.diaryoflegends.base.Config;

public class Summoner extends Model {

	private String name;
	transient private static Logger logger = LogManager
			.getLogger(Summoner.class.getName());

	public Summoner(String name) {
		logger.trace("Summoner() - Entering");
		logger.debug("Parameter {}", name);
		this.name = name;
		this.name = name;
		logger.trace("Summoner() - Leaving");
	}

	/**
	 * Returns the name of the summoner
	 * 
	 * @return {@link String}
	 */
	public String getName() {
		logger.trace("getName() - Entering");
		logger.trace("getName() - Returning");
		logger.debug("Returned {}", name);
		return name;
	}

	/**
	 * Sets the name of the summoner
	 * 
	 * @param name
	 *            {@link String} new name to set
	 */
	public void setName(String name) {
		logger.trace("setName() - Entering");
		logger.debug("Parameter {}", name);
		this.name = name;
		logger.trace("setName() - Leaving");
	}

	/**
	 * @return
	 */
	public String getSummonerInfoString() {
		logger.trace("getSummonerInfoString() - Entering");
		logger.trace("getSummonerInfoString() - Returning");
		String returnValue = "";
		if (this.getName() == null
				|| Config.getInstance().getProperty("REGION") == null
				|| Config.getInstance().getProperty("SUMMONER_ID") == null) {
			returnValue = "no info available";
		} else {
			returnValue += this.getName() + " | ";
			returnValue += this.getLeagueForSummoner() + " | ";
			int streak = this.getStreakForSummoner();
			if (streak < 0) {
				returnValue += "L" + streak;
			} else if (streak > 0) {
				returnValue += "W" + streak;
			} else {
				returnValue += "No streak";
			}
		}
		logger.debug("getSummonerInfoString() - Returning: {}", returnValue);
		return returnValue;
	}

	public int getStreakForSummoner() {
		int returnValue = 0;
		try {
			URLConnection con = new URL("http://api.elophant.com/v2/"
					+ Config.getInstance().getProperty("REGION")
					+ "/recent_games/"
					+ Config.getInstance().getProperty("ACCOUNT_ID") + "?key="
					+ Config.getInstance().getProperty("API_KEY"))
					.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			String json = "";
			while ((inputLine = in.readLine()) != null)
				json += inputLine;
			in.close();
			JSONObject data = new JSONObject(json);
			if (data.getBoolean("success")) {
				JSONArray games = data.getJSONObject("data").getJSONArray(
						"gameStatistics");
				for (int i = games.length() - 1; i >= 0; i--) {
					if (games.getJSONObject(i).getBoolean("ranked")) {
						for (int j = 0; j < games.getJSONObject(i)
								.getJSONArray("statistics").length(); j++) {
							if (games.getJSONObject(i)
									.getJSONArray("statistics")
									.getJSONObject(j).getString("statType")
									.equals("WIN")) {
								if (games.getJSONObject(i)
										.getJSONArray("statistics")
										.getJSONObject(j).getInt("value") == 1) {
									if (returnValue < 0) {
										returnValue = 0;
									}
									returnValue++;
								} else {
									if (returnValue > 0) {
										returnValue = 0;
									}
									returnValue--;
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.debug("Error: {}" + e.getMessage());
			logger.error("Couldn't connect to api.elophant.com summonerID and accountID not set");
		}
		return returnValue;
	}

	public String getLeagueForSummoner() {
		logger.trace("getLeagueForSummoner() - Entering");
		String returnValue = "";
		try {
			URLConnection con = new URL("http://api.elophant.com/v2/"
					+ Config.getInstance().getProperty("REGION") + "/leagues/"
					+ Config.getInstance().getProperty("SUMMONER_ID") + "?key="
					+ Config.getInstance().getProperty("API_KEY"))
					.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			String json = "";
			while ((inputLine = in.readLine()) != null)
				json += inputLine;
			in.close();
			JSONObject data = new JSONObject(json);
			if (data.getBoolean("success")) {
				JSONArray leagues = data.getJSONObject("data").getJSONArray(
						"summonerLeagues");
				for (int i = 0; i < leagues.length(); i++) {
					if (leagues.getJSONObject(i).getString("queue")
							.equals("RANKED_SOLO_5x5")) {
						returnValue += leagues.getJSONObject(i).getString(
								"tier")
								+ " ";
						returnValue += leagues.getJSONObject(i).getString(
								"requestorsRank");
					}
				}
			}
		} catch (IOException e) {
			logger.debug("Error: {}" + e.getMessage());
			logger.error("Couldn't connect to api.elophant.com summonerID and accountID not set");
		}
		logger.trace("getLeagueForSummoner() - Returning");
		logger.debug("getLeagueForSummoner() - Returning: {}", returnValue);
		return returnValue;
	}
}