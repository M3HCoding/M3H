package dev.zmq.m3h.Navigation_Menu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import dev.zmq.m3h.AudioPlayer.AudioPlayerMainActivity;
import dev.zmq.m3h.Calling.Calling;
import dev.zmq.m3h.Chat.Chat;
import dev.zmq.m3h.FM.FM_Radio;
import dev.zmq.m3h.Home.Home;
import dev.zmq.m3h.Live.Live_TV;
import dev.zmq.m3h.My_Profile.MyProfile;
import dev.zmq.m3h.News.News;
import dev.zmq.m3h.R;
import dev.zmq.m3h.Sign_In.SignIn;
import dev.zmq.m3h.URL.URL_Constant;
import dev.zmq.m3h.Upload.Upload;
import dev.zmq.m3h.Video.Videos;
import dev.zmq.m3h.Wallpaper.Gallery;

public class Navigation_Menu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.navigation__menu );
        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        NavigationView navigationView = findViewById( R.id.nav_view );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener( this );

        pref=getSharedPreferences( "M3H",MODE_PRIVATE );

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        this.getSupportActionBar().setTitle( "Home" );
        defaultFragment();
    }

    private void defaultFragment()
    {
        Home home=new Home();
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add( R.id.frameLayout, home).commit();
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home)
        {
            getSupportFragmentManager().popBackStack();
          loadFragment( new Home() );
        } else if (id == R.id.nav_myProfile)
        {
            getSupportFragmentManager().popBackStack();
            loadFragment( new MyProfile() );
        }
        else if (id == R.id.nav_gallery)
        {
            getSupportFragmentManager().popBackStack();
            loadFragment( new Gallery() );
        } else if (id == R.id.nav_songs)
        {
            getSupportFragmentManager().popBackStack();
            //loadFragment( new Songs() );
            loadFragment( new AudioPlayerMainActivity() );
        } else if (id == R.id.nav_video)
        {
            getSupportFragmentManager().popBackStack();
            loadFragment( new Videos() );
        } else if (id == R.id.nav_fm)
        {
            getSupportFragmentManager().popBackStack();
            loadFragment( new FM_Radio() );
        } else if (id == R.id.nav_live_tv)
        {
            getSupportFragmentManager().popBackStack();
            loadFragment( new Live_TV() );
        }
        else if (id == R.id.nav_upload)
        {
            getSupportFragmentManager().popBackStack();
            loadFragment( new Upload() );
        }
        else if (id == R.id.nav_chat)
        {
            getSupportFragmentManager().popBackStack();
            loadFragment( new Chat() );
        }
        else if (id == R.id.nav_calling)
        {
            getSupportFragmentManager().popBackStack();
            loadFragment( new Calling() );
        }
        else if (id == R.id.nav_news)
        {
            getSupportFragmentManager().popBackStack();
            loadFragment( new News() );
        }
        else if (id == R.id.nav_logout)
        {
            logout();
        }

        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }

    private void logout()
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Navigation_Menu.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.drawable.logo);
        builder.setMessage("Do you want to logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putBoolean( URL_Constant.IS_LOGGED_IN, false );
                        editor.putString( URL_Constant.LOGIN_Id, "" );
                        editor.putString( URL_Constant.FULL_NAME, "" );
                        editor.putString( URL_Constant.DATE_OF_BIRTH, "" );
                        editor.putString( URL_Constant.GENDER, "" );
                        editor.putString( URL_Constant.MOBILE_NO, "" );
                        editor.putString(URL_Constant.PASSWORD,"");
                        editor.apply();
                        goToLogin();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void goToLogin()
    {
        Intent intent=new Intent(Navigation_Menu.this, SignIn.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
        finishAffinity();
    }

    public void loadFragment(Fragment fragment)
    {
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace( R.id.frameLayout,fragment,"fragment");
        fragmentTransaction.addToBackStack( "fragment" ).commit();
    }
}
