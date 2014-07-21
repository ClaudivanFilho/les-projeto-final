package com.les.oquecomer.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
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
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String response = client.execute(get, responseHandler);
		List<JSONObject> objectsReceitas = new ArrayList<JSONObject>();
		JSONArray arrayDeReceitas = new JSONArray(response);
		for(int i=0; i< arrayDeReceitas.length(); i++) {
			objectsReceitas.add(arrayDeReceitas.getJSONObject(i));
		}
		return objectsReceitas;
	}
}
