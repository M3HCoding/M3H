package dev.zmq.m3h.Wallpaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import dev.zmq.m3h.R;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.CustomViewHolder> {

    private List<GalleryResponse> dataList;
    private Context context;

    public GalleryAdapter(Context context, List<GalleryResponse> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from( parent.getContext() );
        View view = layoutInflater.inflate( R.layout.gallery_row_item, parent, false );
        return new CustomViewHolder( view );
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.txtTitle.setText( dataList.get( position ).getTitle() );

        Picasso.Builder builder = new Picasso.Builder( context );
        builder.downloader( new OkHttp3Downloader( context ) );
        builder.build().load( dataList.get( position ).getThumbnailUrl() )
                .placeholder( (R.drawable.ic_launcher_background) )
                .error( R.drawable.ic_launcher_background )
                .into( holder.coverImage );

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtTitle;
        private ImageView coverImage;

        CustomViewHolder(View itemView) {
            super( itemView );
            mView = itemView;

            txtTitle = mView.findViewById( R.id.txt_gallery );
            coverImage = mView.findViewById( R.id.iv_gallery );
        }
    }
}
