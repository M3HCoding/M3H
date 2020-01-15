package dev.zmq.m3h.Video;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import dev.zmq.m3h.R;

public class VideoAdapter extends RecyclerView.Adapter<View_Holder> {

    List<VideoDataList> list = Collections.emptyList();
    Context context;

    public VideoAdapter(List<VideoDataList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.video_item, parent, false );
        View_Holder holder = new View_Holder( v );
        return holder;

    }

    @Override
    public void onBindViewHolder(View_Holder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.title.setText( list.get( position ).title );
        holder.description.setText( list.get( position ).description );
        holder.imageView.setImageResource( list.get( position ).imageId );

        //animate(holder);

    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView( recyclerView );
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, VideoDataList data) {
        list.add( position, data );
        notifyItemInserted( position );
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(VideoDataList data) {
        int position = list.indexOf( data );
        list.remove( position );
        notifyItemRemoved( position );
    }

}

class View_Holder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView title;
    TextView description;
    ImageView imageView;

    View_Holder(View itemView) {
        super( itemView );
        cv = (CardView) itemView.findViewById( R.id.video_item_cardView );
        title = (TextView) itemView.findViewById( R.id.txt_video_title );
        description = (TextView) itemView.findViewById( R.id.txt_video_description );
        imageView = (ImageView) itemView.findViewById( R.id.img_video_item );
    }
}
