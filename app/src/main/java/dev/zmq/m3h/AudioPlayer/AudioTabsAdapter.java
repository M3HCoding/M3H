package dev.zmq.m3h.AudioPlayer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import dev.zmq.m3h.AudioPlayer.FMRadioAudioPlayer.FMRadioAudioPlayer;
import dev.zmq.m3h.AudioPlayer.OfflineAudioPlayer.OfflineAudioPlayer;
import dev.zmq.m3h.AudioPlayer.OnlineAudioPlayer.OnlineAudioPlayer;

public class AudioTabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public AudioTabsAdapter(FragmentManager fm, int NoofTabs){
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                OnlineAudioPlayer onlineAudioPlayer = new OnlineAudioPlayer();
                return onlineAudioPlayer;
            case 1:
                OfflineAudioPlayer offlineAudioPlayer = new OfflineAudioPlayer();
                return offlineAudioPlayer;
            case 2:
                FMRadioAudioPlayer fmRadioAudioPlayer = new FMRadioAudioPlayer();
                return fmRadioAudioPlayer;
            default:
                return null;
        }
    }
}