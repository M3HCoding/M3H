package dev.zmq.m3h.Audio;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import dev.zmq.m3h.R;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder>{
    private SongListData[] listData;
    public SongsAdapter(SongListData[] listData) {
        this.listData = listData;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate( R.layout.audio_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SongListData myListData = listData[position];
        holder.txt_Title.setText( listData[position].getTitle());
        holder.txt_mName.setText( listData[position].getmName());
        holder.txt_sName.setText( listData[position].getsName());
        holder.img_album.setImageResource( listData[position].getImgId());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+myListData.getTitle(),Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_album;
        public TextView txt_Title,txt_mName,txt_sName;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.img_album = (ImageView) itemView.findViewById(R.id.img_audio_item);
            this.txt_Title = (TextView) itemView.findViewById(R.id.txt_audio_item_title);
            this.txt_mName = (TextView) itemView.findViewById(R.id.txt_audio_item_moviesName);
            this.txt_sName = (TextView) itemView.findViewById(R.id.txt_audio_item_singerName);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_audio_item);
        }
    }
}