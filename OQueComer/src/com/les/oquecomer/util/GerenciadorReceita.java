package com.les.oquecomer.util;

import java.util.ArrayList;
import java.util.List;

import com.les.oquecomer.entity.Receita;

public class GerenciadorReceita {

	private List<Receita> mReceitas;

	public GerenciadorReceita() {
		mReceitas = new ArrayList<Receita>();
	}

	public void loadAndSyncronizedReceitas(String[] ingredientes) {

		mReceitas = new RequestManager().loadReceitas(ingredientes);

	}

}