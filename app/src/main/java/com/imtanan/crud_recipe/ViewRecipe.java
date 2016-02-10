package com.imtanan.crud_recipe;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ViewRecipe extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextId;
    private EditText editTextName;
    private EditText editTextOrigin;
    private EditText editTextIngre;
    private EditText editTextCM;

    private Button buttonUpdate;
   // private Button buttonDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        Intent intent = getIntent();

        id = intent.getStringExtra(Config.REC_ID);

        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextOrigin = (EditText) findViewById(R.id.editTextOrigin);
        editTextIngre = (EditText) findViewById(R.id.editTextIngre);
        editTextCM = (EditText) findViewById(R.id.editTextCM);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
       // buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        //buttonDelete.setOnClickListener(this);

        editTextId.setText(id);

        getEmployee();
    }

    private void getEmployee() {
        class GetEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewRecipe.this, "Fetching...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_REC, id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(Config.TAG_NAME);
            String origin = c.getString(Config.TAG_ORIGIN);
            String ingre = c.getString(Config.TAG_INGRE);
            String cm = c.getString(Config.TAG_CM);

            editTextName.setText(name);
            editTextOrigin.setText(origin);
            editTextIngre.setText(ingre);
            editTextCM.setText(cm);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee() {
        final String name = editTextName.getText().toString().toLowerCase().trim();
        final String origin = editTextOrigin.getText().toString().toLowerCase().trim();
        final String ingre = editTextIngre.getText().toString().toLowerCase().trim();
        final String cm = editTextCM.getText().toString().toLowerCase().trim();

        class UpdateEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewRecipe.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewRecipe.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_REC_ID, id);
                hashMap.put(Config.KEY_REC_NAME, name);
                hashMap.put(Config.KEY_REC_ORIGIN ,origin);
                hashMap.put(Config.KEY_REC_INGRE, ingre);
                hashMap.put(Config.KEY_REC_CM, cm);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_REC, hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }
/*
    private void deleteEmployee() {
        class DeleteEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ViewRecipe.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ViewRecipe.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_DELETE_REC, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to delete this recipe?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(ViewRecipe.this, ViewAllRecipe.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
*/
    @Override
    public void onClick(View v) {
        if (v == buttonUpdate) {
            updateEmployee();
        }

       /* if (v == buttonDelete) {
            confirmDeleteEmployee();
        }*/
    }
}