package dev.zmq.m3h.Video;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dev.zmq.m3h.R;

public class Videos extends Fragment
{
    private View view;
    private RecyclerView recyclerView;
    private VideoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate( R.layout.videos,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.rv_video);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        List<VideoDataList> data = fill_with_data();


        adapter = new VideoAdapter(data, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private List<VideoDataList> fill_with_data() {
        List<VideoDataList> data = new ArrayList<>();

        data.add(new VideoDataList("Batman vs Superman", "Following the destruction of Metropolis, Batman embarks on a personal vendetta against Superman ", R.drawable.ic_video));
        data.add(new VideoDataList("X-Men: Apocalypse", "X-Men: Apocalypse is an upcoming American superhero film based on the X-Men characters that appear in Marvel Comics ", R.drawable.ic_video));
        data.add(new VideoDataList("Captain America: Civil War", "A feud between Captain America and Iron Man leaves the Avengers in turmoil.  ", R.drawable.ic_video));
        data.add(new VideoDataList("Kung Fu Panda 3", "After reuniting with his long-lost father, Po  must train a village of pandas", R.drawable.ic_video));
        data.add(new VideoDataList("Warcraft", "Fleeing their dying home to colonize another, fearsome orc warriors invade the peaceful realm of Azeroth. ", R.drawable.ic_video));
        data.add(new VideoDataList("Alice in Wonderland", "Alice in Wonderland: Through the Looking Glass ", R.drawable.ic_video));

        return data;
    }
}
