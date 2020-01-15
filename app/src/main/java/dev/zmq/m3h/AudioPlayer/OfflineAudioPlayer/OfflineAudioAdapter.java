package dev.zmq.m3h.AudioPlayer.OfflineAudioPlayer;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.BigDecimal;

import dev.zmq.m3h.R;

public class OfflineAudioAdapter extends SimpleCursorAdapter {
    public OfflineAudioAdapter(Context context, int layout, Cursor c) {
        super( context, layout, c, new String[]{MediaStore.MediaColumns.DISPLAY_NAME,
                        MediaStore.MediaColumns.TITLE, MediaStore.Audio.AudioColumns.DURATION},
                new int[]{R.id.txt_offline_audioItem_displayName
                        , R.id.txt_offline_audioItem_title
                        , R.id.txt_offline_audioItem_duration} );
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title = (TextView) view.findViewById( R.id.txt_offline_audioItem_title );
        TextView name = (TextView) view.findViewById( R.id.txt_offline_audioItem_displayName );
        TextView duration = (TextView) view.findViewById( R.id.txt_offline_audioItem_duration );

        name.setText( cursor.getString( cursor.getColumnIndex(
                MediaStore.MediaColumns.DISPLAY_NAME ) ) );
        title.setText( cursor.getString( cursor.getColumnIndex(
                MediaStore.MediaColumns.TITLE ) ) );
        long durationInMS = Long.parseLong( cursor.getString(
                cursor.getColumnIndex( MediaStore.Audio.AudioColumns.DURATION ) ) );

        double durationInMin = ((double) durationInMS / 1000.0) / 60.0;
        durationInMin = new BigDecimal( Double.toString( durationInMin ) ).
                setScale( 2, BigDecimal.ROUND_UP ).doubleValue();
        duration.setText( "" + durationInMin );
        view.setTag( cursor.getString( cursor.getColumnIndex( MediaStore.MediaColumns.DATA ) ) );
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from( context );
        View v = inflater.inflate( R.layout.offline_audio_item, parent, false );
        bindView( v, context, cursor );
        return v;
    }
}
