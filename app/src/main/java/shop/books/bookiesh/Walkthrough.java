package shop.books.bookiesh;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

//this is walkthrough for the app  when user is not login
public class Walkthrough extends AppCompatActivity implements View.OnClickListener {


    private ViewPager viewPager;
    private SliderAdapter sliderAdapter;
    private Button button;
    RelativeLayout relativeLayout;
    AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);

        relativeLayout = (RelativeLayout) findViewById(R.id.idrelativ);
        animationDrawable = (AnimationDrawable)relativeLayout.getBackground();

        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }

        getSupportActionBar().hide();
        viewPager = (ViewPager) findViewById(R.id.myviewpager);
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        button = (Button)findViewById(R.id.walkbutton);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == button){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
