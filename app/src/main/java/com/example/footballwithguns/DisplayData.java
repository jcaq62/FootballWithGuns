package com.example.footballwithguns;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DisplayData extends AppCompatActivity {

    JSONArray orderedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        LinearLayout dataLayout = findViewById(R.id.DataLayout);

        String[] searchArray = getIntent().getStringArrayExtra(MainActivity.EXTRA_MESSAGE);


        try {
            orderedData = new JSONArray(searchArray[0]);

            if(!searchArray[2].equals("")){
                orderedData = searchFullArray(orderedData, searchArray[2]);
            }

            if(searchArray[1].toString().equals("Weapons")){
                addWeapons(dataLayout, orderedData.getJSONArray(0));
            } else if(searchArray[1].toString().equals("Units")){
                addUnits(dataLayout, orderedData.getJSONArray(1));
            } else {
                addWeapons(dataLayout, orderedData.getJSONArray(0));
                addUnits(dataLayout, orderedData.getJSONArray(1));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONArray searchFullArray(JSONArray targetJSONArray, String searchElement) throws JSONException {
        JSONArray searchedArray = new JSONArray();
        for(int i = 0; i < targetJSONArray.length(); i++){
            searchedArray.put(searchArray(targetJSONArray.getJSONArray(i), searchElement));
        }
        return searchedArray;
    }

    public JSONArray searchArray(JSONArray targetJSONArray, String searchElement) throws JSONException {
        JSONArray searchedArray = new JSONArray();
        for(int i = 0; i < targetJSONArray.length(); i++){
            String targetString = targetJSONArray.getString(i).toUpperCase();
            int index = targetString.indexOf(searchElement.toUpperCase());

            if(index != -1){
                searchedArray.put(targetJSONArray.get(i));
            }

        }

        return searchedArray;

    }

    public void addUnits(LinearLayout targetLayout, JSONArray unitArray) throws JSONException{
        for(int i = 0; i < unitArray.length(); i++){
            addUnit(targetLayout, unitArray.getJSONArray(i));
        }
    }

    public void addWeapons(LinearLayout targetLayout, JSONArray weaponArray) throws JSONException {
        for(int i = 0; i < weaponArray.length(); i++){
            addWeapon(targetLayout, weaponArray.getJSONArray(i));
        }
    }

    public void addUnit(LinearLayout targetLayout, JSONArray unitArray){
        try {
            LinearLayout.LayoutParams spaceLP = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);

            LinearLayout row1 = new LinearLayout(this);
            LinearLayout row2 = new LinearLayout(this);
            LinearLayout row3 = new LinearLayout(this);

            List<TextView> unitElements = new ArrayList<TextView>();

            for (int i = 0; i < unitArray.length(); i++) {
                unitElements.add(new TextView(this));
                unitElements.get(i).setText(unitArray.getString(i));
                if (i < 6) {
                    row1.addView(unitElements.get(i));
                } else {
                    row2.addView(unitElements.get(i));
                }
            }

            TextView spacing = new TextView(this);
            spacing.setText("");
            row3.addView(spacing);

            targetLayout.addView(row1);
            targetLayout.addView(row2);
            targetLayout.addView(row3);
        } catch(JSONException e) {
            System.out.println("Malformed JSON in DisplayData activity");
        }
    }

    public void addWeapon(LinearLayout targetLayout, JSONArray weaponArray) throws JSONException {
        LinearLayout row1 = new LinearLayout(this);
        LinearLayout row2 = new LinearLayout(this);
        LinearLayout row3 = new LinearLayout(this);
        LinearLayout row4 = new LinearLayout(this);

        List<TextView> weaponElements = new ArrayList<TextView>();

        for(int i = 0; i < weaponArray.length(); i++){
            weaponElements.add(new TextView(this));
            weaponElements.get(i).setText(weaponArray.getString(i));
            if(i < 3) {
                row1.addView(weaponElements.get(i));
            } else if (i <= 3 && i < 6){
                row2.addView(weaponElements.get(i));
            } else {
                row3.addView(weaponElements.get(i));
            }
        }

        TextView spacing = new TextView(this);
        spacing.setText("");
        row4.addView(spacing);

        targetLayout.addView(row1);
        targetLayout.addView(row2);
        targetLayout.addView(row3);
        targetLayout.addView(row4);

    }

    /*public void oldAddWeapon(LinearLayout targetLayout, JSONArray weaponArray) throws JSONException {
        LinearLayout.LayoutParams spaceLP = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);

        LinearLayout row1 = new LinearLayout(this);
        LinearLayout row2 = new LinearLayout(this);
        LinearLayout row3 = new LinearLayout(this);

        TextView company = new TextView(this);
        TextView name = new TextView(this);
        TextView range = new TextView(this);
        TextView strength = new TextView(this);
        TextView damage = new TextView(this);
        TextView ammo = new TextView(this);
        TextView traits = new TextView(this);

        Space spaceCompanyName = new Space(this);
        Space spaceNameRange = new Space(this);
        Space spaceStrengthDamage = new Space(this);
        Space spaceDamageAmmo = new Space(this);

        spaceCompanyName.setLayoutParams(spaceLP);
        spaceNameRange.setLayoutParams(spaceLP);
        spaceStrengthDamage.setLayoutParams(spaceLP);
        spaceDamageAmmo.setLayoutParams(spaceLP);

        company.setText(weaponArray.getString(1));
        name.setText(weaponArray.getString(2));
        range.setText(weaponArray.getString(3));
        strength.setText(weaponArray.getString(4));
        damage.setText(weaponArray.getString(5));
        ammo.setText(weaponArray.getString(6));
        traits.setText(weaponArray.getString(7));

        row1.addView(company);
        row1.addView(spaceCompanyName);
        row1.addView(name);
        row1.addView(spaceNameRange);
        row1.addView(range);

        row2.addView(strength);
        row2.addView(spaceStrengthDamage);
        row2.addView(damage);
        row2.addView(spaceDamageAmmo);
        row2.addView(ammo);

        row3.addView(traits);

        LinearLayout row4 = new LinearLayout(this);
        TextView spacing = new TextView(this);
        spacing.setText("");
        row4.addView(spacing);

        targetLayout.addView(row1);
        targetLayout.addView(row2);
        targetLayout.addView(row3);
        targetLayout.addView(row4);
    }*/

}