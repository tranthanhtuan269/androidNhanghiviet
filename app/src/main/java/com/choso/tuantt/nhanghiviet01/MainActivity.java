package com.choso.tuantt.nhanghiviet01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.victor.loading.rotate.RotateLoading;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String url ="https://api.androidhive.info/json/movies.json";
    private List<Item> hotelList = new ArrayList<Item>();
    private ListView listView;
    private ItemAdapter adapter;

    public TextView textView;
    public ImageView imageView;

    public JSONArray data;

    private RotateLoading rotateLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.list_item);
        rotateLoading = (RotateLoading) findViewById(R.id.rotateloading);
        adapter = new ItemAdapter(this, hotelList);
        listView.setAdapter(adapter);

        getDataRequest();






    }

    private void getDataRequest(){
        if (!rotateLoading.isStart()) {
            rotateLoading.start();
        }

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (rotateLoading.isStart()) {
                    rotateLoading.stop();
                }
                for(int i=0; i<response.length();i++){
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Item item = new Item();
                        item.setTitle(obj.getString("title"));
                        item.setImage(obj.getString("image"));
                        item.setRate(obj.getString("rating"));
                        hotelList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getmInstance().addToRequestQueue(jsonArrayRequest);
    }
}
