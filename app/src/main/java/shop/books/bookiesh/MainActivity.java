package shop.books.bookiesh;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

//this is the main activity by default which holds all the fragments(after login).................................................
public class MainActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener,NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private DrawerLayout drawerLayout;
    String name,email;
    TextView headerusername,headeremailid,time;
    ViewGroup container;
    CircleImageView mypropic;
    book book =new book();
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);

        drawerLayout = (DrawerLayout) findViewById(R.id.active_drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        headeremailid=(TextView)findViewById(R.id.heademail);
        headeremailid.setText(SharedPrefManager.getInstance(getApplicationContext()).getUserEmail());
        headerusername=(TextView)findViewById(R.id.headuser);
        headerusername.setText(SharedPrefManager.getInstance(getApplicationContext()).getUserName());

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView1 = (NavigationView) findViewById(R.id.navid);
        navigationView1.setNavigationItemSelectedListener(this);
        mypropic = (CircleImageView)findViewById(R.id.mypropic);
        mypropic.setOnClickListener(this);



        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new explore_fragment());

    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.cart_icon,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
        }

        int id = item.getItemId();
        if(id==R.id.mycartid)
        {
            startActivity(new Intent(this,Mycart.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean loadFragment(Fragment fragment){
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
        return false;
    }

    @SuppressWarnings("Statements with empty bodies")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
       Fragment fragment= null;

        switch (menuItem.getItemId())
        {
            case R.id.navigation_explore:
                fragment=new explore_fragment();
                return loadFragment(fragment);

            case R.id.navigation_fav:
                fragment=new wishlist_fragment();
                return loadFragment(fragment);

            case R.id.navigation_rentout:
                fragment=new rentout_fragment();
                return loadFragment(fragment);

            case R.id.navigation_profile:
                fragment=new profile_fragment();
                return loadFragment(fragment);

            case R.id.Logout:
                Toast.makeText(this,"Logged Out successfully",Toast.LENGTH_SHORT).show();
                SharedPrefManager.getInstance(this).Logout();
                startActivity(new Intent(this,LoginActivity.class));
                finish();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if(v == mypropic)
        {
            Toast.makeText(getApplicationContext(), "profile!", Toast.LENGTH_SHORT).show();
        }
    }
}
