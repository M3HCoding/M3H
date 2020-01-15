package dev.zmq.m3h.AudioPlayer.OfflineAudioPlayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

import dev.zmq.m3h.R;

public class OfflineAudioPlayer extends  ListFragment {
    private static final int UPDATE_FREQUENCY = 500;
    private static final int STEP_VALUE = 4000;
    private final Handler handler = new Handler();
    private View view;
    private TextView selectedFile = null;
    private SeekBar seekBar = null;
    private MediaPlayer player = null;

    private final Runnable updatePositinRunnable = new Runnable() {
        @Override
        public void run() {
            updatePosition();
        }
    };

    private Button prev = null;
    private Button play = null;
    private Button next = null;
    private OfflineAudioAdapter adapter = null;
    private boolean isStarted = true;
    private String currentFile = "";
    private boolean isMovingSeekBar = false;
    private View.OnClickListener OnButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.play: {
                    if (player.isPlaying()) {
                        handler.removeCallbacks( updatePositinRunnable );
                        player.pause();
                        //play.setImageResource( android.R.drawable.ic_media_play );
                        play.setBackgroundResource( R.drawable.ic_play );
                    } else {
                        if (isStarted) {
                            player.start();
                            //play.setImageResource( android.R.drawable.ic_media_pause );
                            play.setBackgroundResource( R.drawable.ic_pause );
                            updatePosition();
                        } else {
                            startPlay( currentFile );
                        }
                    }
                    break;
                }

                case R.id.next: {
                    int seekto = player.getCurrentPosition() + STEP_VALUE;
                    if (seekto > player.getDuration())
                        seekto = player.getDuration();
                    player.pause();
                    player.seekTo( seekto );
                    player.start();
                    break;
                }

                case R.id.previous: {
                    int seekto = player.getCurrentPosition() - STEP_VALUE;
                    if (seekto < 0)
                        seekto = 0;
                    player.pause();
                    player.seekTo( seekto );
                    player.start();
                    break;
                }
            }
        }
    };
    private MediaPlayer.OnCompletionListener onCompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            stopPlay();
        }

        ;
    };
    private MediaPlayer.OnErrorListener onError = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            return false;
        }
    };
    private SeekBar.OnSeekBarChangeListener seekBarChanged =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (isMovingSeekBar) {
                        player.seekTo( progress );
                        Log.i( "OnSeekBarChangeListener", "OnProgressChanged" );
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    isMovingSeekBar = true;
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    isMovingSeekBar = false;
                }
            };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.offline_audioplayer, container, false );
        selectedFile = (TextView) view.findViewById( R.id.selecteditem );
        seekBar = (SeekBar) view.findViewById( R.id.seekBar );
        prev = (Button) view.findViewById( R.id.previous );
        play = (Button) view.findViewById( R.id.play );
        next = (Button) view.findViewById( R.id.next );

        player = new MediaPlayer();
        player.setOnCompletionListener( onCompletion );
        player.setOnErrorListener( onError );
        seekBar.setOnSeekBarChangeListener( seekBarChanged );

//        Cursor cursor = getActivity().getContentResolver().query( MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null );
//
//        if (null != cursor) {
//            cursor.moveToFirst();
//            adapter = new OfflineAudioAdapter( getActivity(), R.layout.offline_audio_item, cursor );
//            setListAdapter( adapter );
//            prev.setOnClickListener( OnButtonClick );
//            play.setOnClickListener( OnButtonClick );
//            next.setOnClickListener( OnButtonClick );
//        }
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick( l, v, position, id );
        currentFile = (String) v.getTag();
        startPlay( currentFile );
    }

    private void startPlay(String file) {
        Log.i( "Selected: ", file );
        selectedFile.setText( file );
        seekBar.setProgress( 0 );
        player.stop();
        player.reset();

        try {
            player.setDataSource( file );
            player.prepare();
            player.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        seekBar.setMax( player.getDuration() );
       // play.setImageResource( android.R.drawable.ic_media_pause );
        play.setBackgroundResource( R.drawable.ic_pause );
        updatePosition();
        isStarted = true;
    }

    private void stopPlay() {
        player.stop();
        player.reset();
        //play.setImageResource( android.R.drawable.ic_media_play );
        play.setBackgroundResource( R.drawable.ic_play );
        handler.removeCallbacks( updatePositinRunnable );
        seekBar.setProgress( 0 );
        isStarted = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks( updatePositinRunnable );
        player.stop();
        player.reset();
        player.release();
        player = null;
    }

    private void updatePosition() {
        handler.removeCallbacks( updatePositinRunnable );
        seekBar.setProgress( player.getCurrentPosition() );
        handler.postDelayed( updatePositinRunnable, UPDATE_FREQUENCY );
    }

}