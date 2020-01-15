package dev.zmq.m3h.Splash;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import dev.zmq.m3h.R;
import dev.zmq.m3h.Sign_In.SignIn;

public class Splash extends AppCompatActivity
{
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.splash );
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        loadFragment( new SignIn() );
    }
    public void loadFragment(Fragment fragment)
    {
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add( R.id.fl_splash,fragment,"fragment");
        fragmentTransaction.commit();
    }

}
