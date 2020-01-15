package dev.zmq.m3h.News;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import dev.zmq.m3h.R;


public class News extends Fragment {
    static final String KEY_AUTHOR = "author";
    static final String KEY_TITLE = "title";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_URL = "url";
    static final String KEY_URLTOIMAGE = "urlToImage";
    static final String KEY_PUBLISHEDAT = "publishedAt";
    private String API_KEY = "64a4cdedb68f487aa44cb26782052117"; // ### YOUE NEWS API HERE ###
    private String NEWS_SOURCE = "techcrunch"; // Other news source code at: https://newsapi.org/sources
    private ListView listNews;
    private ProgressBar loader;
    private ArrayList<HashMap<String, String>> dataList = new ArrayList<>();
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.news, container, false );

        listNews = view.findViewById( R.id.listNews );
        loader = view.findViewById( R.id.loader );
        listNews.setEmptyView( loader );


        if (Function_.isNetworkAvailable( getActivity() )) {
            DownloadNews newsTask = new DownloadNews();
            newsTask.execute();
        } else {
            Toast.makeText( getActivity(), "No Internet Connection", Toast.LENGTH_LONG ).show();
        }
        return view;
    }


    class DownloadNews extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            String xml = Function_.excuteGet( "https://newsapi.org/v1/articles?source=" + NEWS_SOURCE + "&sortBy=top&apiKey=" + API_KEY );
            return xml;
        }

        @Override
        protected void onPostExecute(String xml) {

            if (xml.length() > 10) { // Just checking if not empty

                try {
                    JSONObject jsonResponse = new JSONObject( xml );
                    JSONArray jsonArray = jsonResponse.optJSONArray( "articles" );

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject( i );
                        HashMap<String, String> map = new HashMap<>();
                        map.put( KEY_AUTHOR, jsonObject.optString( KEY_AUTHOR ) );
                        map.put( KEY_TITLE, jsonObject.optString( KEY_TITLE ) );
                        map.put( KEY_DESCRIPTION, jsonObject.optString( KEY_DESCRIPTION ) );
                        map.put( KEY_URL, jsonObject.optString( KEY_URL ) );
                        map.put( KEY_URLTOIMAGE, jsonObject.optString( KEY_URLTOIMAGE ) );
                        map.put( KEY_PUBLISHEDAT, jsonObject.optString( KEY_PUBLISHEDAT ) );
                        dataList.add( map );
                    }
                } catch (JSONException e) {
                    Toast.makeText( getContext(), "Unexpected error", Toast.LENGTH_SHORT ).show();
                }

                ListNewsAdapter adapter = new ListNewsAdapter( getActivity(), dataList );
                listNews.setAdapter( adapter );

                listNews.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent( getActivity(), DetailsActivity.class );
                        i.putExtra( "url", dataList.get( +position ).get( KEY_URL ) );
                        startActivity( i );
                    }
                } );

            } else {
                Toast.makeText( getActivity(), "No news found", Toast.LENGTH_SHORT ).show();
            }
        }
    }

}
