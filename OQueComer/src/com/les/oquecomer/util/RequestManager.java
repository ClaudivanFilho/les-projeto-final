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
		
		try {
			String url = AppConfig.HOST_PORT;
			if(nomeIngredientes.length > 0){
				url = url+nomeIngredientes[0];
			}
			for (int i = 1; i< nomeIngredientes.length;i++){
				url = url+","+nomeIngredientes[i];
			}
			receitasLista = JsonRead.readJsonFromUrl(url);

		} catch (IOException e) {
			Log.d("Load Receitas Erro IO", e.getMessage());
		} catch (JSONException e) {
			Log.d("Load Receitas Erro JSON", e.getMessage());
		} catch (Exception e){
			Log.d("trace geral", "message: "+e.getMessage()+" trace: "+e.getStackTrace().toString()+" cause: "+e.getCause());
		}
		return receitasLista;
	}
}