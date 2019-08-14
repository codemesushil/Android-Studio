package shop.books.bookiesh;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Address extends AppCompatActivity {

    RadioButton radioButton,radioButton2;
    EditText etpin,ethouse,etarea,etcity,etstate,etname,etmobile,etalternate;
    Button save;
    String str11;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        radioButton = (RadioButton)findViewById(R.id.homeaddress);
        radioButton2 =(RadioButton)findViewById(R.id.workaddress);

        progressDialog = new ProgressDialog(this);

        etpin = (EditText)findViewById(R.id.pincode);
        ethouse = (EditText)findViewById(R.id.Houseno);
        etarea = (EditText)findViewById(R.id.area);
        etcity = (EditText)findViewById(R.id.city);
        etstate = (EditText)findViewById(R.id.State);
        etname = (EditText)findViewById(R.id.name);
        etmobile = (EditText)findViewById(R.id.mobile);
        etalternate = (EditText)findViewById(R.id.alternate);
        save = (Button)findViewById(R.id.save) ;

        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str11="home";
                radioButton2.setChecked(false);
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str11="work";
                radioButton.setChecked(false);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1=etpin.getText().toString().trim();
                String str2=etarea.getText().toString().trim();
                String str3=etcity.getText().toString().trim();
                String str4=etstate.getText().toString().trim();
                String str5=etname.getText().toString().trim();
                String str6=etmobile.getText().toString().trim();
                String str7=etalternate.getText().toString().trim();

                String str8=ethouse.getText().toString().trim();
                addtomycart(str1,str2,str3,str4,str5,str6,str7,str8,str11);

                progressDialog.show();
                new CountDownTimer(2000, 1000) {
                    public void onFinish() {
                        // When timer is finished
                        // Execute your code here
                        progressDialog.dismiss();
                        startActivity(new Intent(getApplicationContext(), OrderSelectAddress.class));
                    }

                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();

            }
        });
    }

    private void addtomycart(final String str1, final String str2, final String str3, final String str4, final String str5, final String str6, final String str7,final String str8,final String str11)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.ADD_ADDRESS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(BookActivity.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("pin",str1);
                params.put("house",str8);
                params.put("area",str2);
                params.put("city",str3);
                params.put("state",str4);
                params.put("name",str5);
                params.put("alternate",str6);
                params.put("mobile",str7);
                params.put("userid",SharedPrefManager.getInstance(getApplicationContext()).getUserid());
                params.put("type",str11);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
