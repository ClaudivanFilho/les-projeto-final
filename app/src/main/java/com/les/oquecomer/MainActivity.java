
package com.les.oquecomer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.les.oquecomer.util.GerenciadorReceita;

public class MainActivity extends Activity implements OnClickListener {

	private EditText itemsInput;
	LayoutInflater inflater;
	LinearLayout generalLayout;
	GerenciadorReceita gerenciador;
	String[] ingredientes = {};
	static ListView listField;
	static ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.search);

        // oculta o teclado
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		listField = (ListView) findViewById(R.id.listView1);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, 
				android.R.id.text1, new ArrayList<String>());
		listField.setAdapter(adapter);
		
		gerenciador = new GerenciadorReceita();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		//if(arg0 == recipeSearch){

		//}
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
	
	public void searchReceitas(View v) {
		itemsInput = (EditText) findViewById(R.id.items_text_input);
		String ingrediente = itemsInput.getText().toString() ;
		ingredientes = new String[1];
		ingredientes[0] = ingrediente;
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(itemsInput.getWindowToken(), 0);
		new AtualizaListagem(this).execute();
	}
	
	class AtualizaListagem extends AsyncTask<URL, Integer, Long> {
        private ProgressDialog dialog;

        public AtualizaListagem(MainActivity activity) {
            dialog = new ProgressDialog(activity);
        }

		protected Long doInBackground(URL... urls) {
            try {
                gerenciador.loadAndSyncronizedReceitas(ingredientes);
            } catch (IOException e) {
                Toast.makeText(MainActivity.this, "Erro na requisição!", Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                Toast.makeText(MainActivity.this, "Erro com a resposta do servidor!", Toast.LENGTH_SHORT).show();
            }
            return 0L;
		}

		protected void onProgressUpdate(Integer... progress) {
		}

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Pesquisando Receitas. Por Favor Aguarde!");
            dialog.show();
        }

		protected void onPostExecute(Long result) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
			adapter.clear();
			for(JSONObject j: gerenciador.getRecceitas()) {
				try {
					adapter.add(j.getString("nome") + "\n" + "Nota:" + j.getString("nota"));
				} catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "Erro ao adicionar Receita à lista.!", Toast.LENGTH_SHORT).show();
				}
			}
		}
	}

}
