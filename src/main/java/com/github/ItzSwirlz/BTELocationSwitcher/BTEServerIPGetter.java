package com.github.ItzSwirlz.BTELocationSwitcher;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BTEServerIPGetter {

	// Pretty much this ENTIRE file is copied from resources online.
	// Full disclosure - copied mainly from GeeksForGeeks and Baeldung
	public static String getIp(String serverAlias) throws MalformedURLException, IOException, ParseException {
		
		// first, download the json file of our server list
		InputStream in = new URL("https://raw.githubusercontent.com/ItzSwirlz/BTELocationSwitcher/main-1.12.2/servers.json").openStream();
		Files.copy(in, Paths.get("servers.json"), StandardCopyOption.REPLACE_EXISTING);

		// now lets parse our JSON file
		Object o = new JSONParser().parse(new FileReader("servers.json"));

		JSONObject obj = (JSONObject) o;
		
		// Sanity check
		switch(serverAlias) {
		case "":
			serverAlias = "bte";
			break;
		}
		
		// Get our server IP!
		// THIS is the exciting part
		String serverIp = (String) obj.get(serverAlias);
		
		System.out.println(serverIp);
		return serverIp;
	}

}
