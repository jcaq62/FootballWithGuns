package com.example.footballwithguns;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.footballwithguns.MESSAGE";

    public JSONArray orderedJSON = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button refreshButton = new Button(this);

        importData(refreshButton);


    }

    public void importData(View view){
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = getString(R.string.api_url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray allValues = response.getJSONArray("values");
                    JSONArray holderArray = new JSONArray();

                    JSONArray index = allValues.getJSONArray(0);

                    //1. Get the first value of the index - indicating whether it is a weapon, unit, etc.
                    //2. Compare the index value to the current value.
                    //3. If they match, add the current array in allValues to the holder array
                    //4. If they do not match, add the current holder Array to the sorted results array
                    //   then clear the array and start over with the new values.
                    // This function allows for an arbitrary number of distinct types of objects
                    // to be created, as long as all objects in the document are grouped together
                    for(int i = 0; i < allValues.length(); i++){
                        if(index.getString(0).equals(allValues.getJSONArray(i).getString(0))){
                            holderArray.put(allValues.getJSONArray(i));

                        } else{
                            orderedJSON.put(holderArray);
                            holderArray = new JSONArray();
                            index = allValues.getJSONArray(i);
                            holderArray.put(allValues.getJSONArray(i));
                        }
                    }

                    orderedJSON.put(holderArray);
                }
                catch(Exception e){
                    System.out.println("Malformed JSON Error");
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Error Handler
                System.out.println("Volley/network error");
            }
        });
        queue.add(jsonObjectRequest);
    }

    public void searchButton(View view) throws JSONException {

        String[] searchArray = new String[3];
        searchArray[0] = orderedJSON.toString();

        Intent intent = new Intent(this, DisplayData.class);

        Spinner category = (Spinner) findViewById(R.id.displayCategory);
        EditText searchBox = (EditText) findViewById(R.id.searchTextBox);

        searchArray[1] = category.getSelectedItem().toString();
        searchArray[2] = searchBox.getText().toString();

        intent.putExtra(EXTRA_MESSAGE, searchArray);
        startActivity(intent);
    }


}