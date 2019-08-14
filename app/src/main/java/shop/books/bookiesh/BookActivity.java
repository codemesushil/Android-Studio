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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.HashMap;
import java.util.Map;

//this class is for description of single book(add to cart and buy now buttons).....................................................
public class BookActivity extends AppCompatActivity implements View.OnClickListener {

    Button addtocart,add_days,buynow;
    String bkid;
    Dialog myDialog;
    ImageView closethis,imgfav;
    public int FLAG=0;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        // hide the default actionbar
        getSupportActionBar().hide();

        myDialog = new Dialog(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading cart...");

        add_days = (Button)findViewById(R.id.btnmycart);
        if(getIntent().getExtras().getString("available").equals(SharedPrefManager.getInstance(getApplicationContext()).getUserid()))
        {
            add_days.setText("Go to cart");
        }
        buynow = (Button)findViewById(R.id.btnbuynow);

        imgfav = (ImageView)findViewById(R.id.fav);

        buynow.setOnClickListener(this);
        add_days.setOnClickListener(this);
        imgfav.setOnClickListener(this);

        // Recieve data
        bkid = getIntent().getExtras().getString("bkid");
        String bkname  = getIntent().getExtras().getString("name");
        String bkoriginalprice = getIntent().getExtras().getString("originalprice");
        String bkauthor = getIntent().getExtras().getString("author") ;
        String bkrent = getIntent().getExtras().getString("rent");
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

    private void addtomycart()
    {
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
                params.put("email",SharedPrefManager.getInstance(getApplicationContext()).getUserEmail());
                params.put("bkid",bkid);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void add_days(String days)
    {
        final String days1=days;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.ADD_DAYS,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
//                        Toast.makeText(mContext,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams()
            {
                Map<String,String> params = new HashMap<String, String>();
                params.put("days",days1);
                params.put("bkid",bkid);
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


    @Override
    public void onClick(View v) {
        if(v == add_days)
        {
            if(getIntent().getExtras().getString("available").equals(SharedPrefManager.getInstance(getApplicationContext()).getUserid()) || FLAG==1)
            {
                startActivity(new Intent(getApplicationContext(),Mycart.class));
            }else
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
        FLAG=1;
        myDialog.setContentView(R.layout.customcartpopup_add);
        closethis = myDialog.findViewById(R.id.closeimg1);
        addtocart = myDialog.findViewById(R.id.btnadd);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                EditText etdays;
                etdays = (EditText)myDialog.findViewById(R.id.etdayadd);

                if(Integer.parseInt(etdays.getText().toString().trim())==0)
                {
                    Toast.makeText(getApplicationContext(),"days cannot be zero",Toast.LENGTH_SHORT).show();
                }
                else {
                    add_days(etdays.getText().toString().trim());
                    progressDialog.setMessage("Adding...");
                    addtomycart();
                    progressDialog.show();
                    new CountDownTimer(2000, 1000) {
                        public void onFinish() {
                            // When timer is finished
                            // Execute your code here
                            progressDialog.dismiss();
                            add_days.setText("Go to Cart");
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
                }
                else {
                    add_days(etdays.getText().toString().trim());
                    addtomycart();
                    progressDialog.show();
                    new CountDownTimer(2000, 1000) {
                        public void onFinish() {
                            // When timer is finished
                            // Execute your code here
                            progressDialog.dismiss();
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
