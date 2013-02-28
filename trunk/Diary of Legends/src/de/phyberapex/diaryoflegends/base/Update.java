package de.phyberapex.diaryoflegends.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import de.phyberapex.diaryoflegends.model.Champion;
import de.phyberapex.diaryoflegends.model.Item;
import de.phyberapex.diaryoflegends.model.util.ChampionUtil;
import de.phyberapex.diaryoflegends.model.util.ItemUtil;

public class Update {

	private static Logger logger = LogManager.getLogger(Update.class.getName());

	public static void update() {
		logger.trace("update() - Entering");
		List<Item> allItemsList = ItemUtil.getAllItems();
		List<Champion> allChampionList = ChampionUtil.getAllChampions();
		try {
			JSONArray allItemsArray = getItemsFromElophant().getJSONArray(
					"data");
			for (int i = 0; i < allItemsArray.length(); i++) {
				JSONObject itemObject = allItemsArray.getJSONObject(i);
				Item tmp = new Item(itemObject.getInt("id"),
						itemObject.getString("name"), null);
				boolean already = false;
				for (Item item : allItemsList) {
					if (item.getId() == tmp.getId()) {
						already = true;
						break;
					}
				}
				if (already) {
					logger.debug(
							"Item with id {} already in database skipping",
							tmp.getId());
				} else {
					tmp.setIcon(new URL(
							"http://img.lolking.net/shared/riot/images/items/"
									+ tmp.getId() + "_64.png"));
					ItemUtil.saveItem(tmp);
				}
			}
			JSONArray allChampionsArray = getChampionsFromElophant()
					.getJSONArray("data");
			for (int i = 0; i < allChampionsArray.length(); i++) {
				JSONObject champObject = allChampionsArray.getJSONObject(i);
				Champion tmp = new Champion(champObject.getInt("id"),
						champObject.getString("name"), null);
				boolean already = false;
				for (Champion champ : allChampionList) {
					if (champ.getId() == tmp.getId()) {
						already = true;
						break;
					}
				}
				if (already) {
					logger.debug(
							"Champion with id {} already in database skipping",
							tmp.getId());
				} else {
					tmp.setIcon(new URL(
							"http://img.lolking.net/shared/riot/images/champions/"
									+ tmp.getId() + "_104.png"));
					ChampionUtil.saveChampion(tmp);
				}
			}
			logger.trace("update() - Leaving");
		} catch (IOException e) {

		}
	}

	/**
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	private static JSONObject getItemsFromElophant()
			throws MalformedURLException, IOException {
		URLConnection con = new URL("http://api.elophant.com/v2/items?key="
				+ Config.getInstance().getProperty("API_KEY")).openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		String json = "";
		while ((inputLine = in.readLine()) != null)
			json += inputLine;
		in.close();
		return new JSONObject(json);
	}

	/**
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	private static JSONObject getChampionsFromElophant()
			throws MalformedURLException, IOException {
		URLConnection con = new URL("http://api.elophant.com/v2/champions?key="
				+ Config.getInstance().getProperty("API_KEY")).openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		String json = "";
		while ((inputLine = in.readLine()) != null)
			json += inputLine;
		in.close();
		return new JSONObject(json);
	}
}
