package main;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CurrentStandings {
	static String position;
	static String teamAheadOne;
	static String teamAheadTwo;
	static String teamBelowOne;
	static String teamBelowTwo;
	static String output;
	
	public static String getCurrentPosition() throws IOException {
	 			
			Document doc = Jsoup.connect(Config.leagueWeb).get();
			 
			Element standingsTable = doc.select("table.Tabelle.sortable").first();
			Elements standingsTableRows = standingsTable.select("tr");
			
		    for (int row = 0; row < standingsTableRows.size(); row++){
		    	 position = standingsTableRows.get(row).text();
			    	 
		    	 if (position.contains(Config.teamName)){
		    		 if (row == 16) {
		    			 teamAheadTwo = standingsTableRows.get(row - 2).text();
			    		 teamAheadOne = standingsTableRows.get(row - 1).text();
		    			 output = teamAheadTwo + Config.newLine + teamAheadOne + Config.newLine + position; 
		    		 }
		    		 else if (row >= 2 && row <= 15){
		    			 teamAheadOne = standingsTableRows.get(row - 1).text();
		    			 teamBelowOne = standingsTableRows.get(row + 1).text();
		    			 output = teamAheadOne + Config.newLine + position + Config.newLine + teamBelowOne;
		    		 }
		    		 else if (row == 1){
		    			 teamBelowOne = standingsTableRows.get(row + 1).text();
		    			 teamBelowTwo = standingsTableRows.get(row + 2).text();
		    			 output = position + Config.newLine + teamBelowOne + Config.newLine + teamBelowTwo; 
		    		 }
		    			 } 
		    		 }
			return output;

		    	 }
	}
