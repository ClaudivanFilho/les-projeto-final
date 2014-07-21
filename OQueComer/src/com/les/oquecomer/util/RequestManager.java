package com.les.oquecomer.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.les.oquecomer.entity.Receita;

/**
 * Responsavel por gerenciar as requisições para o servidor remoto.
 */
public class RequestManager {

	public List<Receita> loadReceitas(String[] nomeIngredientes) {
		List<Receita> receitas = null;
		JSONObject json;
		try {
			String url = AppConfig.HOST_PORT;
			
			if(nomeIngredientes.length > 0){
				url = url+nomeIngredientes[0];
			}
			for (int i = 1; i< nomeIngredientes.length;i++){
				url = url+","+nomeIngredientes[i];
			}
			json = JsonRead.readJsonFromUrl(url);
		
			JSONArray receitasJSON = json.getJSONArray("receitas");

			for (int i = 0; i < receitasJSON.length(); i++) {
				JSONObject child = receitasJSON.getJSONObject(i);

				String nome = child.getString("nome");
				float nota = Float.parseFloat(child.getString("nota"));
				int numPessoas = child.getInt("numPessoas");
				String categoria = child.getString("categoria");
				float tempo = Float.parseFloat(child.getString("tempo"));
				String infoNutri = child.getString("infoNutri");
				String modoPreparo = child.getString("modoPreparo");
				
				JSONArray ingredientesJSON = child.getJSONArray("ingredientes");
				
				for(int j = 0; j < ingredientesJSON.length();j++){
					JSONObject grandchild = ingredientesJSON.getJSONObject(j);
					
					String medida = grandchild.getString("medida");
					float quantidade = Float.parseFloat(grandchild.getString("quantidade"));
					String ingrediente = grandchild.getString("ingrediente");
					Log.e("resultado ingrediente:", ingrediente);
					
				}
				
				//String[] ingredientesTemp = child.getString("ingredientes").split(",");
				
				
//				Receita receita = new Receita(nome,nota,numPessoas,categoria,tempo,infoNutri,modoPreparo, ingredientes);
				
			}

		} catch (IOException e) {
			Log.d("Load Receitas Erro IO", e.getMessage());
		} catch (JSONException e) {
			Log.d("Load Receitas Erro JSON", e.getMessage());
		}
		return receitas;
	}
}