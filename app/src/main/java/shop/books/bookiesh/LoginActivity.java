package shop.books.bookiesh;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


//this class initiates login settion for user................................................
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private ProgressDialog progressDialog;
    private TextView tvregister,tvforgot;
    Dialog myDialog;
    ImageView closethis;
    Button btnFollow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDialog = new Dialog(this);


        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);
        tvregister = (TextView)findViewById(R.id.tvregister);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        tvforgot = (TextView)findViewById(R.id.tvforgot);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");

        btnLogin.setOnClickListener(this);
        tvregister.setOnClickListener(this);
        tvforgot.setOnClickListener(this);
    }

    public void userLogin()
    {
        final String password = etPassword.getText().toString().trim();
        final String email = etEmail.getText().toString().trim();

        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if(!obj.getBoolean("error")){
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(obj.getString("id"),obj.getString("username"),obj.getString("email"),obj.getInt("phone"),obj.getInt("wallet"));
                        Toast.makeText(getApplicationContext(),"User Login Successful",Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));

                    }else
                    {
                        Toast.makeText(getApplicationContext(),obj.getString("message"),Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }


    public void ShowPopup(View v) {
        myDialog.setContentView(R.layout.custompopup);
        closethis = myDialog.findViewById(R.id.closeimg);
        closethis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }




    @Override
    public void onClick(View v) {
        if(v == btnLogin){
            userLogin();
        }
        if(v == tvregister){
            finish();
            startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
        }
        if(v == tvforgot){
            //startActivity(new Intent(getApplicationContext(),Resetpassword.class));
           ShowPopup(v);
        }
    }
}
