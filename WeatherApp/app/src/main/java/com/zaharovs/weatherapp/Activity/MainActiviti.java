package com.zaharovs.weatherapp.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.zaharovs.weatherapp.ClickInterface;
import com.zaharovs.weatherapp.Fragment.Fragment2;
import com.zaharovs.weatherapp.R;
import com.zaharovs.weatherapp.Helper.WHttp;
import com.zaharovs.weatherapp.Helper.WJson;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActiviti extends AppCompatActivity implements ClickInterface {

    // VALUE'S
    public static ArrayList<HashMap<String, String>> data = new ArrayList<>();

    private int itemPosition = 0;           // ITEM SELECTED BY DEFAULT
    private boolean withContent = true;     // SHOW TWO-PANE LAYOUT

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // ASYNC
        new AsyncWork().execute();

        // TOOLBAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        // LOAD SAVED ITEM POSITION
        if (savedInstanceState != null) {
            this.itemPosition = savedInstanceState.getInt("itemPosition");
        }

        // SEARCH FRAGMENT WITH CONTENT
        this.withContent = (findViewById(R.id.main_fragment_content) != null);

        if (withContent) {
            showContent(itemPosition);
        }
    }

    // SAVE ITEM POSITION
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("itemPosition", itemPosition);
    }

    // GET ITEM POSITION WITH FRAGMENT
    @Override
    public void clickItem(int position) {
        this.itemPosition = position;
        showContent(position);
    }

    private void showContent(int position) {
        if (withContent) {
            // TWO-PANE LAYOUT
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment2 fragment2 = (Fragment2) fragmentManager.findFragmentById(R.id.main_fragment_content);

            if (fragment2 == null || fragment2.getPosition() != position) {
                fragment2 = Fragment2.newInstance(position);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_content, fragment2);
                fragmentTransaction.commit();
            }
        } else {
            // ONE-PANE LAYOUT
            startActivity(new Intent(this, SecondActivity.class).putExtra("itemPosition", position));
        }
    }

    public class AsyncWork extends AsyncTask<Void, Void, ArrayList<HashMap<String, String>>> {
        @Override
        protected ArrayList<HashMap<String, String>> doInBackground(Void... params) {
            return WJson.getData(WHttp.getWeatherDate());
        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>> hashMaps) {
            super.onPostExecute(hashMaps);
            data = hashMaps;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}