package com.les.oquecomer.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class GerenciadorReceita {

	private List<JSONObject> mReceitas;

	public GerenciadorReceita() {
		mReceitas = new ArrayList<JSONObject>();
	}

	public void loadAndSyncronizedReceitas(String[] ingredientes) {

		mReceitas = new RequestManager().loadReceitas(ingredientes);
		
	}
	
	public List<JSONObject> getRecceitas() {
		return mReceitas;
	}

}