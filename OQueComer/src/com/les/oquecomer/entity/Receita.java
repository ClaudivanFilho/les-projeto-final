package com.les.oquecomer.entity;

public class Receita {
	
	private String mNome;
	private float mNota;
	private int mNumPessoas;
	private String mCategoria;
	private float mTempo;
	private String mInfoNutri;
	private String mModoPreparo;
	private Ingrediente[] mIngredientes;
	
	public Receita(){
		
	}
	
	public Receita(String nome, float nota, int numPessoas, String categoria, float tempo, String infoNutri, String modoPreparo, Ingrediente[] ingredientes){
		this.mNome = nome;
		this.mNota = nota;
		this.mNumPessoas = numPessoas;
		this.mCategoria = categoria;
		this.mTempo = tempo;
		this.mInfoNutri = infoNutri;
		this.mModoPreparo = modoPreparo;
		this.mIngredientes = ingredientes;
	}

	public String getmNome() {
		return mNome;
	}

	public void setmNome(String mNome) {
		this.mNome = mNome;
	}

	public float getmNota() {
		return mNota;
	}

	public void setmNota(float mNota) {
		this.mNota = mNota;
	}

	public int getmNumPessoas() {
		return mNumPessoas;
	}

	public void setmNumPessoas(int mNumPessoas) {
		this.mNumPessoas = mNumPessoas;
	}

	public String getmCategoria() {
		return mCategoria;
	}

	public void setmCategoria(String mCategoria) {
		this.mCategoria = mCategoria;
	}

	public float getmTempo() {
		return mTempo;
	}

	public void setmTempo(float mTempo) {
		this.mTempo = mTempo;
	}

	public String getmInfoNutri() {
		return mInfoNutri;
	}

	public void setmInfoNutri(String mInfoNutri) {
		this.mInfoNutri = mInfoNutri;
	}

	public String getmModoPreparo() {
		return mModoPreparo;
	}

	public void setmModoPreparo(String mModoPreparo) {
		this.mModoPreparo = mModoPreparo;
	}

	public Ingrediente[] getmIngredientes() {
		return mIngredientes;
	}

	public void setmIngredientes(Ingrediente[] mIngredientes) {
		this.mIngredientes = mIngredientes;
	}
	
}
