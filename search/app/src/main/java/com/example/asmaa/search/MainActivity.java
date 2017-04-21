package com.example.asmaa.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String urlAddress="http://192.168.0.100/project/search.php";
    SearchView sv;
    ListView lv;
    ArrayList<String> names=new ArrayList<>();
    ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv= (ListView) findViewById(R.id.lv);
        sv= (SearchView) findViewById(R.id.sv);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                parse(query);

                return false;

            }


            @Override
            public boolean onQueryTextChange(String query) {
                parse(query);
                names.clear();
                //lv.clearFocus();
                //lv.setAdapter(null);

                return false;
            }





        });




    }


    public void parse( final String query){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlAddress, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray JsonArray = new JSONArray(response);

                        final JSONObject jsonObect = JsonArray.getJSONObject(0);
                        final String nam = jsonObect.getString("name");
                        names.add(nam);
                        Log.w("TEST", nam);


                        //do something


                    final ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, R.layout.listview, names);
                    lv.setAdapter(adapter);
                    //adapter.getFilter().filter(query);


                }catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> Prams = new HashMap<String, String>();
                Prams.put("Query", query);


                return Prams;

            }


        };

        singletone.getIstance(getApplicationContext()).addtorequest(stringRequest);



    }
}
