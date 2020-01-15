package dev.zmq.m3h.Audio;

public class SongListData {
    private String title;
    private String mName;
    private String sName;
    private int imgId;

    public SongListData(String title, String mName, String sName, int imgId) {
        this.title = title;
        this.mName = mName;
        this.sName = sName;
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
