package com.les.oquecomer.entity;

public class Ingrediente {

	private String mNome;
	private float mQuantidade;
	private String mMedida;
	
	public Ingrediente(){}
	
	public Ingrediente(String nome, float quantidade, String medida){
		this.mNome = nome;
		this.mQuantidade = quantidade;
		this.mMedida = medida;
	}

	public String getmNome() {
		return mNome;
	}

	public void setmNome(String mNome) {
		this.mNome = mNome;
	}

	public float getmQuantidade() {
		return mQuantidade;
	}

	public void setmQuantidade(float mQuantidade) {
		this.mQuantidade = mQuantidade;
	}

	public String getmMedida() {
		return mMedida;
	}

	public void setmMedida(String mMedida) {
		this.mMedida = mMedida;
	}
	
}
