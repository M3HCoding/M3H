package dev.zmq.m3h.MainActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import dev.zmq.m3h.R;
import dev.zmq.m3h.Splash.Splash;

public class MainActivity extends AppCompatActivity
{
    private ImageButton Splash_Screen;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Splash_Screen=(ImageButton)findViewById( R.id.imgBtn_splash );
        Splash_Screen.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MainActivity.this, Splash.class );
                startActivity( intent );
            }
        } );
    }
}
