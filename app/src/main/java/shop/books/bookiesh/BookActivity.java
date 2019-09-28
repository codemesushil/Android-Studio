package shop.books.bookiesh;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//this class is for description of single book(add to cart and buy now buttons).....................................................
public class BookActivity extends AppCompatActivity implements View.OnClickListener {

    Button addtocart,add_tocartdays,buynow;
    String bkid;
    Dialog myDialog;
    ImageView closethis,imgfav;
    private ProgressDialog progressDialog;
    String bkrent;
    private JsonArrayRequest request1 ;
    private List<book> Bookcheck;
    private RequestQueue requestQueue ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        // hide the default actionbar
        getSupportActionBar().hide();

        myDialog = new Dialog(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading cart...");
        cart_added_check();
        add_tocartdays = (Button)findViewById(R.id.btnmycart);
        buynow = (Button)findViewById(R.id.btnbuynow);

        imgfav = (ImageView)findViewById(R.id.fav);

        buynow.setOnClickListener(this);
        add_tocartdays.setOnClickListener(this);
        imgfav.setOnClickListener(this);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////






////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Recieve data
        bkid = getIntent().getExtras().getString("bkid");
        String bkname  = getIntent().getExtras().getString("name");
        String bkoriginalprice = getIntent().getExtras().getString("originalprice");
        String bkauthor = getIntent().getExtras().getString("author") ;
        bkrent = getIntent().getExtras().getString("rent");
        String image_url = getIntent().getExtras().getString("image") ;

        // ini views

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_bkname = findViewById(R.id.bkname);
        TextView tv_bkoriginalprice = findViewById(R.id.bkoriginalprice);
        TextView tv_bkauthor = findViewById(R.id.bkauthor) ;
        TextView tv_bkrent = findViewById(R.id.bkrent);
        ImageView img = findViewById(R.id.aa_thumbnail);

        // setting values to each view
        tv_bkname.setText(bkname);
        tv_bkoriginalprice.setText(bkoriginalprice);
        tv_bkauthor.setText(bkauthor);
        tv_bkrent.setText(bkrent);

        collapsingToolbarLayout.setTitle(bkname);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        // set image using Glide
        Glide.with(this).load(image_url).apply(requestOptions).into(img);

    }

    private void cart_added_check()
    {
        request1 = new JsonArrayRequest(Constants.CART_JSON, new Response.Listener<JSONArray>()
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
                        if(SharedPrefManager.getInstance(getApplicationContext()).getUserid().equals(jsonObject.getString("uid")) && bkid.equals(jsonObject.getString("bid")))
                        {
                            add_tocartdays.setText("Go to cart");
                            break;
                        }
                            add_tocartdays.setText("Add to cart");
//                            book.setbid(jsonObject.getString("bid"));
//                            book.setuid(jsonObject.getString("uid"));
//                        Bookcheck.add(book);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
                //setuprecyclerview(lstBook);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request1) ;
    }



    private void addtomycart(String days1)
    {
        final String days11 =days1;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.ADD_TO_CART,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(BookActivity.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("userid",SharedPrefManager.getInstance(getApplicationContext()).getUserid());
                params.put("bkid",bkid);
                params.put("bkrent",bkrent);
                params.put("days",days11);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }





    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }


    public void addtowishlist(final String bkid)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.ADD_TO_WISHLIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(BookActivity.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("userid",SharedPrefManager.getInstance(getApplicationContext()).getUserid());
                params.put("bookid",bkid);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void updatecart()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.UPDATE_CART,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(BookActivity.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

public int Flag=0;
    @Override
    public void onClick(View v) {
        if(v == add_tocartdays)
        {
            if(add_tocartdays.getText().equals("Go to cart") || Flag==1) {

                startActivity(new Intent(getApplicationContext(), Mycart.class));
            }
            else
                ShowPopup1(v);
        }
        if(v == buynow)
        {
            ShowPopup2(v);
        }

        if(v == imgfav)
        {
            imgfav.setBackgroundResource(R.drawable.wish1);
            addtowishlist(bkid);
            Toast.makeText(getApplicationContext(),"Added to Wishlist !",Toast.LENGTH_SHORT).show();
        }
    }
//FOR ADD TO CART BUTTON-------------------------------------------------------------------
    public void ShowPopup1(View v) {
        myDialog.setContentView(R.layout.customcartpopup_add);
        closethis = myDialog.findViewById(R.id.closeimg1);
        addtocart = myDialog.findViewById(R.id.btnadd);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                EditText etdays;
                etdays = (EditText)myDialog.findViewById(R.id.etdayadd);

                if(etdays.getText().toString().trim().length()==0)
                {
                    Toast.makeText(getApplicationContext(),"days cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else
                    if(Integer.parseInt(etdays.getText().toString().trim())==0)
                    {
                        Toast.makeText(getApplicationContext(),"days cannot be zero",Toast.LENGTH_SHORT).show();
                    }
                else {
                    Flag=1;
                    progressDialog.setMessage("Adding...");
                    addtomycart(etdays.getText().toString().trim());
                    progressDialog.show();
                    new CountDownTimer(2000, 1000) {
                        public void onFinish() {
                            // When timer is finished
                            // Execute your code here
                            progressDialog.dismiss();
                            add_tocartdays.setText("Go to Cart");
                            updatecart();
                            Toast.makeText(getApplicationContext(), "added to cart!", Toast.LENGTH_SHORT).show();
                        }

                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                        }
                    }.start();
                }
                myDialog.dismiss();
            }
        });
        closethis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

//FOR BUY NOW BUTTON----------------------------------------------------
    public void ShowPopup2(View v) {
        myDialog.setContentView(R.layout.customcartpopup_buy);
        closethis = myDialog.findViewById(R.id.closeimg2);
        addtocart = myDialog.findViewById(R.id.btncont);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                EditText etdays;
                etdays = (EditText)myDialog.findViewById(R.id.etdaybuy);
                if(Integer.parseInt(etdays.getText().toString().trim())==0)
                {
                    Toast.makeText(getApplicationContext(),"days cannot be zero",Toast.LENGTH_SHORT).show();
                }else
                if(Integer.parseInt(etdays.getText().toString().trim())==0)
                {
                    Toast.makeText(getApplicationContext(),"days cannot be zero",Toast.LENGTH_SHORT).show();
                }
                else {
                    addtomycart(etdays.getText().toString().trim());
                    progressDialog.show();
                    new CountDownTimer(2000, 1000) {
                        public void onFinish() {
                            // When timer is finished
                            // Execute your code here
                            progressDialog.dismiss();
                            updatecart();
                            startActivity(new Intent(getApplicationContext(), Mycart.class));
                        }

                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                        }
                    }.start();
                }
                myDialog.dismiss();
            }
        });
        closethis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


}
