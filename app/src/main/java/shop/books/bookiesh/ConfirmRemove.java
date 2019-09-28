package shop.books.bookiesh;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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


//this class is for confirmation for removing book......................................................
public class ConfirmRemove extends AppCompatActivity {

    Button button,button1;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_remove);

        ImageView img = findViewById(R.id.myimage1);


        String image_url = getIntent().getExtras().getString("image") ;
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        Glide.with(this).load(image_url).apply(requestOptions).into(img);



        final BookActivity b =new BookActivity();
        progressDialog = new ProgressDialog(this);
        button1 = (Button) findViewById(R.id.idcancel);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Mycart.class));
            }
        });
        button = (Button)findViewById(R.id.btnconfrm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Removing...");
                b.Flag=0;

                removefromcart(getIntent().getExtras().getString("bkid"),SharedPrefManager.getInstance(getApplicationContext()).getUserid());
                progressDialog.show();
                new CountDownTimer(2000, 1000) {
                    public void onFinish() {
                        // When timer is finished
                        // Execute your code here
                        updatecart();
                        progressDialog.dismiss();
                        startActivity(new Intent(getApplicationContext(),Mycart.class));
                        Toast.makeText(getApplicationContext(), "removed from cart cart!", Toast.LENGTH_SHORT).show();
                    }
                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();
            }
        });
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
                        Toast.makeText(ConfirmRemove.this,error.toString(),Toast.LENGTH_LONG).show();
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


    public void removefromcart(String bkid,String userid)
    {
        final String bkid1=bkid;
        final String userid1=userid;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.REMOVE_FROM_CART,
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
                params.put("bkid",bkid1);
                params.put("userid",userid1);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
