package main;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class LastMatchResult {

	public static String match;
	public static String opponent;
	
	public static String getMatch() throws IOException {
		
		Document doc = Jsoup.connect(Config.leagueWeb).get();

		Elements matchTable = doc.select("table.Spiele");
		Elements matchTableRows = matchTable.select("tr");

		for (int i = 1; i < matchTableRows.size() - 1; i++) {
			match = matchTableRows.get(i).text();
			if (match.contains(Config.teamName)) {
				break;
			}
		}
		return match;

	}
}

