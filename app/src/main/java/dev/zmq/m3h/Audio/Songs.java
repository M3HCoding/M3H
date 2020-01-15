package dev.zmq.m3h.Audio;

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
import android.widget.TextView;


import dev.zmq.m3h.R;

public class Songs extends Fragment implements View.OnClickListener {
    private View view;
    private TextView txt_Song_Title;
    private ProgressBar progressBar;
    private Button btn_Back, btn_Play, btn_Next;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.songs, container, false );
        txt_Song_Title = (TextView) view.findViewById( R.id.txt_songs_title );
        progressBar = (ProgressBar) view.findViewById( R.id.progress_circular_songs );
        btn_Back = (Button) view.findViewById( R.id.btn_songs_back );
        btn_Play = (Button) view.findViewById( R.id.btn_songs_play );
        btn_Next = (Button) view.findViewById( R.id.btn_songs_next );
        recyclerView = (RecyclerView) view.findViewById( R.id.rv_songs );

        btn_Back.setOnClickListener( this );
        btn_Play.setOnClickListener( this );
        btn_Next.setOnClickListener( this );

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        SongListData[] myListData = new SongListData[]{
                new SongListData( "Email", "Email", "Email", android.R.drawable.ic_menu_agenda ),
                new SongListData( "Info", "Email", "Email", android.R.drawable.ic_dialog_info ),
                new SongListData( "Delete", "Email", "Email", android.R.drawable.ic_delete ),
                new SongListData( "Dialer", "Email", "Email", android.R.drawable.ic_dialog_dialer ),
                new SongListData( "Alert", "Email", "Email", android.R.drawable.ic_dialog_alert ),
                new SongListData( "Map", "Email", "Email", android.R.drawable.ic_dialog_map ),
                new SongListData( "Email", "Email", "Email", android.R.drawable.ic_dialog_email ),
                new SongListData( "Info", "Email", "Email", android.R.drawable.ic_dialog_info ),
                new SongListData( "Delete", "Email", "Email", android.R.drawable.ic_delete ),
                new SongListData( "Dialer", "Email", "Email", android.R.drawable.ic_dialog_dialer ),
                new SongListData( "Alert", "Email", "Email", android.R.drawable.ic_dialog_alert ),
                new SongListData( "Map", "Email", "Email", android.R.drawable.ic_dialog_map ),
        };

        SongsAdapter adapter = new SongsAdapter( myListData );
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        recyclerView.setAdapter( adapter );
    }

    @Override
    public void onClick(View v) {
        if (v == btn_Back) {

        } else if (v == btn_Play) {

        } else if (v == btn_Next) {

        }

    }
    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle( "Music" );
    }
}