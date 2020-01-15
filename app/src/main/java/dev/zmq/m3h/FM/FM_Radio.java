package dev.zmq.m3h.FM;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import dev.zmq.m3h.R;

public class FM_Radio extends Fragment
{
    private View view;
    private Button btn_Back,btn_Play,btn_Next;
    private ProgressBar pb_FM_Radio;
    private MediaPlayer mediaPlayer;
    private RecyclerView recyclerView;

    public static final String Broadcast_PLAY_NEW_AUDIO = "dev.zmq.m3h.FM.PlayNewAudio";
    // Change to your package name

    //private String stream="https://tunein.com/radio/911-K-LOVE-Radio-WTKL-s22883";
   // private String stream="https://www.radio.net/s/wfmu";
   // private String stream="https://onlineradios.in/#bombay-beats";
    //private String stream="https://onlineradios.in";
    //private String stream="https://onlineradios.in/106.4";
    //private String stream="http://noisefm.ru:8000/live";
    //private String stream="https://onlineradios.in/desi-music/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate( R.layout.fm_radio,container,false );
        btn_Back=(Button)view.findViewById( R.id.btn_fm_radio_back );
        btn_Play=(Button)view.findViewById( R.id.btn_fm_radio_play );
        btn_Next=(Button)view.findViewById( R.id.btn_fm_radio_next );
        pb_FM_Radio=(ProgressBar)view.findViewById( R.id.progressbar_fm_radio );
        pb_FM_Radio.setVisibility( View.VISIBLE );
        recyclerView=(RecyclerView)view.findViewById( R.id.rv_fm_radio );

        btn_Play.setEnabled( false );
        btn_Play.setBackgroundResource( R.drawable.ic_play );

        btn_Play.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );

        FMListData[] myListData = new FMListData[]{
                new FMListData( "Email", "Email", android.R.drawable.ic_menu_agenda ),
                new FMListData( "Info", "Email", android.R.drawable.ic_dialog_info ),
                new FMListData( "Delete", "Email", android.R.drawable.ic_delete ),
                new FMListData( "Dialer", "Email", android.R.drawable.ic_dialog_dialer ),
                new FMListData( "Alert", "Email", android.R.drawable.ic_dialog_alert ),
                new FMListData( "Map", "Email", android.R.drawable.ic_dialog_map ),
                new FMListData( "Email",  "Email", android.R.drawable.ic_dialog_email ),
                new FMListData( "Info",  "Email", android.R.drawable.ic_dialog_info ),
                new FMListData( "Delete",  "Email", android.R.drawable.ic_delete ),
                new FMListData( "Dialer", "Email", android.R.drawable.ic_dialog_dialer ),
                new FMListData( "Alert",  "Email", android.R.drawable.ic_dialog_alert ),
                new FMListData( "Map", "Email", android.R.drawable.ic_dialog_map ),
        };

        FmRadioAdapter adapter = new FmRadioAdapter( myListData );
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        recyclerView.setAdapter( adapter );

    }


    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle( "FM Radio" );
    }
}
