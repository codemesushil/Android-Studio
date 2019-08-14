package shop.books.bookiesh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderSelectAddress extends AppCompatActivity {

    TextView textView;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<Delivery> address1;
    private RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oreder_select_address);

        textView = (TextView)findViewById(R.id.add_adres);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Address.class));
            }
        });
        address1 = new ArrayList<>() ;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_address_id);
        jsonrequest();

    }

    public void jsonrequest() {

        request = new JsonArrayRequest(Constants.ADDRESS_JSON, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                JSONObject jsonObject  = null ;
                for (int i = 0 ; i < response.length(); i++ )
                {

                    try
                    {
                        jsonObject = response.getJSONObject(i);
                        Delivery delivery = new Delivery();
                        if(jsonObject.getString("userid").equals(SharedPrefManager.getInstance(getApplicationContext()).getUserid()))
                        {
                            delivery.setaddr_id(jsonObject.getString("addr_id"));
                            delivery.setpin(jsonObject.getString("pin"));
                            delivery.sethouse(jsonObject.getString("house"));
                            delivery.setarea(jsonObject.getString("area"));
                            delivery.setcity(jsonObject.getString("city"));
                            delivery.setstate(jsonObject.getString("state"));
                            delivery.setname(jsonObject.getString("name"));
                            delivery.settotalbill(jsonObject.getString("totalbill"));
                            address1.add(delivery);
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
                setuprecyclerview(address1);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request) ;
    }
    private void setuprecyclerview(List<Delivery> address1)
    {
        RecyclerView_Order myadapter = new RecyclerView_Order(this, address1) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);
    }
}
