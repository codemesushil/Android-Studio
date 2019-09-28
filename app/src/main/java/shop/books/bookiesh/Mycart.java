package shop.books.bookiesh;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//this class is used for cart purpose and it will store books added to cart by user........................
public class Mycart extends AppCompatActivity {

    private List<book> lstBook;
    private JsonArrayRequest request ;
    private RecyclerView recyclerView ;
    private RequestQueue requestQueue ;
    book book1 = new book() ;
    Button button ;
    int total=0 ;
    public static int carrytotal=0 ;
    TextView ettotal ;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.cart_row_item);
        setContentView(R.layout.activity_mycart);

        setTitle("CART");

        ettotal = (TextView) findViewById(R.id.tvtotal);
        button = (Button) findViewById(R.id.buttoncont);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateavailable();
                startActivity(new Intent(getApplicationContext(),OrderSelectAddress.class));
            }
        });
        lstBook = new ArrayList<>() ;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_cart_id);
        jsonrequest();
    }


    private void updateavailable()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.UPDATE_AVAILABLE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(BookActivity.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Mycart.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("userid",SharedPrefManager.getInstance(getApplicationContext()).getUserid());


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void jsonrequest() {

        request = new JsonArrayRequest(Constants.JSON_URL, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                JSONObject jsonObject  = null ;
                JSONObject jsonObject2 = null;
                for (int i = 0 ; i < response.length(); i++ )
                {
                    try {
                        jsonObject = response.getJSONObject(i);

                        for(int j = 0 ; j < response.length(); j++)
                            try
                            {

                                jsonObject2 = response.getJSONObject(j);
                                book book = new book();
                                if(/*jsonObject.getString("available").equals(SharedPrefManager.getInstance(getApplicationContext()).getUserid()) &&
                                jsonObject.getString("cart_user").equals(SharedPrefManager.getInstance(getApplicationContext()).getUserid())*/
                                        jsonObject.getString("uid").equals(SharedPrefManager.getInstance(getApplicationContext()).getUserid()))
                                {
                                    if(jsonObject.getString("bid").equals(jsonObject2.getString("bkid")))
                                    {
                                        if(jsonObject.getString("available").equals("1"))
                                        {
                                            book.setavailable("Currently not available !");
                                            book.setbkid(jsonObject2.getString("bkid"));
                                            book.setbkname(jsonObject2.getString("name"));
                                            book.setbkoriginalprice(jsonObject2.getString("originalprice"));
                                            book.setbkauthor(jsonObject2.getString("author"));
                                            book.setbkrent(jsonObject2.getString("rent"));
                                            book.setbkurl(jsonObject2.getString("image"));
                                            book.setdays(jsonObject.getString("days"));
                                            lstBook.add(book);

                                        }
                                        else {
                                            book.setavailable("Available ");
                                            book.setbkid(jsonObject2.getString("bkid"));
                                            book.setbkname(jsonObject2.getString("name"));
                                            book.setbkoriginalprice(jsonObject2.getString("originalprice"));
                                            book.setbkauthor(jsonObject2.getString("author"));
                                            book.setbkrent(jsonObject2.getString("rent"));
                                            book.setbkurl(jsonObject2.getString("image"));
                                            book.setdays(jsonObject.getString("days"));
                                            total = total + Integer.parseInt(jsonObject.getString("total"));
                                            lstBook.add(book);
                                        }
                                    }
                                }
                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
                            }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                carrytotal=total;
                ettotal.setText("₹ "+Integer.toString(total));
                setuprecyclerview(lstBook);
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


    private void setuprecyclerview(List<book> lstBook)
    {

        RecyclerViewAdapter_cart myadapter = new RecyclerViewAdapter_cart(this, lstBook) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }

}
