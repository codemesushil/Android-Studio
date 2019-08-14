package shop.books.bookiesh;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


//to reset the user login password this class is used.......................................................
public class Resetpassword extends AppCompatActivity {

    Dialog myDialog;
    ImageView closethis;
    Button btnFollow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycart);

        myDialog = new Dialog(this);
    }

    public void ShowPopup(View v) {
        myDialog.setContentView(R.layout.custompopup);
        closethis = myDialog.findViewById(R.id.closeimg);
//        btnFollow = myDialog.findViewById(R.id.btnreset);
        closethis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}
