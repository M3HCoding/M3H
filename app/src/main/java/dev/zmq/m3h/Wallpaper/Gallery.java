package dev.zmq.m3h.Wallpaper;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import dev.zmq.m3h.Interface.Interface_API;
import dev.zmq.m3h.R;
import dev.zmq.m3h.Retrofit_API.Retrofit_API;
import retrofit2.Callback;
import retrofit2.Response;

public class Gallery extends Fragment
{
    private View view;
    private GalleryAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        view=inflater.inflate( R.layout.gallery,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated( savedInstanceState );

        progressDoalog = new ProgressDialog(getActivity());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        Interface_API interface_api= Retrofit_API.getRetrofit_API().create(Interface_API.class );

        retrofit2.Call<List<GalleryResponse>> call=interface_api.getAllPhotos();
        call.enqueue( new Callback<List<GalleryResponse>>() {
            @Override
            public void onResponse(retrofit2.Call<List<GalleryResponse>> call, Response<List<GalleryResponse>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(retrofit2.Call<List<GalleryResponse>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText( getActivity(),"Something went wrong...Please try later!",Toast.LENGTH_LONG ).show();
            }
        } );
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<GalleryResponse> photoList)
    {
        recyclerView =(RecyclerView)view.findViewById(R.id.rv_gallery);
        adapter = new GalleryAdapter(getActivity(),photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle( "Wallpaper" );
    }
}
