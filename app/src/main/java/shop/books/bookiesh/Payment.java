package shop.books.bookiesh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Payment extends AppCompatActivity {
    Mycart m1 = new Mycart();
    TextView tvtotal,tvprice;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        tvtotal = (TextView)findViewById(R.id.total);
        tvtotal.setText("₹"+SharedPrefManager.getInstance(getApplicationContext()).getUserWallet());

        tvprice = (TextView)findViewById(R.id.price);
        tvprice.setText(" ₹ "+ m1.total);

        button = (Button)findViewById(R.id.buttonpay);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),OrderConfirm.class));
            }
        });
    }
}
