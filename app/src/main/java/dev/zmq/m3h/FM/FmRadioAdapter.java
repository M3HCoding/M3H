package dev.zmq.m3h.FM;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import dev.zmq.m3h.R;

public class FmRadioAdapter extends RecyclerView.Adapter<dev.zmq.m3h.FM.FmRadioAdapter.ViewHolder> {
    private FMListData[] listData;
    public FmRadioAdapter(FMListData[] listData) {
        this.listData = listData;
    }

    @Override
    public dev.zmq.m3h.FM.FmRadioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from( parent.getContext() );
        View listItem = layoutInflater.inflate( R.layout.fm_radio_item, parent, false );
        dev.zmq.m3h.FM.FmRadioAdapter.ViewHolder viewHolder = new dev.zmq.m3h.FM.FmRadioAdapter.ViewHolder( listItem );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FmRadioAdapter.ViewHolder holder, int position) {
        final FMListData myListData = listData[position];
        holder.txt_Title.setText( listData[position].getTitle() );
        holder.txt_sName.setText( listData[position].getStationName() );
        holder.img_album.setImageResource( listData[position].getImgId() );
        holder.relativeLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( view.getContext(), "click on item: " + myListData.getTitle(), Toast.LENGTH_LONG ).show();
            }
        } );
    }


    @Override
    public int getItemCount() {
        return listData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_album;
        public TextView txt_Title, txt_sName;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super( itemView );
            this.img_album = (ImageView) itemView.findViewById( R.id.img_fm_radio_item );
            this.txt_Title = (TextView) itemView.findViewById( R.id.txt_fm_radio_item_title );
            this.txt_sName = (TextView) itemView.findViewById( R.id.txt_fm_radio_item_stationName );
            relativeLayout = (RelativeLayout) itemView.findViewById( R.id.rl_fm_radio_item );
        }
    }
}
