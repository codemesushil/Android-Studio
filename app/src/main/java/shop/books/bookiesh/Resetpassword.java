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
        setContentView(R.layout.custompopup);

        myDialog = new Dialog(this);
    }


}
