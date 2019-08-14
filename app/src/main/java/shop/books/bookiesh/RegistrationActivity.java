package shop.books.bookiesh;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

//this class is used for registration of new user ........................../////////////////////////
public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUsername, etEmail, etPassword, etphone;
    private Button btnRegister;
    private ProgressDialog progressDialog;
    private TextView tvLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,MainActivity.class));
            return;

        }

        etUsername = (EditText)findViewById(R.id.etUsername);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);
        tvLogin = (TextView)findViewById(R.id.tvLogin);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        etphone = (EditText)findViewById(R.id.etphone);

        progressDialog = new ProgressDialog(this);

        btnRegister.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    private void RegisterUser()
    {
        final String email=etEmail.getText().toString().trim();
        final String username=etUsername.getText().toString().trim();
        final String password=etPassword.getText().toString().trim();
        final String phone=etphone.getText().toString().trim();


        if(email.matches("") || username.matches("") || password.matches("") || phone.matches(""))
        {
            Toast.makeText(this, "please fill all the details", Toast.LENGTH_SHORT).show();
        }
       else {
            progressDialog.setMessage("Registering User...");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        if((jsonObject.getString("message")).equals("Exist"))
                        {
                            Toast.makeText(getApplicationContext(), "It seems you are already registered,please choose a different email and username", Toast.LENGTH_LONG).show();
                        }
                        else{
                          Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                          startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                          finish();
                            }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.hide();
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("email", email);
                    params.put("password", password);
                    params.put("phone", phone);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }
    }
    @Override
    public void onClick(View v) {
        if (v==btnRegister) {
            RegisterUser();
        }
        if(v== tvLogin)
            startActivity(new Intent(this,LoginActivity.class));
    }
}
