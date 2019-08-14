package shop.books.bookiesh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

//this class is used for cart purpose and it will store books added to cart by user........................
public class Mycart extends AppCompatActivity {

    private List<book> lstBook;
    private JsonArrayRequest request ;
    private RecyclerView recyclerView ;
    private RequestQueue requestQueue ;
    Button button;
    public static int total=0;
    TextView ettotal;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.cart_row_item);
        setContentView(R.layout.activity_mycart);

        ettotal = (TextView) findViewById(R.id.tvtotal);
        button = (Button) findViewById(R.id.buttoncont);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),OrderSelectAddress.class));
            }
        });
        lstBook = new ArrayList<>() ;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_cart_id);
        jsonrequest();
    }


    public void jsonrequest() {

        request = new JsonArrayRequest(Constants.JSON_URL, new Response.Listener<JSONArray>()
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
                        book book = new book();
                        if(jsonObject.getString("available").equals(SharedPrefManager.getInstance(getApplicationContext()).getUserid()) &&
                                jsonObject.getString("cart_user").equals(SharedPrefManager.getInstance(getApplicationContext()).getUserid()) )
                        {   book.setbkid(jsonObject.getString("bkid"));
                            book.setbkname(jsonObject.getString("name"));
                            book.setbkoriginalprice(jsonObject.getString("originalprice"));
                            book.setbkauthor(jsonObject.getString("author"));
                            book.setbkrent(jsonObject.getString("rent"));
                            book.setbkurl(jsonObject.getString("image"));
                            book.setdays(jsonObject.getString("days"));
                            total = total+ Integer.parseInt(jsonObject.getString("total"));
                            lstBook.add(book);
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
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
