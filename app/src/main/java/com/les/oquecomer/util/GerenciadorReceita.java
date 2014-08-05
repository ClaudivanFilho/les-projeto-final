package com.les.oquecomer.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class GerenciadorReceita {

	private List<JSONObject> mReceitas;

	public GerenciadorReceita() {
		mReceitas = new ArrayList<JSONObject>();
	}

	public void loadAndSyncronizedReceitas(String[] ingredientes) throws IOException, JSONException {

		mReceitas = new RequestManager().loadReceitas(ingredientes);
		
	}
	
	public List<JSONObject> getRecceitas() {
		return mReceitas;
	}

}