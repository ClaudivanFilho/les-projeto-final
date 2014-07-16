
package com.les.oquecomer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private ImageButton recipeSearch;
	private ImageButton recipeBook;
	private ImageButton facebook;
	private ImageButton site;
	LayoutInflater inflater;
	LinearLayout generalLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		generalLayout = (LinearLayout) findViewById(R.id.generalLayout);
		recipeSearch = (ImageButton) findViewById(R.id.recipeSearch);
		recipeBook = (ImageButton) findViewById(R.id.recipeBook);
		facebook = (ImageButton) findViewById(R.id.facebook);
		site = (ImageButton) findViewById(R.id.site);
		recipeSearch.setOnClickListener(this);
		recipeBook.setOnClickListener(this);
		facebook.setOnClickListener(this);
		site.setOnClickListener(this);
		generalLayout.removeAllViews();
		LinearLayout recipeBookView = (LinearLayout) inflater.inflate(R.layout.recipe_book_view, null);
		generalLayout.addView(recipeBookView);

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
			generalLayout.removeAllViews();
			LinearLayout search = (LinearLayout) inflater.inflate(R.layout.search, null);
			generalLayout.addView(search);
			Toast.makeText(this, "Not Implemented Yet", Toast.LENGTH_SHORT).show();
		}
		else if(arg0 == recipeBook){
			generalLayout.removeAllViews();
			LinearLayout recipeBookView = (LinearLayout) inflater.inflate(R.layout.recipe_book_view, null);
			generalLayout.addView(recipeBookView);
			Toast.makeText(this, "Not Implemented Yet", Toast.LENGTH_SHORT).show();
		}
		else if(arg0 == facebook){
			Toast.makeText(this, "Not Implemented Yet", Toast.LENGTH_SHORT).show();
		}
		else if(arg0 == site){
			Toast.makeText(this, "Not Implemented Yet", Toast.LENGTH_SHORT).show();
		}

	}

}
