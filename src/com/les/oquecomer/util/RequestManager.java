package com.les.oquecomer.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Responsavel por gerenciar as requisições para o servidor remoto.
 */
public class RequestManager {

	public List<JSONObject> loadReceitas(String[] nomeIngredientes) {
		List<JSONObject> receitasLista = new ArrayList<JSONObject>();
		
			String url = AppConfig.HOST_PORT;
			if(nomeIngredientes.length > 0){
				url = url+nomeIngredientes[0];
			}
			for (int i = 1; i< nomeIngredientes.length;i++){
				url = url+","+nomeIngredientes[i];
			}
			try {
				receitasLista = JsonRead.readJsonFromUrl(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		return receitasLista;
	}
}