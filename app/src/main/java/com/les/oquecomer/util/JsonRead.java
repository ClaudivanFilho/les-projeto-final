package com.les.oquecomer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonRead {

	/**
	 * Retorna uma listagem de JSonObjects realativo as receitas
	 * 
	 * @param url
	 *            (e.g "https://graph.facebook.com/19292868552")
	 */
	public static List<JSONObject> readJsonFromUrl(String url) throws IOException,
			JSONException {
		InputStream is = new URL(url).openStream();
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(is,
				Charset.forName("UTF-8")));
		String response = readAll(rd);
		List<JSONObject> objectsReceitas = new ArrayList<JSONObject>();
		JSONArray arrayDeReceitas = new JSONArray(response);
		for(int i=0; i< arrayDeReceitas.length(); i++) {
			objectsReceitas.add(arrayDeReceitas.getJSONObject(i));
		}
		return objectsReceitas;
	}
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
}
