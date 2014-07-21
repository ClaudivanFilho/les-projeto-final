
package com.les.oquecomer;

import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.les.oquecomer.util.GerenciadorReceita;

public class MainActivity extends Activity implements OnClickListener {

	private ImageButton recipeSearch;
	private ImageButton recipeBook;
	private ImageButton facebook;
	private ImageButton site;
	private ImageButton searchDone;
	private EditText itemsInput;
	LayoutInflater inflater;
	LinearLayout generalLayout;
	GerenciadorReceita gerenciador;
	String[] ingredientes = {"agua","gengibre"};
	static ListView listField;
	static ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.search);
//		generalLayout = (LinearLayout) findViewById(R.id.generalLayout);
//		recipeSearch = (ImageButton) findViewById(R.id.recipeSearch);
//		recipeBook = (ImageButton) findViewById(R.id.recipeBook);
//		facebook = (ImageButton) findViewById(R.id.facebook);
//		site = (ImageButton) findViewById(R.id.site);
		//recipeSearch.setOnClickListener(this);
//		recipeBook.setOnClickListener(this);
//		facebook.setOnClickListener(this);
//		site.setOnClickListener(this);
		//generalLayout.removeAllViews();
//		LinearLayout recipeBookView = (LinearLayout) inflater.inflate(R.layout.recipe_book_view, null);
//		generalLayout.addView(recipeBookView);
//		
		listField = (ListView) findViewById(R.id.listView1);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, 
				android.R.id.text1, new ArrayList<String>());
		listField.setAdapter(adapter);
		
		gerenciador = new GerenciadorReceita();
		
		new AtualizaListagem().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		if(arg0 == recipeSearch){
			initSearch();
		}
//		else if(arg0 == recipeBook){
//			generalLayout.removeAllViews();
//			LinearLayout recipeBookView = (LinearLayout) inflater.inflate(R.layout.recipe_book_view, null);
//			generalLayout.addView(recipeBookView);
//			Toast.makeText(this, "Not Implemented Yet", Toast.LENGTH_SHORT).show();
//		}
//		else if(arg0 == facebook){
//			Toast.makeText(this, "Not Implemented Yet", Toast.LENGTH_SHORT).show();
//		}
//		else if(arg0 == site){
//			Toast.makeText(this, "Not Implemented Yet", Toast.LENGTH_SHORT).show();
//		}
//		else if (arg0 == searchDone){
//			Toast.makeText(this, "Shoud be Searching By Now: " + itemsInput.getText(), Toast.LENGTH_SHORT).show();
//		}

	}
	
	public void initSearch() {
		setContentView(R.layout.search);
		//searchDone.setOnClickListener(this);
	}
	
	public void searchReceitas(View v) {
		itemsInput = (EditText) findViewById(R.id.items_text_input);
		String ingrediente = itemsInput.getText().toString() ;
		ingredientes = new String[1];
		ingredientes[0] = ingrediente;
		
		new AtualizaListagem().execute();
	}
	
	class AtualizaListagem extends AsyncTask<URL, Integer, Long> {
		/**
		 * É executado em uma nova thread, para fazer a requisição.
		 */
		protected Long doInBackground(URL... urls) {
			// carrega os tis remotamente com base no usuario de id=1
			gerenciador.loadAndSyncronizedReceitas(ingredientes); 
			return 0L;
		}

		protected void onProgressUpdate(Integer... progress) {
			// setProgressPercen(progress[0]);
		}
		/**
		 * É executado ao fim do doInBackground e é chamado na thread original
		 * da activity.
		 */
		protected void onPostExecute(Long result) {
			adapter.clear();
			for(JSONObject j: gerenciador.getRecceitas()) {
				try {
					adapter.add(j.getString("nome"));
				} catch (JSONException e) {
					Log.d("EROO", e.getMessage());
				}
			}
		}
	}

}
