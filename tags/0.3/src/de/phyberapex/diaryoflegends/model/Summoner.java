package de.phyberapex.diaryoflegends.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
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
		if (Config.isInternetReachable()) {
			if (this.getName() == null
					|| Config.getInstance().getProperty("REGION") == null
					|| Config.getInstance().getProperty("SUMMONER_ID") == null) {
				returnValue = "no info available";
			} else {
				if (Config.getInstance().getProperty("REGION") == null
						|| Config.getInstance().getProperty("SUMMONER_ID") == null) {
					int[] id = Config.getInstance()
							.getSummonerIdByNameAndRegion(
									Config.getInstance().getProperty(
											"SUMMONER_NAME"),
									Region.valueOf(Config.getInstance()
											.getProperty("REGION")));
					if (id[0] != 0) {
						Config.getInstance().setProperty("SUMMONER_ID",
								String.valueOf(id[0]));
					}
					if (id[1] != 0) {
						Config.getInstance().setProperty("ACCOUNT_ID",
								String.valueOf(id[1]));
					}
					Config.getInstance().saveChanges();
				}
				returnValue += this.getName() + " | ";
				try {
					returnValue += this.getLeagueForSummoner() + " | ";
					int streak = this.getStreakForSummoner();
					if (streak < 0) {
						returnValue += "L" + Math.abs(streak);
					} else if (streak > 0) {
						returnValue += "W" + streak;
					} else {
						returnValue += "No streak";
					}
				} catch (IOException e) {
					logger.error("Couldn't connect to the elophant api");
					returnValue = "no info available";
				}
			}
		} else {
			returnValue = "no info available";
		}

		logger.debug("getSummonerInfoString() - Returning: {}", returnValue);
		return returnValue;
	}

	public int getStreakForSummoner() throws MalformedURLException, IOException {
		int returnValue = 0;
		URLConnection con = new URL("http://api.elophant.com/v2/"
				+ Config.getInstance().getProperty("REGION") + "/recent_games/"
				+ Config.getInstance().getProperty("ACCOUNT_ID") + "?key="
				+ Config.getInstance().getProperty("API_KEY")).openConnection();
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
			boolean streakOver = false;
			for (int i = games.length() - 1; i > 0; i--) {
				if (streakOver) {
					break;
				}
				String type = games.getJSONObject(i).getString("queueType");
				if (type.equals("NORMAL") || type.equals("RANKED_SOLO_5x5")) {
					for (int j = 0; j < games.getJSONObject(i)
							.getJSONArray("statistics").length(); j++) {
						if (games.getJSONObject(i).getJSONArray("statistics")
								.getJSONObject(j).getString("statType")
								.equals("WIN")) {
							if (returnValue >= 0) {
								returnValue++;
							} else {
								streakOver = true;
							}
							break;
						} else if (games.getJSONObject(i)
								.getJSONArray("statistics").getJSONObject(j)
								.getString("statType").equals("LOSE")) {
							if (returnValue <= 0) {
								returnValue--;
							} else {
								streakOver = true;
							}
							break;
						}
					}
				}
			}
		}
		return returnValue;
	}

	public String getLeagueForSummoner() throws IOException {
		logger.trace("getLeagueForSummoner() - Entering");
		String returnValue = "";
		URLConnection con = new URL("http://api.elophant.com/v2/"
				+ Config.getInstance().getProperty("REGION") + "/leagues/"
				+ Config.getInstance().getProperty("SUMMONER_ID") + "?key="
				+ Config.getInstance().getProperty("API_KEY")).openConnection();
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
					returnValue += leagues.getJSONObject(i).getString("tier")
							+ " ";
					returnValue += leagues.getJSONObject(i).getString(
							"requestorsRank");
				}
			}
		}
		logger.trace("getLeagueForSummoner() - Returning");
		logger.debug("getLeagueForSummoner() - Returning: {}", returnValue);
		return returnValue;
	}
}